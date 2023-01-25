package kr.gjai.hwabun.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.gjai.hwabun.entity.CosmeticsDTO;

import kr.gjai.hwabun.entity.EventDTO;

import kr.gjai.hwabun.entity.MemberDTO;
import kr.gjai.hwabun.entity.ReviewDTO;
import kr.gjai.hwabun.entity.StarDTO;
import kr.gjai.hwabun.mapper.ProductDetailMapper;

@Service
public class ProductDetailServiceImpl implements ProductDetailService{
	
	@Autowired
	ProductDetailMapper productDetailMapper;

	@Override
	public CosmeticsDTO getProduct(int cos_seq) {
		CosmeticsDTO dto = productDetailMapper.getProduct(cos_seq);
		return dto;
	}

	@Override
	public List<ReviewDTO> getReviews(int cos_seq) {
		List<ReviewDTO> dto = productDetailMapper.getReviews(cos_seq);
		return dto;
	}

	@Override
	public List<StarDTO> getStars(int cos_seq) {
		List<StarDTO> dto = productDetailMapper.getStars(cos_seq);
		return dto;
	}

	@Override
	public void updateReview(ReviewDTO reviewDTO) {
		productDetailMapper.updateReview(reviewDTO);
	}

	@Override
	public void insertReview(ReviewDTO reviewDTO, MultipartFile file) throws IllegalStateException, IOException {
		//업로드 파일 저장 경로 담는 변수
		String projectPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\files";
		//식별자(랜덤파일이름 만들어줌)
		UUID uuid = UUID.randomUUID();
		//식별자_파일 원래이름 으로 파일명이 만들어짐
		String fileName = uuid + "_"+file.getOriginalFilename();
		//파일을 위 경로에 파일명을 fileName으로 담아줌
		File saveFile = new File(projectPath,fileName);
		file.transferTo(saveFile);
		reviewDTO.setReview_photo(fileName);
		reviewDTO.setFilepath("/files/"+fileName);
		productDetailMapper.insertReview(reviewDTO);
		
	}

	@Override
	public int deleteReview(ReviewDTO reviewDTO) {
		int cnt = productDetailMapper.deleteReview(reviewDTO);
		return cnt;
	}


	@Override
	public void registerEvent(EventDTO edo) {
		productDetailMapper.registerEvent(edo);
		
	}

	@Override
	public void cInsertReview(ReviewDTO reviewDTO) {
		productDetailMapper.cInsertReview(reviewDTO);
		
	}

	@Override
	public int like(int review_seq) {
		int cnt = productDetailMapper.like(review_seq);
		return cnt;
	}

	@Override
	public int likeCheck(ReviewDTO reviewDTO) {
		int cnt = productDetailMapper.likeCheck(reviewDTO);
		return 0;
	}

	

	

	
	




}
