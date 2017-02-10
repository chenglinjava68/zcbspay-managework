package com.zcbspay.platform.manager.controller.risk;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcbspay.platform.manager.risk.bean.BlackIdnumBean;
import com.zcbspay.platform.manager.risk.bean.RiskBean;
import com.zcbspay.platform.manager.risk.bean.RiskCaseBean;
import com.zcbspay.platform.manager.risk.service.CardHolderBlackService;
import com.zcbspay.platform.manager.risk.service.RiskService;
import com.zcbspay.platform.manager.system.bean.UserBean;
import com.zcbspay.platform.manager.utils.StringUtil;

@Controller
@RequestMapping("/risk")
public class RiskController {
	@Autowired
	private RiskService riskService;
	@Autowired
	private CardHolderBlackService cardHolderBlackService;

	@RequestMapping("/index")
	public String showRisk() {
		return "/risk/risk_manager";
	}

	// 对应页面功能模块：风控管理；银行卡黑名单，银行卡白名单,商户黑名单，分卡种单笔限额

	/**
	 * 风控版本分页查询
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryRisk")
	public Map<String, Object> queryRisk(HttpServletRequest request, RiskBean risk, int page, int rows) {
		Map<String, Object> variables = new HashMap<String, Object>();
		UserBean loginUser = (UserBean) request.getSession().getAttribute("LOGIN_USER");
		variables.put("userId", loginUser.getUserId());
		if (risk == null) {
			risk = new RiskBean();
		}
		variables.put("riskver", risk.getRiskver());
		variables.put("riskname", risk.getRiskname());
		Map<String, Object> groupList = riskService.findRiskByPage(variables, page, rows);
		return groupList;
	}

	/**
	 * 保存风控版本
	 * 
	 * @param risk
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveRisk")
	public String saveRisk(HttpServletRequest request, RiskBean risk) {
		String result = "";
		UserBean loginUser = (UserBean) request.getSession().getAttribute("LOGIN_USER");
		if (risk == null || StringUtil.isEmpty(risk.getRiskver().trim())
				|| StringUtil.isEmpty(risk.getRiskname().trim())) {
			result = "风控版本代码或者风控名称不能为空";
			return null;
		}

		risk.setInuser(loginUser.getUserId());
		result = riskService.AddOneRisk(risk);
		return result;
	}

	@ResponseBody
	@RequestMapping("/queryOneRisk")
	public Map<String, Object> queryOneRisk(String riskId) {
		Map<String, Object> feeList = riskService.queryOneRisk(riskId);
		return feeList;
	}

	@ResponseBody
	@RequestMapping("/queryOneRiskCase")
	public Map<String, Object> queryOneRiskCase(String riskId) {
		Map<String, Object> feeList = riskService.queryOneRiskCase(riskId);
		return feeList;
	}

	@ResponseBody
	@RequestMapping("/updateOneRisk")
	public String updateOneRisk(HttpServletRequest request, RiskBean risk) {
		String result = "";
		UserBean loginUser = (UserBean) request.getSession().getAttribute("LOGIN_USER");
		if (risk == null || StringUtil.isEmpty(risk.getRiskver().trim())
				|| StringUtil.isEmpty(risk.getRiskname().trim())) {
			result = "风控版本代码或者风控名称不能为空";
			return result;
		}
		risk.setInuser(loginUser.getUserId());
		String mark = riskService.UpdateOneRisk(risk);
		return mark;
	}

	// 根据一条风控ID，查询下面的实例，给实例配置复选业务
	@RequestMapping("/toMakeRiskCase")
	public String toMakeRiskCase(String riskver) {
		return "/risk/risk_case_make";
	}

	@ResponseBody
	@RequestMapping("/queryRiskCase")
	public List<?> queryRiskCase(String riskver) {
		List<?> riskCaseList = riskService.queryRiskCase(riskver);
		return riskCaseList;
	}

	/**
	 * 风控策略(所有的策略)
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryRisklistCheck")
	public List<?> queryRisklistCheck() {
		List<?> riskcheckList = riskService.query_risk_list();
		return riskcheckList;
	}

	/**
	 * 风控策略(一条风控实例包含的风控策略做标记)
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryRisklistCheckActive")
	public List<?> queryRisklistCheckActive(String riskId) {
		List<?> riskcheckList = riskService.query_risk_list_active(riskId);
		return riskcheckList;
	}

	/**
	 * 修改风控实例，生成activeflag64位
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateRiskCase")
	public String updateRiskCase(HttpServletRequest request, RiskCaseBean riskCase, List<String> checkboxList) {
		if (riskCase == null) {
			riskCase = new RiskCaseBean();
		}
		StringBuffer activeflag = new StringBuffer("000000000000000000000000000000000000000000000000000000000000");
		if (checkboxList != null) {
			for (int i = 0; i < checkboxList.size(); i++) {
				activeflag.deleteCharAt(Integer.parseInt(checkboxList.get(i)) - 1);
				activeflag.insert(Integer.parseInt(checkboxList.get(i)) - 1, "1");
			}
		}
		UserBean loginUser = (UserBean) request.getSession().getAttribute("LOGIN_USER");
		riskCase.setUpuser(loginUser.getUserId());
		riskCase.setActiveflag(activeflag.toString());
		String mark = riskService.UpdateOneRiskCase(riskCase);
		return mark;
	}

	/**
	 * 风控版本
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryRisklist")
	public List<?> queryRisklist() {
		List<?> riskcheckList = riskService.query_risk_all();
		return riskcheckList;
	}

	/**
	 * 风控实例
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryRisklistCase")
	public List<?> queryRisklistCase(String riskId) {
		List<?> riskcheckList = riskService.query_risk_case_all(riskId);
		return riskcheckList;
	}

	// -------------------------------------------------------------银行卡黑名单-----------------------------------------------------------------------
	/*private BlacklistPanBean blackpanModel;

	public String showBlackPan() {
		return "black_pan_manager";
	}

	public String queryBlackPanByPage() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("userId", getCurrentUser().getUserId());
		if (blackpanModel == null) {
			blackpanModel = new BlacklistPanBean();
		}
		variables.put("pan", blackpanModel.getPan());
		Map<String, Object> groupList = serviceContainer.getRiskService().findBlackPanByPage(variables, getPage(),
				getRows());
		json_encode(groupList);
		return null;
	}

	// 风险等级
	public String queryRiskLevel() {
		List<?> riskcheckList = serviceContainer.getRiskService().query_risk_level();
		try {
			json_encode(riskcheckList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String saveBlackPan() {
		if (blackpanModel == null) {
			blackpanModel = new BlacklistPanBean();
		}
		String mark = serviceContainer.getRiskService().AddOneBlackPan(blackpanModel);
		json_encode(mark);
		return null;
	}

	public String queryOneBlackPan() {
		Map<String, Object> feeList = serviceContainer.getRiskService().queryOneBlackPan(riskId);
		json_encode(feeList);
		return null;
	}

	public String updateBlackPan() {
		if (blackpanModel == null) {
			blackpanModel = new BlacklistPanBean();
		}
		String mark = serviceContainer.getRiskService().updateOneBlackPan(blackpanModel);
		json_encode(mark);
		return null;
	}

	public String deleteBlackPan() {
		String mark = serviceContainer.getRiskService().deleteOneBlackPan(riskId);
		json_encode(mark);
		return null;
	}

	public String startBlackPan() {
		String mark = serviceContainer.getRiskService().startOneBlackPan(riskId);
		json_encode(mark);
		return null;
	}

	// --------------------------------------------------
	// 银行卡白名单------------------------------------------------------------------------
	private WhitePanModel whitepanModel;

	public String showWhitePan() {
		return "white_pan_manager";
	}

	public String queryWhitePanByPage() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("userId", getCurrentUser().getUserId());
		if (whitepanModel == null) {
			whitepanModel = new WhitePanModel();
		}
		variables.put("pan", whitepanModel.getPan());
		Map<String, Object> groupList = serviceContainer.getRiskService().findWhitePanByPage(variables, getPage(),
				getRows());
		json_encode(groupList);
		return null;
	}

	public String saveWhitePan() {
		if (whitepanModel == null) {
			whitepanModel = new WhitePanModel();
		}
		String mark = serviceContainer.getRiskService().AddOneWhitePan(whitepanModel);
		json_encode(mark);
		return null;
	}

	public String queryOneWhitePan() {
		Map<String, Object> feeList = serviceContainer.getRiskService().queryOneWhitePan(riskId);
		json_encode(feeList);
		return null;
	}

	public String updateWhitePan() {
		if (whitepanModel == null) {
			whitepanModel = new WhitePanModel();
		}
		String mark = serviceContainer.getRiskService().updateOneWhitePan(whitepanModel);
		json_encode(mark);
		return null;
	}

	public String deleteWhitePan() {
		String mark = serviceContainer.getRiskService().deleteOneWhitePan(riskId);
		json_encode(mark);
		return null;
	}

	public String startWhitePan() {
		String mark = serviceContainer.getRiskService().startOneWhitePan(riskId);
		json_encode(mark);
		return null;
	}

	// --------------------------------------------------------------卡类别日累计限额------------------------------------------------------
	private BlacklistMemberModel blacklistMemberModel;

	public String showBlacklistMember() {
		return "black_mem_manager";
	}

	public String queryBlacklistMemberByPage() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("userId", getCurrentUser().getUserId());
		if (blacklistMemberModel == null) {
			blacklistMemberModel = new BlacklistMemberModel();
		}
		variables.put("memberId", blacklistMemberModel.getMemberid());
		variables.put("merchName", merchName);
		Map<String, Object> LimitMemList = serviceContainer.getRiskService().findBlacklistMemberByPage(variables,
				getPage(), getRows());
		json_encode(LimitMemList);
		return null;
	}

	public String saveBlacklistMember() {
		if (blacklistMemberModel == null) {
			blacklistMemberModel = new BlacklistMemberModel();
		}
		String mark = serviceContainer.getRiskService().AddOneBlacklistMember(blacklistMemberModel);
		json_encode(mark);
		return null;
	}

	public String queryOneBlacklistMember() {
		Map<String, Object> onelimit = serviceContainer.getRiskService().queryOneBlacklistMember(riskId);
		json_encode(onelimit);
		return null;
	}

	public String updateBlacklistMember() {
		if (blacklistMemberModel == null) {
			blacklistMemberModel = new BlacklistMemberModel();
		}
		String mark = serviceContainer.getRiskService().updateBlacklistMember(blacklistMemberModel);
		json_encode(mark);
		return null;
	}

	public String deleteBlacklistMember() {
		String mark = serviceContainer.getRiskService().deleteOneBlacklistMember(riskId, getCurrentUser().getUserId());
		json_encode(mark);
		return null;
	}

	public String startBlacklistMember() {
		String mark = serviceContainer.getRiskService().startOneBlacklistMember(riskId, getCurrentUser().getUserId());
		json_encode(mark);
		return null;
	}

	// -------------------------------------------------------------------------------------------
	// --------------------------------------------------------------分卡种单笔限额------------------------------------------------------
	private LimitCreditSingleModel limitCreditSingleModel;

	public String showLimitCreditSingle() {
		return "limit_credit_single_manager";
	}

	public String queryLimitSingleByPage() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("userId", getCurrentUser().getUserId());
		if (limitCreditSingleModel == null) {
			limitCreditSingleModel = new LimitCreditSingleModel();
		}
		variables.put("caseid", limitCreditSingleModel.getCaseid());
		variables.put("riskver", riskver);
		Map<String, Object> LimitMemList = serviceContainer.getRiskService().findlimitCreditSingleByPage(variables,
				getPage(), getRows());
		json_encode(LimitMemList);
		return null;
	}

	public String saveLimitCreditSingle() {
		if (limitCreditSingleModel == null) {
			limitCreditSingleModel = new LimitCreditSingleModel();
		}
		String mark = serviceContainer.getRiskService().AddOnelimitCreditSingle(limitCreditSingleModel);
		json_encode(mark);
		return null;
	}

	public String queryLimitCreditSingle() {
		Map<String, Object> onelimit = serviceContainer.getRiskService().queryOnelimitCreditSingle(riskId);
		json_encode(onelimit);
		return null;
	}

	public String updateLimitCreditSingle() {
		if (limitCreditSingleModel == null) {
			limitCreditSingleModel = new LimitCreditSingleModel();
		}
		String mark = serviceContainer.getRiskService().updateOnelimitCreditSingle(limitCreditSingleModel);
		json_encode(mark);
		return null;
	}

	public String deleteLimitCreditSingle() {
		String mark = serviceContainer.getRiskService().deleteOnelimitCreditSingle(riskId,
				getCurrentUser().getUserId());
		json_encode(mark);
		return null;
	}

	public String startLimitCreditSingle() {
		String mark = serviceContainer.getRiskService().startOnelimitCreditSingle(riskId, getCurrentUser().getUserId());
		json_encode(mark);
		return null;
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	// -----------------------------------------------------------------持卡人黑名单------------------------------------------------
	/*
	 * private BlackIdnumModel blackIdnumModel;
	 * 
	 * public BlackIdnumModel getBlackIdnumModel() { return blackIdnumModel; }
	 * 
	 * public void setBlackIdnumModel(BlackIdnumModel blackIdnumModel) {
	 * this.blackIdnumModel = blackIdnumModel; }
	 * 
	 * public String showCardholderBlackList(){ return "cardholder_black"; }
	 * HttpServletRequest request = ServletActionContext.getRequest();
	 */
	
