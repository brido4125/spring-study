package sample.cafekiosk.spring.api.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.cafekiosk.spring.api.service.product.reponse.ProductResponse;
import sample.cafekiosk.spring.api.service.product.request.ProductCreateServiceRequest;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;

import java.util.List;
import java.util.stream.Collectors;


/*
* readOnly = true : 읽기전용
* CRUD에서 CUD 동작 X / only Read
* JPA : CUD 스냅샷 저장, 변경 감지 X (성능 향상)
* CQRS - Command / Query(Read)를 분리
* */
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public ProductResponse createProduct(ProductCreateServiceRequest request) {
        String latestProductNumber = createNextProductNumber();
        Product product = request.toEntity(latestProductNumber);
        Product save = productRepository.save(product);
        return ProductResponse.of(save);
    }

    public List<ProductResponse> getSellingProducts() {
        return productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay())
            .stream()
            .map(ProductResponse::of).collect(Collectors.toList());
    }

    private String createNextProductNumber() {
        String latestProductNumber = productRepository.findLatestProductNumber();
        if (latestProductNumber == null) {
            return "001";
        }
        int latestInt = Integer.valueOf(latestProductNumber);
        int nextInt = latestInt + 1;
        return String.format("%03d", nextInt);
    }
}
