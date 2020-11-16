package kr.or.ddit.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.repository.MemberRepositoryI;

@Transactional
@Service("MemberService")
public class MemberService implements MemberServiceI {
	
	@Resource(name="MemberRepository")
	private MemberRepositoryI memberRepository;
	
	@Override
	public MemberVo loginMember(MemberVo memberVo) {
		return memberRepository.loginMember(memberVo);
	}
	
	@Override
	public MemberVo getMember(String userid) {
		return memberRepository.getMember(userid);
	}

	@Override
	public List<MemberVo> selectAllMember() {
		return memberRepository.selectAllMember();
	}
	
	@Override
	public Map<String, Object> selectMemberPageList(PageVo pageVo) {
		Map<String, Object> map = new HashMap<>();
		map.put("memberList", memberRepository.selectMemberPageList(pageVo));
		
		// 15건, 페이지 사이즈를 7로 가정했을 때 3개의 페이지가 나와야한다.
		// 15/7은 
		int totalCnt = memberRepository.selectMemberTotalCnt();
		int pages = (int)Math.ceil((double)totalCnt/pageVo.getPageSize());
		
		map.put("pages", pages);
		return map;
	}

	@Override
	public int insertMember(MemberVo memberVo) {
		return memberRepository.insertMember(memberVo);
	}

	@Override
	public int deleteMember(String userid) {
		return memberRepository.deleteMember(userid);
	}

	@Override
	public int updateMember(MemberVo memberVo) {
		return memberRepository.updateMember(memberVo);
	}

	
	

}
