package kr.or.ddit.login;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceI;
import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceI;

@WebServlet("/login")
public class LoginServlet extends HttpServlet { // 로그인 서블렛 객체는 한 번 생성
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);
	private MemberServiceI memberService; // 고로 한 번 생성
	private BoardServiceI boardService;
       
	
	@Override
	public void init() throws ServletException {
		// service 객체 생성
		memberService = new MemberService();
		boardService = new BoardService();
		
	}
	
	// login 화면을 클라이언트에게 응답으로 생성
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("logInServlet doGet");
		logger.debug("UNT_CD parameter : {}", request.getParameter("UNT_CD"));
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	// login 화면에서 사용자가 보낸 아이디, 비밀번호를 사용하여 로그인 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		
		logger.debug("userid : {}, password : {}",userid, password);
		
		MemberVo memberVo = memberService.getMember(userid);
		
		List<BoardVo> activeBoardList = boardService.selectActiveBoard();
		
		logger.debug("액티브 보드!!{}",activeBoardList.size());
		
		// DB에 등록된 회원이 없는 경우 (로그인 페이지) / 비밀번호가 일치하지 않는 경우 (로그인 페이지)
		if(memberVo == null || !memberVo.getPass().equals(password) ) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		// 비밀번호가 일치하는 경우 (메인페이지 이동)
		else if(memberVo.getPass().equals(password)) {
			request.getSession().setAttribute("activeBoardList", activeBoardList);
			request.getSession().setAttribute("S_MEMBER", memberVo); // 세션을 들고와서 값을 설정
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}
	}
}
