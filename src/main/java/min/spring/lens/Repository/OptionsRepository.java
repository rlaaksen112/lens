package min.spring.lens.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import min.spring.lens.Model.Options;
import min.spring.lens.Model.Product;


public interface OptionsRepository extends JpaRepository<Options, Integer > {
	Options findByProductAndProductOptionAndProductPrice(Product productName,
			String productOption,Integer productPrice);
	Options findByProductAndProductOption(Product productName,String productOption);
}