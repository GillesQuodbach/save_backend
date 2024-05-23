package fr.fms.apitrainings.dao;

import fr.fms.apitrainings.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
