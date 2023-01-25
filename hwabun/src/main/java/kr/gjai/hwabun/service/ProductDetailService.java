package kr.gjai.hwabun.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.gjai.hwabun.entity.CosmeticsDTO;

import kr.gjai.hwabun.entity.EventDTO;

import kr.gjai.hwabun.entity.MemberDTO;

import kr.gjai.hwabun.entity.ReviewDTO;
import kr.gjai.hwabun.entity.StarDTO;

public interface ProductDetailService {

	CosmeticsDTO getProduct(int cos_seq);

	List<ReviewDTO> getReviews(int cos_seq);

	List<StarDTO> getStars(int cos_seq);

	void insertReview(ReviewDTO reviewDTO, MultipartFile files) throws IllegalStateException, IOException;

	void updateReview(ReviewDTO reviewDTO);

	int deleteReview(ReviewDTO reviewDTO);


	void registerEvent(EventDTO edo);

	void cInsertReview(ReviewDTO reviewDTO);

	int like(int review_seq);

	int likeCheck(ReviewDTO reviewDTO);


	

	

	


}
