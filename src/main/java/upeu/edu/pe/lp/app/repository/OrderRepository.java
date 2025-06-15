package upeu.edu.pe.lp.app.repository;

import upeu.edu.pe.lp.infrastructure.entity.OrderEntity;
import upeu.edu.pe.lp.infrastructure.entity.UserEntity;


public interface OrderRepository {
    public OrderEntity createOrder(OrderEntity order);
    public Iterable<OrderEntity> getOrders();
    public Iterable<OrderEntity> getOrdersByUser(UserEntity user);
    Long count();

}
