package com.zcbspay.platform.manager.system.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zcbspay.platform.manager.dao.impl.HibernateBaseDAOImpl;
import com.zcbspay.platform.manager.system.dao.CityDao;
import com.zcbspay.platform.manager.system.pojo.PojoCity;

@Repository
public class CityDaoImpl extends HibernateBaseDAOImpl<PojoCity>  implements CityDao {

	@Override
	public List<?> findNotMuniByPid(long pId) {
		
		Object[] paramaters = null;
		
		String sql = "select ct from PojoCity ct where ct.PId = "+ pId;
		return queryByHQL(sql,paramaters);
	}

}
