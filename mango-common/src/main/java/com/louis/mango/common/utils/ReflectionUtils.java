package com.louis.mango.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * @Description 
 *	反射相关辅助方法
 * @author lt
 * @date 2019年10月28日下午1:49:31
 */
public class ReflectionUtils {

	/***
	 * 
	 * @Description 
	 *	根据方法名调用指定对象的方法，返回方法查询的结果
	 * @param object 调用方法的对象
	 * @param method 调用对象的方法名称
	 * @param args 方法参数对象数组
	 * @return
	 */
	public static Object invoke(Object object, String methodName, Object... args) {
		Object result = null;// 执行方法返回的结果
		Class<? extends Object> clazz = object.getClass();
		Method queryMethod = getMethod(clazz, methodName, args);
		if(queryMethod != null) {
			try {
				result = queryMethod.invoke(object, args);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}else {
			try {
				throw new NoSuchMethodException(clazz.getName() + " 类中没有找到 " + methodName + "方法.");
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @Description 
	 *	根据方法名和参数对象查找方法
	 * @param clazz 
	 * @param methodName
	 * @param args
	 * @return
	 */
	public static Method getMethod(Class<?> clazz, String methodName, Object... args) {
		Method queryMethod = null;
		Method[] methods = clazz.getMethods();
		for (Method method: methods) {
			if(method.getName().equals(methodName)) {
				Class<?>[] parameterTypes = method.getExceptionTypes();
				if(parameterTypes.length == args.length) {
					boolean isSameMethod = true;
					for(int i = 0; i < parameterTypes.length; i++) {
						Object arg = args[i];
						if(arg == null) {
							arg = "";
						}
						if(!parameterTypes[i].equals(args[i])) {
							isSameMethod = false;
						}
					}
					if(isSameMethod) {
						queryMethod = method;
						break;
					}
				}
			}
		}
		return queryMethod;
	}
}
