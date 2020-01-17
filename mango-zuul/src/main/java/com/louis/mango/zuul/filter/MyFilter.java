package com.louis.mango.zuul.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/***
 * 
 * @Description 
 * 自定义filter
 *	覆盖run()方法逻辑，在转发请求前进行token认证，如果没有携带token，返回"there is no request token"提示。
 * @author lt
 *
 */
@Component
public class MyFilter extends ZuulFilter{

	@Override
	public boolean shouldFilter() {
		// 表示是否需要执行该filter。true表示执行，false表示不执行
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		// filter需要执行的具体操作
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		System.out.println("zuul sessionID:" + request.getSession().getId());
		String token = request.getParameter("token");
		System.out.println("token:" + token);
//		if(token == null) {
//			ctx.setSendZuulResponse(false);
//			ctx.setResponseStatusCode(401);
//			try {
//				ctx.getResponse().getWriter().write("there is no request token");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			return null;
//		}
		return null;
	}

	@Override
	public String filterType() {
		// filter类型。有pre、route、post、error四种
		return "pre";
	}

	@Override
	public int filterOrder() {
		// 定义filter的顺序。数字越小，表示顺序越高，越先执行
		return 0;
	}

	
}
