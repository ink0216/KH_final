const calcelBtn = document.querySelector("#calcelBtn");
const okBtn = document.querySelector("#okBtn");
const agreeBtn = document.querySelector("#agreeBtn");


calcelBtn.addEventListener("click", () => {
    location.href = "/myPage/myPageInfo";
});

okBtn.addEventListener("click", e => {
    const memberPw = document.querySelector("#memberPw").value;
    if(memberPw.trim().length == 0){
        alert("비밀번호를 입력해주세요.");
        e.preventDefault();
        return;
    }
    if(!agreeBtn.checked){
        alert("약관에 동의해주세요.");
        e.preventDefault();
        return;
    }
    if(confirm("탈퇴하시겠습니까?")){
        okBtn.submit();
    }
    else{
        alert("취소되었습니다.");
        e.preventDefault();
        return;
    }
});