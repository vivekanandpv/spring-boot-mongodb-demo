package com.vivekanandpv.springbootmongodbdemo.services;

import com.vivekanandpv.springbootmongodbdemo.models.Product;
import com.vivekanandpv.springbootmongodbdemo.viewmodels.ProductCreateViewModel;
import com.vivekanandpv.springbootmongodbdemo.viewmodels.ProductUpdateViewModel;
import com.vivekanandpv.springbootmongodbdemo.viewmodels.ProductViewModel;

import java.util.List;

public interface ProductService {
    List<ProductViewModel> get();
    ProductViewModel get(String id);
    ProductViewModel create(ProductCreateViewModel viewModel);
    ProductViewModel update(String id, ProductUpdateViewModel viewModel);
    void delete(String id);
}
