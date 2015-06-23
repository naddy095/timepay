package com.example.timepay.timepay;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Validator {

    String emailRegex;
    Boolean email;


    public String validateAccountDetatis(String emailAddress, String phoneNumber) {

        String message = "Completed";
        email = emailValidate(emailAddress);

        if (emailAddress.isEmpty() || email==false) {
            message = "Email Address is not valid";
        } else {
            if (phoneNumber.isEmpty()) {
                message = "Phone Number cannot be empty";

            }
            else if(phoneNumber.length() != 10)
            {
                message="Phone number should be 10 digit";
            }

        }
        return message;
    }

    public void validateGPR(String fullName,
                              String address, String panNumber,
                              String cardNumber, String cardName,
                              String expiryMonth, String expiryYear) throws Exception {

        String message = "Completed";
        if (fullName.isEmpty()) {
            throw new Exception("Full Name cannot be empty");
        } else if (address.isEmpty()) {
            throw new Exception("Address cannot be empty");
        } else if (panNumber.isEmpty()) {
            throw new Exception("Pan Number cannot be empty");
        } else if (cardNumber.isEmpty()) {
            throw new Exception("Card Number cannot be empty");
        } else if (cardName.isEmpty()) {
            throw new Exception("Name on Card cannot be empty");
        } else if ((expiryMonth.isEmpty()) || (expiryMonth.equals("Month"))) {
            throw new Exception("Expiry Month cannot be empty");
        } else if ((expiryYear.isEmpty()) || (expiryYear.equals("Year"))) {
            throw new Exception("Expiry Year cannot be empty");
        }
    }

    public String validateVendorRegistration(String companyName,
                                             String shopName,
                                             String accountNumber,
                                             String ifscCode,
                                             String panNo) {
        String message="Completed";
        if(companyName.isEmpty()) {
            message = "Company Name cannot be empty";
        } else if (shopName.isEmpty()) {
            message = "Shop name cannot be empty";
        } else if (accountNumber.isEmpty()) {
            message = "Account Number cannot be empty";
        } else if (ifscCode.isEmpty()) {
            message = "IFSC Codecannot be empty";
        } else if (panNo.isEmpty()) {
            message = "Pan No cannot be empty";
        }
        return  message;
    }

    public String validatePrivilageVendorRegistration(String companyName,
                                                      String shopName,
                                                      String accountNumber,
                                                      String ifscCode,
                                                      String panNo) {
        String message="Completed";
        if(companyName.isEmpty()) {
            message = "Company Name cannot be empty";
        } else if (shopName.isEmpty()) {
            message = "Shop name cannot be empty";
        } else if (accountNumber.isEmpty()) {
            message = "Account Number cannot be empty";
        } else if (ifscCode.isEmpty()) {
            message = "IFSC Codecannot be empty";
        } else if (panNo.isEmpty()) {
            message = "Pan No cannot be empty";
        }
        return  message;
    }

    public Boolean emailValidate(String EmailAddress)
    {
        emailRegex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return EmailAddress.matches(emailRegex);
    }


    public void validateExpiryDate(String expiryMonth, String expiryYear) throws Exception{
        Calendar calendar=Calendar.getInstance();
        Integer currentMonthInInteger=Integer.parseInt(new SimpleDateFormat("MM").format(calendar.getTime()));
        Integer currentYearInInteger=Integer.parseInt(new SimpleDateFormat("yyyy").format(calendar.getTime()));
        Integer expiryMonthInInteger=Integer.parseInt(expiryMonth.substring(0, 2));
        Integer expiryYearInInteger=Integer.parseInt(expiryYear);

        Log.i("ExpiryDateRelatedData","ExpiryMonth :"+expiryMonthInInteger+" ExpiryYear :"+expiryYearInInteger);
        Log.i("CurrentDateRelatedData","CurrentMonth :"+currentMonthInInteger+" CurrentYear :"+currentYearInInteger);

        if ((expiryMonthInInteger<currentMonthInInteger)&&(expiryYearInInteger<=currentYearInInteger)){
            throw new Exception("Please Enter correct Expiry date");
        }
    }
}
