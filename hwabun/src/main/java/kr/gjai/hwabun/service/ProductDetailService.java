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

	void insertReview(ReviewDTO reviewDTO, MultipartFile file) throws IllegalStateException, IOException;

	void updateReview(ReviewDTO reviewDTO, MultipartFile file);

	int deleteReview(ReviewDTO reviewDTO);


	void registerEvent(EventDTO edo);


	

	

	


}
