package com.zcbspay.platform.manager.checkinfo.service;

import java.util.List;

import com.zcbspay.platform.manager.checkinfo.bean.ChannelFileBean;

public interface ChannelFileService {
	/**
	 * 获取全部的组织结构
	 * @author: zhangshd
	 * @return List<ChannelFileBean>
	 * @date: 2017年3月1日 下午3:51:09 
	 * @version v1.0
	 */
    List<?> getAllStatusChannel();
}
