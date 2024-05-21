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
    options.push("height=300");
    options.push("left=300");
    options.push("top=100");

    window.open("/hire/hirePopup","_blank",options.toString());
})


function setType(type) {
    document.getElementById('selectedType').value = type;
    checkObj.selectedType=true;
}

/* -------------------------------------------------------------- */
/* 유효성 검사 */
const checkObj={
    "hireTitle" : false, //공고문 제목
    "selectedType" : false, //업직종 선택
    "hrieCount" : false, //모집인원
    "hireEnd"   : false, //모집 종료
    "shopTel"   : false, //연락처
    "shopEmail" : false  //이메일 
}

/* 공고문 제목 유효성 검사 */
const hireTitle = document.getElementById("hireTitle");
const titleMessage = document.getElementById("titleMessage");

hireTitle.addEventListener("input",e=>{
    const inputTitle = e.target.value;

    if(inputTitle.trim().length===0){
        titleMessage.innerHTML="공고 제목을 입력해주세요";
        checkObj.hireTitle=false;
        return;
    }

    if(inputTitle.trim().length<2){
        titleMessage.innerHTML="2자 이상으로 입력해주세요";
        checkObj.hireTitle=false;
        return;
    }

    if(inputTitle.trim().length>40){
        titleMessage.innerHTML="40자 이하로 입력해주세요";
        checkObj.hireTitle=false;
        return;
    }

    titleMessage.innerHTML="유효한 공고 제목입니다.";
    checkObj.hireTitle=true;
})

/* 업직종 선택 유효성 검사 */

/* 모집 인원 유효성 검사 */
const hireCount = document.getElementById("hireCount");//모집 인원
const countMessage = document.getElementById("countMessage");

hireCount.addEventListener("input",e=>{
    const inputCount = e.target.value;
    const regExp = /^\d+$/;

    if(inputCount.trim().length===0){
        countMessage.innerHTML="모집 인원 수를 입력해주세요";
        checkObj.hrieCount=false;
        return;
    }

    if(!regExp.test(inputCount)){
        countMessage.innerHTML="숫자만 입력해주세요";
        checkObj.hrieCount=false;
        return;
    }

    countMessage.innerHTML="모집 인원 작성 완료";
    checkObj.hrieCount=true;
})


/* 모집 종료일 유효성 검사 */
const hireEnd = document.getElementById("hireEnd")//모집 종료
const endMessage = document.getElementById("endMessage");

hireEnd.addEventListener("change",e=>{
    const now = new Date(); //현재 날짜
    const inputEnd = new Date(hireEnd.value); //입력된 날짜

    if(inputEnd.value==''){
        endMessage.innerHTML="모집 종료 날짜를 입력하세요";
        checkObj.hireEnd=false;
        return;
    }

    if(now>=inputEnd){
        endMessage.innerHTML="현재 날짜 이상의 날짜를 선택하세요";
        checkObj.hireEnd=false;
        return;
    }

    endMessage.innerHTML="모집 종료 날짜 입력 완료";
    checkObj.hireEnd=true;
})


/* 연락처 유효성 검사 */
const shopTel = document.getElementById("shopTel");
const telMessage = document.getElementById("telMessage");

shopTel.addEventListener("input",e=>{
    const inputTel = e.target.value;
    const regExp=/^\d{9,11}$/;

    if(inputTel.trim().length===0){
        telMessage.innerHTML="연락처를 입력해주세요";
        checkObj.shopTel=false;
        return;
    }

    if(!regExp.test(inputTel)){
        telMessage.innerHTML='올바른 번호를 입력하세요';
        checkObj.shopTel=false;
        return;
    }

    telMessage.innerHTML="연락처 입력 완성";
    checkObj.shopTel=true;
})


/* 이메일 유효성 검사 */
const shopEmail= document.getElementById("shopEmail");
const emailMessage = document.getElementById("emailMessage");

shopEmail.addEventListener("input",e=>{
    const inputEmail = e.target.value;
    const regExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    if(inputEmail.trim().length===0){
        emailMessage.innerHTML="이메일을 입력해주세요";
        checkObj.shopEmail=false;
        return;
    }

    if(!regExp.test(inputEmail)){
        emailMessage.innerHTML="올바른 이메일 형식으로 입력해주세요";
        checkObj.shopEmail=false;
        return;
    }

    emailMessage.innerHTML="이메일 입력 완료";
    checkObj.shopEmail=true;

})



/* ---------------------------------------------------------------------- */
/* (공고문 등록) */
const hireWrtieForm = document.getElementById("hireWrtieForm"); //form

hireWrtieForm.addEventListener("submit",e=>{

    for(let key in checkObj){
        if(!checkObj[key]){
            let str;

            switch(key){

                case "hireTitle":
                    str="공고문 제목이 유효하지 않습니다."; break;

                case "selectedType":
                    str="업직종을 선택하세요"; break;

                case "hrieCount":
                    str="모집인원이 유효하지 않습니다."; break;

                case "hireEnd":
                    str="모집 마감일이 유효하지 않습니다."; break;

                case "shopTel":
                    str="연락처가 유효하지 않습니다."; break;

                case "shopEmail":
                    str="이메일이 유효하지 않습니다."; break;
            }

            alert(str);
            document.getElementById(key).focus();
            e.preventDefault();
            return;
        }
    }

    /* 근무 기간 유효성 검사 */
    const hireTerm = document.querySelectorAll(".hireTerm");

    let termCount =0;
    hireTerm.forEach(item=>{

        if(item.checked){
            termCount++;
        }
    })

    if(termCount==0){
        alert("근무 기간을 체크하세요.");
        e.preventDefault();
    }


    /* 근무 요일 유효성 검사 */
    const hireDays = document.querySelectorAll(".hireDays"); 

    let dayCount=0;

     hireDays.forEach(day=>{
        if(day.checked){
            dayCount++
        }
     })

     if(dayCount==0){
        alert("근무 요일을 하나 이상 체크하세요.");
        e.preventDefault();
        return;
     }


     /* 근무 시간 */
     const workStart = document.getElementById("workStart"); //시작 시간
     const workEnd = document.getElementById("workEnd"); //시작 시간

     const startTime = Number(workStart.value.replace(":",""));
     const endTime = Number(workEnd.value.replace(":",""))


     if(workStart.value==''){
        alert("시작 시간을 입력하세요");
        e.preventDefault();
        return;
     }

     else if(workEnd.value==''){
        alert("종료 시간을 입력하세요");
        e.preventDefault();
        return;
     }

     if(startTime-endTime>0){
        alert('올바른 시간을 입력하세요');
        e.preventDefault();
     }




     /* 모집 성별 유효성 검사 */
         /* 근무 기간 유효성 검사 */
    const hireGender = document.querySelectorAll(".hireGender");

    let genderCount =0;
    hireGender.forEach(item=>{

        if(item.checked){
            genderCount++;
        }
    })

    if(genderCount==0){
        alert("모집 성별를 체크하세요.");
        e.preventDefault();
        return;
    }

     

})


