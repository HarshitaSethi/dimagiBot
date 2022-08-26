/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dimagi.dimagiBot.service;

import com.dimagi.dimagiBot.controller.model.CommandDescription;
import com.dimagi.dimagiBot.controller.model.History;
import com.dimagi.dimagiBot.util.Intent;
import com.dimagi.dimagiBot.util.ReadPropertiesFile;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author harshita.sethi
 */
@Service
public class BotService {

    @Autowired
    History history;

    @Value("${history.number_of_commands.limit}")
    Long commandLimit;

    public String analyzeInput(String inputMessage) {
        System.out.println(Intent.commandDescMap);
        System.out.println(Intent.commandNameMap);
        System.out.println(Intent.commandDescList);

        if (Intent.commandDescMap.containsKey(inputMessage)) {
            return analyze(Intent.commandDescMap.get(inputMessage), inputMessage);
        } else if (Intent.commandNameMap.containsKey(inputMessage)) {
            return analyze(Intent.commandNameMap.get(inputMessage), inputMessage);
        } else {
            String key = inputMessage.split(" ")[0];
            if (Intent.commandNameMap.containsKey(key)) {
                return analyze(Intent.commandNameMap.get(key), inputMessage);
            }
        }

        return "Can't interpret the command";
    }

    private String analyze(CommandDescription commandDescription, String args) {

        String name = commandDescription.getCommandName();
        String message = ReadPropertiesFile.getPropertyValue(name + ".message");

        args = args.replaceFirst(name, "");

        switch (name) {
            case "help":
                String commands = Intent.commandDescList.stream()
                        .filter(cd -> !cd.getCommandName().equals("help"))
                        .map(cd -> cd.getCommandDescription() + "(" + cd.getCommandName() + ")")
                        .map(s -> "<li>" + s + "</li>")
                        .collect(Collectors.joining());

                return message.replace("<bot_reply>", commands);
            case "hereiam":
                return message.replace("<bot_reply>", "GET_GEO_LOCATION");
            case "history":

                commands = history.getCommandList().stream()
                        .limit(commandLimit)
                        .map(s -> "<li>" + s + "</li>")
                        .collect(Collectors.joining());
                return message.replace("<bot_reply>", commands).replace("<number_of_commands>", "" + commandLimit);
//            case "inoffice":
//                break;
            case "ping":
                return message;
//            case "play":
//                break;
            case "say":
                return message.replace("<bot_reply>", args);
//            case "spreche":
//                break;
//            case "stop":
//                break;
//            case "vol":
//                break;
//            case "whereis":
//                break;

        }

        return "Sorry!! Command not configured yet.";
    }

}
