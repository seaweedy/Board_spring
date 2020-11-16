

package kr.or.ddit.db;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionPoolServlet extends HttpServlet{
	private static final Logger logger = LoggerFactory.getLogger(ConnectionPoolServlet.class);
	@Override
	public void init() throws ServletException {
		logger.debug("ConnectionPoolServlet init()");
		
		// ConnectionPoolServlet 초기화 될 때 ConnectionPool을 생성해서
		// application 영역에 저장
		// 다른 jsp, servlet에서는 application 영역을 통해 ConnectionPool을 접근
		
		// ConnectionPool 생성
		BasicDataSource bd = new BasicDataSource();
		bd.setDriverClassName("oracle.jdbc.driver.OracleDriver"); // Driver 주소  
		bd.setUrl("jdbc:oracle:thin:@localhost:1521/xe"); // 오라클 주소
		bd.setUsername("lmh"); // 오라클 서버의 아이디
		bd.setPassword("java"); // 오라클 서버의 비밀번호
		bd.setInitialSize(20); // ConnectionPool의 사이즈를 지정하주는 메서드
		
		ServletContext sc = getServletContext(); // 서블릿의 정보를 가져옴
		sc.setAttribute("bd", bd);
	}
}
