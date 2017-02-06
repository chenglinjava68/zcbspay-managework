package com.zcbspay.platform.manager.system.service;

import java.util.List;

public interface CityService {

	/**
	 * @param pid
	 * @return
	 */
	public List<?> findNotMuniByPid(long pid);
}
