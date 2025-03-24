package func.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DiscountEntity {

    int discountId;
    String code;
    BigDecimal discountValue;
    BigDecimal maxValue;
    Date startDate;
    Date endDate;
    Date createdAt;

    public DiscountEntity() {
    }

    public DiscountEntity(int discountId, String code, BigDecimal discountValue, BigDecimal maxValue, Date startDate, Date endDate, boolean isActive, Date createdAt) {
        this.discountId = discountId;
        this.code = code;
        this.discountValue = discountValue;
        this.maxValue = maxValue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    public BigDecimal getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
