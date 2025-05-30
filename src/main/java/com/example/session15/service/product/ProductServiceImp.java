package com.example.session15.service.product;

import com.example.session15.model.Product;
import com.example.session15.repsitory.product.ProductRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepositoryImp productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.getProducts();
    }
}
