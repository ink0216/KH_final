/* 급여 입력하기*/


/* 급여 확인 버튼 */
const payConfirmBtn = document.getElementById('payConfirmBtn');
/* 선택한 요일 얻어오기 */
const workDays = document.querySelectorAll('input[name="workDay"'); //근무 요일 checkbox요소 얻어오기

/* 급여 종류 얻어오기 */
const payNoArea = document.querySelector('#payNo');
const payNoOption = payNoArea.options[payNoArea.selectedIndex]; //option 얻어오기
const value = payNoOption.value; //option의 value값 얻어오기


const payInput = document.getElementById('payInput') // 급여 입력


payConfirmBtn.addEventListener("click",()=>{

    /* 선택된 요일 얻어오기 */
    const workDays = document.querySelectorAll('input[name="workDay"]:checked'); 

    let dayCount=0;

    workDays.forEach(day=>{
        dayCount++;

        if(day.value=='aa'){ //'협의가능' 체크 시 count 증가 x
            dayCount-=1;
        }

    }) //workDat.forEach

    console.log(dayCount); //일하는 요일 수

    /* 시간 얻어오기 */
    const workStart = document.getElementById('workStart'); // 알바 시작 시간
    const workEnd = document.getElementById('workEnd'); // 알바 종료 시간

    


})//payConfirmBtn.addEventListener






/* **************************************************************************************** */
/* 지도,주소 */
var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
        level: 5 // 지도의 확대 레벨
};

//지도를 미리 생성
var map = new daum.maps.Map(mapContainer, mapOption);
//주소-좌표 변환 객체를 생성
var geocoder = new kakao.maps.services.Geocoder();
//마커를 미리 생성
var marker = new daum.maps.Marker({
position: new daum.maps.LatLng(37.537187, 127.005476),
map: map
});


function sample5_execDaumPostcode() {
new daum.Postcode({
    oncomplete: function(data) {
        var addr = data.address; // 최종 주소 변수

        // 주소 정보를 해당 필드에 넣는다.
        document.getElementById("address").value = addr;
        // 주소로 상세 정보를 검색
        geocoder.addressSearch(data.address, function(results, status) {
            // 정상적으로 검색이 완료됐으면
            if (status === daum.maps.services.Status.OK) {

                var result = results[0]; //첫번째 결과의 값을 활용

                // 해당 주소에 대한 좌표를 받아서
                var coords = new daum.maps.LatLng(result.y, result.x);
                // 지도를 보여준다.
                mapContainer.style.display = "block";
                map.relayout();
                // 지도 중심을 변경한다.
                map.setCenter(coords);
                // 마커를 결과값으로 받은 위치로 옮긴다.
                marker.setPosition(coords)
            }
        });
    }
}).open();
}


