package com.zcbspay.platform.manager.merchant.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcbspay.platform.manager.merchant.bean.MccListBean;
import com.zcbspay.platform.manager.merchant.dao.MccListDAO;
import com.zcbspay.platform.manager.merchant.pojo.PojoMccList;
import com.zcbspay.platform.manager.merchant.service.MccListService;
import com.zcbspay.platform.manager.system.bean.RoleBean;

@Service("mccListService")
@SuppressWarnings("all")
public class MccListServiceImpl implements MccListService {

	@Autowired
	private MccListDAO mccListDao;
	@Override
	public List<?> findAll() {
//		List<MccListBean> newlist = new ArrayList<MccListBean>();
//		List<PojoMccList> list = (List<PojoMccList>) mccListDao.findAll();
//		
//		for (PojoMccList pojo : list) {
//			MccListBean bean = new MccListBean();
//			
//			bean.setMccList(pojo.getMccList());
//			bean.setMccCont(pojo.getMccCont());
//			bean.setMccType(pojo.getMccType());
//			bean.setMccTrade(pojo.getMccTrade());
//			bean.setIndustry(pojo.getIndustry());
//			bean.setMcc(pojo.getMcc());
//			bean.setStatus(pojo.getStatus());
//			newlist.add(bean);
//		}
		return mccListDao.findAll();
	}

}
