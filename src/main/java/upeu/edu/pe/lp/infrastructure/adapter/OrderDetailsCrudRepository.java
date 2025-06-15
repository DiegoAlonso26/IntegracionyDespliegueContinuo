/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package upeu.edu.pe.lp.infrastructure.adapter;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import upeu.edu.pe.lp.infrastructure.entity.OrderDetailsEntity;
import upeu.edu.pe.lp.infrastructure.entity.OrderEntity;

/**
 *
 * @author DIEGO
 */
public interface OrderDetailsCrudRepository extends CrudRepository<OrderDetailsEntity, Integer>{
    public List<OrderDetailsEntity> findByOrderEntity(OrderEntity orderEntity);
}
