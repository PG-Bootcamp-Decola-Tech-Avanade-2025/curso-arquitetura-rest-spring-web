package pg.decola_tech_avanade_2025.cursos.arquitetura_rest_spring_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pg.decola_tech_avanade_2025.cursos.arquitetura_rest_spring_web.model.Product;
import pg.decola_tech_avanade_2025.cursos.arquitetura_rest_spring_web.repository.ProductRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/*
    Na implementação dessa classe, optei por divergir da implementação demonstrada.
    Os endereços dos enpoints foram alterados para acatar ao conceito de recursos do REST,
    disponibilizando operações de acordo com o método HTTP aplicado sobre cada recurso.
*/

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    public ProductRepository productRepository;

    @GetMapping("")
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product);
    }

    @PostMapping("")
    public ResponseEntity<URI> create(@RequestBody Product newProduct) {
        newProduct = productRepository.save(newProduct);
        URI newProductLocation = URI.create(String.format("/products/%d", newProduct.getId()));
        return ResponseEntity.created(newProductLocation).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Product newProduct) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Product persistedProduct = product.get();

        persistedProduct.setName(newProduct.getName());
        persistedProduct.setPrice(newProduct.getPrice());
        persistedProduct.setDescription(newProduct.getDescription());

        productRepository.save(persistedProduct);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
