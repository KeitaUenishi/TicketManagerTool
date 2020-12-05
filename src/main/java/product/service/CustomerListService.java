package product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import product.domain.CustomerList;
import product.mapper.LiveListMapper;

@Service
public class CustomerListService {

	@Autowired
	LiveListMapper customerListMapper;

	// お客さん情報を1件取得する
	@Transactional
	public CustomerList findOne(Long id) {
		return customerListMapper.customerFindOne(id);
	}

	// customer_listへの新規登録処理
	@Transactional
	public void insert(CustomerList customerList) {
		customerListMapper.customerInsert(customerList);
	}

	// customer_listへの更新処理
	@Transactional
	public void update(CustomerList customerList) {
		customerListMapper.customerUpdate(customerList);
	}

	// customer_listへの削除処理
	@Transactional
	public void delete(Long id) {
		customerListMapper.customerDelete(id);
	}
}
