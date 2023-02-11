package com.zerophi.gestionvie.login;
import org.apache.http.message.BasicNameValuePair;
import com.zerophi.gestionvie.user;

import org.apache.http.NameValuePair;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class LoginData {
     user mUser;
     private String mFormBody;
     public LoginData(user user){
         this.mUser= user;
     }


     public String packLoginData(){
;
         List<NameValuePair> formData =new ArrayList<NameValuePair>();
         formData.add(new BasicNameValuePair("email",mUser.getusername()));
         formData.add(new BasicNameValuePair("password",mUser.getPassword()));

         if(formData == null){
             mFormBody =null;

         }
         StringBuilder sb = new StringBuilder();
         for(int i =0; i<formData.size();i++) {
             NameValuePair item = formData.get(i);

             sb.append(URLEncoder.encode(item.getName()));
             sb.append("=");
             sb.append(URLEncoder.encode(item.getValue()));
             if (i != (formData.size() - 1)) {
                 sb.append("&");
             }
         }
             mFormBody=sb.toString();

         return mFormBody;
     }



}
