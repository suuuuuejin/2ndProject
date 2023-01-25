package kr.gjai.hwabun.service;

import java.util.List;

import kr.gjai.hwabun.entity.CosmeticsDTO;

public interface CateService {
	public List<CosmeticsDTO> getProductList(String cate);
	public List<CosmeticsDTO> getProductList2(String cate1, String cate2);
	public List getCate(String cate);
}
