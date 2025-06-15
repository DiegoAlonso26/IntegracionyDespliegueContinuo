package upeu.edu.pe.lp.infrastructure.controller;

import jakarta.servlet.http.HttpSession;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import upeu.edu.pe.lp.app.service.ProductService;

import upeu.edu.pe.lp.app.service.StockService;

import upeu.edu.pe.lp.infrastructure.entity.StockEntity;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final ProductService productService;
    private final StockService stockService;

    public HomeController(ProductService productService, StockService stockService) {
        this.productService = productService;
        this.stockService = stockService;
    }

    @GetMapping
    public String home(Model model, HttpSession httpSession) {

        model.addAttribute("products", productService.getProducts());
        try {
            model.addAttribute("nombre", httpSession.getAttribute("name").toString());
            model.addAttribute("id", httpSession.getAttribute("iduser").toString());
        } catch (Exception e) {
        }

        return "home";
    }

    @GetMapping("/product-detail/{id}")
    public String productDetail(@PathVariable Integer id, Model model) {
        List<StockEntity> stock = stockService.getStockByProductEntity(productService.getProductById(id));
        Integer lastBalance = stock.get(stock.size() - 1).getBalance();

        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("stock", lastBalance);
        try {
            model.addAttribute("id", 1);
        } catch (Exception e) {

        }
        return "user/producto_home";
    }
}
