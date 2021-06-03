package com.freelance.anantahairstudio.signup.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Authentication {
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

        @SerializedName("token")
        @Expose
        private String token;
        @SerializedName("basic")
        @Expose
        private Basic basic;
        @SerializedName("address")
        @Expose
        private List<Object> address = null;
        @SerializedName("points")
        @Expose
        private Integer points;
        @SerializedName("isNewUser")
        @Expose
        private Integer isNewUser;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Basic getBasic() {
            return basic;
        }

        public void setBasic(Basic basic) {
            this.basic = basic;
        }

        public List<Object> getAddress() {
            return address;
        }

        public void setAddress(List<Object> address) {
            this.address = address;
        }

        public Integer getPoints() {
            return points;
        }

        public void setPoints(Integer points) {
            this.points = points;
        }

        public Integer getIsNewUser() {
            return isNewUser;
        }

        public void setIsNewUser(Integer isNewUser) {
            this.isNewUser = isNewUser;
        }

        public class Basic {

            @SerializedName("email")
            @Expose
            private String email;
            @SerializedName("phone")
            @Expose
            private Object phone;
            @SerializedName("referral")
            @Expose
            private String referral;

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public Object getPhone() {
                return phone;
            }

            public void setPhone(Object phone) {
                this.phone = phone;
            }

            public String getReferral() {
                return referral;
            }

            public void setReferral(String referral) {
                this.referral = referral;
            }

        }
    }
}
