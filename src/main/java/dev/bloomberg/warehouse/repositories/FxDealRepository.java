package dev.bloomberg.warehouse.repositories;


import dev.bloomberg.warehouse.models.entities.FxDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link FxDeal} entity repository, serves as main persistence provider
 */
@Repository
public interface FxDealRepository extends JpaRepository<FxDeal, Long> {

}
