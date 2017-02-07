package com.zcbspay.platform.manager.merch.dao.impl;

import org.springframework.stereotype.Repository;

import com.zcbspay.platform.manager.dao.impl.HibernateBaseDAOImpl;
import com.zcbspay.platform.manager.merch.bean.ProductBean;
import com.zcbspay.platform.manager.merch.dao.ProductDao;

@Repository
public class ProductDaoImpl extends HibernateBaseDAOImpl<ProductBean>  implements ProductDao {
	@Override
	public String AddOneProduct(ProductBean product, String busicodeStr) {
		Object[] paramaters = new Object[] { product.getPrdtname(), product.getNotes(), product.getRemarks(),
				busicodeStr, product.getInuser() };
		String[] columns = new String[] { "v_prdtname", "v_notes", "v_remarks", "v_casestr", "v_inuser" };
		Object total = executeOracleProcedure("{CALL PCK_T_PRODUCT.ins_t_product(?,?,?,?,?,?)}", columns,
				paramaters, "cursor0").get(0).get("INFO");
		return (String) total;
	}

}
