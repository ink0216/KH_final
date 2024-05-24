
const regExp = /^[a-zA-Z0-9!@#_\-\^]{8,20}$/;
const changeBtn = document.querySelector("#changeBtn");
let regExpStatus = false;

changeBtn.addEventListener("click", e => {
    const curPassword = document.querySelector("#curPassword").value;
    const newPassword = document.querySelector("#newPassword").value;
    const checkNewPassword = document.querySelector("#checkNewPassword").value;
    
    if(curPassword.trim().length == 0){
        e.preventDefault();
        alert("비밀번호를 입력해주세요.");
        regExpStatus = false;
        return;
    }

    if(newPassword.trim().length == 0){
        e.preventDefault();
        alert("새로운 비밀번호를 입력해주세요.");
        regExpStatus = false;
        return;
    }

    if(checkNewPassword.trim().length == 0){
        e.preventDefault();
        alert("비밀번호 확인을 입력해주세요.");
        regExpStatus = false;
        return;
    }

    if(!regExp.test(newPassword)){
        e.preventDefault();
        alert("형식에 맞는 비밀번호를 작성해주세요.");
        return;
    }

    if(newPassword != checkNewPassword){
        e.preventDefault();
        alert("입력한 비밀번호와 동일한 비밀번호를 입력해주세요.");
        return;
    }
    regExpStatus = true;
});

