const reject = document.querySelector("#reject");
const accept = document.querySelector("#accept");
const memberNo = document.querySelector("#memberNo");
const commentDeclareNo = document.querySelector("#commentDeclareNo");
const commentNo = document.querySelector("#commentNo");
const connectToDeclareAdmin = document.querySelector("#connectToDeclareAdmin");
const reportedMemberNo = document.querySelector("#reportedMemberNo");
const commentDeclareDate = document.querySelector("#commentDeclareDate");
const tbody = document.querySelector(".tbody");

const commentContent = document.querySelector("#commentContent");



// 게시글 신고 페이지 이동 버튼
connectToDeclareAdmin.addEventListener("click", () => {
    location.href = "/declare/1?cp=1";
})




const createPageLink = (pageNo, text) => {
    const li = document.createElement("li");

    const span = document.createElement("span");
    span.className = "page-link";

    span.dataset.cp = pageNo; // 이동할 페이지 번호

    span.innerText = text; // 화면에 보여질 기호(<< ,>>)/숫자(1,2,3)

    li.append(span);

    return li;
}




// 댓글 신고 표 조회
const selectList = (cp) => {


    fetch(`/commentDeclare/selectList?cp=${cp}`)

        .then(response => response.json())

        .then(result => {
  

            // const declareList = JSON.parse(result);
            const commentDeclareList = result.commentDeclareList;
            const pagination = result.pagination;



            tbody.innerHTML = "";


            for (let commentDeclare of commentDeclareList) {
                
                const tr = document.createElement("tr");

                const arr = [
                    'commentDeclareNo',
                    'commentContent',
                    'memberNo',
                    'commentDeclareContent',
                    'commentDeclareDate',
                    'reportedMemberNo'
                ];


                for (let key of arr) {
                    const td = document.createElement("td");

 
                    if (key === 'commentDeclareContent') {
                        const div = document.createElement("div");
                        div.innerText = commentDeclare[key];
                        div.classList.add("commentDeclareContent");
                        td.appendChild(div);

                    } else 

                    if (key === 'commentContent') {

                        const div = document.createElement("div");
                        div.innerText = commentDeclare[key];
                        div.classList.add("commentContent");
                        td.appendChild(div);
                    }
                    else
                    {

                        td.innerText = commentDeclare[key];

                    }

                    tr.append(td);


                }





                const buttonTd = document.createElement("td");

                const div = document.createElement("div");
                div.classList.add("buttonArea");

                const reject = document.createElement("button");
                reject.innerText = "반려";
                reject.classList.add("reject");



                const accept = document.createElement("button");
                accept.innerText = "확정";
                accept.classList.add("accept");


                div.append(reject,accept);
                buttonTd.append(div);
                tr.append(buttonTd);



                tbody.append(tr);
            }


            /* pagination 만들기 */
            const ul =  document.querySelector(".pagination");
            ul.innerHTML = ""; // 이전 내용 삭제

            // 첫 페이지
            const firstPage = createPageLink(1, "<<");
            ul.append(firstPage);

            // 이전 페이지
            const prevPage = createPageLink(pagination.prevPage, "<");
            ul.append(prevPage);

            for(let i= pagination.startPage ; i <= pagination.endPage ; i++) {

                // 현재 페이지 == cp
                if(i === pagination.currentPage) {
                    const li = document.createElement("li");
                    const span = document.createElement("span");
                    span.className = "current";
                    span.innerText = i; 
                
                    li.append(span);
                    ul.append(li);

                } else {
                    const page = createPageLink(i, i);
                    ul.append(page);
                }

            }


            // 다음 페이지
            const nextPage = createPageLink(pagination.nextPage, ">");
            ul.append(nextPage);
            
            // 마지막 페이지
            const endPage = createPageLink(pagination.maxPage, ">>");
            ul.append(endPage);


     
            attachEventListeners();
        });
}


const attachEventListeners = () => {
    const rejectButtons = document.querySelectorAll('.reject');



    rejectButtons.forEach(button => {
        button.addEventListener('click', e => {
            // reject 기능 추가



            if (confirm("해당 신고내용을 반려 처리하시겠습니까?")) {
                fetch("/commentDeclare/reject", {

                    method: "PUT",

                    headers: { "Content-Type": "application/json" },

                    body: button.closest("tr").children[0].innerText

                })
                    .then(response => response.text())

                    .then(result => {

                        if (result > 0) {

                            alert("해당 신고가 반려 처리되었습니다.");

                            let cp = document.querySelector(".current").innerText;  

                            if(document.querySelectorAll(".tbody > tr").length ===1){

                                if(cp > 1 ){
                                    cp = cp-1;
                                }
                            }

                            selectList(cp);
                        }
                    });

            } else {

                alert("반려 처리가 취소되었습니다.");

                e.preventDefault();

                return;

            }
        });
    });



// 신고 처리 완료 비동기
    const acceptButtons = document.querySelectorAll('.accept');


    acceptButtons.forEach(button => {

        button.addEventListener('click', function () {

            // accept 기능 추가

            if (confirm("확인 버튼을 누르면 해당 댓글이 삭제되고 댓글 작성자의 경고 횟수가 1 증가합니다. 정말 신고 확정을 하시겠습니까?")) {

                fetch("/commentDeclare/complete", {

                    method: "PUT",

                    headers: { "Content-Type": "application/json" },

                    body: button.closest("tr").children[0].innerText

                })
                    .then(response => response.text())

                    .then(result => {

                        if (result > 0) {

                            alert("신고 확정 처리되었습니다.");

                            let cp = document.querySelector(".current").innerText;  

                            if(document.querySelectorAll(".tbody > tr").length ===1){

                                if(cp > 1 ){
                                    cp = cp-1;
                                }   
                            }

                            selectList(cp);
                        }
                    });
            } else {

                alert("신고 확정 처리가 취소되었습니다.");

                e.preventDefault();

                return;

            }
        });
    });


    /* 페이지네이션 버튼 클릭 동작 추가 */
    const pageLinks = document.querySelectorAll(".page-link");


    pageLinks.forEach(link => {
        link.addEventListener("click", e => {
            selectList(link.dataset.cp);
        })
    })
}











document.getElementById('searchBtn').addEventListener("click", () => {

    const memberNo = document.getElementById("inputMemberNo").value;


    if(inputMemberNo.value.trim().length === 0){

        alert("계정 상태를 확인 할 회원번호를 입력해주세요.");

        resultDiv.innerHTML = "";

        e.preventDefault();

        return;

    }

    fetch(`/member/search?memberNo=${memberNo}`)
    .then(response => response.json())
    .then(data => {
        const resultDiv = document.getElementById('result');
        if (data) {
            let memberStatusText;
            switch (data.memberStatus) {
                case 1:
                    memberStatusText = "1회 경고";
                    break;
                case 2:
                    memberStatusText = "2회 경고";
                    break;
                case 3:
                    memberStatusText = "정지 상태";
                    break;
                case 4:
                    memberStatusText = "정상 회원";
                    break;
                case 5:
                    memberStatusText = "탈퇴 회원";
                    break;
                default:
                    memberStatusText = "알 수 없는 상태";
            }

            resultDiv.innerHTML = `회원 번호 : ${data.memberNo} <br> 회원 계정 : ${data.memberEmail} <br> 회원 상태 : ${memberStatusText}`;
        } 
    })
    .catch(error => {
        document.getElementById('result').innerHTML = '존재하지 않은 회원 번호입니다.';
    });


    

})

document.addEventListener('DOMContentLoaded', function () {
  
    attachEventListeners();
    
  
});



