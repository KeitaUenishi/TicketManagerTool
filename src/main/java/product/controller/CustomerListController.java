package product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
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
 * お客さんのリストを操作するコントローラー
 * @author keita
 *
 */
@Controller
@RequestMapping("customer")
public class CustomerListController {

	@Autowired
	private CustomerListService customerListService;

	@Autowired
	private LiveListService liveListService;

	// Live日程新規作成画面の表示
	@GetMapping("/customerNew/{dateId}")
	public String newCustomerList(@PathVariable Long dateId, Model model) {
		LiveList liveList = liveListService.findOne(dateId);
		model.addAttribute("liveList", liveList);
		return "customer/customerNew";
	}

	// お客さん情報編集画面の表示
	@GetMapping("{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		CustomerList customerList = customerListService.findOne(id);
		model.addAttribute("customerList", customerList);
		return "customer/customerEdit";
	}

	// customerデータの保存
	@PostMapping
	public String customerCreate(@ModelAttribute CustomerList customerList) {
		customerListService.insert(customerList);
		return "redirect:/liveList/{dateId}";
	}

	// お客さんデータの更新
	@GetMapping("/update/{id}")
	@Transactional(readOnly = false)
	public String update(@PathVariable Long id,
			@ModelAttribute CustomerList customerList) {
		customerList.setId(id);
		customerListService.update(customerList);
		return "redirect:/liveList";
	}

	// お客さんデータの削除
	@PostMapping("/{id}")
	public String delete(@PathVariable Long id) {
		customerListService.delete(id);
		return "redirect:/liveList";
	}

}
