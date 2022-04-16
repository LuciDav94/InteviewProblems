package vacuumlabs.trademark.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vacuumlabs.trademark.model.Trademark;

import java.util.List;

@Repository
public interface TrademarkRepository extends JpaRepository<Trademark, Long>, JpaSpecificationExecutor<Trademark> {

    Trademark findByWordMarkSpecification(String name);

    List<Trademark> findByWordMarkSpecificationContainingIgnoreCase(String name);
}


