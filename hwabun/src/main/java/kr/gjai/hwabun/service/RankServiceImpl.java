package kr.gjai.hwabun.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.gjai.hwabun.entity.CosmeticsDTO;
import kr.gjai.hwabun.entity.MemberDTO;
import kr.gjai.hwabun.entity.UserSkinDTO;
import kr.gjai.hwabun.mapper.RankMapper;

@Service
public class RankServiceImpl implements RankService{
	
	@Autowired
	RankMapper rankMapper;
	
	@Override
	public List<CosmeticsDTO> getAllProduct() {
		List<CosmeticsDTO> list= rankMapper.getAllProduct();
		return list;
	}

	@Override
	public List<CosmeticsDTO> getRankSales() {
		List<CosmeticsDTO> list= rankMapper.getRankSales();
		return list;
	}
	@Override
	public List<CosmeticsDTO> getRankLikes() {
		List<CosmeticsDTO> list= rankMapper.getRankLikes();
		return list;
	}

	@Override
	public List<CosmeticsDTO> getRankReviews() {
		List<CosmeticsDTO> list= rankMapper.getRankReviews();
		return list;
	}


	@Override
	public List<CosmeticsDTO> rank_sales(String cateName) {
		
		List<CosmeticsDTO> list = new ArrayList<CosmeticsDTO>();
		if(cateName.equals("전체")) {
			list= rankMapper.getRankSales();
		}else {
			list= rankMapper.rank_sales(cateName);
		}
		return list;
	}

	@Override
	public List<CosmeticsDTO> rank_reviews(String cateName) {
		
		List<CosmeticsDTO> list = new ArrayList<CosmeticsDTO>();
		if(cateName.equals("전체")) {
			list= rankMapper.getRankReviews();
		}else {
			list= rankMapper.rank_reviews(cateName);
		}
		return list;
	}

	@Override
	public List<CosmeticsDTO> rank_likes(String cateName) {
		
		List<CosmeticsDTO> list = new ArrayList<CosmeticsDTO>();
		if(cateName.equals("전체")) {
			list= rankMapper.getRankLikes();
		}else {
			list= rankMapper.rank_likes(cateName);
		}
		
		return list;
	}

}
