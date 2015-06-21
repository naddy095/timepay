package com.example.timepay.timepay;

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

    public String validateGPR(String fullName,
                              String address, String panNumber,
                              String cardNumber, String cardName,
                              String expiryMonth, String expiryYear) {

        String message = "Completed";
        if (fullName.isEmpty()) {
            message = "Full Name cannot be empty";
        } else if (address.isEmpty()) {
            message = "Address cannot be empty";
        } else if (panNumber.isEmpty()) {
            message = "Pan Number cannot be empty";
        } else if (cardNumber.isEmpty()) {
            message = "Card Number cannot be empty";
        } else if (cardName.isEmpty()) {
            message = "Name on Card cannot be empty";
        } else if ((expiryMonth.isEmpty()) || (expiryMonth.equals("Month"))) {
            message = "Expiry Month cannot be empty";
        } else if ((expiryYear.isEmpty()) || (expiryYear.equals("Year"))) {
            message = "Expiry Year cannot be empty";
        }
        return message;
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


}
