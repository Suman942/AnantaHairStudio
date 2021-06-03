package com.freelance.anantahairstudio.services.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServicesResponse {
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

        @SerializedName("services")
        @Expose
        private List<Service> services = null;

        public List<Service> getServices() {
            return services;
        }

        public void setServices(List<Service> services) {
            this.services = services;
        }


        public class Service {

            @SerializedName("id")
            @Expose
            private String id;
            @SerializedName("category_id")
            @Expose
            private String categoryId;
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
            @SerializedName("info")
            @Expose
            private Object info;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(String categoryId) {
                this.categoryId = categoryId;
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

            public Object getInfo() {
                return info;
            }

            public void setInfo(Object info) {
                this.info = info;
            }

        }

    }

}
