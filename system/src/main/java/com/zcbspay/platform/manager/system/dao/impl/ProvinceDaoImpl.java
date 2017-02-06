package com.zcbspay.platform.manager.system.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.zcbspay.platform.manager.dao.impl.HibernateBaseDAOImpl;
import com.zcbspay.platform.manager.system.dao.ProvinceDao;
import com.zcbspay.platform.manager.system.pojo.PojoProvince;
@Repository
@SuppressWarnings("all")
public class ProvinceDaoImpl extends HibernateBaseDAOImpl<PojoProvince> implements ProvinceDao {

	
	private static final Logger log = LoggerFactory.getLogger(DeptDAOImpl.class);
	@Override
	public List<?> findAll() {
		Object[] paramaters = null;
		String sql = "select * from T_PROVINCE";
		return queryBySQL(sql,paramaters);
	}

}
