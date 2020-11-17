package kr.or.ddit.member.service;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.ModelTestConfig;
import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.member.model.MemberVo;

public class MemberServiceTest extends ModelTestConfig {
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceTest.class);

	@Resource(name = "MemberService")
	private MemberServiceI memberService;
	
	
	@Test
	public void getMemberTest() {
		/*** Given ***/
		String userId = "brown";

		MemberVo answerMemberVo = new MemberVo();
		answerMemberVo.setUserid("brown");
		answerMemberVo.setPass("brownPass");

		/*** When ***/
		MemberVo memberVo = memberService.getMember(userId);

		/*** Then ***/
		assertEquals("brown", memberVo.getUserid());
		assertEquals(answerMemberVo.getUserid(), memberVo.getUserid());
	}
	
	@Test
	public void selectAllMemberTest() {
		/*** Given ***/
		
		/*** When ***/
		List<MemberVo> memberList = memberService.selectAllMember();
		
		/*** Then ***/
		assertEquals(16, memberList.size());
	}
	
	@Test
	public void selectMemberPageListTest() {
		/***Given***/
		PageVo pageVo = new PageVo();
		pageVo.setPage(1);
		pageVo.setPageSize(7);
		
		/***When***/
		Map<String, Object> map = memberService.selectMemberPageList(pageVo);
		List<MemberVo> memberPageList = (List<MemberVo>)map.get("memberList");

		// 생성해야할 page 수 확인
		int pages = (int)map.get("pages"); 
		
		/***Then***/
		assertEquals(7, memberPageList.size());
		assertEquals(3, pages);
		
	}
	
	@Test
	public void insertMember_SUCCESS_Test() {
		/***Given***/
		MemberVo memberVo = new MemberVo("temp", "dditpass", "대덕인재", "개발원", "", "", "", "", "");

		/***When***/
		int insertCnt = memberService.insertMember(memberVo);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	
//	@Test
//	public void insertMember_FAIL_Test() {
//		/***Given***/
//		MemberVo memberVo = new MemberVo("ddit", "dditpass", "대덕인재", "개발원", "", "", "", "", "");
//		
//		/***When***/
//		int insertCnt = memberService.insertMember(memberVo);
//		
//		/***Then***/
//		logger.debug("insertCnt : {} ", insertCnt);
//		assertEquals(0, insertCnt);
//	}
	
	@Test
	public void updateMemberTest() {
		/***Given***/
		MemberVo memberVo = new MemberVo("ddit", "updatepass", "update대덕인재", "update개발원", "", "", "", "", "");
		
		/***When***/
		int insertCnt = memberService.updateMember(memberVo);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}

}
