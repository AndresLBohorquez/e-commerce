/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.util;

/**
 *
 * @author andre
 */
public class ContenidoOrderMail {

    private String correoOrden = "<!doctype html>\n"
            + "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n"
            + "\n"
            + "<head>\n"
            + "    <title>Order</title>\n"
            + "    <!--[if !mso]><!-- -->\n"
            + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
            + "    <!--<![endif]-->\n"
            + "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
            + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
            + "    <style type=\"text/css\">\n"
            + "        span.productOldPrice {\n"
            + "            color: #ff5151;\n"
            + "            text-decoration: line-through;\n"
            + "        }\n"
            + "        \n"
            + "        #outlook a {\n"
            + "            padding: 0;\n"
            + "        }\n"
            + "        \n"
            + "        body {\n"
            + "            margin: 0;\n"
            + "            padding: 0;\n"
            + "            -webkit-text-size-adjust: 100%;\n"
            + "            -ms-text-size-adjust: 100%;\n"
            + "        }\n"
            + "        \n"
            + "        table,\n"
            + "        td {\n"
            + "            border-collapse: collapse;\n"
            + "            mso-table-lspace: 0pt;\n"
            + "            mso-table-rspace: 0pt;\n"
            + "        }\n"
            + "        \n"
            + "        img {\n"
            + "            border: 0;\n"
            + "            height: auto;\n"
            + "            line-height: 100%;\n"
            + "            outline: none;\n"
            + "            text-decoration: none;\n"
            + "            -ms-interpolation-mode: bicubic;\n"
            + "        }\n"
            + "        \n"
            + "        p {\n"
            + "            display: block;\n"
            + "            margin: 13px 0;\n"
            + "        }\n"
            + "    </style>\n"
            + "    <!--[if mso]> \n"
            + "<xml>\n"
            + "<o:OfficeDocumentSettings>\n"
            + "<o:AllowPNG/>\n"
            + "<o:PixelsPerInch>96</o:PixelsPerInch>\n"
            + "</o:OfficeDocumentSettings>\n"
            + "</xml>\n"
            + "<![endif]-->\n"
            + "    <!--[if lte mso 11]> \n"
            + "<style type=\"text/css\"> .outlook-group-fix { width:100% !important; } </style>\n"
            + "<![endif]-->\n"
            + "    <!--[if !mso]><!-->\n"
            + "    <link href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,400,500,700\" rel=\"stylesheet\" type=\"text/css\">\n"
            + "    <style type=\"text/css\">\n"
            + "        @import url(https://fonts.googleapis.com/css?family=Open+Sans:300,400,500,700);\n"
            + "    </style>\n"
            + "    <!--<![endif]-->\n"
            + "    <style type=\"text/css\">\n"
            + "        @media only screen and (min-width:480px) {\n"
            + "            .column-per-100 {\n"
            + "                width: 100% !important;\n"
            + "                max-width: 100%;\n"
            + "            }\n"
            + "            .column-per-25 {\n"
            + "                width: 25% !important;\n"
            + "                max-width: 25%;\n"
            + "            }\n"
            + "            .column-per-75 {\n"
            + "                width: 75% !important;\n"
            + "                max-width: 75%;\n"
            + "            }\n"
            + "            .column-per-48-4 {\n"
            + "                width: 48.4% !important;\n"
            + "                max-width: 48.4%;\n"
            + "            }\n"
            + "            .column-per-50 {\n"
            + "                width: 50% !important;\n"
            + "                max-width: 50%;\n"
            + "            }\n"
            + "        }\n"
            + "    </style>\n"
            + "    <style type=\"text/css\">\n"
            + "        @media only screen and (max-width:480px) {\n"
            + "            table.full-width-mobile {\n"
            + "                width: 100% !important;\n"
            + "            }\n"
            + "            td.full-width-mobile {\n"
            + "                width: auto !important;\n"
            + "            }\n"
            + "        }\n"
            + "        \n"
            + "        noinput.menu-checkbox {\n"
            + "            display: block !important;\n"
            + "            max-height: none !important;\n"
            + "            visibility: visible !important;\n"
            + "        }\n"
            + "        \n"
            + "        @media only screen and (max-width:480px) {\n"
            + "            .menu-checkbox[type=\"checkbox\"]~.inline-links {\n"
            + "                display: none !important;\n"
            + "            }\n"
            + "            .menu-checkbox[type=\"checkbox\"]:checked~.inline-links,\n"
            + "            .menu-checkbox[type=\"checkbox\"]~.menu-trigger {\n"
            + "                display: block !important;\n"
            + "                max-width: none !important;\n"
            + "                max-height: none !important;\n"
            + "                font-size: inherit !important;\n"
            + "            }\n"
            + "            .menu-checkbox[type=\"checkbox\"]~.inline-links>a {\n"
            + "                display: block !important;\n"
            + "            }\n"
            + "            .menu-checkbox[type=\"checkbox\"]:checked~.menu-trigger .menu-icon-close {\n"
            + "                display: block !important;\n"
            + "            }\n"
            + "            .menu-checkbox[type=\"checkbox\"]:checked~.menu-trigger .menu-icon-open {\n"
            + "                display: none !important;\n"
            + "            }\n"
            + "        }\n"
            + "    </style>\n"
            + "    <style type=\"text/css\">\n"
            + "        @media only screen and (min-width:481px) {\n"
            + "            .products-list-table img {\n"
            + "                width: 120px !important;\n"
            + "                display: block;\n"
            + "            }\n"
            + "            .products-list-table .image-column {\n"
            + "                width: 20% !important;\n"
            + "            }\n"
            + "        }\n"
            + "        \n"
            + "        a {\n"
            + "            color: #bdbdbd;\n"
            + "        }\n"
            + "        \n"
            + "        .server-img img {\n"
            + "            width: 100%\n"
            + "        }\n"
            + "        \n"
            + "        .server-box-one a,\n"
            + "        .server-box-two a {\n"
            + "            text-decoration: underline;\n"
            + "            color: #2E9CC3;\n"
            + "        }\n"
            + "        \n"
            + "        .server-img img {\n"
            + "            width: 100%\n"
            + "        }\n"
            + "        \n"
            + "        .server-box-one a,\n"
            + "        .server-box-two a {\n"
            + "            text-decoration: underline;\n"
            + "            color: #2E9CC3;\n"
            + "        }\n"
            + "        \n"
            + "        .server-img img {\n"
            + "            width: 100%\n"
            + "        }\n"
            + "        \n"
            + "        .server-box-one a,\n"
            + "        .server-box-two a {\n"
            + "            text-decoration: underline;\n"
            + "            color: #2E9CC3;\n"
            + "        }\n"
            + "    </style>\n"
            + "</head>\n"
            + "\n"
            + "<body style=\"background-color:#4c5051;\">\n"
            + "    <div style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; background-color: #4c5051;\">\n"
            + "        <!-- Body Wrapper -->\n"
            + "        <!--[if mso | IE]> \n"
            + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body-wrapper-outlook\" style=\"width:600px;\" width=\"600\" >\n"
            + "<tr>\n"
            + "<td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n"
            + "<![endif]-->\n"
            + "        <div class=\"body-wrapper\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; padding-bottom: 20px; box-shadow: 0 4px 10px #424549; background: #424549; background-color: #424549; margin: 0px auto; max-width: 600px; margin-bottom: 10px;\">\n"
            + "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:#424549;background-color:#424549;width:100%;\">\n"
            + "                <tbody>\n"
            + "                    <tr>\n"
            + "                        <td style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; direction: ltr; font-size: 0px; padding: 10px 20px; text-align: center;\" align=\"center\">\n"
            + "                            <!--[if mso | IE]> \n"
            + "<table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
            + "<![endif]-->\n"
            + "                            <!-- Pre-Headers -->\n"
            + "                            <!--[if mso | IE]> \n"
            + "<tr>\n"
            + "<td class=\"pre-header-outlook\" width=\"600px\" >\n"
            + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"pre-header-outlook\" style=\"width:560px;\" width=\"560\" >\n"
            + "<tr>\n"
            + "<td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n"
            + "<![endif]-->\n"
            + "                            <div class=\"pre-header\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; height: 1px; overflow: hidden; margin: 0px auto; max-width: 560px;\">\n"
            + "                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n"
            + "                                    <tbody>\n"
            + "                                        <tr>\n"
            + "                                            <td style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; direction: ltr; font-size: 0px; padding: 0px; text-align: center;\" align=\"center\">\n"
            + "                                                <!--[if mso | IE]> \n"
            + "               <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
            + "                  <tr>\n"
            + "                     <td class=\"\" style=\"vertical-align:top;width:560px;\" >\n"
            + "                        <![endif]-->\n"
            + "                                                <div class=\"column-per-100 outlook-group-fix\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 0px; text-align: left; direction: ltr; display: inline-block; vertical-align: top; width: 100%;\">\n"
            + "                                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n"
            + "                                                        <tr>\n"
            + "                                                            <td align=\"center\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 0px; padding: 0; word-break: break-word;\">\n"
            + "                                                                <div style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 1px; font-weight: 400; line-height: 0; text-align: center; color: #424549;\">Tu Orden!    </div>\n"
            + "                                                            </td>\n"
            + "                                                        </tr>\n"
            + "                                                    </table>\n"
            + "                                                </div>\n"
            + "                                                <!--[if mso | IE]> \n"
            + "                     </td>\n"
            + "                  </tr>\n"
            + "               </table>\n"
            + "               <![endif]-->\n"
            + "                                            </td>\n"
            + "                                        </tr>\n"
            + "                                    </tbody>\n"
            + "                                </table>\n"
            + "                            </div>\n"
            + "                            <!--[if mso | IE]> \n"
            + "</td>\n"
            + "</tr>\n"
            + "</table>\n"
            + "</td>\n"
            + "</tr>\n"
            + "<![endif]-->\n"
            + "                            <!-- header -->\n"
            + "                            <!--[if mso | IE]> \n"
            + "<tr>\n"
            + "<td class=\"header-outlook\" width=\"600px\" >\n"
            + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"header-outlook\" style=\"width:560px;\" width=\"560\" >\n"
            + "<tr>\n"
            + "<td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n"
            + "<![endif]-->\n"
            + "                            <div class=\"header\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; line-height: 22px; padding: 15px 0; margin: 0px auto; max-width: 560px;\">\n"
            + "                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n"
            + "                                    <tbody>\n"
            + "                                        <tr>\n"
            + "                                            <td style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; direction: ltr; font-size: 0px; padding: 0px; text-align: center;\" align=\"center\">\n"
            + "                                                <!--[if mso | IE]> \n"
            + "               <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
            + "                  <tr>\n"
            + "                     <![endif]-->\n"
            + "                                                <!-- LOGO -->\n"
            + "                                                <!--[if mso | IE]> \n"
            + "                     <td class=\"\" style=\"vertical-align:middle;width:140px;\" >\n"
            + "                        <![endif]-->\n"
            + "                                                <div class=\"column-per-25 outlook-group-fix\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 0px; text-align: left; direction: ltr; display: inline-block; vertical-align: middle; width: 100%;\">\n"
            + "                                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:middle;\" width=\"100%\">\n"
            + "                                                        <tr>\n"
            + "                                                            <td align=\"center\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 0px; padding: 0; word-break: break-word;\">\n"
            + "                                                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:collapse;border-spacing:0px;\">\n"
            + "                                                                    <tbody>\n"
            + "                                                                        <tr>\n"
            + "                                                                            <td style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif;width: 160px;\" width=\"160\">\n"
            + "                                                                                <a href=\"http://laesquinallanera.sytes.net\" target=\"_blank\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; padding: 0 10px;\"> <img  height=\"auto\" src=\"https://1.bp.blogspot.com/-lTnkxnHRn58/YHEfG-4sfqI/AAAAAAAARFw/QxIZBD_Akss4pY11Lo9bZtq1uN3vrAdrgCLcBGAsYHQ/s0/logo-01011-white.png\" style=\"border:0;display:block;outline:none;text-decoration:none;height:auto;width:100%;font-size:13px;\"\n"
            + "                                                                                        width=\"160\"> </a>\n"
            + "                                                                            </td>\n"
            + "                                                                        </tr>\n"
            + "                                                                    </tbody>\n"
            + "                                                                </table>\n"
            + "                                                            </td>\n"
            + "                                                        </tr>\n"
            + "                                                    </table>\n"
            + "                                                </div>\n"
            + "                                                <!--[if mso | IE]> \n"
            + "                     </td>\n"
            + "                     <![endif]-->\n"
            + "                                                <!-- Navigation Bar -->\n"
            + "                                                <!--[if mso | IE]> \n"
            + "                     <td class=\"navigation-bar-outlook\" style=\"vertical-align:middle;width:420px;\" >\n"
            + "                        <![endif]-->\n"
            + "                                                <div class=\"column-per-75 outlook-group-fix navigation-bar\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 0px; text-align: left; direction: ltr; display: inline-block; vertical-align: middle; width: 100%;\">\n"
            + "                                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:middle;\" width=\"100%\">\n"
            + "                                                        <tr>\n"
            + "                                                            <td align=\"right\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; text-align: right; font-size: 0px; word-break: break-word;\">\n"
            + "                                                                <div class=\"inline-links\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif;\">\n"
            + "                                                                    <!--[if mso | IE]> \n"
            + "                                       <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n"
            + "                                          <tr>\n"
            + "                                             <td style=\"padding:15px 10px;\" class=\"\" >\n"
            + "                                                <![endif]-->\n"
            + "                                                                    <!--[if mso | IE]> \n"
            + "                                             </td>\n"
            + "                                          </tr>\n"
            + "                                       </table>\n"
            + "                                       <![endif]-->\n"
            + "                                                                </div>\n"
            + "                                                            </td>\n"
            + "                                                        </tr>\n"
            + "                                                    </table>\n"
            + "                                                </div>\n"
            + "                                                <!--[if mso | IE]> \n"
            + "                     </td>\n"
            + "                  </tr>\n"
            + "               </table>\n"
            + "               <![endif]-->\n"
            + "                                            </td>\n"
            + "                                        </tr>\n"
            + "                                    </tbody>\n"
            + "                                </table>\n"
            + "                            </div>\n"
            + "                            <!--[if mso | IE]> \n"
            + "</td>\n"
            + "</tr>\n"
            + "</table>\n"
            + "</td>\n"
            + "</tr>\n"
            + "<![endif]-->\n"
            + "                            <!-- notice -->\n"
            + "                            <!--[if mso | IE]> \n"
            + "<tr>\n"
            + "<td class=\"notice-wrap-outlook margin-bottom-outlook\" width=\"600px\" >\n"
            + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"notice-wrap-outlook margin-bottom-outlook\" style=\"width:560px;\" width=\"560\" >\n"
            + "<tr>\n"
            + "<td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n"
            + "<![endif]-->\n"
            + "                            <div class=\"notice-wrap margin-bottom\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; margin: 0px auto; max-width: 560px; margin-bottom: 15px;\">\n"
            + "                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n"
            + "                                    <tbody>\n"
            + "                                        <tr>\n"
            + "                                            <td style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; direction: ltr; font-size: 0px; padding: 0px; text-align: center;\" align=\"center\">\n"
            + "                                                <!--[if mso | IE]> \n"
            + "               <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
            + "                  <tr>\n"
            + "                     <td class=\"\" style=\"vertical-align:top;width:560px;\" >\n"
            + "                        <![endif]-->\n"
            + "                                                <div class=\"column-per-100 outlook-group-fix\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 0px; text-align: left; direction: ltr; display: inline-block; vertical-align: top; width: 100%;\">\n"
            + "                                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">\n"
            + "                                                        <tbody>\n"
            + "                                                            <tr>\n"
            + "                                                                <td style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; background-color: #4c5053; box-shadow: 0 3px 5px 0 rgba(0,0,0,.1); border-radius: 10px; vertical-align: top; padding: 30px 25px;\" bgcolor=\"#4c5053\" valign=\"top\">\n"
            + "                                                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style width=\"100%\">\n"
            + "                                                                        <tr>\n"
            + "                                                                            <td align=\"left\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 0px; padding: 0; word-break: break-word;\">\n"
            + "                                                                                <div style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 26px; font-weight: bold; line-height: 30px; text-align: left; color: #e5e5e5;\">Tu Orden</div>\n"
            + "                                                                            </td>\n"
            + "                                                                        </tr>\n"
            + "                                                                        <tr>\n"
            + "                                                                            <td align=\"left\" class=\"link-wrap\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; color: #e5e5e6; font-size: 0px; padding: 0; padding-bottom: 20px; word-break: break-word;\">\n"
            + "                                                                                <div style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 25px; text-align: left; color: #e5e5e6;\"><br> Hola ";

//ACA DEBE IR NOMBRE DEL CLIENTE
    private String correoOrden1 = " !<br><br> El código de tu orden <a target='_blank' style='font-weight: bold;color: #2E9CC3;' href=\"\">";

//ACA DEBE IR CODIGO DE LA ORDEN
    private String correoOrden2 = "</a> ha sido registrado correctamente!\n"
            + "                                                                                        \n"
            + "                                                                            </td>\n"
            + "                                                                        </tr>\n"
            + "                                                                        <tr>\n"
            + "                                                                            <td align=\"left\" vertical-align=\"middle\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 0px; padding: 0; word-break: break-word;\">\n"
            + "                                                                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:separate;line-height:100%;\">\n"
            + "                                                                                    <tr>\n"
            + "                                                                                        <td align=\"center\" bgcolor=\"#dd2c00\" role=\"presentation\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; border: none; border-radius: 3px; cursor: auto; mso-padding-alt: 10px 25px; background: #dd2c00;\" valign=\"middle\"> <a href=\"\" style=\"display: inline-block; background: #dd2c00; color: #eee; font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 18px; font-weight: bold; line-height: 120%; margin: 0; text-decoration: none; text-transform: none; padding: 10px 25px; mso-padding-alt: 0px; border-radius: 3px;\"\n"
            + "                                                                                                target=\"_blank\"> Comprobar Estado </a> </td>\n"
            + "                                                                                    </tr>\n"
            + "                                                                                </table>\n"
            + "                                                                            </td>\n"
            + "                                                                        </tr>\n"
            + "                                                                        <tr>\n"
            + "                                                                            <td align=\"left\" class=\"link-wrap\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; color: #e5e5e6; font-size: 0px; padding: 0; padding-bottom: 20px; word-break: break-word;\">\n"
            + "                                                                                <div style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 25px; text-align: left; color: #e5e5e6;\"><br> Si tienes alguna duda, por favor, <a target='_blank' style='font-weight: bold;color: #2E9CC3;' href=\"http://laesquinallanera.sytes.net\">Contáctanos</a>! <br></div>\n"
            + "                                                                            </td>\n"
            + "                                                                        </tr>\n"
            + "                                                                    </table>\n"
            + "                                                                </td>\n"
            + "                                                            </tr>\n"
            + "                                                        </tbody>\n"
            + "                                                    </table>\n"
            + "                                                </div>\n"
            + "                                                <!--[if mso | IE]> ";
    
    
    
    
    private String correoPassword = "<!doctype html>\n" +
"<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
"<head>\n" +
"<title>Recuperar Contraseña</title>\n" +
"<!--[if !mso]><!-- --> \n" +
"<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
"<!--<![endif]--> \n" +
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
"<style type=\"text/css\"> span.productOldPrice { color: #A0131C; text-decoration: line-through;} #outlook a { padding: 0; } body { margin: 0; padding: 0; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; } table, td { border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; } img { border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; } p { display: block; margin: 13px 0; } </style>\n" +
"<!--[if mso]> \n" +
"<xml>\n" +
"<o:OfficeDocumentSettings>\n" +
"<o:AllowPNG/>\n" +
"<o:PixelsPerInch>96</o:PixelsPerInch>\n" +
"</o:OfficeDocumentSettings>\n" +
"</xml>\n" +
"<![endif]--> <!--[if lte mso 11]> \n" +
"<style type=\"text/css\"> .outlook-group-fix { width:100% !important; } </style>\n" +
"<![endif]--> <!--[if !mso]><!--> \n" +
"<link href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,400,500,700\" rel=\"stylesheet\" type=\"text/css\">\n" +
"<style type=\"text/css\"> @import url(https://fonts.googleapis.com/css?family=Open+Sans:300,400,500,700); </style>\n" +
"<!--<![endif]--> \n" +
"<style type=\"text/css\"> @media only screen and (min-width:480px) { .column-per-100 { width: 100% !important; max-width: 100%; } .column-per-25 { width: 25% !important; max-width: 25%; } .column-per-75 { width: 75% !important; max-width: 75%; } .column-per-48-4 { width: 48.4% !important; max-width: 48.4%; } .column-per-50 { width: 50% !important; max-width: 50%; } } </style>\n" +
"<style type=\"text/css\"> @media only screen and (max-width:480px) { table.full-width-mobile { width: 100% !important; } td.full-width-mobile { width: auto !important; } } noinput.menu-checkbox { display: block !important; max-height: none !important; visibility: visible !important; } @media only screen and (max-width:480px) { .menu-checkbox[type=\"checkbox\"]~.inline-links { display: none !important; } .menu-checkbox[type=\"checkbox\"]:checked~.inline-links, .menu-checkbox[type=\"checkbox\"]~.menu-trigger { display: block !important; max-width: none !important; max-height: none !important; font-size: inherit !important; } .menu-checkbox[type=\"checkbox\"]~.inline-links>a { display: block !important; } .menu-checkbox[type=\"checkbox\"]:checked~.menu-trigger .menu-icon-close { display: block !important; } .menu-checkbox[type=\"checkbox\"]:checked~.menu-trigger .menu-icon-open { display: none !important; } } </style>\n" +
"<style type=\"text/css\"> @media only screen and (min-width:481px) { .products-list-table img { width: 120px !important; display: block; } .products-list-table .image-column { width: 20% !important; } } a { color: #000; } .server-img img { width: 100% } .server-box-one a, .server-box-two a { text-decoration: underline; color: #2E9CC3; } .server-img img { width: 100% } .server-box-one a, .server-box-two a { text-decoration: underline; color: #2E9CC3; } .server-img img { width: 100% } .server-box-one a, .server-box-two a { text-decoration: underline; color: #2E9CC3; } </style>\n" +
"</head>\n" +
"<body style=\"background-color:#4c5051;\">\n" +
"<div style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; background-color: #4c5051;\">\n" +
"<!-- Body Wrapper --> <!--[if mso | IE]> \n" +
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body-wrapper-outlook\" style=\"width:600px;\" width=\"600\" >\n" +
"<tr>\n" +
"<td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
"<![endif]--> \n" +
"<div class=\"body-wrapper\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; padding-bottom: 20px; box-shadow: 0 4px 10px #424549; background: #424549; background-color: #424549; margin: 0px auto; max-width: 600px; margin-bottom: 10px;\">\n" +
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:#424549;background-color:#424549;width:100%;\">\n" +
"<tbody>\n" +
"<tr>\n" +
"<td style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; direction: ltr; font-size: 0px; padding: 10px 20px; text-align: center;\" align=\"center\">\n" +
"<!--[if mso | IE]> \n" +
"<table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
"<![endif]--> <!-- Pre-Headers --> <!--[if mso | IE]> \n" +
"<tr>\n" +
"<td class=\"pre-header-outlook\" width=\"600px\" >\n" +
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"pre-header-outlook\" style=\"width:560px;\" width=\"560\" >\n" +
"<tr>\n" +
"<td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
"<![endif]--> \n" +
"<div class=\"pre-header\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; height: 1px; overflow: hidden; margin: 0px auto; max-width: 560px;\">\n" +
"   <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
"      <tbody>\n" +
"         <tr>\n" +
"            <td style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; direction: ltr; font-size: 0px; padding: 0px; text-align: center;\" align=\"center\">\n" +
"               <!--[if mso | IE]> \n" +
"               <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
"                  <tr>\n" +
"                     <td class=\"\" style=\"vertical-align:top;width:560px;\" >\n" +
"                        <![endif]--> \n" +
"                        <div class=\"column-per-100 outlook-group-fix\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 0px; text-align: left; direction: ltr; display: inline-block; vertical-align: top; width: 100%;\">\n" +
"                           <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
"                              <tr>\n" +
"                                 <td align=\"center\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 0px; padding: 0; word-break: break-word;\">\n" +
"                                    <div style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 1px; font-weight: 400; line-height: 0; text-align: center; color: #424549;\">Tu contraseña ha sido cambiada correctamente</div>\n" +
"                                 </td>\n" +
"                              </tr>\n" +
"                           </table>\n" +
"                        </div>\n" +
"                        <!--[if mso | IE]> \n" +
"                     </td>\n" +
"                  </tr>\n" +
"               </table>\n" +
"               <![endif]--> \n" +
"            </td>\n" +
"         </tr>\n" +
"      </tbody>\n" +
"   </table>\n" +
"</div>\n" +
"<!--[if mso | IE]> \n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"<![endif]--> <!-- header --> <!--[if mso | IE]> \n" +
"<tr>\n" +
"<td class=\"header-outlook\" width=\"600px\" >\n" +
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"header-outlook\" style=\"width:560px;\" width=\"560\" >\n" +
"<tr>\n" +
"<td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
"<![endif]--> \n" +
"<div class=\"header\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; line-height: 22px; padding: 15px 0; margin: 0px auto; max-width: 560px;\">\n" +
"   <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
"      <tbody>\n" +
"         <tr>\n" +
"            <td style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; direction: ltr; font-size: 0px; padding: 0px; text-align: center;\" align=\"center\">\n" +
"               <!--[if mso | IE]> \n" +
"               <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
"                  <tr>\n" +
"                     <![endif]--> <!-- LOGO --> <!--[if mso | IE]> \n" +
"                     <td class=\"\" style=\"vertical-align:middle;width:140px;\" >\n" +
"                        <![endif]--> \n" +
"                        <div class=\"column-per-25 outlook-group-fix\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 0px; text-align: left; direction: ltr; display: inline-block; vertical-align: middle; width: 100%;\">\n" +
"                           <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:middle;\" width=\"100%\">\n" +
"                              <tr>\n" +
"                                 <td align=\"center\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 0px; padding: 0; word-break: break-word;\">\n" +
"                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:collapse;border-spacing:0px;\">\n" +
"                                       <tbody>\n" +
"                                          <tr>\n" +
"                                             <td style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif;width: 160px;\" width=\"160\"> <a href=\"http://laesquinallanera.sytes.net\" target=\"_blank\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; padding: 0 10px;\"> <img height=\"auto\" src=\"https://1.bp.blogspot.com/-lTnkxnHRn58/YHEfG-4sfqI/AAAAAAAARFw/QxIZBD_Akss4pY11Lo9bZtq1uN3vrAdrgCLcBGAsYHQ/s0/logo-01011-white.png\" style=\"border:0;display:block;outline:none;text-decoration:none;height:auto;width:100%;font-size:13px;\" width=\"160\"> </a> </td>\n" +
"                                          </tr>\n" +
"                                       </tbody>\n" +
"                                    </table>\n" +
"                                 </td>\n" +
"                              </tr>\n" +
"                           </table>\n" +
"                        </div>\n" +
"                        <!--[if mso | IE]> \n" +
"                     </td>\n" +
"                     <![endif]--> <!-- Navigation Bar --> <!--[if mso | IE]> \n" +
"                     <td class=\"navigation-bar-outlook\" style=\"vertical-align:middle;width:420px;\" >\n" +
"                        <![endif]--> \n" +
"                        <div class=\"column-per-75 outlook-group-fix navigation-bar\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 0px; text-align: left; direction: ltr; display: inline-block; vertical-align: middle; width: 100%;\">\n" +
"                           <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:middle;\" width=\"100%\">\n" +
"                              <tr>\n" +
"                                 <td align=\"right\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; text-align: right; font-size: 0px; word-break: break-word;\">\n" +
"                                 </td>\n" +
"                              </tr>\n" +
"                           </table>\n" +
"                        </div>\n" +
"                        <!--[if mso | IE]> \n" +
"                     </td>\n" +
"                  </tr>\n" +
"               </table>\n" +
"               <![endif]--> \n" +
"            </td>\n" +
"         </tr>\n" +
"      </tbody>\n" +
"   </table>\n" +
"</div>\n" +
"<!--[if mso | IE]> \n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"<![endif]--> <!-- notice --> <!--[if mso | IE]> \n" +
"<tr>\n" +
"<td class=\"notice-wrap-outlook margin-bottom-outlook\" width=\"600px\" >\n" +
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"notice-wrap-outlook margin-bottom-outlook\" style=\"width:560px;\" width=\"560\" >\n" +
"<tr>\n" +
"<td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
"<![endif]--> \n" +
"<div class=\"notice-wrap margin-bottom\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; margin: 0px auto; max-width: 560px; margin-bottom: 15px;\">\n" +
"   <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
"      <tbody>\n" +
"         <tr>\n" +
"            <td style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; direction: ltr; font-size: 0px; padding: 0px; text-align: center;\" align=\"center\">\n" +
"               <!--[if mso | IE]> \n" +
"               <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
"                  <tr>\n" +
"                     <td class=\"\" style=\"vertical-align:top;width:560px;\" >\n" +
"                        <![endif]--> \n" +
"                        <div class=\"column-per-100 outlook-group-fix\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 0px; text-align: left; direction: ltr; display: inline-block; vertical-align: top; width: 100%;\">\n" +
"                           <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">\n" +
"                              <tbody>\n" +
"                                 <tr>\n" +
"                                    <td style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; background-color: #4c5053; box-shadow: 0 3px 5px 0 rgba(0,0,0,.1); border-radius: 10px; vertical-align: top; padding: 30px 25px;\" bgcolor=\"#4c5053\" valign=\"top\">\n" +
"                                       <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style width=\"100%\">\n" +
"                                          <tr>\n" +
"                                             <td align=\"left\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 0px; padding: 0; word-break: break-word;\">\n" +
"                                                <div style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 26px; font-weight: bold; line-height: 30px; text-align: left; color: #e5e5e5;\">Tu contraseña ha sido cambiada correctamente!</div>\n" +
"                                             </td>\n" +
"                                          </tr>\n" +
"                                          <tr>\n" +
"                                             <td align=\"left\" class=\"link-wrap\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; color: #e5e5e6; font-size: 0px; padding: 0; padding-bottom: 20px; word-break: break-word;\">\n" +
"                                                <div style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 16px; font-weight: 300; line-height: 25px; text-align: left; color: #e5e5e6;\">Nueva Información: <br></div>\n" +
"                                             </td>\n" +
"                                          </tr>\n" +
"                                          <tr>\n" +
"                                             <td align=\"left\" class=\"link-wrap\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; color: #e5e5e6; font-size: 0px; padding: 0; padding-bottom: 20px; word-break: break-word;\">\n" +
"                                                <div style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 16px; font-weight: 300; line-height: 25px; text-align: left; color: #e5e5e6;\">Email: <strong>";
    //ACA DEBE IR EL CORREO DEL CLIENTE
    
