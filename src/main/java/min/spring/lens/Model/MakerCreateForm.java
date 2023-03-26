package min.spring.lens.Model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MakerCreateForm {
	@NotEmpty(message = "메이커 이름은 필수사항 입니다.")
	@Size(max = 30)
	private String makerName;
}
