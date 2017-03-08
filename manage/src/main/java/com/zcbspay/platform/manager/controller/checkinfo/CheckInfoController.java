package com.zcbspay.platform.manager.controller.checkinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcbspay.platform.cnaps.application.bean.DetailCheckPaymentInformation;
import com.zcbspay.platform.cnaps.application.bean.ResultBean;
import com.zcbspay.platform.cnaps.application.bean.TotalCheckMessageBean;
import com.zcbspay.platform.cnaps.application.bean.TotalCheckPaymentBean;
import com.zcbspay.platform.manager.checkinfo.bean.ChannelFileBean;
import com.zcbspay.platform.manager.checkinfo.service.ChannelFileService;
import com.zcbspay.platform.manager.checkinfo.service.CheckInfoService;
import com.zcbspay.platform.manager.checkinfo.service.UploadlogService;
import com.zcbspay.platform.manager.utils.JsonUtils;
import com.zcbspay.platform.manager.utils.UserHelper;

@Controller
@RequestMapping("/checkinfo/")
public class CheckInfoController {

	@Autowired
	private CheckInfoService checkInfoService;
	
	@Autowired
	private ChannelFileService channelFileService;
	
	@Autowired
	private UploadlogService uploadlogService;

	/**
	 * 总账数据页面
	 * 
	 * @author: zhangshd
	 * @return String
	 * @date: 2017年2月27日 16:48:00
	 * @version v1.0
	 */
	@RequestMapping("showTotalCheckInfo")
	public String showBusiRate() {
		return "checkinfo/checkinfo_total";
	}

	/**
	 * 开始对账页面
	 * 
	 * @author: zhangshd
	 * @return String
	 * @date: 2017年2月27日 16:48:00
	 * @version v1.0
	 */
	@RequestMapping("showStartCheck")
	public String showStartCheck() {
		return "checkinfo/file_start";
	}

	/**
	 * 查询全部对账数据
	 * 
	 * @author: zhangshd
	 * @param request
	 * @param checkInfoDate
	 * @param page
	 * @param rows
	 * @return List<?>
	 * @date: 2017年2月28日 上午11:45:32
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("queryCheckInfoTotal")
	public Object queryCheckInfoTotal(String checkInfoDate) {
		return checkInfoService.queryCheckInfoTotal(checkInfoDate);
	}

	/**
	 * 查询对账详细信息
	 * 
	 * @author: zhangshd
	 * @param caseid
	 * @return ResultBean
	 * @date: 2017年2月28日 下午3:16:49
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("queryCheckInfoDetail")
	public ResultBean queryCheckInfoDetail(TotalCheckPaymentBean totalCheckPaymentBean, String checkDate) {
		return checkInfoService.queryCheckInfoDetail(totalCheckPaymentBean, checkDate);
	}

	/**
	 *  查询机构
	 * @author: zhangshd
	 * @return List<ChannelFileBean>
	 * @date: 2017年3月1日 下午3:31:05 
	 * @version v1.0
	 */
	 
	@ResponseBody
	@RequestMapping("queryChannel")
	public List<?> queryChannel() {
		Map<String, Object> result = new HashMap<String, Object>();
		List<?> list = channelFileService.getAllStatusChannel();
		return list;
	}
	
	/**
	 * 任务分页查询（新增任务，开始对账）
	 * @author: zhangshd
	 * @return Map<String, Object>
	 * @date: 2017年3月1日 下午4:22:35 
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("queryProcess")
	public Map<String, Object> queryProcess(String startDate,String endDate,String page,String rows) {
		Map<String, Object> variables = new HashMap<String, Object>();
        if (!StringUtils.isBlank(startDate)) {
            variables.put("startDate", startDate.replace("-", ""));
        }
        if (!StringUtils.isBlank(endDate)) {
            variables.put("endDate", endDate.replace("-", ""));
        }
        Map<String, Object> processList =uploadlogService.findProcessByPage(variables, page,rows);
        return processList;
	}
	
	
	/**
	 * 保存任务
	 * @author: zhangshd
	 * @param instiid
	 * @return Object
	 * @date: 2017年3月1日 下午4:49:24 
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("saveProcess")
	public Object saveProcess(String instiid) {
		 List<?> list = uploadlogService.saveProcess(instiid);
	     return list.get(0);
	}
	
	/**
	 * 开始执行核对
	 * @author: zhangshd
	 * @param filestartid
	 * @return Object
	 * @date: 2017年3月1日 下午4:56:23 
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("startCheckFile")
	public List<?> startCheckFile(String filestartid) {
		List<?> list = uploadlogService.startCheckFile(filestartid);
        return list;
	}
	/**
	 * 查询对账差错的记录
	 * @author: zhangshd
	 * @param filestartid
	 * @return List<?>
	 * @date: 2017年3月1日 下午5:04:13 
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("queryFail")
	public Map<String, Object> queryFail(HttpServletRequest request,String page,String rows) {
		Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("proid", request.getParameter("proid")); 
        variables.put("user",UserHelper.getCurrentUser(request).getUserId());
        Map<String, Object> failList = uploadlogService.queryFail(variables, page,rows);
        return failList;
	}
	
	/**
	 * 查询对账成功的记录
	 * @author: zhangshd
	 * @param filestartid
	 * @return List<?>
	 * @date: 2017年3月1日 下午5:04:13 
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("querySuccess")
	public Map<String, Object> querySuccess(HttpServletRequest request,String page,String rows) {
		Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("proid", request.getParameter("proid")); 
        variables.put("user",UserHelper.getCurrentUser(request).getUserId());
        Map<String, Object> failList = uploadlogService.querySuccess(variables, page,rows);
        return failList;
	}
	
	
	
}
