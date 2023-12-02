package sel.prac.springboot.SalesApp.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sel.prac.springboot.SalesApp.Model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
