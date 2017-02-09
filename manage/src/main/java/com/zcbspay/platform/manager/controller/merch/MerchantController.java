package com.zcbspay.platform.manager.controller.merch;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zcbspay.platform.manager.merchant.bean.EnterpriseBean;
import com.zcbspay.platform.manager.merchant.bean.MerchBean;
import com.zcbspay.platform.manager.merchant.service.MccListService;
import com.zcbspay.platform.manager.merchant.service.MerchDetaService;
import com.zcbspay.platform.manager.system.bean.UserBean;
import com.zcbspay.platform.manager.system.service.CityService;
import com.zcbspay.platform.manager.system.service.ProvinceService;

@Controller
@RequestMapping("/merchant")
@SuppressWarnings("all")
public class MerchantController {
//	    private static final Log log = LogFactory.getLog(PojoMerchDeta.class);
	    private static final long serialVersionUID = 1L;
	    // private UserModel user;
	    private long pid;
	    private String vid;
//	    private String flag;// 标记流程 1商户信息管理列表 2初审查询列表 3复审查询列表 5商户初审审核页面 10商户查询页面
	    private String bankName;
//	    private MerchDeta merchDeta;
//	    private Enterprise enterprise;
	    private Map<String, Object> merchMap;
	    private File headImage;
	    private String headImageFileName; // 文件名称
	    private String headImageContentType;
	    private String memberId;
	    private String imageURL;
	    private String merchId;
	    private String certTypeCode;
//	    @Autowired
//	    MemberService memberService;
	    private String isAgree;
	    private String oldBankName;
	    private String fouceDownload;
	    private String merchApplyId;
	    private String deposit;
	    private String charge;
	    private String merchStatus;
	    private final static BigDecimal HUNDERED = new BigDecimal(100);

	    private String enterpriseApplyId;
	    private Map<String,Object> enterpriseDeta;
	    private String enterpriseId;
	    
	    @Autowired
	    private MerchDetaService merchDetaService;
	    @Autowired
		private ProvinceService provinceService;
	    @Autowired
		private CityService cityService;
	    @Autowired
	    private MccListService mccListService;
	    // 商户信息管理页面
	    @ResponseBody
	    @RequestMapping("/show")
	    public ModelAndView show(HttpServletRequest request) {
	        ModelAndView result=new ModelAndView("/merch/merch_query");
	        result.addObject("flag", "1");
	        return result;
	    }

	    // 商户新增页面
	    @ResponseBody
	    @RequestMapping("/showMerchAdd")
	    public ModelAndView showMerchAdd(HttpServletRequest request) {
	        ModelAndView result=new ModelAndView("/merch/add/step_first_record");
	        return result;
	    }

	    // 商户初审分页查询页面
	    @ResponseBody
	    @RequestMapping("/showMerchAuditQuery")
	    public ModelAndView showMerchAuditQuery(HttpServletRequest request) {
	        ModelAndView result=new ModelAndView("/merch/merch_query");
	        result.addObject("flag", "2");
	        return result;
	    }

	    // 商户复审分页查询页面
	    @ResponseBody
	    @RequestMapping("/showMerchReAuditQuery")
	    public ModelAndView showMerchReAuditQuery(HttpServletRequest request) {
	        ModelAndView result=new ModelAndView("/merch/merch_query");
	        result.addObject("flag", "3");
	        return result;
	    }

	    // 商户初审审核页面
	    @ResponseBody
	    @RequestMapping("/toMerchAudit")
	    public ModelAndView toMerchAudit(HttpServletRequest request) {
	        ModelAndView result=new ModelAndView("/merch/merch_detail");
	        result.addObject("flag", "5");
	        return result;
	    }

	    // 商户查询页面（查询所有状态）
	    @ResponseBody
	    @RequestMapping("/showMerchQueryAll")
	    public ModelAndView showMerchQueryAll(HttpServletRequest request) {
	        ModelAndView result=new ModelAndView("/merch/merch_query_all");
	        result.addObject("flag", "10");
	        return result;
	    }
	    


