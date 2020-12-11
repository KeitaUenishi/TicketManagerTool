package product.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CustomerList {

	/** ID */
	@NotBlank
	@Size(min = 1, max = 10)
	private Long id;

	/** 日付ID */
	@NotBlank
	@Size(min = 1, max = 10)
	private Long dateId;

	/** 名前 */
	@NotBlank
	@Size(min = 1, max = 50)
	private String name;

	/** 枚数 */
	@NotBlank
	@Size(min = 1, max = 5)
	private int number;

	/** 備考 */
	private String comment;

}
