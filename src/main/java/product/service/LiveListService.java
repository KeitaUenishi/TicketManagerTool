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

	// live_listを全件取得する
	@Transactional(readOnly = true)
	public List<LiveList> findAll() {
		return liveListMapper.liveFindAll();
	}

	// live_listから1件取得する
	@Transactional
	public LiveList findOne(Long dateId) {
		return liveListMapper.liveFindOne(dateId);
	}

	// live_listに新規登録する
	@Transactional
	public void insert(LiveList liveList) {
		liveListMapper.liveInsert(liveList);
	}

	// live_listを更新する
	@Transactional
	public void update(LiveList liveList) {
		liveListMapper.liveUpdate(liveList);
	}

	// live_listから1件削除する
	@Transactional
	public void delete(Long dateId) {
		liveListMapper.liveDelete(dateId);
	}

	// JOIN dateIdを結合
	public LiveList selectLiveList(Long dateId) {
		return liveListMapper.selectLiveList(dateId);
	}
}
