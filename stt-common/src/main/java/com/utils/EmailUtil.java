package com.utils;

import java.util.regex.Pattern;

public class EmailUtil {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static String getActivateEmailContent(String email,String key) {
        String uri = "https://www.yasyl.com/activate?email=" + email + "&key=" + key + "";
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <meta charset=\"UTF-8\" />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "    <link\n" +
                "      rel=\"shortcut icon\"\n" +
                "      href=\"http://yasyl.com/favicon.ico\"\n" +
                "      type=\"image/x-icon\"\n" +
                "    />\n" +
                "    <title>YASYL</title>\n" +
                "    <style>\n" +
                "      * {\n" +
                "        margin: 0;\n" +
                "        padding: 0;\n" +
                "        box-sizing: border-box;\n" +
                "      }\n" +
                "      .header-logo {\n" +
                "        color: #52c41a;\n" +
                "        margin-bottom: 5svh;\n" +
                "      }\n" +
                "      .text-center {\n" +
                "        text-align: center;\n" +
                "      }\n" +
                "      .my-3 {\n" +
                "        margin: 1.5rem 0;\n" +
                "      }\n" +
                "      .container {\n" +
                "        position: relative;\n" +
                "        height: 96svh;\n" +
                "        margin: 2svh;\n" +
                "        padding-top: 5svh;\n" +
                "        background-color: #fff;\n" +
                "        border-radius: 20px;\n" +
                "        overflow: hidden;\n" +
                "        box-shadow: rgba(0, 0, 0, 0.1) 0px 4px 12px;\n" +
                "      }\n" +
                "      .bottom {\n" +
                "        position: absolute;\n" +
                "        bottom: 3svh;\n" +
                "        left: 50%;\n" +
                "        transform: translateX(-50%);\n" +
                "        color: #52c41a;\n" +
                "        font-size: 1.5rem;\n" +
                "      }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body style=\"background-color: #fff\">\n" +
                "    <div class=\"container\">\n" +
                "      <h1 class=\"header-logo text-center\">\n" +
                "        <img src=\"http://yasyl.com/images/logo.png\" alt=\"Logo\" />\n" +
                "      </h1>\n" +
                "      <h2 class=\"text-center\">Верификация</h2>\n" +
                "      <div class=\"text-center\">\n" +
                "        Нажмите на ссылку ниже чтобы завершить верификацию\n" +
                "      </div>\n" +
                "      <div class=\"text-center my-3\">\n" +
                "        <a href=\"" + uri + " \">"+uri+"</a >\n" +
                "      </div>\n" +
                "      <div class=\"bottom\">YASYL.COM</div>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>\n";
    }
}