package rentalsvc;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private Long productId;

//    @PostPersist
//    public void when_PostPersist_publishEvent(){
//        RentalRequested rentalRequested = new RentalRequested();
//        BeanUtils.copyProperties(this, rentalRequested);
//        rentalRequested.publish();
//
//        RentalCancellationOccured rentalCancellationOccured = new RentalCancellationOccured();
//        BeanUtils.copyProperties(this, rentalCancellationOccured);
//        rentalCancellationOccured.publish();
//
//    }

//
//    @PostPersist
//    public void publishRentalRequested(){
//
//        RentalRequested rentalRequested = new RentalRequested();
//        BeanUtils.copyProperties(this, rentalRequested);
//        rentalRequested.publish();
//
//    }
//
//    @PostPersist
//    public void publishRentalCancellationOccured(){
//
//        RentalCancellationOccured rentalCancellationOccured = new RentalCancellationOccured();
//        BeanUtils.copyProperties(this, rentalCancellationOccured);
//        rentalCancellationOccured.publish();
//
//    }
//

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

}
