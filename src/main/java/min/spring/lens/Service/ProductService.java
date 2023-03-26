package min.spring.lens.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import min.spring.lens.Model.Member;
import min.spring.lens.Repository.MemberRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import min.spring.lens.Model.Maker;
import min.spring.lens.Model.Product;
import min.spring.lens.Model.Store;
import min.spring.lens.Repository.MakerRepository;
import min.spring.lens.Repository.ProductRepository;
import min.spring.lens.Repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;
	private final MakerRepository makerRepository;
	private final StoreRepository storeRepository;
	private final MemberRepository memberRepository;

	public void create(String storeName, String makerName,Product product) {
		Product q = new Product();
		Optional<Store> qq = this.storeRepository.findByStoreName(storeName);
		Optional<Maker> qqq = this.makerRepository.findByMakerName(makerName);

		q.setStore(qq.get());
		q.setMaker(qqq.get());
		q.setProductName(product.getProductName());

		this.productRepository.save(q);
	}

	public Product getProduct(Integer Id) {

		this.productRepository.findById(Id).get();

		return this.productRepository.findById(Id).get();
	}

	public List<Product> getList(){

		return this.productRepository.findAll();
	}

	public List<Product> getList2(Principal principal){
		Optional<Member> a = this.memberRepository.findByMemberId(principal.getName());
		Store store = this.storeRepository.findByStoreName(a.get().getStore().getStoreName()).get();

		return	this.productRepository.findByStoreLike(store);
	}

}
