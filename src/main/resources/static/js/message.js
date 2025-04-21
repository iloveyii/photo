

(function(){

const socket = new SockJS('https://spring.softhem.net/ws');
const stompClient = Stomp.over(socket);

stompClient.connect({}, function(frame) {
    console.log('Connected: ' + frame);

    // Subscribe to the topic
    stompClient.subscribe('/topic/chat', function(greeting) {
        console.log('Received greetings');
        console.log(greeting);
        const message = JSON.parse(greeting.body);
        console.log('Message::' , message)
        showGreeting(message);
    });
});

function sendName() {
    const name = document.getElementById('name').value;
    const message = document.getElementById('message').value
    if (!name) {
        alert('Please type your name and then click Send button');
        return;
    }
    if(!message) {
        alert('Message is empty!');
        return;
    }
    console.log('inside sendName:: name:' + name + ', message:' + message)
    // Send message to the server
    stompClient.send("/app/messaging", {}, JSON.stringify({ 'name': name, 'message': message }));
}

function showGreeting(message) {
    const response = document.getElementById('messages');
    const p = document.createElement('p');
    const b = document.createElement('b');
    b.appendChild(document.createTextNode(message.name + ' says:'));
    p.appendChild(b);
    p.appendChild(document.createTextNode(message.message));
    response.appendChild(p);
}

const sendBtn = document.getElementById('btn-send')
sendBtn.addEventListener('click', (e) => {
    e.preventDefault();
    console.log('clicked');
    sendName();
})


})();