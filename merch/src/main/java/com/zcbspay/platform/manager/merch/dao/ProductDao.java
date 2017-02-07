package com.zcbspay.platform.manager.merch.dao;

import com.zcbspay.platform.manager.dao.BaseDAO;
import com.zcbspay.platform.manager.merch.bean.ProductBean;

public interface ProductDao extends BaseDAO<ProductBean>{

	/**
	 * 添加一个产品
	 * @param productBean 产品实体类
	 * @param busicodeStr 业务code组成的字符串用"," 逗号隔开
	 * @return
	 */
	public String AddOneProduct(ProductBean productBean,String busicodeStr);

}
