package com.learn.photo.web;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class SiteController {

    @GetMapping("/lang")
    public String changeLanguage() {
        // The locale change is now handled by the interceptor
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "lang", required = false) String lang,
                        HttpServletRequest request) {
        //if (lang != null) {
            // Locale locale = new Locale(lang);
            // model.addAttribute("currentLocale", locale);
        //}

        // Locale locale = RequestContextUtils.getLocale(request);
        // model.addAttribute("currentLocale", locale);

        // List<String> messages = new ArrayList<>();
        // Add any messages you want to display
        // messages.add("Welcome to our application!");
        // model.addAttribute("messages", messages);
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
}
