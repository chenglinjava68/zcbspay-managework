package com.zcbspay.platform.manager.merch.dao;

import java.util.List;
import java.util.Map;

import com.zcbspay.platform.manager.dao.BaseDAO;
import com.zcbspay.platform.manager.merch.bean.BusiRateEntity;
import com.zcbspay.platform.manager.merch.bean.CardRateEntity;
import com.zcbspay.platform.manager.merch.bean.FeeEntity;
import com.zcbspay.platform.manager.merch.bean.ProductBean;

public interface FeeDao extends BaseDAO<FeeEntity>{

	/**
	 * 查询所有扣率版本
	 * @return
	 */
	public List<?> queryFeeAll();
	/**
	 * 查询业务扣率
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
	 * @param caseid
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


}
