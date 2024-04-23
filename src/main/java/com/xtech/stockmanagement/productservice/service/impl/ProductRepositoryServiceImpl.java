package com.xtech.stockmanagement.productservice.service.impl;

import com.xtech.stockmanagement.productservice.repository.ProductRepository;
import com.xtech.stockmanagement.productservice.repository.entity.Product;
import com.xtech.stockmanagement.productservice.request.ProductCreateRequest;
import com.xtech.stockmanagement.productservice.request.ProductUpdateRequest;
import com.xtech.stockmanagement.productservice.service.IProductRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductRepositoryServiceImpl implements IProductRepositoryService {
    private final ProductRepository productRepository;


    @Override
    public Product createProduct(ProductCreateRequest productCreateRequest) {
        try{
            Product product = Product.builder()
                    .productName(productCreateRequest.getProductName())
                    .quantity(productCreateRequest.getQuantity())
                    .price(productCreateRequest.getPrice())
                    .deleted(false)
                    .build();
            Product productResponse = productRepository.save(product);
            return productResponse;
        }
        catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public Product getProduct(Long productId) {
        return null;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = productRepository.getAllByDeletedFalse();

        if(products.isEmpty()){
            throw new RuntimeException();
        }
        return products;
    }

    @Override
    public Product updateProduct(Long productId, ProductUpdateRequest productUpdateRequest) {
        return null;
    }

    @Override
    public Product deleteProduct(Long productId) {
        return null;
    }
}
