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

	public List<CosmeticsDTO> getRankReviews();
	
	public List<CosmeticsDTO> rank_sales(String cateName);
	
	public List<CosmeticsDTO> rank_reviews(String cateName);
	
	public List<CosmeticsDTO> rank_likes(String cateName);

	
	
}
