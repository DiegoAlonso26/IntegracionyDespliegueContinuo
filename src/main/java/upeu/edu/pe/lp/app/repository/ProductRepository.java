package upeu.edu.pe.lp.app.repository;

import java.util.Optional;
import upeu.edu.pe.lp.infrastructure.entity.ProductEntity;
import upeu.edu.pe.lp.infrastructure.entity.UserEntity;

    public interface ProductRepository {
    //Lista de todos los productos
    Iterable<ProductEntity> getProducts();
    //Lista de productos por usuario
    Iterable<ProductEntity> getProductsByUser(UserEntity user);
    ProductEntity getProductById(Integer id);
    ProductEntity saveProduct(ProductEntity product);
    void deleteProductById(Integer id);
    Long count();
}