	    /**
	     * 保存商户信息
	     * 
	     * @return
	     */
	    public List<?> saveMerchDeta(MerchBean merchDeta,EnterpriseBean enterprise,HttpServletRequest request) {
//	        String codeANDnode = merchDeta.getBankNode();
//	        if (!"".equals(codeANDnode) && null != codeANDnode) {
//	            Object[] paramaters = codeANDnode.split(",");
//	            merchDeta.setBankCode(paramaters[0].toString());
//	            merchDeta.setBankNode(paramaters[1].toString());
//	        }
//	        if (enterprise.getIsDelegation() == null) {
//	            enterprise.setIsDelegation(0L);
//	        }
//
//	        if (charge == null || charge.equals("")) {
//	            merchDeta.setCharge(Money.ZERO);
//	        } else {
//	            merchDeta.setCharge(Money.valueOf(new BigDecimal(charge)
//	                    .multiply(HUNDERED)));
//	        }
//
//	        if (deposit == null || deposit.equals("")) {
//	            merchDeta.setDeposit(Money.ZERO);
//	        } else {
//	            merchDeta.setDeposit(Money.valueOf(new BigDecimal(deposit)
//	                    .multiply(HUNDERED)));
//	        }
//
//	        UserBean currentUser = (UserBean) request.getSession().getAttribute("LOGIN_USER");
//	        merchDeta.setmInUser(currentUser.getUserId());
//	        merchDeta.setMember(enterprise);
//
//	        return merchDetaService.saveMerchDeta(merchDeta);
	    	return null;
	    }

	    /**
	     * 商户信息管理（查询，修改，查看详情）页面
	     * 
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping("/queryMerch")
	    public Map<String, Object> queryMerch(String memberId,String memberName,Integer page,Integer rows,HttpServletRequest request,String flag) {
	        Map<String, Object> variables = new HashMap<String, Object>();
	        UserBean loginUser = (UserBean) request.getSession().getAttribute("LOGIN_USER");
	        variables.put("userId",loginUser.getUserId());
	        MerchBean merchDeta = new MerchBean();
	        merchDeta =null;
        	variables.put("merberId", memberId);
            variables.put("merchName", memberName);
	        variables.put("flag", flag);
	        return merchDetaService.findMerchByPage(variables, page, rows);
	    }
	    /**
	     * 跳转到上传证件页面
	     * 
	     * @return
	     */
	    public String toUpload() {
//	        merchDeta = merchDetaService.getBean(
//	                Long.parseLong(merchApplyId));
//	        if (merchDeta == null) {
//	            // TODO return merchant not exist error
//	        }

	        return "toUpload";
	    }

	    /**
	     * 
	     * @return
	     */
	    public String upload() {
//	        Map<String, String> result = new HashMap<String, String>();
//	        if (certTypeCode == null || certTypeCode.equals("")) {
//	            result.put("status", "FAIL");
//	            return null;
//	        }
//	        CertType certType = CertType.format(certTypeCode);
//
//	        IMerchDetaService merchDetaService = serviceContainer
//	                .getMerchDetaService();
//	        boolean isSucc = merchDetaService.upload(Long.parseLong(merchApplyId),
//	                headImageFileName, headImage, certType);
//	        if (isSucc) {
//	            result.put("status", "OK");
//	        } else {
//	            result.put("status", "FAIL");
//	        }
//	        json_encode(result);
	        return null;
	    }

