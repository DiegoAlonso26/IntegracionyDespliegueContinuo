 package upeu.edu.pe.lp.infrastructure.adapter;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import upeu.edu.pe.lp.app.repository.ProductRepository;
import upeu.edu.pe.lp.infrastructure.entity.ProductEntity;
import upeu.edu.pe.lp.infrastructure.entity.UserEntity;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductCrudRepository productCrudRepository;

    public ProductRepositoryImpl(ProductCrudRepository productCrudRepository) {
        this.productCrudRepository = productCrudRepository;
    }

    @Override
    public Iterable<ProductEntity> getProducts() {
        return productCrudRepository.findAll();
    }

    @Override
    public Iterable<ProductEntity> getProductsByUser(UserEntity user) {
        return productCrudRepository.findByUserEntity(user);
    }

    @Override
    public ProductEntity getProductById(Integer id) {
        return productCrudRepository.findById(id).get();
    }

    @Override
    public ProductEntity saveProduct(ProductEntity product) {
        return productCrudRepository.save(product);
    }

    @Override
    public void deleteProductById(Integer id) {
        productCrudRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return productCrudRepository.count();
    }

}
