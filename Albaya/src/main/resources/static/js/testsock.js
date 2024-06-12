const testSock = new SockJS("/testSock");

const sendMessageFn = (str) => {
    testSock.send(str);
}

testSock.addEventListener('message', e => {
    console.log(e.data);
})