package kr.or.ddit.post.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.attachment.model.AttachmentVo;
import kr.or.ddit.attachment.service.AttachmentServiceI;
import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.fileUpload.FileUploadUtil;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.service.PostServiceI;
import kr.or.ddit.reply.model.ReplyVo;
import kr.or.ddit.reply.service.ReplyServiceI;

@RequestMapping("/post")
@Controller
public class PostController {
	private static final Logger logger = LoggerFactory.getLogger(PostController.class);

	@Resource(name="PostService")
	private PostServiceI postService;
	
	@Resource(name="AttachmentService")
	private AttachmentServiceI attachmentService;
	
	@Resource(name="ReplyService")
	private ReplyServiceI replyService;
	
	
	@RequestMapping("/listView")
	public String allPostView(PageVo pageVo ,String board_name, Model model ) {
		logger.debug("PostController.allPostView()진입");
		logger.debug("board_name {} ",board_name);
		
		model.addAttribute("board_name", board_name);
//		model.addAttribute("pageVo", pageVo);
		
		return "post/postList";
	}
	
	@RequestMapping("/list")
	public String allPost(PageVo pageVo, Model model) {
		logger.debug("PostController.allPost()진입");
		logger.debug("board_name {} " , pageVo.getBoard_name());
		
		Map<String, Object> map = postService.selectPostPageList(pageVo);
	
		if((int)map.get("pages") == 0) {
			
		}
		model.addAttribute("postList", map.get("postList"));
		model.addAttribute("pages",	map.get("pages"));
		model.addAttribute("pageVo", pageVo);
		
		return  "post/listHTML";
	}
	@RequestMapping("/insertPostView")
	public String insertPostView(String board_name, String mem_id, Model model) {
		logger.debug("PostController.insertPostView()진입");
		
		model.addAttribute("board_name", board_name);
		model.addAttribute("mem_id", mem_id);
		
		return "post/insertPostForm";
	}
	
