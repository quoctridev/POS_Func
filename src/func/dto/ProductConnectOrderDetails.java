/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.dto;
import java.util.Date;
import java.math.BigDecimal;
/**
 *
 * @author AZ Tech
 */
public class ProductConnectOrderDetails {
   int  product_id, stock_quantity, statusId,chefId,orderDetailId;
   String  name,image,status;
   Date createdAt;
   BigDecimal price;

    public ProductConnectOrderDetails() {
    }

    public ProductConnectOrderDetails(int product_id, int stock_quantity, int statusId, int chefId, int orderDetailId, String name, String image, String status, Date createdAt, BigDecimal price) {
        this.product_id = product_id;
        this.stock_quantity = stock_quantity;
        this.statusId = statusId;
        this.chefId = chefId;
        this.orderDetailId = orderDetailId;
        this.name = name;
        this.image = image;
        this.status = status;
        this.createdAt = createdAt;
        this.price = price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getChefId() {
        return chefId;
    }

    public void setChefId(int chefId) {
        this.chefId = chefId;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
   
}
