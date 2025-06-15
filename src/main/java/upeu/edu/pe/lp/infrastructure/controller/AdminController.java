package upeu.edu.pe.lp.infrastructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import upeu.edu.pe.lp.app.service.OrderService;
import upeu.edu.pe.lp.app.service.ProductService;
import upeu.edu.pe.lp.app.service.StockService;
import upeu.edu.pe.lp.app.service.UserService;
import upeu.edu.pe.lp.infrastructure.entity.StockEntity;

@Controller
@RequestMapping("/admin")
public class AdminController {

    public final ProductService productService;
    private final StockService stockService;
    private final UserService userService;
    private final OrderService orderService;

    public AdminController(ProductService productService, StockService stockService, UserService userService, OrderService orderService) {
        this.productService = productService;
        this.stockService = stockService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping
    public String home (Model model){
        model.addAttribute("products",productService.getProducts());
        model.addAttribute("totalUsuarios", userService.countTotalUsers());
        model.addAttribute("totalOrder", orderService.count());
        model.addAttribute("totalProducts", productService.countTotalProducts());
        return "admin/home_admin";
    }

    @GetMapping("/users")
    public String users (Model model){
        model.addAttribute("users",userService.getUsers());
        model.addAttribute("totalUsuarios", userService.countTotalUsers());
        model.addAttribute("totalOrder", orderService.count());
        model.addAttribute("totalProducts", productService.countTotalProducts());
        return "admin/user";
    }

    @GetMapping("/pedidos")
    public String pedidos (Model model){
        model.addAttribute("pedidos",orderService.getOrders());
        model.addAttribute("totalUsuarios", userService.countTotalUsers());
        model.addAttribute("totalOrder", orderService.count());
        model.addAttribute("totalProducts", productService.countTotalProducts());
        return "admin/pedidos";
    }


}
