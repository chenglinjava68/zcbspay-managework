package com.zcbspay.platform.manager.merch.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zcbspay.platform.manager.dao.impl.HibernateBaseDAOImpl;
import com.zcbspay.platform.manager.merch.bean.ProductBean;
import com.zcbspay.platform.manager.merch.dao.ProductDao;

@Repository
public class ProductDaoImpl extends HibernateBaseDAOImpl<ProductBean> implements ProductDao {
	@Override
	public String AddOneProduct(ProductBean product, String busicodeStr) {
		Object[] paramaters = new Object[] { product.getPrdtname(), product.getNotes(), product.getRemarks(),
				busicodeStr, product.getInuser() };
		String[] columns = new String[] { "v_prdtname", "v_notes", "v_remarks", "v_casestr", "v_inuser" };
		Object total = executeOracleProcedure("{CALL PCK_T_PRODUCT.ins_t_product(?,?,?,?,?,?)}", columns, paramaters,
				"cursor0").get(0).get("INFO");
		return (String) total;
	}

	@Override
	public Map<String, Object> findProductByPage(Map<String, Object> variables, int page, int rows) {
		String[] columns = new String[] { "v_prdtver", "v_prdtname", "i_no", "i_perno" };

		Object[] paramaters = new Object[] { variables.containsKey("prdtver") ? variables.get("prdtver") : null,
				variables.containsKey("prdtname") ? variables.get("prdtname") : null, page, rows };
		return executePageOracleProcedure("{CALL PCK_T_PRODUCT.sel_t_product(?,?,?,?,?,?)}", columns, paramaters,
				"cursor0", "v_total");
	}

	@Override
	public List<?> queryBusinessType() {
		String[] columns = new String[] { "v_in" };
		Object[] paramaters = new Object[1];
		paramaters[0] = 1;
		return executeOracleProcedure("{CALL  PCK_FOR_SELECT.sel_business(?,?)}", columns, paramaters, "cursor0");
	}

	@Override
	public List<?> findByProperty(String propertyName, String value) {
		String sql = "select * from T_PRODUCT ct where ct."+propertyName+"="+ value;
		return queryBySQL(sql, null);
	}

	@Override
	public List<?> queryProdCase(String prdtver) {
		String[] columns = new String[] { "v_prdtver" };
		Object[] paramaters = new Object[1];
		paramaters[0] = prdtver;
		return executeOracleProcedure("{CALL  PCK_T_PRODUCT.sel_prod_case(?,?)}", columns, paramaters,
				"cursor0");
	}

	@Override
	public String updateProduct(ProductBean product, String busicodeStr) {
		Object[] paramaters = new Object[] { product.getPrdtver(), product.getPrdtname(), product.getNotes(),
				product.getRemarks(), busicodeStr, product.getInuser() };
		String[] columns = new String[] { "v_prdtver", "v_prdtname", "v_notes", "v_remarks", "v_casestr", "v_upuser" };
		Object total = executeOracleProcedure("{CALL PCK_T_PRODUCT.upt_t_product(?,?,?,?,?,?,?)}", columns,
				paramaters, "cursor0").get(0).get("INFO");
		return (String) total;
	}

	@Override
	public List<?> findAll() {
		String sql = "select * from T_PRODUCT ";
		return queryBySQL(sql, null);
	}

}
