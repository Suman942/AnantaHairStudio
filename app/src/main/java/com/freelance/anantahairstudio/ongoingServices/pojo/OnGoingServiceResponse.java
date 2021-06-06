package com.freelance.anantahairstudio.ongoingServices.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OnGoingServiceResponse {

    @SerializedName("data")
    @Expose
    private List<Data> data = null;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("booking_id")
        @Expose
        private String bookingId;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("slot")
        @Expose
        private String slot;
        @SerializedName("services")
        @Expose
        private List<Service> services = null;

        public String getBookingId() {
            return bookingId;
        }

        public void setBookingId(String bookingId) {
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

        public List<Service> getServices() {
            return services;
        }

        public void setServices(List<Service> services) {
            this.services = services;
        }

        public class Service {

            @SerializedName("individuals")
            @Expose
            private String individuals;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("img")
            @Expose
            private String img;
            @SerializedName("price")
            @Expose
            private String price;
            @SerializedName("discounted_price")
            @Expose
            private String discountedPrice;

            public String getIndividuals() {
                return individuals;
            }

            public void setIndividuals(String individuals) {
                this.individuals = individuals;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getDiscountedPrice() {
                return discountedPrice;
            }

            public void setDiscountedPrice(String discountedPrice) {
                this.discountedPrice = discountedPrice;
            }

        }

    }

}
