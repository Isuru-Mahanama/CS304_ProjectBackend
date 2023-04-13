package com.example.demo.dto;

import com.stripe.exception.CardException;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.model.PaymentMethodCollection;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentMethodListParams;

public class ChargeCustomerDTO {
    static void chargeCustomer(String customerId) throws StripeException {
        // Lookup the payment methods available for the customer
        PaymentMethodListParams listParams = new PaymentMethodListParams.Builder().setCustomer(customerId)
                .setType(PaymentMethodListParams.Type.CARD).build();
        PaymentMethodCollection paymentMethods = PaymentMethod.list(listParams);
        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder().setCurrency("usd")
                .setAmount( Long.valueOf (1099))
                .setPaymentMethod(paymentMethods.getData().get(0).getId())
                .setCustomer(customerId)
                .setConfirm(true)
                .setOffSession(true)
                .build();
        try {
            // Charge the customer and payment method immediately
            PaymentIntent paymentIntent = PaymentIntent.create(createParams);
        } catch (StripeException err) {
            // Error code will be authentication_required if authentication is needed
            System.out.println("Error code is : " + err.getCode());
            String paymentIntentId = err.getStripeError().getPaymentIntent().getId();
            PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);
            System.out.println(paymentIntent.getId());
        }
    }

}
