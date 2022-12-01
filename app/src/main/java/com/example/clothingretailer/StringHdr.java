package com.example.clothingretailer;

import android.widget.Toast;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

import javax.mail.internet.InternetAddress;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHdr {
    private String str;
    private String encode_str;

    String specialCharactes = new String("\" !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~\"");
    String lowcase = new String("abcdefghijklmnopqrstuvwxyz");
    String upcase = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    String digit = new String("123456789");
    String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
        //this.encode_str = Base64.encodeBase64String(str.getBytes());
    }

    public StringHdr(String str) {
        this.str = str;
        //this.encode_str = Base64.encodeBase64String(str.getBytes());
    }

    public StringHdr(String encode_str, String flag) {
        //this.str = new String(Base64.decodeBase64(encode_str));
        this.encode_str = encode_str;
    }

    public String encodePassword(){
        //encode_str = Base64.encodeBase64String(str.getBytes());
        return encode_str;
    }

    //public String decodePassword(){
    //    return new String(Base64.decodeBase64(encode_str));
    //}

    public boolean containAtLeastOne(String _str){
        for(int i = 0; i < _str.length(); i++){
            if(str.indexOf(_str.charAt(i)) != -1){
                return true;
            }
        }
        return false;
    }

    public String validPassword(){
        String mess = "";
        if(str.length() < 8)
            mess = "The password must be at least 8 characters long.";
        else if(str.length() > 30)
            mess = "The password must be at most 30 characters long.";
        else if(!containAtLeastOne(specialCharactes)){
            mess = "The password must have at least 1 special character.";
        }
        else if(!containAtLeastOne(upcase)){
            mess = "The password must have at least 1 upcase character";
        }
        else if(!containAtLeastOne(lowcase)){
            mess = "The password must have at least 1 lowcase character";
        }
        else if(!containAtLeastOne(digit)){
            mess = "The password must have at least 1 digit.";
        }
        return mess;
    }

    public String validUsername(){
        String mess = "";
        if(str.length() < 6)
            mess = "The username must be at least 6 characters long.";
        else if(str.length() > 30)
            mess = "The username must be at most 30 characters long.";
        else if(!containAtLeastOne(lowcase)){
            mess = "The username must be at least 1 ascii letter (a-z).";
        }
        else if(str.indexOf("_") == 0 || str.indexOf("_") == str.length() - 1){
            mess =  "The first or last character must be an ascii letter (a-z) or number (0-9)";
        }
        else if(containAtLeastOne(specialCharactes)){
            mess = "Only ascii letter (a-z) ,number (0-9), and underscore are allowed.";
        }
        return mess;
    }

    public String validName(){
        String mess = "";
        if(str.length() == 0){
            mess = "No name can be left blank.";
        }
        else if(containAtLeastOne(digit)){
            mess = "There must be no numbers in the name.";
        }
        return mess;
    }

    public String validEmail(){
        String mess ="";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(str);
        if(!m.matches()){
            mess = "Invalid email";
        }
        return mess;
    }

    public String validPhone() {
        String mess="";
        if(str.length() != 10 && str.length() != 11){
            mess = "Phone number length must be 10 or 11.";
        }
        return mess;
    }

    public static List<String> getURLImgs(String image_path){
        String URL_SEPERATE = "<<<";
        List<String> res = new ArrayList<String>();
        int pos = image_path.indexOf(URL_SEPERATE);
        int beg = 0;
        while(pos != -1){
            res.add(image_path.substring(beg, pos));
            beg = pos + URL_SEPERATE.length();
            pos = image_path.indexOf(URL_SEPERATE, beg);
        }
        res.add(image_path.substring(beg, image_path.length()));
        return res;
    }

}
