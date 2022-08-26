/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dimagi.dimagiBot.util;

import com.dimagi.dimagiBot.controller.model.CommandDescription;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author harshita.sethi
 */
@Component
public class Intent {

//    @Value("${command.list}")
    String commandList = "help,hereiam,history,inoffice,ping,play,say,spreche,stop,vol,whereis";

    public static Map<String, CommandDescription> commandDescMap;
    public static Map<String, CommandDescription> commandNameMap;
    public static List<CommandDescription> commandDescList;

    public Intent() {
        System.out.println("in constr commandList" + commandList);
        commandDescList = Stream.of(commandList.split(","))
                .map(command -> {
                    String desc = ReadPropertiesFile.getPropertyValue(command);
                    CommandDescription cd = new CommandDescription();
                    cd.setCommandName(command);

                    String[] split = desc.split(";");
                    cd.setCommandDescription(split[0]);
                    if (split.length > 1) {
                        cd.setCommandSyntax(split[1]);
                    }
                    return cd;
                }).collect(Collectors.toList());

        commandDescMap = commandDescList.stream().collect(Collectors.toMap(cd -> cd.getCommandDescription(), cd -> cd));
        commandNameMap = commandDescList.stream().collect(Collectors.toMap(cd -> cd.getCommandName(), cd -> cd));

    }
}
