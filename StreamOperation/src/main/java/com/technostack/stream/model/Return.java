package com.technostack.stream.model;

public class Return {

    private String orderId;
    private String itemId;
    private String reason;
    private double refundAmount;

    public Return(String orderId, String itemId, String reason, double refundAmount) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.reason = reason;
        this.refundAmount = refundAmount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(double refundAmount) {
        this.refundAmount = refundAmount;
    }

    @Override
    public String toString() {
        return "Return{" +
                "orderId='" + orderId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", reason='" + reason + '\'' +
                ", refundAmount=" + refundAmount +
                '}';
    }
}
