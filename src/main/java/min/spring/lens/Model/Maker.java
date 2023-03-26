package min.spring.lens.Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Maker {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lens_seq1")
	@SequenceGenerator(sequenceName = "lens_seq1", allocationSize = 1, name = "lens_seq1")
	private Integer makerCode;

	@Column(length = 30, unique = true)
	private String makerName;

	 @OneToMany(mappedBy = "maker")
	 private List<Product> products;
}

//--[Maker]
//---P-MakerCode 	integer					//메이커 코드
//---MakerName		varchar2(50)			//메이커 이름