package com.freelance.anantahairstudio.myBooking.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookingDetailsResponse {
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

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("user")
        @Expose
        private String user;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("slot")
        @Expose
        private String slot;
        @SerializedName("timestamp")
        @Expose
        private String timestamp;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("services")
        @Expose
        private List<Service> services = null;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
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

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Service> getServices() {
            return services;
        }

        public void setServices(List<Service> services) {
            this.services = services;
        }


        public class Service {

            @SerializedName("service_id")
            @Expose
            private String serviceId;
            @SerializedName("individuals")
            @Expose
            private String individuals;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("price")
            @Expose
            private String price;
            @SerializedName("discounted_price")
            @Expose
            private String discountedPrice;

            public String getServiceId() {
                return serviceId;
            }

            public void setServiceId(String serviceId) {
                this.serviceId = serviceId;
            }

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
