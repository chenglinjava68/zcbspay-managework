package com.zcbspay.platform.manager.merch.service;

import java.util.List;
import java.util.Map;

import com.zcbspay.platform.manager.merch.bean.BusiRateEntity;
import com.zcbspay.platform.manager.merch.bean.CardRateEntity;
import com.zcbspay.platform.manager.merch.bean.CommonRateEntity;
import com.zcbspay.platform.manager.merch.bean.NewCardRateEntity;

public interface FeeService {
	/**
	 * 查选所有扣率信息
	 * @return
	 */
	public List<?> queryFeeAll();
	/**
	 * 分页查询业务扣率
	 * @param variables
	 * @param page
	 * @param rows
	 * @return
	 */
	public Map<String, Object> findBusiRateByPage(Map<String, Object> variables, String page, String rows);
	/**
	 * 扣率版本下的业务实例
	 * @param feever
	 * @return
	 */
	public List<?> queryFeeCaseByFeever(String feever);
	/**
	 * 查询一条扣率版本实例信息
	 * @return
	 */
	public Map<String, Object> queryOneBusiRate(String caseid);
	
	/**
	 * 修改扣率实例
	 * @param busiRateEntity
	 * @return
	 */
	public String updateBusiRate(BusiRateEntity busiRateEntity);
	/**
	 * 添加扣率实例
	 * @return
	 */
	public String addOneBusiRate(BusiRateEntity busiRate);
	/**
	 * 卡扣率分页查询
	 * @return
	 */
	public Map<String, Object> findCardRateByPage(Map<String, Object> variables, String page, String rows);
	/**
	 * 查询一条卡扣率版本实例信息
	 * @return
	 */
	public Map<String, Object> queryOneCardRate(String caseid);
	/**
	 * 更新卡扣率信息
	 * @return
	 */
	public String updateCardRate(CardRateEntity cardrate);
	/**
	 * 添加卡扣率信息
	 * @return
	 */
	public String addOneCardRate(CardRateEntity cardRateEntity);
	/**
	 * 添加常规扣率信息
	 * @return
	 */
	public String addOneCommonRate(CommonRateEntity rateModel);
	/**
	 * 查询扣率类型
	 * @return
	 */
	public List<?> queryRateType();
	/**
	 * 分页查询常规扣率版本
	 * @return
	 */
	public Map<String, Object> findCommonRateByPage(Map<String, Object> variables, String page, String rows);
	/**
	 * 查询一条常规扣率版本
	 * @return
	 */
	public Map<String, Object> queryOneCommonRate(String caseid);
	/**
	 * 更新常规费率
	 * @return
	 */
	public String updateCommonRate(CommonRateEntity rateEntity);
	/**
	 * 添加新卡类型扣率
	 * @return
	 */
	public String saveNewCardRate(NewCardRateEntity cardRateEntity);
	/**
	 * 新卡扣率分页查询
	 * 
	 * @return
	 */
	public Map<String, Object> findNewCardRateByPage(NewCardRateEntity variables, String page, String rows);
	/**
	 * 查询一条新卡类型扣率版本实例信息
	 * 
	 * @return
	 */
	public Map<String, Object> queryOneNewCardRate(String caseid);
}
