package br.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/studentRegistration")
    public String telaCadastro() {
        return "studentRegistration";
    }

    @GetMapping("/login")
    public String telaLogin() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}
