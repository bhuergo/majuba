package com.majuba.majuba.services;

import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.datastructures.payment.Payer;
import org.springframework.stereotype.Controller;

@Controller
public class PaymentService {


        public void mppayment() throws MPException, MPConfException {
            MercadoPago.SDK.setAccessToken("TEST-5245977742845310-080321-39fc00d3f23246acf95337f68679ad4f-275572218");

            Payment payment = new Payment()
                    .setTransactionAmount(100f)
                    .setToken("your_cardtoken")
                    .setDescription("description")
                    .setInstallments(1)
                    .setPaymentMethodId("visa")
                    .setPayer(new Payer()
                            .setEmail("dummy_email"));

            payment.save();
        }
    }