	    /**
	     * get cert img url
	     * 
	     * @return
	     */
	    public String downloadImgUrl() {
//	        String webRootPath = ServletActionContext.getServletContext()
//	                .getRealPath("/");
//	        String realpath = webRootPath + "/" + CommonUtil.DOWNLOAD_ROOTPATH;
//	        boolean fouce = (fouceDownload != null && fouceDownload.equals("fouce"));
//	        String filePath = merchDetaService.downloadFromFtp
//	                (Long.parseLong(merchApplyId), realpath,CertType.format(certTypeCode), fouce);
//	        Map<String, String> result = new HashMap<String, String>();
//	        if (filePath == null) {
//	            result.put("status", "fail");
//	        } else if (filePath.equals("")) {
//	            result.put("status", "notExist");
//	        } else {
//	            result.put("status", "OK");
//	            result.put("url", filePath);
//	            new MerchantThread(webRootPath + "/" + filePath).start();
//	        }
//	        json_encode(result);
	        return null;
	    }

	    /**
	     * 商户修改页面
	     * 
	     * @return
	     */
	    public String toMerchChange() {
//	        merchDeta = merchDetaService.getBean(
//	                Long.parseLong(merchApplyId));
//	        oldBankName = merchDetaService.queryBankName(
//	                merchDeta.getBankNode(), merchDeta.getBankCode());
//
//	        charge = merchDeta.getCharge().toString();
//	        deposit = merchDeta.getDeposit().toString();

	        return "merch_change";
	    }

	    public class MerchantThread extends Thread {
//	        private String sPath;
//
//	        public void run() {
//	            try {
//	                deleteFile(sPath);
//	            } catch (Exception e) {
//	                e.printStackTrace();
//	            }
//	        }
//
//	        public MerchantThread(String sPath) {
//	            this.sPath = sPath;
//	        }
//
//	        /**
//	         * 删除单个文件
//	         * 
//	         * @param sPath
//	         *            被删除文件的文件名
//	         * @return 单个文件删除成功返回true，否则返回false
//	         */
//	        @SuppressWarnings("static-access")
//	        public void deleteFile(String sPath) {
//	            try {// 保留一小时
//	                Thread.currentThread().sleep(1000 * 60 * 60);
//	            } catch (InterruptedException e) {
//	                // TODO Auto-generated catch block
//	                e.printStackTrace();
//	            }
//	            File file = new File(sPath);
//	            // 路径为文件且不为空则进行删除
//	            if (file.isFile() && file.exists()) {
//	                file.delete();
//	            }
//	        }
//
//	        public String getsPath() {
//	            return sPath;
//	        }
//
//	        public void setsPath(String sPath) {
//	            this.sPath = sPath;
//	        }
	    }

	    /**
	     * 修改商户信息
	     * 
	     * @return
	     */
	    public String saveChangeMerchDeta() {
//	        if (merchDeta.getMember().getIsDelegation() == null) {
//	            merchDeta.getMember().setIsDelegation(0L);
//	        }
//
//	        if (merchDeta.getMember().getIsDelegation() == null) {
//	            merchDeta.getMember().setIsDelegation(0L);
//	        }
//
//	        if (charge == null || charge.equals("")) {
//	            merchDeta.setCharge(Money.ZERO);
//	        } else {
//	            merchDeta.setCharge(Money.valueOf(new BigDecimal(charge)
//	                    .multiply(HUNDERED)));
//	        }
//
//	        if (deposit == null || deposit.equals("")) {
//	            merchDeta.setDeposit(Money.ZERO);
//	        } else {
//	            merchDeta.setDeposit(Money.valueOf(new BigDecimal(deposit)
//	                    .multiply(HUNDERED)));
//	        }
//
//	        List<?> resultlist = merchDetaService
//	                .saveChangeMerchDeta(Long.parseLong(merchApplyId), merchDeta);
//	        merchDeta.setmInUser(getCurrentUser().getUserId());
//	        json_encode(resultlist.get(0));
	        return null;
	    }

	    /**
	     * 商户申请提交
	     * 
	     * @return
	     */
	    public String commitMerch() {
//	        Map<String, String> result = new HashMap<String, String>();
//	        IMerchDetaService merchDetaService = serviceContainer
//	                .getMerchDetaService();
//	        boolean isSucc = merchDetaService.commitMerch(Long
//	                .parseLong(merchApplyId));
//	        if (isSucc) {
//	            result.put("status", "OK");
//	        } else {
//	            result.put("status", "FAIL");
//	        }
//	        json_encode(result);
	        return null;
	    }

