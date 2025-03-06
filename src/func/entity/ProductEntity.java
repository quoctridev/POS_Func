/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author quoctris.dev
 */
public class ProductEntity {

    private int productId;
    private String name;
    private BigDecimal price;
    private String image;
    private int stockQuantity;
    private int categoryId;
    private Date createdAt;

    public ProductEntity() {
    }

    public ProductEntity(int productId, String name, BigDecimal price, String image, int stockQuantity, int categoryId, Date createdAt) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.image = image;
        this.stockQuantity = stockQuantity;
        this.categoryId = categoryId;
        this.createdAt = createdAt;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
