/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dimagi.dimagiBot.controller.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author harshita.sethi
 */
public class Command {

    private SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");

    private String userInput;
    private String timeStamp;

    public Command(String userInput) {
        this.userInput = userInput;
        Date date = new Date();
        this.timeStamp = sdf.format(date);
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return userInput + " - " + timeStamp;
    }

}