	    /**
	     * 查看某一条商户信息,查看商户详细信息
	     * 
	     * @return
	     */
	    public String toMerchDetail() {
//	        Long userId = getCurrentUser().getUserId();
//	        merchMap = merchDetaService.queryApplyMerchDeta(
//	                Long.parseLong(merchApplyId), userId);
	        return "merch_detail";
	    }
	    /***
	     * 复审未通过，则允许变更商户申请信息
	     * 
	     * @return
	     */
	    public String toMerchModify(){
//	        Long userId = getCurrentUser().getUserId();
//	        merchMap = merchDetaService.queryApplyMerchDeta(Long.parseLong(merchApplyId), userId);
	        return "merch_modify";
	        
	    }

	    /**
	     * 商户审核（通过，否决，驳回） --0 通过 1 拒绝 9 终止
	     * 
	     * @return
	     * @throws Exception
	     */
	    public String audit() throws Exception {

//	        // 初审意见和复审意见，在页面中都是通过merchDate.stexaopt传过来的
//	        String stexopt = URLDecoder.decode(merchDeta.getStexaOpt(), "utf-8");
//	        if (flag.equals("2")) {// 初审，需要记录初审人和初审意见
//	            merchDeta.setStexaOpt(stexopt);
//	            merchDeta.setStexaUser(getCurrentUser().getUserId());
//	        } else if (flag.equals("3")) {// 复审，需要记录复审人和复审意见
//	            merchDeta.setCvlexaOpt(stexopt);
//	            merchDeta.setCvlexaUser(getCurrentUser().getUserId());
//	        }else if(flag.equals("5")){//变更初审，需要记录初审人和初审意见
//	            merchDeta.setStexaOpt(stexopt);
//	            merchDeta.setStexaUser(getCurrentUser().getUserId());
//	        }else if(flag.equals("6")){//变更复审，需要记录复审人和复审意见
//	            merchDeta.setCvlexaOpt(stexopt);
//	            merchDeta.setCvlexaUser(getCurrentUser().getUserId());
//	        }
//	        @SuppressWarnings("unchecked")
//	        List<Map<String, Object>> resultlist = (List<Map<String, Object>>) serviceContainer
//	                .getMerchDetaService().merchAudit(Long.parseLong(merchApplyId),
//	                        merchDeta, flag, isAgree);
//	        if(flag.equals("6")){
//	            resultlist.get(0).put("FLAG", "复审通过");
//	        }else{
//	           resultlist.get(0).put("FLAG", ""); 
//	        }
//	        json_encode(resultlist);
	        return null;
	    }

