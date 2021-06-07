package com.freelance.anantahairstudio.network;


import com.google.gson.JsonObject;

public class RequestFormatter {
       public static JsonObject sendNotification(String to,String title,String body,int logo) {
              JsonObject jsonObject = new JsonObject();
              jsonObject.addProperty("to",to);
              JsonObject notificationObj = new JsonObject();
              notificationObj.addProperty("title", title);
              notificationObj.addProperty("body", body);
              notificationObj.addProperty("icon", logo);


              jsonObject.add("notification",notificationObj);
              return jsonObject;
       }
}
