const inputPw = document.querySelector("#inputPw");
const regExp = /^[a-zA-Z0-9!@#_\-\^]{8,20}$/;
const changeBtn = document.querySelector("#changeBtn");
const changePwForm = document.querySelector("#changePwForm");
const inputPwCheck = document.querySelector("#inputPwCheck");
let regExpStatus = false;

changeBtn.addEventListener("click", e => {

    if(inputPw.value.trim().length == 0){
        e.preventDefault();
        alert("비밀번호를 입력해주세요.");
        regExpStatus = false;
        return;
    }

    if(inputPwCheck.value.trim().length == 0){
        e.preventDefault();
        alert("비밀번호 확인을 입력해주세요.");
        regExpStatus = false;
        return;
    }

    if(!regExp.test(inputPw.value)){
        e.preventDefault();
        alert("형식에 맞는 비밀번호를 작성해주세요.");
        return;
    }

    if(inputPw.value != inputPwCheck.value){
        e.preventDefault();
        alert("입력한 비밀번호와 동일한 비밀번호를 입력해주세요.");
        return;
    }
    regExpStatus = true;
    alert("비밀번호가 변경되었습니다");
});
