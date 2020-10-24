package com.example.creditcardinputexercise;

import android.util.Log;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.Console;
//check card validity
public class CreditCardValidity {

    public CreditCardValidity(){ }

    public boolean checkLuhn(String get_card_number) {
        int security_length = get_card_number.length();
        int sum = 0;
        int count = 1;
        for (int i = security_length - 1; i >= 0; i--) {
            int value = get_card_number.charAt(i) - '0';
            if (count % 2 == 0)
                value = value * 2;
            sum += value / 10;
            sum += value % 10;
            count = count + 1;
        }
        return (sum % 10 == 0);
    }

    public boolean check_length(String get_card_number){
        int security_length = get_card_number.length();
        if (security_length >= 13 && security_length <= 16)
            return true;
        else
            return false;
    }

    //check if the first few characters are right
    public boolean check_substring(String get_card_number){
        int security_length = get_card_number.length();
        boolean ret = false;
        if (security_length != 0){
            if (Integer.parseInt(get_card_number.substring(0, 1)) == 4 || Integer.parseInt(get_card_number.substring(0, 1)) == 5 || Integer.parseInt(get_card_number.substring(0, 1)) == 6
                    || Integer.parseInt(get_card_number.substring(0, 2)) == 37){
                ret = true;
            }
            else{
                ret = false;
            }
        }
        return ret;
    }

    //get card name based on first few characters
    public String get_card_name(String get_card_number){
        String security_code = get_card_number;
        String ret = "";
        if (Integer.parseInt(security_code.substring(0, 1)) == 4){
            ret = "Visa";
        }
        if (Integer.parseInt(security_code.substring(0, 1)) == 5){
            ret = "Master";
        }
        if (Integer.parseInt(security_code.substring(0, 1)) == 6) {
            ret = "Discover";
        }
        if (Integer.parseInt(security_code.substring(0, 2)) == 37){
            ret = "American";
        }
        return ret;
    }
}
