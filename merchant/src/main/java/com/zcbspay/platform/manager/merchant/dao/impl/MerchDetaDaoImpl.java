package com.zcbspay.platform.manager.merchant.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zcbspay.platform.manager.dao.impl.HibernateBaseDAOImpl;
import com.zcbspay.platform.manager.merchant.bean.MerchBean;
import com.zcbspay.platform.manager.merchant.dao.MerchDetaDAO;
import com.zcbspay.platform.manager.merchant.pojo.PojoMerchDetaApply;

@Repository
@SuppressWarnings("all")
public class MerchDetaDaoImpl extends HibernateBaseDAOImpl<PojoMerchDetaApply> implements MerchDetaDAO {


	public List<?> queryMerchParent() {
		return executeOracleProcedure("{CALL  PCK_FOR_SELECT.sel_parent_merch (?)}", new String[]{},new Object[]{}, "cursor0");
	}

	public Map<String, Object> findMerchByPage(Map<String, Object> variables, Integer page, Integer rows) {
		 String[] columns = new String[]{"v_user", "v_member_id",
	                "v_merch_name", "v_address", "v_status", "v_coop_insti_id",
	                "v_flag", "i_no", "i_perno"};

	        Object[] paramaters = new Object[]{
	                variables.containsKey("userId")
	                        ? variables.get("userId")
	                        : null,
	                variables.containsKey("merberId")
	                        ? variables.get("merberId")
	                        : null,
	                variables.containsKey("merchName")
	                        ? variables.get("merchName")
	                        : null,
	                variables.containsKey("address")
	                        ? variables.get("address")
	                        : null,
	                variables.containsKey("status")
	                        ? variables.get("status")
	                        : null,
	                variables.containsKey("coopInstiId") ? variables
	                        .get("coopInstiId") : null,
	                variables.containsKey("flag") ? variables.get("flag") : null,
	                page, rows};
	        // busiAcctServiceImpl.openBusiAcct(member, busiAcct, userId)
	        return executePageOracleProcedure(
	                "{CALL PCK_MERCH.sel_t_merchant(?,?,?,?,?,?,?,?,?,?,?)}",
	                columns, paramaters, "cursor0", "v_total");

	}


