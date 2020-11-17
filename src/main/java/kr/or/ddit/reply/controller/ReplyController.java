package kr.or.ddit.reply.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.reply.model.ReplyVo;
import kr.or.ddit.reply.service.ReplyServiceI;

@RequestMapping("/reply")
@Controller
public class ReplyController {
	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	@Resource(name="ReplyService")
	private ReplyServiceI replyService;
	
	@RequestMapping("/replyInsert")
	public String replyInsert(ReplyVo replyVo, Model model,
			RedirectAttributes ra) {
		logger.debug("Replycontroller.replyInsert()진입");
		
		replyService.insertReply(replyVo);
		
		ra.addAttribute("post_seq", replyVo.getPost_seq());
		
		return"redirect:/post/selectView";
	}
	
	@RequestMapping("/replyDelete")
	public String replyDelete(ReplyVo replyVo, Model model,
			RedirectAttributes ra) {
		logger.debug("Replycontroller.replyDelete()진입");
		
		replyService.deleteReply(replyVo.getReply_seq());
		
		ra.addAttribute("post_seq", replyVo.getPost_seq());
		
		return"redirect:/post/selectView";
	}
}
