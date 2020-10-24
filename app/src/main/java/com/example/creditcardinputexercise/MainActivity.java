package com.example.creditcardinputexercise;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout get_card;
    private TextInputLayout get_date;
    private TextInputLayout get_security_code;
    private TextInputLayout get_first_name;
    private TextInputLayout get_last_name;

    private TextInputEditText get_card_number;
    private TextInputEditText get_mm_yy_edit;
    private TextInputEditText get_security_code_edit;
    private TextInputEditText get_first_name_edit;
    private TextInputEditText get_last_name_edit;
    private Button submit_button;
    private String card_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        get_card = findViewById(R.id.card_number);
        get_date = findViewById(R.id.mm_yy);
        get_security_code = findViewById(R.id.security_code);
        get_first_name = findViewById(R.id.first_name);
        get_last_name = findViewById(R.id.last_name);

        get_card_number = findViewById(R.id.card_number_edit);
        get_mm_yy_edit = findViewById(R.id.mm_yy_edit);
        get_security_code_edit = findViewById(R.id.security_code_edit);
        get_first_name_edit = findViewById(R.id.first_name_edit);
        get_last_name_edit = findViewById(R.id.last_name_edit);
        submit_button = findViewById(R.id.submit);

        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                set_error_false();
                clear_focus();
                String cvv = get_security_code_edit.getText().toString();
                String card_num = get_card_number.getText().toString();
                String last_name = get_last_name_edit.getText().toString();
                String first_name = get_first_name_edit.getText().toString();
                String dt = get_mm_yy_edit.getText().toString();
                if (checkPaymentValidity(card_num) && checkCvv(cvv, card_name) && check_name(first_name, last_name) && date(dt)){
                    display_alert();
                }
            }
        });
    }

    //clear focus before displaying the alert
    public void clear_focus(){
        get_last_name.clearFocus();
        get_card_number.clearFocus();
        get_mm_yy_edit.clearFocus();
        get_security_code_edit.clearFocus();
        get_first_name_edit.clearFocus();
        get_last_name_edit.clearFocus();
        submit_button.clearFocus();
    }

    //clear error messages and padding before displaying alert
    public void set_error_false(){
        get_card.setErrorEnabled(false);
        get_date.setErrorEnabled(false);
        get_security_code.setErrorEnabled(false);
        get_first_name.setErrorEnabled(false);
        get_last_name.setErrorEnabled(false);
    }

    //check if the card name if valid, not empty and correct
    public boolean checkPaymentValidity(String card_num) {
        CreditCardValidity validity = new CreditCardValidity();
        boolean length = validity.check_length(card_num);
        boolean str = validity.check_substring(card_num);
        boolean luhn = validity.checkLuhn(card_num);
        if (!length) {
            get_card.setError("Invalid length");
            return false;
        }
        if (!str) {
            get_card.setError("Invalid start number");
            return false;
        }
        if (!luhn) {
            get_card.setError("Invalid number");
            return false;
        }
        if (length && str && luhn) {
            card_name = validity.get_card_name(card_num);
            Log.d("MainActivity", card_name);
            get_card.setError(null);
            return true;
        }
        return false;
    }

    //check if cvv field is valid
    public boolean checkCvv(String cvv, String card_name) {
        int len = cvv.length();

        if (!field_empty(cvv)){
            get_security_code.setError("Field Empty");
            return false;
        }
        else{
            if (card_name != null){
                if (card_name == "Visa" || card_name == "Master" || card_name == "Discover"){
                    if (len == 3){
                        get_card_number.setError(null);
                        return true;
                    }
                    else{
                        get_security_code.setError("Invalid length");
                        return false;
                    }
                }
                else if (card_name == "American"){
                    if (len == 4){
                        get_card_number.setError(null);
                        return true;
                    }
                    else{
                        get_security_code.setError("Invalid length");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //check if field is empty
    public boolean field_empty(String name){
        int len = name.length();
        boolean ret = true;
        if (len <= 0){
            ret = false;
        }
        else if (len > 0){
            ret = true;
        }
        return ret;
    }

    //individual name field check
    public boolean name_matches(String name){
        int len = name.length();
        boolean ret = true;
        if(!name.matches("[a-zA-Z ]+")) {
            ret = false;
        }
        return ret;
    }

    //integrated name field check
    public boolean check_name(String first_name, String last_name){
        if(!field_empty(first_name)){
            get_first_name.setError("Field Empty");
            return false;
        }
        if(!field_empty(last_name)){
            get_last_name.setError("Field Empty");
            return false;
        }
        if(!name_matches(first_name)) {
            get_first_name.setError("Invalid name");
            return false;
        }
        if(!name_matches(last_name)){
            get_last_name.setError("Invalid name");
            return false;
        }
        return true;
    }

    //individual date field matches
    public boolean date_matches(String date_text){
        boolean ret = true;
        if(!date_text.matches("^(0[1-9]|1[0-2])/[0-9]{2}$")) {
            ret = false;
        }
        return ret;
    }

    //individual date field check
    public boolean date_field_length(String date_text){
        int len = date_text.length();
        boolean ret = true;
        if(len > 5 || len < 5) {
            ret = false;
        }
        return ret;
    }

    //integrated check date field for empty and format
    public boolean date(String date_text){
        if(!field_empty(date_text)){
            get_date.setError("Field Empty");
            return false;
        }
        if(!date_field_length(date_text)){
            get_date.setError("Invalid Length");
            return false;
        }
        if (!date_matches(date_text)) {
            get_date.setError("Wrong format");
            return false;
        }
        if(!check_expiration(date_text)) {
            get_date.setError("Card Expired");
            return false;
        }
        return true;
    }

    //check expiration date
    public boolean check_expiration(String date_text){
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH) + 1;
        int year = c.get(Calendar.YEAR);
        int y2 = Integer.parseInt(String.valueOf(year).substring(2, 4));
        int d1 = Integer.parseInt(date_text.substring(0, 2));
        int y1 = Integer.parseInt(date_text.substring(3, 5));

        if (y1 > 0 && y1 < 20){
            return false;
        }
        if (y1 < 100 && y1 > 50){
            return false;
        }
        if (y1 == y2 && d1 < month){
            return false;
        }
        return true;
    }

    //display alert
    public void display_alert(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Payment Successful");
        dialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        //Toast.makeText(getApplicationContext(),"Payment Successful",Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }
}