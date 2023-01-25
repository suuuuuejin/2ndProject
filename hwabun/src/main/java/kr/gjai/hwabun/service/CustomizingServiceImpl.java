package kr.gjai.hwabun.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.gjai.hwabun.entity.ConsultingDTO;
import kr.gjai.hwabun.entity.CosmeticsDTO;
import kr.gjai.hwabun.entity.IngredientsDTO;
import kr.gjai.hwabun.entity.MemberDTO;
import kr.gjai.hwabun.entity.MemberIngredientsDTO;
import kr.gjai.hwabun.entity.SkinMBTIDTO;
import kr.gjai.hwabun.entity.SkinTestAnswerDTO;
import kr.gjai.hwabun.entity.SkinTestQuestionDTO;
import kr.gjai.hwabun.entity.UserSkinDTO;
import kr.gjai.hwabun.mapper.CustomizingMapper;


@Service
public class CustomizingServiceImpl implements CustomizingService{
	
	@Autowired
	CustomizingMapper customizingMapper;
	
	// 1:1 컨설팅 예약 관련 메소드
	@Override
	public void reservation(ConsultingDTO vo, MemberDTO mvo) {
		vo.setMb_id(mvo.getMb_id());
		customizingMapper.reservation(vo);
		
	}
	
	// 피부진단 질문지 가져오는 메소드
	@Override
	public HashMap<String, Object> getQuestion(MemberDTO mvo) {
		
		// 이전 테스트 횟수 
		int testCnt = customizingMapper.testCount(mvo);
		// 테스트지
		List<SkinTestQuestionDTO> que = new ArrayList<SkinTestQuestionDTO>();
		// 
		HashMap<String, Object > queBundle = new HashMap<String, Object>();
		
		if(testCnt==0) {		
			if(mvo.getMb_gender()=='F') {
				queBundle.put("start", 101);
				queBundle.put("end", 199); // 101~ first 여성

			}else{
				queBundle.put("start", 201);
				queBundle.put("end", 299); // 201~ first 남성
				
			}		
		}else {
			if(mvo.getMb_gender()=='F') {
				
				queBundle.put("start", 301);
				queBundle.put("end", 399); // 301~ 두번 이상 여성
				
			}else{
				
				queBundle.put("start", 401);
				queBundle.put("end", 499); // 401~ 두번 이상 남성
					
			}
		}
		
		que= customizingMapper.getQuestion(queBundle);
		HashMap<String, Object> prepareTest =  new HashMap<String, Object>();
		prepareTest.put("questions", que);
		prepareTest.put("testCnt",testCnt);
		return prepareTest;
	}

	
	// 특정 성분 유형 모두 가져오기
	@Override
	public List<IngredientsDTO> getTypeIngredients(String ingredient_type) {
		List<IngredientsDTO> ingre = customizingMapper.getTypeIngredients(ingredient_type);
		return ingre;
	}

	
	
	// skintest 결과 저장 피부특성 저장 mbti저장
	@Override
	public void mbtiAnalysis(SkinTestAnswerDTO ans, String[] ingredient, MemberDTO mvo) {
		
		
		
		// 유저의 피부 진단 테스트 답변 저장
		for(int i=0; i<ans.getAnsList().size();i++) {
			SkinTestAnswerDTO answer = new SkinTestAnswerDTO();
			
			answer.setMb_id(ans.getAnsList().get(i).getMb_id());
			answer.setQue_seq(ans.getAnsList().get(i).getQue_seq());
			answer.setQue_type(ans.getAnsList().get(i).getQue_type());
			answer.setAns_value(ans.getAnsList().get(i).getAns_value());
			answer.setSkintest_cnt(ans.getAnsList().get(i).getSkintest_cnt());
			
			customizingMapper.insertAnswer(answer);	
		}

		
		// 유저가 알러지 성분이 있는지 여부 저장하고 있을 시 성분 저장
		UserSkinDTO userSkin = new UserSkinDTO();
		userSkin.setMb_id(mvo.getMb_id());
		if(ingredient!=null) {	
			userSkin.setIngredient("Y");		
			
			for(int i = 0 ; i<ingredient.length;i++) {		
				MemberIngredientsDTO memIngre = new MemberIngredientsDTO();
				int ingre_seq = Integer.parseInt(ingredient[i]);
				memIngre.setMb_id(mvo.getMb_id());
				memIngre.setIngredient_seq(ingre_seq);
				customizingMapper.insertMemIngre(memIngre);
			}
			
		}else {
			userSkin.setIngredient("N");
		}
		
		// 유저의 특성값 저장
		// 최근 3회차 값들만 평균내서 불러오기
		List<SkinTestAnswerDTO> answerAvg = customizingMapper.getAnswerAvg(mvo.getMb_id());
		
		// user_skin 값에 넣을 값 저장
		for(SkinTestAnswerDTO ansAvg : answerAvg) {
		
			if(ansAvg.getQue_type().equals("유분")) {
				userSkin.setOil(ansAvg.getAns_value());
			}else if(ansAvg.getQue_type().equals("수분")) {
				userSkin.setWater(5-ansAvg.getAns_value());
			}else if(ansAvg.getQue_type().equals("색소")) {
				userSkin.setColor(ansAvg.getAns_value());
			}else if(ansAvg.getQue_type().equals("주름")) {
				userSkin.setWrinkle(ansAvg.getAns_value());
			}else if(ansAvg.getQue_type().equals("트러블")) {
				userSkin.setTrouble(ansAvg.getAns_value());
			}else if(ansAvg.getQue_type().equals("민감")) {
				userSkin.setSensi(ansAvg.getAns_value());
			}
		}
		
		// 피부 mbti
		String mbti = "";		
		mbti += userSkin.getOil()+userSkin.getWater()>=5?"O":"D";
		mbti += userSkin.getTrouble()+userSkin.getSensi()>=5?"S":"R";
		mbti += userSkin.getColor()*2>=5?"P":"N";
		mbti += userSkin.getWrinkle()*2>=5?"W":"T";
		userSkin.setMbti(mbti);
		customizingMapper.insertUserSkin(userSkin);
	}


	// 모든 mbti 유형 불러오기
	@Override
	public List<SkinMBTIDTO> getAllMbti() {
		List<SkinMBTIDTO> mbti = customizingMapper.getAllMbti(); 
		
		return mbti;
	}

	@Override
	public UserSkinDTO getUserSkin(MemberDTO mvo) {
		UserSkinDTO userSkin = customizingMapper.getUserSkin(mvo);
		return userSkin;
	}

	@Override
	public SkinMBTIDTO getUserMbti(String mbti) {
		SkinMBTIDTO userMbti = customizingMapper.getUserMbti(mbti);
		return userMbti;
	}

	@Override
	public List<CosmeticsDTO> getRecommendationDR() {
		List<CosmeticsDTO> dr = customizingMapper.getRecommendationDR();
		return dr;
	}

	@Override
	public UserSkinDTO getMeanSkin() {
		UserSkinDTO meanSkin =customizingMapper.getMeanSkin();
		return meanSkin;
	}

	
	
}
