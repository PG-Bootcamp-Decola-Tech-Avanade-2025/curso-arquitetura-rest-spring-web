package pg.decola_tech_avanade_2025.cursos.arquitetura_rest_spring_web.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pg.decola_tech_avanade_2025.cursos.arquitetura_rest_spring_web.exceptions.CompositeValidationException;
import pg.decola_tech_avanade_2025.cursos.arquitetura_rest_spring_web.model.Product;
import pg.decola_tech_avanade_2025.cursos.arquitetura_rest_spring_web.repository.ProductRepository;

import java.net.URI;
import java.util.LinkedList;
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
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        return ResponseEntity.ok(product.orElseThrow(() -> new EntityNotFoundException(String.format("Produto com ID %d não foi encontrado.", id))));
    }

    @PostMapping("")
    public ResponseEntity<URI> create(@RequestBody Product newProduct) {
        List<String> exceptions = new LinkedList<>();
        if(newProduct.getName() == null || newProduct.getName().isBlank()) exceptions.add("Nome não deve ser nulo ou vazio.");
        if(newProduct.getPrice() == null) exceptions.add("Preco não deve ser nulo.");
        if(newProduct.getDescription() == null ||newProduct.getDescription().isBlank()) exceptions.add("Descrição não deve ser nula ou vazia.");
        if(!exceptions.isEmpty()) throw new CompositeValidationException(exceptions);

        newProduct = productRepository.save(newProduct);
        return ResponseEntity.created(URI.create(String.format("/products/%d", newProduct.getId()))).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Product newProduct) {
        Optional<Product> product = productRepository.findById(id);
        Product persistedProduct = product.orElseThrow(() -> new EntityNotFoundException(String.format("Produto com ID %d não foi encontrado.", id)));

        List<String> exceptions = new LinkedList<>();
        if(newProduct.getName().isBlank()) exceptions.add("Nome não deve ser nulo ou vazio.");
        if(newProduct.getPrice() == null) exceptions.add("Preco não deve ser nulo.");
        if(newProduct.getDescription().isBlank()) exceptions.add("Descrição não deve ser nula ou vazia.");
        if(!exceptions.isEmpty()) throw new CompositeValidationException(exceptions);

        persistedProduct.setName(newProduct.getName());
        persistedProduct.setPrice(newProduct.getPrice());
        persistedProduct.setDescription(newProduct.getDescription());
        productRepository.save(persistedProduct);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        productRepository.findById(id)
                .orElseThrow(
                    () -> new EntityNotFoundException(String.format(
                            "Produto com ID %d não foi encontrado.",
                            id
                    ))
                );

        productRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