	@Override
	public List<Map<String, String>> saveMerchDeta(MerchBean merch) {

//        PojoEnterpriseDeta enterprise = merch.getMember();
//
//        boolean isRepeat = isRepeat(merch.getMember().getEmail(), enterprise.getPhone(),enterprise.getInstiCode());
//
//        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
//        Map<String, String> resultMap = new HashMap<String, String>();
//        if (isRepeat) {
//            resultMap.put("RET", "fail");
//            resultMap.put("INFO", "手机号或邮箱重复");
//            result.add(resultMap);
//            return result;
//        }
//
//        MccListModel mccList = daoContainer.getMccListDAO().get(enterprise.getMccList());
//        enterprise.setMcc(mccList.getMcc());
//
//        String[] columns = new String[]{"v_parent", "v_setlcycle",
//                "v_setltype", "v_bankcode", "v_banknode", "v_accnum",
//                "v_accname", "v_charge", "v_deposit", "v_agreemt_start",
//                "v_agreemt_end", "v_prdtver", "v_feever", "v_spiltver",
//                "v_riskver", "v_routver", "v_inuser", "v_notes", "v_remarks",
//                "v_merch_name_e", "v_coop_insti_id_e", "v_cellphoneno",
//                "v_mcc_e", "v_mcclist_e", "v_merchinsti_e", "v_province_e",
//                "v_city_e", "v_street_e", "v_postcode_e", "v_address_e",
//                "v_email_e", "v_website_e", "v_cardtype_e", "v_taxno_e",
//                "v_licenceno_e", "v_orgcode_e", "v_corporation_e",
//                "v_corpno_e", "v_contact_e", "v_contphone_e", "v_conttitle_e",
//                "v_contemail_e", "v_contaddress_e", "v_contpost_e",
//                "v_custfrom_e", "v_custmgr_e", "v_custmgrdept_e",
//                "v_isdelegation_e", "v_signatory_e", "v_signcertno_e",
//                "v_notes_e", "v_remarks_e"};
//        Object[] paramaters = new Object[]{
//                "".equals(merch.getParent()) ? null : "".equals(merch
//                        .getParent()),
//                "".equals(merch.getSetlCycle()) ? null : merch.getSetlCycle(),
//                "".equals(merch.getSetlType()) ? null : merch.getSetlType(),
//                "".equals(merch.getBankCode()) ? null : merch.getBankCode(),
//                "".equals(merch.getBankNode()) ? null : merch.getBankNode(),
//                "".equals(merch.getAccNum()) ? null : merch.getAccNum(),
//                "".equals(merch.getAccName()) ? null : merch.getAccName(),
//                merch.getCharge().toYuan(),
//                merch.getDeposit().toYuan(),
//                merch.getAgreemtStart()==null? null : new Timestamp(merch.getAgreemtStart().getTime()),
//                merch.getAgreemtEnd()==null ? null : new Timestamp(merch.getAgreemtEnd().getTime()),
//                "".equals(merch.getPrdtVer()) ? null : merch.getPrdtVer(),
//                "".equals(merch.getFeeVer()) ? null : merch.getFeeVer(),
//                "".equals(merch.getSpiltVer()) ? null : merch.getSpiltVer(),
//                "".equals(merch.getRiskVer()) ? null : merch.getRiskVer(),
//                "".equals(merch.getRoutVer()) ? null : merch.getRoutVer(),
//                "".equals(enterprise.getInUser()) ? null : enterprise
//                        .getInUser(),
//                "".equals(merch.getNotes()) ? null : merch.getNotes(),
//                "".equals(merch.getRemarks()) ? null : merch.getRemarks(),
//                "".equals(enterprise.getEnterpriseName()) ? null : enterprise
//                        .getEnterpriseName(),
//                "".equals(enterprise.getCoopInstiId()) ? null : enterprise
//                        .getCoopInstiId(),
//                "".equals(enterprise.getPhone()) ? null : enterprise.getPhone(),
//
//                "".equals(enterprise.getMcc()) ? null : enterprise.getMcc(),
//                "".equals(enterprise.getMccList()) ? null : enterprise
//                        .getMccList(),
//                "".equals(enterprise.getEnterpriseInsti()) ? null : enterprise
//                        .getEnterpriseInsti(),
//                "".equals(enterprise.getProvince()) ? null : enterprise
//                        .getProvince(),
//                "".equals(enterprise.getCity()) ? null : enterprise.getCity(),
//                "".equals(enterprise.getStreet()) ? null : enterprise
//                        .getStreet(),
//                "".equals(enterprise.getPostCode()) ? null : enterprise
//                        .getPostCode(),
//                "".equals(enterprise.getAddress()) ? null : enterprise
//                        .getAddress(),
//                "".equals(enterprise.getEmail()) ? null : enterprise.getEmail(),
//                "".equals(enterprise.getWebsite()) ? null : enterprise
//                        .getWebsite(),
//                "".equals(enterprise.getCardType()) ? null : enterprise
//                        .getCardType(),
//                "".equals(enterprise.getTaxno()) ? null : enterprise.getTaxno(),
//                "".equals(enterprise.getLicenceNo()) ? null : enterprise
//                        .getLicenceNo(),
//                "".equals(enterprise.getOrgCode()) ? null : enterprise
//                        .getOrgCode(),
//                "".equals(enterprise.getCorporation()) ? null : enterprise
//                        .getCorporation(),
//                "".equals(enterprise.getCorpNo()) ? null : enterprise
//                        .getCorpNo(),
//                "".equals(enterprise.getContact()) ? null : enterprise
//                        .getContact(),
//                "".equals(enterprise.getContPhone()) ? null : enterprise
//                        .getContPhone(),
//                "".equals(enterprise.getContTitle()) ? null : enterprise
//                        .getContTitle(),
//                "".equals(enterprise.getContEmail()) ? null : enterprise
//                        .getContEmail(),
//                "".equals(enterprise.getContAddress()) ? null : enterprise
//                        .getContAddress(),
//                "".equals(enterprise.getContPost()) ? null : enterprise
//                        .getContPost(),
//                "".equals(enterprise.getCustFrom()) ? null : enterprise
//                        .getCustFrom(),
//                "".equals(enterprise.getCustMgr()) ? null : enterprise
//                        .getCustMgr(),
//                "".equals(enterprise.getCustMgrDept()) ? null : enterprise
//                        .getCustMgrDept(),
//                "".equals(enterprise.getIsDelegation()) ? null : enterprise
//                        .getIsDelegation(),
//                "".equals(enterprise.getSignatory()) ? null : enterprise
//                        .getSignatory(),
//                "".equals(enterprise.getSignCertNo()) ? null : enterprise
//                        .getSignCertNo(),
//                "".equals(enterprise.getNotes()) ? null : enterprise.getNotes(),
//                "".equals(enterprise.getRemarks()) ? null : enterprise
//                        .getRemarks()};
//        List<?> dbResult = executeOracleProcedure(
//                        "{CALL PCK_MERCH.pro_i_t_merch_deta(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}",
//                        columns, paramaters, "cursor0");
//        if (dbResult == null || dbResult.get(0) == null) {
//            resultMap.clear();
//            resultMap.put("RET", "fail");
//            resultMap.put("INFO", "操作失败,请联系技术人员");
//            result.add(resultMap);
//        } else {
//           
//            resultMap = (Map<String, String>) dbResult.get(0);
//        }
//        result.add(resultMap);
        return null;
    }

