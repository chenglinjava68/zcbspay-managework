package com.zcbspay.platform.manager.controller.merchant;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zcbspay.platform.manager.merchant.bean.CertType;
import com.zcbspay.platform.manager.merchant.bean.EnterpriseDetaApplyBean;
import com.zcbspay.platform.manager.merchant.bean.MerchDetaApplyBean;
import com.zcbspay.platform.manager.merchant.service.CoopInstiService;
import com.zcbspay.platform.manager.merchant.service.EnterpriseDetaService;
import com.zcbspay.platform.manager.merchant.service.MccListService;
import com.zcbspay.platform.manager.merchant.service.MerchDetaService;
import com.zcbspay.platform.manager.merchant.service.PojoProductService;
import com.zcbspay.platform.manager.pojo.Money;
import com.zcbspay.platform.manager.system.bean.UserBean;
import com.zcbspay.platform.manager.system.service.CityService;
import com.zcbspay.platform.manager.system.service.ProvinceService;
@Controller
@RequestMapping("/merchant")
@SuppressWarnings("all")
public class MerchDetaController {
    private String deposit;
    private String charge;
    private final static BigDecimal HUNDERED = new BigDecimal(100);
    
    @Autowired
	private MerchDetaService merchDetaService;
    @Autowired
    private EnterpriseDetaService enterpriseDetaService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CityService cityService;
    @Autowired
    private MccListService mccListService;
    @Autowired
    private CoopInstiService coopInstiService;
    @Autowired
    private PojoProductService pojoProductService;

    // 商户信息管理页面
    @ResponseBody
	@RequestMapping("/show")
    public ModelAndView show(HttpServletRequest request) { 
    	ModelAndView result = new ModelAndView("/merch/merch_query"); 
    	result.addObject("flag", "1"); 
    	return result; 
    }

    // 商户新增页面
    @ResponseBody
	@RequestMapping("/showMerchAdd")
    public ModelAndView showMerchAdd(HttpServletRequest request) { 
    	ModelAndView result = new ModelAndView("/merch/add/step_first_record"); 
    	return result; 
    }

    // 商户初审分页查询页面
    @ResponseBody
	@RequestMapping("/showMerchAuditQuery")
    public ModelAndView showMerchAuditQuery(HttpServletRequest request) { 
    	ModelAndView result = new ModelAndView("/merch/merch_query"); 
    	result.addObject("flag", "2"); 
    	return result;
    }

    // 商户复审分页查询页面
    @ResponseBody
	@RequestMapping("/showMerchReAuditQuery")
    public ModelAndView showMerchReAuditQuery(HttpServletRequest request) {
    	 ModelAndView result = new ModelAndView("/merch/merch_query");
    	 result.addObject("flag", "3"); 
    	 return result; 
    }

    // 商户初审审核页面
    @ResponseBody
	@RequestMapping("/toMerchAudit")
    public ModelAndView toMerchAudit(HttpServletRequest request) {
    	 ModelAndView result = new ModelAndView("/merch/merch_detail");
    	 result.addObject("flag", "5");
    	 return result; 
    }

    // 商户查询页面（查询所有状态）
    @ResponseBody
	@RequestMapping("/showMerchQueryAll")
    public ModelAndView showMerchQueryAll(HttpServletRequest request) {
   	 ModelAndView result = new ModelAndView("/merch/merch_query_all");
   	 result.addObject("flag", "10");
   	 return result; 
   }


    /**
     * 保存商户信息
     */
    @ResponseBody
	@RequestMapping("/saveMerchDeta")
    public List<?> saveMerchDeta(MerchDetaApplyBean merchDeta,EnterpriseDetaApplyBean enterprise,HttpServletRequest request) {
        String codeANDnode = merchDeta.getBankNode();
        if (!"".equals(codeANDnode) && null != codeANDnode) {
            Object[] paramaters = codeANDnode.split(",");
            merchDeta.setBankCode(paramaters[0].toString());
            merchDeta.setBankNode(paramaters[1].toString());
        }
        if (enterprise.getIsDelegation() == null) {
            enterprise.setIsDelegation(0L);
        }

        if (charge == null || charge.equals("")) {
            merchDeta.setCharge(Money.ZERO.toString());
        } else {
            merchDeta.setCharge(Money.valueOf(new BigDecimal(charge)
                    .multiply(HUNDERED)).toString());
        }

        if (deposit == null || deposit.equals("")) {
            merchDeta.setDeposit(Money.ZERO.toString());
        } else {
            merchDeta.setDeposit(Money.valueOf(new BigDecimal(deposit)
                    .multiply(HUNDERED)).toString());
        }

        UserBean currentUser = (UserBean)request.getSession().getAttribute("LOGIN_USER");
        merchDeta.setmInUser(currentUser.getUserId());
        return merchDetaService.saveMerchDeta(merchDeta, enterprise);
    }

