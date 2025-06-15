package upeu.edu.pe.lp.infrastructure.adapter;

import org.springframework.stereotype.Repository;
import upeu.edu.pe.lp.app.repository.StockRepository;
import upeu.edu.pe.lp.infrastructure.entity.ProductEntity;
import upeu.edu.pe.lp.infrastructure.entity.StockEntity;

import java.util.List;

@Repository
public class StockRepositoryImpl implements StockRepository {
    private final StockCrudRepository stockCrudRepository;

    public StockRepositoryImpl(StockCrudRepository stockCrudRepository) {
        this.stockCrudRepository = stockCrudRepository;
    }

    @Override
    public StockEntity saveStock(StockEntity stockEntity) {
        return stockCrudRepository.save(stockEntity);
    }

    @Override
    public List<StockEntity> getStockByProductEntity(ProductEntity productEntity) {
        return stockCrudRepository.getStockByProductEntity(productEntity);
    }
}
