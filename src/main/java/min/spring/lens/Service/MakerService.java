package min.spring.lens.Service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import min.spring.lens.Model.Maker;
import min.spring.lens.Repository.MakerRepository;

@Service
@RequiredArgsConstructor
public class MakerService {

	private final MakerRepository makerRepository;

	public void create(String makerName) {
		Maker q = new Maker();
		q.setMakerName(makerName);

		this.makerRepository.save(q);
	}

	public Maker getMaker(Integer Id) {

		this.makerRepository.findById(Id).get();

		return this.makerRepository.findById(Id).get();

	}

//	public Maker getMakerName(String makerName) {
//
//		this.makerRepository.findByMakername(makerName);
//
//		return this.makerRepository.findByMakername(makerName);
//
//	}

}
