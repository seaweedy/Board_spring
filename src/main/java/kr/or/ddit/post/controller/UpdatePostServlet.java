package kr.or.ddit.post.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.attachment.model.AttachmentVo;
import kr.or.ddit.attachment.service.AttachmentService;
import kr.or.ddit.attachment.service.AttachmentServiceI;
import kr.or.ddit.fileUpload.FileUploadUtil;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.service.PostService;
import kr.or.ddit.post.service.PostServiceI;

@WebServlet("/updatePost")
@MultipartConfig(maxFileSize = 1024 * 1024 * 100, maxRequestSize = 1024 * 1024 * 26)
public class UpdatePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(SelectPostServlet.class);
	private PostServiceI postService;
	private AttachmentServiceI attachmentService;
	
	@Override
	public void init() throws ServletException {
		postService = new PostService();
		attachmentService = new AttachmentService();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int post_seq = Integer.parseInt(request.getParameter("post_seq"));
		String userid = request.getParameter("userid");
		
		PostVo postVo = postService.selectPost(post_seq);
		// post정보 가져옴
		
		List<AttachmentVo> attachmentList = attachmentService.selectAttachmentList(post_seq);
		// attachment정보 가져옴
		
		request.setAttribute("postVo", postVo);
		request.setAttribute("attachmentList", attachmentList);
		request.setAttribute("userid", userid);
		
		//----------------------------------post관련---------------------------
		
		request.getRequestDispatcher("/updatePostForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String board_name = request.getParameter("board_name");
		String userid = request.getParameter("userid");
		String post_title = request.getParameter("post_title");
		String post_content = request.getParameter("post_content");
		int post_parent = Integer.parseInt(request.getParameter("post_parent"));
		int post_seq = Integer.parseInt(request.getParameter("post_seq"));
		
		
		//---------------------- 글 수정--------------------------
		
		PostVo postVo = new PostVo();
		postVo.setBoard_name(board_name);
		postVo.setPost_content(post_content);
		postVo.setPost_parent(post_parent);
		postVo.setPost_title(post_title);
		postVo.setUserid(userid);
		postVo.setPost_seq(post_seq);
		
		int updateCnt = postService.updatePost(postVo); // 글 등록
		
		//----------------------삭제 첨부파일 제거--------------------
		
		int delcnt = 0; // 삭제 첨부파일
		if(request.getParameter("delcnt").equals("")) {
			delcnt = 0; // 삭제 횟수 값
		}else {
			delcnt = Integer.parseInt(request.getParameter("delcnt")); // 삭제 횟수 값
		}
		
		int deleteCnt = 0;
		if(delcnt > 0) { // 삭제할게 생김
			//여기서부터!!!!!
			for(int i = 1; i <= delcnt; i++) { // 첨부파일제거 보낸 횟수만큼 진행
				int atc_seq = Integer.parseInt(request.getParameter("delfilename"+i));
				deleteCnt = attachmentService.deleteOneAttachment(atc_seq); // 첨부파일 삭제
			}
		}else { // 첨부파일을 보내지않음
			
		}
		
		//----------------------새롭게 등록한 첨부파일 등록--------------------
		
		int cnt = 0; // 새로운 첨부파일
		if(request.getParameter("cnt").equals("")) {
			cnt = 0; // 새로운 첨부파일 횟수 값
		}else {
			cnt = Integer.parseInt(request.getParameter("cnt")); // 새로운 첨부파일 횟수 값
			
		}
		
		String filepath = "";
		String realfilename = "";
		int insertatcCnt = 0;
		
		if(cnt > 0) { // 첨부파일을 하나 이상 보냄
			AttachmentVo attachmentVo;
			for(int i = 1; i <= cnt; i++) { // 첨부파일 보낸 횟수만큼 진행
				Part atch = request.getPart("filename"+i); // 첨부파일 가져오기	
				realfilename = FileUploadUtil.getFileName(atch.getHeader("Content-Disposition"));
				if(!realfilename.equals("")) {
					String extension = "." + FileUploadUtil.getExtension(realfilename); // 확장자 붙이기위한 작업
					String uuidfilename = UUID.randomUUID().toString() + extension; // 랜덤한 이름부여 atc_rfname
					filepath = "D:\\atch\\" + uuidfilename; // atc_fname
					atch.write(filepath);
					
					attachmentVo = new AttachmentVo();
					attachmentVo.setAtc_fname(filepath);
					attachmentVo.setAtc_rfname(uuidfilename);
					attachmentVo.setPost_seq(post_seq);
					
					insertatcCnt =  attachmentService.insertAttachment(attachmentVo); // 첨부파일 등록
				}
			}
		}else { // 첨부파일을 보내지않음
			realfilename ="";
		}
		
		if(updateCnt == 1) { // 글을 정상적으로 등록
			logger.debug("성공!!!!!!!!!!!!!!!!!!");
			response.sendRedirect("/selectPost?post_seq="+post_seq+"&userid="+userid);
		}else {
			response.sendRedirect("/selectPost?post_seq="+post_seq+"&userid="+userid);
			logger.debug("실패!!!!!!!!!!!!!!!!!!");
		}
		
	}

}
