package br.com.diegoplaninscheck.n3_seguranca_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.diegoplaninscheck.n3_seguranca_backend.Repository.ProductRepository;
import br.com.diegoplaninscheck.n3_seguranca_backend.model.Product;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product create(Product product) {
        return repository.save(product);
    }

    public List<Product> listAll() {
        return repository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    public Product update(Long id, Product newProduct) {
        return repository.findById(id).map(product -> {
            product.setName(newProduct.getName());
            product.setColor(newProduct.getColor());
            product.setSize(newProduct.getSize());
            return repository.save(product);
        }).orElseThrow(() -> new RuntimeException("Product not found!"));
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }
}
