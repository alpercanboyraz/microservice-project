package com.xtech.stockmanagement.productservice.service;

import com.xtech.stockmanagement.productservice.repository.entity.Product;
import com.xtech.stockmanagement.productservice.request.ProductCreateRequest;
import com.xtech.stockmanagement.productservice.request.ProductUpdateRequest;
import java.util.List;

public interface IProductRepositoryService {

    Product createProduct(ProductCreateRequest product);
    Product getProduct(Long productId);
    List<Product> getProducts();
    Product updateProduct(Long productId, ProductUpdateRequest productUpdateRequest);
    Product deleteProduct(Long productId);
}
