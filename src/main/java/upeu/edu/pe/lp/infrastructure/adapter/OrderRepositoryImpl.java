package upeu.edu.pe.lp.infrastructure.adapter;

import org.springframework.stereotype.Repository;
import upeu.edu.pe.lp.app.repository.OrderRepository;
import upeu.edu.pe.lp.infrastructure.entity.OrderEntity;
import upeu.edu.pe.lp.infrastructure.entity.UserEntity;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderCrudRepository orderCrudRepository;

    public OrderRepositoryImpl(OrderCrudRepository orderCrudRepository) {
        this.orderCrudRepository = orderCrudRepository;
    }

    @Override
    public OrderEntity createOrder(OrderEntity order) {
        return orderCrudRepository.save(order);
    }

    @Override
    public Iterable<OrderEntity> getOrders() {
        return orderCrudRepository.findAll();
    }

    @Override
    public Iterable<OrderEntity> getOrdersByUser(UserEntity user) {
        return orderCrudRepository.findByUserEntity(user);
    }

    @Override
    public Long count() {
        return orderCrudRepository.count();
    }


}
