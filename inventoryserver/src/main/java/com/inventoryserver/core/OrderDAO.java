package com.inventoryserver.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.itextpdf.io.IOException;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.alignment.HorizontalAlignment;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
@Repository
public class OrderDAO {
	
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	 LocalDateTime now = LocalDateTime.now();
	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	 
	 String formattedDateTime = now.format(formatter);
	 
	 

public String save(Order newOrder) {
    String insertSql = "INSERT INTO orders (order_date, customer_id, ship_date, total_amount, order_status) VALUES (?, ?, ?, ?,?)";
    String insertDetailSql = "INSERT INTO orderdetails (order_id, product_name, product_qty, product_unitprice, product_total) VALUES (?, ?, ?, ?, ?)";
    String updateProductSql = "UPDATE products SET product_qty = product_qty - ? WHERE product_name = ?";
    
    try {
        // Start a transaction
        jdbcTemplate.execute("START TRANSACTION");

        // Insert the order
        jdbcTemplate.update(insertSql, formattedDateTime, newOrder.getCustomerID(), newOrder.getShipDate(), newOrder.getTotalAmount(), newOrder.getOrderStatus());

        // Get the ID of the inserted order
        int orderId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        // Insert order details and update product quantities
        for (OrderDetail detail : newOrder.getOrderDetails()) {
            jdbcTemplate.update(insertDetailSql, orderId, detail.getProductId(), detail.getProductQty(), detail.getUnitPrice(), detail.getProductTotal());
            jdbcTemplate.update(updateProductSql, detail.getProductQty(), detail.getProductId());
        }

        // Commit the transaction
        jdbcTemplate.execute("COMMIT");

        return "Success";
    } catch(Exception ex) {
        
        jdbcTemplate.execute("ROLLBACK");
        ex.printStackTrace();
        return "Error";
    }
}
	 
	 
	 public int getTotalValue()
	 {
		 String sql = "SELECT SUM(total_amount) AS total_amnt FROM orders";
	        Integer totalQuantity = jdbcTemplate.queryForObject(sql, Integer.class);
	        return totalQuantity != null ? totalQuantity : 0;
	 }

	 
	 public Order getOrderById(int orderId) {
	        String query = "SELECT * FROM orders WHERE order_id = ?";
	        try {
	            List<Order> orders = jdbcTemplate.query(query, new Object[]{orderId}, new OrderRowMapper());
	            if (!orders.isEmpty()) {
	                Order order = orders.get(0);
	                List<OrderDetail> orderDetails = getOrderDetailsByOrderId(orderId);
	                order.setOrderDetails(orderDetails);
	                return order;
	            }
	            
	            return null;
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return null;
	        }
	    }
	 
	 

	    private List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
	        String query = "SELECT * FROM orderdetails WHERE order_id = ?";
	        try {
	            List<OrderDetail> orderDetails = jdbcTemplate.query(query, new Object[]{orderId}, new OrderDetailRowMapper());
	            return orderDetails;
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return null;
	        }
	    }
	    
	    
	    public String changeStatus(String status, int orderid) {
	    	
	    	  String updateProductSql = "UPDATE orders SET order_status = ? WHERE order_id = ?";
	    	  
	    	  
	    	  try {
	    		  jdbcTemplate.update(updateProductSql, status, orderid);
	    		  
	    		  return "Success";
	    	  }catch(Exception ex) {
	    	       
	    	        ex.printStackTrace();
	    	        return "Error";
	    	    }
	    }
	    
	    
	    
	    
