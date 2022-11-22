package kr.gjai.hwabun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.gjai.hwabun.entity.MemberDTO;

@Mapper
public interface TestMapper {
	public List<MemberDTO> getList();
}
