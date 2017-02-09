package com.zcbspay.platform.manager.merch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcbspay.platform.manager.merch.bean.BusiRateEntity;
import com.zcbspay.platform.manager.merch.bean.CardRateEntity;
import com.zcbspay.platform.manager.merch.dao.FeeDao;
import com.zcbspay.platform.manager.merch.service.FeeService;

@Service("feeService")
public class FeeServiceImpl implements FeeService {

	@Autowired
	private FeeDao feeDao;

	@Override
	public List<?> queryFeeAll() {
		return feeDao.queryFeeAll();
	}

	@Override
	public Map<String, Object> findBusiRateByPage(Map<String, Object> variables, String page, String rows) {
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
	public String updateBusiRate(BusiRateEntity busiRateEntity) {
		if (busiRateEntity == null) {
			return "操作失败！";
		}
		return feeDao.updateBusiRate(busiRateEntity);
	}

	@Override
	public String addOneBusiRate(BusiRateEntity busiRate) {
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
	public String updateCardRate(CardRateEntity cardrate) {
		if (cardrate == null) {
			return "操作失败！";
		}
		return feeDao.updateCardRate(cardrate);
	}

	@Override
	public String addOneCardRate(CardRateEntity cardRateEntity) {
		if (cardRateEntity == null) {
            return "操作失败！";
        }
		return feeDao.addOneCardRate(cardRateEntity);
	}

}
