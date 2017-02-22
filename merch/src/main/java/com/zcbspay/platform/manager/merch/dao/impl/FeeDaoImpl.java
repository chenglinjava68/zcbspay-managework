package com.zcbspay.platform.manager.merch.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zcbspay.platform.manager.dao.impl.HibernateBaseDAOImpl;
import com.zcbspay.platform.manager.merch.bean.BusiRateEntity;
import com.zcbspay.platform.manager.merch.bean.CardRateEntity;
import com.zcbspay.platform.manager.merch.bean.CommonRateEntity;
import com.zcbspay.platform.manager.merch.bean.FeeEntity;
import com.zcbspay.platform.manager.merch.bean.NewCardRateEntity;
import com.zcbspay.platform.manager.merch.bean.ProductBean;
import com.zcbspay.platform.manager.merch.dao.FeeDao;
import com.zcbspay.platform.manager.merch.dao.ProductDao;

@Repository
public class FeeDaoImpl extends HibernateBaseDAOImpl<FeeEntity > implements FeeDao {

	@Override
	public List<?> queryFeeAll() {
		String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = 1;
        return executeOracleProcedure(
                "{CALL  PCK_T_FEE.sel_fee_date_all(?,?)}", columns, paramaters,
                "cursor0");
	}

	@Override
	public Map<String, Object> findBusiRateByPage(Map<String, Object> variables, String page, String rows) {
		 String[] columns = new String[]{"v_feever", "v_feename", "v_busicode",
	                "v_businame", "v_rate_type", "i_no", "i_perno"};

	        Object[] paramaters = new Object[]{
	                variables.containsKey("feever")
	                        ? variables.get("feever")
	                        : null,
	                variables.containsKey("feename")
	                        ? variables.get("feename")
	                        : null,
	                variables.containsKey("busicode")
	                        ? variables.get("busicode")
	                        : null,
	                variables.containsKey("businame")
	                        ? variables.get("businame")
	                        : null,
	                variables.containsKey("rate_type")
	                        ? variables.get("rate_type")
	                        : null,

	                page, rows};
	        return executePageOracleProcedure(
	                "{CALL PCK_T_BUSI_RATE.sel_t_busi_rate(?,?,?,?,?,?,?,?,?)}",
	                columns, paramaters, "cursor0", "v_total");
	}

	@Override
	public List<?> queryFeeCaseByFeever(String feever) {
		String[] columns = new String[]{"v_feever"};
        Object[] paramaters = new Object[1];
        paramaters[0] = feever;
        return executeOracleProcedure(
                "{CALL  PCK_T_FEE_CASE.sel_fee_case_by_feever(?,?)}", columns,
                paramaters, "cursor0");
	}

	@Override
	public Map<String, Object> queryOneBusiRate(String caseid) {
		String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[]{caseid};
        return executeOracleProcedure(
                "{CALL PCK_T_BUSI_RATE.sel_t_busi_date(?,?)}", columns,
                paramaters, "cursor0").get(0);
	}

