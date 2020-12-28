package product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import product.domain.CustomerList;
import product.domain.LiveList;
import product.service.CustomerListService;
import product.service.LiveListService;

/**
 * ライブ日程のリストを表示するコントローラー
 * ログイン後始めに表示されるページ
 * @author keita
 */

@Controller
@RequestMapping("liveList")
public class LiveListController {

	@Autowired
	private LiveListService liveListService;

	@Autowired
	private CustomerListService customerListService;

	/**
	 * @param model liveList.htmlへデータを持っていく
	 * @return liveList.htmlへ遷移
	 */

	// Live一覧画面の表示
	@GetMapping
	public String findAll(Model model) {
		List<LiveList> liveList = liveListService.findAll();
		model.addAttribute("liveList", liveList);
		return "liveList";
	}

	/**
	 * liveListデータ入力画面に遷移する
	 * @param model liveList.htmlへデータを持っていく
	 * @return liveList.htmlへ遷移
	 */

	// Live日程新規作成画面の表示
	@GetMapping("new")
	public String newLiveList(@ModelAttribute LiveList liveList, Model model) {
		model.addAttribute("liveList", liveList);
		return "liveList/new";
	}

	// Live日程編集画面の表示
	@GetMapping("{dateId}/edit")
	public String edit(@PathVariable Long dateId, Model model) {
		LiveList liveList = liveListService.findOne(dateId);
		model.addAttribute("liveList", liveList);
		return "liveList/edit";
	}

	// 取り置きリスト表示画面の表示
	@GetMapping("{dateId}")
	public String show(@PathVariable Long dateId, Model model) {
		LiveList liveListChoise = liveListService.selectLiveList(dateId);

		// CustomerListに何もなければnull
		try {
			List<CustomerList> customerLists = liveListChoise.getCustomers();
			model.addAttribute("customerLists", customerLists);
		} catch (NullPointerException e) {
			// TODO: handle exception
			model.addAttribute("customerLists", null);
		}

		LiveList liveList = liveListService.findOne(dateId);
		model.addAttribute("liveList", liveList);
		return "liveList/show";
	}

	// Live日程データの保存
	@PostMapping
	@Transactional
	public String create(@Valid @ModelAttribute LiveList liveList,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "/liveList/new";
		}
		liveListService.insert(liveList);
		return "redirect:/liveList";
	}

	// Live日程データの更新
	@GetMapping("/update/{dateId}")
	@Transactional(readOnly = false)
	public String update(@PathVariable Long dateId,
			@Valid @ModelAttribute LiveList liveList, BindingResult result) {
		if (result.hasErrors()) {
			return "/liveList/edit";
		}
		liveList.setDateId(dateId);
		liveListService.update(liveList);
		return "redirect:/liveList";
	}

	// Live日程データの削除
	@PostMapping("/{dateId}")
	@Transactional
	public String delete(@PathVariable Long dateId) {
		liveListService.delete(dateId);
		return "redirect:/liveList";
	}
}
