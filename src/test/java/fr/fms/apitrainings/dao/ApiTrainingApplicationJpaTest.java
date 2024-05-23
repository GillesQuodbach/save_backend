//package fr.fms.apitrainings.dao;
//
//import fr.fms.apitrainings.entities.*;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import org.junit.runner.RunWith;
//
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class ApiTrainingApplicationJpaTest {
//
//    @Autowired
//    TrainingRepository trainingRepository;
//    @Autowired
//    CategoryRepository categoryRepository;
//    @Autowired
//    CustomerRepository customerRepository;
//    @Autowired
//    OrderRepository orderRepository;
//    @Autowired
//    OrderItemRepository orderItemRepository;
//
//    @Test
//    public void createCategoryTest() {
//        //GIVEN
//        Category category = new Category(null, "Sport", null);
//
//        //WHEN
//        categoryRepository.save(category);
//
//        //THEN
//        assertTrue(categoryRepository.findById(category.getId()).isPresent());
//    }
//
//    @Test
//    public void createTrainingTest() {
//        //GIVEN
//        Training training = new Training(null, "Football", "Formation d'arbitrage", 150, 1, "foot.png", null);
//
//        //WHEN
//        trainingRepository.save(training);
//
//        //THEN
//        assertTrue(trainingRepository.findById(training.getId()).isPresent());
//    }
//
//    @Test
//    public void findTrainingByCategoryTest() {
//        // GIVEN
//        Category category = new Category(null, "Sport", null);
//        Training training1 = new Training(null, "Football", "Formation d'arbitrage", 150, 1, "foot.png", category);
//        Training training2 = new Training(null, "Tennis", "Formation au service", 200, 1, "tennis.png", category);
//
//        //WHEN
//        categoryRepository.save(category);
//        trainingRepository.save(training1);
//        trainingRepository.save(training2);
//        List<Training> trainings = trainingRepository.findByCategory(category);
//
//        //THEN
//        assertEquals(2, trainings.size());
//        assertEquals(category.getId(), trainings.get(0).getCategory().getId());
//        assertEquals(category.getId(), trainings.get(1).getCategory().getId());
//    }
//
//    @Test
//    public void createCustomerTest() {
//        //GIVEN
//        Customer customer = new Customer(null, "Arthur", "Gibert", "15 rue des rosiers", "0558523456", "arthur@free.fr", null);
//
//        //WHEN
//        customerRepository.save(customer);
//
//        //THEN
//        assertTrue(customerRepository.findById(customer.getId()).isPresent());
//    }
//
//    @Test
//    public void createOrderTest() {
//        //GIVEN
//        Order order = new Order(null, 1500, null);
//
//        //WHEN
//        orderRepository.save(order);
//
//        //THEN
//        assertTrue(orderRepository.findById(order.getId()).isPresent());
//    }
//
//    @Test
//    public void createOrderItemTest() {
//        //GIVEN
//        OrderItem orderItem = new OrderItem(null, 1, 25, null, null);
//
//        //WHEN
//        orderItemRepository.save(orderItem);
//
//        //THEN
//        assertTrue(orderItemRepository.findById(orderItem.getId()).isPresent());
//    }
//}
