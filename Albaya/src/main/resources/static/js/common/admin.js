
const reject = document.querySelector("#reject");
const accept = document.querySelector("#accept");
const reviewBoardDeclareNo = document.querySelector("#reviewBoardDeclareNo");

// 반려 버튼

reject.addEventListener("click", () => {
    
    if(confirm("해당 신고내용을 반려 처리하시겠습니까?")){

        const declareNo = reviewBoardDeclareNo.innerText;

        fetch("/ajax/delete", {
            method : "DELETE",
            headers : {"Content-Type":"application/json"},
            body : declareNo
        })
        .then(response => response.text())
        .then(result => {
            if(result > 0){

                alert("해당 신고가 반려 처리되었습니다.");
                
            }
        })

       



    } else {

        alert("반려 처리가 취소되었습니다.");

        e.preventDefault();

        return;
    }
})



// 처리 버튼

accept.addEventListener("click", () => {

    if(confirm("확인 버튼을 누르면 해당 게시글이 삭제되고 게시글 작성자의 경고 횟수가 1 증가합니다. 정말 신고 확정을 하시겠습니까?")){

        alert("신고 확정 처리되었습니다.");


    } else {

        alert("신고 확정 처리가 취소되었습니다.");

        e.preventDefault();

        return;
    }
})