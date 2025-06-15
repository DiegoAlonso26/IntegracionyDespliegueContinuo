package upeu.edu.pe.lp.infrastructure.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import upeu.edu.pe.lp.app.service.ProductService;
import upeu.edu.pe.lp.infrastructure.entity.ProductEntity;
import upeu.edu.pe.lp.infrastructure.entity.UserEntity;

import java.io.IOException;


@CrossOrigin(origins = "http://localhost:4200")
/*@RestController*/
@RequestMapping("api")
public class ProductControllerApi {

    private final ProductService productService;

    public ProductControllerApi(ProductService productService) {
        this.productService = productService;
    }

    //crear product
    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductEntity saveProduct(@RequestBody ProductEntity productEntity) throws IOException {
        // return productService.saveProduct(productEntity).toString();
        return productService.saveProductApi(productEntity);
    }

    //ver productos
    @GetMapping("/product")
    public Iterable<ProductEntity> showProduct() {
        UserEntity user = new UserEntity();
        user.setId(1);
        return productService.getProductsByUser(user);
    }

    //buscar producto por Id
    @GetMapping("/product/{id}")
    public ProductEntity show(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    //editar un product
    @PutMapping("/product/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductEntity editProduct(@RequestBody ProductEntity product, @PathVariable Integer id) throws IOException    {
        ProductEntity productActual = productService.getProductById(id);
        productActual.setDescription(product.getDescription());
        productActual.setName(product.getName());
        productActual.setPrice(product.getPrice());
        productActual.setUserEntity(product.getUserEntity());
        return productService.saveProductApi(productActual);
        // log.info("Product obtenido: {}", product);
        //model.addAttribute("product", product);
        //return "admin/products/edit";
    }

    //eliminar un product
    @DeleteMapping("/product/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProductById(id);
        // return "redirect:/admin/products/show";
    }

}