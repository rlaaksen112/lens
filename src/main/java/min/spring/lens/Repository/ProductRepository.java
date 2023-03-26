package min.spring.lens.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import min.spring.lens.Model.Product;
import min.spring.lens.Model.Store;

public interface ProductRepository extends JpaRepository<Product, Integer > {
	Optional<Product> findByProductName(String productName);
	Optional<Product> findByProductNameAndStore(String productName,Store storeCode);

	List<Product> findByStoreLike(Store store);
}
