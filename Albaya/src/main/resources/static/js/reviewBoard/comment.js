


/* 댓글 목록 요청(ajax) */
const selectCommentList = () => {



    fetch("/comment?reviewBoardNo="+ reviewBoardNo) // GET 방식 요청
    .then(response => response.json())
    .then(commentList => {
        console.log(commentList);
        
        // 화면에 존재하는 기존 댓글 목록 삭제 후 조회된 commentList를 이용해서 새로운 댓글 목록을 출력


        // ul태그(댓글 목록을 감싸는 요소)
        const ul = document.querySelector("#commentList");

        ul. innerHTML = ""; // 기존 댓글 목록 삭제


        // 조회된 commentList를 이용해 댓글 출력
        for(let comment of commentList){
            

            // 행 생성 + 클래스 추가
            const commentRow = document.createElement("li");
            commentRow.classList.add("comment-row");


            // 대댓글(자식댓글)인 경우(parentCommentNo 가 0이 아닌경우) "child-comment"클래스 추가
            if(comment.parentCommentNo != 0){
                commentRow.classList.add("child-comment");}

            
                // 삭제된 댓글인데, 대댓글이 존재하는 경우 부모 댓글만 안보이게 하게 설정
            if(comment.commentDelFl == 'Y'){

                    commentRow.innerText = "*삭제된 댓글*";

            } else {
                    // 부모 댓글이 삭제되지 않은 댓글일 경우 작성자, 날짜 감싸는 요소가 표시
                    const commentWriter = document.createElement("p");

                    commentWriter.classList.add("comment-writer");


                   
                    


                

                    // 닉네임
                    const nickname = document.createElement("span");
                    nickname.innerText = comment.memberEmail;

                    // 날짜(작성일)
                    const commentDate = document.createElement("span");
                    
                    commentDate.classList.add("comment-date");

                    commentDate.innerText = comment.commentWriteDate;


                    // 작성자 영역(commentWriter)에 닉네임, 날짜 추가
                    commentWriter.append(nickname, commentDate);

                    
                    // 댓글 행에 작성자 영역 추가
                    commentRow.append(commentWriter);


                    // 댓글 내용
                    const content = document.createElement("p");
                    content.classList.add("comment-content");
                    content.innerText = comment.commentContent;
                    commentRow.append(content);



                    // 버튼 영역
                    const commentBtnArea = document.createElement("div");
                    commentBtnArea.classList.add("comment-btn-area");


                    // 답글 버튼
                    const childCommentBtn = document.createElement("button");
                    childCommentBtn.innerText = "답글";

                    // 답글 버튼에 onclick 이벤트 리스너 추가 
                    childCommentBtn.setAttribute("onclick",`showInsertComment(${comment.commentNo},this)`);


                    // 버튼 영역에 답글 추가
                    commentBtnArea.append(childCommentBtn);


                    // 현재 로그인한 사람이 있으면서 로그인한 회원 번호와 댓글 작성자의 번호와 같으면 댓글 수정/삭제 버튼도 출력
                    if(loginMemberNo != null && loginMemberNo == comment.memberNo){

                        // 수정 버튼
                        const updateBtn = document.createElement("button");
                        updateBtn.innerText = "수정";
                       


                        // 삭제 버튼
                        const deleteBtn = document.createElement("button");
                        deleteBtn.innerText = "삭제";
                        deleteBtn.setAttribute("onclick",`deleteComment(${comment.commentNo})`);



                        // 버튼 영역에 수정, 삭제 버튼 추가
                        commentBtnArea.append(updateBtn, deleteBtn);
                    }


                    // 행에 버튼 영역을 추가
                    commentRow.append(commentBtnArea);

                }

                // 댓글 목록(ul)에 행(li)추가
                ul.append(commentRow);
            }

            
        

    });
}




/* 댓글 작성하고 등록하기 */

const addContent = document.querySelector("#addComment");
const commentContent = document.querySelector("#commentContent");


// 댓글 등록 버튼 클릭
addContent.addEventListener("click", e =>{

    // // 일반 회원 로그인이 되어있지 않은 경우
    // if(loginMemberNo == null){
        
    //     alert("일반 회원으로 로그인하셔야 작성할 수 있습니다.");

    //     return;
    // }


    // 아무것도 입력하지 않고 댓글 작성하려고 한 경우
    if(commentContent.value.trim().length == 0){

        alert("내용이 작성되지 않았습니다.");

        commentContent.focus();

        return;
    }


    // ajax를 이용해 댓글 등록 요청
    const data = {

        "commentContent" : commentContent.value,
        "reviewBoardNo" : reviewBoardNo,
        "memberNo" : memberNo // 또는 Session 회원 번호

    };


    fetch("/comment",{
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify(data) // data 객체를 JSON 문자열로 변환
    })


    .then(response => response.text())
    .then(result => {


        // result == 작성된 댓글 번호

        if(result > 0) {
            
            alert("댓글 등록 완료");

            // 댓글 달고 나면 commentContent 내용을 지움
            commentContent.value = "";


            // 댓글 목록을 지웠다가 다시 조회해서 화면에 출력하는 함수인 selectCommentList() 호출
            selectCommentList();    


            /* 글 작성자에게 댓글이 등록되었다는 알림 전달 + 알림 클릭시 해당 댓글이 작성된 위치로 이동 */
            sendNotificationFn("insertComment", `${location.pathname}?cn=${result}`,reviewBoardNo);
        }

        else{
            alert("댓글 등록 실패");
        }

    })
    .catch(err => console.log(err));

});



