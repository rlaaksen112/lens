package min.spring.lens.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lens_seq7")
    @SequenceGenerator(sequenceName = "lens_seq7", allocationSize = 1, name = "lens_seq7")
    private Integer saleCode;

    @Column(length = 30, nullable = false)
    private String productName;

    @Column(length = 30, nullable = false)
    private Integer sale;

    @Column(length = 30, nullable = false)
    private String productOption;

    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "STORE_CODE")
    private Store store;

}

//    Sales
//    SalesCode
//    Store (Fk)		가게명
//    productName	제품명
//    sale			판매갯수
//    create_date	판매 완료 시간