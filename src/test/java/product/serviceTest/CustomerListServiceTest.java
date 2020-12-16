package product.serviceTest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import product.service.CustomerListService;

@RunWith(SpringRunner.class)
public class CustomerListServiceTest {

	@Autowired
	private CustomerListService customerListService;

	@Test
	public void test() {
		fail("まだ実装されていません");
	}

}
