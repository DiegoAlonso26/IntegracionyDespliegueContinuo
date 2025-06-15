package upeu.edu.pe.lp.infrastructure.adapter;

import org.springframework.data.repository.CrudRepository;
import upeu.edu.pe.lp.infrastructure.entity.ProductEntity;
import upeu.edu.pe.lp.infrastructure.entity.StockEntity;

import java.util.List;

public interface StockCrudRepository extends CrudRepository<StockEntity, Integer> {

    List<StockEntity> getStockByProductEntity(ProductEntity productEntity);
}
