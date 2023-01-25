package kr.gjai.hwabun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.gjai.hwabun.entity.MemberDTO;
import kr.gjai.hwabun.entity.OrderDTO;
import kr.gjai.hwabun.entity.OrderDetailDTO;
import kr.gjai.hwabun.entity.RecReviewDTO;
import kr.gjai.hwabun.entity.RecoDTO;
import kr.gjai.hwabun.entity.ReviewDTO;

@Mapper
public interface MyPageMapper {

	

	@Select("select * from members where mb_id=#{mb_id}")
	public List<MemberDTO> getInfo(String mb_id);

	
	@Update("update members set mb_pw=#{param2} where mb_id=#{param1}")
	public void changePwd(String param1, String param2);

	@Select("select r.*, c.cos_name, m.mb_name from reviews r,cosmetics c, members m where r.cos_seq=c.cos_seq and m.mb_id=r.mb_id and r.mb_id=#{mb_id}")
	public List<ReviewDTO> showReview(String mb_id);

	
	@Select("select * from orders where mb_id=#{mb_id} order by order_date desc")
	public List<OrderDTO> showOrders(String mb_id);

	@Select("select d.*,c.cos_name,c.cos_photo1,c.cos_price,(select d.cos_cnt*c.cos_price from dual) as price from order_detail d,cosmetics c where d.cos_seq=c.cos_seq and d.order_seq=#{number}")
	public List<OrderDetailDTO> showDetail(int number);
	
	@Select("select u.*,r.reco_seq ,r.cos_seq from user_skin u inner join recommendation r\n"
			+ "on u.skin_seq=r.skin_seq where mb_id=#{mb_id};")
	public List<RecoDTO> recInfo(String mb_id);
	
	
	
	@Select("select o.*,d.od_seq,d.cos_seq,d.cos_cnt from orders o inner join order_detail d\n"
			+"on o.order_seq=d.order_seq where o.mb_id=#{mb_id}")
	public List<OrderDTO> orderInfo(String mb_id);


	
	@Select("select distinct order_detail.cos_seq, recommendation.reco_seq, cosmetics.cos_name, cosmetics.cos_photo1, user_skin.reg_date, recommendation_review.rv_content from recommendation inner join user_skin on recommendation.skin_seq=user_skin.skin_seq inner join cosmetics on cosmetics.cos_seq=recommendation.cos_seq inner join order_detail on order_detail.cos_seq=recommendation.cos_seq inner join orders on orders.order_seq=order_detail.order_seq left outer join recommendation_review on recommendation.reco_seq=recommendation_review.reco_seq where user_skin.mb_id=#{mb_id} order by user_skin.reg_date desc;")
	public List<RecReviewDTO> showRReview(String mb_id);

	
	
	
	
	
	
	
	@Select("select max(u.skin_seq) from user_skin u inner join recommendation r on u.skin_seq=r.skin_seq where u.mb_id=#{param1} and r.cos_seq=#{param2}")
	public int findSkinseq(String param1, Integer param2);


	@Insert("insert into recommendation_review(reco_seq,rv_content,rv_date,mb_id) values(#{param2},#{param3},now(),#{param1})"
			+ "on duplicate key update rv_content=#{param3}, rv_date=now()")
	public void saveRReview(String param1, int param2, String param3);
	
	
}
