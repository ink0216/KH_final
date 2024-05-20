const siren = document.querySelector("#siren");

/* 신고 아이콘 클릭시 신고 팝업창 */


    function openPopup(){
  
        window.open('/declarePopup.html', 'popupWindow', 'width=770, height=1200, left=150, resizable = no');
    }




    





/* 목록으로 버튼 */
const goToListBtn = document.querySelector("#goToListBtn");


goToListBtn.addEventListener("click", () =>{

    location.href="/reviewBoardList";
});