package kr.gjai.hwabun.service;

import java.util.HashMap;
import java.util.List;

import kr.gjai.hwabun.entity.ConsultingDTO;
import kr.gjai.hwabun.entity.CosmeticsDTO;
import kr.gjai.hwabun.entity.IngredientsDTO;
import kr.gjai.hwabun.entity.MemberDTO;
import kr.gjai.hwabun.entity.SkinMBTIDTO;
import kr.gjai.hwabun.entity.SkinTestAnswerDTO;
import kr.gjai.hwabun.entity.UserSkinDTO;


public interface CustomizingService {

	public HashMap<String, Object> getQuestion(MemberDTO mvo);

	public void reservation(ConsultingDTO vo, MemberDTO mvo);

	public List<IngredientsDTO> getTypeIngredients(String ingredient_type);

	public List<SkinMBTIDTO> getAllMbti();

	public void mbtiAnalysis(SkinTestAnswerDTO ans, String[] ingredient, MemberDTO mvo);

	public UserSkinDTO getUserSkin(MemberDTO mvo);

	public SkinMBTIDTO getUserMbti(String mbti);

	public List<CosmeticsDTO> getRecommendationDR();

	public UserSkinDTO getMeanSkin();

	

}
