package kr.or.ddit.member.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardServiceI;
import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.service.MemberServiceI;

@RequestMapping("/login")
@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource(name="MemberService")
	private MemberServiceI memberService;

	@Resource(name="BoardService")
	private BoardServiceI boardService;
	
	@RequestMapping("/view")
	public String loginView() {
		logger.debug("MemberController.loginView()진입");
		return "login/login";
	}
	
	@RequestMapping("/process")
	public String login(MemberVo memberVo,HttpSession session, Model model) {
		logger.debug("MemberController.login()진입");
		logger.debug("memberVo.getMem_id : {} " , memberVo.getUserid());
		logger.debug("memberVo.getMem_pass : {} " , memberVo.getPass());
		
		List<BoardVo> activeBoarList = boardService.selectActiveBoard();
		logger.debug("activeBoarList : {} " , activeBoarList);
		
		MemberVo loginVo = memberService.getMember(memberVo.getUserid());
		logger.debug("loginVo : {}", loginVo);
		if(loginVo != null && memberVo.getPass().equals(loginVo.getPass())) {
			// prefix : /WEB-INF/views/
			// surfix : .jsp
			session.setAttribute("S_MEMBER", loginVo);
			session.setAttribute("activeBoardList", activeBoarList);
			
			return "main/main"; 
		}else {
			model.addAttribute("msg","fail");
//			return "redirect:view.do";
			return "login/login";
		}
	}
	
	
	
}
