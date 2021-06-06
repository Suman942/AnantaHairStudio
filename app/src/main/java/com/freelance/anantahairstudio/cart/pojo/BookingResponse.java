package com.freelance.anantahairstudio.cart.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingResponse {
    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("booking_id")
        @Expose
        private Integer bookingId;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("slot")
        @Expose
        private String slot;

        public Integer getBookingId() {
            return bookingId;
        }

        public void setBookingId(Integer bookingId) {
            this.bookingId = bookingId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSlot() {
            return slot;
        }

        public void setSlot(String slot) {
            this.slot = slot;
        }

    }
}
