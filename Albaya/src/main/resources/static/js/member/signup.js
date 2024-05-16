document.querySelector('#memberAuth').addEventListener('click', function(event) {
    event.preventDefault();
    const inputTel = document.querySelector('#inputTel').value;
    const randomNumber = Math.floor(100000 + Math.random() * 900000);
    const msg = randomNumber.toString();

    console.log(msg);
    const obj = {
        "inputTel" : inputTel,
        "msg" : msg
    }

    fetch('/member/send-one', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(obj)
    })
    .then(response => response.json())
    .then(resp => {
        alert('인증번호가 전송되었습니다.');
    })
    .catch(error => {
        alert('인증번호 요청에 실패했습니다.');
    });
});
