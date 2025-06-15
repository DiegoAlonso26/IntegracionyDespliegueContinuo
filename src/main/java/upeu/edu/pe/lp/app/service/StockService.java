package upeu.edu.pe.lp.app.service;

import upeu.edu.pe.lp.app.repository.StockRepository;
import upeu.edu.pe.lp.infrastructure.entity.ProductEntity;
import upeu.edu.pe.lp.infrastructure.entity.StockEntity;

import java.util.List;

public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<StockEntity> getStockByProductEntity(ProductEntity productEntity){
        return stockRepository.getStockByProductEntity(productEntity);
    }
    public StockEntity saveStock(StockEntity stockEntity) {
        return stockRepository.saveStock(stockEntity);
    }

}
