package min.spring.lens.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import min.spring.lens.Model.Maker;

public interface MakerRepository extends JpaRepository<Maker, Integer >{
	Optional<Maker> findByMakerName(String makerName);
}
