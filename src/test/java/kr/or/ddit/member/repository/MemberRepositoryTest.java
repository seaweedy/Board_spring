package kr.or.ddit.member.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.ModelTestConfig;
import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.member.model.MemberVo;


public class MemberRepositoryTest extends ModelTestConfig {
	private static final Logger logger = LoggerFactory.getLogger(MemberRepositoryTest.class);

	@Resource(name="MemberRepository")
	private MemberRepositoryI memberRepository;
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	@Test
	public void getMemberTest() {
		/*** Given ***/
		String userId = "apeach";

		MemberVo answerMemberVo = new MemberVo();
		answerMemberVo.setUserid("apeach");
		answerMemberVo.setPass("apeachPass");

		/*** When ***/
		MemberVo memberVo = memberRepository.getMember(userId);

		/*** Then ***/
//		assertEquals("brown", memberVo.getUserId());
//		assertEquals("passBrown", memberVo.getPassword());

		assertEquals(answerMemberVo.getUserid(), memberVo.getUserid());
	}
	
	@Test
	public void selectAllMemberTest() {
		/***Given***/
		

		/***When***/
		List<MemberVo> memberList = memberRepository.selectAllMember();

		/***Then***/
		assertEquals(16, memberList.size());
//		assertTrue(memberList.size() == 16);
	}
	
	@Test
	public void selectMemberPageListTest() {
		/***Given***/
		PageVo pageVo = new PageVo();
		pageVo.setPage(1);
		pageVo.setPageSize(10);

		/***When***/
		memberRepository.selectMemberPageList(pageVo);

		/***Then***/
		logger.debug("selectMemberPageList count : {} ", (memberRepository.selectMemberPageList(pageVo)).size());
		assertEquals(10, (memberRepository.selectMemberPageList(pageVo)).size());
		
	}
	
	@Test
	public void selectMemberTotalCntTest() {
		/***Given***/
		
		/***When***/
		memberRepository.selectMemberTotalCnt();
		
		/***Then***/
		assertEquals(16, memberRepository.selectMemberTotalCnt());
		
	}
	
	@Test
	public void insertMemberTest() {
		/***Given***/
		MemberVo memberVo = new MemberVo("test", "testPass", "testNm", "testAlias", "testAddr1", "testAddr2", "1234", "", "");
		
		/***When***/
		int insertCnt = memberRepository.insertMember(memberVo);
		
		/***Then***/
		assertEquals(1, insertCnt);
		
	}
	
	@Test
	public void updateMemberTest() {
		/***Given***/
		MemberVo memberVo = new MemberVo("ddit", "dditPass", "dditNm", "dditeAlias", "dditAddr1", "dditAddr2", "4321", "", "");
		
		/***When***/
		int updateCnt = memberRepository.updateMember(memberVo);
		
		/***Then***/
		assertEquals(1, updateCnt);
		
	}

}














