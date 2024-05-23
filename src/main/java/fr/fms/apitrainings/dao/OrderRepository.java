package fr.fms.apitrainings.dao;

import fr.fms.apitrainings.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
