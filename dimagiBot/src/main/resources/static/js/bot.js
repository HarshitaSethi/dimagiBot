/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

var chatbox;

window.onload = function () {
    chatbox = document.getElementById("chatbox");
}


function addUserMessage(message) {
    var div = document.createElement("div");
    div.classList.add("userDiv");

    var h6 = document.createElement("h6");
    h6.appendChild(document.createTextNode(message));

    var icon = document.createElement("i");
    icon.classList.add("fa", "fa-user", "text-dark");

    var iconDiv = document.createElement("div");
    iconDiv.appendChild(icon);

    div.appendChild(h6);
    div.appendChild(iconDiv);

    chatbox.appendChild(div);
    addTimeStamp("userInput");


}


function addBotReply(message) {
    var div = document.createElement("div");
    var botDiv = document.createElement("div");
    botDiv.classList.add("chat");

    botDiv.innerHTML = message;
//    botDiv.appendChild(document.createTextNode(message));

    var icon = document.createElement("i");
    icon.classList.add("fas", "fa-universal-access", "fa-lg", "text-white", "font-20");

    var iconDiv = document.createElement("div");
    iconDiv.classList.add("profile");
    iconDiv.appendChild(icon);

    div.appendChild(botDiv);
    div.appendChild(iconDiv);

    chatbox.appendChild(div);
    addTimeStamp("botReply");

}


function addTimeStamp(className) {

    const currentTime = new Date().toLocaleTimeString();
    var p = document.createElement("p");
    p.appendChild(document.createTextNode(currentTime));
    p.classList.add(className);
    chatbox.appendChild(p);

    chatbox.scrollTop = chatbox.scrollHeight;

}

function userInputOnKeydown(ele) {
    if (event.key === 'Enter') {
        analyzeUserIntent(ele.value);
    }
}

function buttonClick() {
    analyzeUserIntent(document.getElementById("userInputField").value);
}

function analyzeUserIntent(message) {
    document.getElementById("userInputField").value = "";
    addUserMessage(message);
    callController(message);
}

async  function callController(message) {

    const rawResponse = await fetch('postMessage', {
        method: 'POST',
        headers: {
            'Accept': 'text/plain',
            'Content-Type': 'text/plain'
        },
        body: message
    });
    const content = await rawResponse.text();

    console.log(content);

    addBotReply(content);
}