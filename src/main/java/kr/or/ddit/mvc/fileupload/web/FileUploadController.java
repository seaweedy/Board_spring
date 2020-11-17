package kr.or.ddit.mvc.fileupload.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/fileupload")
@Controller
public class FileUploadController {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	// 화면에 대한 요청을 받는 메서드
	@RequestMapping("/view")
	public String View() {
		// prefix, sufix에 의해
		
		return "fileupload/fileupload";
	}
	
	// 파일 업로드 처리메서드
	@RequestMapping("/upload")
	public String upload(String userid, @RequestPart("file") MultipartFile file) {
		logger.debug("userid : {}",userid);
		logger.debug("name : {} / filename : {} / size : {}" ,file.getName(), file.getOriginalFilename(), file.getSize());
		
		File uploadFile = new File("d:\\upload\\" + file.getOriginalFilename());
		try {
			file.transferTo(uploadFile);
		} catch (IllegalStateException | IOException e) {
		}
		
		return "fileupload/fileupload";
	}
	
	
	// location/fileupload/view 요청시 화면 요청 처리 메서드
	// jsp로 응답생성
	// jsp에서는 파일을 선택할 수 있는 input태그 1개
	// userid 파라미터를 보낼 수 있는 input태그 1개
	// 전송을 담당하는 submit input 태그 1개를 작성
	// jsp : /WEB-INF/views/fileupload/fileupload.jsp
}
