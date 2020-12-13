package product.domain;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LiveList {

	/** 日付ID */
	@NotNull
	@Digits(integer = 8, fraction = 0)
	private Long dateId;

	/** 会場 */
	@NotBlank
	@Size(max = 32, message = "{error.maxsize}")
	private String place;

	/** 備考 */
	private String remarks;

	/** 子クラス（来場客リスト）の要素定義 */
	private List<CustomerList> customers;

}
