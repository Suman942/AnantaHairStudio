package com.freelance.anantahairstudio.contactUs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactUpdateResponse {
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

        @SerializedName("business_info")
        @Expose
        private BusinessInfo businessInfo;

        public BusinessInfo getBusinessInfo() {
            return businessInfo;
        }

        public void setBusinessInfo(BusinessInfo businessInfo) {
            this.businessInfo = businessInfo;
        }
        public class BusinessInfo {

            @SerializedName("phone")
            @Expose
            private String phone;
            @SerializedName("whatsapp")
            @Expose
            private String whatsapp;
            @SerializedName("email")
            @Expose
            private String email;

            @SerializedName("facebook")
            @Expose
            private String facebook;

            @SerializedName("instagram")
            @Expose
            private String instagram;

            @SerializedName("youtube")
            @Expose
            private String youtube;
            @SerializedName("website")
            @Expose
            private String website;

            public String getFacebook() {
                return facebook;
            }

            public void setFacebook(String facebook) {
                this.facebook = facebook;
            }

            public String getInstagram() {
                return instagram;
            }

            public void setInstagram(String instagram) {
                this.instagram = instagram;
            }

            public String getYoutube() {
                return youtube;
            }

            public void setYoutube(String youtube) {
                this.youtube = youtube;
            }

            public String getWebsite() {
                return website;
            }

            public void setWebsite(String website) {
                this.website = website;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getWhatsapp() {
                return whatsapp;
            }

            public void setWhatsapp(String whatsapp) {
                this.whatsapp = whatsapp;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

        }

    }
}
