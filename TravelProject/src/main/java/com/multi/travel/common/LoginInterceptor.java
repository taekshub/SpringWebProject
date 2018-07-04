package com.multi.travel.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor 
                  extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler)
					throws Exception {
		
		//url ��û�� ���� ���� preHandler�� �� �޾Ƽ� 
		//�α׿� ���ο� ����, �α׿� �������� ������ �ƴϸ�
		//�״�� ���������� �����Ѵ� 
		System.out.println("Interceptor : PreHandle");
		   
		//���ǿ� �α׿��� �Ͽ����� ���¸� �˾ƺ��� 
		HttpSession session = request.getSession();   
		String email = (String)session.getAttribute("email");
		
		// Login �� �ȵǾ��� ��� �α��� �������� �̵��Ѵ�
		if(email==null || email.equals("")) {
		    System.out.println("Interceptor : Session Check Fail");
		   			 
		    String url="/travel/member/login.do";
		    response.sendRedirect(url);
		    return false;//�ݵ�� false�� �����Ѵ� 
		}
		
		
	    
	   //�α��� ������ 	    
	   System.out.println("Interceptor : Session Check true");
	   return super.preHandle(request, response, handler);
	   //�� �θ�Ŭ������ preHandler�� ȣ���ؾ� �Ѵ� 
	}
}
