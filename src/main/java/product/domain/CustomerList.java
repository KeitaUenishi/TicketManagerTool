package product.domain;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class CustomerList {

	/** ID */
	private Long id;

	/** 日付ID */
	@NotNull
	@Digits(integer = 8, fraction = 0)
	private Long dateId;

	/** 名前 */
	@NotBlank(message = "\u540d\u524d\u3092\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044")
	@Size(max = 50, message = "{error.maxSize}\u3067\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044")
	private String name;

	/** 枚数 */
	@NotNull(message = "\u53d6\u308a\u7f6e\u304d\u679a\u6570\u3092\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044")
	@Range( min = 1, max = 99, 
	message = "\uff11\u304b\u3089\uff19\uff19\u307e\u3067\u306e\u6570\u5024\u3092\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044")
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
