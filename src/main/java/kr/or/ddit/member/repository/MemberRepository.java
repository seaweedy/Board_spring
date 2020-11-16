package kr.or.ddit.member.repository;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.member.model.MemberVo;

@Repository("MemberRepository")
public class MemberRepository implements MemberRepositoryI {
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	@Override
	public MemberVo loginMember(MemberVo memberVo) {
		return sqlSession.selectOne("member.loginMember",memberVo);
	}
	
	@Override
	public MemberVo getMember(String userid) {
		// DB에서 데이터를 조회하는 로직이 있어야하나
		// Controller기능에 집중 -> 하드코딩을 통해 dao, service는 간략하게 넘어간다.
		// Mock (가짜)

		/*
		 * MemberVo memberVo = new MemberVo(); memberVo.setUserId("brown"); // 하드코딩
		 * memberVo.setPassword("passBrown");
		 */

		// select
		// 한 건 : selectOne
		// 여러 건 : selectList

		MemberVo memberVo = sqlSession.selectOne("member.getMember", userid);
		return memberVo;

	}

	@Override
	public List<MemberVo> selectAllMember() {
		List<MemberVo> memberList = sqlSession.selectList("member.selectAllMember");
		return memberList;
	}
	
	public List<MemberVo> selectMemberPageList(PageVo pageVo) {
		return sqlSession.selectList("member.selectMemberPageList", pageVo);
	}

	@Override
	public int selectMemberTotalCnt() {
		return sqlSession.selectOne("member.selectMemberTotalCnt");
	}

	@Override
	public int insertMember(MemberVo memberVo) {
		int insertCnt = sqlSession.insert("member.insertMember",memberVo);
		return insertCnt;
	}

	@Override
	public int deleteMember(String userid) {
		int deleteCnt = sqlSession.delete("member.deleteMember",userid);
		return deleteCnt;
	}

	@Override
	public int updateMember(MemberVo memberVo) {
		int updateCnt = sqlSession.update("member.updateMember", memberVo);
		return updateCnt;
	}

}
