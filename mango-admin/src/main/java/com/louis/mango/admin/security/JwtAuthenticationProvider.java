package com.louis.mango.admin.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.louis.mango.admin.utils.PasswordEncoder;

/***
 * 
 * @Description 
 *	身份验证提供者
 * @author lt
 *
 */
public class JwtAuthenticationProvider extends DaoAuthenticationProvider{

	public JwtAuthenticationProvider(UserDetailsService userDetailsService) {
		setUserDetailsService(userDetailsService);
	}
	
	/***
	 * 额外的验证用户名和密码是否正确
	 * @param userDetails 根据用户名从数据库中获取用户信息，包括权限
	 * @param authentication 前端用户填写用户名和密码信息
	 */
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		if(authentication.getAuthorities() == null) {
			logger.debug("Authentication failed: no credentials provided");
			throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
		}
		String presentedPassword = authentication.getCredentials().toString();
		String salt = ((JwtUserDetails) userDetails).getSalt();
		// 覆盖密码验证逻辑
		if(!new PasswordEncoder(salt).matches(userDetails.getPassword(), presentedPassword)) {
			logger.debug("Authentication failed: password does not match stored value");
			throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
		}
	}
	
}
