package com.lmph.be.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping("/error/500")
    public String error500(Model model){
        return "error_500";
    }
}
