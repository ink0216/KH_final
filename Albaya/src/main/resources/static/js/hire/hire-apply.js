const applyBtn = document.getElementById("apply-btn"); /* 지원하기 버튼 */
const applyTitle = document.getElementById('applyTitle'); /* 지원제목 */
const applyContent = document.getElementById('applyContent'); /* 전달 메세지 */
const applyCheck = document.getElementById('apply-check');
const contentMessage = document.getElementById('content-message');


applyContent.addEventListener("input",e=>{
    if(e.target.value.trim().length == applyContent.maxLength){
        contentMessage.classList.remove("green");
        contentMessage.classList.add("red");
        contentMessage.innerText= e.target.value.trim().length+"/1000";
        return;
    }
    
    contentMessage.classList.remove("red");
    contentMessage.classList.add("green");
    contentMessage.innerText=e.target.value.trim().length + "/1000";
})



applyBtn.addEventListener("click",e=>{

    if(applyTitle.value.trim().length==0){
        alert("지원 제목을 작성해주세요");
        e.preventDefault();
        return;
    }

    if(applyContent.value.trim().length == 0) {
        alert("전달 메세지를 작성해주세요");
        e.preventDefault();
        return;
    }

    if(!applyCheck.checked){
        alert("내용 전달 메세지에 동의해주세요");
        e.preventDefault();
        return;
    }

    sendNotificationFn("hireApply", location.pathname, hireNo);

})