    /**
     * 商户信息管理（查询，修改，查看详情）页面
     */
    @ResponseBody
	@RequestMapping("/queryMerch")
    public Map<String, Object> queryMerch(String memberId, String memberName, String merchStatus, Integer page, Integer rows,
    		HttpServletRequest request, String flag) {
        Map<String, Object> variables = new HashMap<String, Object>();
        UserBean loginUser = (UserBean)request.getSession().getAttribute("LOGIN_USER");
        variables.put("userId", loginUser.getUserId());
        MerchDetaApplyBean merchDeta = new MerchDetaApplyBean();
        merchDeta = null; variables.put("merberId", memberId);
        variables.put("merchName", memberName);
        variables.put("status", merchStatus);
        variables.put("flag", flag);
        return merchDetaService.findMerchByPage(variables, page, rows);
    }
    /**
     * 跳转到上传证件页面
     */
    @ResponseBody
	@RequestMapping("/toUpload")
    public ModelAndView toUpload(String merchApplyId) {
        ModelAndView result = new ModelAndView("/merch/add/step_sec_upload");
        MerchDetaApplyBean merchDeta = merchDetaService.getBean(Long.parseLong(merchApplyId));
        EnterpriseDetaApplyBean enterpriseDeta = enterpriseDetaService.findById(merchDeta.getSelfId().toString());
        result.addObject("merchDeta", merchDeta); 
        result.addObject("member", enterpriseDeta); 
        result.addObject("merchApplyId", merchApplyId);
        return result;
    }

    /**
     * 图片上传
     * @return
     * @throws Exception 
     */
    @ResponseBody
	@RequestMapping("/upload")
    public Map<String, String> upload( String certTypeCode, String merchApplyId,
    		@RequestParam("headImage")CommonsMultipartFile headImage, String fileName) throws Exception {
        Map<String, String> result = new HashMap<String, String>();
        if(certTypeCode == null || certTypeCode.equals("")){
        	result.put("status", "FAIL");
        	return result;
        }
        CertType certType = CertType.format(certTypeCode);
        String fileNamea = fileUpload(certTypeCode,merchApplyId,headImage);
        boolean isSucc = merchDetaService.upload(merchApplyId,fileNamea, certType);
        if (isSucc) {
            result.put("status", "OK");
        } else {
            result.put("status", "FAIL");
        }
        return result;
    }
    
    /**
     * 
     * 图片上传路径
     * @param certTypeCode
     * @param merchApplyId
     * @param file
     * @return
     * @throws Exception
     */
    @ResponseBody
	@RequestMapping("/fileUpload")
    public String fileUpload(String certTypeCode, String merchApplyId, CommonsMultipartFile file)
    		 throws Exception { 
		 String fileName = merchApplyId +new Date().getTime()+file.getOriginalFilename();
		 String path = "E:\\java_code\\picture\\"+fileName;
		 File newFile = new File(path);
		 file.transferTo(newFile); 
		 return fileName;
    }

    /**
     * get cert img url
     * 
     * @return
     */
    @ResponseBody
	@RequestMapping("/downloadImgUrl")
    public Map<String, String> downloadImgUrl(HttpServletRequest request, String fouceDownload, String merchApplyId, String certTypeCode) { 
    	String filePath = merchDetaService.downloadFromFtp(merchApplyId, CertType.format(certTypeCode));
        Map<String, String> result = new HashMap<String, String>();
        if (filePath == null) {
            result.put("status", "fail");
        } else if (filePath.equals("")) {
            result.put("status", "notExist");
        } else {
            result.put("status", "OK");
            result.put("url", filePath);
        }
        return result;
    }

