package product.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import product.service.CustomerListService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class CustomerListControllerTest {

	private MockMvc mockMvc;

	@MockBean
	private CustomerListService service;

	@Autowired
	private CustomerListController target;

	@Before
	public void setUp() throws Exception {
		// ビュー名にprefixやsuffixなどのプロパティを設定して最終的なビューページのURLを生成できる。
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("classpath:templates/");
		viewResolver.setSuffix(".html");

		mockMvc = MockMvcBuilders.standaloneSetup(target).setViewResolvers(viewResolver).build();
	}

	@Test
	public void showページのリクエスト結果が正常となりViewとしてcustomerNewが返ること() throws Exception {
		mockMvc.perform(get("/customer/customerNew/20201210"))
				.andExpect(status().isOk())
				.andExpect(view().name("customer/customerNew"));
	}

	@Test
	public void showページのリクエスト結果が正常となりViewとしてcustomerEditが返ること() throws Exception {
		mockMvc.perform(get("/customer/1/edit"))
				.andExpect(status().isOk())
				.andExpect(view().name("customer/customerEdit"));
	}

	/**
	 * 登録、削除処理のテスト
	 */

	@Test
	public void liveListページで削除処理を行うとサービスで処理されてliveListに遷移されること() throws Exception {
		mockMvc.perform(post("/customer/1"))
				.andExpect(redirectedUrl("/liveList"));

		verify(service, times(1)).delete((long) 1);
	}

	@Test
	public void custemerNewページで新規登録を行うとサービスで処理されてliveList画面に遷移されること() throws Exception {
		mockMvc.perform(post("/customer").param("dateId", "20201231")
				.param("name", "テストテスト").param("number", "1").param("comment", "test"))
				.andExpect(redirectedUrl("/liveList"));

		verify(service, times(1)).insert(any());
	}

	@Test
	public void customerNewページで名前をNullの状態で登録ボタンを押すと例外情報が画面に返ること() throws Exception {
		mockMvc.perform(post("/customer").param("dateId", "20201231")
				.param("number", "1").param("comment", "test"))
				.andExpect(status().isOk())
				.andExpect(view().name("/customer/customerNew"));
	}

	@Test
	public void customerNewページで名前を空の状態で登録ボタンを押すと例外情報が画面に返ること() throws Exception {
		mockMvc.perform(post("/customer").param("dateId", "20201231")
				.param("name", "").param("number", "1").param("comment", "test"))
				.andExpect(status().isOk())
				.andExpect(view().name("/customer/customerNew"));
	}

	@Test
	public void customerNewページで名前を50文字以上の状態で登録ボタンを押すと例外情報が画面に返ること() throws Exception {
		mockMvc.perform(post("/customer").param("dateId", "20201231")
				.param("name", "123456789012345678901234567890123456789012345678901").param("number", "1")
				.param("comment", "test"))
				.andExpect(status().isOk())
				.andExpect(view().name("/customer/customerNew"));
	}

	@Test
	public void customerNewページで枚数を空の状態で登録ボタンを押すと例外情報が画面に返ること() throws Exception {
		mockMvc.perform(post("/customer").param("dateId", "20201231")
				.param("name", "テストテスト").param("number", "").param("comment", "test"))
				.andExpect(status().isOk())
				.andExpect(view().name("/customer/customerNew"));
	}

	//	@Test
	//	public void customerNewページで枚数をNullの状態で登録ボタンを押すと例外情報が画面に返ること() throws Exception {
	//		mockMvc.perform(post("/customer").param("dateId", "20201231")
	//				.param("name", "テストテスト").param("comment", "test"))
	//				.andExpect(status().isOk())
	//				.andExpect(view().name("/customer/customerNew"));
	//	}
}
