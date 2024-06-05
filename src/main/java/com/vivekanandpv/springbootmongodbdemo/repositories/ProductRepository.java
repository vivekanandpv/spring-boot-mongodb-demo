package com.vivekanandpv.springbootmongodbdemo.repositories;

import com.vivekanandpv.springbootmongodbdemo.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
