package com.zcbspay.platform.manager.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcbspay.platform.manager.business.bean.BusiRateBean;
import com.zcbspay.platform.manager.business.bean.CardRateBean;
import com.zcbspay.platform.manager.business.bean.CommonRateBean;
import com.zcbspay.platform.manager.business.bean.NewCardRateBean;
import com.zcbspay.platform.manager.business.bean.StepRateBean;
import com.zcbspay.platform.manager.business.dao.FeeDao;
import com.zcbspay.platform.manager.business.service.FeeService;

@Service("feeService")
public class FeeServiceImpl implements FeeService {

	@Autowired
	private FeeDao feeDao;

	@Override
	public List<?> queryFeeAll() {
		return feeDao.queryFeeAll();
	}

	@Override
	public Map<String, Object> findBusiRateByPage(BusiRateBean variables, String page, String rows) {
		return feeDao.findBusiRateByPage(variables, page, rows);
	}

	@Override
	public List<?> queryFeeCaseByFeever(String feever) {
		return feeDao.queryFeeCaseByFeever(feever);
	}

	@Override
	public Map<String, Object> queryOneBusiRate(String caseid) {
		return feeDao.queryOneBusiRate(caseid);
	}

	@Override
	public String updateBusiRate(BusiRateBean busiRateEntity) {
		if (busiRateEntity == null) {
			return "操作失败！";
		}
		return feeDao.updateBusiRate(busiRateEntity);
	}

	@Override
	public String addOneBusiRate(BusiRateBean busiRate) {
		if (busiRate == null) {
			return "操作失败！";
		}
		return feeDao.addOneBusiRate(busiRate);
	}

	@Override
	public Map<String, Object> findCardRateByPage(Map<String, Object> variables, String page, String rows) {
		return feeDao.findCardRateByPage(variables, page, rows);
	}

	@Override
	public Map<String, Object> queryOneCardRate(String caseid) {
		return feeDao.queryOneCardRate(caseid);
	}

	@Override
	public String updateCardRate(CardRateBean cardrate) {
		if (cardrate == null) {
			return "操作失败！";
		}
		return feeDao.updateCardRate(cardrate);
	}

	@Override
	public String addOneCardRate(CardRateBean cardRateEntity) {
		if (cardRateEntity == null) {
            return "操作失败！";
        }
		return feeDao.addOneCardRate(cardRateEntity);
	}

	@Override
	public String addOneCommonRate(CommonRateBean rateModel) {
		if (rateModel == null) {
            return "操作失败！";
        }
		return feeDao.addOneCommonRate(rateModel);
	}

	@Override
	public List<?> queryRateType() {
		return feeDao.queryRateType();
	}

	@Override
	public Map<String, Object> findCommonRateByPage(CommonRateBean variables, String page, String rows) {
		return feeDao.findCommonRateByPage(variables, page, rows);
	}

	@Override
	public Map<String, Object> queryOneCommonRate(String caseid) {
		return feeDao.queryOneCommonRate(caseid);
	}

	@Override
	public String updateCommonRate(CommonRateBean rateEntity) {
		return feeDao.updateCommonRate(rateEntity);
	}

	@Override
	public String saveNewCardRate(NewCardRateBean cardRateEntity) {
		return feeDao.saveNewCardRate(cardRateEntity);
	}

	@Override
	public Map<String, Object> findNewCardRateByPage(NewCardRateBean variables, String page, String rows) {
		return feeDao.findNewCardRateByPage(variables, page, rows);
	}

	@Override
	public Map<String, Object> queryOneNewCardRate(String caseid) {
		return feeDao.queryOneNewCardRate(caseid);
	}

	@Override
	public String updateNewCardRate(NewCardRateBean cardrate) {
		return feeDao.updateNewCardRate(cardrate);
	}

	@Override
	public String AddOneStepRate(StepRateBean stepRateBean) {
		return feeDao.AddOneStepRate(stepRateBean);
	}

	@Override
	public  Map<String, Object> findStpeRateByPage(StepRateBean stepRateBean, String page, String rows) {
		return feeDao.findStpeRateByPage(stepRateBean, page, rows);
	}

	@Override
	public Map<String, Object> queryOneStepRate(String caseid) {
		return feeDao.queryOneStepRate(caseid);
	}

	@Override
	public String updateStepRate(StepRateBean stepRateBean) {
		return feeDao.updateStepRate(stepRateBean);
	}


}
