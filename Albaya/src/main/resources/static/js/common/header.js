document.addEventListener("DOMContentLoaded", () => {
    const logo = document.querySelector("#logo");
    logo.addEventListener("click", () => {
        location.href = "/";
    });

    const account = document.querySelector("#account");
    if (account) { 
        account.addEventListener("click", () => {
            location.href = "/myPage/myPageInfo";
        });
    }
    //이력서 작성 버튼

const resumeRedirect = document.querySelector("#resumeRedirect");
if(resumeRedirect != null){
    resumeRedirect.addEventListener("click", ()=>{
        location.href = "/resume/resumeList";
    })
}
});


const hireListBtn = document.getElementById('hireListBtn');
const loginShop = /*[[${session.shopMember}]]*/'기업 회원'
if(hireListBtn !=null){
    hireListBtn.addEventListener("click",()=>{
        // if(!loginShop){
        //     alert("기업 회원만 공고문 작성이 가능합니다. ");
        //     return;
        // }
        location.href='/hire/hireList';
    })
}



const goHireKind = document.getElementById('goHireKind');
if(goHireKind !=null){
    goHireKind.addEventListener("click",()=>{
        location.href='/hire/hireKind';
    });
}


const goHireLocation = document.getElementById('goHireLocation');
if(goHireLocation !=null){
    goHireLocation.addEventListener("click",()=>{
        location.href='/hire/hireLocation';
    });
}


const goReview2 = document.getElementById('goReview2');
if(goReview2 !=null){
    goReview2.addEventListener("click",()=>{
        location.href='/reviewBoard/2?cp=1';
    });
}




/* ***********************************************************/
/* 알림 설정 */

/* 알림 관련 전역 변수 */
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
        noticationSock.send(JSON.stringify(notification));
    }


    /* 웹소켓을 통해 서버에서 전달된 메세지가 있을 경우 */
    noticationSock.addEventListener("message",e=>{

        const notificationBtn = document.querySelector(".notification-btn");
        notificationBtn.classList.remove("fa-regular");
        notificationBtn.classList.add("fa-solid");

        selectNotificationFn();
    })

    /* 읽지 않은 알림이 있는지 확인 */
    notReadCheckFn = async () =>{
        const resp = await fetch("/notification/notReadCheck");
        const notReadCount = await resp.text();
        return notReadCount; //안읽은 알림 개수가 Promise 객체에 담겨 반환
    }

    /* 비동기로 알림을 조회하는 함수 */
    selectNotificationFn = ()=>{
        fetch("/notification")
        .then(resp=>resp.json())
        .then(selectList =>{

            const notiList = document.querySelector(".notification-list");
            notiList.innerHTML = '';

            const noticeAreaTitle  = document.createElement("div");
            noticeAreaTitle.className='notice-title';

            const h1 = document.createElement("h3");
            h1.innerText="공고 지원 알림";

            noticeAreaTitle.append(h1);

            notiList.append(noticeAreaTitle);


            for(let data of selectList){

                const notiItem = document.createElement("li");
                notiItem.className='notification-item';


                // 알림을 읽지 않은 경우
                if(data.notificationCheck =='N') notiItem.classList.add("not-read");

                const notiText = document.createElement("div");
                notiText.className = 'notification-text';

                notiText.addEventListener("click", e=>{

                    /* 만약 읽지 않은 알림인 경우 */
                    if(data.notificationCheck == 'N'){
                        fetch("/notification",{
                            method: "PUT",
                            headers:{"Content-Type": "application/json"},
                            body: data.notificationNo
                        })
                    }

                    let url = data.notificationUrl;
                    let newUrl = url.replace("/hire/hireApply/", "/hire/hireApplyDetail/${memberNo}/")

                    window.open(url, "_blank");
                });

                // 알림 내용 영역
                const contentContainer = document.createElement("div");
                contentContainer.className = 'notification-content-container';
                
                // 알림 보내진 시간
                const notiDate = document.createElement("p");
                notiDate.className = 'notification-date';
                notiDate.innerText = data.notificationDate;

                // 알림 내용
                const notiContent = document.createElement("p");
                notiContent.className = 'notification-content';
                notiContent.innerHTML = data.notificationContent; // 태그가 해석 될 수 있도록 innerHTML

                // 삭제 버튼
                const notiDelete = document.createElement("span");
                notiDelete.className = 'notidication-delete';
                notiDelete.innerHTML = '&times;';


                /* 삭제 버튼 클리 시 비동기로 해당 알림 지우기 */
                notiDelete.addEventListener("click", e=>{
                    fetch("/notification",{
                        method:"DELETE",
                        headers:{"Content-Type":"application/json"},
                        body: data.notificationNo
                    })
                    .then(resp=>resp.text())
                    .then(result=>{

                        // 클릭된 x버튼이 포함되는 알림 삭제
                        notiDelete.parentElement.remove();

                        //남은 알림 개수 확인 
                        notReadCheckFn().then(notReadCount=>{
                            const notificationBtn = document.querySelectorAll('.notification-btn');

                            /* 남은 알림이 있으면 활성화*/
                            if(notReadCount > 0){
                                notificationBtn.classList.remove("fa-regular");
                                notificationBtn.classList.add("fa-solid");
                            }else{ // 없으면 비활성화
                                notificationBtn.classList.add("fa-regular");
                                notificationBtn.classList.remove("fa-solid");
                            }
                        })
                    })
                })
                notiList.append(notiItem);
                notiItem.append(notiText, notiDelete);
                notiText.append(contentContainer);
                contentContainer.append(notiDate, notiContent);
            }
        })
    }
}

document.addEventListener("DOMContentLoaded",()=>{

    //알림 버튼
    const notificationBtn = document.querySelector(".notification-btn");

    notReadCheckFn().then(notReadCount => {
        if(notReadCount > 0){
            notificationBtn.classList.remove("fa-regular");
            notificationBtn.classList.add("fa-solid");
        }
    })

    /* 알립 버튼 클릭 */
    if(notificationBtn !=null){
        notificationBtn.addEventListener("click",e=>{
            const notiList=document.querySelector('.notification-list');
    
              // 보이는 상태일 때
            if(notiList.classList.contains("notification-show")){
                notiList.classList.remove("notification-show");
                notiList.classList.add("notification-showx")
                return;
            }
    
            /* 로그인 상태인 경우 알림 목록을 바로 비동기로 조회 */
            selectNotificationFn();
            notiList.classList.remove("notification-showx");
            notiList.classList.add("notification-show");
        })
    }
    
})
//------------------------------------------------------------------------------------
const query = document.querySelector("#searchField");

query.addEventListener("keydown", e => {
    if (e.key === "Enter") {
        fetch(`/hire/selectHireList?cp=1&query=${query.value}`)
        .then(resp => resp.json())
        .then(map => {  
            if (!map || !map.hireList || map.hireList.length === 0) {
                alert("검색된 결과가 없습니다.");
                return;
            }
            console.log(map);
            const { hireList, pagination } = map;

            // 여기서 테이블과 페이징을 업데이트 합니다.
            tbody.innerHTML = '';
            numberButtonWrapper.innerHTML = '';

            setPageOf(hireList);
            getPagination(pagination);

            query.value = "";
        
        });
    }
});









