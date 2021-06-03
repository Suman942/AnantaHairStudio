package com.freelance.anantahairstudio.myInfo.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyAccountResponse {
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

        @SerializedName("basic")
        @Expose
        private Basic basic;
        @SerializedName("address")
        @Expose
        private List<Address> address = null;
        @SerializedName("points")
        @Expose
        private String points;

        public Basic getBasic() {
            return basic;
        }

        public void setBasic(Basic basic) {
            this.basic = basic;
        }

        public List<Address> getAddress() {
            return address;
        }

        public void setAddress(List<Address> address) {
            this.address = address;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

    }

    public class Basic {

        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("referral")
        @Expose
        private String referral;

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

        public String getReferral() {
            return referral;
        }

        public void setReferral(String referral) {
            this.referral = referral;
        }

    }

    public class Address {

        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("is_primary")
        @Expose
        private String isPrimary;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIsPrimary() {
            return isPrimary;
        }

        public void setIsPrimary(String isPrimary) {
            this.isPrimary = isPrimary;
        }

    }
}
