var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
mapOption = {
    center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
    level: 3 // 지도의 확대 레벨
};

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption);

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

// 주소로 좌표를 검색합니다
// geocoder.addressSearch('서울 중구 서소문로 136', function (result, status) {
let address = document.getElementById("address");

geocoder.addressSearch(address.innerHTML, function (result, status) {

// 정상적으로 검색이 완료됐으면 
if (status === kakao.maps.services.Status.OK) {

    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

    // 결과값으로 받은 위치를 마커로 표시합니다
    var marker = new kakao.maps.Marker({
        map: map,
        position: coords
    });

    // 인포윈도우로 장소에 대한 설명을 표시합니다
    var infowindow = new kakao.maps.InfoWindow({
        content: '<div style="width:150px;text-align:center;padding:6px 0;">HERE</div>' 
    });
    infowindow.open(map, marker);

    // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
    map.setCenter(coords);
}
});    



/* 닫기버튼 */
const closeBtn = document.querySelector("#closeBtn");
closeBtn.addEventListener("click",()=>{
    let url = document.referrer;
    if(url==''){
        url='/';
    }
    location.href=url;
})




/* ******************************************************************* */
/* 지원하기 */
const applyBtn = document.getElementById('applyBtn');
if(applyBtn != null){
    const hireNo = applyBtn.getAttribute('hire-no');

    applyBtn.addEventListener("click",()=>{
        fetch("/hire/hireApplyCheck/"+hireNo,{
            method: "GET"
        })
        .then(resp => resp.json())
        .then(result => {
            if(result == 1){
                alert("이미 지원한 공고입니다.");
                console.log(result);
                return;
            }
            else{
                const options=[];
                options.push("width=600");
                options.push("height=650");
                options.push("left=300");
                options.push("top=100");
            
                window.open(`/hire/hireApply/${hireNo}`,"_blank",options.toString());
            }
        })
    })
}
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

/* ******************************************************************* */
/* 공고 수정 버튼 */
/* 
const url = location.pathname.replace("board","editBoard") + "/delete"; // /editBoard/1/2000/delete
    const queryString = location.search; // ?cp=1
    location.href = url + queryString;
    });

*/
const updateBtn = document.getElementById('updateBtn');
if(updateBtn != null){

    updateBtn.addEventListener("click", () => {

        location.href = location.pathname.replace("hire","hire/update");
    });
}
