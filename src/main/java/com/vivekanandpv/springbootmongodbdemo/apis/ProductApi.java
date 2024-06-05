package com.vivekanandpv.springbootmongodbdemo.apis;

import com.vivekanandpv.springbootmongodbdemo.exceptions.RecordNotFoundException;
import com.vivekanandpv.springbootmongodbdemo.services.ProductService;
import com.vivekanandpv.springbootmongodbdemo.viewmodels.ProductCreateViewModel;
import com.vivekanandpv.springbootmongodbdemo.viewmodels.ProductUpdateViewModel;
import com.vivekanandpv.springbootmongodbdemo.viewmodels.ProductViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/products")
public class ProductApi {
    private final ProductService productService;

    public ProductApi(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductViewModel>> get() {
        return ResponseEntity.ok(productService.get());
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductViewModel> get(@PathVariable String id) {
        return ResponseEntity.ok(productService.get(id));
    }

    @PostMapping
    public ResponseEntity<ProductViewModel> create(@RequestBody ProductCreateViewModel viewModel) {
        return ResponseEntity.ok(productService.create(viewModel));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductViewModel> update(@PathVariable String id, @RequestBody ProductUpdateViewModel viewModel) {
        return ResponseEntity.ok(productService.update(id, viewModel));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRecordNotFoundException(RecordNotFoundException exception) {
        return ResponseEntity.badRequest().body(Map.of("error", exception.getMessage()));
    }
}