	@Override
	public String updateBusiRate(BusiRateEntity busirate) {
		Object[] paramaters = new Object[]{
                busirate.getFeever(),
                busirate.getBusicode(),
                busirate.getFeeRate(),
                busirate.getMinFee() == null ? 0.00f : busirate.getMinFee()
                        .doubleValue(),
                busirate.getMaxFee() == null ? 0.00f : busirate.getMaxFee()
                        .doubleValue(), busirate.getInuser(),
                busirate.getRateType(), busirate.getNotes(),
                busirate.getRemarks()};

        String[] columns = new String[]{"v_feever", "v_busicode",
                " v_fee_rate", "v_min_fee", "v_max_fee", "v_inuser",
                "v_rate_type", "v_notes", "v_remarks"};
        Object total =executeOracleProcedure(
                        "{CALL PCK_T_BUSI_RATE.upt_t_busi_rate(?,?,?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
	}

	@Override
	public String addOneBusiRate(BusiRateEntity busiRate) {
		Object[] paramaters = new Object[]{
				busiRate.getFeever(),
				busiRate.getBusicode(),
				busiRate.getFeeRate(),
				busiRate.getMinFee() == null ? 0.00f : busiRate.getMinFee()
                        .doubleValue(),
                        busiRate.getMaxFee() == null ? 0.00f : busiRate.getMaxFee()
                        .doubleValue(), busiRate.getRateType(),
                        busiRate.getInuser(), busiRate.getNotes(),
                        busiRate.getRemarks()};

        String[] columns = new String[]{"v_feever", "v_busicode",
                " v_fee_rate", "v_min_fee", "v_max_fee", "v_rate_type",
                "v_inuser", "v_notes", "v_remarks"};
        Object total = executeOracleProcedure(
                        "{CALL PCK_T_BUSI_RATE.ins_t_busi_rate(?,?,?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
	}

	@Override
	public Map<String, Object> findCardRateByPage(Map<String, Object> variables, String page, String rows) {
		String[] columns = new String[]{"v_feever", "v_busicode", "v_feename",
                "v_cardtype", "v_rate_type", "i_no", "i_perno"};
        Object[] paramaters = new Object[]{
                variables.containsKey("feever")
                        ? variables.get("feever")
                        : null,
                variables.containsKey("busicode")
                        ? variables.get("busicode")
                        : null,
                null,
                variables.containsKey("cardtype")
                        ? variables.get("cardtype")
                        : null, null, page, rows};
        return executePageOracleProcedure(
                "{CALL PCK_T_CARD_RATE.sel_t_card_rate(?,?,?,?,?,?,?,?,?)}",
                columns, paramaters, "cursor0", "v_total");
	}

	@Override
	public Map<String, Object> queryOneCardRate(String caseid) {
		 String[] columns = new String[]{"v_in"};
	        Object[] paramaters = new Object[]{caseid};
	        return executeOracleProcedure(
	                "{CALL PCK_T_CARD_RATE.sel_t_card_date(?,?)}", columns,
	                paramaters, "cursor0").get(0);
	}

	@Override
	public String updateCardRate(CardRateEntity cardrate) {
		Object[] paramaters = new Object[] { cardrate.getFeever(), cardrate.getBusicode(), cardrate.getCardtype(),
				cardrate.getFeeRate(), cardrate.getMinFee() == null ? 0.00f : cardrate.getMinFee().doubleValue(),
				cardrate.getMaxFee() == null ? 0.00f : cardrate.getMaxFee().doubleValue(), cardrate.getRateType(),
				cardrate.getInuser(), cardrate.getNotes(), cardrate.getRemarks() };
		String[] columns = new String[] { "v_feever", "v_busicode", "v_cardtype", " v_fee_rate", "v_min_fee",
				"v_max_fee", "v_rate_type", "v_inuser", "v_notes", "v_remarks" };
		Object total = executeOracleProcedure("{CALL PCK_T_CARD_RATE.upt_t_card_rate(?,?,?,?,?,?,?,?,?,?,?)}",
				columns, paramaters, "cursor0").get(0).get("INFO");
		return (String) total;
	}

	@Override
	public String addOneCardRate(CardRateEntity cardrate) {
		Object[] paramaters = new Object[]{
                cardrate.getFeever(),
                cardrate.getBusicode(),
                cardrate.getCardtype(),
                cardrate.getFeeRate(),
                cardrate.getMinFee() == null ? 0.00f : cardrate.getMinFee()
                        .doubleValue(),
                cardrate.getMaxFee() == null ? 0.00f : cardrate.getMaxFee()
                        .doubleValue(), cardrate.getRateType(),
                cardrate.getInuser(), cardrate.getNotes(),
                cardrate.getRemarks()};
        String[] columns = new String[]{"v_feever", "v_busicode", "v_cardtype",
                " v_fee_rate", "v_min_fee", "v_max_fee", "v_rate_type",
                "v_inuser", "v_notes", "v_remarks"};
        Object total =executeOracleProcedure(
                        "{CALL PCK_T_CARD_RATE.ins_t_card_rate(?,?,?,?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
	}

	@Override
	public String addOneCommonRate(CommonRateEntity rateModel) {
		Object[] paramaters = new Object[]{
				rateModel.getRateId(),
				rateModel.getRateDes(),
				rateModel.getFeeRate(),
				rateModel.getMinFee() == null ? 0.00f : rateModel.getMinFee()
                        .doubleValue(),
                        rateModel.getMaxFee() == null ? 0.00f : rateModel.getMaxFee()
                        .doubleValue(), rateModel.getRateType(),
                        rateModel.getInuser(), rateModel.getNotes(),
                        rateModel.getRemarks()};

        String[] columns = new String[]{"v_RATE_ID", "v_RATE_DESC",
                " v_FEE_RATE", "v_MIN_FEE", "v_MAX_FEE", "v_RATE_TYPE",
                "v_INUSER", "v_NOTES", "v_REMARKS"};
        Object total = executeOracleProcedure(
                        "{CALL PCK_T_RATE_BUSI.ins_T_RATE_BUSI(?,?,?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
	}

	@Override
	public List<?> queryRateType() {
		String sql = "select * from t_para_dic t where t.para_type='MERRATETYPE' and para_code!='04'";
		return queryBySQL(sql, null);
	}

	@Override
	public Map<String, Object> findCommonRateByPage(Map<String, Object> variables, String page, String rows) {
		 String[] columns = new String[]{"v_RATE_ID", "v_RATE_DESC", "v_rate_type",
	                "i_no", "i_perno"};

	        Object[] paramaters = new Object[]{
	                variables.containsKey("rateId")
	                        ? variables.get("rateId")
	                        : null,
	                variables.containsKey("rateDes")
	                        ? variables.get("rateDes")
	                        : null,
	                variables.containsKey("rateType")
	                        ? variables.get("rateType")
	                        : null,
	                page, rows};
	        return executePageOracleProcedure(
	                "{CALL PCK_T_RATE_BUSI.sel_T_RATE_BUSI(?,?,?,?,?,?,?)}",
	                columns, paramaters, "cursor0", "v_total");
	}

	@Override
	public Map<String, Object> queryOneCommonRate(String caseid) {
		String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[]{caseid};
        return executeOracleProcedure(
                "{CALL PCK_T_RATE_BUSI.sel_t_busi_data(?,?)}", columns,
                paramaters, "cursor0").get(0);
	}

	@Override
	public String updateCommonRate(CommonRateEntity rateEntity) {
		Object[] paramaters = new Object[]{
				rateEntity.getRateId(),
				rateEntity.getRateDes(),
				rateEntity.getFeeRate(),
				rateEntity.getMinFee() == null ? 0.00f : rateEntity.getMinFee()
                        .doubleValue(),
                        rateEntity.getMaxFee() == null ? 0.00f : rateEntity.getMaxFee()
                        .doubleValue(),rateEntity.getRateType(), rateEntity.getInuser(),
                         rateEntity.getNotes(),
                        rateEntity.getRemarks()};

        String[] columns = new String[]{"v_RATE_ID", "v_RATE_DESC",
                " v_FEE_RATE", "v_MIN_FEE", "v_MAX_FEE", "v_RATE_TYPE",
                "v_INUSER", "v_NOTES", "v_REMARKS"};
        Object total =executeOracleProcedure(
                        "{CALL PCK_T_RATE_BUSI.upt_T_RATE_BUSI(?,?,?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
	}

	@Override
	public String saveNewCardRate(NewCardRateEntity cardRate) {
		Object[] paramaters = new Object[]{
                cardRate.getRateId(),
                cardRate.getRateDes(),
                cardRate.getFeeRateD(),
                cardRate.getMinFeeD() == null ? 0.00f : Double.valueOf(cardRate.getMinFeeD()).doubleValue(),
                cardRate.getMaxFeeD() == null ? 0.00f : Double.valueOf(cardRate.getMaxFeeD()).doubleValue(), 
                cardRate.getRateTypeD(),
                cardRate.getFeeRateC(),
                cardRate.getMinFeeC() == null ? 0.00f : Double.valueOf(cardRate.getMinFeeC()).doubleValue(),
                cardRate.getMaxFeeC() == null ? 0.00f : Double.valueOf(cardRate.getMaxFeeC()).doubleValue(), 
                cardRate.getRateTypeC(),
                cardRate.getFeeRateSC(),
                cardRate.getMinFeeSC() == null ? 0.00f : Double.valueOf(cardRate.getMinFeeSC()).doubleValue(),
                cardRate.getMaxFeeSC() == null ? 0.00f : Double.valueOf(cardRate.getMaxFeeSC()).doubleValue(), 
                cardRate.getRateTypeSC(),
                cardRate.getInuser(), 
                cardRate.getNotes(),
                cardRate.getRemarks()};
        String[] columns = new String[]{					
        		"v_RATE_ID",
        		"v_RATE_DESC",
        		"v_FEE_RATE_D",
        		"v_MIN_FEE_D",
        		"v_MAX_FEE_D",
        		"v_RATE_TYPE_D",
        		"v_FEE_RATE_C",
        		"v_MIN_FEE_C",
        		"v_MAX_FEE_C",
        		"v_RATE_TYPE_C",
        		"v_FEE_RATE_SC",
        		"v_MIN_FEE_SC",
        		"v_MAX_FEE_SC",
        		"v_RATE_TYPE_SC",
        		"v_INUSER",
        		"v_NOTES",
        		"v_REMARKS",
        		};
        Object total =executeOracleProcedure(
                        "{CALL PCK_t_rate_card.ins_t_rate_card(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
	}

	@Override
	public Map<String, Object> findNewCardRateByPage(NewCardRateEntity variables, String page, String rows) {
		String[] columns = new String[]{"v_RATE_ID", "v_RATE_DESC", "v_rate_type_d",
                "v_rate_type_c", "v_rate_type_sc", "i_no", "i_perno"};
        Object[] paramaters = new Object[]{
                variables.getRateId(),
                variables.getRateDes(),
                null, null, null, page, rows};
        return executePageOracleProcedure(
                "{CALL PCK_t_rate_card.sel_t_rate_card(?,?,?,?,?,?,?,?,?)}",
                columns, paramaters, "cursor0", "v_total");
	}

	@Override
	public Map<String, Object> queryOneNewCardRate(String caseid) {
		 String[] columns = new String[]{"v_in"};
	        Object[] paramaters = new Object[]{caseid};
	        return executeOracleProcedure(
	                "{CALL PCK_t_rate_card.sel_t_busi_data(?,?)}", columns,
	                paramaters, "cursor0").get(0);
	}
	

}