	    /**
	     * 商户秘钥下载
	     * 
	     * @return
	     */
	    public String loadMerchMk() {
//	        if (memberId != null && !memberId.equals("")) {
//	        }
//	        merchMap = merchDetaService.loadMerchMk(memberId);
//	        if (merchMap == null) {
//	            json_encode("没有密钥");
//	            return null;
//	        }
	        return "merch_mk_export";
	    }
	    /**
	     * 查询正式在用的商户详细信息    
	     * @return
	     */
	    public String toOfficalMerchDetail(){
//	        Long userId = getCurrentUser().getUserId();
//	        merchMap = merchDetaService.queryMerchDeta(
//	                Long.parseLong(merchId), userId);
	        return "merch_detail";
	    }
	    
	    
	    /*
	     * 加载商户新增页面所有下拉框。这部分违反单一职责，考虑将这部分责任分散到对应的action中
	     */
	    /**
	     * 省
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping("/queryProvince")
	    public List<?> queryProvince() {
	        return provinceService.findAll();
	    }

	    /**
	     * 市
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping("/queryCity")
	    public List<?> queryCity(Long pid) {
	        return cityService.findNotMuniByPid(pid);
	    }

	    /**
	     * 县
	     * 
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping("/queryCounty")
	    public List<?> queryCounty(String pid) {
	    	return merchDetaService.queryCounty(pid);
	    }

	    /**
	     * 所属行业
	     * 
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping("/queryTrate")
	    public List<?> queryTrate() {
	        return merchDetaService.queryTrade();
	    }

	    /**
	     * 商户类型
	     * 
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping("/queryMerchType")
	    public List<?> queryMerchType() {
	        return merchDetaService.queryMerchType();
	    }

	    /**
	     * 商户清算类型
	     * 
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping("/queryMerchClearType")
	    public List<?> queryMerchClearType() {
	        return merchDetaService.querysetltype();
	    }

	    /**
	     * 所属商户
	     * 
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping("/showMerchParent")
	    public List<?> showMerchParent() {
	        return merchDetaService.queryMerchParent();
	    }

	    /**
	     * 关键字查询开户行
	     * 
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping("/queryBankNode")
	    public List<?> queryBankNode(String bankName, Integer page, Integer rows) {
	        return merchDetaService.queryBankNode(bankName, page,rows);
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
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping("/queryChnlnameAll")
	    public List<?> queryChnlnameAll() {
	        return merchDetaService.queryChnlnameAll();
	    }

	    /**
	     * 路由版本
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping("/queryRouteAll")
	    public List<?> queryRouteAll() {
	        return merchDetaService.queryRouteAll();
	    }

	    /**
	     * 风控版本
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping("/queryRiskType")
	    public List<?> queryRiskType(String vid) {
	        return merchDetaService.queryRiskType(vid);
	    }

	    /**
	     * 分润版本
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping("/querySplit")
	    public List<?> querySplit(String vid) {
	        return merchDetaService.querySplit(vid);
	    }

	    /**
	     * 扣率版本
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping("/queryFee")
	    public List<?> queryFee(String vid) {
	        return merchDetaService.queryFee(vid);
	    }

	    /**
	     * 清算周期
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping("/querySetlcycleAll")
	    public List<?> querySetlcycleAll() {
	        return merchDetaService.querySetlcycleAll();
	    }
	    
	    /**
	     * 产品类型
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping("/queryMccList")
	    public List<?> queryMccList(){
			return mccListService.findAll();
		}
	//*********************************商户信息变更*******************************************
	    /**
	     * 商户信息变更菜单
	     * @return
	     */
	    public String showMerchModify(String flag){
	        flag="4";
	        return "merch_modify_query";
	    }
	    /**
	     * 商户变更初审
	     */
	    public String merchModifyFirstCheck(String flag){
	        flag="5";
	        return "merch_modify_query";
	    }
	    /**
	     * 商户变更复审
	     */
	    public String merchModifySecondCheck(String flag){
	        flag="6";
	        return "merch_modify_query";
	    }
	    /**
	     * 商户信息变更界面
	     * @return
	     */
	    public String queryMerchModify(){
//	        Map<String, Object> variables = new HashMap<String, Object>();
//	        variables.put("userId", getCurrentUser().getUserId());
//	        if (merchDeta != null) {
//	            variables.put("merberId", merchDeta.getMember().getMemberId());
//	            variables.put("merchName", merchDeta.getMember().getMemberName());
//	        }
//	        variables.put("flag", flag);
//	        Map<String, Object> merchList = merchDetaService
//	                .findMerchModifyByPage(variables, getPage(), getRows());
//	        json_encode(merchList);
	        return null;
	    }
	    
	    /**
	     * 商户信息变更列表的变更功能
	     * @return
	     */
	    public String toMerchModifyEdit(){
//	        merchDeta = merchDetaService.getBean(
//	                Long.parseLong(merchApplyId));
//	        oldBankName = merchDetaService.queryBankName(
//	                merchDeta.getBankNode(), merchDeta.getBankCode());
//
//	        charge = merchDeta.getCharge().toString();
//	        deposit = merchDeta.getDeposit().toString();

	        return "merch_modify_edit";    
	    }
	    
