package kr.or.ddit.config.spring;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import kr.or.ddit.mvc.interceptor.PerformanceCheckInterceptor;
import kr.or.ddit.mvc.interceptor.SessionCheckInterceptor;

// @Configuration
public class InterceptorContext extends WebMvcConfigurerAdapter{
	
	/*
	 <!-- interceptor 설정 -->
	<mvc:interceptors>
		<mvc:interceptor>
			<mvc:mapping path="/**"/>
			<bean class ="kr.or.ddit.mvc.interceptor.PerformanceCheckInterceptor"></bean>
		</mvc:interceptor>
		
		<mvc:interceptor>
			<mvc:mapping path="/**"/>
			<mvc:exclude-mapping path="/login/**"/>
			<mvc:exclude-mapping path="/js/**"/>
			<mvc:exclude-mapping path="/css/**"/>
			<mvc:exclude-mapping path="/resources/**"/>
			<bean class ="kr.or.ddit.mvc.interceptor.SessionCheckInterceptor"></bean>
		</mvc:interceptor>
	</mvc:interceptors> 
	  
	 */
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new PerformanceCheckInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(new SessionCheckInterceptor())
			.addPathPatterns("/**")
			.excludePathPatterns("/login/**","/js/**","/css/**","/resources/**");
	}
}
