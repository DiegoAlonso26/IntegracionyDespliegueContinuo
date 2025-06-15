package upeu.edu.pe.lp.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.WebApplicationContext;
import upeu.edu.pe.lp.app.repository.OrderDetailsRepository;
import upeu.edu.pe.lp.app.repository.OrderRepository;
import upeu.edu.pe.lp.app.repository.ProductRepository;
import upeu.edu.pe.lp.app.repository.StockRepository;
import upeu.edu.pe.lp.app.repository.UserRepository;
import upeu.edu.pe.lp.app.service.*;

@Configuration
public class BeanConfiguration {

    @Bean
    public ProductService productService(ProductRepository productRepository, UploadFile uploadFile) {
        return new ProductService(productRepository, uploadFile);
    }

    @Bean
    public StockService stockService(StockRepository stockRepository) {
        return new StockService(stockRepository);
    }

    @Bean
    public OrderService orderService(OrderRepository orderRepository) {
        return new OrderService(orderRepository);
    }

    @Bean
    public OrderDetailsService orderDetailsService(OrderDetailsRepository orderDetailsRepository) {
        return new OrderDetailsService(orderDetailsRepository);
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public CartService cartService() {
        return new CartService();
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

    @Bean
    public ValidateStock validateStock(StockService stockService) {
        return new ValidateStock(stockService);
    }

    @Bean
    public LoginService loginService(UserService userService){
        return new LoginService(userService);
    }
    @Bean
    public LogoutService logoutService(){
        return  new LogoutService();
    }
    @Bean
    public RegistrationService registrationService(UserService userService, PasswordEncoder passwordEncoder){
        return  new RegistrationService(userService, passwordEncoder);
    }

}
