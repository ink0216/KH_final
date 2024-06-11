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
    location.href = "/declare/1";
})


