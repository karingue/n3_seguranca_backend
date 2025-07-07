package br.com.diegoplaninscheck.n3_seguranca_backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diegoplaninscheck.n3_seguranca_backend.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
