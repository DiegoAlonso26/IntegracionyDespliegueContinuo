package upeu.edu.pe.lp.infrastructure.adapter;

import jakarta.persistence.Id;
import org.springframework.data.repository.CrudRepository;
import upeu.edu.pe.lp.infrastructure.entity.OrderEntity;
import upeu.edu.pe.lp.infrastructure.entity.UserEntity;

public interface OrderCrudRepository extends CrudRepository<OrderEntity, Integer> {
    Iterable<OrderEntity>findByUserEntity(UserEntity entity);
}
