package kr.gjai.hwabun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.gjai.hwabun.entity.MemberDTO;
import kr.gjai.hwabun.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberMapper memberMapper;
	
		
	@Override 
	public void register(MemberDTO mdo) {
	  
		memberMapper.register(mdo); 
	  
	  }
	
	// id, pw로 회원 정보 가져오는 메소드  
	@Override
	public MemberDTO getMemInfo(MemberDTO mdo) {
		
		MemberDTO mvo = memberMapper.getMemInfo(mdo);
	  	return mvo; 
	  	
	}
	
	
	@Override
	public String idCheck(String mb_id) {
		MemberDTO mvo = memberMapper.idCheck(mb_id);
		
		if(mvo!=null) {
			return"retry";
	}else {
		return "null";
		}
		
	}

}
