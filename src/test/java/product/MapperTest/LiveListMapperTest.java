package product.MapperTest;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import product.domain.CustomerList;
import product.domain.LiveList;
import product.mapper.LiveListMapper;

@RunWith(SpringRunner.class)
@MybatisTest
@TestPropertySource(locations = "classpath:test.properties")
public class LiveListMapperTest {

	@Autowired
	private LiveListMapper mapperTest;

	/**
	 * live_listテーブルに関するテスト
	 */

	@Test
	public void 検索_live_list_が全件取得できる() throws Exception {
		List<LiveList> actual = mapperTest.liveFindAll();
		assertThat(actual.size()).isEqualTo(2);
	}

	@Test
	public void 検索_1件検索してlive_listテーブルからキーに紐づく1件だけ取得できる() throws Exception {
		LiveList actual = mapperTest.liveFindOne((long) 20201210);

		assertThat(actual.getDateId()).isEqualTo((long) 20201210);
		assertThat(actual.getPlace()).isEqualTo("アメリカ村");
		assertThat(actual.getRemarks()).isEqualTo("キャパ10");
	}

	@Test
	public void 検索_live_listで存在しないデータを検索するとNULLとなること() throws Exception {
		LiveList actual = mapperTest.liveFindOne((long) 20201101);

		assertThat(actual).isNull();
	}

	@Test
	public void 新規_livelist_新規登録ができること() throws Exception {
		LiveList liveList = createLiveList((long) 20201101, "大阪エジプト", "キャパ8");

		mapperTest.liveInsert(liveList);
		LiveList actual = mapperTest.liveFindOne((long) 20201101);

		assertThat(actual.getDateId()).isEqualTo((long) 20201101);
	}

	@Test
	public void 更新_livelist_キーに紐づく1件の更新ができること() throws Exception {
		LiveList liveList = createLiveList((long) 20210105, "東京egypt", "ノルマ15");

		mapperTest.liveUpdate(liveList);
		LiveList actual = mapperTest.selectLiveList((long) 20210105);

		assertThat(actual.getDateId()).isEqualTo((long) 20210105);
		assertThat(actual.getPlace()).isEqualTo("東京egypt");
		assertThat(actual.getRemarks()).isEqualTo("ノルマ15");
	}

	@Test
	public void 削除_livelist_キーに紐づく1件の削除ができること() throws Exception {
		mapperTest.liveDelete((long) 20210105);
		List<LiveList> actual = mapperTest.liveFindAll();

		assertThat(actual.size()).isEqualTo(1);
	}

	private LiveList createLiveList(Long 日付ID, String 場所, String 備考) {
		LiveList liveList = new LiveList();
		liveList.setDateId(日付ID);
		liveList.setPlace(場所);
		liveList.setRemarks(備考);
		return liveList;
	}

	/**
	 * customers_listテーブルに関するテスト
	 */

	@Test
	public void 検索_customers_list_来場客リストが全件取得できる() throws Exception {
		List<CustomerList> actual = mapperTest.customerFindAll();
		assertThat(actual.size()).isEqualTo(2);
	}

	@Test
	public void 検索_1件検索してcustomers_listテーブルからキーに紐づく1件だけ取得できる() throws Exception {
		CustomerList actual = mapperTest.customerFindOne((long) 1);

		assertThat(actual.getId()).isEqualTo((long) 1);
		assertThat(actual.getDateId()).isEqualTo((long) 20201210);
		assertThat(actual.getName()).isEqualTo("田中権蔵");
		assertThat(actual.getNumber()).isEqualTo(1);
		assertThat(actual.getComment()).isEqualTo("最近来た");
	}

	@Test
	public void 検索_customers_listで存在しないデータを検索するとNULLとなること() throws Exception {
		CustomerList actual = mapperTest.customerFindOne((long) 20201101);

		assertThat(actual).isNull();
	}

	@Test
	public void 新規_customers_list_新規登録ができること() throws Exception {
		CustomerList customerList = createCustomerList((long) 3, (long) 20201101, "やんばる三蔵", 1, "テンション高い");

		mapperTest.customerInsert(customerList);
		CustomerList actual = mapperTest.customerFindOne((long) 3);

		assertThat(actual.getId()).isEqualTo((long) 3);
	}

	@Test
	public void 更新_customers_list_キーに紐づく1件の更新ができること() throws Exception {
		CustomerList customerList = createCustomerList((long) 2, (long) 20210105, "おてあらい", 2, "元気");

		mapperTest.customerUpdate(customerList);
		CustomerList actual = mapperTest.customerFindOne((long) 2);

		assertThat(actual.getId()).isEqualTo(2);
		assertThat(actual.getDateId()).isEqualTo((long) 20210105);
		assertThat(actual.getName()).isEqualTo("おてあらい");
		assertThat(actual.getNumber()).isEqualTo(2);
		assertThat(actual.getComment()).isEqualTo("元気");
	}

	@Test
	public void 削除_customers_list_キーに紐づく1件の削除ができること() throws Exception {
		mapperTest.customerDelete((long) 1);
		List<CustomerList> actual = mapperTest.customerFindAll();

		assertThat(actual.size()).isEqualTo(1);
	}

	private CustomerList createCustomerList(Long id, Long 日付ID, String 名前, int 枚数, String コメント) {
		CustomerList customerList = new CustomerList();
		customerList.setId(id);
		customerList.setDateId(日付ID);
		customerList.setName(名前);
		customerList.setNumber(枚数);
		customerList.setComment(コメント);
		return customerList;
	}
}
