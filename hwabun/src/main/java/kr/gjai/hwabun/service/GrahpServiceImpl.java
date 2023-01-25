package kr.gjai.hwabun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.gjai.hwabun.entity.CosmeticsDTO;
import kr.gjai.hwabun.entity.MemberDTO;
import kr.gjai.hwabun.entity.UserSkinDTO;
import kr.gjai.hwabun.mapper.GraphMapper;
import kr.gjai.hwabun.mapper.TestMapper;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class GrahpServiceImpl implements GraphService{
	
	@Autowired
	GraphMapper graphMapper;


	@Override
	public List<UserSkinDTO> getMbtiGraph(String mb_id) {
		List<UserSkinDTO> list = graphMapper.getMbtiGraph(mb_id);
		log.info(list);
		return list;
	}


	@Override
	public List<CosmeticsDTO> getSameMbtiGraph(String mb_id) {
		List<CosmeticsDTO> list = graphMapper.getSameMbtiGraph(mb_id);
	
		
		return list;
	}


	@Override
	public List<CosmeticsDTO> getSameMbtiProduct(String mb_id) {
		List<CosmeticsDTO> list = graphMapper.getSameMbtiProduct(mb_id);
		return list;
	}
	
	@Override
		public List<CosmeticsDTO> getTwProduct() {
			List<CosmeticsDTO> list = graphMapper.getTwProduct();
			return list;
		}



	@Override
	public List<CosmeticsDTO> getThProduct() {
		List<CosmeticsDTO> list = graphMapper.getThProduct();
		return list;
	}


	


	}

	

	
	
	

