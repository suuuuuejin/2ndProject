package kr.gjai.hwabun.service;

import java.util.List;

import kr.gjai.hwabun.entity.CosmeticsDTO;
import kr.gjai.hwabun.entity.UserSkinDTO;

public interface GraphService {
	
	public List<UserSkinDTO> getMbtiGraph(String mb_id);

	public List<CosmeticsDTO> getSameMbtiGraph(String mb_id);

	public List<CosmeticsDTO> getSameMbtiProduct(String mb_id);

	public List<CosmeticsDTO> getTwProduct();

	public List<CosmeticsDTO> getThProduct();


}
