const siren = document.querySelector("#declare-area");

/* 신고 아이콘 클릭시 신고 팝업창 */


    siren.addEventListener("click",(e) => {

        if(loginMemberNo == null){

            alert("로그인 후 신고 서비스를 이용할 수 있습니다.");
            return;
        }

        
       

        window.open('/declarePopup?reviewBoardNo='+reviewBoardNo,
                'popupWindow', 'width=770, height=1200, left=150, resizable = no');
       
      
        
    }
);

        








/* 삭제 버튼 */

const deleteBtn = document.querySelector("#deleteBtn")

if(deleteBtn != null){
deleteBtn.addEventListener("click", () => {

    if(!confirm("게시글을 정말 삭제하시겠습니까?")){
        
        alert("게시글 삭제가 취소되었습니다.");
        return;
    }
    alert("삭제되었습니다.");

    

     // /board/AS/21?cp=1 -> /editBoard/AS/21/delete?cp=1 
     const url = location.pathname.replace("reviewBoard","editReviewBoard")  + "/delete"; 

     // form태그 생성 
     const form = document.createElement("form");
     form.action = url;
     form.method = "POST";
 
     // cp값을 저장할 input 생성
     const input = document.createElement("input");
     input.type = "hidden";
     input.name = "cp";
 
     // 쿼리스트링에서 원하는 파라미터 얻어오기
     const params = new URLSearchParams(location.search); 
     //주소에 있는 파라미터만 다루는 객체 URLSearchParams
     //location.search 하면 쿼리스트링만 나오게 함
     
     const cp = params.get("cp"); //주소 쿼리스트링에 있는 cp값이 얼마냐 해서 값 꺼내올 수 있다
     input.value = cp;
 
     form.append(input);
 
     // 화면에 form태그를 추가한 후 제출하기
     document.querySelector("body").append(form);
     form.submit();
     });


};



/* 수정버튼  */

const updateBtn = document.querySelector("#updateBtn");

if(updateBtn != null){

    

    updateBtn.addEventListener("click", () => {


       
        location.href = location.pathname.replace('reviewBoard','editReviewBoard') + "/update" + location.search;
    })

}



/* 목록으로 버튼을 클릭해서 게시판 목록으로 돌아가기 */
const goToListBtn = document.querySelector("#goToListBtn");

goToListBtn.addEventListener("click", () => {



  let url = location.pathname;
  url = url.substring(0, url.lastIndexOf("/"));

  // 쿼리스트링
  location.href = url + location.search;
 
});