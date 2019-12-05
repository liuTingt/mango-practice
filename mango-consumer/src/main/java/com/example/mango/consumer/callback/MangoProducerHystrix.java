package com.example.mango.consumer.callback;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mango.consumer.service.MangoProducerService;

/***
 * 
 * @Description 
 *	回调类，实现MangoProducerService接口，并实现对应的方法，返回调用失败后的信息
 * @author lt
 *
 */
@Component
public class MangoProducerHystrix implements MangoProducerService{

	@RequestMapping(value = "/hello")
	public String hello() {
		return "sorry, hello service call failed.";
	}

}
