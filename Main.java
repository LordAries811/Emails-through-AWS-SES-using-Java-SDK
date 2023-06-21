package com.ses.services;

import java.util.Map;
import java.util.HashMap;
public class Main {
	public static void main(String[] args) {
        // Create the dynamic data map
        Map<String, String> dynamicData = new HashMap<>();
        dynamicData.put("paymentType", "Credit Card");
        dynamicData.put("paymentMethod", "Visa");
        dynamicData.put("amount", "100.00");

        // Send the email with dynamic data
        Email.send(dynamicData);
    }
}
