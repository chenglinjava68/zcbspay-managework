package com.zcbspay.platform.manager.controller.contract;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.zcbspay.platform.manager.merchant.bean.ContractBean;
import com.zcbspay.platform.manager.merchant.bean.EnterpriseDetaApplyBean;
import com.zcbspay.platform.manager.merchant.bean.MerchDetaApplyBean;
import com.zcbspay.platform.manager.merchant.service.ContractService;
import com.zcbspay.platform.manager.system.bean.UserBean;

@Controller
@RequestMapping("/contract")
@SuppressWarnings("all")
public class ContractController {

	@Autowired
	private ContractService contractService;
	
	@ResponseBody
    @RequestMapping("/show")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView result=new ModelAndView("/contract/contract_add");
        return result;
    }
	@ResponseBody
	@RequestMapping("/showAudit")
	public ModelAndView showAuditQuery(HttpServletRequest request) {
		ModelAndView result=new ModelAndView("/contract/contract_edit");
		return result;
	}
	
	/**
	 * 查询
	 * @param bankAccout
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResponseBody
    @RequestMapping("/query")
	public Map<String, Object> query(String merchNo, String contractNum, String debName,
			String debAccNo, String credName, String credAccNo,
			Integer page, Integer rows,
    		HttpServletRequest request, String flag) {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("merchNo", merchNo);
        variables.put("contractNum", contractNum);
        variables.put("debName", debName);
        variables.put("debAccNo", debAccNo); 
        variables.put("credName", credName);
        variables.put("credAccNo", credAccNo);
        return contractService.findAll(variables, page, rows);
    }
	/**
	 * 审核查询
	 * @param bankAccout
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryAudit")
	public Map<String, Object> queryAudit(ContractBean contract,Integer page, Integer rows,HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<?> list = contractService.findAllAccout(contract,page, rows);
		Integer total= contractService.findAllCont(contract);
		result.put("total", total);
		result.put("rows", list);
		return result;
	}
	
	
	/**
	 * 新增用户信息
	 * @param request
	 * @param bankAccout
	 * @return
	 */
	@ResponseBody
    @RequestMapping("/save")
	public Map<String, Object> save(HttpServletRequest request,ContractBean contract) {
		Map<String, String> result = new HashMap<String, String>();
		UserBean loginUser = (UserBean)request.getSession().getAttribute("LOGIN_USER");
		contract.setInUser(loginUser.getUserId());
		if (contract.getFileAddress() == null || contract.getFileAddress().equals("")) {
			return null;
		}
        return contractService.addContract(contract);
	}
	
	/**
	 * 查询商户信息
	 * @param request
	 * @param tId
	 * @return
	 */
	@ResponseBody
    @RequestMapping("/findById")
	public ContractBean findById(HttpServletRequest request,String tId) {
		return contractService.findById(tId);
	}
	
	/**
	 * 删除信息
	 * @param bankAccout
	 * @return queryCity
	 */
	@ResponseBody
    @RequestMapping("/delect")
	public Map<String, Object> delect(String tId,String withdrawOpt,HttpServletRequest request) {
		Map<String, String> result = new HashMap<String, String>();
		UserBean loginUser = (UserBean)request.getSession().getAttribute("LOGIN_USER");
		ContractBean bean = contractService.findById(tId);
		bean.setStatus("99");
		bean.setWithdrawUser(loginUser.getUserId());
		bean.setWithdrawOpt(withdrawOpt);
        return contractService.eidtContract(bean);
	}
	
	
    /**
     * @return
     */
    @ResponseBody
	@RequestMapping("/downloadImgUrl")
    public Map<String, String> downloadImgUrl(HttpServletRequest request, String fouceDownload, String tId, String certTypeCode) { 
    	ContractBean bean = contractService.findById(tId);
    	String filePath = bean.getFileAddress();
    	filePath = "javaCode/"+filePath;
        Map<String, String> result = new HashMap<String, String>();
        if (bean == null) {
            result.put("status", "fail");
        } else if (bean.equals("")) {
            result.put("status", "notExist");
        } else {
            result.put("status", "OK");
            result.put("url", filePath);
        }
        return result;
    }
	
    @ResponseBody
	@RequestMapping("/fileUpload")
    public Map<String, String> fileUpload(HttpServletRequest request,String tId) throws Exception{
    	
    	Map<String, String> result = new HashMap<String, String>();
    	//上传文件解析器
    	CommonsMultipartResolver mutiparRe = new CommonsMultipartResolver();
    	//如果是文件类型的请求
    	if (mutiparRe.isMultipart(request)) {
    		
			MultipartHttpServletRequest mhr = (MultipartHttpServletRequest) request;
			//创建子目录
//			DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
//			String dirs = df.format(new Date().getTime());
			//获取路径
			String uploadDir = request.getSession().getServletContext().getRealPath("/")+"javaCode\\";
			//如果目录不存在，创建一个目录
			if (!new File(uploadDir).exists()) {
				File dir = new File(uploadDir);
				dir.mkdirs();
			}
			//迭代文件名称
			Iterator<String> it = mhr.getFileNames();
			while(it.hasNext()){
				//获取下一个文件
				MultipartFile mf = mhr.getFile(it.next());
				if (mf !=null) {
					//原文件名称
					String resFileName = mf.getOriginalFilename();
					//保存文件名
					resFileName = UUID.randomUUID().toString().replace("-", "") + resFileName.substring(resFileName.lastIndexOf("."));
//					String fileName = rename(resFileName);
					//路径＋文件名
					File outFile = new File(uploadDir+"/"+resFileName);
					String path = "javaCode/"+resFileName;
					mf.transferTo(outFile);
					result.put("status", "OK");
					result.put("path", path);
					result.put("fileName", resFileName);
				}else{
					result.put("status", "FAIL");
				}
			}
		}
    	return result;
    }
    
    /**
     * 合同审核（通过，否决，驳回） --0 通过 1 拒绝 9 终止
     * @throws Exception
     */
    @ResponseBody
	@RequestMapping("/audit")
    public List<Map<String, Object>> audit(ContractBean contract,String tId, String isAgree, HttpServletRequest request) throws Exception {

        // 初审意见和复审意见，在页面中都是通过merchDate.stexaopt传过来的
        String stexopt = URLDecoder.decode(contract.getStexaOpt(), "utf-8");
        UserBean currentUser = (UserBean)request.getSession().getAttribute("LOGIN_USER");
    	ContractBean deta = contractService.findById(tId);
    	deta.setCvlexaOpt(stexopt);
    	deta.setCvlexaUser(currentUser.getUserId());
    	
        List<Map<String, Object>> resultlist = contractService.merchAudit(deta,isAgree);
//        if(flag.equals("6")){
//            resultlist.get(0).put("FLAG", "复审通过");
//        }else{
//           resultlist.get(0).put("FLAG", ""); 
//        }
        return resultlist;
    }
}
