package com.zcbspay.platform.manager.controller.merch;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zcbspay.platform.manager.merch.bean.CashBean;
import com.zcbspay.platform.manager.merch.bean.ProductBean;
import com.zcbspay.platform.manager.merch.service.ProductService;
import com.zcbspay.platform.manager.system.bean.UserBean;

@Controller
@RequestMapping("/product/")
public class ProductController {
	
	//@Autowired
	private ProductService productService;

	/*private ServiceContainer serviceContainer;
	private ProductModel productModel;
	private CashModel cashModel;
	private String pid;
    
	private List<ProductModel> groupList;
	private Map<String, Object> cashMap;*/
	private List checkboxList;
	/**
	 * 产品信息管理页面
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("show")
	public ModelAndView show(HttpServletRequest request) {
		ModelAndView result = new ModelAndView("/business/product_manager");
		return result;
	}

	//产品组合分页查询
	public String  queryProduct(){
		/*Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("userId", getCurrentUser().getUserId());
		if(productModel==null){
			productModel=new ProductModel();
		}
		variables.put("prdtver", productModel.getPrdtver());
		variables.put("prdtname", productModel.getPrdtname());
		Map<String, Object> groupList=serviceContainer.getProductService().findProductByPage(variables, getPage(), getRows());
		json_encode(groupList);*/
		return null;
	}
	//产品新增
	@ResponseBody
	@RequestMapping("addProduct")
	public String addProduct(ProductBean productBean,HttpServletRequest request){
	    String result = "";
        if (StringUtils.isEmpty(productBean.getPrdtname().trim())) {
            result = "产品名称不能为空";
        }
        UserBean loginUser = (UserBean) request.getSession().getAttribute("LOGIN_USER");
        productBean.setInuser(loginUser.getUserId());
        result =productService.AddOneProduct(productBean, checkboxList);
		//result=serviceContainer.getProductService().AddOneProduct(productModel, checkboxList);
		return result;
	}
	//业务类型
	 public String queryBusinessType() throws Exception {
        /*List<?> paralist2=(List<?>) serviceContainer.getProductService().queryBusinessType();
	    json_encode(paralist2);*/
        return null; 
	}
    //查询一条产品信息
	public String queryOneProduct(){
	    	/*if(pid!=null&&!pid.equals("")){
	    		List<?> product=serviceContainer.getProductService().findByProperty("prdtver", pid);
	    		if(product.size()>0){
	    			productModel=(ProductModel) product.get(0);
	    		}
                json_encode(productModel);
	    	}*/
	    	
	    	return null;
	 }
	 //查询业务类型，标记拥有的业务
	 public String queryProductCase(){
	    	/*if(pid!=null&&!pid.equals("")){
	    		List<?> caselist=serviceContainer.getProductService().queryProdCase(pid);
                try {
					json_encode(caselist);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}*/
	    	
	    	return null;
	 }
	//产品修改
	public String updateProduct(){
	    String result = "";
       /* if (productModel == null||StringUtil.isEmpty(productModel.getPrdtname().trim())||StringUtil.isEmpty(productModel.getPrdtver().trim())) {
            result = "产品名称不能为空";
            json_encode(result);
            return null;
        }
		productModel.setInuser(getCurrentUser().getUserId());
		String markString=serviceContainer.getProductService().updateProduct(productModel, checkboxList);
		json_encode(markString);*/
		return null;
	}
	//-----------------------------------------------收银台----------------------------------------------------------------
    public String showCash(){
		return "cash_manager";
    }
	//收银台分页查询
    @ResponseBody
    @RequestMapping("queryCash")
	public String  queryCash(){
		/*Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("userId", getCurrentUser().getUserId());
		if(cashModel==null){
			cashModel=new CashModel();
		}
		variables.put("cashver", cashModel.getCashver());
		variables.put("cashname", cashModel.getCashname());
		Map<String, Object> groupList=serviceContainer.getProductService().findCashByPage(variables, getPage(), getRows());
		json_encode(groupList);*/
		return null;
	}
	
	//收银台新增
    @ResponseBody
    @RequestMapping("addCash")
	public String addCash(){
		/*cashModel.setInuser(getCurrentUser().getUserId());
		String markString=serviceContainer.getProductService().AddOneCash(cashModel, checkboxList);
		json_encode(markString);*/
		return null;
	}
	//支付渠道
    @ResponseBody
    @RequestMapping("queryChnlType")
	public String queryChnlType() throws Exception {
	    /*List<?> paralist2=(List<?>) serviceContainer.getProductService().queryChnlType();
		json_encode(paralist2);*/
	    return null; 
   }
	 //查询业务类型，标记拥有的业务
    @ResponseBody
    @RequestMapping("queryChnlMark")
    public String queryChnlMark(){
	    /*if(pid!=null&&!pid.equals("")){
	    	List<?> caselist=serviceContainer.getProductService().queryCaseMark(pid);
             try {
				json_encode(caselist);
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	}*/	    	
	    return null;
	 }
    //查询一条收银台，显示到修改页面
    @ResponseBody
    @RequestMapping("queryOneCash")
   	public String queryOneCash(){
   	    	/*if(pid!=null&&!pid.equals("")){
   	    		cashMap=serviceContainer.getProductService().queryOneCase(pid);
                json_encode(cashMap);
   	    	}*/
   	    	return null;
   	 }
    //产品修改
   	@ResponseBody
   	@RequestMapping("updateCash")
  	public String updateCash(){
  		/*cashModel.setInuser(getCurrentUser().getUserId());
  		String markString=serviceContainer.getProductService().UpdateCash(cashModel, checkboxList);
  		json_encode(markString);*/
  		return null;
  	}
	/*public ServiceContainer getServiceContainer() {
		return serviceContainer;
	}
	public void setServiceContainer(ServiceContainer serviceContainer) {
		this.serviceContainer = serviceContainer;
	}

	@SuppressWarnings("rawtypes")
    public List getCheckboxList() {
		return checkboxList;
	}
	@SuppressWarnings("rawtypes")
    public void setCheckboxList(List checkboxList) {
		this.checkboxList = checkboxList;
	}
	public List<ProductModel> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<ProductModel> groupList) {
		this.groupList = groupList;
	}
	public ProductModel getProductModel() {
		return productModel;
	}
	public void setProductModel(ProductModel productModel) {
		this.productModel = productModel;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}

	public CashModel getCashModel() {
		return cashModel;
	}

	public void setCashModel(CashModel cashModel) {
		this.cashModel = cashModel;
	}

	public Map<String, Object> getCashMap() {
		return cashMap;
	}

	public void setCashMap(Map<String, Object> cashMap) {
		this.cashMap = cashMap;
	}
	*/

}
