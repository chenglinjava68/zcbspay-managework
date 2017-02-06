package com.zcbspay.platform.manager.controller.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zcbspay.platform.manager.system.bean.UserBean;
import com.zcbspay.platform.manager.system.service.FunctionService;
import com.zcbspay.platform.manager.system.service.UserService;
import com.zcbspay.platform.manager.utils.MD5Util;

@Controller
@RequestMapping("/login")
@SuppressWarnings("all")
public class LoginController {

	private static final long serialVersionUID = 453655250496268464L;
//	private ServiceContainer serviceContainer;
    private List<?> funlist;
    private String loginName;
    private String randcode;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private Map<String, Object> session;
    private int pwdFlag;// 密码有效期过期标示 1-过期 0-未过期
    private int pwdDay;// 密码到期时间，5天时开始提示
	    
	@Autowired
	private UserService userService;
	@Autowired
	private FunctionService functionService;

	/**
	 * 验证用户登录信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/validateUser")
	public Object validateUser(UserBean user,HttpServletRequest request,String randcode) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String rand = ""; 
		HttpSession session = request.getSession(true);
		if (session.getAttribute("randCheckCode") == null) {
			rand = "";
		} else {
			rand = session.getAttribute("randCheckCode").toString();
		}
		if (!randcode.equals(rand)) {
			returnMap.put("ret", "err_rand");
			returnMap.put("info", "验证码错误！");
			return returnMap;
		}

		String passwordMark = "w5y1j5z1s1l1z6z0y8z1m1l0c5r5y3z4";
		passwordMark = passwordMark + user.getPwd();

		UserBean userBean = new UserBean();
		userBean.setLoginName(user.getLoginName());
		userBean.setStatus("00");
 		userBean.setPwd(MD5Util.MD5(passwordMark));
		UserBean loginUser = userService.getLoginUser(userBean);
		
		boolean loginFlag = false;
		if (loginUser != null) {
			if (loginUser.getLoginName().equals(user.getLoginName())
					&& loginUser.getPwd().equals(MD5Util.MD5(passwordMark))) {

//				session.put("LOGIN_USER", loginUser);
				returnMap.put("ret", "success");
			} else {
				loginFlag = true;
			}
		} else {
			loginFlag = true;
		}
		if (loginFlag) {
			returnMap.put("ret", "err_user");
			returnMap.put("info", "用户名或密码错误！");
		}else{
			request.getSession().setAttribute("LOGIN_USER", loginUser);
		}
		return returnMap;
	}

	@ResponseBody
    @RequestMapping("/loginSuccess")
    public ModelAndView createUserTo(UserBean user, HttpServletRequest request) {
        ModelAndView result=new ModelAndView("/index");
        UserBean loginUser = (UserBean) request.getSession().getAttribute("LOGIN_USER");
        if (loginUser.getLoginName().equals("admin")) {
            funlist = functionService.findFunction();
        } else {
            funlist = functionService.findLoginFuntion(loginUser);
        }
        checkPWDDate();
        pwdDay = calcExpirationDay();
       
        result.addObject("loginName",loginUser.getLoginName());
        result.addObject("funlist",funlist);
        result.addObject("pwdDay",pwdDay);
        
        return result;
    }
	
	// 操作栏
//	public String querymenu() throws Exception {
//		  UserBean loginUser = (UserBean) request.getSession().getAttribute("LOGIN_USER");
//	        if (loginUser.getLoginName().equals("admin")) {
//	            funlist = serviceContainer.getFunctionService().findFunction();
//	        } else {
//	            funlist = serviceContainer.getFunctionService().findLoginFuntion(
//	                    getCurrentUser());
//	        }
//	        session.put("Authority", funlist);
//	        checkPWDDate();
//	        pwdDay = calcExpirationDay();
//	        
//		return "";
//	}

	/**
	 * 检查密码有效期
	 */
	private void checkPWDDate() {

	}

	/**
	 * 计算时间间隔
	 * 
	 * @return
	 */
	private int calcExpirationDay() {
		return 0;
	}

	/**
	 * 用户登出
	 * 
	 * @return
	 */
	public String logout() {

		return "logout";
	}

	/**
	 * 生成图片验证码
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/validateCode")
	public void validateCode(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 禁止缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		// 指定生成的响应是图片
		response.setContentType("image/jpeg");
		int width = 65, height = 40;
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics g = image.getGraphics();
        // 生成随机类
        Random random = new Random();
        // 设定背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        // 设定字体
        g.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        // 取随机产生的认证码(4位数字)
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            // 将认证码显示到图象中
            g.setColor(new Color(20 + random.nextInt(110), 20 + random
                    .nextInt(110), 20 + random.nextInt(110)));
            // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(rand, 13 * i + 6, 26);
        }
        // 将认证码存入SESSION
     // 将生成的验证码保存到Session中
     		HttpSession session = request.getSession(true);
     		session.setAttribute("randCheckCode", sRand);
        // ActionContext.getContext().getSession().put("rand",sRand);
        // 图象生效
        g.dispose();
        ImageIO.write(image, "JPEG", response.getOutputStream());
	}

	/*
	 * 给定范围获得随机颜色
	 */
	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("http_client_ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		// 如果是多级代理，那么取第一个ip为客户ip
		if (ip != null && ip.indexOf(",") != -1) {
			ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
		}
		return ip;
	}
}
