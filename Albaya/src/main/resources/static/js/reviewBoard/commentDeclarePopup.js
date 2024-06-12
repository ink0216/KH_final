
const commentDeclareBtn = document.querySelector("#commentDeclareBtn");


document.querySelector("#commentDeclareFrm").addEventListener("submit", e => {

    const commentDeclareContent = document.querySelector("[name='commentDeclareContent']");
    

    // 테스트용
    // declareBtn.addEventListener("click",() => {

    //     alert("신고버튼클릭");

    //     return;
    // })
    


    if(commentDeclareContent.value.trim().length == 0) {

        alert("신고 사유를 입력해주세요.");

        commentDeclareContent.focus();

        e.preventDefault();

        return;
    }

  




    alert("신고 처리가 완료되었습니다.");

})


