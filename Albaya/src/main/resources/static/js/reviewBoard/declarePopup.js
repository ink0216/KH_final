
const declareFrm = document.querySelector("#declareFrm");

declareFrm.addEventListener("submit",e => {

    // const declarePopupContent = document.querySelector("[name='declarePopupContent]");


    if(declarePopupContent.value.trim().length == 0) {

        alert("신고 사유를 입력해주세요.");

        declarePopupContent.focus();

        e.preventDefault();
    }

})


const declareBtn = document.querySelector("#declareBtn");

declareBtn.addEventListener("click",() => {

    alert("신고버튼클릭");
})

