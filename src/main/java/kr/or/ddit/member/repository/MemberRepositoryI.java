package kr.or.ddit.member.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.member.model.MemberVo;

public interface MemberRepositoryI {
	MemberVo getMember(String userid);
	
	List<MemberVo> selectAllMember();
	
	List<MemberVo> selectMemberPageList(PageVo pageVo);
	
	int selectMemberTotalCnt();
	
	int insertMember(MemberVo memberVo);
	
	int updateMember(MemberVo memberVo);
}
