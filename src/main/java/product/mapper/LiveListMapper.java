package product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import product.domain.LiveList;

@Mapper
public interface LiveListMapper {

	@Select("SELECT * FROM live_list")
	List<LiveList> findAll();

	@Select("SELECT * FROM live_list WHERE dateId = #{dateId}")
	LiveList findOne(Long dateId);

	@Insert("INSERT INTO live_list (dateId, place, remarks) VALUES (#{dateId}, #{place}, #{remarks})")
	void insert(LiveList liveList);

	@Update("UPDATE live_list SET place = #{place}, remarks = #{remarks}) WHERE id = #{dateId}")
	void update(LiveList liveList);

	@Delete("DELETE FROM live_list WHERE dateId = #{dateId}")
	void delete(Long dateId);
}
