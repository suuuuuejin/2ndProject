package kr.gjai.hwabun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.gjai.hwabun.entity.CosmeticsDTO;
import kr.gjai.hwabun.entity.EventDTO;
import kr.gjai.hwabun.entity.MemberDTO;
import kr.gjai.hwabun.entity.ReviewDTO;
import kr.gjai.hwabun.entity.StarDTO;

@Mapper
public interface ProductDetailMapper {

	CosmeticsDTO getProduct(int cos_seq);

	List<ReviewDTO> getReviews(int cos_seq);

	List<StarDTO> getStars(int cos_seq);

	void insertReview(ReviewDTO reviewDTO);

	void updateReview(ReviewDTO reviewDTO);

	int deleteReview(ReviewDTO reviewDTO);


	void registerEvent(EventDTO edo);

	void cInsertReview(ReviewDTO reviewDTO);

	int like(int review_seq);

	int likeCheck(ReviewDTO reviewDTO);

	
}
