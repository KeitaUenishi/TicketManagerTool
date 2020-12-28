package product.domain;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDateId() {
		return dateId;
	}

	public void setDateId(Long dateId) {
		this.dateId = dateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