    private String correoPassword1 = "</strong> <br></div>\n" +
"                                             </td>\n" +
"                                          </tr>\n" +
"                                          <tr>\n" +
"                                             <td align=\"left\" class=\"link-wrap\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; color: #e5e5e6; font-size: 0px; padding: 0; padding-bottom: 20px; word-break: break-word;\">\n" +
"                                                <div style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 16px; font-weight: 300; line-height: 25px; text-align: left; color: #e5e5e6;\">Contraseña: <strong>";
    
    //ACA DEBE IR LA NUEVA CONTRASEÑA
    
    private String correoPassword2 = "</strong> <br></div>\n" +
"                                                <div style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 14px; font-weight: 300; line-height: 25px; text-align: left; color: #e5e5e6;\">Recuerda que puedes cambiarla desde tu perfil cuando quieras! <br></div>\n" +
"                                             </td>\n" +
"                                          </tr>\n" +
"                                          <tr>\n" +
"                                             <td align=\"left\" vertical-align=\"middle\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 0px; padding: 0; word-break: break-word;\">\n" +
"                                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:separate;line-height:100%;\">\n" +
"                                                   <tr>\n" +
"                                                      <td align=\"center\" bgcolor=\"#dd2c00\" role=\"presentation\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; border: none; border-radius: 3px; cursor: auto; mso-padding-alt: 10px 25px; background: #dd2c00;\" valign=\"middle\"> <a href=\"http://laesquinallanera.sytes.net\" style=\"display: inline-block; background: #dd2c00; color: #eee; font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 18px; font-weight: bold; line-height: 120%; margin: 0; text-decoration: none; text-transform: none; padding: 10px 25px; mso-padding-alt: 0px; border-radius: 3px;\" target=\"_blank\"> Login</a> </td>\n" +
"                                                   </tr>\n" +
"                                                </table>\n" +
"                                             </td>\n" +
"                                          </tr>\n" +
"                                          <tr>\n" +
"                                             <td align=\"left\" class=\"link-wrap\" style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; color: #e5e5e6; font-size: 0px; padding: 0; padding-bottom: 20px; word-break: break-word;\">\n" +
"                                                <div style=\"font-family: Open Sans, Helvetica, Tahoma, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 25px; text-align: left; color: #e5e5e6;\"><br> Si tienes alguna duda, por favor, <a target='_blank' style='font-weight: bold;color: #2E9CC3;' href=\"http://laesquinallanera.sytes.net\">Contáctanos</a>! <br></div>\n" +
"                                             </td>\n" +
"                                          </tr>\n" +
"                                       </table>\n" +
"                                    </td>\n" +
"                                 </tr>\n" +
"                              </tbody>\n" +
"                           </table>\n" +
"                        </div>\n" +
"                        <!--[if mso | IE]> \n" +
"                     </td>\n" +
"                  </tr>\n" +
"               </table>\n" +
"               <![endif]--> \n" +
"            </td>\n" +
"         </tr>\n" +
"      </tbody>\n" +
"   </table>\n" +
"</div>\n" +
"<!--[if mso | IE]> \n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"<![endif]--> \n" +
"\n" +
"\n" +
"<!--[if mso | IE]> \n" +
"</table>\n" +
"<![endif]--> \n" +
"</td>\n" +
"</tr>\n" +
"</tbody>\n" +
"</table>\n" +
"</div>\n" +
"<!--[if mso | IE]> \n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"\n" +
"\n" +
"\n" +
"<![endif]--> <!-- footer start --> <!-- Footer Wrapper -->\n" +
"\n" +
"<div class=\"footer-wrapper\" style=\"margin: 0px auto; max-width: 600px;\">\n" +
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background-color: #4c5051; width: 100%;\" width=\"100%\" bgcolor=\"#4c051\">\n" +
"<tbody>\n" +
"<tr>\n" +
"<td style=\"direction:ltr;font-size:0px;padding:20px 0;text-align:center;\">\n" +
"            <div class=\"footer-information\" style=\"margin:0px auto;max-width:600px;\">\n" +
"               <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background-color: #4c5051; width: 100%;\" width=\"100%\" bgcolor=\"#4c051\">\n" +
"                  <tbody>\n" +
"                     <tr>\n" +
"                        <td style=\"direction:ltr;font-size:0px;padding:0px;text-align:center;\">\n" +
"                                                      <!--[if mso | IE]>\n" +
"                           <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
"                              <tr>\n" +
"                                 <td class=\"\" style=\"vertical-align:top;width:600px;\">\n" +
"                                    <![endif]-->\n" +
"                                    <div class=\"column-per-100 outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
"                                       <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background-color: #4c5051; vertical-align: top;\" width=\"100%\" valign=\"top\" bgcolor=\"#4c051\">\n" +
"                                          <tbody>\n" +
"                                             <tr>\n" +
"                                                <td align=\"center\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
"                                                   <div style=\"font-family:OpenSans, Helvetica, Tahoma, Arial, sans-serif;font-size:12px;font-weight:400;line-height:20px;text-align:center;color:#bdbdbd;\">\n" +
"                                                       &copy; 2021 La Esquina Llanera\n" +
"                                                     \n" +
"                                                      \n" +
"                                                   </div>\n" +
"                                                </td>\n" +
"                                             </tr>\n" +
"                                          </tbody>\n" +
"                                       </table>\n" +
"                                    </div>\n" +
"                                    <!--[if mso | IE]>\n" +
"                                 </td>\n" +
"                              </tr>\n" +
"                           </table>\n" +
"                           <![endif]-->\n" +
"                        </td>\n" +
"                     </tr>\n" +
"                  </tbody>\n" +
"               </table>\n" +
"            </div>\n" +
"            <!--[if mso | IE]>\n" +
"         </td>\n" +
"      </tr>\n" +
"   </table>\n" +
"</td>\n" +
"</tr>\n" +
"<![endif]-->\n" +
"<!-- footer logo -->\n" +
"<!--[if mso | IE]>\n" +
"</table>\n" +
"<![endif]-->\n" +
"</td>\n" +
"</tr>\n" +
"</tbody>\n" +
"</table>\n" +
"</div>\n" +
"<!-- footer end --> \n" +
"</div>\n" +
"</body>\n" +
"</html>";
    
    

    public String getCorreoOrden() {
        return correoOrden;
    }

    public void setCorreoOrden(String correoOrden) {
        this.correoOrden = correoOrden;
    }

    public String getCorreoOrden1() {
        return correoOrden1;
    }

    public void setCorreoOrden1(String correoOrden1) {
        this.correoOrden1 = correoOrden1;
    }

    public String getCorreoOrden2() {
        return correoOrden2;
    }

    public void setCorreoOrden2(String correoOrden2) {
        this.correoOrden2 = correoOrden2;
    }

    public String getCorreoPassword() {
        return correoPassword;
    }

    public void setCorreoPassword(String correoPassword) {
        this.correoPassword = correoPassword;
    }

    public String getCorreoPassword1() {
        return correoPassword1;
    }

    public void setCorreoPassword1(String correoPassword1) {
        this.correoPassword1 = correoPassword1;
    }

    public String getCorreoPassword2() {
        return correoPassword2;
    }

    public void setCorreoPassword2(String correoPassword2) {
        this.correoPassword2 = correoPassword2;
    }

}
