package com.louis.mango.admin.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.louis.mango.admin.model.SysUser;
import com.louis.mango.admin.security.JwtAuthenticationToken;
import com.louis.mango.admin.service.ISysUserService;
import com.louis.mango.admin.utils.PasswordUtils;
import com.louis.mango.admin.utils.SecurityUtils;
import com.louis.mango.admin.vo.LoginBean;
import com.louis.mango.common.utils.IOUtils;
import com.louis.mango.core.http.HttpResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/***
 * 
 * @Description 系统登录相关API
 * @author lt
 *
 */
//@CrossOrigin(allowCredentials = "true")
@Api(tags = { "系统登录相关API" })
@RestController
public class SysLoginController {

	@Autowired
	private Producer producer;

	@Autowired
	private ISysUserService sysUserService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping(value = "Kaptcha.jpg")
	public void kaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Cache-Control", "no-stroe, no-cache");
		response.setContentType("image/jpeg");
		// 生成文字验证
		String text = producer.createText();
		// 生成图片验证
		BufferedImage image = producer.createImage(text);
		// 保存验证码到session
		request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);

		System.out.println("kaptc sessionID:" + request.getSession().getId());
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		IOUtils.closeQuietly(out);
	}

	@ApiOperation(value = "登录")
	@PostMapping(value = "/login")
	public HttpResult login(@RequestBody LoginBean loginBean, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				System.out.println("cookie:" + cookie.getName());
			}
		}

		String username = loginBean.getAccount();
		String password = loginBean.getPassword();
		String captcha = loginBean.getCaptcha();
		// 从session中获取之前保存的验证码跟前台传来的验证码进行匹配
		Object kaptcha = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		System.out.println("login sessionID:" + request.getSession().getId());
		if (kaptcha == null) {
			return HttpResult.error("验证码已失效！");
		}
		if (!kaptcha.equals(captcha)) {
			return HttpResult.error("验证码不正确!");
		}
		SysUser user = sysUserService.findByName(username);
		if (user == null) {
			return HttpResult.error("账号不存在");
		}
		if (!PasswordUtils.match(user.getSalt(), password, user.getPassword())) {
			return HttpResult.error("密码不正确");
		}
		if (user.getStatus() == 0) {
			return HttpResult.error("账号已被锁定，请联系管理员");
		}
		// 系统登录认证
		JwtAuthenticationToken token = SecurityUtils.login(request, username, password, authenticationManager);
		return HttpResult.ok(token);
	}
	
}
