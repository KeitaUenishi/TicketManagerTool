package product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import product.domain.LiveList;

@Mapper
public interface LiveListMapper {

	List<LiveList> findAll();

	LiveList findOne(Long dateId);

	void insert(LiveList liveList);

	void update(LiveList liveList);

	void delete(Long dateId);
}
