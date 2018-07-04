package com.multi.travel.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StopWatch;

import com.sun.media.jfxmedia.logging.Logger;

@Aspect
public class MyLogger {
	protected Log log=LogFactory.getLog(MyLogger.class);
	
	@Around("execution(* com.multi.myhome1..*(..))")
	public Object logPrint(ProceedingJoinPoint point)
	              throws Throwable{
		
		String type = 
			point.getSignature().getDeclaringTypeName();
		//Ŭ���� �̸��� Controller  �� �� ������
		String name="";
		String msg="";
		
		StopWatch sw = new StopWatch();
		
		long startTime = System.currentTimeMillis();
		//1234�и��ʸ�ŭ cpu�� ���´�. �ð������� ���
		//��Ȯ�� �ð��� �ƴϴ�. 
		//2�ʸ� ��ٷȴµ� �׶� �ٸ� ���μ����� cpu�� ���
		//���̸� ��� ����� �� �� �ִ�
		//Thread.sleep(2000);
		sw.start();
		if(type.indexOf("Controller")>-1)
		{
			name= "Controller :  " + type;
		}
		else if( type.indexOf("Service")>-1)
		{
			name= "Service :  " + type;
		}
		else if(type.indexOf("Dao")>-1)
		{
			name= "Dao :  " + type;
		}
		sw.stop();
		long endTime = System.currentTimeMillis();
		
		msg = String.format("�ɸ��ð� : %d", endTime-startTime);
		log.debug("****** TimeCount ********** " );
		log.debug(name + " : "  + sw.toString());
		
		return point.proceed();
	}
}


