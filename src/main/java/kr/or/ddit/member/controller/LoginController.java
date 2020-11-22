package kr.or.ddit.member.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.scribejava.core.model.OAuth2AccessToken;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardServiceI;
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
	
	@Autowired
    private KakaoService kakaoService;
	
	/* NaverLoginBO */
	private NaverService naverService;
	private String apiResult = null;

	@Autowired
	private void setNaverService(NaverService naverService) {
		this.naverService = naverService;
	}
	
	// 네이버 로그인 첫 화면 요청 메소드
	@RequestMapping(value = "/naver", method = { RequestMethod.GET, RequestMethod.POST })
	public String naverLogin(Model model, HttpSession session) {
		logger.debug("MemberController.naverLogin()진입");
		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
		String naverAuthUrl = naverService.getAuthorizationUrl(session);
		// https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		// redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
		logger.debug("네이버:{}",  naverAuthUrl);
		// 네이버
		model.addAttribute("url", naverAuthUrl);
		return "redirect:/login/navercallback";
	}
	
	// 네이버 로그인 성공시 callback호출 메소드
	@RequestMapping(value = "/navercallback", method = { RequestMethod.GET, RequestMethod.POST })
	public String naverCallback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
			throws IOException, ParseException, org.json.simple.parser.ParseException {
		logger.debug("MemberController.naverCallback()진입");
		
		OAuth2AccessToken oauthToken;
		oauthToken = naverService.getAccessToken(session, code, state);
		// 1. 로그인 사용자 정보를 읽어온다.
		apiResult = naverService.getUserProfile(oauthToken); // String형식의 json데이터
		logger.debug("apiResult : {}", apiResult);
		/**
		 * apiResult json 구조 {"resultcode":"00", "message":"success",
		 * "response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"sh@naver.com","name":"\uc2e0\ubc94\ud638"}}
		 **/
		// 2. String형식인 apiResult를 json형태로 바꿈
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		// 3. 데이터 파싱
		// Top레벨 단계 _response 파싱
		JSONObject response_obj = (JSONObject) jsonObj.get("response");
		// response의 nickname값 파싱
		String nickname = (String) response_obj.get("nickname");
		logger.debug("nickname : {}", nickname);
		
		// 4.파싱 닉네임 세션으로 저장
		session.setAttribute("sessionId", nickname); // 세션 생성
		model.addAttribute("result", apiResult);
		return "main/main";
	}



	
	@RequestMapping("/kakao")
	public String kakaoLogin( @RequestParam(value = "code", required = false) String code,
			HttpSession session) {
		logger.debug("MemberController.kakao()진입");
		logger.debug("code : {}", code);
		
		JsonNode accessToken;
		// JsonNode트리형태로 토큰받아온다
		JsonNode jsonToken = kakaoService.getKakaoAccessToken(code);
		// 여러 json객체 중 access_token을 가져온다
		accessToken = jsonToken.get("access_token");
		logger.debug("=========================새로운==================");
		logger.debug("###access_Token####  : {}" , accessToken);
		
		JsonNode userInfo = kakaoService.getKakaoUserInfo(accessToken);
		logger.debug("userInfo : {}", userInfo);
		
		// Get id
		String id = userInfo.path("id").asText();
		String name = null;
		String email = null;
		
		// 유저정보 카카오에서 가져오기 Get properties
		JsonNode properties = userInfo.path("properties");
		JsonNode kakao_account = userInfo.path("kakao_account");
		logger.debug("properties : {}", properties);
		logger.debug("kakao_account : {}", kakao_account);
		
		name = properties.path("nickname").asText();
		email = kakao_account.path("email").asText();
		
		
		logger.debug("id : {}", id);
		logger.debug("name : {}", name);
		logger.debug("email : {}", email);
		
		session.setAttribute("S_MEMBER", userInfo);
		
		return ""; 
	}
	
	@RequestMapping("/view")
	public String loginView(Model model, HttpSession session) {
		logger.debug("MemberController.loginView()진입");
		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
		
		String naverAuthUrl = naverService.getAuthorizationUrl(session);
		//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		//redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
		logger.debug("네이버: {}" , naverAuthUrl);
		//네이버
		model.addAttribute("url", naverAuthUrl);
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
