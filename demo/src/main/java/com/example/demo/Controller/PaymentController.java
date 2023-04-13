package com.example.demo.Controller;

//import com.example.demo.dto.CreatePaymentDTO;
import com.example.demo.Model.PaymentDetails;
import com.example.demo.Service.PaymentService;
import com.example.demo.Service.UserService;
import com.example.demo.dto.CreatePaymentDTO;
import com.example.demo.dto.CreatePaymentResponseDTO;
import com.example.demo.dto.UserDTO;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.nio.file.Paths;

@RestController
@RequestMapping(value ="api/v1/user")
@CrossOrigin(origins ="*", allowedHeaders = "*")
public class PaymentController {
    @Autowired
    private UserService userService;
    @Autowired
    private PaymentService paymentService;

    private static Gson gson = new Gson();
    @PostMapping("/create-payment-intent")
    public CreatePaymentResponseDTO createPaymentsIntent(@RequestBody CreatePaymentDTO createPayment, @AuthenticationPrincipal UserDetails userDetails) throws StripeException {
       UserDTO user = userService.findUserID(userDetails);
        System.out.println("createPayment");
        System.out.println(createPayment);

        PaymentDetails paymentDetails = paymentService.savePaymentDetails(user,createPayment.getAmount(),createPayment.getProjectID());
     //   this.paymentStatus(paymentDetails);

        //System.out.println("Payment section");
          //  System.out.println(user);
       // port(4242);
       // staticFiles.externalLocation(Paths.get("public").toAbsolutePath().toString());

        CustomerCreateParams customerParams = new CustomerCreateParams.Builder().build();
        Customer customer = Customer.create(customerParams);

      //  CreatePaymentDTO postBody = gson.fromJson(request.body(), CreatePaymentDTO.class);
      //  CreatePaymentDTO postBody = gson.fromJson(createPayment.getItems(), CreatePaymentDTO.class);
        PaymentIntentCreateParams params = new PaymentIntentCreateParams.Builder()
                            .setCustomer(customer.getId())
                            .setSetupFutureUsage(PaymentIntentCreateParams.SetupFutureUsage.OFF_SESSION)
                            .setAmount((long) (Double.parseDouble(createPayment.getAmount()) * 100))
                            .setCurrency("usd")
                            .setAutomaticPaymentMethods(
                                    PaymentIntentCreateParams.AutomaticPaymentMethods
                                            .builder()
                                            .setEnabled(true)
                                            .build()
                            )
                            .build();

            // Create a PaymentIntent with the order amount and currency
            PaymentIntent paymentIntent = PaymentIntent.create(params);
            return  new CreatePaymentResponseDTO(paymentIntent.getClientSecret());

    }


    @PutMapping("/paymentDetails")
    public  void PaymentDetails() throws StripeException {
     //   UserDTO user = userService.findUserID(userDetails);
     //   System.out.println(createPayment);
       // paymentService.updatePaymentStatus(user,createPayment);



        System.out.println("Payment section");
     //   System.out.println(user);


    }
}
