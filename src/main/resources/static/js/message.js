

(function(){

const socket = new SockJS('/ws');
const stompClient = Stomp.over(socket);

stompClient.connect({}, function(frame) {
    console.log('Connected: ' + frame);

    // Subscribe to the topic
    stompClient.subscribe('/topic/greetings', function(greeting) {
        const message = JSON.parse(greeting.body);
        showGreeting(message.content);
    });
});

function sendName() {
    const name = document.getElementById('name').value;
    // Send message to the server
    stompClient.send("/app/hello", {}, JSON.stringify({ 'name': name }));
}

function showGreeting(message) {
    const response = document.getElementById('response');
    const p = document.createElement('p');
    p.appendChild(document.createTextNode(message));
    response.appendChild(p);
}


})();