const applyBtn = document.getElementById("apply-btn"); /* 지원하기 버튼 */
const applyTitle = document.getElementById('applyTitle'); /* 지원제목 */
const applyContent = document.getElementById('applyContent'); /* 전달 메세지 */
const applyCheck = document.getElementById('apply-check');
const contentMessage = document.getElementById('content-message');

const notificationLoginCheck = /*[[${session.loginShop or session.loginMember} ? true : false]]*/ '로그인 체크';

let noticationSock;     //알림 웹소켓 객체
let sendNotificationFn;  //웹소켓을 이용해 알림을 보내는 함수

let selectNotificationFn;  //비동기로 알림을 조회하는 함수
let notReadCheckFn;        //비동기로 읽지 않은 알림 개수 채크하는 함수

// const notificationBtn= document.querySelector('.notification-btn');


if(notificationLoginCheck){//로그인 된 상태인 경우
    noticationSock = new SockJS("/notification/send");

    /* 웹소켓을 이용해 알림을 전달하는 함수 */
    sendNotificationFn = (type,url,pkNo) =>{
        const notification = {
            "notificationType" : type,
            "notificationUrl" : url,
            "pkNo" : pkNo 
        }
        console.log(notification);
        noticationSock.send(JSON.stringify(notification));
    }
}

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

    sendNotificationFn("hireApply", `/hire/hireApplyDetail/${memberNo}/${hireNo}`, hireNo);

})
