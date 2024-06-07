
const reject = document.querySelector("#reject");
const accept = document.querySelector("#accept");
const reviewBoardDeclareNo = document.querySelector("#reviewBoardDeclareNo");
const reviewBoardNo = document.querySelector("#reviewBoardNo");
const memberNo = document.querySelector("#memberNo");
const reviewBoardCondition = document.querySelector("#reviewBoardCondition");
const boardDeclareDate = document.querySelector("#boardDeclareDate");
const reportedMemberNo = document.querySelector("#reportedMemberNo");
const tbody = document.querySelector(".tbody");






// 표 다시 조회하기
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

                        link.href = `/reviewBoard/2/${declare[key]}?reviewBoardCode=${reviewBoardCode}&reviewBoardNo=${declare[key]}&cp=${currentPage}`;

                        link.innerText = declare[key];

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











// 반려 버튼