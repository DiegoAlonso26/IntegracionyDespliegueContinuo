package upeu.edu.pe.lp.app.service;


import upeu.edu.pe.lp.app.repository.OrderRepository;
import upeu.edu.pe.lp.infrastructure.entity.OrderEntity;
import upeu.edu.pe.lp.infrastructure.entity.UserEntity;



public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    
      public OrderEntity createOrder(OrderEntity order){
          return orderRepository.createOrder(order);
      }
    public Iterable<OrderEntity> getOrders(){
        return orderRepository.getOrders();
        
    }
    public Iterable<OrderEntity> getOrdersByUser(UserEntity user){
        return orderRepository.getOrdersByUser(user);
    }

    public Long count(){
        Long countValue = orderRepository.count();
        if (countValue != null) {
            return countValue;
        } else {
            return 0L;
        }
    }

  
}