	@RequestMapping("/showCardholderBlackList")
	public String showCardholderBlackList(){ 
		return "cardholder_black_manager"; 
	}
	public final static String DEFAULT_TIME_STAMP_FROMAT2 = "yyyy-MM-dd";

	/**
	 * 根据身份证号查询持卡人黑名单
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryCardHolderBlackList")
	public Map<String, Object> queryCardHolderBlackList(BlackIdnumBean blackIdnumBean, int page, int rows) {
		Map<String, Object> variables = new HashMap<String, Object>();
		if (blackIdnumBean == null) {
			blackIdnumBean = new BlackIdnumBean();
		}
		variables.put("idnum", blackIdnumBean.getIdnum());

		Map<String, Object> result = cardHolderBlackService.queryCardHolderBlackList(variables, page, rows);
		return result;
	}

	/***
	 * 保存持卡人黑名单信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveCardHolderBlack")
	public String saveCardHolderBlack(BlackIdnumBean blackIdnumBean) {
		if (blackIdnumBean == null) {
			blackIdnumBean = new BlackIdnumBean();
		}
		String sdateString = blackIdnumBean.getSdate();
		String edateString = blackIdnumBean.getEdate();
		String[] sdate1 = sdateString.split("-");
		String[] edate1 = edateString.split("-");
		String sdate = "";
		String edate = "";
		for (int i = 0; i < sdate1.length; i++) {
			sdate = sdate + sdate1[i];
			edate = edate + edate1[i];
		}
		blackIdnumBean.setSdate(sdate);
		blackIdnumBean.setEdate(edate);
		String result = cardHolderBlackService.AddOneBlackCardHolder(blackIdnumBean);
		return result;
	}

	/**
	 * （根据选中的记录的tid）查询一条持卡人黑名单信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryOneBlackCardHolder")
	public Map<String, Object> queryOneBlackCardHolder(HttpServletRequest request) {
		String tid = request.getParameter("tid");
		Map<String, Object> blackCardHolder = cardHolderBlackService.queryOneBlackCardHolder(tid);
		String sdateString = (String) blackCardHolder.get("SDATE");
		String edateString = (String) blackCardHolder.get("EDATE");
		String sdate = sdateString.substring(0, 4) + "-" + sdateString.substring(4, 6) + "-"
				+ sdateString.substring(6, 8);
		String edate = edateString.substring(0, 4) + "-" + edateString.substring(4, 6) + "-"
				+ edateString.substring(6, 8);
		blackCardHolder.put("SDATE", sdate);
		blackCardHolder.put("EDATE", edate);

		return blackCardHolder;
	}

	/***
	 * 修改持卡人黑名单
	 */
	@ResponseBody
	@RequestMapping("/updateBlackCardHolder")
	public String updateBlackCardHolder(HttpServletRequest request, BlackIdnumBean blackIdnumBean) {
		String tid = request.getParameter("tid");
		blackIdnumBean.setTid(tid);
		String sdateString = blackIdnumBean.getSdate();
		String edateString = blackIdnumBean.getEdate();
		String[] sdate1 = sdateString.split("-");
		String[] edate1 = edateString.split("-");
		String sdate = "";
		String edate = "";
		for (int i = 0; i < sdate1.length; i++) {
			sdate = sdate + sdate1[i];
			edate = edate + edate1[i];
		}
		blackIdnumBean.setSdate(sdate);
		blackIdnumBean.setEdate(edate);
		String mark = cardHolderBlackService.updateBlackCardHolder(blackIdnumBean);
		return mark;
	}

	/**
	 * （根据选中记录的tid）注销此持卡人黑名单
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteCardHolderBlack")
	public String deleteCardHolderBlack(HttpServletRequest request) {
		String tid = request.getParameter("tid");
		String mark = cardHolderBlackService.delteOneCardHolderBlack(tid);
		return mark;
	}

	/**
	 * （根据选中记录的tid）启用此持卡人黑名单
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/startCardHolderBlack")
	public String startCardHolderBlack(HttpServletRequest request) {
		String tid = request.getParameter("tid");
		String mark = cardHolderBlackService.startCardHolderBlack(tid);
		return mark;

	}
}
