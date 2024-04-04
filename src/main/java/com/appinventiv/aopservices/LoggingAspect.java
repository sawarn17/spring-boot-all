package com.appinventiv.aopservices;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect // it contains advice methods
@Component
public class LoggingAspect {
	
	@Autowired ObjectMapper mapper;

	Logger log = LoggerFactory.getLogger(LoggingAspect.class);

	// here we provide the package in terms 
	/*
	 * Here first start mentioned any return type
	 * then we have to mentioned root package ==com.appinventiv.controller
	 * then if we applied on class then pass the class name like UserController other wise * for all classes
	 * then we have to pass the method name where i want to applied them other wise we can metioned * for entire things
	 * (..): This is a wildcard that matches any number of parameters (zero or more) of any type.
	 * 
	 */
	@Pointcut(value = "execution(* com.appinventiv.controller.UserController.findAllUsers(..) )")
	public void myPointcut() {

	}

	@Around("myPointcut()")
	public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
		String methodName = pjp.getSignature().getName();
		String className = pjp.getTarget().getClass().toString();
		Object[] array = pjp.getArgs();
		log.info("method invoked " + className + " : " + methodName + "()" + "arguments : "
				+ mapper.writeValueAsString(array));
		Object object = pjp.proceed();
		log.info(className + " : " + methodName + "()" + "Response : " + mapper.writeValueAsString(object));
		return object;
	}
	
	@Before("execution(* com.appinventiv.controller.UserController.updateUserName(..))")
    public void logBefore() {
        log.info("Before method execution");
    } 
}
