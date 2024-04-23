package com.xtech.stockmanagement.productservice.controller;

import com.xtech.stockmanagement.productservice.repository.entity.Product;
import com.xtech.stockmanagement.productservice.request.ProductCreateRequest;
import com.xtech.stockmanagement.productservice.response.FriendlyMessage;
import com.xtech.stockmanagement.productservice.response.InternalApiResponse;
import com.xtech.stockmanagement.productservice.response.ProductResponse;
import com.xtech.stockmanagement.productservice.service.IProductRepositoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/1.0/product")
@RequiredArgsConstructor

public class ProductController {
    private final IProductRepositoryService productRepositoryService;

    @Operation(summary = "Create a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)

    public InternalApiResponse<ProductResponse> createProduct(@RequestBody ProductCreateRequest productCreateRequest) {
        Product product = productRepositoryService.createProduct(productCreateRequest);

        ProductResponse productResponse = convertProductResponse(product);
        return InternalApiResponse.<ProductResponse>builder()
                .httpStatus(HttpStatus.CREATED)
                .hasError(false)
                .payload(productResponse).build();
    }

    @Operation(summary = "Get Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @PostMapping("/list")
    @ResponseStatus(HttpStatus.OK)

    public InternalApiResponse<List<ProductResponse>> getProducts() {
      List<Product> products = productRepositoryService.getProducts();
      List<ProductResponse> productResponses = convertProductResponseList(products);

        return InternalApiResponse.<List<ProductResponse>>builder()
                .httpStatus(HttpStatus.CREATED)
                .hasError(false)
                .payload(productResponses).build();
    }

    private List<ProductResponse> convertProductResponseList(List<Product> productList){
        return productList.stream()
                .map(arg -> ProductResponse.builder()
                        .productId(arg.getProductId())
                        .productName(arg.getProductName())
                        .quantity(arg.getQuantity())
                        .price(arg.getPrice())
                        .productCreatedDate(arg.getProductCreatedDate().getTime())
                        .productUpdatedDate(arg.getProductUpdatedDate().getTime())
                        .build())
                .collect(Collectors.toList());


    }
    private static ProductResponse convertProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .productCreatedDate(product.getProductCreatedDate().getTime())
                .productUpdatedDate(product.getProductUpdatedDate().getTime())
                .build();
    }
}
