package br.com.diegoplaninscheck.n3_seguranca_backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    @PreAuthorize("hasRole('USER_GET') || hasRole('admin')")
    public String list() {
        return "Listando produtos";
    }

    @PostMapping
    @PreAuthorize("hasRole('USER_CREATE') || hasRole('admin')")
    public String create() {
        return "Produto criado";
    }
}
