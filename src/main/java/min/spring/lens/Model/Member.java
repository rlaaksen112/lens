package min.spring.lens.Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "난 이걸로 쓸게 ")
	// @SequenceGenerator(sequenceName = "오라클에 생성할 시퀀스 이름", allocationSize = 1, name
	// = "난 이걸로 쓸게 ")

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lens_seq")
	@SequenceGenerator(sequenceName = "lens_seq", allocationSize = 1, name = "lens_seq")
	private Integer memberCode;

	@Column(length = 30, unique = true)
	private String memberId;

	@Column(length = 60)
	private String memberPw;

	@Column(length = 30)
	private String memberName;

	@Column(length = 30, unique = true)
	private String memberPhone;

	@Column(length = 320, unique = true)
	private String memberEmail;

	@Column(length = 30, unique = true)
	private String memberBirth;

	@OneToMany(mappedBy = "member")
	private List<Reservation> reservations;

	@ManyToOne
	@JoinColumn(name = "STORE_CODE")
	private Store store;
}

//—P-MemberCode	Integer						//회원 코드
//---MemberId		varchar2(30)				//회원 아이디
//---MemberPw		varchar2(20)				//회원 비밀번호
//---MemberName 	varchar2(30)			//회원 이름
//---MemberPhone 	varchar2(20)			//회원 핸드폰 번호
//---MemberEmail	varchar2(320) 				//회원 이메일
//---MemberBirth	date 							//회원 생년월일
