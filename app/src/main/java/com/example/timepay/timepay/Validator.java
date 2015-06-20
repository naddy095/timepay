package com.example.timepay.timepay;

import android.widget.EditText;

public class Validator {


    public String validateAccountDetatis(String emailAddress, String phoneNumber) {

        String message = "Completed";
        if (emailAddress.length() == 0) {
            message = "Email Address cannot be empty";
        } else if (phoneNumber.length() == 0) {
            message = "Phone Number cannot be empty";
        }
        return message;
    }

    public String validateGPR(String fullName,
                              String address, String panNumber,
                              String cardNumber, String cardName,
                              String expiryMonth, String expiryYear) {

        String message = "Completed";
        if (fullName.length() == 0) {
            message = "Full Name cannot be empty";
        } else if (address.length() == 0) {
            message = "Address cannot be empty";
        } else if (panNumber.length() == 0) {
            message = "Pan Number cannot be empty";
        } else if (cardNumber.length() == 0) {
            message = "Card Number cannot be empty";
        } else if (cardName.length() == 0) {
            message = "Name on Card cannot be empty";
        } else if ((expiryMonth.length() == 0) || (expiryMonth.equals("Month"))) {
            message = "Expiry Month cannot be empty";
        } else if ((expiryYear.length() == 0) || (expiryYear.equals("Year"))) {
            message = "Expiry Year cannot be empty";
        }
        return message;
    }

    public String validateVendorRegistration(EditText companyName,
                                             EditText shopName,
                                             EditText accountNumber,
                                             EditText ifscCode,
                                             EditText panNo) {
        String message="Completed";
        if(companyName.length() == 0) {
            message = "Full Name cannot be empty";
        } else if (shopName.length() == 0) {
            message = "Address cannot be empty";
        } else if (accountNumber.length() == 0) {
            message = "Pan Number cannot be empty";
        } else if (ifscCode.length() == 0) {
            message = "Card Number cannot be empty";
        } else if (panNo.length() == 0) {
            message = "Name on Card cannot be empty";
        }
        return  message;
    }
}
