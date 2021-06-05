package com.freelance.anantahairstudio.cart.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartListResponse {
    @SerializedName("data")
    @Expose
    private List<Cart> data = null;

    public List<Cart> getData() {
        return data;
    }

    public void setData(List<Cart> data) {
        this.data = data;
    }

    public class Cart {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("individuals")
        @Expose
        private String individuals;
        @SerializedName("service_id")
        @Expose
        private String serviceId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("discounted_price")
        @Expose
        private String discountedPrice;
        @SerializedName("img")
        @Expose
        private String img;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIndividuals() {
            return individuals;
        }

        public void setIndividuals(String individuals) {
            this.individuals = individuals;
        }

        public String getServiceId() {
            return serviceId;
        }

        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
