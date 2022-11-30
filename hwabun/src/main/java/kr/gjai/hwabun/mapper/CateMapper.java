package kr.gjai.hwabun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.gjai.hwabun.entity.CosmeticsDTO;

@Mapper
public interface CateMapper {
	public List<CosmeticsDTO> getProductList(String cate);

	public List getProductList2(@Param("cate1") String cate1,@Param("cate2") String cate2);

	public List getCate(String cate);
}
