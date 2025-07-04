package br.com.diegoplaninscheck.n3_seguranca_backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diegoplaninscheck.n3_seguranca_backend.model.Product;

@RestController
@RequestMapping("/products")
public class ProductController {
    private List<Product> products = new ArrayList<>();

    public void insertProducts() {
        Product product = new Product("Lapis", "Branco", "Normal");
        Product product2 = new Product("Lapis", "Preto", "Normal");
        Product product3 = new Product("Lapis", "Vermelho",
                "Normal");

        products.add(product);
        products.add(product2);
        products.add(product3);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER_GET') || hasRole('admin')")
    public ResponseEntity<List<Product>> list() {
        insertProducts();

        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER_CREATE') || hasRole('admin')")
    public String create() {
        return "Produto criado";
    }
}
