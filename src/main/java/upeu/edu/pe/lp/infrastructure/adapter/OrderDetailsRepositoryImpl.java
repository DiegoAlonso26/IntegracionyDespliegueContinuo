/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package upeu.edu.pe.lp.infrastructure.adapter;

import java.util.List;
import org.springframework.stereotype.Repository;
import upeu.edu.pe.lp.app.repository.OrderDetailsRepository;
import upeu.edu.pe.lp.infrastructure.entity.OrderDetailsEntity;
import upeu.edu.pe.lp.infrastructure.entity.OrderEntity;

/**
 *
 * @author DIEGO
 */
@Repository
public class OrderDetailsRepositoryImpl implements OrderDetailsRepository {
    
    private final OrderDetailsCrudRepository orderDetailsCrudRepository;

    public OrderDetailsRepositoryImpl(OrderDetailsCrudRepository orderDetailsCrudRepository) {
        this.orderDetailsCrudRepository = orderDetailsCrudRepository;
    }

    @Override
    public OrderDetailsEntity create(OrderDetailsEntity orderProduct) {
        return orderDetailsCrudRepository.save(orderProduct);
    }

    @Override
    public Iterable<OrderDetailsEntity> getOrderProducts() {

        return orderDetailsCrudRepository.findAll();
    }

    @Override
    public List<OrderDetailsEntity> getOrdersDetailsByOrder(OrderEntity orderEntity) {
       return orderDetailsCrudRepository.findByOrderEntity(orderEntity);
    }

    
    

    
}
