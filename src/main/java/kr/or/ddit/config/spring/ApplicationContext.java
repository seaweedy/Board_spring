package kr.or.ddit.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import kr.or.ddit.mvc.view.ExcelDownloadView;
import kr.or.ddit.mvc.view.ProfileDownloadView;
import kr.or.ddit.mvc.view.ProfileImgView;

/*
	<context:component-scan base-package="kr.or.ddit" use-default-filters="false">
		<context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
		<context:include-filter type="annotation" expression="org.springframework.web.bind.annotation.ControllerAdvice"/>
	</context:component-scan>
 */
@Configuration
@ComponentScan(basePackages ={"kr.or.ddit"}, useDefaultFilters = false,
			includeFilters = {
					@Filter(
							type=FilterType.ANNOTATION,
							classes = {Controller.class,ControllerAdvice.class} )} )
// <mvc:annotation-driven/>
@EnableWebMvc

public class ApplicationContext extends WebMvcConfigurerAdapter{
	
	// <mvc:default-servlet-handler/> => class 상속(WebMvcConfigurerAdapter)
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	/*
	<bean id="profileImgView" class="kr.or.ddit.mvc.view.ProfileImgView"/>
	<bean id="profileDownloadView" class="kr.or.ddit.mvc.view.ProfileDownloadView"/>
	<bean id="jsonView" class="org.springframework.web.servlet.view.json.MappingJackson2JsonView"/>
	<bean id="excelView" class="kr.or.ddit.mvc.view.ExcelDownloadView"/>
	
	<bean class="org.springframework.web.servlet.view.BeanNameViewResolver">
		<property name="order" value="1"></property>
	</bean>
	 */
	
	@Bean
	public ProfileImgView profileImgView() {
		return new ProfileImgView();
	}
	
	@Bean
	public ProfileDownloadView profileDownloadView() {
		return new ProfileDownloadView();
	}
	
	@Bean
	public MappingJackson2JsonView jsonView() {
		return new MappingJackson2JsonView();
	}
	
	@Bean
	public ExcelDownloadView excelDownlaodView() {
		return new ExcelDownloadView();
	}
	
	
	/*
	<bean class="org.springframework.web.servlet.view.BeanNameViewResolver">
		<property name="order" value="1"></property>
	</bean> 
	 */
	@Bean
	public BeanNameViewResolver beanNameViewResolver() {
		BeanNameViewResolver beanNameViewResolver = 
				new BeanNameViewResolver();
		beanNameViewResolver.setOrder(1);
		return beanNameViewResolver;
	}
	
	/*
	 <!-- tiles view resolver 추가 
		1. tiles 설정 작업 ==> TilesConfigurer를 통해 설정
		2. tiles view resolver 등록-->
	<bean id="tilesConfiguer" class="org.springframework.web.servlet.view.tiles3.TilesConfigurer">
		<property name="definitions">
			<list>
				<value>classpath:kr/or/ddit/config/tiles/tiles-definition.xml</value>
			</list>
		</property>
	</bean> 
	 */
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesconfigurer = new TilesConfigurer();
		tilesconfigurer.setDefinitions("classpath:kr/or/ddit/config/tiles/tiles-definition.xml");
		return tilesconfigurer;
	}
	
	/*
	 <bean class="org.springframework.web.servlet.view.tiles3.TilesViewResolver">
		<property name="order" value="0"></property>
		<property name="viewClass" value="org.springframework.web.servlet.view.tiles3.TilesView"></property>
	</bean>
	 */
	
	@Bean
	public TilesViewResolver tilesViewResolver() {
		TilesViewResolver tilesViewResolver = new TilesViewResolver();
		tilesViewResolver.setOrder(0);
		tilesViewResolver.setViewClass(TilesView.class);
		return tilesViewResolver;
	}
	/*
	 <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<!-- prefix : 접두어, suffix : 접미어
			Controller가 리턴하는 문자열에 prefix, suffix를 적용하여 해당 경로의 파일로
			응답을 생성
			LoginController.getView() ==> "login/view"
			
				prefix           viewName      suffix
			"/WEB-INF/view/" + "login/view" + ".jsp"
			"/WEB-INF/view/login/view.jsp"
		  -->
		<property name="order" value="2"></property>
		<property name="prefix" value="/WEB-INF/views/"/>
		<property name="suffix" value=".jsp"/>
	</bean> 
	 */
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver internalResourceViewResolver =
				new InternalResourceViewResolver();
		internalResourceViewResolver.setOrder(2);
		internalResourceViewResolver.setPrefix("/WEB-INF/views/");
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;
	}
	
	/*
	 <!-- 파일 업로드 처리를 위한 multipartResolver 설정 -->
	<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver"></bean>
	 */
	@Bean
	public MultipartResolver multipartResolver() {
		MultipartResolver multipartResolver = 
				new CommonsMultipartResolver();
		return multipartResolver;
	}
	
	/*
	 <!-- 국제화 -->
	 <!-- localeResolver -->
	<bean id="localeResolver"
	class="org.springframework.web.servlet.i18n.SessionLocaleResolver"/> 
	 */
	@Bean
	public SessionLocaleResolver sessionLocaleResolver() {
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		return sessionLocaleResolver;
	}
	
	/*
	 <!-- locale 변경 감지 interceptor -->
	<mvc:interceptors>
		<mvc:interceptor>
			<mvc:mapping path="/**"/>
			<bean class="org.springframework.web.servlet.i18n.LocaleChangeInterceptor">
				<property name="paramName" value="lang"/>
			</bean>
		</mvc:interceptor>
	</mvc:interceptors> 
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeChangeInterceptor = 
				new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		registry.addInterceptor(localeChangeInterceptor).addPathPatterns("/**");
	}
			
	/*
	 <!-- 정적자원 매핑 처리
		1. url단축을 목적
		2. WEB-INF : 외부에서 접근불가능한 공간에 저장된 자원들 접근 매핑	 -->
		<!-- localhost/resources/style/.css -->
		<mvc:resources mapping="/resources/**" location="/WEB-INF/views/error/" /> 
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
			.addResourceLocations("/WEB-INF/views/error/");
		
		registry.addResourceHandler("/layout/**")
		.addResourceLocations("/WEB-INF/views/layout/");
		
		registry.addResourceHandler("/images/**")
		.addResourceLocations("/WEB-INF/images/");
		
		registry.addResourceHandler("/lay/**")
		.addResourceLocations("/WEB-INF/views/layout/");
		
		registry.addResourceHandler("/js/**")
		.addResourceLocations("/WEB-INF/js/");
		
		registry.addResourceHandler("/css/**")
		.addResourceLocations("/WEB-INF/css/");
	}
}