//	    PDF GENERATOR CODE
	    
	    public ByteArrayInputStream createPDF(int orderId, String userid) {
	        // Retrieve order details using getOrderById method
	        Order order = getOrderById(orderId);
	        if (order == null) {
	            System.out.println("Order not found.");
	            return null;
	        }
	        	ByteArrayOutputStream out = new ByteArrayOutputStream();
	        	
	        // Path to save the PDF invoice
	        

	        try {
	            Document document = new Document();
	            PdfWriter.getInstance(document, out);
	            document.open();

	            // Add order details to the PDF
	            Font font = FontFactory.getFont(FontFactory.HELVETICA, 23, Font.BOLD);
	            Font font2 = FontFactory.getFont(FontFactory.HELVETICA, 19);
	            Font font3 = FontFactory.getFont(FontFactory.HELVETICA, 14);
	            Font font4 = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD);
	            
	            
	            Paragraph invoiceParagraph = new Paragraph("Invoice", font2);
	            invoiceParagraph.setAlignment(Element.ALIGN_CENTER);

	            Paragraph head = new Paragraph("Inventory Management System", font);
	            head.setAlignment(Element.ALIGN_CENTER);
	            
	            Paragraph tail = new Paragraph("711 E Boldt Way, Appleton, WI, 54911", font3);
	            tail.setAlignment(Element.ALIGN_CENTER);
	            
	            Paragraph footer = new Paragraph("920-832-6666 info@ims.com", font3);
	            footer.setAlignment(Element.ALIGN_CENTER);
	            
	            
	            Paragraph invoiceDateParagraph = new Paragraph("Invoice Date: " + formattedDateTime);
	            invoiceDateParagraph.setAlignment(Element.ALIGN_RIGHT);
	        
	            
	            Paragraph orderdetails = new Paragraph("Order Details", font4);
	            orderdetails.setAlignment(Element.ALIGN_CENTER);
	            
	            
	            document.add(invoiceParagraph);
	            document.add(head);
	            document.add(tail);
	            document.add(footer);
	            
	            document.add(new Paragraph("	     	      	      "));
	            document.add(invoiceDateParagraph);
	            document.add(new Paragraph("	     	      	      "));
	            
	            
	            
	            
	            document.add(new Paragraph("Invoice Number: " + order.getOrderID()));
	            document.add(new Paragraph("Customer Name: " + order.getCustomerID()));
	            document.add(new Paragraph("Order Date: " + order.getOrderDate()));
	            document.add(new Paragraph("Shipment Date: " + order.getShipDate()));
	            
	           

	            // Create paragraph for Invoice Date
	           	            
	            
	            Paragraph userpara = new Paragraph(userid);
	            userpara.setAlignment(Element.ALIGN_RIGHT);
	            
	            
	            Paragraph signature = new Paragraph("-------------------");
	            signature.setAlignment(Element.ALIGN_RIGHT);
	            // Add the paragraphs to your document
	           
	            
	           
	            document.add(orderdetails);
	            
	            Table table = new Table(4);
	            table.setWidth(100);;

	            // Add header cells
	            table.addCell(createHeaderCell("Product Name"));
	            table.addCell(createHeaderCell("Quantity"));
	            table.addCell(createHeaderCell("Unit Price"));
	            table.addCell(createHeaderCell("Total"));

	            
	            
	            // Add order details to the table
	            List<OrderDetail> orderDetails = order.getOrderDetails();
	            for (OrderDetail detail : orderDetails) {
	                table.addCell(createCell(detail.getProductId()));
	                table.addCell(createCell(String.valueOf(detail.getProductQty())));
	                table.addCell(createCell(String.valueOf(detail.getUnitPrice())));
	                table.addCell(createCell("$"+String.valueOf(detail.getProductTotal())));
	            }

	            table.addCell(createCell("  "));
                table.addCell(createCell("  "));
                table.addCell(createBoldCell( "Total Amount "));
                table.addCell(createBoldCell("$"+String.valueOf(order.getTotalAmount())));
	            // Add the table to the document
	            document.add(table);
	            // Add order details
	            document.add(new Paragraph("	     	      	      "));
	            document.add(new Paragraph("	     	      	      "));
	            
	            document.add(signature);
	            document.add(userpara);
	            document.add(new Paragraph("	     	      	      "));
	            
	            document.add(new Paragraph("=========================================================================="));
	            
	            Paragraph toc = new Paragraph("Terns & Conditions", font4);
	            toc.setAlignment(Element.ALIGN_CENTER);
	            
	           
	            document.add(toc);
	            document.add(new Paragraph("1) Returns must be presented within 15 days of Invoice Date."));
	            document.add(new Paragraph("2) E&OE"));
	            
	            document.close();

	           
	        } catch (DocumentException  e) {
	            e.printStackTrace();
	        }
	        return new ByteArrayInputStream(out.toByteArray());
	    }
	    
	    
	 
	    // Method to create Cell with text, alignment, and bold style for header cells
	   
	    private Cell createHeaderCell(String text) {
	        Cell cell = new Cell();
	        
	        Font font4 = FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLD);
	        Paragraph invoiceParagraph = new Paragraph(text, font4);
            
            
	        cell.add(new Paragraph(invoiceParagraph));
	         // Align text to center
	        
	        cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	        return cell;
	    }

	    private Cell createCell(String text) {
	        Cell cell = new Cell();
	        cell.add(new Paragraph(text));
	 // Align text to center
	        cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	        return cell;
	    }

	    
	    private Cell createBoldCell(String text) {
	        Cell cell = new Cell();
	        
	        Font font4 = FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLD);
	        Paragraph invoiceParagraph = new Paragraph(text, font4);
            
            
	        cell.add(new Paragraph(invoiceParagraph));
	 // Align text to center
	        cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	        return cell;
	    }

	    
//	    PDF GENERATOR CODE
}
