package func.dto;

import java.math.BigDecimal;

public class ProductDTO {

    int productId;
    String productName;
    BigDecimal price;
    int stock;
    String image;
    int categoryId;
    boolean isAvailable;

    public ProductDTO() {
    }

    public ProductDTO(int productId, String productName, BigDecimal price, int stock, String image, int categoryId, boolean isAvailable) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.image = image;
        this.categoryId = categoryId;
        this.isAvailable = isAvailable;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

}
