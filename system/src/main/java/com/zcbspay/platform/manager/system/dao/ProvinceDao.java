package com.zcbspay.platform.manager.system.dao;

import java.util.List;

import com.zcbspay.platform.manager.dao.BaseDAO;
import com.zcbspay.platform.manager.system.pojo.PojoProvince;

public interface ProvinceDao extends BaseDAO<PojoProvince>{

	public List<?> findAll();
}
