const declareFrm = document.querySelector("#declareFrm");
const declareBtn = document.querySelector("#declareBtn");


declareFrm.addEventListener("submit", e => {

    const declareContent = document.querySelector("[name='boardDeclareContent']");
    

    // 테스트용
    // declareBtn.addEventListener("click",() => {

    //     alert("신고버튼클릭");

    //     return;
    // })
    


    if(declareContent.value.trim().length == 0) {

        alert("신고 사유를 입력해주세요.");

        declareContent.focus();

        e.preventDefault();

        return;
    }

  




    if(confirm("정말 신고를 하시겠습니까?")){

        alert("신고 처리가 완료되었습니다.");

    } else {

        e.preventDefault();
        return;
        
    }
    

})


