package min.spring.lens.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import min.spring.lens.Model.Member;
import min.spring.lens.Model.Options;
import min.spring.lens.Model.Product;
import min.spring.lens.Model.Store;
import min.spring.lens.Repository.MemberRepository;
import min.spring.lens.Repository.OptionsRepository;
import min.spring.lens.Repository.ProductRepository;
import min.spring.lens.Repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class OptionsService {
	private final OptionsRepository optionsRepository;
	private final ProductRepository productRepository;
	private final MemberRepository memberRepository;
	private final StoreRepository storeRepository;




	public void create(Options options , String productName , Principal principal) {
		Options q = new Options();

		Optional<Member> mem = this.memberRepository.findByMemberId(principal.getName());

		Store store = this.storeRepository.findByStoreName(mem.get().getStore().getStoreName()).get();

		Optional<Product> qq = this.productRepository.findByProductNameAndStore(productName ,store);

		q.setProduct(qq.get());
		q.setProductPrice(options.getProductPrice());
		q.setProductOption(options.getProductOption());
		q.setStoreStock(options.getStoreStock());


		this.optionsRepository.save(q);
	}

	public Options getOptions(Integer Id) {
		this.optionsRepository.findById(Id).get();

		return this.optionsRepository.findById(Id).get();
	}

	   public void modify(Options options , String productName , Principal principal) {
		      Optional<Member> mem = this.memberRepository.findByMemberId(principal.getName());

		      Store store = this.storeRepository.findByStoreName(mem.get().getStore().getStoreName()).get();

		      Product qq = this.productRepository.findByProductNameAndStore(productName ,store).get();

		      Options optionsmodify = this.optionsRepository.findByProductAndProductOption(qq,options.getProductOption());

		      optionsmodify.setStoreStock(options.getStoreStock());

		      this.optionsRepository.save(optionsmodify);
		   }

	   public List<Options> getList() {
		   return this.optionsRepository.findAll();
	   }
}
