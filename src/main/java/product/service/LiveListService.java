package product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import product.domain.LiveList;
import product.mapper.LiveListMapper;

@Service
public class LiveListService {

	@Autowired
	private LiveListMapper liveListMapper;

	@Transactional(readOnly = true)
	public List<LiveList> findAll() {
		return liveListMapper.findAll();
	}

	@Transactional
	public LiveList findOne(Long dateId) {
		return liveListMapper.findOne(dateId);
	}

	@Transactional
	public void insert(LiveList liveList) {
		liveListMapper.insert(liveList);
	}

	@Transactional
	public void update(LiveList liveList) {
		liveListMapper.update(liveList);
	}

	@Transactional
	public void delete(Long dateId) {
		liveListMapper.delete(dateId);
	}
}
