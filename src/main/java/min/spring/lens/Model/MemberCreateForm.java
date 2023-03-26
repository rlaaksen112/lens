package min.spring.lens.Model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreateForm {

	@Size(min = 1,max = 30)
	@NotEmpty(message="사용자 아이디")
	private String memberId;

	@Size(min = 1,max = 30)
	@NotEmpty(message="패스워드")
	private String memberPw;

	@Size(min = 1,max = 30)
	@NotEmpty(message="패스워드 확인")
	private String memberPw2;

	@Size(min = 1,max = 30)
	@NotEmpty(message="사용자 이름")
	private String memberName;

	@Size(min = 1,max = 30)
	@NotEmpty(message="사용자 핸드폰 번호")
	private String memberPhone;

	@Size(min = 1,max = 70)
	@Email
	@NotEmpty(message="사용자 이메일")
	private String memberEmail;

	@Size(min = 1,max = 30)
	@NotEmpty(message="사용자 생년월일")
	private String memberBirth;

}

