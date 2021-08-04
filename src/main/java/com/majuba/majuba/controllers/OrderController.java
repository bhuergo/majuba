package com.majuba.majuba.controllers;


import com.majuba.majuba.MajubaApplication;
import com.majuba.majuba.entities.Order;
import com.majuba.majuba.services.*;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;
import org.graalvm.compiler.core.target.Backend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    @Autowired
    private TableService tableService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/checkout/{table_id}")
    public ModelAndView showFinal(@PathVariable Long table_id,HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("pay");
        Map<String,?> map = RequestContextUtils.getInputFlashMap(request);
        Order order = orderService.showOrder(table_id);

        if(map !=null){
            mav.addObject("preferenceId",map.get("preferenceId"));
        }
        mav.addObject("order", order);
        mav.addObject("order_elements", cartService.findByOrder(order.getOrder_id()));
        return mav;
    }


    @PostMapping("/payment/{order_id}")
    public RedirectView checkout(@PathVariable Long order_id, @RequestParam String clientName
            , @RequestParam String email, RedirectAttributes attributes) throws MPException, MPConfException {
        orderService.setEmail(order_id, clientName, email);
        Order order = orderService.findOrderById(order_id);
        String emailBody = emailService.cuerpo(order, clientName);
        emailService.enviarCorreo(email, "Pagar pedido 000" + order_id, emailBody);
        System.out.println("correo enviado");


            //MERCADOPAGO
        MercadoPago.SDK.setAccessToken("TEST-5245977742845310-080321-39fc00d3f23246acf95337f68679ad4f-275572218");

            //crear objeto preferencia
        Preference preference = new Preference();

            //Creo item en la preferencia
        Item item = new Item();
        item.setTitle("Orden "+order_id)
                .setQuantity(1)
                .setUnitPrice((float)order.getTotal().doubleValue());
        preference.appendItem(item);

        preference.save();

            //Paso el id al MAV
        attributes.addFlashAttribute("preferenceId",preference.getId());







//        tableService.resetTable(order.getTable().getTable_id());
//        cartService.deleteAll(order_id);
//        orderService.deleteOrder(order);






        return new RedirectView("/checkout/"+order.getTable().getTable_id());
    }

}
