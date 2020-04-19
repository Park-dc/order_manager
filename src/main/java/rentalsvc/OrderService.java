package rentalsvc;

import rentalsvc.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
	
    @Autowired
    private OrderRepository orderRepository;
    
    public void save(String data){
        ObjectMapper mapper = new ObjectMapper();
        Order order = null;
        try {
            order = mapper.readValue(data, Order.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        orderRepository.save(order);
    }
    
    public void save(Order order){
        orderRepository.save(order);
    }

    /**
     * Rental 요청 취소처리 함   
     * 
     */
    public void rentalCancel(Long orderId) {
    	orderRepository.deleteByOrderId(orderId);
    }
    
    /**
     * Rental 요청 취소 
     * 재고 부족 Event InventoryStockShortage 발생시  
     * 
     */

    @StreamListener(KafkaProcessor.INPUT)
    public void onInventoryStockShortage(@Payload InventoryStockShortage inventoryStockShortage) {
    	
        try {
        	if (inventoryStockShortage.isMe()) {
            	System.out.println("##### listener : " + inventoryStockShortage.toJson());
                rentalCancel(inventoryStockShortage.getOrderId());
                System.out.println("##### 랜탈 요청취소됨(재고부족) : " + inventoryStockShortage.getOrderId());
        		}
            }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