/**
 * 답글 작성 화면 추가
 * @param {*} parentCommentNo 
 * @param {*} btn 
 */



const showInsertComment = (parentCommentNo, btn) => {

    
  // ** 답글 작성 textarea가 한 개만 열릴 수 있도록 만들기 **
  const temp = document.getElementsByClassName("commentInsertContent");



  if(temp.length > 0){ // 답글 작성 textara가 이미 화면에 존재하는 경우



    if(confirm("다른 답글을 작성 중입니다. 현재 댓글에 답글을 작성 하시겠습니까?")){

      // 예를 누를 시

      temp[0].nextElementSibling.remove(); // 버튼 영역부터 삭제


      temp[0].remove(); // textara 삭제 (기준점은 마지막에 삭제해야 된다!)
    

    } else{

      return; // 함수를 종료시켜 답글이 생성되지 않게함.
    }

  }
  
  // 답글을 작성할 textarea 요소 생성
  const textarea = document.createElement("textarea");
  textarea.classList.add("commentInsertContent");
  


  // 답글 버튼의 부모의 뒤쪽에 textarea 추가
  // after(요소) : 뒤쪽에 추가
  btn.parentElement.after(textarea);



  // 답글 버튼 영역 + 등록/취소 버튼 생성 및 추가
  const commentBtnArea = document.createElement("div");
  commentBtnArea.classList.add("comment-btn-area");



  const insertBtn = document.createElement("button");
  insertBtn.innerText = "등록";
  insertBtn.setAttribute("onclick", "insertChildComment("+parentCommentNo+", this)");
 

  const cancelBtn = document.createElement("button");
  cancelBtn.innerText = "취소";
  cancelBtn.setAttribute("onclick", "insertCancel(this)");



  // 답글 버튼 영역의 자식으로 등록/취소 버튼 추가
  commentBtnArea.append(insertBtn, cancelBtn);



  // 답글 버튼 영역을 화면에 추가된 textarea 뒤쪽에 추가
  textarea.after(commentBtnArea);


}




/* 자식 댓글 작성 취소 버튼 동작 */

/** */
// 자식 댓글 작성란에서 취소 버튼을 누르면 댓글 작성 버튼과 text-area가 보이지 않게 됨
const insertCancel = (cancelBtn) => {

    // 취소 버튼 부모의 이전 요소인 text-area를 제거
    cancelBtn.parentElement.previousElementSibling.remove();


    // 취소 버튼이 존재하는 버튼 영역이 제거
    cancelBtn.parentElement.remove();
}




/* 자식 댓글 등록 버튼 동작 */
const insertChildComment = (parentCommentNo, btn) => {

    // 답글 내용이 작성된 textarea
    const textarea = btn.parentElement.previousElementSibling;


    if(textarea.value.trim().length == 0) {
        // 자식 댓글 내용이 써져있지 않다면 등록 안됨
        alert("내용이 작성되지 않았습니다.");
        textarea.focus();
        return;
    }


    // 답글 작성시 필요한 요소들을 가져옴
    const data = {

        "commentContent" : textarea.value,
        "reviewBoardNo" : reviewBoardNo,
        "memberNo" : loginMemberNo, // 또는 Session 회원 번호
        "parentCommentNo" : parentCommentNo

    };

    fetch("/comment",{
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify(data) // data 객체를 JSON 문자열로 변환
    })


    .then(response => response.text())
    .then(result => {

        if(result > 0) {
            
            alert("답글 등록 완료");

            // 답글 달고 나면 commentContent 내용을 지움
            // commentContent.value = "";

            // 답글을 달면 댓글 작성자에게 알림이 감
            sendNotificationFn("insertComment", `${location.pathname}?cn=${result}`,reviewBoardNo);


            // 댓글 목록을 지웠다가 다시 조회해서 화면에 출력하는 함수인 selectCommentList() 호출
            selectCommentList();    
        }

        else{
            alert("답글 등록 실패");
        }

    })
    .catch(err => console.log(err));

}




