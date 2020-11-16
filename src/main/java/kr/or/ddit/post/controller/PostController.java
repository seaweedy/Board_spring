package kr.or.ddit.post.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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

@RequestMapping("/post")
@Controller
public class PostController {
	private static final Logger logger = LoggerFactory.getLogger(PostController.class);

	@Resource(name="PostService")
	private PostServiceI postService;
	
	@Resource(name="AttachmentService")
	private AttachmentServiceI attachmentService;
	
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
		model.addAttribute("postVo", postVo);
		model.addAttribute("attachmentList", attachmentList);
		
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
	
//	@RequestMapping("/deletePost")
//	public String deletePost(int post_seq,int delCnt, Model model) {
//		
//		PostVo postVo = postService.selectPost(post_seq);
//		// post정보 가져옴
//		
//		List<AttachmentVo> attachmentList = attachmentService.selectAttachmentList(post_seq);
//		// attachment정보 가져옴
//		
//		//----------------------------------post관련---------------------------
//		
//		PostVo updatePostVo = new PostVo();
//		updatePostVo.setPost_content(postVo.getPost_content());
//		updatePostVo.setPost_title(postVo.getPost_title());
//		updatePostVo.setPost_seq(postVo.getPost_seq());
//		
//		int updateCnt = postService.updatePost(updatePostVo); // 글 등록
//		
//		//----------------------삭제 첨부파일 제거--------------------
//		
//		if(delCnt==0) {
//			delCnt = 0; // 삭제 횟수 값
//		}else {
//			delcnt = Integer.parseInt(request.getParameter("delcnt")); // 삭제 횟수 값
//		}
//		
//		int deleteCnt = 0;
//		if(delcnt > 0) { // 삭제할게 생김
//			//여기서부터!!!!!
//			for(int i = 1; i <= delcnt; i++) { // 첨부파일제거 보낸 횟수만큼 진행
//				int atc_seq = Integer.parseInt(request.getParameter("delfilename"+i));
//				deleteCnt = attachmentService.deleteOneAttachment(atc_seq); // 첨부파일 삭제
//			}
//		}else { // 첨부파일을 보내지않음
//			
//		}
//		
//		//----------------------새롭게 등록한 첨부파일 등록--------------------
//		
//		int cnt = 0; // 새로운 첨부파일
//		if(request.getParameter("cnt").equals("")) {
//			cnt = 0; // 새로운 첨부파일 횟수 값
//		}else {
//			cnt = Integer.parseInt(request.getParameter("cnt")); // 새로운 첨부파일 횟수 값
//			
//		}
//		
//		String filepath = "";
//		String realfilename = "";
//		int insertatcCnt = 0;
//		
//		if(cnt > 0) { // 첨부파일을 하나 이상 보냄
//			AttachmentVo attachmentVo;
//			for(int i = 1; i <= cnt; i++) { // 첨부파일 보낸 횟수만큼 진행
//				Part atch = request.getPart("filename"+i); // 첨부파일 가져오기	
//				realfilename = FileUploadUtil.getFileName(atch.getHeader("Content-Disposition"));
//				if(!realfilename.equals("")) {
//					String extension = "." + FileUploadUtil.getExtension(realfilename); // 확장자 붙이기위한 작업
//					String uuidfilename = UUID.randomUUID().toString() + extension; // 랜덤한 이름부여 atc_rfname
//					filepath = "D:\\atch\\" + uuidfilename; // atc_fname
//					atch.write(filepath);
//					
//					attachmentVo = new AttachmentVo();
//					attachmentVo.setAtc_fname(filepath);
//					attachmentVo.setAtc_rfname(uuidfilename);
//					attachmentVo.setPost_seq(post_seq);
//					
//					insertatcCnt =  attachmentService.insertAttachment(attachmentVo); // 첨부파일 등록
//				}
//			}
//		}else { // 첨부파일을 보내지않음
//			realfilename ="";
//		}
//		
//		if(updateCnt == 1) { // 글을 정상적으로 등록
//			logger.debug("성공!!!!!!!!!!!!!!!!!!");
//			response.sendRedirect("/selectPost?post_seq="+post_seq+"&userid="+userid);
//		}else {
//			response.sendRedirect("/selectPost?post_seq="+post_seq+"&userid="+userid);
//			logger.debug("실패!!!!!!!!!!!!!!!!!!");
//		}
//	}
}
