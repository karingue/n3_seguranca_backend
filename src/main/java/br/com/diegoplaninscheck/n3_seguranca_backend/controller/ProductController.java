package br.com.diegoplaninscheck.n3_seguranca_backend.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diegoplaninscheck.n3_seguranca_backend.model.Product;
import br.com.diegoplaninscheck.n3_seguranca_backend.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('USER_CREATE') || hasRole('admin')")
    @Operation(summary = "Create new Product")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        System.out.println(product.getName());
        System.out.println(product.getColor());
        System.out.println(product.getSize());
        return ResponseEntity.ok(service.create(product));
    }

    @GetMapping
    @PreAuthorize("hasRole('USER_GET') || hasRole('admin')")
    @Operation(summary = "List all Products")
    public ResponseEntity<List<Product>> listar() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER_GET') || hasRole('admin')")
    @Operation(summary = "Find Product by ID")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER_UPDATE') || hasRole('admin')")
    @Operation(summary = "Update Product by ID")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        try {
            return ResponseEntity.ok(service.update(id, product));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER_DELETE') || hasRole('admin')")
    @Operation(summary = "Delete Product by ID")
    public ResponseEntity<List<Product>> remove(@PathVariable Long id) {
        service.remove(id);
        return ResponseEntity.ok(service.listAll());
    }
}
