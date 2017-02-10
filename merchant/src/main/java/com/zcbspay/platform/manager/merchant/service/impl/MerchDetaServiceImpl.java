package com.zcbspay.platform.manager.merchant.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcbspay.platform.manager.merchant.bean.MerchBean;
import com.zcbspay.platform.manager.merchant.bean.MerchDetaApplyBean;
import com.zcbspay.platform.manager.merchant.dao.MerchDetaDAO;
import com.zcbspay.platform.manager.merchant.pojo.PojoMerchDetaApply;
import com.zcbspay.platform.manager.merchant.service.MerchDetaService;
@Service("merchDetaService")
@SuppressWarnings("all")
public class MerchDetaServiceImpl implements MerchDetaService {

	
	@Autowired
	private MerchDetaDAO merchDetaDAO;
	
	public List<?> queryMerchParent() {
		return merchDetaDAO.queryMerchParent();
	}
	
	public List<?> queryMerchType() {
		
		return merchDetaDAO.queryMerchType();
	}
	
	public List<?> queryTrade() {
		
		return merchDetaDAO.queryTrade();
	}

	public List<?> querysetltype() {
		
		return merchDetaDAO.querysetltype();
	}
	
	public Map<String, Object> findMerchByPage(Map<String, Object> variables, Integer page, Integer rows) {
		
		return merchDetaDAO.findMerchByPage(variables,page,rows);
	}


	@Override
	public List<?> saveMerchDeta(MerchBean merchDeta) {
//		 EnterpriseBean enterprise = merchDeta.getMember();
//
//        boolean isRepeat = merchDetaDAO.isRepeat(merchDeta.getMember().getEmail(), enterprise.getPhone(),enterprise.getInstiCode());
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
		return merchDetaDAO.saveMerchDeta(merchDeta);
	}


	@Override
	public List<?> queryCounty(String pid) {
		return merchDetaDAO.queryCounty(pid);
	}

	@Override
	public List<?> queryBankNode(String bankName, Integer page, Integer rows) {
		return merchDetaDAO.queryBankNode(bankName,page,rows);
	}

	@Override
	public List<?> queryCashAll() {
		return merchDetaDAO.queryCashAll();
	}

	@Override
	public List<?> queryChnlnameAll() {
		return merchDetaDAO.queryChnlnameAll();
	}

	@Override
	public List<?> queryRouteAll() {
		return merchDetaDAO.queryRouteAll();
	}

	@Override
	public List<?> queryRiskType(String vid) {
		return merchDetaDAO.queryRiskType(vid);
	}

	@Override
	public List<?> querySplit(String vid) {
		return merchDetaDAO.querySplit(vid);
	}

	@Override
	public List<?> queryFee(String vid) {
		return merchDetaDAO.queryFee(vid);
	}

	@Override
	public List<?> querySetlcycleAll() {
		return merchDetaDAO.querySetlcycleAll();
	}

	public List<?> restList(List<?> list){
		List<MerchDetaApplyBean> newlist = new ArrayList<MerchDetaApplyBean>();
		List<PojoMerchDetaApply> restList = (List<PojoMerchDetaApply>) list;
		
		for (PojoMerchDetaApply pojo : restList) {
			MerchDetaApplyBean bean = new MerchDetaApplyBean();
			
			bean.setSelfId(pojo.getSelfId());
			bean.setMerchId(pojo.getMerchId());
			bean.setMemberId(pojo.getMemberId());
			bean.setParent(pojo.getParent());
			bean.setSetlCycle(pojo.getSetlCycle());
			bean.setSetlType(pojo.getSetlType());
			bean.setBankCode(pojo.getBankCode());
			bean.setBankNode(pojo.getBankNode());
			bean.setAccNum(pojo.getAccNum());
			bean.setAccName(pojo.getAccName());
			bean.setCharge(pojo.getCharge());
			bean.setDeposit(pojo.getDeposit());
			bean.setAgreemtStart(pojo.getAgreemtStart());
			bean.setAgreemtEnd(pojo.getAgreemtEnd());
			bean.setPrdtVer(pojo.getPrdtVer());
			bean.setFeeVer(pojo.getFeeVer());
			bean.setSpiltVer(pojo.getSpiltVer());
			bean.setRiskVer(pojo.getRiskVer());
			bean.setRoutVer(pojo.getRoutVer());
			bean.setMerchStatus(pojo.getMerchStatus());
			bean.setmInTime(pojo.getmInTime());
			bean.setmInUser(pojo.getmInUser());
			bean.setStexaUser(pojo.getStexaUser());
			bean.setStexaTime(pojo.getStexaTime());
			bean.setStexaOpt(pojo.getStexaOpt());
			bean.setCvlexaUser(pojo.getCvlexaUser());
			bean.setCvlexaTime(pojo.getCvlexaTime());
			bean.setCvlexaOpt(pojo.getCvlexaOpt());
			bean.setNotes(pojo.getNotes());
			bean.setRemarks(pojo.getRemarks());
			bean.setMemApplyId(pojo.getMemApplyId());
			bean.setPayBankCode(pojo.getPayBankCode());
			bean.setPayBankNode(pojo.getPayBankNode());
			bean.setPayAccNum(pojo.getPayAccNum());
			bean.setPayAccName(pojo.getPayAccName());
			
			newlist.add(bean);
		}
		return newlist;
	}
}












