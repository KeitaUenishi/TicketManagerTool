package product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import product.domain.CustomerList;
import product.domain.LiveList;

@Mapper
public interface LiveListMapper {

	List<LiveList> liveFindAll();

	LiveList liveFindOne(Long dateId);

	void liveInsert(LiveList liveList);

	void liveUpdate(LiveList liveList);

	void liveDelete(Long dateId);

	List<CustomerList> customerFindAll();

	// JOIN
	public LiveList selectLiveList(Long dateId);

	CustomerList customerFindOne(Long id);

	void customerInsert(CustomerList customerList);

	void liveUpdate(CustomerList customerList);

	void customerDelete(Long id);

}
