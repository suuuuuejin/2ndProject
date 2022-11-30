package kr.gjai.hwabun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.gjai.hwabun.entity.CosmeticsDTO;
import kr.gjai.hwabun.entity.MemberDTO;

@Mapper
public interface RankMapper {

	public List<CosmeticsDTO> getAllProduct();

	public List<CosmeticsDTO> getRankSales();

	public List<CosmeticsDTO> getRankLikes();

	public List<CosmeticsDTO> getCateRankSales(String cate);

	public List<CosmeticsDTO> getCateRankLikes(String cate);
	
}
