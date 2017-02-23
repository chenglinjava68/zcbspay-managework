package com.zcbspay.platform.manager.business.dao;

import java.util.List;
import java.util.Map;

import com.zcbspay.platform.manager.business.bean.BusiRateBean;
import com.zcbspay.platform.manager.business.bean.CardRateBean;
import com.zcbspay.platform.manager.business.bean.CommonRateBean;
import com.zcbspay.platform.manager.business.bean.FeeBean;
import com.zcbspay.platform.manager.business.bean.NewCardRateBean;
import com.zcbspay.platform.manager.business.bean.ProductBean;
import com.zcbspay.platform.manager.business.bean.StepRateBean;
import com.zcbspay.platform.manager.dao.BaseDAO;

public interface FeeDao extends BaseDAO<FeeBean>{

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
	public Map<String, Object> findBusiRateByPage(BusiRateBean variables, String page, String rows);
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
	public String updateBusiRate(BusiRateBean busiRateEntity);
	/**
	 * 添加扣率实例
	 * @return
	 */
	public String addOneBusiRate(BusiRateBean busiRate);
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
	public String updateCardRate(CardRateBean cardrate);
	/**
	 * 添加卡扣率信息
	 * @return
	 */
	public String addOneCardRate(CardRateBean cardRateEntity);
	/**
	 * 添加常规扣率信息
	 * @return
	 */
	public String addOneCommonRate(CommonRateBean rateModel);
	/**
	 * 查询扣率类型
	 * @return
	 */
	public List<?> queryRateType();
	/**
	 * 分页查询常规扣率
	 * @return
	 */
	public Map<String, Object> findCommonRateByPage(CommonRateBean variables, String page, String rows);
	/**
	 * 查询一条常规扣率
	 * @return
	 */
	public Map<String, Object> queryOneCommonRate(String caseid);
	/**
	 * 更新常规扣率
	 * @return
	 */
	public String updateCommonRate(CommonRateBean rateEntity);
	/**
	 * 添加新卡类型扣率
	 * @return
	 */
	public String saveNewCardRate(NewCardRateBean cardRateEntity);
	/**
	 * 新卡扣率分页查询
	 * 
	 * @return
	 */
	public Map<String, Object> findNewCardRateByPage(NewCardRateBean variables, String page, String rows);
	/**
	 * 查询一条新卡类型扣率版本实例信息
	 * 
	 * @return
	 */
	public Map<String, Object> queryOneNewCardRate(String caseid);
	/**
	 * 更新新的卡类型扣率
	 * 
	 * @return
	 */
	public String updateNewCardRate(NewCardRateBean cardrate);
	/**
	 * 添加分段扣率信息
	 * @param stepRateBean
	 * @return
	 */
	public String AddOneStepRate(StepRateBean stepRateBean);
	/**
	 * 分页查询分段扣率
	 * @param stepRateBean
	 * @param page
	 * @param rows
	 * @return
	 */
	public  Map<String, Object> findStpeRateByPage(StepRateBean stepRateBean, String page, String rows);
	/**
	 * 查询一条分段扣率版本实例信息
	 * 
	 * @return
	 */
	public Map<String, Object> queryOneStepRate(String caseid);
	/**
	 * 更新分段扣率信息
	 * 
	 * @return
	 */
	public String updateStepRate(StepRateBean stepRateBean);


}
