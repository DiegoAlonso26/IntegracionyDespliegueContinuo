/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package upeu.edu.pe.lp.infrastructure.controller;

import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import upeu.edu.pe.lp.app.service.CartService;

/**
 *
 * @author DIEGO
 */
@Controller
@RequestMapping("/user/cart")
public class CartController {
    private final CartService cartService;
    private final Logger log = LoggerFactory.getLogger(CartController.class);

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add-product")
    public String addProductCart(@RequestParam Integer quantity, @RequestParam Integer idProduct, @RequestParam String nameProduct, @RequestParam BigDecimal price) {
        cartService.addItemCart(idProduct, nameProduct, quantity, price);
        showCart();

        // Agregar un retraso de 1 segundo (1000 milisegundos) antes de redirigir
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "redirect:/home";
    }


    //ver en consola
    private void showCart(){
        cartService.getItemCarts().forEach(itemCart -> log.info("Item cart {}",itemCart));
    }
     @GetMapping("/get-cart")
     public String getCart(Model model,HttpSession httpSession){
         showCart();
         model.addAttribute("cart", cartService.getItemCarts());
         model.addAttribute("total", cartService.getTotalCart());
         model.addAttribute("id", httpSession.getAttribute("iduser").toString());
         return "user/cart/cart";
     }
    @GetMapping("/delete-item-cart/{id}")
    public String deleteItemCart(@PathVariable Integer id){
        cartService.removeItemCart(id);
        return "redirect:/user/cart/get-cart";
    }
    
}
