/* 글쓰기 버튼 동작 */
const insertBtn = document.querySelector("#insertBtn");
const insertNoticeBtn = document.querySelector("#insertNoticeBtn");

if(insertBtn != null){
insertBtn.addEventListener("click", () =>{
    
  
    
    location.href = `/editReviewBoard/${reviewBoardCode}/insert`;
});


}


/* 관리자전용 공지작성 버튼 동작 */
if(insertNoticeBtn != null){
insertNoticeBtn.addEventListener("click", () =>{

    
    location.href = `/editReviewBoard/${reviewBoardCode}/insert`;
});


}

/* 공지버튼 토크버튼 효과 */
const notice = document.getElementById('notice');
const talk = document.getElementById('talk');





/* 현재 게시판 위치에 따라 버튼 효과 넣기  */



function toggleColor(boxId, url) {
    var box = document.getElementById(boxId);
    var otherBoxId = boxId === 'talk' ? 'notice' : 'talk';
    var otherBox = document.getElementById(otherBoxId);

    if (!box.classList.contains('active')) {
        box.classList.add('active');
        otherBox.classList.remove('active');
    }

    localStorage.setItem('highlightedBox', boxId);

    window.location.href = url;
}

function restoreColor() {
    var highlightedBox = localStorage.getItem('highlightedBox');
    if (highlightedBox) {
        document.getElementById(highlightedBox).classList.add('active');
    } else {
        // 기본으로 'talk'를 활성화합니다.
        document.getElementById('talk').classList.add('active');
    }
}

window.onload = restoreColor;















/* 검색 관련 요소 */
const searchKey = document.querySelector("#searchKey");
const searchQuery = document.querySelector("#searchQuery");
const options = document.querySelectorAll("#searchKey > option");


/* 하단 검색창에서 검색을 하고 화면이 바뀌면, 검색한 내역이 그대로 남게 하는 기능 */





// 쿼리스트링 값을 key, value 구분해서 저장하는 객체 반환
(()=>{
    const params = new URL(location.href).searchParams;
  
    const key = params.get("key"); // t, c, tc 중 하나
    const query = params.get("query"); // 검색어
  
    if(key != null){ // 검색을 했을 때
        searchQuery.value = query; // 검색어를 화면에 출력
  
        // option태그를 하나씩 순차 접근해서 value가 key랑 같으면
        // selected 속성 추가 
        for(let op of options){
            if(op.value == key){
                op.selected = true;
            }
        }
    }
}
)




