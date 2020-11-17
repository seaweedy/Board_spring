package kr.or.ddit.member.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.service.MemberServiceI;

@RequestMapping("/member")
@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Resource(name="MemberService")
	private MemberServiceI memberService;
	
	@RequestMapping("/main")
	public String main() {
		logger.debug("main진입");
		return "main/main";
	}
	
	@RequestMapping("/list")
	public String listAjax(PageVo pageVo, Model model) {
		logger.debug("pageVo : {}", pageVo);
		
		Map<String, Object> map = memberService.selectMemberPageList(pageVo);
		
		model.addAttribute("memberList", map.get("memberList"));
		model.addAttribute("pages", map.get("pages"));
		logger.debug("pages : {}", map.get("pages"));
		return "jsonView"; 
	}
	
	@RequestMapping("/listHTML")
	public String listHTML(PageVo pageVo, Model model) {
		logger.debug("MemberController.listHTML()진입");
		logger.debug("pageVo : {}", pageVo);
		
		Map<String, Object> map = memberService.selectMemberPageList(pageVo);
		
		model.addAttribute("memberList", map.get("memberList"));
		model.addAttribute("pages", map.get("pages"));
		logger.debug("pages : {}", map.get("pages"));
		
		// 응답을 html ==> jsp로 생성
		return "member/listHTML"; 
	}
	
	@RequestMapping("/listPage")
	public String listPage() {
		logger.debug("MemberController.listPage()진입");
		
		return "tiles/member/listPage";
	}
	
	@RequestMapping("/get")
	public String getMember(String userid, Model model) {
		model.addAttribute("memberVo",memberService.getMember(userid));
//		return "member/member";
		return "tiles/member/member";
	}
	
	@RequestMapping("/getAjax")
	public String getMemberAjax(String userid, Model model) {
		model.addAttribute("memberVo",memberService.getMember(userid));
//		return "member/member";
		return "tiles/member/memberAjax";
	}
	
	@RequestMapping("/getAjaxHTML")
	public String getMemberHTML(String userid, Model model) {
		model.addAttribute("memberVo",memberService.getMember(userid));
//		return "member/member";
		return "member/memberAjax";
	}
	
	@RequestMapping("/registform")
	public String insertMemberView() {
		return "tiles/member/insertMemberFormContent";
	}
	
	@RequestMapping("/regist")
	public String insertMember(Model model,@Valid MemberVo memberVo, BindingResult br,
			@RequestParam(name="file",required = false) MultipartFile file,
			RedirectAttributes ra) {
		logger.debug("MemberController.insertMember()진입");
		
		// 검증을 통과하지 못했으므로 사용자 등록 화면으로 이동
		if(br.hasErrors()) {
			return "tiles/member/insertMemberFormContent";
		}
		
		logger.debug("memberVo : {} ",memberVo);
		logger.debug("file : {}" , file.getOriginalFilename());
		
		List<MemberVo> memberList = memberService.selectAllMember();
		
		for (MemberVo member : memberList) {
			if(memberVo.getUserid().equals(member.getUserid())) { // 중복된 아이디 입력
				return "tiles/member/insertMemberFormContent"; // 실패했을 때
			}
		}
		
		if(file.getSize() > 0) {
			// 확장자 추출
			int index = file.getOriginalFilename().lastIndexOf(".");
			String extension = file.getOriginalFilename().substring(index + 1); // jpg
			
			// 프로필파일 vo 등록
			memberVo.setRealfilename(file.getOriginalFilename());
			String uploadFileName = UUID.randomUUID().toString() + "." + extension;
			memberVo.setFilename("d:\\profile\\" +uploadFileName);
			
			// 파일 업로드
			File uploadFile = new File("d:\\profile\\" + uploadFileName);
			try {
				file.transferTo(uploadFile); // 업로드하는 메서드
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			
			// 회원등록
			memberService.insertMember(memberVo);
		}else {
			logger.debug("프로필 없이 등록: {},{}" , memberVo.getFilename(), memberVo.getRealfilename());
			memberService.insertMember(memberVo);
		}
		
		return "redirect:/member/listPage";
	}
	
	@RequestMapping("/profileImgView")
	public String profile(String userid,Model model) throws IOException {
		// 응답으로 생성하려고 하는 것 : 이미지 파일을 읽어서 ouput stream 객체에 쓰는 것 => 이미지로 응답을 만들어냄
		
		MemberVo memberVo = memberService.getMember(userid);
		model.addAttribute("filepath",memberVo.getFilename());
		
		return "profileImgView";
		
	}
	
	@RequestMapping("/profileDownloadView")
	public String profileDownlaod(String userid, Model model) {
		// 응답으로 생성하려고 하는 것 : 이미지 파일을 읽어서 ouput stream 객체에 쓰는 것 => 이미지로 응답을 만들어냄

		MemberVo memberVo = memberService.getMember(userid);
		model.addAttribute("filename", memberVo.getFilename());
		model.addAttribute("realfilename", memberVo.getRealfilename());

		return "profileDownloadView";

	}
	
}
