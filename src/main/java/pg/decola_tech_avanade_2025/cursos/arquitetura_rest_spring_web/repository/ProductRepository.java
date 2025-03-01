package pg.decola_tech_avanade_2025.cursos.arquitetura_rest_spring_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pg.decola_tech_avanade_2025.cursos.arquitetura_rest_spring_web.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
