package com.ivanpanussis.supermarket.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ivanpanussis.supermarket.model.Product;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testSaveAndFindById() {
        Product p = Product.builder()
                .name("Apple")
                .category("Fruit")
                .price(2.5)
                .stock(10)
                .build();

        Product saved = productRepository.save(p);

        Optional<Product> found = productRepository.findById(saved.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Apple");
    }

    @Test
    void testFindAll() {
        Product p1 = Product.builder().name("Banana").category("Fruit").price(1.0).stock(5).build();
        Product p2 = Product.builder().name("Carrot").category("Vegetable").price(0.5).stock(20).build();

        productRepository.save(p1);
        productRepository.save(p2);

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(2);
        assertThat(products).extracting("name").containsExactlyInAnyOrder("Banana", "Carrot");
    }

    @Test
    void testDelete() {
        Product p = Product.builder().name("Orange").category("Fruit").price(1.5).stock(15).build();
        Product saved = productRepository.save(p);

        productRepository.deleteById(saved.getId());

        Optional<Product> found = productRepository.findById(saved.getId());
        assertThat(found).isNotPresent();
    }

    @Test
    void testExistsById() {
        Product p = Product.builder().name("Grapes").category("Fruit").price(3.0).stock(8).build();
        Product saved = productRepository.save(p);

        boolean exists = productRepository.existsById(saved.getId());
        assertThat(exists).isTrue();
    }
}
