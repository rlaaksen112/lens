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
public class Store {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lens_seq6")
	@SequenceGenerator(sequenceName = "lens_seq6", allocationSize = 1, name = "lens_seq6")
	private Integer storeCode;

	@Column(nullable = false, length = 30)
	private String storeName;

	@Column(unique = true, length = 30)
	private String storeTel;

	@OneToMany(mappedBy = "store")
	private List<Reservation> reservations;

	 @OneToMany(mappedBy = "store")
	 private List<Product> products;

	 @OneToMany(mappedBy = "store")
	 private List<Member> member;

	 @OneToMany(mappedBy = "store")
	 private List<Sales> salesList;
}

//--[Store]
//---P-StoreCode 	Integer					//매장 코드
//---StoreName	 	varchar2(50)   			//매장 이름
//---StoreTel			varchar2(50)			//매장 전화번호
