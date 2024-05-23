package fr.fms.apitrainings.dao;

import fr.fms.apitrainings.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
