/* 급여 확인 버튼 */
const payConfirmBtn = document.getElementById('payConfirmBtn');


payConfirmBtn.addEventListener("click",()=>{

    /* 급여 종류 얻어오기 */
    const payNoArea = document.querySelector('#payNo');
    const value = payNoArea.value; //option의 value값 얻어오기

    /* 급여 입력 */
    const payInput = document.getElementById('payInput') 

    /* 선택된 요일 얻어오기 */
    const workDays = document.querySelectorAll('input[name="workDay"]:checked'); 



    //-----------------------------------------------------------------
    /* 일하는 요일 수 얻어오기 */
    let dayCount=0;//일하는 요일 수

    workDays.forEach(day=>{
        dayCount++;

        if(day.value=='aa'){ //'협의가능' 체크 시 count 증가 x
            dayCount-=1;
        }

    }) //workDat.forEach

    console.log(`근무 요일 수 : ${dayCount}`); 
    //----------------------------------------------------------------------




    //----------------------------------------------------------------------
    /* 시간 얻어오기 */
    const workStart = document.getElementById('workStart'); // 알바 시작 시간
    const workEnd = document.getElementById('workEnd'); // 알바 종료 시간

    const diffMSec =  new Date(0+' '+workEnd.value) - new Date(0+' '+workStart.value)
    const workTime = diffMSec / (60 * 60 * 1000);
    console.log(`근무 시간:  ${workTime}`);
    console.log(value);

    const weekWorkTime = dayCount * workTime;
    let weekPay = 0;
    let monthPay =0;
    let yearPay =0;

    console.log("weekWorkTime : ", weekWorkTime);

    //----------------------------------------------------------------------



    //----------------------------------------------------------------------
    /* 급여의 종류에 따라 급여 계산 */
    switch(value){
        
        case '1' : // 시급
            if(payInput.value<9860)
                {alert("최저 시급 이상 입력해 주세요");
                payInput.value='';}
            break;


        case '2': //주급

            if(weekWorkTime>48){
                weekPay = (9860 * workTime * dayCount)*1.2;
            }
            else{
                weekPay = 9860 * workTime * dayCount;
            }

            console.log("weekPay : ", weekPay);
            if(payInput.value<weekPay){
                alert("최저 주급 이상 입력하세요");
                payInput.value='';
            }
            break;

        case '3': //월급
            if(weekWorkTime>48){
                weekPay =  (9860 * workTime * dayCount)*1.2;
            }
            else{
                weekPay = 9860 * workTime * dayCount;
            }

            monthPay = weekPay * 4;
            console.log("monthPay : ", monthPay);

            if(payInput.value<monthPay){
                alert("최저 월급 이상 입력하세요");
                payInput.value='';
            }
            break;

        case '4': //연봉
            if(weekWorkTime>48){
                weekPay =  (9860 * workTime * dayCount)*1.2;
            }
            else{
                weekPay = 9860 * workTime * dayCount;
            }

            monthPay = weekPay * 4;
            yearPay = monthPay*365; 
            console.log("monthPay : ", monthPay);

            if(payInput.value<yearPay){
                alert("최저 연봉 이상 입력하세요");
                payInput.value='';
            }
            break;

        case '5': //일급
        if(payInput.value<(9860*workTime)){
            alert("최저 일급 이상 입력하세요");
            payInput.value='';
        }

        
    }
    //----------------------------------------------------------------------

    


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


/* -------------------------------------------------------------- */
/* 업직종 선택 popup창 */
const typeBtn = document.querySelector("#typeBtn"); //업직종 선택 버튼

typeBtn.addEventListener("click",()=>{

    const options=[];
    options.push("width=500");
    options.push("height=400");
    options.push("left=300");
    options.push("top=100");

    window.open("/hire/hirePopup","_blank",options.toString());
})


function setType(type) {
    document.getElementById('selectedType').value = type;
}

/* -------------------------------------------------------------- */
