package com.majuba.majuba.services;

import com.majuba.majuba.entities.Cart;
import com.majuba.majuba.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private CartService cartService;
    @Autowired
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String from;

    public void enviarCorreo(String to, String asunto,String cuerpo)  {

       new Thread(() -> {
            System.out.println("Enviando correo a "+to);
            try {

                //codigo para enviar el mail - setea el asunto, cuerpo,destinatario
                MimeMessage message = sender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message,"UTF-8");
                helper.setFrom(from);
                helper.setTo(to);
                helper.setSubject(asunto);
                helper.setText(cuerpo,true);

                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(cuerpo,"text/html");

//                String urlLogo = "src/main/resources/static/images/logo.png";

                /*MimeBodyPart imagePart = new MimeBodyPart();
                imagePart.attachFile(urlLogo);
                imagePart.setContentID("<logoMajubaEncabezado>");
                imagePart.setDisposition(Part.INLINE);*/

                sender.send(message);

            } catch (MessagingException e) {
                e.printStackTrace(); // chequear el mensaje.
            }

      }).run();

    }

    public String cuerpo(Order order, String name) {
        List<Cart> carts = cartService.findByOrder(order.getOrder_id());
        return "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\" bgcolor=\"#F5F5F5\" style=\"padding:0 10px\">\n" +
                "  <tbody><tr>\n" +
                "    <td height=\"20\"></td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td align=\"center\">\n" +
                "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"600px\" bgcolor=\"#FFFFFF\" id=\"m_4732803484524104983order\" style=\"font-family:Arial,sans-serif\">\n" +
                "        <tbody><tr>\n" +
                "          <td align=\"center\">\n" +
                "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\">\n" +
                "              <tbody><tr>\n" +
                "                <td height=\"20\"></td>\n" +
                "              </tr>\n" +
                "              <tr align=\"center\">\n" +
                "                <td>\n" +
                "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\">\n" +
                "                    <tbody><tr>\n" +
                "                      <td width=\"40\"></td>\n" +
                "                      <td>\n" +
                "                        <img src=\"https://ci5.googleusercontent.com/proxy/UDiOeLichpu9SXtqKrU1vuc0CnAswwW-9O2IQ6VHISl929q5X6gtiR55zZYGpaenSV7DpRiMCV5uGBa3NUcgE3NlpnDp=s0-d-e1-ft#https://images.rappi.com/web/gifts/icons/rappi.png\" alt=\"\" height=\"30px\" width=\"60px\" class=\"CToWUd\">\n" +
                "                      </td>\n" +
                "                      <td width=\"40\"></td>\n" +
                "                    </tr>\n" +
                "                  </tbody></table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td height=\"30\"></td>\n" +
                "              </tr>\n" +
                "              \n" +
                "              <tr>\n" +
                "                <td>\n" +
                "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\">\n" +
                "                    <tbody><tr>\n" +
                "                      <td width=\"40\"></td>\n" +
                "                      <td>\n" +
                "                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\">\n" +
                "\n" +
                "                          <tbody><tr>\n" +
                "                            <td>\n" +
                "                              <p style=\"color:#332927;line-height:1.2;letter-spacing:-2;font-size:2.5em;font-weight:bold;padding-top:10px;margin:0px\">\n" +
                "                                Hola, <span text=\"name\"></span>.</p>\n" +
                "                              <p style=\"color:#706967;padding-top:10px;font-size:1.5em\">\n" +
                "                                Gracias por tu pedido\n" +
                "                                por <span class=\"il\">Rappi</span></p>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                          <tr>\n" +
                "                            <td height=\"0\"></td>\n" +
                "                          </tr>\n" +
                "                        </tbody></table>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </tbody></table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </tbody></table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "\n" +
                "        \n" +
                "\n" +
                "\n" +
                "        \n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\">\n" +
                "              <tbody><tr>\n" +
                "                <td height=\"20\"></td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td width=\"5%\"></td>\n" +
                "                <td>\n" +
                "                  <table cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\">\n" +
                "                    <tbody><tr>\n" +
                "                      <td style=\"padding:15px 15px\">\n" +
                "                        \n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "\n" +
                "                  </tbody></table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </tbody></table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        \n" +
                "\n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\">\n" +
                "              <tbody><tr>\n" +
                "                <td height=\"20\"></td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td width=\"5%\"></td>\n" +
                "                <td>\n" +
                "                  <table cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\" style=\"border-bottom:1px solid rgba(0,0,0,0.14)\">\n" +
                "                    <tbody><tr>\n" +
                "                      <td style=\"padding:15px 15px\">\n" +
                "                        <p style=\"font-weight:600;font-size:1.5em\">Tu pedido</p>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                      <td style=\"padding:10px 15px;vertical-align:top\">\n" +
                "                        <table width=\"100%\">\n" +
                "                          <tbody><tr>\n" +
                "                            <td width=\"40\" style=\"vertical-align:top\">\n" +
                "                              <img height=\"30px\" src=\"https://ci3.googleusercontent.com/proxy/h7zjg6UawgDlNQjrpoTmNpZrM32GeccI4YRUccp4FMcDszbVQfuVA1_OK51WtfO4LTqoZ2a_MKvnSoRj8vq2XTm6YZf082PmAEFpzCc-hWfyHmT_EXrj0aRU6mQq6-9u_A=s0-d-e1-ft#https://images.rappi.com.ar/restaurants_logo/green-1622668444898.png?d=100x100\" alt=\"\" style=\"display:block;height:auto;max-height:70px;margin-left:auto;margin-right:auto;max-width:100%;margin-top:0\" class=\"CToWUd\">\n" +
                "                            </td>\n" +
                "                            <td width=\"10px\"></td>\n" +
                "                            <td style=\"vertical-align:top\">\n" +
                "                              <p style=\"font-size:18px;color:#332927;font-weight:bold;margin:0\">\n" +
                "                                Green Eat - Abasto Plaza</p>\n" +
                "\n" +
                "                              <p style=\"color:#ff7d00;margin:0;font-size:14px;display:none\">\n" +
                "                                â“˜ Entregado con cambios</p>\n" +
                "\n" +
                "                              <p style=\"font-size:14px;margin-top:10px;color:#706967\">\n" +
                "                                <img goomoji=\"1f5fa\" data-goomoji=\"1f5fa\" style=\"margin:0 0.2ex;vertical-align:middle;max-height:24px\" alt=\"ðŸ—º\" src=\"https://mail.google.com/mail/e/1f5fa\" data-image-whitelisted=\"\" class=\"CToWUd\"> Anchorena 580, Caba</p>\n" +
                "                              <p style=\"font-size:14px;margin-top:5px;color:#706967\">\n" +
                "                                <img goomoji=\"1f55b\" data-goomoji=\"1f55b\" style=\"margin:0 0.2ex;vertical-align:middle;max-height:24px\" alt=\"ðŸ•›\" src=\"https://mail.google.com/mail/e/1f55b\" data-image-whitelisted=\"\" class=\"CToWUd\"> 2021-06-12 13:02:48</p>\n" +
                "                              <p style=\"font-size:0.9em;margin-top:10px;color:#0cc665;font-weight:600\">\n" +
                "                                <a style=\"color:#0cc665;font-weight:600\" href=\"http://link.support.rappimail.com/ls/click?upn=wjaWOmlOXN5j8oLcClPcNBjUANLa-2FzDkI-2Bnc5dU9o97mepjkYDxxtiruoWKSoDoJ2vNr_XAqE1bivOUeWaaWO3Xvevfi5EKV1gmjmR5c7bmgsul83aCzi5liMfoJ5MS4MQZ4hjsTyP6iTCtHWovMwXjoR90IIB7E2Eq8gSNmZGscyMFY0ycp0lmecYLmqxPVo3Le9uWMlrmrEMAKrrHeuV-2B9Zt0gNOaeSDrnFo8A7f5-2B66VXqGB9nly0NVzu6SbgM1suOQbxp6Jgbu0UqPNS-2FUY8ayvI3WNeTE4L74zPXIlGgGWBXCdU5Y87zN8igDLJlFAlF32tgYYlsWtq-2Fn1sZ5m3J3c1NTCVPpvDRDAIkrertwcC-2FAX-2FwZZvNqWH1OrEFoEuXMH8c0gFwcfKEKj21R8JcduGlBWgFxYx862EEqLxffHqh8E3psOMnQfLuUAzBWnKzmYowg0m0doVDsbdpD2ZP1xhEWuWvdJNw64p-2FScIBiKHHaIx3MThPlqCOybImj6TfTHIj2jNhY5CI3LzanuZkV2NRJhKana4EIcXUDoQ0NXyHYwFgz2kwbx1g-2Fz282xo7k4oh112ETOSkTba5scqnbuZg9wBy6XyecCw89ccS95RznAQyBzrF0IVceya3-2Fw3k0PRmNOX1xGKIvqr8xEoabE2d9shuGQLPX0WU-2F2gj0J-2FyilDfmgVtKC79uHY3zPOPjyySDGZAuR7KESSI9S2CwdlIT0sFgHf2ZpGOAxpvM34-3D\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=http://link.support.rappimail.com/ls/click?upn%3DwjaWOmlOXN5j8oLcClPcNBjUANLa-2FzDkI-2Bnc5dU9o97mepjkYDxxtiruoWKSoDoJ2vNr_XAqE1bivOUeWaaWO3Xvevfi5EKV1gmjmR5c7bmgsul83aCzi5liMfoJ5MS4MQZ4hjsTyP6iTCtHWovMwXjoR90IIB7E2Eq8gSNmZGscyMFY0ycp0lmecYLmqxPVo3Le9uWMlrmrEMAKrrHeuV-2B9Zt0gNOaeSDrnFo8A7f5-2B66VXqGB9nly0NVzu6SbgM1suOQbxp6Jgbu0UqPNS-2FUY8ayvI3WNeTE4L74zPXIlGgGWBXCdU5Y87zN8igDLJlFAlF32tgYYlsWtq-2Fn1sZ5m3J3c1NTCVPpvDRDAIkrertwcC-2FAX-2FwZZvNqWH1OrEFoEuXMH8c0gFwcfKEKj21R8JcduGlBWgFxYx862EEqLxffHqh8E3psOMnQfLuUAzBWnKzmYowg0m0doVDsbdpD2ZP1xhEWuWvdJNw64p-2FScIBiKHHaIx3MThPlqCOybImj6TfTHIj2jNhY5CI3LzanuZkV2NRJhKana4EIcXUDoQ0NXyHYwFgz2kwbx1g-2Fz282xo7k4oh112ETOSkTba5scqnbuZg9wBy6XyecCw89ccS95RznAQyBzrF0IVceya3-2Fw3k0PRmNOX1xGKIvqr8xEoabE2d9shuGQLPX0WU-2F2gj0J-2FyilDfmgVtKC79uHY3zPOPjyySDGZAuR7KESSI9S2CwdlIT0sFgHf2ZpGOAxpvM34-3D&amp;source=gmail&amp;ust=1625768999142000&amp;usg=AFQjCNESrn_inh2dKRIJVWJLC90lbMY96A\" fg_scanned=\"1\">\n" +
                "                                  Calificar </a>\n" +
                "                              </p>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody></table>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </tbody></table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </tbody></table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "\n" +
                "        \n" +
                "\n" +
                "\n" +
                "        \n" +
                "        \n" +
                "\n" +
                "        \n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\">\n" +
                "              <tbody><tr>\n" +
                "                <td height=\"20\"></td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td width=\"5%\"></td>\n" +
                "                <td>\n" +
                "                  <table cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\">\n" +
                "                    <tbody><tr>\n" +
                "                      <td style=\"padding:0px 15px\">\n" +
                "                        <p style=\"font-weight:600;font-size:20px\">Productos</p>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </tbody></table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </tbody></table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        \n" +
                "\n" +
                "        \n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\">\n" +
                "              <tbody><tr>\n" +
                "                <td height=\"20\"></td>\n" +
                "              </tr>\n" +
                "\n" +
                "              <tr>\n" +
                "                <td width=\"5%\"></td>\n" +
                "\n" +
                "                <td>\n" +
                "                  <table cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\" style=\"border-bottom:1px solid rgba(0,0,0,0.14)\">\n" +
                "                    <tbody><tr>\n" +
                "                      <td style=\"padding:0 15px\">\n" +
                "\n" +
                "                        <table width=\"100%\" style=\"padding:.5em 0em .4em 0em\">\n" +
                "                          <tbody><tr>\n" +
                "                            <td width=\"50\">\n" +
                "                              <img height=\"30px\" src=\"https://ci6.googleusercontent.com/proxy/zoPbyN9XFC7wQaee52ZCMXLFJF-bpexlGnAPitDGAzN5bkKes8QOX_Kcna1ER_YB9dYElEmnO0mdKI-fIC3YyRVUWvPnd63As-qfR8jwk7KVANr6pJpGt02v=s0-d-e1-ft#https://images.rappi.com.ar/products/571861-1622121072350.jpg?d=100x100\" alt=\"\" style=\"display:block;height:auto;max-height:70px;margin-left:auto;margin-right:auto;max-width:100%\" class=\"CToWUd\">\n" +
                "                            </td>\n" +
                "                            <td width=\"10px\"></td>\n" +
                "                            <td>\n" +
                "                              <p style=\"color:#332927;line-height:1.5;letter-spacing:-0.5;font-size:16px;margin:0px;font-weight:bold\">\n" +
                "                                Porto Cheese Burger</p>\n" +
                "                              <p style=\"color:#6b6f72;line-height:1.5;letter-spacing:-0.5;font-size:14px;margin:0px\">\n" +
                "                                2\n" +
                "                                unidades</p>\n" +
                "                              <p style=\"display:none;color:#6b6f72;line-height:1.5;letter-spacing:-0.5;font-size:14px;margin:0px\">\n" +
                "                                Antes\n" +
                "                                <span style=\"text-decoration:line-through;margin:0px\">$1.070,00</span>\n" +
                "                              </p>\n" +
                "                            </td>\n" +
                "                            <td align=\"right\">\n" +
                "                              <p style=\"color:#332927;line-height:1.5;letter-spacing:-0.5;font-size:16px;font-weight:bold;margin:0px;margin-top:0\">\n" +
                "                                $1.070,00</p>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                          <tr style=\"height:12px\">\n" +
                "                            <td></td>\n" +
                "                          </tr>\n" +
                "                        </tbody></table>\n" +
                "\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "\n" +
                "                  </tbody></table>\n" +
                "                  <table cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\" style=\"border-bottom:1px solid rgba(0,0,0,0.14)\">\n" +
                "                    <tbody><tr>\n" +
                "                      <td style=\"padding:0 15px\">\n" +
                "\n" +
                "                        <table width=\"100%\" style=\"padding:.5em 0em .4em 0em\">\n" +
                "                          <tbody><tr>\n" +
                "                            <td width=\"50\">\n" +
                "                              <img height=\"30px\" src=\"https://ci5.googleusercontent.com/proxy/bxQzW5oVLr-QDwOPPDRKN6IgcSNWLvKYR744TxAaALzH8AeQpP3vx4-5Fau06cnEKQSnkbrGuu5AbgXd_f_yDDm_QcWo4gpnpuxQmKWzvqi29a033c-9HKrYqA=s0-d-e1-ft#https://images.rappi.com.ar/products/2227098-1621019321072.png?d=100x100\" alt=\"\" style=\"display:block;height:auto;max-height:70px;margin-left:auto;margin-right:auto;max-width:100%\" class=\"CToWUd\">\n" +
                "                            </td>\n" +
                "                            <td width=\"10px\"></td>\n" +
                "                            <td>\n" +
                "                              <p style=\"color:#332927;line-height:1.5;letter-spacing:-0.5;font-size:16px;margin:0px;font-weight:bold\">\n" +
                "                                Wrap Victoria IncreÃ\u00ADble</p>\n" +
                "                              <p style=\"color:#6b6f72;line-height:1.5;letter-spacing:-0.5;font-size:14px;margin:0px\">\n" +
                "                                1\n" +
                "                                unidad</p>\n" +
                "                              <p style=\"display:none;color:#6b6f72;line-height:1.5;letter-spacing:-0.5;font-size:14px;margin:0px\">\n" +
                "                                Antes\n" +
                "                                <span style=\"text-decoration:line-through;margin:0px\">$570,00</span>\n" +
                "                              </p>\n" +
                "                            </td>\n" +
                "                            <td align=\"right\">\n" +
                "                              <p style=\"color:#332927;line-height:1.5;letter-spacing:-0.5;font-size:16px;font-weight:bold;margin:0px;margin-top:0\">\n" +
                "                                $570,00</p>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                          <tr style=\"height:12px\">\n" +
                "                            <td></td>\n" +
                "                          </tr>\n" +
                "                        </tbody></table>\n" +
                "\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "\n" +
                "                  </tbody></table>\n" +
                "                </td>\n" +
                "                <td width=\"40\"></td>\n" +
                "              </tr>\n" +
                "            </tbody></table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        \n" +
                "\n" +
                "        \n" +
                "        <tr style=\"display:none\">\n" +
                "          <td>\n" +
                "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\">\n" +
                "              <tbody><tr>\n" +
                "                <td height=\"30\"></td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td width=\"5%\"></td>\n" +
                "                <td>\n" +
                "                  <table cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\">\n" +
                "                    <tbody><tr>\n" +
                "                      <td style=\"padding:0px 15px\">\n" +
                "                        <p style=\"font-weight:600;font-size:20px\">Productos Sustituidos\n" +
                "                        </p>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </tbody></table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </tbody></table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        \n" +
                "\n" +
                "        \n" +
                "        <tr style=\"display:none\">\n" +
                "          <td>\n" +
                "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\">\n" +
                "              <tbody><tr>\n" +
                "                <td height=\"20\"></td>\n" +
                "              </tr>\n" +
                "            </tbody></table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        \n" +
                "\n" +
                "        \n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\">\n" +
                "              <tbody><tr>\n" +
                "                <td height=\"20\"></td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td width=\"5%\"></td>\n" +
                "                <td>\n" +
                "                  <table cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\" style=\"border-bottom:1px solid rgba(0,0,0,0.14);padding-bottom:1em\">\n" +
                "                    <tbody><tr>\n" +
                "                      <td style=\"padding:0px 15px\">\n" +
                "                        <p style=\"font-weight:600;font-size:1.1em\">Resumen</p>\n" +
                "                      </td>\n" +
                "                      <td align=\"right\">\n" +
                "                        <p style=\"color:#0cc665;font-weight:bold;font-size:1em\">\n" +
                "                          <a style=\"color:#0cc665;font-weight:bold\" href=\"http://link.support.rappimail.com/ls/click?upn=wjaWOmlOXN5j8oLcClPcNBdgs32aBj2gAVMxRI6oiiQN12BOR8MGSKXkD-2FJoqDZMaZAw_XAqE1bivOUeWaaWO3Xvevfi5EKV1gmjmR5c7bmgsul83aCzi5liMfoJ5MS4MQZ4hjsTyP6iTCtHWovMwXjoR90IIB7E2Eq8gSNmZGscyMFY0ycp0lmecYLmqxPVo3Le9uWMlrmrEMAKrrHeuV-2B9Zt0gNOaeSDrnFo8A7f5-2B66VXqGB9nly0NVzu6SbgM1suOQbxp6Jgbu0UqPNS-2FUY8ayvI3WNeTE4L74zPXIlGgGWBXCdU5Y87zN8igDLJlFAlF32tgYYlsWtq-2Fn1sZ5m3J3c1NTCVPpvDRDAIkrertwcC-2FAX-2FwZZvNqWH1OrEFoEuXMH8c0gFwcfKEKj21R8JcduGlBWgFxYx862EEqLxffHqh8E3psOMnQfLuUAzBWnKzmYowg0m0doVDsbdpD2ZP1xhEWuWvdJNw64p-2FScIBiKHHaIx3MThPlqCOybImj6TfTHIj2jNhY5CI3LzanuZkV2NRJhKana4EIcXUDoQ0NXzEudhVQbhzLN6cB4qhXt3zS9Renm3kZ4ZVkQ1AW-2BJ-2BzALupKJ5SAzx93dIVqSrq2WNEZdWKc81dH6kiVb-2F9ib1m6nP9iNBvp-2BvjBW8L8zW-2Bh8pESuPUGrL2LQuDyRvxeHSopQv93xuEvc3tSaynbBqMT3CaNSzFdoT2DeJhYIJb82pPwkqO3hGWoNn6qYmYL0-3D\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=http://link.support.rappimail.com/ls/click?upn%3DwjaWOmlOXN5j8oLcClPcNBdgs32aBj2gAVMxRI6oiiQN12BOR8MGSKXkD-2FJoqDZMaZAw_XAqE1bivOUeWaaWO3Xvevfi5EKV1gmjmR5c7bmgsul83aCzi5liMfoJ5MS4MQZ4hjsTyP6iTCtHWovMwXjoR90IIB7E2Eq8gSNmZGscyMFY0ycp0lmecYLmqxPVo3Le9uWMlrmrEMAKrrHeuV-2B9Zt0gNOaeSDrnFo8A7f5-2B66VXqGB9nly0NVzu6SbgM1suOQbxp6Jgbu0UqPNS-2FUY8ayvI3WNeTE4L74zPXIlGgGWBXCdU5Y87zN8igDLJlFAlF32tgYYlsWtq-2Fn1sZ5m3J3c1NTCVPpvDRDAIkrertwcC-2FAX-2FwZZvNqWH1OrEFoEuXMH8c0gFwcfKEKj21R8JcduGlBWgFxYx862EEqLxffHqh8E3psOMnQfLuUAzBWnKzmYowg0m0doVDsbdpD2ZP1xhEWuWvdJNw64p-2FScIBiKHHaIx3MThPlqCOybImj6TfTHIj2jNhY5CI3LzanuZkV2NRJhKana4EIcXUDoQ0NXzEudhVQbhzLN6cB4qhXt3zS9Renm3kZ4ZVkQ1AW-2BJ-2BzALupKJ5SAzx93dIVqSrq2WNEZdWKc81dH6kiVb-2F9ib1m6nP9iNBvp-2BvjBW8L8zW-2Bh8pESuPUGrL2LQuDyRvxeHSopQv93xuEvc3tSaynbBqMT3CaNSzFdoT2DeJhYIJb82pPwkqO3hGWoNn6qYmYL0-3D&amp;source=gmail&amp;ust=1625768999142000&amp;usg=AFQjCNGRkZsV3rWpg9XDH2iVWEj3hHTJrQ\" fg_scanned=\"1\">Ver\n" +
                "                            resumen</a>\n" +
                "                        </p>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </tbody></table>\n" +
                "                </td>\n" +
                "                <td width=\"40\"></td>\n" +
                "              </tr>\n" +
                "            </tbody></table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "\n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\">\n" +
                "              <tbody><tr>\n" +
                "                <td height=\"20\"></td>\n" +
                "              </tr>\n" +
                "\n" +
                "              <tr>\n" +
                "                <td width=\"5%\"></td>\n" +
                "                <td>\n" +
                "                  <table cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\" style=\"border-bottom:1px solid rgba(0,0,0,0.14);padding-bottom:1em\">\n" +
                "                    <tbody><tr style=\"padding:10px 0;vertical-align:middle\">\n" +
                "                      <td style=\"padding:10px 15px\">\n" +
                "                        <p style=\"font-size:1em;color:#706967\">Costo de los productos</p>\n" +
                "                      </td>\n" +
                "\n" +
                "                      <td align=\"right\" style=\"vertical-align:middle\">\n" +
                "\n" +
                "                        <p style=\"color:#332927;font-size:16px\">$1.640,00</p>\n" +
                "\n" +
                "                      </td>\n" +
                "\n" +
                "                    </tr>\n" +
                "                    <tr style=\"padding:10px 0;vertical-align:middle\">\n" +
                "                      <td style=\"padding:10px 15px\">\n" +
                "                        <p style=\"font-size:1em;color:#706967\">Propina</p>\n" +
                "                      </td>\n" +
                "\n" +
                "                      <td align=\"right\" style=\"vertical-align:middle\">\n" +
                "\n" +
                "                        <p style=\"color:#332927;font-size:16px\">$25,00</p>\n" +
                "\n" +
                "                      </td>\n" +
                "\n" +
                "                    </tr>\n" +
                "                    <tr style=\"padding:10px 0;vertical-align:middle\">\n" +
                "                      <td style=\"padding:10px 15px\">\n" +
                "                        <p style=\"font-size:1em;color:#706967\">Domicilio</p>\n" +
                "                      </td>\n" +
                "\n" +
                "                      <td align=\"right\" style=\"vertical-align:middle\">\n" +
                "\n" +
                "                        <p style=\"color:#332927;font-size:16px\">$100,00</p>\n" +
                "\n" +
                "                      </td>\n" +
                "\n" +
                "                    </tr>\n" +
                "                    <tr style=\"padding:10px 0;vertical-align:middle\">\n" +
                "                      <td style=\"padding:10px 15px\">\n" +
                "                        <p style=\"font-size:1em;color:#706967\">Descuento por oferta</p>\n" +
                "                      </td>\n" +
                "\n" +
                "                      <td align=\"right\" style=\"vertical-align:middle\">\n" +
                "                        <p style=\"color:#ff441f;font-size:16px\">-$472,00</p>\n" +
                "                      </td>\n" +
                "\n" +
                "                    </tr>\n" +
                "                    <tr style=\"padding:10px 0\">\n" +
                "                      <td style=\"padding:10px 15px;vertical-align:middle\">\n" +
                "                        <p style=\"font-size:16px;color:#000000;font-weight:bold\">\n" +
                "                          Total pagado</p>\n" +
                "                      </td>\n" +
                "                      <td align=\"right\" style=\"vertical-align:middle\">\n" +
                "                        <p style=\"font-size:16px;color:#000000;font-weight:bold\">\n" +
                "                          $1.293,00</p>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </tbody></table>\n" +
                "                </td>\n" +
                "                <td width=\"40\"></td>\n" +
                "\n" +
                "              </tr>\n" +
                "            </tbody></table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        \n" +
                "\n" +
                "        \n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\">\n" +
                "              <tbody><tr>\n" +
                "                <td height=\"20\"></td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td width=\"5%\"></td>\n" +
                "                <td>\n" +
                "                  <table cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\">\n" +
                "                    <tbody><tr style=\"padding:10px 0;vertical-align:middle\">\n" +
                "                      <td style=\"padding:10px 15px\">\n" +
                "                        <p style=\"font-weight:600;font-size:18px\">Detalle de\n" +
                "                          transacciones</p>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </tbody></table>\n" +
                "                </td>\n" +
                "                <td width=\"40\"></td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td width=\"5%\"></td>\n" +
                "                <td>\n" +
                "                  <table cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\">\n" +
                "                    <tbody><tr style=\"padding:10px 0;vertical-align:middle\">\n" +
                "                      <td style=\"padding:10px 15px\">\n" +
                "                        <p style=\"color:#96999c;line-height:1.8;letter-spacing:-0.5;font-size:14px\">\n" +
                "                          12 de jun. de 2021 13:03</p>\n" +
                "                        <p style=\"font-size:16px;color:#706967\">PreautorizaciÃ³n\n" +
                "                          |\n" +
                "                          <img width=\"20px\" src=\"https://ci5.googleusercontent.com/proxy/C5CpwcaZ83KktBaBVXMFjE70fSd3bWzXhifWBcLbNf8T5461rDdEs0VN4UPSWeUxq6SMI_p92BWxRQ_SFPlEas93B5-i1rFX6bO8_Wf6=s0-d-e1-ft#https://img.rappi.com/support/icons/paymentMethods/visa.png\" alt=\"card.logo_type\" class=\"CToWUd\">\n" +
                "                          <span>9738</span>\n" +
                "                        </p>\n" +
                "                      </td>\n" +
                "                      <td align=\"right\" style=\"vertical-align:middle\">\n" +
                "                        <p style=\"font-size:16px;color:#6b6f72\">\n" +
                "                          $1.293,00</p>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                    <tr style=\"padding:10px 0;vertical-align:middle\">\n" +
                "                      <td style=\"padding:10px 15px\">\n" +
                "                        <p style=\"color:#96999c;line-height:1.8;letter-spacing:-0.5;font-size:14px\">\n" +
                "                          12 de jun. de 2021 13:49</p>\n" +
                "                        <p style=\"font-size:16px;color:#706967\">Cargo a TC\n" +
                "                          |\n" +
                "                          <img width=\"20px\" src=\"https://ci5.googleusercontent.com/proxy/C5CpwcaZ83KktBaBVXMFjE70fSd3bWzXhifWBcLbNf8T5461rDdEs0VN4UPSWeUxq6SMI_p92BWxRQ_SFPlEas93B5-i1rFX6bO8_Wf6=s0-d-e1-ft#https://img.rappi.com/support/icons/paymentMethods/visa.png\" alt=\"card.logo_type\" class=\"CToWUd\">\n" +
                "                          <span>9738</span>\n" +
                "                        </p>\n" +
                "                      </td>\n" +
                "                      <td align=\"right\" style=\"vertical-align:middle\">\n" +
                "                        <p style=\"font-size:16px;color:#6b6f72\">\n" +
                "                          $1.293,00</p>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </tbody></table>\n" +
                "                </td>\n" +
                "                <td width=\"40\"></td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td width=\"5%\"></td>\n" +
                "                <td>\n" +
                "                  <table cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\" style=\"border-bottom:1px solid rgba(0,0,0,0.14)\">\n" +
                "                    <tbody><tr style=\"padding:10px 0;vertical-align:middle\">\n" +
                "                      <td style=\"padding:10px 15px\">\n" +
                "                        <p style=\"font-size:16px;color:#000000;font-weight:bold\">\n" +
                "                          Cargo neto</p>\n" +
                "                      </td>\n" +
                "                      <td align=\"right\" style=\"vertical-align:middle\">\n" +
                "                        <p style=\"color:#000000;font-weight:bold;line-height:1.8;letter-spacing:-0.5;font-size:16px\">\n" +
                "                          $1.293,00</p>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </tbody></table>\n" +
                "\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </tbody></table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        \n" +
                "\n" +
                "        \n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\">\n" +
                "              <tbody><tr>\n" +
                "                <td height=\"50\"></td>\n" +
                "              </tr>\n" +
                "              <tr align=\"center\">\n" +
                "                <td width=\"5%\"></td>\n" +
                "                <td align=\"center\">\n" +
                "                  <table cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\" style=\"border-bottom:1px solid rgba(0,0,0,0.14);padding-bottom:1em\">\n" +
                "                    <tbody><tr>\n" +
                "                      <td style=\"padding:0px 15px\">\n" +
                "                        <img border=\"0\" style=\"display:block;color:#000000;text-decoration:none;font-family:arial,helvetica,sans-serif;font-size:16px;max-width:15%!important;width:20%;height:auto!important;margin:0 auto\" src=\"https://ci3.googleusercontent.com/proxy/pJ4EH6ERoEzE8M_Giryf-moexryVO0a5RKHsa90U_20nCl2fUcJGiKy5CJa3JxuHG-rWJPzpEoTH3lcqIy_UrEKy7P911GrqhC3U45XOrl_-Z_FzUcqsSDUSvlNr26Q3FeD67t9msYiJxn4rf0YtCjtDXR60ueuODEvcTI7h_8-Sm8yI9A=s0-d-e1-ft#http://cdn.mcauto-images-production.sendgrid.net/ebc09cbb48969be0/c7b7eb37-8018-4b30-ae78-1feee473a221/372x119.png\" alt=\"\" width=\"90\" class=\"CToWUd\">\n" +
                "\n" +
                "\n" +
                "                        <img border=\"0\" style=\"display:block;color:#000000;text-decoration:none;font-family:arial,helvetica,sans-serif;font-size:16px;max-width:100%!important;width:50%;height:auto!important;margin:30px auto\" src=\"https://ci5.googleusercontent.com/proxy/Bd1w25EHikTDTj46TWsRZcrtkz_9XTU_mRDPwsf9tOww7r6GA2CMTK738MA6jUgBd-Mr-mLNEiqcqIY5197gvyRnppc2vICTf3Es2aphngOFtq_3_gXGip08ZjmFw3Bu7XBMUivzL0KlUlTUb2hfrzNTVCaDTWsq4149_RgrpB6uTbyRB5k=s0-d-e1-ft#http://cdn.mcauto-images-production.sendgrid.net/ebc09cbb48969be0/1468cfe9-61bd-4049-abc6-03ea212494e6/1000x123.png\" alt=\"\" width=\"90\" class=\"CToWUd\">\n" +
                "\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </tbody></table>\n" +
                "                </td>\n" +
                "                <td width=\"40\"></td>\n" +
                "\n" +
                "              </tr>\n" +
                "            </tbody></table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "\n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\">\n" +
                "              <tbody><tr>\n" +
                "                <td height=\"10\"></td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td align=\"center\">\n" +
                "                  <table align=\"center\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                    <tbody>\n" +
                "                      <tr>\n" +
                "                        <td valign=\"top\" align=\"center\"><a href=\"http://link.support.rappimail.com/ls/click?upn=wjaWOmlOXN5j8oLcClPcNFSkKDIyGB-2FgyMXywndmdoiu76t2A5sfLkqfbofxWV1r-Lv8_XAqE1bivOUeWaaWO3Xvevfi5EKV1gmjmR5c7bmgsul83aCzi5liMfoJ5MS4MQZ4hjsTyP6iTCtHWovMwXjoR90IIB7E2Eq8gSNmZGscyMFY0ycp0lmecYLmqxPVo3Le9uWMlrmrEMAKrrHeuV-2B9Zt0gNOaeSDrnFo8A7f5-2B66VXqGB9nly0NVzu6SbgM1suOQbxp6Jgbu0UqPNS-2FUY8ayvI3WNeTE4L74zPXIlGgGWBXCdU5Y87zN8igDLJlFAlF32tgYYlsWtq-2Fn1sZ5m3J3c1NTCVPpvDRDAIkrertwcC-2FAX-2FwZZvNqWH1OrEFoEuXMH8c0gFwcfKEKj21R8JcduGlBWgFxYx862EEqLxffHqh8E3psOMnQfLuUAzBWnKzmYowg0m0doVDsbdpD2ZP1xhEWuWvdJNw64p-2FScIBiKHHaIx3MThPlqCOybImj6TfTHIj2jNhY5CI3LzanuZkV2NRJhKana4EIcXUDoQ0NXx-2Bkmgi1BjHAzItk2rK-2F4-2FZTalqMOEcyeyhn0Tzd6WsrA2wLnIcDXGifpa2gvaT5Jpp-2Bw51xaetdzeSoPzgQBag8516e31pcLgyfzq-2BZT-2Fl3KQ1iHQAkWwSuUEhvUCQY-2F7MgXIU3s5AmYGZkCWmplKU0La9Qit8jHHbT9quVFAG-2BWo1FTAaD0HRnai6pj1ZQps-3D\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=http://link.support.rappimail.com/ls/click?upn%3DwjaWOmlOXN5j8oLcClPcNFSkKDIyGB-2FgyMXywndmdoiu76t2A5sfLkqfbofxWV1r-Lv8_XAqE1bivOUeWaaWO3Xvevfi5EKV1gmjmR5c7bmgsul83aCzi5liMfoJ5MS4MQZ4hjsTyP6iTCtHWovMwXjoR90IIB7E2Eq8gSNmZGscyMFY0ycp0lmecYLmqxPVo3Le9uWMlrmrEMAKrrHeuV-2B9Zt0gNOaeSDrnFo8A7f5-2B66VXqGB9nly0NVzu6SbgM1suOQbxp6Jgbu0UqPNS-2FUY8ayvI3WNeTE4L74zPXIlGgGWBXCdU5Y87zN8igDLJlFAlF32tgYYlsWtq-2Fn1sZ5m3J3c1NTCVPpvDRDAIkrertwcC-2FAX-2FwZZvNqWH1OrEFoEuXMH8c0gFwcfKEKj21R8JcduGlBWgFxYx862EEqLxffHqh8E3psOMnQfLuUAzBWnKzmYowg0m0doVDsbdpD2ZP1xhEWuWvdJNw64p-2FScIBiKHHaIx3MThPlqCOybImj6TfTHIj2jNhY5CI3LzanuZkV2NRJhKana4EIcXUDoQ0NXx-2Bkmgi1BjHAzItk2rK-2F4-2FZTalqMOEcyeyhn0Tzd6WsrA2wLnIcDXGifpa2gvaT5Jpp-2Bw51xaetdzeSoPzgQBag8516e31pcLgyfzq-2BZT-2Fl3KQ1iHQAkWwSuUEhvUCQY-2F7MgXIU3s5AmYGZkCWmplKU0La9Qit8jHHbT9quVFAG-2BWo1FTAaD0HRnai6pj1ZQps-3D&amp;source=gmail&amp;ust=1625768999143000&amp;usg=AFQjCNHGyZYufByxdpcITjV2XrDmPemuMw\" fg_scanned=\"1\"><img title=\"Facebook\" src=\"https://ci5.googleusercontent.com/proxy/Td7-Nm-vbvMgF41i33XZygo-TwZwtx9RqVonv8IF1LIDQO3KgGjMLP0KIHINYw5n2V-UqDzuS75u1w_U0rcZOi4OC-OWdve4bRwpgYDv4fNjXRN9wP7TfMPtFGRfR7M2DAe4EpaP0Xf_6IuWkAZLQnN1L6P94sFSE_X27UAclGP-zCF5Ew=s0-d-e1-ft#http://cdn.mcauto-images-production.sendgrid.net/ebc09cbb48969be0/236dc6f7-f686-4e56-ba61-b376eba2d2b5/105x116.png\" alt=\"Fb\" width=\"35\" height=\"35\" class=\"CToWUd\"></a></td>\n" +
                "                        <td valign=\"top\" align=\"center\"><a href=\"http://link.support.rappimail.com/ls/click?upn=wjaWOmlOXN5j8oLcClPcNH1SpGNxFnnfhS0NorhRIXnQk0bUg7oPoGXuu2kA6DeekL1-2BgTqappHyr4MoWSjyQA-3D-3DkRM8_XAqE1bivOUeWaaWO3Xvevfi5EKV1gmjmR5c7bmgsul83aCzi5liMfoJ5MS4MQZ4hjsTyP6iTCtHWovMwXjoR90IIB7E2Eq8gSNmZGscyMFY0ycp0lmecYLmqxPVo3Le9uWMlrmrEMAKrrHeuV-2B9Zt0gNOaeSDrnFo8A7f5-2B66VXqGB9nly0NVzu6SbgM1suOQbxp6Jgbu0UqPNS-2FUY8ayvI3WNeTE4L74zPXIlGgGWBXCdU5Y87zN8igDLJlFAlF32tgYYlsWtq-2Fn1sZ5m3J3c1NTCVPpvDRDAIkrertwcC-2FAX-2FwZZvNqWH1OrEFoEuXMH8c0gFwcfKEKj21R8JcduGlBWgFxYx862EEqLxffHqh8E3psOMnQfLuUAzBWnKzmYowg0m0doVDsbdpD2ZP1xhEWuWvdJNw64p-2FScIBiKHHaIx3MThPlqCOybImj6TfTHIj2jNhY5CI3LzanuZkV2NRJhKana4EIcXUDoQ0NXx-2Fm9kdI-2Fdn0nyQcEUwngDxjPUcRB6J9gCmskfB50-2Fcr-2BJf0REBJfhDegDaIm-2B2j4tr4wDQ-2BKfl-2B0zJjJpS8nka7w4p7E0Sjl5BTy5lQNGINRRp08kTI0vSj4FLPiDDmZqnNJxx80ikODlIZtpgN6edFOzXMO1ORRthU6s60MUD91scjtYIokJjXL5ufIzgN6U-3D\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=http://link.support.rappimail.com/ls/click?upn%3DwjaWOmlOXN5j8oLcClPcNH1SpGNxFnnfhS0NorhRIXnQk0bUg7oPoGXuu2kA6DeekL1-2BgTqappHyr4MoWSjyQA-3D-3DkRM8_XAqE1bivOUeWaaWO3Xvevfi5EKV1gmjmR5c7bmgsul83aCzi5liMfoJ5MS4MQZ4hjsTyP6iTCtHWovMwXjoR90IIB7E2Eq8gSNmZGscyMFY0ycp0lmecYLmqxPVo3Le9uWMlrmrEMAKrrHeuV-2B9Zt0gNOaeSDrnFo8A7f5-2B66VXqGB9nly0NVzu6SbgM1suOQbxp6Jgbu0UqPNS-2FUY8ayvI3WNeTE4L74zPXIlGgGWBXCdU5Y87zN8igDLJlFAlF32tgYYlsWtq-2Fn1sZ5m3J3c1NTCVPpvDRDAIkrertwcC-2FAX-2FwZZvNqWH1OrEFoEuXMH8c0gFwcfKEKj21R8JcduGlBWgFxYx862EEqLxffHqh8E3psOMnQfLuUAzBWnKzmYowg0m0doVDsbdpD2ZP1xhEWuWvdJNw64p-2FScIBiKHHaIx3MThPlqCOybImj6TfTHIj2jNhY5CI3LzanuZkV2NRJhKana4EIcXUDoQ0NXx-2Fm9kdI-2Fdn0nyQcEUwngDxjPUcRB6J9gCmskfB50-2Fcr-2BJf0REBJfhDegDaIm-2B2j4tr4wDQ-2BKfl-2B0zJjJpS8nka7w4p7E0Sjl5BTy5lQNGINRRp08kTI0vSj4FLPiDDmZqnNJxx80ikODlIZtpgN6edFOzXMO1ORRthU6s60MUD91scjtYIokJjXL5ufIzgN6U-3D&amp;source=gmail&amp;ust=1625768999143000&amp;usg=AFQjCNHpCiqf4CWI7xk0gfR5ru-TEDuwsw\" fg_scanned=\"1\"><img title=\"Instagram\" src=\"https://ci5.googleusercontent.com/proxy/o4dyvYfrPIbqtzueUAaRzEASGd-dEnneJyv_NKV3VsUDehBDuH0ajEZ-rCQxrP6bFSdjXo3YlvwzCTi6enNRjWaU5bpw6_6QOXKbDIb_p3qgLpMGiTz-B2ouLrmE2aihXPa2RmU_PIG8Mst4MKY1RfHSAZNrd-Jtqb9RCEiXm7UYNrq1Og=s0-d-e1-ft#http://cdn.mcauto-images-production.sendgrid.net/ebc09cbb48969be0/d6cd026d-24a6-4146-a775-1dbeb7965546/104x112.png\" alt=\"Inst\" width=\"35\" height=\"35\" class=\"CToWUd\"></a></td>\n" +
                "                        <td valign=\"top\" align=\"center\"><a href=\"http://link.support.rappimail.com/ls/click?upn=wjaWOmlOXN5j8oLcClPcNNsY5OF6VE-2BXUckVFxN9RGZ-2BjOB5MR8BPRxRd70XC53Pe37G_XAqE1bivOUeWaaWO3Xvevfi5EKV1gmjmR5c7bmgsul83aCzi5liMfoJ5MS4MQZ4hjsTyP6iTCtHWovMwXjoR90IIB7E2Eq8gSNmZGscyMFY0ycp0lmecYLmqxPVo3Le9uWMlrmrEMAKrrHeuV-2B9Zt0gNOaeSDrnFo8A7f5-2B66VXqGB9nly0NVzu6SbgM1suOQbxp6Jgbu0UqPNS-2FUY8ayvI3WNeTE4L74zPXIlGgGWBXCdU5Y87zN8igDLJlFAlF32tgYYlsWtq-2Fn1sZ5m3J3c1NTCVPpvDRDAIkrertwcC-2FAX-2FwZZvNqWH1OrEFoEuXMH8c0gFwcfKEKj21R8JcduGlBWgFxYx862EEqLxffHqh8E3psOMnQfLuUAzBWnKzmYowg0m0doVDsbdpD2ZP1xhEWuWvdJNw64p-2FScIBiKHHaIx3MThPlqCOybImj6TfTHIj2jNhY5CI3LzanuZkV2NRJhKana4EIcXUDoQ0NXzRA310c-2F7pSOGqcLw-2BMTllj-2FJtzgBI7s7NQOI5MFPA9S-2BJLAcCh5s0aNVbZaFRB1gdPpbFphdmebHhE7Hxz2wSgxggvbjYITKJUlbxYllSzyQ9ANRCqtzYMe5IHOn0DD1dqO-2FrHkri1Bk6QnQQ6ZOrRXQtxLB2IaV5XAh62wXCyMhBwRKg9e1dXehmt5NA-2FpI-3D\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=http://link.support.rappimail.com/ls/click?upn%3DwjaWOmlOXN5j8oLcClPcNNsY5OF6VE-2BXUckVFxN9RGZ-2BjOB5MR8BPRxRd70XC53Pe37G_XAqE1bivOUeWaaWO3Xvevfi5EKV1gmjmR5c7bmgsul83aCzi5liMfoJ5MS4MQZ4hjsTyP6iTCtHWovMwXjoR90IIB7E2Eq8gSNmZGscyMFY0ycp0lmecYLmqxPVo3Le9uWMlrmrEMAKrrHeuV-2B9Zt0gNOaeSDrnFo8A7f5-2B66VXqGB9nly0NVzu6SbgM1suOQbxp6Jgbu0UqPNS-2FUY8ayvI3WNeTE4L74zPXIlGgGWBXCdU5Y87zN8igDLJlFAlF32tgYYlsWtq-2Fn1sZ5m3J3c1NTCVPpvDRDAIkrertwcC-2FAX-2FwZZvNqWH1OrEFoEuXMH8c0gFwcfKEKj21R8JcduGlBWgFxYx862EEqLxffHqh8E3psOMnQfLuUAzBWnKzmYowg0m0doVDsbdpD2ZP1xhEWuWvdJNw64p-2FScIBiKHHaIx3MThPlqCOybImj6TfTHIj2jNhY5CI3LzanuZkV2NRJhKana4EIcXUDoQ0NXzRA310c-2F7pSOGqcLw-2BMTllj-2FJtzgBI7s7NQOI5MFPA9S-2BJLAcCh5s0aNVbZaFRB1gdPpbFphdmebHhE7Hxz2wSgxggvbjYITKJUlbxYllSzyQ9ANRCqtzYMe5IHOn0DD1dqO-2FrHkri1Bk6QnQQ6ZOrRXQtxLB2IaV5XAh62wXCyMhBwRKg9e1dXehmt5NA-2FpI-3D&amp;source=gmail&amp;ust=1625768999143000&amp;usg=AFQjCNGO_qQKCARPMQopVrKU09z-NsrZfw\" fg_scanned=\"1\"><img title=\"Twitter\" src=\"https://ci4.googleusercontent.com/proxy/BQa_s8FEfRygNEc6nN4SUT-QGd8K3OEA72R8hhL0tVlKCBwur81xujAnAUyJ7g6egnGRmmG8w1eX3PuEJQUFsFQRr9jlpoKgzMMErr94NC_bJccH2WJEo7AnwqmT6spKxZHUVe-ht30Aj71sFCmpTXprh5MFl6KDOU13COEyZp593nT_dw=s0-d-e1-ft#http://cdn.mcauto-images-production.sendgrid.net/ebc09cbb48969be0/15ce5f97-5b5f-4d40-8303-bfed332486f4/106x116.png\" alt=\"Tw\" width=\"35\" height=\"35\" class=\"CToWUd\"></a></td>\n" +
                "\n" +
                "                      </tr>\n" +
                "                    </tbody>\n" +
                "                  </table>\n" +
                "\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td height=\"10\"></td>\n" +
                "              </tr>\n" +
                "              <tr style=\"margin-top:50px\">\n" +
                "                <td align=\"center\">\n" +
                "                  <p style=\"color:#a1a1a1\"><a style=\"color:#ff5b4a;font-weight:bold;text-decoration:none\" href=\"http://link.support.rappimail.com/ls/click?upn=wjaWOmlOXN5j8oLcClPcNDyltEBbLh59NCl1NgZYfzNYm-2BCwNliNFyD1eOJEgEQbQeFe_XAqE1bivOUeWaaWO3Xvevfi5EKV1gmjmR5c7bmgsul83aCzi5liMfoJ5MS4MQZ4hjsTyP6iTCtHWovMwXjoR90IIB7E2Eq8gSNmZGscyMFY0ycp0lmecYLmqxPVo3Le9uWMlrmrEMAKrrHeuV-2B9Zt0gNOaeSDrnFo8A7f5-2B66VXqGB9nly0NVzu6SbgM1suOQbxp6Jgbu0UqPNS-2FUY8ayvI3WNeTE4L74zPXIlGgGWBXCdU5Y87zN8igDLJlFAlF32tgYYlsWtq-2Fn1sZ5m3J3c1NTCVPpvDRDAIkrertwcC-2FAX-2FwZZvNqWH1OrEFoEuXMH8c0gFwcfKEKj21R8JcduGlBWgFxYx862EEqLxffHqh8E3psOMnQfLuUAzBWnKzmYowg0m0doVDsbdpD2ZP1xhEWuWvdJNw64p-2FScIBiKHHaIx3MThPlqCOybImj6TfTHIj2jNhY5CI3LzanuZkV2NRJhKana4EIcXUDoQ0NXzpVnhU0IB-2FUNJ1evuWF-2BaMkiQGjrSgif0aYxN9KGEx0Rxlom-2F06Er3irqt215h6ufS2WGmtem8fwIAEqH-2FclUUOKwhjbKgEAe6wJuG7M9n4RykVoqqg352kUen6a2H3VEyvOHZ-2FHu6hIMikmcZK-2B9-2BngxfMNHux5dSEmRaL0WQR3zUABUvhFIJOm06B2LM2Qc-3D\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=http://link.support.rappimail.com/ls/click?upn%3DwjaWOmlOXN5j8oLcClPcNDyltEBbLh59NCl1NgZYfzNYm-2BCwNliNFyD1eOJEgEQbQeFe_XAqE1bivOUeWaaWO3Xvevfi5EKV1gmjmR5c7bmgsul83aCzi5liMfoJ5MS4MQZ4hjsTyP6iTCtHWovMwXjoR90IIB7E2Eq8gSNmZGscyMFY0ycp0lmecYLmqxPVo3Le9uWMlrmrEMAKrrHeuV-2B9Zt0gNOaeSDrnFo8A7f5-2B66VXqGB9nly0NVzu6SbgM1suOQbxp6Jgbu0UqPNS-2FUY8ayvI3WNeTE4L74zPXIlGgGWBXCdU5Y87zN8igDLJlFAlF32tgYYlsWtq-2Fn1sZ5m3J3c1NTCVPpvDRDAIkrertwcC-2FAX-2FwZZvNqWH1OrEFoEuXMH8c0gFwcfKEKj21R8JcduGlBWgFxYx862EEqLxffHqh8E3psOMnQfLuUAzBWnKzmYowg0m0doVDsbdpD2ZP1xhEWuWvdJNw64p-2FScIBiKHHaIx3MThPlqCOybImj6TfTHIj2jNhY5CI3LzanuZkV2NRJhKana4EIcXUDoQ0NXzpVnhU0IB-2FUNJ1evuWF-2BaMkiQGjrSgif0aYxN9KGEx0Rxlom-2F06Er3irqt215h6ufS2WGmtem8fwIAEqH-2FclUUOKwhjbKgEAe6wJuG7M9n4RykVoqqg352kUen6a2H3VEyvOHZ-2FHu6hIMikmcZK-2B9-2BngxfMNHux5dSEmRaL0WQR3zUABUvhFIJOm06B2LM2Qc-3D&amp;source=gmail&amp;ust=1625768999143000&amp;usg=AFQjCNENeS_-aK0ypKAvj-HjbMC4ixRfhg\" fg_scanned=\"1\">Centro de ayuda</a><br>\n" +
                "                    <a style=\"color:#a1a1a1;text-decoration:none\" href=\"http://link.support.rappimail.com/ls/click?upn=wjaWOmlOXN5j8oLcClPcNAJm-2BYbQIQtgBFKl3tawvC8t8PiUgACZLjpQCwYuHqqjsZ05KDsIq0gvFXTeZBaWnUIShXW9yZjQ4YzsP4obT-2F4iW3U7TkhIIrJuNK6OusOIuI6XO-2BsSwiZVl7XUqsxbkA-3D-3DC66T_XAqE1bivOUeWaaWO3Xvevfi5EKV1gmjmR5c7bmgsul83aCzi5liMfoJ5MS4MQZ4hjsTyP6iTCtHWovMwXjoR90IIB7E2Eq8gSNmZGscyMFY0ycp0lmecYLmqxPVo3Le9uWMlrmrEMAKrrHeuV-2B9Zt0gNOaeSDrnFo8A7f5-2B66VXqGB9nly0NVzu6SbgM1suOQbxp6Jgbu0UqPNS-2FUY8ayvI3WNeTE4L74zPXIlGgGWBXCdU5Y87zN8igDLJlFAlF32tgYYlsWtq-2Fn1sZ5m3J3c1NTCVPpvDRDAIkrertwcC-2FAX-2FwZZvNqWH1OrEFoEuXMH8c0gFwcfKEKj21R8JcduGlBWgFxYx862EEqLxffHqh8E3psOMnQfLuUAzBWnKzmYowg0m0doVDsbdpD2ZP1xhEWuWvdJNw64p-2FScIBiKHHaIx3MThPlqCOybImj6TfTHIj2jNhY5CI3LzanuZkV2NRJhKana4EIcXUDoQ0NXzIpnJAbP73OS402w2s2u9g98s3zQQoPtO-2BJAUVuH3-2B8DfCzBApXqFBrG4nwbLXHU6tLLT7poSb2BaTyxPW2jJ-2BjVa7JZb3FToobqTKOPeMLTUYTwYnY1sA9vmoeNrfhMsg2XDr8sf3V4etzLaEckkYOB8c-2BTVceNLIwPb623Epx0B7fkfV3IYTZDx91KDmVsw-3D\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=http://link.support.rappimail.com/ls/click?upn%3DwjaWOmlOXN5j8oLcClPcNAJm-2BYbQIQtgBFKl3tawvC8t8PiUgACZLjpQCwYuHqqjsZ05KDsIq0gvFXTeZBaWnUIShXW9yZjQ4YzsP4obT-2F4iW3U7TkhIIrJuNK6OusOIuI6XO-2BsSwiZVl7XUqsxbkA-3D-3DC66T_XAqE1bivOUeWaaWO3Xvevfi5EKV1gmjmR5c7bmgsul83aCzi5liMfoJ5MS4MQZ4hjsTyP6iTCtHWovMwXjoR90IIB7E2Eq8gSNmZGscyMFY0ycp0lmecYLmqxPVo3Le9uWMlrmrEMAKrrHeuV-2B9Zt0gNOaeSDrnFo8A7f5-2B66VXqGB9nly0NVzu6SbgM1suOQbxp6Jgbu0UqPNS-2FUY8ayvI3WNeTE4L74zPXIlGgGWBXCdU5Y87zN8igDLJlFAlF32tgYYlsWtq-2Fn1sZ5m3J3c1NTCVPpvDRDAIkrertwcC-2FAX-2FwZZvNqWH1OrEFoEuXMH8c0gFwcfKEKj21R8JcduGlBWgFxYx862EEqLxffHqh8E3psOMnQfLuUAzBWnKzmYowg0m0doVDsbdpD2ZP1xhEWuWvdJNw64p-2FScIBiKHHaIx3MThPlqCOybImj6TfTHIj2jNhY5CI3LzanuZkV2NRJhKana4EIcXUDoQ0NXzIpnJAbP73OS402w2s2u9g98s3zQQoPtO-2BJAUVuH3-2B8DfCzBApXqFBrG4nwbLXHU6tLLT7poSb2BaTyxPW2jJ-2BjVa7JZb3FToobqTKOPeMLTUYTwYnY1sA9vmoeNrfhMsg2XDr8sf3V4etzLaEckkYOB8c-2BTVceNLIwPb623Epx0B7fkfV3IYTZDx91KDmVsw-3D&amp;source=gmail&amp;ust=1625768999143000&amp;usg=AFQjCNEcP0bIvbe_4pqJ159-kGqB_LCleg\" fg_scanned=\"1\">Aviso\n" +
                "                      de privacidad</a><br>\n" +
                "                    <a style=\"color:#a1a1a1;text-decoration:none\" href=\"http://link.support.rappimail.com/ls/click?upn=wjaWOmlOXN5j8oLcClPcNAJm-2BYbQIQtgBFKl3tawvC9mL7Je4ctPr1zdin87LwG-2FBwQwxPwdtSsF1ayTTgE5-2B7kaJY0dxooeHNtnDSEuNS4EONNLf7NckAX58KWriDTkczAw_XAqE1bivOUeWaaWO3Xvevfi5EKV1gmjmR5c7bmgsul83aCzi5liMfoJ5MS4MQZ4hjsTyP6iTCtHWovMwXjoR90IIB7E2Eq8gSNmZGscyMFY0ycp0lmecYLmqxPVo3Le9uWMlrmrEMAKrrHeuV-2B9Zt0gNOaeSDrnFo8A7f5-2B66VXqGB9nly0NVzu6SbgM1suOQbxp6Jgbu0UqPNS-2FUY8ayvI3WNeTE4L74zPXIlGgGWBXCdU5Y87zN8igDLJlFAlF32tgYYlsWtq-2Fn1sZ5m3J3c1NTCVPpvDRDAIkrertwcC-2FAX-2FwZZvNqWH1OrEFoEuXMH8c0gFwcfKEKj21R8JcduGlBWgFxYx862EEqLxffHqh8E3psOMnQfLuUAzBWnKzmYowg0m0doVDsbdpD2ZP1xhEWuWvdJNw64p-2FScIBiKHHaIx3MThPlqCOybImj6TfTHIj2jNhY5CI3LzanuZkV2NRJhKana4EIcXUDoQ0NXySl6lTF44hAbcsaO3w-2BJMH06OVUIoVczsoswp8xwnyJFZ4VZPy9turtnTAN5La-2FAnXbVSqMcslDz3jC2UbCJS0Hkbpe3YE-2FKh-2FR6chC-2FvypWqeSd8UiPQ-2FKiVEbrM9FXmwBQk-2FbiPA9c-2BNyAiULzluucUKrCnpUOJrucbixoVMw46SmurewH7GtCqBaIRWIDo-3D\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=http://link.support.rappimail.com/ls/click?upn%3DwjaWOmlOXN5j8oLcClPcNAJm-2BYbQIQtgBFKl3tawvC9mL7Je4ctPr1zdin87LwG-2FBwQwxPwdtSsF1ayTTgE5-2B7kaJY0dxooeHNtnDSEuNS4EONNLf7NckAX58KWriDTkczAw_XAqE1bivOUeWaaWO3Xvevfi5EKV1gmjmR5c7bmgsul83aCzi5liMfoJ5MS4MQZ4hjsTyP6iTCtHWovMwXjoR90IIB7E2Eq8gSNmZGscyMFY0ycp0lmecYLmqxPVo3Le9uWMlrmrEMAKrrHeuV-2B9Zt0gNOaeSDrnFo8A7f5-2B66VXqGB9nly0NVzu6SbgM1suOQbxp6Jgbu0UqPNS-2FUY8ayvI3WNeTE4L74zPXIlGgGWBXCdU5Y87zN8igDLJlFAlF32tgYYlsWtq-2Fn1sZ5m3J3c1NTCVPpvDRDAIkrertwcC-2FAX-2FwZZvNqWH1OrEFoEuXMH8c0gFwcfKEKj21R8JcduGlBWgFxYx862EEqLxffHqh8E3psOMnQfLuUAzBWnKzmYowg0m0doVDsbdpD2ZP1xhEWuWvdJNw64p-2FScIBiKHHaIx3MThPlqCOybImj6TfTHIj2jNhY5CI3LzanuZkV2NRJhKana4EIcXUDoQ0NXySl6lTF44hAbcsaO3w-2BJMH06OVUIoVczsoswp8xwnyJFZ4VZPy9turtnTAN5La-2FAnXbVSqMcslDz3jC2UbCJS0Hkbpe3YE-2FKh-2FR6chC-2FvypWqeSd8UiPQ-2FKiVEbrM9FXmwBQk-2FbiPA9c-2BNyAiULzluucUKrCnpUOJrucbixoVMw46SmurewH7GtCqBaIRWIDo-3D&amp;source=gmail&amp;ust=1625768999143000&amp;usg=AFQjCNGX5FmgH_h0y5hRfe6bcMmlW-fv7w\" fg_scanned=\"1\">TÃ©rminos\n" +
                "                      y condiciones</a><br>\n" +
                "                    <a style=\"color:#a1a1a1;text-decoration:none\"></a><a style=\"color:#a1a1a1;text-decoration:none\">Cancelar suscripciÃ³n</a>\n" +
                "                  </p>\n" +
                "                  <p style=\"color:#a1a1a1\"><br></p>\n" +
                "                  <p style=\"color:#a1a1a1;font-size:10px\">Recibiste\n" +
                "                    este mail porque eres nuestro usuario</p>\n" +
                "                  <p style=\"color:#a1a1a1;font-size:10px\">Copyright\n" +
                "                    Â® 2019 <span class=\"il\">RAPPI</span> S.A.S. Todos los derechos reservados\n" +
                "                  </p>\n" +
                "                  <p style=\"color:#a1a1a1\"><br></p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </tbody></table>\n" +
                "          </td>\n" +
                "\n" +
                "        </tr>\n" +
                "        \n" +
                "\n" +
                "      </tbody></table>\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "</tbody></table>\n";
    }




}
