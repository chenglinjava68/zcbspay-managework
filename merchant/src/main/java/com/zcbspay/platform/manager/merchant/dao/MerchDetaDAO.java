package com.zcbspay.platform.manager.merchant.dao;

import java.util.List;
import java.util.Map;

import com.zcbspay.platform.manager.dao.BaseDAO;
import com.zcbspay.platform.manager.merchant.bean.MerchBean;
import com.zcbspay.platform.manager.merchant.pojo.PojoMerchDetaApply;

public interface MerchDetaDAO extends BaseDAO<PojoMerchDetaApply>{

	/**
	 * 查询所有商户
	 * @return
	 */
	List<?> queryMerchParent();

	/**
	 * 商户信息管理（查询，修改，查看详情）页面
	 * @param variables
	 * @param page
	 * @param rows
	 * @return
	 */
	Map<String, Object> findMerchByPage(Map<String, Object> variables, Integer page, Integer rows);

	/**
	 * 保存商户信息
	 * @param merchDeta
	 * @return
	 */
	List<?> saveMerchDeta(MerchBean merchDeta);

	/**
	 * 县
	 * @param pid
	 * @return
	 */
	List<?> queryCounty(String pid);

	/**
	 * 所属行业
	 * @return
	 */
	List<?> queryMerchType();

	/**
	 * 商户类型
	 * @return
	 */
	List<?> queryTrade();

	/**
	 * 商户清算类型
	 * @return
	 */
	List<?> querysetltype();

	/**
	 * 关键字查询开户行
	 * @param bankName
	 * @param page
	 * @param rows
	 * @return
	 */
	List<?> queryBankNode(String bankName, Integer page, Integer rows);

	/**
	 * 收银台版本
	 * @return
	 */
	List<?> queryCashAll();

	/**
	 * 交易渠道
	 * @return
	 */
	List<?> queryChnlnameAll();

	/**
	 * 路由版本
	 * @return
	 */
	List<?> queryRouteAll();

	/**
	 * 风控版本
	 * @param vid
	 * @return
	 */
	List<?> queryRiskType(String vid);

	/**
	 * 分润版本
	 * @param vid
	 * @return
	 */
	List<?> querySplit(String vid);

	/**
	 * 扣率版本
	 * @param vid
	 * @return
	 */
	List<?> queryFee(String vid);

	/**
	 * 清算周期
	 * @return
	 */
	List<?> querySetlcycleAll();
	
	

}
