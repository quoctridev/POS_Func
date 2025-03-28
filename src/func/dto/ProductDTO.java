/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author quoctris.dev
 */
public class ProductDTO {

    int productId;
    String productName;
    BigDecimal price;
    boolean isActive;
    String image;
    int categoryId;
    String categoryName;

    public ProductDTO() {
    }

    public ProductDTO(int productId, String productName, BigDecimal price, boolean isActive, String image, int categoryId, String categoryName) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.isActive = isActive;
        this.image = image;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
