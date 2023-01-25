package kr.gjai.hwabun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.gjai.hwabun.entity.CosmeticsDTO;
import kr.gjai.hwabun.entity.MemberDTO;
import kr.gjai.hwabun.entity.UserSkinDTO;

@Mapper
public interface GraphMapper {
	
	public List<UserSkinDTO> getMbtiGraph(String mb_id);

	public List<CosmeticsDTO> getSameMbtiGraph(String mb_id);

	public List<CosmeticsDTO> getSameMbtiProduct(String mb_id);

	public List<CosmeticsDTO> getTwProduct();

	public List<CosmeticsDTO> getThProduct();
}
