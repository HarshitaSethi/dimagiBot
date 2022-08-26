/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dimagi.dimagiBot.controller;

import com.dimagi.dimagiBot.controller.model.Command;
import com.dimagi.dimagiBot.controller.model.History;
import com.dimagi.dimagiBot.service.BotService;
import com.dimagi.dimagiBot.util.Intent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author harshita.sethi
 */
@Controller
public class BotController {
    
    @Value("${chatbot.name}")
    String botName;
    
    @Autowired
    BotService botService;
    
    @Autowired
    History history;
    
    @Autowired
    Intent intent;
    
    
    
    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView();
        
        mv.addObject("message", botName);
        mv.setViewName("Bot");
        
        return mv;
    }
    
    @PostMapping("/postMessage")
    @ResponseBody
    public String analyzeUserMessage(@RequestBody String inputMessage) {
        String s = "Received user input as " + inputMessage;
        System.out.println(s);
        
        String analyzeInput = botService.analyzeInput(inputMessage);
        
        history.setCommand(new Command(inputMessage));
        
        return analyzeInput;
    }
    
}
