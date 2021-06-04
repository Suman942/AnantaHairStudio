package com.freelance.anantahairstudio.cart.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddtoCartResponse {
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
        @SerializedName("item")
        @Expose
        private Item item;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }
    }
    public class Item {

        @SerializedName("service_id")
        @Expose
        private String serviceId;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("discounted_price")
        @Expose
        private String discountedPrice;
        @SerializedName("individuals")
        @Expose
        private String individuals;
        @SerializedName("id")
        @Expose
        private Integer id;

        public String getServiceId() {
            return serviceId;
        }

        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
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

        public String getIndividuals() {
            return individuals;
        }

        public void setIndividuals(String individuals) {
            this.individuals = individuals;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

    }
}
