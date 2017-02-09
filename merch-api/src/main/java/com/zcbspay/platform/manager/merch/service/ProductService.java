package com.zcbspay.platform.manager.merch.service;

import java.util.List;
import java.util.Map;

//import com.zcbspay.platform.manager.merch.bean.CashBean;
import com.zcbspay.platform.manager.merch.bean.ProductBean;

public interface ProductService {
	public Map<String, Object> findProductByPage(Map<String, Object> variables, int page,int rows) ;
	public List<?> queryBusinessType() ;
	public String AddOneProduct(ProductBean product,List listbusicode);
	public List<?> queryProdCase(String  prdtver) ;
	public String updateProduct(ProductBean product,List listbusicode);
	public Map<String, Object> findCashByPage(Map<String, Object> variables, int page,int rows);
	public List<?> queryChnlType();
	public List<?> queryCaseMark(String  cashver) ;
	public Map<String, Object> queryOneCase(String cashver) ;
	/**
	 * 根据标识符查询产品
	 * @param value
	 * @return
	 */
	public Map<String, Object> findByPrdtver(String value);
	/**
	 * 查询所有产品
	 * @return
	 */
	public List<?> findAll();
}
