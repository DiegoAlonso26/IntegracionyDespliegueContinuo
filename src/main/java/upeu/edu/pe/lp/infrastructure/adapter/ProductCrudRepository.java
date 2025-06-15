package upeu.edu.pe.lp.infrastructure.adapter;

import org.springframework.data.repository.CrudRepository;
import upeu.edu.pe.lp.infrastructure.entity.ProductEntity;
import upeu.edu.pe.lp.infrastructure.entity.UserEntity;

public interface ProductCrudRepository extends CrudRepository<ProductEntity, Integer> {
    Iterable<ProductEntity> findByUserEntity(UserEntity entity);
}
