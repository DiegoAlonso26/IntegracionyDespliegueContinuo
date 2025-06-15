package upeu.edu.pe.lp.infrastructure.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table (name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @Column(name = "order_date")
    private LocalDateTime orderDate;
    private String Orderstatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Transient
    private List<OrderDetailsEntity> orderDetails;
        /*agregar productos a arreglo*/
     public void addOrdersProduct(List<OrderDetailsEntity> orderDetailsentity){
        this.setOrderDetails(orderDetailsentity);
    }
    
    public BigDecimal getTotalOrderPrice(){
        return getOrderDetails().stream().map(
                p->p.getTotalPrice()
        ).reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    
}
