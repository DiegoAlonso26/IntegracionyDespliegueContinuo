package upeu.edu.pe.lp.infrastructure.controller;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import upeu.edu.pe.lp.app.domain.ItemCart;
import upeu.edu.pe.lp.app.service.*;
import upeu.edu.pe.lp.infrastructure.entity.OrderDetailsEntity;
import upeu.edu.pe.lp.infrastructure.entity.OrderEntity;
import upeu.edu.pe.lp.infrastructure.entity.StockEntity;
import upeu.edu.pe.lp.infrastructure.entity.UserEntity;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Controller
@RequestMapping("/user/order")
public class OrderController {
    private final CartService cartService;
    private final UserService userServices;
    private final OrderService orderService;
    private final ProductService productService;
    private final OrderDetailsService orderProductService;
    private final Integer entradas = 0;
    private final StockService stockService;
    private final ValidateStock validateStock;
    
        private final JavaMailSender javamailsend;
    private final TemplateEngine templateEngine;

    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    public OrderController(CartService cartService, UserService userServices, OrderService orderService, ProductService productService, OrderDetailsService orderProductService, StockService stockService, ValidateStock validateStock, JavaMailSender javamailsend, TemplateEngine templateEngine) {
        this.cartService = cartService;
        this.userServices = userServices;
        this.orderService = orderService;
        this.productService = productService;
        this.orderProductService = orderProductService;
        this.stockService = stockService;
        this.validateStock = validateStock;
        this.javamailsend = javamailsend;
        this.templateEngine = templateEngine;
    }



    @GetMapping("/sumary-order")
    public String showSumaryOrder(Model model, HttpSession httpSession) {
        UserEntity user = userServices.findById(Integer.parseInt(httpSession.getAttribute("iduser").toString()));
        model.addAttribute("cart", cartService.getItemCarts());
        model.addAttribute("total", cartService.getTotalCart());
        model.addAttribute("user", user);
        model.addAttribute("id", httpSession.getAttribute("iduser").toString());
        model.addAttribute("nombre", httpSession.getAttribute("name").toString());

        return "user/sumaryorder";

    }

    @GetMapping("/create-order")
    public String createOrder(RedirectAttributes attributes, HttpSession httpSession) {
        UserEntity user = userServices.findById(Integer.parseInt(httpSession.getAttribute("iduser").toString()));
        OrderEntity order = new OrderEntity();
        order.setOrderDate(LocalDateTime.now());
        order.setOrderstatus("Proceso");
        order.setUserEntity(user);
        log.info("order", order);
        //guardar Order
        order = orderService.createOrder(order);

        List<OrderDetailsEntity> orderProduct = new ArrayList<>();
        for (ItemCart itemCart : cartService.getItemCarts()) {
            orderProduct.add(new OrderDetailsEntity(productService.getProductById(itemCart.getIdProduct()),
                    itemCart.getQuantity(),
                    order));
        }

        orderProduct.forEach(
                op -> {
                    orderProductService.create(op);
                    StockEntity stock = new StockEntity();
                    stock.setDescripcion("salida");
                    stock.setEntradas(entradas);
                    stock.setProductEntity(op.getProductEntity());
                    stock.setSalidas(op.getQuantity());
                    stockService.saveStock(validateStock.calculateBalance(stock));
                }
        );

      // Construir el mensaje de correo electrónico utilizando Thymeleaf
        String emailBody = buildEmailBodyThymeleaf(user, order, cartService.getItemCarts(), cartService.getTotalCart());

     // Enviar el correo electrónico
        sendOrderConfirmationEmail(user.getEmail(), "Confirmación de orden", emailBody);

        cartService.removeAllItemCart();
        attributes.addFlashAttribute("id", httpSession.getAttribute("iduser").toString());
        attributes.addFlashAttribute("nombre", httpSession.getAttribute("name").toString());
        return "redirect:/home";
    }
    
    // Método para enviar el correo electrónico
    private void sendOrderConfirmationEmail(String to, String subject, String body) {
        MimeMessage message = javamailsend.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            javamailsend.send(message);
        } catch (MessagingException e) {
            // Manejar la excepción (puedes loguearla, relanzarla, etc.)
            e.printStackTrace();
        }
        
    }
    
    // Método para construir el cuerpo del correo electrónico usando Thymeleaf
private String buildEmailBodyThymeleaf(UserEntity user, OrderEntity order, List<ItemCart> items, BigDecimal total) {
    // Crear el contexto de Thymeleaf
    Context context = new Context();
    context.setVariable("user", user);
    context.setVariable("order", order);
    context.setVariable("items", items);
    context.setVariable("total", total);

    // Procesar la plantilla Thymeleaf
    return templateEngine.process("user/orderConfirmationEmail", context);
}

}








