




/* 제목, 내용이 입력이 안되어있을 경우 제출 못하게 하는 유효성 검사 */


document.querySelector("#reviewBoardWriteFrm").addEventListener("submit", e => {

    const reviewBoardTitle = document.querySelector("[name='reviewBoardTitle']");
    const reviewBoardContent = document.querySelector("[name='reviewBoardContent']");


    // 제목 미작성시
    if(reviewBoardTitle.value.trim().length == 0){
        alert("제목을 입력하세요.");

        // 제목쪽으로 커서를 옮김
        reviewBoardTitle.focus();

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

    alert("게시글 작성이 완료되었습니다.");
})







/* 삭제 버튼 클릭시
1. 삭제하시겠습니까? 확인/취소
2. 취소하면 취소되었습니다. 끝
3. 확인하면 /editBoard/{boardCode}/{boardNo}/delete GET 방식요청 
4. {boardCode} 게시판의 {boardNo} 글의 BOARD_DEL_FL 값을 Y로 변경 
5. 변경 성공시 해당 게시판 목록 1page로 리다이렉트, 실패시 보고있던 페이지 그대로.*/



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