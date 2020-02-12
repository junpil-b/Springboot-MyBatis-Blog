package com.cos.blog.aop.info;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// 에러 advice -> 의존성이 없다?

@Component
@Aspect
public class LogAdvice {
	
	private static final Logger log = LoggerFactory.getLogger(LogAdvice.class);
	
	@Around("execution(* com.cos.blog.controller..*Controller.*(..))"
			+" or execution(* com.cos.blog.service..*Service.*(..))"
			+" or execution(* com.cos.blog.repository..*Repository.*(..))")
// @Around는 proceedingJoinPoint을 찾을 수 있다?
//	인자가 필요할 때 @Around 사용
// http://closer27.github.io/backend/2017/08/03/spring-aop/ -> aop 표현식 참조
	
	public Object logPrint(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
// before와 after는  proceedingJoinPoint를 찾을 수 없다 

		String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
// 자신의 메서드 타입을 알려준다?
		
		log.info(type+"."+proceedingJoinPoint.getSignature().getName()+"() <=================");
		log.info("Argument/Parameter : "+Arrays.toString(proceedingJoinPoint.getArgs()));
// Arrays.toString -> 문자열을 배열처럼 사용할 수 있다
		
		log.info("=================>");
// log.info는 콘솔에서만 출력된다
		
		return proceedingJoinPoint.proceed();
	}
	
}
