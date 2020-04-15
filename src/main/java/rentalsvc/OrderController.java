package rentalsvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

 @RestController
 public class OrderController {

@RequestMapping(value = "/rentalRequest",
        method = RequestMethod.GET,
        produces = "application/json;charset=UTF-8")

public void rentalRequest(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        System.out.println("##### /order/rentalRequest  called #####");
        }

@RequestMapping(value = "/rentalCancel",
        method = RequestMethod.GET,
        produces = "application/json;charset=UTF-8")

public void cancellationOfRental(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        System.out.println("##### /order/cancellationOfRental  called #####");
        }
 }
