package com.zcbspay.platform.manager.controller.trade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcbspay.platform.manager.trade.bean.CnapsLogBean;
import com.zcbspay.platform.manager.trade.bean.CollectAndPaymentBean;
import com.zcbspay.platform.manager.trade.bean.OrderInfoBean;
import com.zcbspay.platform.manager.trade.bean.TxnsLogBean;
import com.zcbspay.platform.manager.trade.service.BusinessService;
import com.zcbspay.platform.manager.trade.service.ChnlDetaService;
import com.zcbspay.platform.manager.trade.service.TxnsLogService;
import com.zcbspay.platform.manager.utils.UserHelper;

@Controller
@RequestMapping("/trade/")
public class TradeController {

	@Autowired
	private BusinessService businessService;

	@Autowired
	private ChnlDetaService chnlDetaService;

	@Autowired
	private TxnsLogService txnsLogService;

	/**
	 * 核心交易流水查询页面
	 * @author: zhangshd
	 * @return String
	 * @date: 2017年2月27日 16:48:00
	 * @version v1.0
	 */
	@RequestMapping("showTxnsLog")
	public String showTxnsLog(Model model) {
		model.addAttribute("bus", businessService.getAllBusiness());
		model.addAttribute("channel", chnlDetaService.getAllChannelCodeList());
		return "trade/txns_log";
	}
	/**
	 * 交易订单查询页面
	 * @author: zhangshd
	 * @param model
	 * @return String
	 * @date: 2017年3月6日 上午10:09:39 
	 * @version v1.0
	 */
	@RequestMapping("showTxnsOrderInfo")
	public String showTxnsOrderInfo(Model model) {
		return "trade/txns_orderinfo";
	}
	
	/**
	 * CNAPS核心交易流水页面
	 * @author: zhangshd
	 * @return String
	 * @date: 2017年2月27日 16:48:00
	 * @version v1.0
	 */
	@RequestMapping("showCnapsLog")
	public String showCnapsLog(Model model) {
		return "trade/cnaps_log";
	}
	
	/**
	 * BEPS批量代收交易流水查询
	 * @author: zhangshd
	 * @param model
	 * @return String
	 * @date: 2017年3月7日 下午2:43:33 
	 * @version v1.0
	 */
	@RequestMapping("showBepsCollectBatch")
	public String showBepsCollectBatch(Model model) {
		return "trade/beps_collect_batch";
	}
	
	/**
	 * BEPS批量代付交易流水查询
	 * @author: zhangshd
	 * @param model
	 * @return String
	 * @date: 2017年3月8日 下午2:27:03 
	 * @version v1.0
	 */
	@RequestMapping("showBepsPaymentBatch")
	public String showBepsPaymentBatch(Model model) {
		return "trade/beps_payment_batch";
	}
	
