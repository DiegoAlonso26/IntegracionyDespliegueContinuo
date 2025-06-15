package upeu.edu.pe.lp.infrastructure.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table (name = "detallesOrden")
public class OrderDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    public OrderDetailsEntity(ProductEntity productEntity, Integer quantity, OrderEntity orderEntity) {
        this.productEntity = productEntity;
        this.quantity = quantity;
        this.orderEntity = orderEntity;
    }
    
        public BigDecimal getTotalPrice() {
        return this.productEntity.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
