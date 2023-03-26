package min.spring.lens.Model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
public class Options {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lens_seq2")
    @SequenceGenerator(sequenceName = "lens_seq2", allocationSize = 1, name = "lens_seq2")
    private Integer optionCode;

    @Column(length = 30, nullable = false)
    private Integer productPrice;

    @Column(length = 30, nullable = false)
    private String productOption;

    @Column(length = 30)
    private Integer storeStock;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_CODE")
    private Product product;

}
//--[Option]
//—-P-OptionCode		Integer				//옵션 코드
//---F-ProductCode 		Integer				//제품 코드
//---ProductPrice 		Integer				//제품 가격
//---ProductOp 			Integer				//제품 도수 (-4,5) == > -4.5
