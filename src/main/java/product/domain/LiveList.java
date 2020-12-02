package product.domain;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LiveList {

	/** 日付ID */
	@NotBlank
	@Size(min = 1, max = 10)
	private Long dateId;

	/** 会場 */
	@NotBlank
	@Size(min = 1, max = 32)
	private String place;

	/** 備考 */
	private String remarks;

	/** 子クラス（来場客リスト）の要素定義 */
	private List<CustomerList> customers;

}
