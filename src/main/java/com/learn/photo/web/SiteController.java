package com.learn.photo.web;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
        return "pages/index";
    }

    @GetMapping("/about")
    public String about() {
        return "pages/about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "pages/contact";
    }

    @GetMapping("/login")
    public String login() {
        return "pages/login";
    }
}
