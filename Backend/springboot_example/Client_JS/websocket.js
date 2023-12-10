
//var ws;
//
//function connect() {
//    var username = document.getElementById("username").value;
//    var wsserver = document.getElementById("wsserver").value;
//    var url = wsserver + username;
//    //var url = "ws://echo.websocket.org";
//
//    ws = new WebSocket(url);
//
//    ws.onmessage = function(event) { // Called when client receives a message from the server
//        console.log(event.data);
//
//        // display on browser
//        var log = document.getElementById("log");
//        log.innerHTML += "message seen " + "\n";
//        log.innerHTML += "message from server: " + event.data + "\n";
//    };
//
//    ws.onopen = function(event) { // called when connection is opened
//        var log = document.getElementById("log");
//        log.innerHTML += "Connected to " + event.currentTarget.url + "\n";
//    };
//}
//
//function send() {  // this is how to send messages
//    var content = document.getElementById("msg").value;
//    ws.send(content);
//}



var ws;

function connect() {
    var username = document.getElementById("username").value;
    var wsserver = document.getElementById("wsserver").value;
    var url = wsserver + username;
    //var url = "ws://echo.websocket.org";

    ws = new WebSocket(url);

    ws.onmessage = function(event) { // Called when client receives a message from the server
        console.log(event.data);

        // display on browser
        var log = document.getElementById("log");
        log.innerHTML += "message seen " + "\n";
        log.innerHTML += "message from server: " + event.data + "\n";
    };

    ws.onopen = function(event) { // called when connection is opened
        var log = document.getElementById("log");
        log.innerHTML += "Connected to " + event.currentTarget.url + "\n";
    };
}

function send() {  // this is how to send messages
    var content = document.getElementById("msg").value;
    ws.send(content);
}

<<<<<<< HEAD
// var ws;
//
// function connect() {
//     var username = document.getElementById("username").value;
//     var wsserver = document.getElementById("wsserver").value;
//     var url = wsserver + username;
//
//     ws = new WebSocket(url);
//
//     ws.onmessage = function (event) {
//         console.log(event.data);
//
//         var log = document.getElementById("log");
//         if (event.data.startsWith("Message seen: ")) {
//             // Handle "Message seen" status
//             var seenMessage = event.data.substring("Message seen: ".length);
//             log.innerHTML += "Message seen by server: " + seenMessage + "\n";
//         } else {
//             // Display regular messages
//             log.innerHTML += "Message from server: " + event.data + "\n";
//         }
//     };
//
//     ws.onopen = function (event) {
//         var log = document.getElementById("log");
//         log.innerHTML += "Connected to " + event.currentTarget.url + "\n";
//     };
// }
//
// function send() {
//     var content = document.getElementById("msg").value;
//     ws.send(content);
// }

