const personalMember = document.querySelector('#personalMember');
const coparationMember = document.querySelector('#coparationMember');
const authKey = document.querySelector("#authKey");
const authBtn = document.querySelector("#authBtn");
const nextBtn = document.querySelector("#nextBtn");
let authState = false;

personalMember.addEventListener("click", () => {
    personalMember.style.backgroundColor = "rgb(190, 189, 189)";
    coparationMember.style.backgroundColor = "white"
    document.querySelector('.login-form').action = '/member/login';
});

coparationMember.addEventListener("click", () => {
    coparationMember.style.backgroundColor = "rgb(190, 189, 189)";
    personalMember.style.backgroundColor = "white"
    document.querySelector('.login-form').action = '/shop/login';
});

document.querySelector('#memberAuth').addEventListener('click', e => {
    e.preventDefault();
    const inputTel = document.querySelector('#inputTel').value;
    const memberName = document.querySelector("#memberName").value;
    const randomNumber = Math.floor(100000 + Math.random() * 900000);
    const msg = randomNumber.toString();

    const findIdObj = {
        "memberPhoneNumber" : inputTel,
        "memberName" : memberName
    }

    const obj = {
        "inputTel" : inputTel,
        "msg" : msg
    }

    fetch('/member/findId', {
        method : 'POST',
        headers : {
            'Content-Type': 'application/json'
        },
        body : JSON.stringify(findIdObj)
    })
    .then(resp => resp.json())
    .then(result => {
        if(result >= 1){
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
                authBtn.addEventListener("click", () => {
                    if(authKey.value != obj.msg){
                        alert("인증번호가 일치하지 않습니다.");
                        authState = false;
                    }
                    else{
                        alert('인증되었습니다.');
                        authState = true;
                    }
                })
            })
            .catch(error => {
                alert('인증번호 요청에 실패했습니다.');
            });
        }
        else{
            alert('일치하는 회원 정보가 없습니다.');
        }
    })
});

nextBtn.addEventListener("click", e => {
    if(!authState){
        e.preventDefault();
        alert("인증을 완료해주세요.");
        return;
    }
})