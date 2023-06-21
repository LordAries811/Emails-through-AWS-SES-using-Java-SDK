package com.ses.services;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;

import java.util.Map;
//import java.util.ArrayList;
//import java.util.List;

public class Email {
    public static void send(Map<String, String> dynamicData) {
        // Extract dynamic data from the HashMap
        String paymentType = dynamicData.get("paymentType");
        String paymentMethod = dynamicData.get("paymentMethod");
        String amount = dynamicData.get("amount");

        String TO = "adityabikramhalder@gmail.com";
        String FROM = "adityabikramhalder@gmail.com";
        try {
            // Existing code for sending email
            Regions region = Regions.AP_SOUTH_1;
            AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard().withRegion(region)
                    .build();
            SendEmailRequest sendEmailRequest = new SendEmailRequest();
            sendEmailRequest.setSource(FROM);

            java.util.List<String> toAddresses = new java.util.ArrayList<String>();
            toAddresses.add(TO);
            Destination destination = new Destination(toAddresses);

            sendEmailRequest.setDestination(destination);

            // Create the email subject and body using dynamic data
            String emailSubject = "Payment Details";
            String emailBody = "<html>" +
                    "<head></head>" +
                    "<body>" +
                    "<h1>Payment Details</h1>" +
                    "<p>Payment Type: " + paymentType + "</p>" +
                    "<p>Payment Method: " + paymentMethod + "</p>" +
                    "<p>Amount: " + amount + "</p>" +
                    "</body>" +
                    "</html>";

            Content subject = new Content(emailSubject);
            Content htmlContent = new Content(emailBody);
            Body body = new Body().withHtml(htmlContent);
            Message message = new Message(subject, body);
            sendEmailRequest.setMessage(message);

            // Send the email
            SendEmailResult response = client.sendEmail(sendEmailRequest);
            if (response.getMessageId() != null) {
                System.out.println("Email SENT!");
            } else {
                System.out.println("Error");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