	@RequestMapping(path = "/insertPost", method = RequestMethod.POST )
	public String insertPost(PostVo postVo, MultipartHttpServletRequest request, RedirectAttributes ra) {
		logger.debug("PostController.insertPost()진입");
		
		logger.debug("제목 : {}",postVo.getPost_title());
		logger.debug("내용 : {}",postVo.getPost_content());
//		logger.debug("시퀀스 : {}",post_seq);
		logger.debug("보드 : {}",postVo.getBoard_name());
		logger.debug("작성자 : {}",postVo.getUserid());
		logger.debug("부모 글 : {}",postVo.getPost_parent());
		int insertCnt = postService.insertPost(postVo);
		
		int post_seq = postVo.getPost_seq(); 
		
		List<MultipartFile> fileList = request.getFiles("filename");

		String path = "d:\\atch\\";
		
		for (MultipartFile file : fileList) {
			logger.debug("껍데기 {}",file.getOriginalFilename());
			File uploadFile = new File(path + file.getOriginalFilename());
			
			String realfilename = file.getOriginalFilename(); // 원본 파일 명(realfilename)
			String extension = FileUploadUtil.getExtension(realfilename); // 확장자
			String uuidfilename = UUID.randomUUID().toString()+ "." + extension; // 랜덤한 이름부여 
			String filename = path + uuidfilename; // atc_rfname
			
			uploadFile = new File(filename);
			
				logger.debug("파일이름:'{}'", realfilename);
				AttachmentVo attachmentVo = new AttachmentVo();
				attachmentVo.setPost_seq(post_seq);
				attachmentVo.setAtc_fname(filename);
				attachmentVo.setAtc_rfname(uuidfilename);
				
			if(!"".equals(realfilename)) {
				try {
					file.transferTo(uploadFile);
					attachmentService.insertAttachment(attachmentVo);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		ra.addAttribute("board_name", postVo.getBoard_name());
		return "redirect:/post/listView";
	}
	
	@RequestMapping("/selectView")
	public String selectView(PostVo postVo, Model model) {
		logger.debug("PostController.selectView()진입");
		logger.debug("post_seq : {} ", postVo.getPost_seq());
		
		postVo = postService.selectPost(postVo.getPost_seq());
		List<AttachmentVo> attachmentList = attachmentService.selectAttachmentList(postVo.getPost_seq());
		List<ReplyVo> replyList = replyService.showReply(postVo.getPost_seq());
		
		model.addAttribute("postVo", postVo);
		model.addAttribute("attachmentList", attachmentList);
		model.addAttribute("replyList", replyList);
		
		return "post/selectPost";
	}
	
	@RequestMapping("/updateView")
	public String updateView(int post_seq, Model model) {
		logger.debug("PostController.selectView()진입");
		logger.debug("post_seq : {} ", post_seq);
		PostVo postVo = postService.selectPost(post_seq);
		
		List<AttachmentVo> attachmentList = attachmentService.selectAttachmentList(post_seq);
		
		model.addAttribute("postVo", postVo);
		model.addAttribute("attachmentList", attachmentList);
		
		
		return "post/updatePostForm";
	}
	
	@RequestMapping(path = "/updatePost",method = RequestMethod.POST )
	public String updatePost(PostVo postVo, int delcnt,
			Model model,MultipartHttpServletRequest request, String delfilename,
			RedirectAttributes ra) {
		logger.debug("PostController.updatePost()진입");
		//----------------------------------post관련---------------------------
		
		logger.debug("postVo확인 {}",postVo);
		
		int updateCnt = postService.updatePost(postVo); // 글 수정
		
		PostVo editPostVo = postService.selectPost(postVo.getPost_seq());
		
		//----------------------삭제 첨부파일 제거--------------------
		if(delcnt > 0) { // 삭제할게 생김
			//여기서부터!!!!!
			for(int i = 1; i <= delcnt; i++) { // 첨부파일제거 보낸 횟수만큼 진행
				int atc_seq = Integer.parseInt(delfilename);
				attachmentService.deleteOneAttachment(atc_seq); // 첨부파일 삭제
			}
		}
		//----------------------새롭게 등록한 첨부파일 등록--------------------
		
		List<MultipartFile> fileList = request.getFiles("filename");

		String path = "d:\\atch\\";
		
		for (MultipartFile file : fileList) {
			logger.debug("껍데기 {}",file.getOriginalFilename());
			File uploadFile = new File(path + file.getOriginalFilename());
			
			String realfilename = file.getOriginalFilename(); // 원본 파일 명(realfilename)
			String extension = FileUploadUtil.getExtension(realfilename); // 확장자
			String uuidfilename = UUID.randomUUID().toString()+ "." + extension; // 랜덤한 이름부여 
			String filename = path + uuidfilename; // atc_rfname
			
			uploadFile = new File(filename);
			
				logger.debug("파일이름:'{}'", realfilename);
				AttachmentVo attachmentVo = new AttachmentVo();
				attachmentVo.setPost_seq(postVo.getPost_seq());
				attachmentVo.setAtc_fname(filename);
				attachmentVo.setAtc_rfname(uuidfilename);
				
			if(!"".equals(realfilename)) {
				try {
					file.transferTo(uploadFile);
					attachmentService.insertAttachment(attachmentVo);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		ra.addAttribute("post_seq", editPostVo.getPost_seq()); 
		return "redirect:/post/selectView";
	}
	
	@RequestMapping("/delete")
	public String delete(PostVo postVo, Model model) {
		logger.debug("PostController.delete()진입");
		logger.debug("postVo : {}",postVo);
		
		postService.deletePost(postVo);
		
		model.addAttribute("board_name",postVo.getBoard_name());
		return "redirect:/post/listView";
	}
	
	@RequestMapping("/insertAnswerView")
	public String insertAnswerView(PostVo postVo, Model model) {
		logger.debug("PostController.insertAnswerView()진입");
		logger.debug("postVo : {}",postVo);
		
		return"post/insertAnswerPostForm";
	}
	
	@RequestMapping("/insertAnswerPost")
	public String insertAnswerPost(PostVo postVo, 
			MultipartHttpServletRequest request, RedirectAttributes ra) {
		logger.debug("PostController.insertAnswerView()진입");
		logger.debug("postVo : {}",postVo);
		int insertCnt = postService.insertAnswerPost(postVo);
		
		int post_seq = postVo.getPost_seq(); 
		
		logger.debug("post_seq : {}",post_seq);
		
		List<MultipartFile> fileList = request.getFiles("filename");

		String path = "d:\\atch\\";
		
		for (MultipartFile file : fileList) {
			logger.debug("껍데기 {}",file.getOriginalFilename());
			File uploadFile = new File(path + file.getOriginalFilename());
			
			String realfilename = file.getOriginalFilename(); // 원본 파일 명(realfilename)
			String extension = FileUploadUtil.getExtension(realfilename); // 확장자
			String uuidfilename = UUID.randomUUID().toString()+ "." + extension; // 랜덤한 이름부여 
			String filename = path + uuidfilename; // atc_rfname
			
			uploadFile = new File(filename);
			
			logger.debug("파일이름:'{}'", realfilename);
			AttachmentVo attachmentVo = new AttachmentVo();
			attachmentVo.setPost_seq(post_seq);
			attachmentVo.setAtc_fname(filename);
			attachmentVo.setAtc_rfname(uuidfilename);
				
			if (!"".equals(realfilename)) {
				try {
					file.transferTo(uploadFile);
					attachmentService.insertAttachment(attachmentVo);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		ra.addAttribute("board_name", postVo.getBoard_name());
		return "redirect:/post/listView";
	}
	
	@RequestMapping("/AtchDownloadView")
	public String atchDownlaod(int atc_seq, Model model) {
		// 응답으로 생성하려고 하는 것 : 이미지 파일을 읽어서 ouput stream 객체에 쓰는 것 => 이미지로 응답을 만들어냄
		
		AttachmentVo attachmentVo = attachmentService.selectAttachment(atc_seq);
		model.addAttribute("filename", attachmentVo.getAtc_fname());
		model.addAttribute("realfilename", attachmentVo.getAtc_rfname());
		
		return "profileDownloadView";
		
	}
}
