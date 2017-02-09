package com.zcbspay.platform.manager.merchant.service;

import java.util.List;
import java.util.Map;

import com.zcbspay.platform.manager.merchant.bean.MerchBean;
@SuppressWarnings("all")
public interface MerchDetaService {

	/**
	 * 所属商户
	 * @return
	 */
	public List<?> queryMerchParent();
	
	/**
	 * 商户类型
	 * @return
	 */
	public List<?> queryMerchType();
	
	/**
	 * 所属行业
	 * @return
	 */
	public List<?> queryTrade();
	
	/**
	 * 商户清算类型
	 * @return
	 */
	public List<?> querysetltype();

	/**
	 * 商户信息管理（查询，修改，查看详情）页面
	 * @param variables
	 * @param page
	 * @param rows
	 * @return
	 */
	public Map<String, Object> findMerchByPage(Map<String, Object> variables, Integer page, Integer rows);

	/**
	 * 保存商户信息
	 * @param merchDeta
	 * @return
	 */
	public List<?> saveMerchDeta(MerchBean merchDeta);

	/**
	 * 县
	 * @param pid
	 * @return
	 */
	public List<?> queryCounty(String pid);

	/**
	 * 关键字查询开户行
	 * @param bankName
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<?> queryBankNode(String bankName, Integer page, Integer rows);

	/**
	 * 收银台版本
	 * @return
	 */
	public List<?> queryCashAll();

	/**
	 * 交易渠道
	 * @return
	 */
	public List<?> queryChnlnameAll();

	/**
	 * 路由版本
	 * @return
	 */
	public List<?> queryRouteAll();

	/**
	 * 风控版本
	 * @param vid
	 * @return
	 */
	public List<?> queryRiskType(String vid);

	/**
	 * 分润版本
	 * @param vid
	 * @return
	 */
	public List<?> querySplit(String vid);

	/**
	 * 扣率版本
	 * @param vid
	 * @return
	 */
	public List<?> queryFee(String vid);

	/**
	 * 清算周期
	 * @return
	 */
	public List<?> querySetlcycleAll();
}
