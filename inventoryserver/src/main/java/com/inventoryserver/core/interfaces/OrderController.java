package com.inventoryserver.core.interfaces;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventoryserver.core.Customer;
import com.inventoryserver.core.Order;
import com.inventoryserver.core.OrderDAO;
import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {
	
	public OrderDAO ordDao;
	
	
	public OrderController(OrderDAO ordDao) {
		this.ordDao = ordDao;
		
	}

	@PostMapping("/neworder")
	public ResponseEntity<String> createOrder(@RequestBody Order newOrder) {
	    // Check if any required fields are missing
	    if ( newOrder.getCustomerID().isEmpty() || newOrder.getTotalAmount() == 0 || newOrder.getOrderDetails().isEmpty()) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some fields are missing, please check");
	    }

	    // Save the new order
	    String orderId = ordDao.save(newOrder);

	    if (orderId.equals("Error")) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to save the order");
	    } else {
	        return ResponseEntity.ok().body(orderId);
	    }
	}
	
	@GetMapping("/totalvalue")
    public int getTotalAmount() {
        return ordDao.getTotalValue();
    }
	

	@GetMapping("/viewdetails/{orderid}")
    public String viewOrderDetails(@PathVariable int orderid) {
        try {
            // Query database to retrieve order information along with order details
            Order order = ordDao.getOrderById(orderid); // Implement this method to fetch order by ID

            // Serialize order object to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String orderJson = objectMapper.writeValueAsString(order);

            return orderJson;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "{\"error\": \"Failed to fetch order details\"}";
        }
    }
	
	
	@GetMapping("/generatePDF/{orderid}/{userid}")
	public ResponseEntity<InputStreamResource> createPdf(@PathVariable int orderid,@PathVariable String userid) {
	    ByteArrayInputStream pdf = ordDao.createPDF(orderid, userid);
	    HttpHeaders httpHeaders = new HttpHeaders(); // Fix syntax error here
	    httpHeaders.add("Content-Disposition", "inline; filename=invoice.pdf"); // Fix syntax error and add space before "filename"
	    return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(pdf)); // Fix syntax error and wrap pdf in InputStreamResource
	}
	
	
	@PostMapping("{orderid}/updateStatus/{status}")
	public ResponseEntity<String> setStatus(@PathVariable String status, @PathVariable int orderid ) {
		 String orderId = ordDao.changeStatus(status, orderid);

		    if (orderId.equals("Error")) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to save the order");
		    } else {
		        return ResponseEntity.ok().body("success" );
		    }
	}
	
	
	@GetMapping("/getallorders")
    public List<Order> getAllOrders() {
        return ordDao.getAllOrders();
    }
	
}
