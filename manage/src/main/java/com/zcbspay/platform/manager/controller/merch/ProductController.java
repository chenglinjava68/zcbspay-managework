package com.zcbspay.platform.manager.controller.merch;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcbspay.platform.manager.merch.bean.ProductBean;
import com.zcbspay.platform.manager.merch.service.ProductService;
import com.zcbspay.platform.manager.utils.JsonUtils;
import com.zcbspay.platform.manager.utils.UserHelper;


@Controller
@RequestMapping("/product/")
public class ProductController {

	@Autowired
	private ProductService productService;

	

	/**
	 * 产品信息管理页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("show")
	public String show(HttpServletRequest request) {
		return "/business/product_manager";
	}

	/**
	 * 产品组合分页查询
	 * 
	 * @return
	 */
	@ResponseBody

	@RequestMapping("addProduct")
	public String addProduct(){
		String result = "";
        /*if (productModel == null||StringUtil.isEmpty(productModel.getPrdtname().trim())) {
            result = "产品名称不能为空";
            json_encode(result);
            return null;
        }
		productModel.setInuser(getCurrentUser().getUserId());
		result=serviceContainer.getProductService().AddOneProduct(productModel, checkboxList);
		json_encode(result);*/
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
=======
	@RequestMapping("queryProduct")
	public Map<String, Object> queryProduct(HttpServletRequest request, ProductBean productBean, String page,
			String rows) {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("userId", UserHelper.getCurrentUser(request).getUserId());
		variables.put("prdtver", productBean.getPrdtver());
		variables.put("prdtname", productBean.getPrdtname());
		Map<String, Object> groupList = productService.findProductByPage(variables, Integer.valueOf(page),
				Integer.valueOf(rows));
>>>>>>> branch 'develop' of https://github.com/paytonggithub/zcbspay-managework.git
		return groupList;
	}

	/**
	 * 产品新增
	 * 
	 * @param productBean
	 * @param request
	 * @param checkboxList
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addProduct")
	public void addProduct(ProductBean productBean, HttpServletRequest request, HttpServletResponse response,
			String[] checkboxList) {
		String result = "";
		if (StringUtils.isEmpty(productBean.getPrdtname().trim())) {
			result = "产品名称不能为空";
		} else if (checkboxList == null || checkboxList.length == 0) {
			result = "请选择业务";
		} else {
			productBean.setInuser(UserHelper.getCurrentUser(request).getUserId());
			result = productService.AddOneProduct(productBean, Arrays.asList(checkboxList));
		}

		JsonUtils.json_encodeAndWrite(response, result);
	}

	/**
	 * 业务类型
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("queryBusinessType")
	public List<?> queryBusinessType() throws Exception {
		List<?> paralist = (List<?>) productService.queryBusinessType();
		return paralist;
	}

	/**
	 * 查询一条产品信息
	 */
	@ResponseBody
	@RequestMapping("queryOneProduct")
	public Map<String, Object> queryOneProduct(String pid) {
		Map<String, Object> product = null;
		if (pid != null && !pid.equals("")) {
			product = productService.findByPrdtver(pid);
		}
		return product;
	}

	// 查询业务类型，标记拥有的业务
	@ResponseBody
	@RequestMapping("queryProductCase")
	public List<?> queryProductCase(String pid, HttpServletResponse response) {
		List<?> caselist = null;
		if (pid != null && !pid.equals("")) {
			caselist = productService.queryProdCase(pid);
			return caselist;
		}
		return caselist;
	}

	// 产品修改
	@ResponseBody
	@RequestMapping("updateProduct")
	public void updateProduct(ProductBean productBean,HttpServletResponse response,HttpServletRequest request,String[] checkboxList) {
		String result = "";
		if (StringUtils.isEmpty(productBean.getPrdtname().trim())
				|| StringUtils.isEmpty(productBean.getPrdtver().trim())) {
			result = "产品名称不能为空";
		}
		productBean.setInuser(UserHelper.getCurrentUser(request).getUserId());
	    result = productService.updateProduct(productBean, Arrays.asList(checkboxList));
		JsonUtils.json_encodeAndWrite(response, result);
	}
	
	/**
	 * 查询所有产品
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryProductAll")
	public List<?> queryProductAll() {
		List<?> resultList = productService.findAll();
		return resultList;
	}
}
