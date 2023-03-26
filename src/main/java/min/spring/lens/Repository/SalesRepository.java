package min.spring.lens.Repository;


import min.spring.lens.Model.Sales;
import min.spring.lens.Model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SalesRepository extends JpaRepository<Sales,Integer>{

    List<Sales> findByStoreLike(Store store);
}
