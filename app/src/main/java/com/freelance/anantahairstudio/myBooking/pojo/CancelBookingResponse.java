package com.freelance.anantahairstudio.myBooking.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CancelBookingResponse {

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

        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("booking_id")
        @Expose
        private String bookingId;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getBookingId() {
            return bookingId;
        }

        public void setBookingId(String bookingId) {
            this.bookingId = bookingId;
        }

    }
}
