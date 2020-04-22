package rentalsvc.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(name = "inventoryCheck", url = "http://inventorymanager:8080")
public interface InventoryService {

    @RequestMapping(method = RequestMethod.GET, path = "/inventoryCheck")
    public Long inventoryCheck(@RequestParam("productId") Long productId);

}
