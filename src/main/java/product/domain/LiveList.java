package product.domain;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LiveList {

	/** 日付ID */
	@NotNull
	@Digits(integer = 8, fraction = 0)
	private Long dateId;

	public Long getDateId() {
		return dateId;
	}

	public void setDateId(Long dateId) {
		this.dateId = dateId;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<CustomerList> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerList> customers) {
		this.customers = customers;
	}

	/** 会場 */
	@NotBlank
	@Size(max = 32, message = "{error.maxsize}")
	private String place;

	/** 備考 */
	private String remarks;

	/** 子クラス（来場客リスト）の要素定義 */
	private List<CustomerList> customers;

}