	    /**
	     * 点击下一步，保存本页信息
	     * @return
	     */
	    public String toUploadModifyInfo(){
//	        merchDeta = merchDetaService.getBean(
//	                Long.parseLong(merchApplyId));
//	        if (merchDeta == null) {
//	        }

	        return "toUploadModifyInfo";
	    }
	    /**
	     * 
	     * 商户变更的提交申请功能
	     * @return
	     */
	    public String commitMerchModify(){
//	        Map<String, String> result = new HashMap<String, String>();
//	        IMerchDetaService merchDetaService = serviceContainer
//	                .getMerchDetaService();
//	        boolean isSucc = merchDetaService.commitMerchModify(Long
//	                .parseLong(merchApplyId));
//	        if (isSucc) {
//	            result.put("status", "OK");
//	        } else {
//	            result.put("status", "FAIL");
//	        }
//	        json_encode(result);
	        return null;
	    }
	    
	    /**
	     * 点击下一步，对商户变更信息做保存更新
	     * @return
	     */
	    public String saveMerchModifyDeta(){
//	        if (merchDeta.getMember().getIsDelegation() == null) {
//	            merchDeta.getMember().setIsDelegation(0L);
//	        }
//
//	        if (merchDeta.getMember().getIsDelegation() == null) {
//	            merchDeta.getMember().setIsDelegation(0L);
//	        }
//
//	        if (charge == null || charge.equals("")) {
//	            merchDeta.setCharge(Money.ZERO);
//	        } else {
//	            merchDeta.setCharge(Money.valueOf(new BigDecimal(charge)
//	                    .multiply(HUNDERED)));
//	        }
//
//	        if (deposit == null || deposit.equals("")) {
//	            merchDeta.setDeposit(Money.ZERO);
//	        } else {
//	            merchDeta.setDeposit(Money.valueOf(new BigDecimal(deposit)
//	                    .multiply(HUNDERED)));
//	        }
//
//	        List<?> resultlist = merchDetaService
//	                .saveMerchModifyDeta(Long.parseLong(merchApplyId), merchDeta);
//	        merchDeta.setmInUser(getCurrentUser().getUserId());
//	        json_encode(resultlist.get(0));
	        return null;
	        
	    }
	    /**
	     * 变更信息的审核（初审、复审）
	     * @return
	     */
	    
	    public String toMerchModifyDetail(){
//	        Long userId = getCurrentUser().getUserId();
//	        merchMap = merchDetaService.queryModifyMerchDeta(
//	                Long.parseLong(merchApplyId), userId);
	        return "merch_modify_detail";  
	    }
//	    public ServiceContainer getServiceContainer() {
//	        return serviceContainer;
//	    }

	 //------------------------------------企业审核和查询----------------------------------------------------   
	    /**
	     * 企业初审菜单
	     * @param serviceContainer
	     */
	    public String enterpriseFirstExam(String flag){  
	        flag="2";
	        return "enterprise_exam_query";       
	    }
	    
	    /***
	     * 企业复审菜单
	     * @param serviceContainer
	     */
	    public String enterpriseSecondExam(String flag){
	        flag="3";
	        return "enterprise_exam_query";
	    }
	    
	    /**
	     * 企业查询菜单
	     * @param serviceContainer
	     */
	    public String enterpriseQuery(String flag){
	        flag="10";
	        return "enterpriseQueryAll";
	    }
	    