	@Override
	public List<?> queryCounty(String pid) {
		String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = pid;
        return executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_county(?,?)}", columns, paramaters,
                "cursor0");
    }

	@Override
    public List<?> queryMerchType() {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = 1;
        return executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_dic_MERCHTYPE(?,?)}", columns,
                paramaters, "cursor0");
    }
	@Override
	 public List<?> queryTrade() {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = 1;
        return executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_dic_TRADE(?,?)}", columns,
                paramaters, "cursor0");
    }

	@Override
	 public List<?> querysetltype() {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = 1;
        return executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_setltype(?,?)}", columns,
                paramaters, "cursor0");
    }

	@Override
	public List<?> queryBankNode(String bankName, Integer page, Integer rows) {
        String[] columns = new String[]{"v_bank_name", "v_bank_address",
                "i_no", "i_perno"};
        Object[] paramaters = new Object[4];
        paramaters[0] = bankName;
        paramaters[1] = null;
        paramaters[2] = page;
        paramaters[3] = rows;
        return executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_t_bank_info(?,?,?,?,?)}", columns,
                paramaters, "cursor0");
    }

	@Override
	public List<?> queryCashAll() {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = 1;
        return executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_cash(?,?)}", columns, paramaters,
                "cursor0");
    }

	@Override
    public List<?> queryChnlnameAll() {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = 1;
        return executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_chnlname(?,?)}", columns,
                paramaters, "cursor0");
    }
    @Override
    public List<?> queryRouteAll() {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = 1;
        return executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_route(?,?)}", columns, paramaters,
                "cursor0");
    }

    @Override
    public List<?> querySetlcycleAll() {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = 1;
        return executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_dic_setl_cycle(?,?)}", columns,
                paramaters, "cursor0");
    }

    // 查询产品下的分润版本
    @Override
    public List<?> querySplit(String pid) {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = pid;
        return executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_split(?,?)}", columns, paramaters,
                "cursor0");
    }

    // 查询产品下的风控版本
    @Override
    public List<?> queryRiskType(String pid) {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = pid;
        return executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_risk(?,?)}", columns, paramaters,
                "cursor0");
    }

    // 查询产品下的扣率版本
    @Override
    public List<?> queryFee(String pid) {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = pid;
        return executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_fee(?,?)}", columns, paramaters,
                "cursor0");
    }

}