	/**
	 * 分页查询核心交易流水
	 * @author: zhangshd
	 * @return String
	 * @date: 2017年3月2日 下午3:14:43
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("getTxnsLogByPage")
	public Map<String, Object> getTxnsLogByPage(TxnsLogBean values, String page, String rows,
			HttpServletRequest request) {
		values.setUserId(UserHelper.getCurrentUser(request).getUserId());
		return txnsLogService.getTxnsLogByPage(page, rows, values);
	}
	/**
	 * BEPS批量代收交易流水查询分页查询
	 * @author: zhangshd
	 * @param values
	 * @param page
	 * @param rows
	 * @param request
	 * @return Map<String,Object>
	 * @date: 2017年3月7日 下午3:38:02 
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("getBepsCollectBatchByPage")
	public Map<String, Object> getBepsCollectBatchByPage(CollectAndPaymentBean collectBatchBean, String page, String rows,
			HttpServletRequest request) {
		collectBatchBean.setUserId(UserHelper.getCurrentUser(request).getUserId());
		return txnsLogService.getBepsCollectBatchByPage(page, rows, collectBatchBean);
	}
	/**
	 * BEPS批量代付交易流水查询分页查询
	 * @author: zhangshd
	 * @param values
	 * @param page
	 * @param rows
	 * @param request
	 * @return Map<String,Object>
	 * @date: 2017年3月7日 下午3:38:02 
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("getBepsPaymentBatchByPage")
	public Map<String, Object> getBepsPaymentBatchByPage(CollectAndPaymentBean collectBatchBean, String page, String rows,
			HttpServletRequest request) {
		collectBatchBean.setUserId(UserHelper.getCurrentUser(request).getUserId());
		return txnsLogService.getBepsPaymentBatchByPage(page, rows, collectBatchBean);
	}
	
	/**
	 * BEPS批量代收交易流水详细信息分页查询
	 * @author: zhangshd
	 * @param collectBatchBean
	 * @param page
	 * @param rows
	 * @param request
	 * @return Map<String,Object>
	 * @date: 2017年3月7日 下午3:45:49 
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("queryCollectDetail")
	public Map<String, Object> queryCollectDetail(CollectAndPaymentBean collectBatchBean, String page, String rows,
			HttpServletRequest request) {
		collectBatchBean.setUserId(UserHelper.getCurrentUser(request).getUserId());
		return txnsLogService.queryDetail(page, rows, collectBatchBean);
	}
	
	/**
	 * BEPS批量代付交易流水详细信息分页查询
	 * @author: zhangshd
	 * @param collectBatchBean
	 * @param page
	 * @param rows
	 * @param request
	 * @return Map<String,Object>
	 * @date: 2017年3月8日 下午2:39:04 
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("queryPaymentDetail")
	public Map<String, Object> queryPaymentDetail(CollectAndPaymentBean collectBatchBean, String page, String rows,
			HttpServletRequest request) {
		collectBatchBean.setUserId(UserHelper.getCurrentUser(request).getUserId());
		return txnsLogService.queryPaymentDetail(page, rows, collectBatchBean);
	}
	
	
	/**
	 * CNAPS核心交易分页查询
	 * @author: zhangshd
	 * @return String
	 * @date: 2017年3月2日 下午3:14:43
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("getCnapsLogByPage")
	public Map<String, Object> getCnapsLogByPage(CnapsLogBean values, String page, String rows,
			HttpServletRequest request) {
		values.setUserId(UserHelper.getCurrentUser(request).getUserId());
		return txnsLogService.getCnapsLogByPage(page, rows, values);
	}
	/**
	 * 
	 * @author: zhangshd
	 * @return String
	 * @date: 2017年3月2日 下午3:14:43
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("getOrderInfoByPage")
	public Map<String, Object> getOrderInfoByPage(OrderInfoBean values, String page, String rows,
			HttpServletRequest request) {
		values.setUserId(UserHelper.getCurrentUser(request).getUserId());
		return txnsLogService.getOrderInfoByPage(page, rows, values);
	}
	/**
	 * 获取交易流水详细信息
	 * @author: zhangshd
	 * @param txnseqno
	 * @return Map<String,Object>
	 * @date: 2017年3月3日 上午11:04:33 
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("getTxnsLogByTxnseqno")
	public Map<String, Object> getTxnsLogByTxnseqno(String txnseqno) {
		String messg=null;   
		Map<String, Object> map = new HashMap<String, Object>();
		List<?> li = txnsLogService.getTxnsLogById(txnseqno);
		if (li == null || li.isEmpty()) {
			messg = "交易数据不存在";
			map.put("messg", messg);
		} else {
			map.put("json", li.get(0));
		}
		return map;
	}
	
	/**
	 * 获取订单详细信息
	 * @author: zhangshd
	 * @param txnseqno
	 * @return Map<String,Object>
	 * @date: 2017年3月3日 上午11:04:33 
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("getOrderInfoDetail")
	public Map<String, Object> getOrderInfoDetail(String id) {
		String messg=null;   
		Map<String, Object> map = new HashMap<String, Object>();
		List<?> li = txnsLogService.getOrderInfoDetail(id);
		if (li == null || li.isEmpty()) {
			messg = "交易数据不存在";
			map.put("messg", messg);
		} else {
			map.put("json", li.get(0));
		}
		return map;
	}

}
