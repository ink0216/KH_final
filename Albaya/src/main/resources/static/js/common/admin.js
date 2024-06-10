
const reject = document.querySelector("#reject");
const accept = document.querySelector("#accept");
const reviewBoardDeclareNo = document.querySelector("#reviewBoardDeclareNo");
const reviewBoardNo = document.querySelector("#reviewBoardNo");
const memberNo = document.querySelector("#memberNo");
const reviewBoardCondition = document.querySelector("#reviewBoardCondition");
const boardDeclareDate = document.querySelector("#boardDeclareDate");
const reportedMemberNo = document.querySelector("#reportedMemberNo");
const tbody = document.querySelector(".tbody");


// 표 조회
const selectList = () => {

    fetch("/declare/selectList")

        .then(response => response.json())

        .then(result => {
  

            // const declareList = JSON.parse(result);
            const declareList = result;


            tbody.innerHTML = "";


            for (let declare of declareList) {
                
                const tr = document.createElement("tr");

                

                const arr = [
                    'reviewBoardDeclareNo',
                    'reviewBoardNo',
                    'memberNo',
                    'boardDeclareContent',
                    'reviewBoardCondition',
                    'boardDeclareDate',
                    'reportedMemberNo'
                ];





                for (let key of arr) {
                    const td = document.createElement("td");





                    if (key === 'reviewBoardNo') {

                        const link = document.createElement("a");

                        link.href = `/reviewBoard/2/${declare[key]}?reviewBoardCode=2&reviewBoardNo=${declare[key]}&cp=${currentPage}`;

                        link.innerText = declare[key];

                        link.classList.add("connectToReviewBoard");

                        td.appendChild(link);

                    } else {

                        td.innerText = declare[key];

                    }

                    tr.append(td);


                }




                const buttonTd = document.createElement("td");



                const reject = document.createElement("button");
                reject.innerText = "반려";
                reject.classList.add("reject");



                const accept = document.createElement("button");
                accept.innerText = "확정";
                accept.classList.add("accept");



                buttonTd.append(reject, accept);
                tr.append(buttonTd);



                tbody.append(tr);
            }

     
            attachEventListeners();
        });
}




// 반려 버튼 
const attachEventListeners = () => {
    const rejectButtons = document.querySelectorAll('.reject');



    rejectButtons.forEach(button => {
        button.addEventListener('click', e => {
            // reject 기능 추가



            if (confirm("해당 신고내용을 반려 처리하시겠습니까?")) {
                fetch("/declare/reject", {

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

                fetch("/declare/complete", {

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

document.addEventListener('DOMContentLoaded', function () {
  
    attachEventListeners();
    
    selectList();
});




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



const connectToCommentAdmin = document.getElementById('connectToCommentAdmin');
connectToCommentAdmin.addEventListener('click', () => {
    
    alert("클릭 테스트");
});  