/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package upeu.edu.pe.lp.app.repository;

import java.util.List;
import upeu.edu.pe.lp.infrastructure.entity.OrderDetailsEntity;
import upeu.edu.pe.lp.infrastructure.entity.OrderEntity;

public interface OrderDetailsRepository {
    public OrderDetailsEntity create (OrderDetailsEntity orderProduct);
    public Iterable<OrderDetailsEntity> getOrderProducts();
    public List<OrderDetailsEntity> getOrdersDetailsByOrder(OrderEntity orderEntity);
    
   
}
