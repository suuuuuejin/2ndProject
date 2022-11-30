package kr.gjai.hwabun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.gjai.hwabun.entity.CosmeticsDTO;
import kr.gjai.hwabun.entity.MemberDTO;
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
	public List<CosmeticsDTO> getCateRankSales(String cate) {
		List<CosmeticsDTO> list= rankMapper.getCateRankSales(cate);
		return list;
	}
	@Override
	public List<CosmeticsDTO> getCateRankLikes(String cate) {
		List<CosmeticsDTO> list= rankMapper.getCateRankLikes(cate);
		return list;
	}

}
