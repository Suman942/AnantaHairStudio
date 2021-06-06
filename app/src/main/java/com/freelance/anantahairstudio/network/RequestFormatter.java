package com.freelance.anantahairstudio.network;


import com.google.gson.JsonObject;

public class RequestFormatter {
       public static JsonObject sendNotification(String to,String title,String body) {
              JsonObject jsonObject = new JsonObject();
              jsonObject.addProperty("to",to);
              JsonObject notiObj = new JsonObject();
              notiObj.addProperty("title", title);
              notiObj.addProperty("body", body);

              jsonObject.add("notification",notiObj);
              return jsonObject;
       }
}
