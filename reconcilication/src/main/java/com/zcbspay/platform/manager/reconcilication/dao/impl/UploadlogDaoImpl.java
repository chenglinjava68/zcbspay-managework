package com.zcbspay.platform.manager.reconcilication.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zcbspay.platform.manager.dao.impl.HibernateBaseDAOImpl;
import com.zcbspay.platform.manager.reconcilication.bean.ChannelFileBean;
import com.zcbspay.platform.manager.reconcilication.dao.UploadlogDao;

@Repository
public class UploadlogDaoImpl extends HibernateBaseDAOImpl<ChannelFileBean> implements UploadlogDao {

	@Override
	public Map<String, Object> findProcessByPage(Map<String, Object> variables, String page, String rows) {
		String[] columns = new String[] { "v_stime", "v_etime", "i_no", "i_perno" };
		Object[] paramaters = new Object[4];
		paramaters[0] = variables.containsKey("startDate") ? variables.get("startDate") : null;
		paramaters[1] = variables.containsKey("endDate") ? variables.get("endDate") : null;
		paramaters[2] = page;
		paramaters[3] = rows;
		return executePageOracleProcedure("{CALL PCK_T_SETT_PROCESS.sel_t_sett_process(?,?,?,?,?,?)}", columns,
				paramaters, "cursor0", "v_total");
	}

	@Override
	public List<?> saveProcess(String instiid) {
		String[] columns = new String[] {"v_instiid","v_stage","v_speed","v_status"}; 
		Object[] paramaters = new Object[]{instiid,"11","0","00"};
		return executeOracleProcedure(
				"{CALL  PCK_T_SETT_PROCESS.ins_t_sett_process(?,?,?,?,?)}",columns,
				paramaters, "cursor0");
	}

	@Override
	public List<?> startCheckFile(String filestartid) {
		String[] columns = new String[] {"v_tid"}; 
		Object[] paramaters = new Object[]{filestartid};
		return executeOracleProcedure(
				"{CALL  CHECK_BILL.checkbill(?,?)}",columns,
				paramaters, "cursor0");
	}

	@Override
	public Map<String, Object> queryFail(Map<String, Object> variables, String page, String rows) {
		String[] columns = new String[]{"v_proid","v_stime","v_etime","v_user","i_no","i_perno"};
        Object[] paramaters = new Object[6];
        paramaters[0] = variables.containsKey("proid")?variables.get("proid"):null;
        paramaters[1] = variables.containsKey("stime")?variables.get("stime"):null;
        paramaters[2] = variables.containsKey("etime")?variables.get("etime"):null;
        paramaters[3] = variables.containsKey("user")?variables.get("user"):null;
        paramaters[4] = page;
        paramaters[5] = rows;
        return executePageOracleProcedure("{CALL PCK_SEL_checkfile_succ.sel_mistake(?,?,?,?,?,?,?,?)}",
                columns, paramaters, "cursor0", "v_total");
	}

	@Override
	public Map<String, Object> querySuccess(Map<String, Object> variables, String page, String rows) {
		String[] columns = new String[]{"v_proid","v_stime","v_etime","v_user","i_no","i_perno"};
        Object[] paramaters = new Object[6];
        paramaters[0] = variables.containsKey("proid")?variables.get("proid"):null;
        paramaters[1] = variables.containsKey("stime")?variables.get("stime"):null;
        paramaters[2] = variables.containsKey("etime")?variables.get("etime"):null;
        paramaters[3] = variables.containsKey("user")?variables.get("user"):null;
        paramaters[4] = page;
        paramaters[5] = rows;
        return executePageOracleProcedure("{CALL PCK_SEL_checkfile_succ.sel_succ(?,?,?,?,?,?,?,?)}",
                columns, paramaters, "cursor0", "v_total");
	}

}
