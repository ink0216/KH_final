


// 수정 폼 제출 유효성 검사(내부 문장들은 글쓰기때의 유효성 검사랑 동일함)
const reviewBoardUpdateFrm = document.querySelector("#reviewBoardUpdateFrm");


reviewBoardUpdateFrm.addEventListener("submit", e => {


    const reviewTitle = document.querySelector("[name='reivewTitle']");
    const boardContent = document.querySelector("[name='reviewBoardContent']");


    // 제목 미작성시
    if(reviewTitle.value.trim().length == 0){
        alert("제목을 입력하세요.");

        // 제목쪽으로 커서를 옮김
        reviewTitle.focus();

        // form 제출 막음
        e.preventDefault();
        
        return;
    }


    // 내용 미작성시
    if(reviewBoardContent.value.trim().length == 0){
        alert("내용을 입력하세요.");

        // 제목쪽으로 커서를 옮김
        reviewBoardContent.focus();

        // form 제출 막음
        e.preventDefault();

        return;
    }



});

