package com.louis.mango.admin.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.louis.mango.admin.security.GrantedAuthorityImpl;
import com.louis.mango.admin.security.JwtAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/****
 * 
 * @Description 
 *	JWT工具类	
 * @author lt
 *
 */
public class JwtTokenUtils implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户名称
	 */
	private static final String USERNAME = Claims.SUBJECT;
	
	/***
	 * 创建时间
	 */
	private static final String CREATER = "creater";
	

	/**
	 * 权限列表
	 */
	private static final String AUTHORITIES = "authorities";
	
	
	/****
	 * 密钥
	 */
	private static final String SECRET = "secret123";
	
	/****
	 * 有效期12小时
	 */
	private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000;
	
	/***
	 * 
	 * @Description 
	 *	生成令牌
	 * @param authentication
	 * @return
	 */
	public static String generateToken(Authentication authentication) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put(USERNAME, SecurityUtils.getUsername(authentication));
		claims.put(CREATER, new Date());
		//claims.put(AUTHORITIES, authentication.getAuthorities());
		claims.put(AUTHORITIES, getAuthorities(authentication.getAuthorities()));
		return generateToken(claims);
	}
	
	public static String getAuthorities(Collection<? extends GrantedAuthority> col) {
		StringBuilder build = new StringBuilder();
		for (GrantedAuthority grantedAuthority : col) {
			build.append(",").append(grantedAuthority.getAuthority());
		}
		System.out.println(build.toString().substring(1));
		return build.toString().substring(1);
	}
	
	/***
	 * 
	 * @Description 
	 *	从数据声明 生成令牌
	 * @param claims
	 * @return
	 */
	public static String generateToken(Map<String, Object> claims) {
		Date expirationDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
	}
	
	/***
	 * 
	 * @Description 
	 *	从令牌中获取用户名
	 * @param token
	 * @return
	 */
	public static String getUsernameFromToken(String token) {
		String username;
		try {
			Claims claims = getClamisFormToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}
	
	
	/***
	 * 
	 * @Description 
	 *	根据请求获取登录认证信息
	 * @param request
	 * @return
	 */
	public static Authentication getAuthenticationFromToken(HttpServletRequest request) {
		Authentication authentication = null;
		// 获取请求携带的令牌
		String token = getToken(request);
		if(token != null) { // 请求令牌不能为空
			if(SecurityUtils.getAuthentication() == null) { // 上下文中Authentication为空
				Claims claims = getClamisFormToken(token);
				if(claims == null) {
					return null;
				}
				String userName = claims.getSubject();
				if(userName == null) {
					return null;
				}
				if(isTokenExpired(token)) {
					return null;
				}
				Object authors = claims.get(AUTHORITIES);
				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				if(authors != null) {
					authorities = Arrays.stream(authors.toString().split(",")).map(GrantedAuthorityImpl::new).collect(Collectors.toList());
//					String[] authorsArr = authors.toString().split(",");
//					for (String str: authorsArr) {
//						authorities.add(new GrantedAuthorityImpl(str));
//					}
				}
				authentication = new JwtAuthenticationToken(userName, null, authorities, token);
			} else {
				if(validateToken(token, SecurityUtils.getUsername())) {
					// 如果上下文中Authentication非空，且请求令牌合法，直接返回当前登录认证信息
					authentication = SecurityUtils.getAuthentication();
				}
			}
		}
		return authentication;
	}
	/**
	 * 
	 * @Description 
	 *	获取请求token
	 * @param request
	 * @return
	 */
	public static String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		String tokenHead = "Bearer ";
		if(token == null) {
			token = request.getHeader("token");
		} else if(token.contains(tokenHead)) {
			token = token.substring(tokenHead.length());
		}
		
		if("".equals(token)) {
			token = null;
		}
		return token;
	}
	
	/***
	 * 
	 * @Description 
	 *	刷新令牌
	 * @param token
	 * @return
	 */
	public static String refreshToken(String token) {
		String refreshedToken;
		try {
			Claims claims = getClamisFormToken(token);
			claims.setExpiration(new Date());
			refreshedToken = generateToken(claims);
		} catch (Exception e) {
			refreshedToken = null;
		}
		return refreshedToken;
	}
	
	/****
	 * 
	 * @Description 
	 *
	 * @param token
	 * @param username
	 * @return
	 */
	public static Boolean validateToken(String token, String username) {
		String userName = getUsernameFromToken(token);
		return (username.equals(userName)) && !isTokenExpired(token);
	}
	
	/***
	 * 
	 * @Description 
	 *	判断令牌是否过期
	 * @param token  令牌
	 * @return
	 */
	public static Boolean isTokenExpired(String token) {
		try {
			Claims claims = getClamisFormToken(token);
			Date expiration = claims.getExpiration();
			return expiration.before(new Date());
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 
	 * @Description 
	 *	从令牌中获取数据声明
	 * @param token
	 * @return
	 */
	public static Claims getClamisFormToken(String token) {
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		} 
		return claims;
	}
	
}
