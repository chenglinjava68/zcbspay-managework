package com.zcbspay.platform.manager.controller.merch;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcbspay.platform.manager.merch.bean.BusiRateEntity;
import com.zcbspay.platform.manager.merch.bean.CardRateEntity;
import com.zcbspay.platform.manager.merch.service.FeeService;
import com.zcbspay.platform.manager.utils.JsonUtils;
import com.zcbspay.platform.manager.utils.UserHelper;

@Controller
@RequestMapping("/fee/")
public class FeeController {

	@Autowired
	private FeeService feeService;
	
	@RequestMapping("showBusiRate")
	public String showBusiRate() {
		return "fee/txn_rate_manager";
	}

	@RequestMapping("showCardRate")
	public String showCardRate() {
		return "fee/card_rate_manager";
	}

	/**
	 * 查询所有扣率版本
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryFeeAll")
	public List<?> queryFeeAll() {
		List<?> resultList = feeService.queryFeeAll();
		return resultList;
	}

	/**
	 * 扣率版本分页查询
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryTxnRate")
	public Map<String, Object> queryTxnRate(HttpServletRequest request, BusiRateEntity busiRateEntity, String page,
			String rows) {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("userId", UserHelper.getCurrentUser(request).getUserId());
		variables.put("feever", busiRateEntity.getFeever());
		variables.put("busicode", busiRateEntity.getBusicode());
		Map<String, Object> busiList = feeService.findBusiRateByPage(variables, page, rows);
		return busiList;
	}

	/**
	 * 扣率版本下的业务实例
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryFeeCaseByFeever")
	public List<?> queryFeeCaseByFeever(String feever) {
		List<?> resultList = feeService.queryFeeCaseByFeever(feever);
		return resultList;
	}

	/**
	 * 查询一条扣率版本实例信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryOneBusiRate")
	public Map<String, Object> queryOneBusiRate(String caseid) {
		Map<String, Object> feecase = feeService.queryOneBusiRate(caseid);
		return feecase;
	}

	/**
	 * 修改扣率实例
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateBusiRate")
	public void updateBusiRate(BusiRateEntity busiRateEntity, HttpServletResponse response) {
		if (!StringUtils.isEmpty(busiRateEntity.getMinFeeStr())) {
			busiRateEntity.setMinFee(new BigDecimal(busiRateEntity.getMinFeeStr()));
		}

		if (!StringUtils.isEmpty(busiRateEntity.getMaxFeeStr())) {
			busiRateEntity.setMaxFee(new BigDecimal(busiRateEntity.getMaxFeeStr()));
		}

		if (!StringUtils.isEmpty(busiRateEntity.getFeeRateStr())) {
			busiRateEntity.setFeeRate(new BigDecimal(busiRateEntity.getFeeRateStr()));
		}
		String mark = feeService.updateBusiRate(busiRateEntity);
		JsonUtils.json_encodeAndWrite(response, mark);
	}

	/**
	 * 添加扣率实例
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("saveTxnRate")
	public void saveTxnRate(BusiRateEntity busiRateModel, HttpServletRequest request, HttpServletResponse response) {

		if (!StringUtils.isEmpty(busiRateModel.getMinFeeStr())) {
			busiRateModel.setMinFee(new BigDecimal(busiRateModel.getMinFeeStr()));
		}

		if (!StringUtils.isEmpty(busiRateModel.getMaxFeeStr())) {
			busiRateModel.setMaxFee(new BigDecimal(busiRateModel.getMaxFeeStr()));
		}

		if (!StringUtils.isEmpty(busiRateModel.getFeeRateStr())) {
			busiRateModel.setFeeRate(new BigDecimal(busiRateModel.getFeeRateStr()));
		}

		busiRateModel.setInuser(UserHelper.getCurrentUser(request).getUserId().toString());
		String mark = feeService.addOneBusiRate(busiRateModel);
		JsonUtils.json_encodeAndWrite(response, mark);
	}

	/**
	 * 卡扣率分页查询
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryCardRate")
	public Map<String, Object> queryCardRate(CardRateEntity cardRateEntity, String page, String rows,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("userId", UserHelper.getCurrentUser(request).getUserId());
		variables.put("feever", cardRateEntity.getFeever());
		variables.put("busicode", cardRateEntity.getBusicode());
		variables.put("cardtype", cardRateEntity.getCardtype());
		Map<String, Object> busiList = feeService.findCardRateByPage(variables, page, rows);
		return busiList;
	}

	/**
	 * 查询一条卡扣率版本实例信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryOneCardRate")
	public Map<String, Object> queryOneCardRate(String caseid) {
		Map<String, Object> feecase = feeService.queryOneCardRate(caseid);
		return feecase;
	}

	/**
	 * 更新卡扣率信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateCardRate")
	public void updateCardRate(CardRateEntity cardrate, HttpServletResponse response) {
		if (!StringUtils.isEmpty(cardrate.getMinFeeStr())) {
			cardrate.setMinFee(new BigDecimal(cardrate.getMinFeeStr()));
		}

		if (!StringUtils.isEmpty(cardrate.getMaxFeeStr())) {
			cardrate.setMaxFee(new BigDecimal(cardrate.getMaxFeeStr()));
		}

		if (!StringUtils.isEmpty(cardrate.getFeeRateStr())) {
			cardrate.setFeeRate(new BigDecimal(cardrate.getFeeRateStr()));
		}

		String mark = feeService.updateCardRate(cardrate);
		JsonUtils.json_encodeAndWrite(response, mark);

	}

	/**
	 * 添加卡扣率信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("saveCardRate")
	public void saveCardRate(CardRateEntity cardRateEntity, HttpServletRequest request, HttpServletResponse response) {
		if (!StringUtils.isEmpty(cardRateEntity.getMinFeeStr())) {
			cardRateEntity.setMinFee(new BigDecimal(cardRateEntity.getMinFeeStr()));
		}

		if (!StringUtils.isEmpty(cardRateEntity.getMaxFeeStr())) {
			cardRateEntity.setMaxFee(new BigDecimal(cardRateEntity.getMaxFeeStr()));
		}

		if (!StringUtils.isEmpty(cardRateEntity.getFeeRateStr())) {
			cardRateEntity.setFeeRate(new BigDecimal(cardRateEntity.getFeeRateStr()));
		}
		cardRateEntity.setInuser(UserHelper.getCurrentUser(request).getUserId());
		String mark = feeService.addOneCardRate(cardRateEntity);
		JsonUtils.json_encodeAndWrite(response, mark);
	}

	
}
