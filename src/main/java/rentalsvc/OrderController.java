package rentalsvc;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rentalsvc.external.Inventory;
import rentalsvc.external.InventoryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

 @RestController
 public class OrderController {

 @Autowired
 OrderService orderService;
	    
@RequestMapping(value = "/rentalRequest",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8")

public void rentalRequest(@RequestParam Long orderId,@RequestParam Long productId,@RequestParam Long qty,@RequestParam Long amount)
        throws Exception {
        System.out.println("##### /order/rentalRequest  called #####");
        
        
        Order order = new Order();
        RentalRequested rentalRequested = new RentalRequested();
        rentalRequested.setOrderId(orderId);
        rentalRequested.setProductId(productId);
        rentalRequested.setQty(qty);
        rentalRequested.setAmount(amount);
        BeanUtils.copyProperties(this, rentalRequested);
        rentalRequested.publish();
                
        Long dd = Application.applicationContext.getBean(InventoryService.class).inventoryCheck(productId);
        
        System.out.println("-------------- Return Value --------------" + dd);
        
        
        order.setOrderId(orderId);
        order.setProductId(productId);
        System.out.println("##### jason #####" + order );
        
        orderService.save(order);
        
        System.out.println("##### rentalRequest  event published #####");
        
        
        }

@RequestMapping(value = "/rentalCancel",
        method = RequestMethod.GET,
        produces = "application/json;charset=UTF-8")

public void cancellationOfRental(@RequestParam Long orderId,@RequestParam Long productId)
        throws Exception {
        System.out.println("##### /order/cancellationOfRental  called #####");
        
        RentalCancellationOccured rentalCancellationOccured = new RentalCancellationOccured();
        rentalCancellationOccured.setOrderId(orderId);
        rentalCancellationOccured.setProductId(productId);
        BeanUtils.copyProperties(this, rentalCancellationOccured);
        rentalCancellationOccured.publish();

        System.out.println("##### rentalCancellationOccured  event published #####");
        
        }
 }