/* 댓글 삭제 버튼 동작 */
const deleteComment = commentNo => {
    

    // 댓글 삭제 여부 확인하기
    if(!confirm("삭제하시겠습니까?")) {

        alert("삭제가 취소되었습니다.");


        return;
    }    
    fetch("/comment",{
        method : "DELETE",
        headers : {"Content-Type":"application/json"},
        body : commentNo
    })
    .then(resp => resp.text())
    .then(result => {
        if ( result > 0 ){

            alert("댓글이 삭제되었습니다.");

            selectCommentList(); // 삭제를 조회하기 위해 화면 조회

        } else {

            alert("댓글 삭제 실패");

        }
    })
    .catch(err => console.log(err));
}





// 수정중인 상황에서 취소버튼을 눌러서 기존 화면으로 돌아가기 위한 변수 생성
let beforeCommentRow;

/* 댓글 수정버튼 동작 */
/* 수정버튼 클릭시 기존에 댓글 내용이 있는 공간에서 textarea로 바뀜 */

const showUpdateComment = (commentNo, btn) => {

    /* 댓글 수정 화면이 1개만 열릴 수 있도록 하기 */
    const temp = document.querySelector(".update-textarea");


    // 수정 textarea가 존재하는 상태라면 현재 열려있는 수정창을 닫기
    if(temp != null) {

        if(confirm("수정 중인 댓글이 있습니다. 수정할 댓글을 변경하시겠습니까?")){

            
            const commentRow = temp.parentElement;
            // 사전에 백업용으로 만들었던 변수를 이용해서 기존 댓글 화면을 되돌리고
            // 다음 수정 화면에서의 다시 백업을 만든다음 그 이전 백업 삭제
            commentRow.after(beforeCommentRow);
            commentRow.remove();


        }else {
            // 취소
            return;
        }
    }



    /* 1. 댓글 수정이 클릭된 행(.comment-row) 선택 */
    // 부모중에서 가장 가까운 li태그를 찾음
    const commentRow = btn.closest("li");

    // console.log(commentRow);


    /* 2. 수정 화면에서 취소버튼을 통해 돌아갈 때를 대비한 행 전체를 백업 */
    // 요소.cloneNode(true) : 요소 복제, true를 입력하면 하위 요소도 복제하겠다는 뜻
    beforeCommentRow = commentRow.cloneNode(true);


    // console.log(beforeCommentRow);


    /* 3. 기존 댓글에 작성되어 있던 내용만 얻어오기 */
  
    let beforeContent = commentRow.children[1].innerText;


    /* 4. 댓글 행 내부를 모두 삭제함 */
    commentRow.innerHTML = "";


    /* 5. 삭제된 행 내부 안에 textarea 생성 + 클래스, 내용 추가 */
    const textarea = document.createElement("textarea");

    textarea.classList.add("update-textarea");

    textarea.value = beforeContent;


    /* 6. 댓글 행에 textarea 추가  */
    commentRow.append(textarea);
    

    /* 7. 버튼 영역 생성 */
    const commentBtnArea = document.createElement("div");

    commentBtnArea.classList.add("comment-btn-area");


    /* 8. 수정 버튼 생성 */
    const updateBtn = document.createElement("button");

    updateBtn.innerHTML = "수정";

    updateBtn.setAttribute("onclick", `updateComment(${commentNo},this)`);


    /* 9. 취소 버튼 생성 */
    const cancelBtn = document.createElement("button");

    cancelBtn.innerHTML = "취소";

    cancelBtn.setAttribute("onclick","updateCancel(this)");



    /* 10. 버튼 영역에 수정/취소 버튼 추가 후 댓글 행에 버튼 영역 추가 */
    commentBtnArea.append(updateBtn,cancelBtn);

    commentRow.append(commentBtnArea);

 
}



/* 댓글 수정 취소 버튼 동작 */
const updateCancel = (btn) => {


    // 취소 동작을 하게 되면, 백업본이 있는 beforeCommentRow를 시행하고 기존 백업본을 소환함
        if(confirm("취소하시겠습니까?")){

            
            const commentRow = btn.closest("li");

            commentRow.after(beforeCommentRow);

            commentRow.remove();


        }



}


/* 댓글 수정 버튼 동작 */
/**
 * 
 * @param {*} commentNo 수정할 댓글 번호
 * @param {*} btn 클릭된 수정 버튼
 */
const updateComment = (commentNo, btn) => {

    // 수정된 내용이 작성된 textarea 얻어오기
    const textarea = btn.parentElement.previousElementSibling;


    // 수정 입력창이 아무것도 안 입력된 상태에서 제출하려고 하는 경우
    if(textarea.value.trim().length == 0){

        alert("수정할 내용이 없습니다.");

        textarea.focus();

        return;
    }


    const data = {

        "commentNo" : commentNo,
        "commentContent" : textarea.value

    }
    
    fetch("/comment", {

        method : "PUT",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify(data)
    })
    .then(resp => resp.text())
    .then(result => {
        if(result > 0){

            alert("수정되었습니다.");

            selectCommentList();
        }
        else {

            alert("수정 실패");
        }
    })
    .catch(err => console.log(err));
}










