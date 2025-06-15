package upeu.edu.pe.lp.app.service;

import org.slf4j.*;
import org.springframework.web.multipart.MultipartFile;
import upeu.edu.pe.lp.app.repository.ProductRepository;
import upeu.edu.pe.lp.infrastructure.entity.ProductEntity;
import upeu.edu.pe.lp.infrastructure.entity.UserEntity;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;


public class ProductService {
    private final ProductRepository productRepository;
    private final UploadFile uploadFile;
    private Logger log = LoggerFactory.getLogger(ProductService.class);

    public ProductService(ProductRepository productRepository, UploadFile uploadFile) {
        this.productRepository = productRepository;
        this.uploadFile = uploadFile;
    }

    public Iterable<ProductEntity> getProducts(){
        return productRepository.getProducts();
    }
    public Iterable<ProductEntity> getProductsByUser(UserEntity user){
        return productRepository.getProductsByUser(user);
    }
    
     public  ProductEntity getProductByid(Integer id){
         
         return productRepository.getProductById(id);
     }

    
    public ProductEntity getProductById(Integer id){
        return productRepository.getProductById(id);
    }
    
    public ProductEntity saveProduct(ProductEntity product, MultipartFile multipartFile) throws IOException {
        if (product.getId() == null) {
            UserEntity user = new UserEntity();
            user.setId(1);
            product.setDataCreated(LocalDateTime.now());
            product.setDataUpdated(LocalDateTime.now());
            product.setUserEntity(user);
            product.setImage(uploadFile.upload(multipartFile));
            return productRepository.saveProduct(product);
        } else {
            ProductEntity productDB = productRepository.getProductById(product.getId());
            log.info("product {}", productDB);

            //actualizar la imagen del producto
            if (multipartFile.isEmpty()) {
                product.setImage(productDB.getImage());
            } else {
                if (!productDB.getImage().equals("default.jpg")) {
                    uploadFile.delete(productDB.getImage());
                }
                product.setImage(uploadFile.upload(multipartFile));
            }

            product.setCode(productDB.getCode());
            product.setUserEntity(productDB.getUserEntity());
            product.setDataCreated(productDB.getDataCreated());
            product.setDataUpdated(LocalDateTime.now());
            return productRepository.saveProduct(product);
        }
    }


        public void deleteProductById(Integer id) {
            ProductEntity p = new ProductEntity();
            p = productRepository.getProductById(id);

            if (!p.getImage().equals("default.jpg")) {
                uploadFile.delete(p.getImage());
            }

            productRepository.deleteProductById(id);
        }

    public ProductEntity saveProductApi(ProductEntity product) throws IOException {
        if (product.getId() == null) {
            UserEntity user = new UserEntity();
            user.setId(1);
            product.setDataCreated(LocalDateTime.now());
            product.setDataCreated(LocalDateTime.now());
            product.setUserEntity(user);
            return productRepository.saveProduct(product);
        } else {
            ProductEntity productDB = productRepository.getProductById(product.getId());
            log.info("product: {}", productDB);
            //sino se carga la imagen toma la que se le guardo al registro

            product.setCode(productDB.getCode());
            product.setUserEntity(productDB.getUserEntity());
            product.setDataCreated(productDB.getDataCreated());
            product.setDataUpdated(LocalDateTime.now());
            return productRepository.saveProduct(product);
        }

    }

    public Long countTotalProducts(){
        Long countValue = productRepository.count();
        if (countValue != null) {
            return countValue;
        } else {
            return 0L;
        }
    }

}
