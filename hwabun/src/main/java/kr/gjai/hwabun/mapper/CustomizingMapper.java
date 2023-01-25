package kr.gjai.hwabun.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.gjai.hwabun.entity.ConsultingDTO;
import kr.gjai.hwabun.entity.CosmeticsDTO;
import kr.gjai.hwabun.entity.IngredientsDTO;
import kr.gjai.hwabun.entity.MemberDTO;
import kr.gjai.hwabun.entity.MemberIngredientsDTO;
import kr.gjai.hwabun.entity.SkinMBTIDTO;
import kr.gjai.hwabun.entity.SkinTestAnswerDTO;
import kr.gjai.hwabun.entity.SkinTestQuestionDTO;
import kr.gjai.hwabun.entity.UserSkinDTO;

@Mapper
public interface CustomizingMapper {

	public void reservation(ConsultingDTO vo);
	
	public List<SkinTestQuestionDTO> getQuestion(HashMap<String, Object> queBundle);
	
	public int testCount(MemberDTO mvo);

	public List<IngredientsDTO> getTypeIngredients(String ingredient_type);

	public void insertMemIngre(MemberIngredientsDTO memIngre);

	public void insertAnswer(SkinTestAnswerDTO ans);

	public List<SkinMBTIDTO> getAllMbti();

	public List<SkinTestAnswerDTO> getAnswerAvg(String mb_id);

	public void insertUserSkin(UserSkinDTO userSkin);

	public UserSkinDTO getUserSkin(MemberDTO mvo);

	public SkinMBTIDTO getUserMbti(String mbti);

	public List<CosmeticsDTO> getRecommendationDR();

	public UserSkinDTO getMeanSkin(); 
}
