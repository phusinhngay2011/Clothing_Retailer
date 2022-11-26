package com.example.clothingretailer;

import android.widget.Toast;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

public class StringHdr {
    private String str;
    String specialCharactes = new String("\" !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~\"");
    String lowcase = new String("abcdefghijklmnopqrstuvwxyz");
    String upcase = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    String digit = new String("123456789");
    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public StringHdr(String str) {
        this.str = str;
    }

    public byte[] encodePassword(String pw){
        return Base64.encodeBase64(pw.getBytes(StandardCharsets.UTF_8));
    }

    public String decodePassword(byte[] epw){
        return new String(Base64.decodeBase64(epw), StandardCharsets.UTF_8);
    }
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
        else if(containAtLeastOne(specialCharactes) || containAtLeastOne(digit)){
            mess = "There must be no special characters or numbers in the name.";
        }
        return mess;
    }

}
