package func.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ProductEntity {

    int productId;
    String productName;
    BigDecimal price;
    boolean isActive;
    String image;
    int categoryId;
    Date createdAt;

    public ProductEntity() {
    }

    public ProductEntity(int productId, String productName, BigDecimal price, boolean isActive, String image, int categoryId, Date createdAt) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.isActive = isActive;
        this.image = image;
        this.categoryId = categoryId;
        this.createdAt = createdAt;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
