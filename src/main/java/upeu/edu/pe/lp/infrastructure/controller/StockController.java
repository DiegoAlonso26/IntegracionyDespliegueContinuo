package upeu.edu.pe.lp.infrastructure.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import upeu.edu.pe.lp.app.service.ProductService;
import upeu.edu.pe.lp.app.service.StockService;
import upeu.edu.pe.lp.app.service.ValidateStock;
import upeu.edu.pe.lp.infrastructure.entity.ProductEntity;
import upeu.edu.pe.lp.infrastructure.entity.StockEntity;

@Controller
@RequestMapping("/admin/inventario")
public class StockController {
    
 private final StockService stockService;
    private final ValidateStock validateStock;

    public StockController(StockService stockService, ValidateStock validateStock) {
        this.stockService = stockService;
        this.validateStock = validateStock;
    }
    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model){
        ProductEntity product = new ProductEntity();
        product.setId(id);
        List<StockEntity> stocks = stockService.getStockByProductEntity(product);
        model.addAttribute("stocks", stocks);
        model.addAttribute("idproduct", id);
        return "admin/stock/add_units";
        
    }
    @GetMapping("create-unit-product/{id}")
    public String create(@PathVariable Integer id, Model model){
        model.addAttribute("idproduct", id);
      return"admin/stock/create";  
    } 
    
    @PostMapping("save-unit-product")
    public String save(StockEntity stock, @RequestParam("idproduct") Integer idproduct){
      stock.setDescripcion("entradas");
      ProductEntity product = new ProductEntity();
      product.setId(idproduct);
      stock.setProductEntity(product);
      stockService.saveStock(validateStock.calculateBalance(stock));
      return "redirect:/admin";
    }

   

}
