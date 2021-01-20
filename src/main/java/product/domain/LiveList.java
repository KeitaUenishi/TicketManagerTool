package product.domain;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LiveList {

	/** 日付ID */
	@NotNull(message = "\u65e5\u4ed8\u60c5\u5831\u3092\uff18\u6841\u3067\u5165\u529b\u3057\u3066\u4e0b\u3055\u3044")
	@Digits(integer = 8, fraction = 0, 
	message = "\u65e5\u4ed8\u60c5\u5831\u3092\uff18\u6841\u3067\u5165\u529b\u3057\u3066\u4e0b\u3055\u3044")
	private Long dateId;

	/** 会場 */
	@NotBlank(message = "\u5834\u6240\u306e\u60c5\u5831\u3092\u5165\u529b\u3057\u3066\u4e0b\u3055\u3044")
	@Size(max = 32, message = "{error.maxSize}\u306e\u6587\u5b57\u6570\u3067\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044")
	private String place;
	
	/** 備考 */
	private String remarks;
	
	/** 子クラス（来場客リスト）の要素定義 */
	private List<CustomerList> customers;
	
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


}
