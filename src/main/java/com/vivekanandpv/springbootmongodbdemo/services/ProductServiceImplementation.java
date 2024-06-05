package com.vivekanandpv.springbootmongodbdemo.services;

import com.vivekanandpv.springbootmongodbdemo.exceptions.RecordNotFoundException;
import com.vivekanandpv.springbootmongodbdemo.models.Product;
import com.vivekanandpv.springbootmongodbdemo.repositories.ProductRepository;
import com.vivekanandpv.springbootmongodbdemo.viewmodels.ProductCreateViewModel;
import com.vivekanandpv.springbootmongodbdemo.viewmodels.ProductUpdateViewModel;
import com.vivekanandpv.springbootmongodbdemo.viewmodels.ProductViewModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplementation implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImplementation(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductViewModel> get() {
        return productRepository
                .findAll()
                .stream()
                .map(this::toViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public ProductViewModel get(String id) {
        return toViewModel(getEntityById(id));
    }

    @Override
    public ProductViewModel create(ProductCreateViewModel viewModel) {
        return toViewModel(productRepository.save(toEntity(viewModel)));
    }

    @Override
    public ProductViewModel update(String id, ProductUpdateViewModel viewModel) {
        Product entity = getEntityById(id);
        BeanUtils.copyProperties(viewModel, entity);
        return toViewModel(productRepository.save(entity));
    }

    @Override
    public void delete(String id) {
        productRepository.delete(getEntityById(id));
    }

    private ProductViewModel toViewModel(Product entity) {
        ProductViewModel viewModel = new ProductViewModel();
        BeanUtils.copyProperties(entity, viewModel);
        return viewModel;
    }

    private Product toEntity(ProductCreateViewModel viewModel) {
        Product entity = new Product();
        BeanUtils.copyProperties(viewModel, entity);
        return entity;
    }

    private Product getEntityById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Could not find the product %s", id)));
    }
}
