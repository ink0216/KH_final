const reject = document.querySelector("#reject");
const accept = document.querySelector("#accept");
const memberNo = document.querySelector("#memberNo");
const commentDeclareNo = document.querySelector("#commentDeclareNo");
const commentNo = document.querySelector("#commentNo");
const connectToDeclareAdmin = document.querySelector("#connectToDeclareAdmin");
const reportedMemberNo = document.querySelector("#reportedMemberNo");
const commentDeclareDate = document.querySelector("#commentDeclareDate");
const commentCondition = document.querySelector("#commentCondition");
const tbody = document.querySelector(".tbody");



// 게시글 신고 페이지 이동 버튼
connectToDeclareAdmin.addEventListener("click", () => {
    location.href = "/declare/1?cp=1";
})



// 댓글 신고 표 조회
// const selectList = (cp) => {

//     fetch("/commentDeclare/selectList?cp="+cp)

//         .then(response => response.json())

//         .then(result => {
  

           
//             const commentDeclareList = result.commentDeclareList;
//             const pagination = result.pagination;

//             console.log(pagination)


//             tbody.innerHTML = "";


//             for (let commentDeclare of commentDeclareList) {
                
//                 const tr = document.createElement("tr");

//                 const arr = [
//                     'commentDeclareNo',
//                     'commentNo',
//                     'memberNo',
//                     'commentDeclareContent',
//                     'commentDeclareCondition',
//                     'commentDeclareDate',
//                     'reportedMemberNo'
//                 ];


//                 for (let key of arr) {
//                     const td = document.createElement("td");


//                     // if (key === 'commentNo') {

//                     //     const link = document.createElement("a");

//                     //     link.href = `/reviewBoard/2/${commentDeclare[key]}?reviewBoardCode=2&reviewBoardNo=${declare[key]}&cp=${currentPage}`;

//                     //     link.innerText = declare[key];

//                     //     link.classList.add("connectToReviewBoard");

//                     //     td.appendChild(link);

//                     // } else {

//                         td.innerText = declare[key];

//                     // }

//                     tr.append(td);


//                 }




//                 const buttonTd = document.createElement("td");



//                 const reject = document.createElement("button");
//                 reject.innerText = "반려";
//                 reject.classList.add("reject");



//                 const accept = document.createElement("button");
//                 accept.innerText = "확정";
//                 accept.classList.add("accept");



//                 buttonTd.append(reject, accept);
//                 tr.append(buttonTd);



//                 tbody.append(tr);
//             }


//             /* pagination 만들기 */
//             const ul =  document.querySelector(".pagination");
//             ul.innerHTML = ""; // 이전 내용 삭제

//             // 첫 페이지
//             const firstPage = createPageLink(1, "<<");
//             ul.append(firstPage);

//             // 이전 페이지
//             const prevPage = createPageLink(pagination.prevPage, "<");
//             ul.append(prevPage);

//             for(let i= pagination.startPage ; i <= pagination.endPage ; i++) {

//                 // 현재 페이지 == cp
//                 if(i === pagination.currentPage) {
//                     const li = document.createElement("li");
//                     const span = document.createElement("span");
//                     span.className = "current";
//                     span.innerText = i; 
                
//                     li.append(span);
//                     ul.append(li);

//                 } else {
//                     const page = createPageLink(i, i);
//                     ul.append(page);
//                 }

//             }


//             // 다음 페이지
//             const nextPage = createPageLink(pagination.nextPage, ">");
//             ul.append(nextPage);
            
//             // 마지막 페이지
//             const endPage = createPageLink(pagination.maxPage, ">>");
//             ul.append(endPage);


     
//             attachEventListeners();
//         });
// }





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

                            selectList();
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

            if (confirm("확인 버튼을 누르면 해당 게시글이 삭제되고 게시글 작성자의 경고 횟수가 1 증가합니다. 정말 신고 확정을 하시겠습니까?")) {

                fetch("/commentDeclare/complete", {

                    method: "PUT",

                    headers: { "Content-Type": "application/json" },

                    body: button.closest("tr").children[0].innerText

                })
                    .then(response => response.text())

                    .then(result => {

                        if (result > 0) {

                            alert("신고 확정 처리되었습니다.");

                            selectList();
                        }
                    });
            } else {

                alert("신고 확정 처리가 취소되었습니다.");

                e.preventDefault();

                return;

            }
        });
    });
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
            resultDiv.innerHTML = `회원 번호 : ${data.memberNo} <br> 회원 계정 : ${data.memberEmail} <br> 회원 상태 : ${data.memberStatus}`;
        } else {
            resultDiv.innerHTML = '회원이 존재하지 않습니다.';
        }
    })
    .catch(error => {
        console.error('Error:', error);
        document.getElementById('result').innerHTML = '존재하지 않은 회원 번호입니다.';
    });


    

})

document.addEventListener('DOMContentLoaded', function () {
  
    attachEventListeners();
    
    selectList();
});



