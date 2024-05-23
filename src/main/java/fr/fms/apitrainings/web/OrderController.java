package fr.fms.apitrainings.web;

import fr.fms.apitrainings.business.IBusinessImpl;
import fr.fms.apitrainings.entities.Customer;
import fr.fms.apitrainings.entities.Order;
import fr.fms.apitrainings.entities.OrderItem;
import fr.fms.apitrainings.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@CrossOrigin("*")
/*@CrossOrigin("http://localhost:4200/")*/
@RestController
/*@RequestMapping("/api")*/
public class OrderController {

    @Autowired
    private IBusinessImpl iBusiness;

    @PostMapping("/customers")
    public ResponseEntity<Customer> savCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = iBusiness.saveCustomer(customer);
        if (Objects.isNull(savedCustomer)) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCustomer.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedCustomer);
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable("id") Long id) {
        return iBusiness.getCustomerById(id)
                .orElseThrow(() -> new RecordNotFoundException("Id de client " + id + " n'éxiste pas"));
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> savCustomer(@RequestBody Order order) {
        Order savedOrder = iBusiness.saveOrder(order);
        if (Objects.isNull(savedOrder)) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedOrder.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedOrder);
    }

    @PostMapping("/ordersitems")
    public ResponseEntity<OrderItem> savCustomer(@RequestBody OrderItem orderItem) {
        OrderItem savedOrderItem = iBusiness.saveOrderItem(orderItem);
        if (Objects.isNull(savedOrderItem)) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedOrderItem.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
