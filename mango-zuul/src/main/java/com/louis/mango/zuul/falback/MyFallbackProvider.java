package com.louis.mango.zuul.falback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

/***
 * 
 * @Description 
 *	
 * @author lt
 *
 */
@Component
public class MyFallbackProvider implements FallbackProvider{

	/**
	 * 指定熔断器功能应用于哪些路由的服务
	 */
	@Override
	public String getRoute() {
		// 如果需要所有的路由服务都加熔断功能，此处返回 return "*";
		//return "mango-consumer";
		return "*";
	}

	/**
	 * 进入熔断器功能时执行的逻辑
	 */
	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		System.out.println("route:" + route);
		System.out.println("exception:" + cause.getMessage());
		
		return new ClientHttpResponse() {
			
			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.setContentType(MediaType.APPLICATION_JSON);
				return httpHeaders;
			}
			
			@Override
			public InputStream getBody() throws IOException {
				return new ByteArrayInputStream("Sorry, the service is unavailable now.".getBytes());
			}
			
			@Override
			public String getStatusText() throws IOException {
				return "ok";
			}
			
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}
			
			@Override
			public int getRawStatusCode() throws IOException {
				return 200;
			}
			
			@Override
			public void close() {
				// TODO Auto-generated method stub
			}
		};
	}

}
