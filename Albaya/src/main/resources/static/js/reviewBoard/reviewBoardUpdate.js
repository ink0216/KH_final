


// 수정 폼 제출 유효성 검사(내부 문장들은 글쓰기때의 유효성 검사랑 동일함)
const reviewBoardUpdateFrm = document.querySelector("#reviewBoardUpdateFrm");


reviewBoardUpdateFrm.addEventListener("submit", e => {


    const reviewTitle = document.querySelector("[name='reviewTitle']");
    const reviewBoardContent = document.querySelector("[name='reviewBoardContent']");


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


/* 뒤로가기 버튼 */

const cancelBtn = document.querySelector("#cancelBtn")

cancelBtn.addEventListener("click",() => {

    if(confirm("뒤로가기를 하시면 지금까지 작성했던 내용이 모두 사라집니다. 정말 뒤로가시겠습니까?")){
        
      
    

        location.href = '/reviewBoard/2';

    } else{

        e.preventDefault();
        return;
    }

})
