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

import product.service.LiveListService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class LiveListControllerTest {

	private MockMvc mockMvc;

	@MockBean
	private LiveListService service;

	@Autowired
	private LiveListController target;

	@Before
	public void setUp() throws Exception {
		// ビュー名にprefixやsuffixなどのプロパティを設定して最終的なビューページのURLを生成できる。
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("classpath:templates/");
		viewResolver.setSuffix(".html");

		mockMvc = MockMvcBuilders.standaloneSetup(target).setViewResolvers(viewResolver).build();
	}

	/**
	 * 画面表示テスト
	 */

	@Test
	public void liveListページのリクエスト結果が正常となりViewとしてliveListが返ること() throws Exception {
		mockMvc.perform(get("/liveList"))
				.andExpect(status().isOk())
				.andExpect(view().name("liveList"));
	}

	@Test
	public void newページのリクエスト結果が正常となりViewとしてnewが返ること() throws Exception {
		mockMvc.perform(get("/liveList/new"))
				.andExpect(status().isOk())
				.andExpect(view().name("liveList/new"));
	}

	@Test
	public void editページのリクエスト結果が正常となりViewとしてeditが返ること() throws Exception {
		mockMvc.perform(get("/liveList/20210105/edit"))
				.andExpect(status().isOk())
				.andExpect(view().name("liveList/edit"));
	}

	@Test
	public void showページのリクエスト結果が正常となりViewとしてshowが返ること() throws Exception {
		mockMvc.perform(get("/liveList/20210105"))
				.andExpect(status().isOk())
				.andExpect(view().name("liveList/show"));
	}

	/**
	 * 登録、削除処理のテスト
	 */

	@Test
	public void liveListページで削除処理を行うとサービスで処理されて同一画面に遷移されること() throws Exception {
		mockMvc.perform(post("/liveList/20210105"))
				//				.andExpect(status().isOk())
				.andExpect(redirectedUrl("/liveList"));

		verify(service, times(1)).delete((long) 20210105);
	}

	@Test
	public void newページで新規登録を行うとサービスで処理されてliveList画面に遷移されること() throws Exception {
		mockMvc.perform(
				post("/liveList").param("dateId", "20201231").param("place", "テストテスト").param("remarks", "test"))
				.andExpect(redirectedUrl("/liveList"));

		verify(service, times(1)).insert(any());
	}

	@Test
	public void newページで日付をNullの状態で登録ボタンを押すと例外情報が画面に返ること() throws Exception {
		mockMvc.perform(post("/liveList").param("place", "テストテスト").param("remarks", "test"))
				.andExpect(status().isOk())
				.andExpect(view().name("/liveList/new"));
	}

	@Test
	public void newページで日付を空にして登録ボタンを押すと例外情報が画面に返ること() throws Exception {
		mockMvc.perform(post("/liveList").param("dateId", "").param("place", "テストテスト").param("remarks", "test"))
				.andExpect(status().isOk())
				.andExpect(view().name("/liveList/new"));
	}

	@Test
	public void newページで日付を9桁以上にして登録ボタンを押すと例外情報が画面に返ること() throws Exception {
		mockMvc.perform(
				post("/liveList").param("dateId", "202010123").param("place", "テストテスト").param("remarks", "test"))
				.andExpect(status().isOk())
				.andExpect(view().name("/liveList/new"));
	}

	@Test
	public void newページで場所をNullの状態で登録ボタンを押すと例外情報が画面に返ること() throws Exception {
		mockMvc.perform(
				post("/liveList").param("dateId", "202010123").param("remarks", "test"))
				.andExpect(status().isOk())
				.andExpect(view().name("/liveList/new"));
	}

	@Test
	public void newページで場所を空の状態で登録ボタンを押すと例外情報が画面に返ること() throws Exception {
		mockMvc.perform(
				post("/liveList").param("dateId", "202010123").param("place", "").param("remarks", "test"))
				.andExpect(status().isOk())
				.andExpect(view().name("/liveList/new"));
	}

	@Test
	public void newページで場所を32文字以上の状態で登録ボタンを押すと例外情報が画面に返ること() throws Exception {
		mockMvc.perform(
				post("/liveList").param("dateId", "202010123").param("place", "123456789012345678901234567890123")
						.param("remarks", "test"))
				.andExpect(status().isOk())
				.andExpect(view().name("/liveList/new"));
	}

	/**
	 * 更新処理
	 */

	@Test
	public void editページで更新登録を行うとサービスで処理されてliveList画面に遷移されること() throws Exception {
		mockMvc.perform(get("/liveList/update/20201231").param("dateId", "20201231")
				.param("place", "テストテスト").param("remarks", "test"))
				.andExpect(redirectedUrl("/liveList"));

		verify(service, times(1)).update(any());
	}

	@Test
	public void editページで場所をNullの状態で登録ボタンを押すと例外情報が画面に返ること() throws Exception {
		mockMvc.perform(
				get("/liveList/update/20201231").param("dateId", "20201231")
						.param("remarks", "test"))
				.andExpect(status().isOk())
				.andExpect(view().name("/liveList/edit"));
	}

	@Test
	public void editページで場所を空の状態で登録ボタンを押すと例外情報が画面に返ること() throws Exception {
		mockMvc.perform(
				get("/liveList/update/20201231").param("dateId", "20201231")
						.param("place", "").param("remarks", "test"))
				.andExpect(status().isOk())
				.andExpect(view().name("/liveList/edit"));
	}

	@Test
	public void editページで場所を32桁以上の状態で登録ボタンを押すと例外情報が画面に返ること() throws Exception {
		mockMvc.perform(
				get("/liveList/update/20201231").param("dateId", "20201231")
						.param("place", "123456789012345678901234567890123").param("remarks", "test"))
				.andExpect(status().isOk())
				.andExpect(view().name("/liveList/edit"));
	}
}
