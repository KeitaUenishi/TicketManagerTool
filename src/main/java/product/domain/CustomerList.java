package product.domain;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CustomerList {

	/** ID */
	private Long id;

	/** 日付ID */
	@NotNull
	@Digits(integer = 8, fraction = 0)
	private Long dateId;

	/** 名前 */
	@NotBlank
	@Size(min = 1, max = 50)
	private String name;

	/** 枚数 */
	@NotNull
	private int number;

	/** 備考 */
	private String comment;

}