	    /**
	     * 初审、复审的查询界面
	     * @param serviceContainer
	     */
	    public String queryEnterprise(){
//	        Map<String, Object> variables = new HashMap<String, Object>();
//	        variables.put("userId", getCurrentUser().getUserId());
//	        if (enterprise != null) {
//	            variables.put("enterpriseMemberId", enterprise.getEnterpriseMemberId());//会员编号
//	            variables.put("enterpriseName", enterprise.getEnterpriseName());//企业名称
//	            variables.put("enterpriseStatus", enterprise.getEnterpriseStatus());//状态
//	        }
//	        variables.put("flag", flag);
//	        Map<String, Object> enterpriseList = merchDetaService
//	                .findEnterpriseByPage(variables, getPage(), getRows());
//	        json_encode(enterpriseList);
	        return null;
	    }
	    /**
	     * 审核界面
	     * @param serviceContainer
	     */
	    public String toEnterpriseDetail(){
//	        Long userId = getCurrentUser().getUserId();
//	        enterpriseDeta = merchDetaService.queryEnterpriseExamDeta
//	                (Long.parseLong(enterpriseApplyId),userId);
	        return "enterpriseFirstExam"; 
	    }
	    
	    /**
	     * 企业查询功能
	     * @param serviceContainer
	     */
	    public String queryEnterpriseAll(){
//	        Map<String, Object> variables = new HashMap<String, Object>();
//	        variables.put("userId", getCurrentUser().getUserId());
//	        if (merchDeta != null) {
//	            variables.put("merberId", merchDeta.getMember().getMemberId());
//	            variables.put("merchName", merchDeta.getMember().getMemberName());
//	            variables.put("address",
//	                    ((Enterprise) merchDeta.getMember()).getAddress());
//	            variables.put("status", merchStatus);
//	            variables.put(
//	                    "coopInstiId",
//	                    merchDeta.getMember().getInstiCode() != null ? merchDeta
//	                            .getMember() : null);
//	        }
//
//	        variables.put("flag", flag);
//	        Map<String, Object> merchList = merchDetaService
//	                .findMerchByPage(variables, getPage(), getRows());
//	        json_encode(merchList);
	        return null;
	    }
	    /**
	     * 企业的审核（初审、复审）
	     * @return
	     * @throws IOException 
	     */
	    public String examEnterprise() throws IOException{
	        // 初审意见和复审意见，在页面中都是通过merchDate.stexaopt传过来的
//	        String stexopt = URLDecoder.decode(enterprise.getStexaOpt(), "utf-8");
//	        if (flag.equals("2")) {// 初审，需要记录初审人和初审意见
//	            enterprise.setStexaOpt(stexopt);
//	            enterprise.setStexaUser(getCurrentUser().getUserId());
//	        } else if (flag.equals("3")) {// 复审，需要记录复审人和复审意见
//	            enterprise.setCvlexaOpt(stexopt);
//	            enterprise.setCvlexaUser(getCurrentUser().getUserId());
//	        }        
//	        List<Map<String, Object>> resultlist = (List<Map<String, Object>>) serviceContainer
//	        .getMerchDetaService().enterpriseAudit(Long.parseLong(enterpriseApplyId),
//	              enterprise, flag, isAgree);
//	        json_encode(resultlist);
	        return null;
	    } 
	    
	    /**
	     * 加载证件照片等
	     * @param serviceContainer
	     */
	    
	    public String downloadEnterpriseImgUrl(){
//	        String webRootPath = ServletActionContext.getServletContext().getRealPath("/");
//	        String realpath = webRootPath + "/" + CommonUtil.DOWNLOAD_ROOTPATH;
//	        boolean fouce = (fouceDownload != null && fouceDownload.equals("fouce"));
//	        String filePath = serviceContainer.getFtpEnterpriseService().downloadEnterpriseFromFtp
//	                (Long.parseLong(enterpriseApplyId), realpath,CertType.format(certTypeCode), fouce);
//	        Map<String, String> result = new HashMap<String, String>();
//	        if (filePath == null) {
//	            result.put("status", "fail");
//	        } else if (filePath.equals("")) {
//	            result.put("status", "notExist");
//	        } else {
//	            result.put("status", "OK");
//	            result.put("url", filePath);
//	            new MerchantThread(webRootPath + "/" + filePath).start();
//	        }
//	        json_encode(result);
	        return null;
	    }
	}
