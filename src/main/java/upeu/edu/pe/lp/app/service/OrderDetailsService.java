/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package upeu.edu.pe.lp.app.service;

import java.util.List;
import upeu.edu.pe.lp.app.repository.OrderDetailsRepository;
import upeu.edu.pe.lp.infrastructure.entity.OrderDetailsEntity;
import upeu.edu.pe.lp.infrastructure.entity.OrderEntity;

/**
 *
 * @author DIEGO
 */
public class OrderDetailsService {
    
    private final OrderDetailsRepository orderDetailsRepository;

    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }
    
    public OrderDetailsEntity create (OrderDetailsEntity orderProduct){
         return orderDetailsRepository.create(orderProduct);
     }
    public Iterable<OrderDetailsEntity> getOrderProducts(){
        return orderDetailsRepository.getOrderProducts();
        
    }
    public List<OrderDetailsEntity> getOrdersProductByOrder(OrderEntity orderEntity){
        return orderDetailsRepository.getOrdersDetailsByOrder(orderEntity);
    } 
    

   

    
}
