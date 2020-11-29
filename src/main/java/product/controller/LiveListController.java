package product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import product.domain.LiveList;
import product.service.LiveListService;

/**
 * ライブ日程のリストを表示するコントローラー
 * ログイン後始めに表示されるページ
 * @author keita
 *
 */

@Controller
@RequestMapping("liveList")
public class LiveListController {

	@Autowired
	private LiveListService liveListService;

	/**
	 * @param model liveList.htmlへデータを持っていく
	 * @return liveList.htmlへ遷移
	 */

	// 一覧画面の表示
	@GetMapping
	public String findAll(Model model) {
		List<LiveList> liveList = liveListService.findAll();
		model.addAttribute("liveList", liveList);
		return "liveList";
	}

	/**
	 * liveListデータ入力画面に遷移する
	 * @param model liveListData.htmlへデータを持っていく
	 * @return liveListData.htmlへ遷移
	 */

	// 新規作成画面の表示
	@GetMapping("new")
	public String newLiveList(Model model) {
		return "liveList/new";
	}

	// 編集画面の表示
	@GetMapping("{dateId}/edit")
	public String edit(@PathVariable Long dateId, Model model) {
		LiveList liveList = liveListService.findOne(dateId);
		model.addAttribute("liveList", liveList);
		return "liveList/edit";
	}

	// 参照画面の表示
	@GetMapping("{id}")
	public String show(@PathVariable Long dateId, Model model) {
		LiveList liveList = liveListService.findOne(dateId);
		model.addAttribute("liveList", liveList);
		return "liveList/show";
	}

	// データの保存
	@PutMapping
	public String create(@ModelAttribute LiveList liveList) {
		liveListService.insert(liveList);
		return "redirect:/liveList";
	}

	// データの更新
	@DeleteMapping("/update/{id}")
	@Transactional(readOnly = false)
	public String update(@PathVariable Long dateId, @ModelAttribute LiveList liveList) {
		liveList.setDateId(dateId);
		liveListService.update(liveList);
		return "redirect:/liveList";
	}

	// データの削除
	@PostMapping("delete/{id}")
	public String delete(@PathVariable Long dateId) {
		liveListService.delete(dateId);
		return "redirect:/liveList";
	}

}
