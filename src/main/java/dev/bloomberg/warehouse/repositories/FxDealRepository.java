package dev.bloomberg.warehouse.repositories;


import dev.bloomberg.warehouse.models.entities.FxDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FxDealRepository extends JpaRepository<FxDeal, Long> {

}