    /**
     * 商户修改页面
     * 
     * @return
     */
    @ResponseBody
	@RequestMapping("/toMerchChange")
    public ModelAndView toMerchChange(String merchApplyId, String oldBankName) {
    	 ModelAndView result = new ModelAndView("/merch/merch_change");
    	 MerchDetaApplyBean merchDeta = merchDetaService.getBean(Long.parseLong(merchApplyId));
    	 EnterpriseDetaApplyBean enterpriseDeta = enterpriseDetaService.findById(merchDeta.getSelfId().toString()); 
    	 oldBankName = merchDetaService.queryBankName(merchDeta.getBankNode(), merchDeta.getBankCode()); 
    	 result.addObject("merchDeta", merchDeta); result.addObject("member", enterpriseDeta);
    	 result.addObject("oldBankName", oldBankName); 
    	 result.addObject("merchApplyId", merchApplyId); 
    	 result.addObject("charge", merchDeta.getCharge().toString()); 
    	 result.addObject("deposit", merchDeta.getDeposit().toString());
    	 return result; 
    }
    public class MerchantThread extends Thread {
        private String sPath;

        public void run() {
            try {
                deleteFile(sPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public MerchantThread(String sPath) {
            this.sPath = sPath;
        }

        /**
         * 删除单个文件
         * @param sPath
         *            被删除文件的文件名
         * @return 单个文件删除成功返回true，否则返回false
         */
        @SuppressWarnings("static-access")
        public void deleteFile(String sPath) {
            try {// 保留一小时
                Thread.currentThread().sleep(1000 * 60 * 60);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            File file = new File(sPath);
            // 路径为文件且不为空则进行删除
            if (file.isFile() && file.exists()) {
                file.delete();
            }
        }

        public String getsPath() {
            return sPath;
        }

        public void setsPath(String sPath) {
            this.sPath = sPath;
        }
    }

    /**
     * 修改商户信息
     * @return
     */
    @ResponseBody
	@RequestMapping("/saveChangeMerchDeta")
    public List<?> saveChangeMerchDeta(MerchDetaApplyBean merchDeta, EnterpriseDetaApplyBean enterpriseDeta, String merchApplyId, HttpServletRequest request) { 
        if (enterpriseDeta.getIsDelegation() == null) {
            enterpriseDeta.setIsDelegation(0L);
        }

        if (charge == null || charge.equals("")) {
            merchDeta.setCharge(Money.ZERO.toString());
        } else {
            merchDeta.setCharge(Money.valueOf(new BigDecimal(charge).multiply(HUNDERED)).toString());
        }

        if (deposit == null || deposit.equals("")) {
            merchDeta.setDeposit(Money.ZERO.toString());
        } else {
            merchDeta.setDeposit(Money.valueOf(new BigDecimal(deposit)
                    .multiply(HUNDERED)).toString());
        }

        UserBean currentUser = (UserBean)request.getSession().getAttribute("LOGIN_USER");
        merchDeta.setmInUser(currentUser.getUserId()); 
        List<?> resultlist = merchDetaService.saveChangeMerchDeta(merchApplyId, merchDeta, enterpriseDeta); 
        return resultlist; 
    }

    /**
     * 商户申请提交
     * @return
     */
    @ResponseBody
	@RequestMapping("/commitMerch")
    public Map<String, String> commitMerch(String merchApplyId) {
        Map<String, String> result = new HashMap<String, String>();
        boolean isSucc = merchDetaService.commitMerch(merchApplyId);
        if (isSucc) {
            result.put("status", "OK");
        } else {
            result.put("status", "FAIL");
        }
        return result;
    }

    /**
     * 查看某一条商户信息,查看商户详细信息
     * @return
     */
    @ResponseBody
	@RequestMapping("/toMerchDetail")
    public ModelAndView toMerchDetail(String merchApplyId, HttpServletRequest request, String flag) { 
    	ModelAndView result = new ModelAndView("/merch/merch_detail"); 
    	UserBean currentUser = (UserBean)request.getSession().getAttribute("LOGIN_USER");
    	Map<String, Object> merchMap = merchDetaService.queryApplyMerchDeta(merchApplyId, currentUser.getUserId()); 
    	 result.addObject("merchMap", merchMap); 
    	 result.addObject("flag", flag);
    	return result; 
    }
    /***
     * 复审未通过，则允许变更商户申请信息
     * @return
     */
    @ResponseBody
	@RequestMapping("/toMerchModify")
    public ModelAndView toMerchModify(String merchApplyId, HttpServletRequest request, String flag) { 
    	ModelAndView result = new ModelAndView("/merch/merch_detail"); 
    	UserBean currentUser = (UserBean)request.getSession().getAttribute("LOGIN_USER");
    	Map<String, Object> merchMap = merchDetaService.queryApplyMerchDeta(merchApplyId, currentUser.getUserId()); 
    	 result.addObject("merchMap", merchMap); 
    	 result.addObject("flag", flag);
    	return result; 
        
    }

    /**
     * 商户审核（通过，否决，驳回） --0 通过 1 拒绝 9 终止
     * @throws Exception
     */
    @ResponseBody
	@RequestMapping("/audit")
    public List<Map<String, Object>> audit(MerchDetaApplyBean merchDeta, String flag, String merchApplyId, String isAgree, HttpServletRequest request) throws Exception {

        // 初审意见和复审意见，在页面中都是通过merchDate.stexaopt传过来的
        String stexopt = URLDecoder.decode(merchDeta.getStexaOpt(), "utf-8");
        UserBean currentUser = (UserBean)request.getSession().getAttribute("LOGIN_USER");
        if (flag.equals("2")) {// 初审，需要记录初审人和初审意见
            merchDeta.setStexaOpt(stexopt);
            merchDeta.setStexaUser(currentUser.getUserId());
        } else if (flag.equals("3")) {// 复审，需要记录复审人和复审意见
            merchDeta.setCvlexaOpt(stexopt);
            merchDeta.setCvlexaUser(currentUser.getUserId());
        }else if(flag.equals("5")){//变更初审，需要记录初审人和初审意见
            merchDeta.setStexaOpt(stexopt);
            merchDeta.setStexaUser(currentUser.getUserId());
        }else if(flag.equals("6")){//变更复审，需要记录复审人和复审意见
            merchDeta.setCvlexaOpt(stexopt);
            merchDeta.setCvlexaUser(currentUser.getUserId());
        }
        EnterpriseDetaApplyBean deta = enterpriseDetaService.findById(merchApplyId);
        List<Map<String, Object>> resultlist = merchDetaService.merchAudit(merchApplyId, merchDeta, deta.getMemId(), flag, isAgree);
        if(flag.equals("6")){
            resultlist.get(0).put("FLAG", "复审通过");
        }else{
           resultlist.get(0).put("FLAG", ""); 
        }
        return resultlist;
    }

    /**
     * 商户秘钥下载
     * @return
     */
    @ResponseBody
	@RequestMapping("/loadMerchMk")
    public String loadMerchMk() {
//        if (memberId != null && !memberId.equals("")) {
//        }
//        merchMap = serviceContainer.getMerchDetaService().loadMerchMk(memberId);
//        if (merchMap == null) {
//            json_encode("没有密钥");
//            return null;
//        }
        return "merch_mk_export";
    }
    /**
     * 查询正式在用的商户详细信息    
     * @return
     */
    @ResponseBody
	@RequestMapping("/toOfficalMerchDetail")
    public String toOfficalMerchDetail(){
//        Long userId = currentUser.getUserId();
//        merchMap = serviceContainer.getMerchDetaService().queryMerchDeta(
//                Long.parseLong(merchId), userId);
        return "merch_detail";
    }
    
    /**
     * 省
     */
    @ResponseBody
	@RequestMapping("/queryProvince")
    public List<?> queryProvince() {
        return provinceService.findAll();
    }

    /**
     * 市
     */
    @ResponseBody
	@RequestMapping("/queryCity")
    public List<?> queryCity(Long pid) {
    	return cityService.findNotMuniByPid(pid.longValue());
    }

    /**
     * 县
     */
    @ResponseBody
	@RequestMapping("/queryCounty")
    public List<?> queryCounty(String pid) {
    	return merchDetaService.queryCounty(pid);
    }

    /**
     * 所属行业
     */
    @ResponseBody
	@RequestMapping("/queryTrate")
    public List<?> queryTrate() {
    	return merchDetaService.queryTrade();
    }
    /**
     * 商户类型
     */
    @ResponseBody
	@RequestMapping("/queryMerchType")
    public List<?> queryMerchType() {
    	return merchDetaService.queryMerchType();
    }

    /**
     * 商户清算类型
     */
    @ResponseBody
	@RequestMapping("/queryMerchClearType")
    public List<?> queryMerchClearType() {
    	return merchDetaService.querysetltype();
    }

    /**
     * 所属商户
     */
    @ResponseBody
	@RequestMapping("/showMerchParent")
    public List<?> showMerchParent() {
    	return merchDetaService.queryMerchParent();
    }

    /**
     * 关键字查询开户行
     */
    @ResponseBody
	@RequestMapping("/queryBankNode")
    public List<?> queryBankNode(String bankName, Integer page, Integer rows) { 
    	return merchDetaService.queryBankNode(bankName, page, rows);
     }

    /**
     * 收银台版本
     * @return
     */
    @ResponseBody
	@RequestMapping("/queryCash")
    public List<?> queryCash() {
    	return merchDetaService.queryCashAll();
    }

    /**
     * 交易渠道
     */
    @ResponseBody
	@RequestMapping("/queryChnlnameAll")
    public List<?> queryChnlnameAll() {
    	return merchDetaService.queryChnlnameAll();
    }

    /**
     * 路由版本
     */
    @ResponseBody
	@RequestMapping("/queryRouteAll")
    public List<?> queryRouteAll() {
    	return merchDetaService.queryRouteAll();
    }

    /**
     * 风控版本
     */
    @ResponseBody
	@RequestMapping("/queryRiskType")
    public List<?> queryRiskType(String vid) {
    	return merchDetaService.queryRiskType(vid);
    }

    /**
     * 分润版本
     */
    @ResponseBody
	@RequestMapping("/querySplit")
    public List<?> querySplit(String vid) {
    	return merchDetaService.querySplit(vid);
    }

    /**
     * 扣率版本
     */
    @ResponseBody
	@RequestMapping("/queryFee")
    public List<?> queryFee(String vid) {
    	return merchDetaService.queryFee(vid);
    }

    /**
     * 清算周期
     */
    @ResponseBody
	@RequestMapping("/querySetlcycleAll")
    public List<?> querySetlcycleAll() {
    	return merchDetaService.querySetlcycleAll();
    }
    
    /**
     * 商品类型
     */
    @ResponseBody
	@RequestMapping("/queryMccList")
    public List<?> queryMccList(){
    	return mccListService.findAll();
    }
    
    /**
     * 合作机构
     * @return
     */
    @ResponseBody
	@RequestMapping("/queryAll")
    public List<?> queryAll(){
    	return coopInstiService.findAll();
    }
    @ResponseBody
	@RequestMapping("/queryProduct")
    public List<?> queryProduct(long coopInstiId){
    	return pojoProductService.queryProduct(coopInstiId);
    }
    
//*********************************商户信息变更*******************************************
    /**
     * 商户信息变更菜单
     * @return
     */
    @ResponseBody
	@RequestMapping("/showMerchModify")
    public String showMerchModify(String flag){
        flag="4";
        return "merch_modify_query";
    }
    /**
     * 商户变更初审
     */
    @ResponseBody
	@RequestMapping("/merchModifyFirstCheck")
    public String merchModifyFirstCheck(String flag){
        flag="5";
        return "merch_modify_query";
    }
    /**
     * 商户变更复审
     */
    @ResponseBody
	@RequestMapping("/merchModifySecondCheck")
    public String merchModifySecondCheck(String flag){
        flag="6";
        return "merch_modify_query";
    }
    /**
     * 商户信息变更界面
     * @return
     */
    @ResponseBody
	@RequestMapping("/queryMerchModify")
    public String queryMerchModify(){
//        Map<String, Object> variables = new HashMap<String, Object>();
//        variables.put("userId", currentUser.getUserId());
//        if (merchDeta != null) {
//            variables.put("merberId", enterpriseDeta.getMemberId());
//            variables.put("merchName", enterpriseDeta.getMemberName());
//        }
//        variables.put("flag", flag);
//        Map<String, Object> merchList = serviceContainer.getMerchDetaService()
//                .findMerchModifyByPage(variables, getPage(), getRows());
//        json_encode(merchList);
        return null;
    }
    
    /**
     * 商户信息变更列表的变更功能
     * @return
     */
    @ResponseBody
	@RequestMapping("/toMerchModifyEdit")
    public String toMerchModifyEdit(){
//        merchDeta = serviceContainer.getMerchDetaService().getBean(
//                Long.parseLong(merchApplyId));
//        oldBankName = serviceContainer.getMerchDetaService().queryBankName(
//                merchDeta.getBankNode(), merchDeta.getBankCode());
//
//        charge = merchDeta.getCharge().toString();
//        deposit = merchDeta.getDeposit().toString();

        return "merch_modify_edit";    
    }
    
    /**
     * 点击下一步，保存本页信息
     * @return
     */
    @ResponseBody
	@RequestMapping("/toUploadModifyInfo")
    public String toUploadModifyInfo(){
//        merchDeta = serviceContainer.getMerchDetaService().getBean(
//                Long.parseLong(merchApplyId));
//        if (merchDeta == null) {
//        }

        return "toUploadModifyInfo";
    }
    /**
     * 
     * 商户变更的提交申请功能
     * @return
     */
    @ResponseBody
	@RequestMapping("/commitMerchModify")
    public String commitMerchModify(){
//        Map<String, String> result = new HashMap<String, String>();
//        IMerchDetaService merchDetaService = serviceContainer
//                .getMerchDetaService();
//        boolean isSucc = merchDetaService.commitMerchModify(Long
//                .parseLong(merchApplyId));
//        if (isSucc) {
//            result.put("status", "OK");
//        } else {
//            result.put("status", "FAIL");
//        }
//        json_encode(result);
        return null;
    }
    
    /**
     * 点击下一步，对商户变更信息做保存更新
     * @return
     */
    @ResponseBody
	@RequestMapping("/saveMerchModifyDeta")
    public String saveMerchModifyDeta(){
//        if (enterpriseDeta.getIsDelegation() == null) {
//            enterpriseDeta.setIsDelegation(0L);
//        }
//
//        if (enterpriseDeta.getIsDelegation() == null) {
//            enterpriseDeta.setIsDelegation(0L);
//        }
//
//        if (charge == null || charge.equals("")) {
//            merchDeta.setCharge(Money.ZERO);
//        } else {
//            merchDeta.setCharge(Money.valueOf(new BigDecimal(charge)
//                    .multiply(HUNDERED)));
//        }
//
//        if (deposit == null || deposit.equals("")) {
//            merchDeta.setDeposit(Money.ZERO);
//        } else {
//            merchDeta.setDeposit(Money.valueOf(new BigDecimal(deposit)
//                    .multiply(HUNDERED)));
//        }
//
//        List<?> resultlist = serviceContainer.getMerchDetaService()
//                .saveMerchModifyDeta(Long.parseLong(merchApplyId), merchDeta);
//        merchDeta.setmInUser(currentUser.getUserId());
//        json_encode(resultlist.get(0));
        return null;
        
    }
    /**
     * 变更信息的审核（初审、复审）
     * @return
     */
    
//    public String toMerchModifyDetail(){
//        Long userId = currentUser.getUserId();
//        merchMap = serviceContainer.getMerchDetaService().queryModifyMerchDeta(
//                Long.parseLong(merchApplyId), userId);
//        return "merch_modify_detail";  
//    }
//    public ServiceContainer getServiceContainer() {
//        return serviceContainer;
//    }

 //------------------------------------企业审核和查询----------------------------------------------------   
    /**
     * 企业初审菜单
     * @param serviceContainer
     */
    @ResponseBody
	@RequestMapping("/enterpriseFirstExam")
    public String enterpriseFirstExam(String flag){  
        flag="2";
        return "enterprise_exam_query";       
    }
    
    /***
     * 企业复审菜单
     * @param serviceContainer
     */
    @ResponseBody
	@RequestMapping("/enterpriseSecondExam")
    public String enterpriseSecondExam(String flag){
        flag="3";
        return "enterprise_exam_query";
    }
    
    /**
     * 企业查询菜单
     * @param serviceContainer
     */
    @ResponseBody
	@RequestMapping("/enterpriseQuery")
    public String enterpriseQuery(String flag){
        flag="10";
        return "enterpriseQueryAll";
    }
    
    /**
     * 初审、复审的查询界面
     * @param serviceContainer
     */
    @ResponseBody
	@RequestMapping("/queryEnterprise")
    public String queryEnterprise(){
//        Map<String, Object> variables = new HashMap<String, Object>();
//        variables.put("userId", currentUser.getUserId());
//        if (enterprise != null) {
//            variables.put("enterpriseMemberId", enterprise.getEnterpriseMemberId());//会员编号
//            variables.put("enterpriseName", enterprise.getEnterpriseName());//企业名称
//            variables.put("enterpriseStatus", enterprise.getEnterpriseStatus());//状态
//        }
//        variables.put("flag", flag);
//        Map<String, Object> enterpriseList = serviceContainer.getMerchDetaService()
//                .findEnterpriseByPage(variables, getPage(), getRows());
//        json_encode(enterpriseList);
        return null;
    }
    /**
     * 审核界面
     * @param serviceContainer
     */
    @ResponseBody
	@RequestMapping("/toEnterpriseDetail")
    public String toEnterpriseDetail(){
//        Long userId = currentUser.getUserId();
//        enterpriseDeta = serviceContainer.getMerchDetaService().queryEnterpriseExamDeta
//                (Long.parseLong(enterpriseApplyId),userId);
        return "enterpriseFirstExam"; 
    }
    
    /**
     * 企业查询功能
     * @param serviceContainer
     */
    @ResponseBody
	@RequestMapping("/queryEnterpriseAll")
    public String queryEnterpriseAll(){
//        Map<String, Object> variables = new HashMap<String, Object>();
//        variables.put("userId", currentUser.getUserId());
//        if (merchDeta != null) {
//            variables.put("merberId", enterpriseDeta.getMemberId());
//            variables.put("merchName", enterpriseDeta.getMemberName());
//            variables.put("address",
//                    ((Enterprise) merchDeta.getMember()).getAddress());
//            variables.put("status", merchStatus);
//            variables.put(
//                    "coopInstiId",
//                    enterpriseDeta.getInstiCode() != null ? merchDeta
//                            .getMember() : null);
//        }
//
//        variables.put("flag", flag);
//        Map<String, Object> merchList = serviceContainer.getMerchDetaService()
//                .findMerchByPage(variables, getPage(), getRows());
//        json_encode(merchList);
        return null;
    }
    /**
     * 企业的审核（初审、复审）
     * @return
     * @throws IOException 
     */
    @ResponseBody
	@RequestMapping("/examEnterprise")
    public String examEnterprise() throws IOException{
        // 初审意见和复审意见，在页面中都是通过merchDate.stexaopt传过来的
//        String stexopt = URLDecoder.decode(enterprise.getStexaOpt(), "utf-8");
//        if (flag.equals("2")) {// 初审，需要记录初审人和初审意见
//            enterprise.setStexaOpt(stexopt);
//            enterprise.setStexaUser(currentUser.getUserId());
//        } else if (flag.equals("3")) {// 复审，需要记录复审人和复审意见
//            enterprise.setCvlexaOpt(stexopt);
//            enterprise.setCvlexaUser(currentUser.getUserId());
//        }        
//        List<Map<String, Object>> resultlist = (List<Map<String, Object>>) serviceContainer
//        .getMerchDetaService().enterpriseAudit(Long.parseLong(enterpriseApplyId),
//              enterprise, flag, isAgree);
        return null;
    } 
    
    /**
     * 加载证件照片等
     * @param serviceContainer
     */
    @ResponseBody
	@RequestMapping("/downloadEnterpriseImgUrl")
    public String downloadEnterpriseImgUrl(){
//        String webRootPath = ServletActionContext.getServletContext().getRealPath("/");
//        String realpath = webRootPath + "/" + CommonUtil.DOWNLOAD_ROOTPATH;
//        boolean fouce = (fouceDownload != null && fouceDownload.equals("fouce"));
//        String filePath = serviceContainer.getFtpEnterpriseService().downloadEnterpriseFromFtp
//                (Long.parseLong(enterpriseApplyId), realpath,CertType.format(certTypeCode), fouce);
//        Map<String, String> result = new HashMap<String, String>();
//        if (filePath == null) {
//            result.put("status", "fail");
//        } else if (filePath.equals("")) {
//            result.put("status", "notExist");
//        } else {
//            result.put("status", "OK");
//            result.put("url", filePath);
//            new MerchantThread(webRootPath + "/" + filePath).start();
//        }
        return null;
    }
    
}
