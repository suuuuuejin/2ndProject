package kr.gjai.hwabun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.gjai.hwabun.entity.CosmeticsDTO;
import kr.gjai.hwabun.mapper.CateMapper;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CateServiceImpl implements CateService{

	@Autowired
	CateMapper cateMapper;
	
	@Override
	public List<CosmeticsDTO> getProductList(String cate) {
		List list = cateMapper.getProductList(cate);
		return list;
	}

	@Override
	public List<CosmeticsDTO> getProductList2(String cate1, String cate2) {
		List list = cateMapper.getProductList2(cate1, cate2);
		return list;
	}

	@Override
	public List getCate(String cate) {
		List cate2 = cateMapper.getCate(cate);
		return cate2;
	}

}
