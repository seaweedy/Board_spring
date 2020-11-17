package kr.or.ddit.member.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.InputStream;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import kr.or.ddit.WebTestConfig;


public class MemberControllerTest extends WebTestConfig{

	@Test
	public void selectMemberPageListTest() throws Exception {
		mockMvc.perform(get("/member/listPage"))
			.andExpect(model().attribute("page", 1))
			.andExpect(model().attribute("pageSize", 5));
	}
	
	@Test
	public void getMemberTest() throws Exception {
		mockMvc.perform(get("/member/get")
				.param("userid","brown"))
		.andExpect(view().name("tiles/member/member"));
	}
	
	@Test
	public void insertScuccessMemberViewTest() throws Exception {
		InputStream is = getClass().getResourceAsStream("/kr/or/ddit/upload/sally.png");
		MockMultipartFile file = new MockMultipartFile("file", "sally.png", "image/png", is);
		
		mockMvc.perform(fileUpload("/member/regist")
				.file(file)
				.param("userid", "test")
				.param("usernm","test")
				.param("pass","testpass")
				.param("alias","test")
				.param("addr1","test")
				.param("addr2","test")
				.param("zipcode","12345"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/member/listPage"))
		.andDo(print()) ;
	}
	@Test
	public void viewProfileTest() throws Exception {
		mockMvc.perform(get("/member/profile")
				.param("userid","brown"));
	}
	
	@Test
	public void updateMemberTest() throws Exception {
		InputStream is = getClass().getResourceAsStream("/kr/or/ddit/upload/sally.png");
		MockMultipartFile file = new MockMultipartFile("file", "sally.png", "image/png", is);
		
		mockMvc.perform(fileUpload("/member/update")
				.file(file)
				.param("userid", "test")
				.param("usernm","test")
				.param("pass","testpass")
				.param("alias","test")
				.param("addr1","test")
				.param("addr2","test")
				.param("zipcode","12345"))
		.andExpect(status().isOk())
		.andExpect(view().name("tiles/member/member"));
	}

}
