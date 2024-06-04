const declareFrm = document.querySelector("#declareFrm");

const declareBtn = document.querySelector("#declareBtn");



document.querySelector("#declareFrm").addEventListener("submit", e => {

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



    

    // if(reviewBoardCondition == '1'){
    //     alert("이미 신고된 게시글입니다.");
    //     e.preventDefault();
    //     return;
    // }


 
    alert("신고 처리가 완료되었습니다.");

})


