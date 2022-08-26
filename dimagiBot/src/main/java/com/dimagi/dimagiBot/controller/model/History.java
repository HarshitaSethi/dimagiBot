/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dimagi.dimagiBot.controller.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.springframework.stereotype.Component;

/**
 *
 * @author harshita.sethi
 */
@Component
public class History {

    List<Command> commandList;

    public List<Command> getCommandList() {
        return commandList;

    }

//    public void setCommandList(Stack<Command> commandList) {
//        this.commandList = commandList;
//    }
    public void setCommand(Command command) {
        if (this.commandList == null) {
            this.commandList = new LinkedList<>();
        }
        this.commandList.add(0,command);
    }
//    Stack<Command> commandList;
//
//    public Stack<Command> getCommandList() {
//        return commandList;
//
//    }
//
////    public void setCommandList(Stack<Command> commandList) {
////        this.commandList = commandList;
////    }
//    public void setCommand(Command command) {
//        if (this.commandList == null) {
//            this.commandList = new Stack<>();
//        }
//        this.commandList.push(command);
//    }

}
