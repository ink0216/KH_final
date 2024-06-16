// /* 급여 확인 버튼 */
// const payConfirmBtn = document.getElementById('payConfirmBtn');
// const payInput = document.getElementById('payInput') 

// payInput.addEventListener("input",e=>{

//     const pay= e.target.value;

//     /* 급여 종류 얻어오기 */
//     const payNoArea = document.querySelector('#payNo');
//     const value = payNoArea.value; //option의 value값 얻어오기

//     /* 급여 입력 */
//     // const payInput = document.getElementById('payInput');
//     const payMessage = document.getElementById('payMessage');

//     /* 선택된 요일 얻어오기 */
//     const workDays = document.querySelectorAll('input[name="workDay"]:checked'); 



//     //-----------------------------------------------------------------
//     /* 일하는 요일 수 얻어오기 */
//     let dayCount=0;//일하는 요일 수

//     workDays.forEach(day=>{
//         dayCount++;

//         if(day.value=='aa'){ //'협의가능' 체크 시 count 증가 x
//             dayCount-=1;
//         }

//     }) //workDat.forEach

//     console.log(`근무 요일 수 : ${dayCount}`); 
//     //----------------------------------------------------------------------




//     //----------------------------------------------------------------------
//     /* 시간 얻어오기 */
//     const workStart = document.getElementById('workStart'); // 알바 시작 시간
//     const workEnd = document.getElementById('workEnd'); // 알바 종료 시간

//     const diffMSec =  new Date(0+' '+workEnd.value) - new Date(0+' '+workStart.value)
//     const workTime = diffMSec / (60 * 60 * 1000);
//     console.log(`근무 시간:  ${workTime}`);
//     console.log(value);

//     const weekWorkTime = dayCount * workTime;
//     let weekPay = 0;
//     let monthPay =0;
//     let yearPay =0;

//     console.log("weekWorkTime : ", weekWorkTime);

//     //----------------------------------------------------------------------



//     //----------------------------------------------------------------------
//     /* 급여의 종류에 따라 급여 계산 */
//     switch(value){
        
//         case '1' : // 시급
//             if(pay<9860)
//                 {
//                 payMessage.innerHTML="최저 시급 이상 입력하세요";
//                 payMessage.classList.remove("blue");
//                 payMessage.classList.add("red");
//                 checkObj.payInput=false;
//                 return;
//             }
//             checkObj.payInput=true;
//             payMessage.innerHTML="급여 입력 완료";
//             payMessage.classList.remove("red");
//             payMessage.classList.add("blue");
//             break;


//         case '2': //주급

//             if(weekWorkTime>48){
//                 weekPay = (9860 * workTime * dayCount)*1.2;
//             }
//             else{
//                 weekPay = 9860 * workTime * dayCount;
//             }
//             console.log(Number(pay)<weekPay);
//             console.log("weekPay : ", weekPay);
//             if(Number(pay)<weekPay){
//                 payMessage.innerHTML="최저 주급 이상 입력하세요";
//                 payMessage.classList.remove("blue");
//                 payMessage.classList.add("red");
//                 checkObj.payInput=false;
//                 return;
//             }
//                 checkObj.payInput=true;
//                 payMessage.innerHTML="급여 입력 완료";
//                 payMessage.classList.remove("red");
//                 payMessage.classList.add("blue");
//             break;

//         case '3': //월급
//             if(weekWorkTime>48){
//                 weekPay =  (9860 * workTime * dayCount)*1.2;
//             }
//             else{
//                 weekPay = 9860 * workTime * dayCount;
//             }

//             monthPay = weekPay * 4;
//             console.log("monthPay : ", monthPay);

//             if(pay<monthPay){
//                 // alert("최저 월급 이상 입력하세요");
//                 payMessage.innerHTML="최저 월급 이상 입력하세요";
//                 payMessage.classList.remove("blue");
//                 payMessage.classList.add("red");
//                 checkObj.payInput=false;
//                 return;
//             }
//             checkObj.payInput=true;
//             payMessage.innerHTML="급여 입력 완료";
//             payMessage.classList.remove("red");
//             payMessage.classList.add("blue");
//             break;

//         case '4': //연봉
//             if(weekWorkTime>48){
//                 weekPay =  (9860 * workTime * dayCount)*1.2;
//             }
//             else{
//                 weekPay = 9860 * workTime * dayCount;
//             }

//             monthPay = weekPay * 4;
//             yearPay = monthPay*365; 
//             console.log("monthPay : ", monthPay);

//             if(pay<yearPay){
//                 // alert("최저 연봉 이상 입력하세요");
//                 payMessage.innerHTML="최저 연봉 이상 입력하세요";
//                 payMessage.classList.remove("blue");
//                 payMessage.classList.add("red");
//                 checkObj.payInput=false;
//                 return;
//             }
//             checkObj.payInput=true;
//             payMessage.innerHTML="급여 입력 완료";
//             payMessage.classList.remove("red");
//             payMessage.classList.add("blue");
//             break;

//         case '5': //일급
//         if(pay<(9860*workTime)){
//             // alert("최저 일급 이상 입력하세요");
//             payMessage.innerHTML="최저 일급 이상 입력하세요";
//             payMessage.classList.remove("blue");
//             payMessage.classList.add("red");
//             checkObj.payInput=false;
//             return;
//         }
        
//         checkObj.payInput=true;
//         payMessage.innerHTML="급여 입력 완료";
//         payMessage.classList.remove("red");
//         payMessage.classList.add("blue");
//         break;

        
//     }
//     //----------------------------------------------------------------------

    


// })//payConfirmBtn.addEventListener






// /* **************************************************************************************** */
// /* 지도,주소 */
// var mapContainer = document.getElementById('map'), // 지도를 표시할 div
//     mapOption = {
//         center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
//         level: 5 // 지도의 확대 레벨
// };

// //지도를 미리 생성
// var map = new daum.maps.Map(mapContainer, mapOption);
// //주소-좌표 변환 객체를 생성
// var geocoder = new kakao.maps.services.Geocoder();
// //마커를 미리 생성
// var marker = new daum.maps.Marker({
// position: new daum.maps.LatLng(37.537187, 127.005476),
// map: map
// });



// const dd = document.querySelector('.address-dd'); //dd태그

// const div = document.createElement('div');
// div.className='hidden';

// const sido = document.createElement('input'); // 시도
// const sigunsu = document.createElement('input'); // 시군구
// const dong = document.createElement('input'); //동읍면



// function sample5_execDaumPostcode() {
//     new daum.Postcode({
//         oncomplete: function(data) {

//             var addr;

//             if(data.jibunAddress!=''){ //지번주소가 있는 경우
//                 addr = data.jibunAddress; // 최종 주소 변수(지번주소)
//             } 
//             if(data.autoJibunAddress!=''){  //지번 주소가 없는 경우
//                 addr = data.autoJibunAddress; 
//             }


//             console.log(data);
//             console.log("data.autoJibunAddress : ",data.autoJibunAddress);
//             console.log("addr : ",addr);


//             //-----------------------------------------------------------------------
//             //input 요소 추가        

//             div.innerHTML='';

//             sido.setAttribute("name","dosiName"); 
//             sigunsu.setAttribute("name","sigunguName");
//             dong.setAttribute("name","dongName");

//             sido.value=data.sido;
            
//             if(data.sigungu.length>4){
//                 sigunsu.value=data.sigungu.substr(0,3)+data.sigungu.substr(4); //띄어쓰기 됐던 포항시 북구 -> 포항시북구 붙이기
//             }else{
//                 sigunsu.value=data.sigungu;
//             }
            
//             dong.value=data.bname;

//             div.append(sido,sigunsu,dong);
//             dd.append(div);

//             //---------------------------------------------------------------------------

//             // 주소 정보를 해당 필드에 넣는다.
//             document.getElementById("address").value = addr;
    
//             // 주소로 상세 정보를 검색
//             geocoder.addressSearch(data.address, function(results, status) {
//                 // 정상적으로 검색이 완료됐으면
//                 if (status === daum.maps.services.Status.OK) {

//                     var result = results[0]; //첫번째 결과의 값을 활용

//                     // 해당 주소에 대한 좌표를 받아서
//                     var coords = new daum.maps.LatLng(result.y, result.x);
//                     // 지도를 보여준다.
//                     mapContainer.style.display = "block";
//                     map.relayout();
//                     // 지도 중심을 변경한다.
//                     map.setCenter(coords);
//                     // 마커를 결과값으로 받은 위치로 옮긴다.
//                     marker.setPosition(coords)
//                 }
//             });
//         }
//     }).open();
// }


// /* -------------------------------------------------------------- */
// /* 업직종 선택 popup창 */
// const typeBtn = document.querySelector("#typeBtn"); //업직종 선택 버튼

// typeBtn.addEventListener("click",()=>{

//     const options=[];
//     options.push("width=500");
//     options.push("height=300");
//     options.push("left=300");
//     options.push("top=100");

//     window.open("/hire/hirePopup","_blank",options.toString());
// })


// function setType(type) {
//     document.getElementById('selectedType').value = type;
//     checkObj.selectedType=true;
// }


// /* -------------------------------------------------------------- */
// /* 유효성 검사 */
// const checkObj={
//     "hireTitle" : false, //공고문 제목
//     "selectedType" : false, //업직종 선택
//     "hireCount" : false, //모집인원
//     "hireEnd"   : false, //모집 종료
//     "shopTel"   : false, //연락처
//     "shopEmail" : false,  //이메일 
//     "payInput"  : false, //급여
//     "hireContent" : false // 내용
//     }

// /* 공고문 제목 유효성 검사 */
// const hireTitle = document.getElementById("hireTitle");
// const titleMessage = document.getElementById("titleMessage");

// hireTitle.addEventListener("input",e=>{
//     const inputTitle = e.target.value;

//     if(inputTitle.trim().length===0){
//         titleMessage.innerHTML="공고 제목을 입력해주세요";
//         titleMessage.classList.remove("blue");
//         titleMessage.classList.add("red");
//         checkObj.hireTitle=false;
//         return;
//     }

//     if(inputTitle.trim().length<2){
//         titleMessage.innerHTML="2자 이상으로 입력해주세요";
//         titleMessage.classList.remove("blue");
//         titleMessage.classList.add("red");
//         checkObj.hireTitle=false;
//         return;
//     }

//     if(inputTitle.trim().length>40){
//         titleMessage.innerHTML="40자 이하로 입력해주세요";
//         titleMessage.classList.remove("blue");
//         titleMessage.classList.add("red");
//         checkObj.hireTitle=false;
//         return;
//     }

//     titleMessage.innerHTML="제목 입력 완료";
//     titleMessage.classList.remove("red");
//     titleMessage.classList.add("blue");
//     checkObj.hireTitle=true;
// })



// /* 업직종 선택 유효성 검사 */

// /* 모집 인원 유효성 검사 */
// const hireCount = document.getElementById("hireCount");//모집 인원
// const countMessage = document.getElementById("countMessage");

// hireCount.addEventListener("input",e=>{
//     const inputCount = e.target.value;
//     const regExp = /^\d+$/;

//     if(inputCount.trim().length===0){
//         countMessage.innerHTML="모집 인원 수를 입력해주세요";
//         countMessage.classList.remove("blue");
//         countMessage.classList.add("red");
//         checkObj.hireCount=false;
//         return;
//     }

//     if(!regExp.test(inputCount)){
//         countMessage.innerHTML="숫자만 입력해주세요";
//         countMessage.classList.remove("blue");
//         countMessage.classList.add("red");
//         checkObj.hireCount=false;
//         return;
//     }

//     countMessage.innerHTML="모집 인원 작성 완료";
//     countMessage.classList.remove("red");
//     countMessage.classList.add("blue");
//     checkObj.hireCount=true;
// })


// /* 모집 종료일 유효성 검사 */
// const hireEnd = document.getElementById("hireEnd")//모집 종료
// const endMessage = document.getElementById("endMessage");

// hireEnd.addEventListener("change",e=>{
//     const now = new Date(); //현재 날짜
//     const inputEnd = new Date(hireEnd.value); //입력된 날짜

//     if(inputEnd.value==''){
//         endMessage.innerHTML="모집 종료 날짜를 입력하세요";
//         endMessage.classList.remove("blue");
//         endMessage.classList.add("red");
//         checkObj.hireEnd=false;
//         return;
//     }

//     if(now>=inputEnd){
//         endMessage.innerHTML="현재 날짜 이상의 날짜를 선택하세요";
//         endMessage.classList.remove("blue");
//         endMessage.classList.add("red");
//         checkObj.hireEnd=false;
//         return;
//     }

//     endMessage.innerHTML="모집 종료 날짜 입력 완료";
//     endMessage.classList.remove("red");
//     endMessage.classList.add("blue");
//     checkObj.hireEnd=true;
// })


// /* 연락처 유효성 검사 */
// const shopTel = document.getElementById("shopTel");
// const telMessage = document.getElementById("telMessage");

// shopTel.addEventListener("input",e=>{
//     const inputTel = e.target.value;
//     const regExp=/^\d{9,11}$/;

//     if(inputTel.trim().length===0){
//         telMessage.innerHTML="연락처를 입력해주세요";
//         telMessage.classList.remove("blue");
//         telMessage.classList.add("red");
//         checkObj.shopTel=false;
//         return;
//     }

//     if(!regExp.test(inputTel)){
//         telMessage.innerHTML='올바른 번호를 입력하세요';
//         telMessage.classList.remove("blue");
//         telMessage.classList.add("red");
//         checkObj.shopTel=false;
//         return;
//     }

//     telMessage.innerHTML="연락처 입력 완료";
//     telMessage.classList.remove("red");
//     telMessage.classList.add("blue");
//     checkObj.shopTel=true;
// })


// /* 이메일 유효성 검사 */
// const shopEmail= document.getElementById("shopEmail");
// const emailMessage = document.getElementById("emailMessage");

// shopEmail.addEventListener("input",e=>{
//     const inputEmail = e.target.value;
//     const regExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

//     if(inputEmail.trim().length===0){
//         emailMessage.innerHTML="이메일을 입력해주세요";
//         checkObj.shopEmail=false;
//         return;
//     }

//     if(!regExp.test(inputEmail)){
//         emailMessage.innerHTML="올바른 이메일 형식으로 입력해주세요";
//         emailMessage.classList.remove("blue");
//         emailMessage.classList.add("red");
//         checkObj.shopEmail=false;
//         return;
//     }

//     emailMessage.innerHTML="이메일 입력 완료";
//     emailMessage.classList.remove("red");
//     emailMessage.classList.add("blue");
//     checkObj.shopEmail=true;

// })



// /* ---------------------------------------------------------------------- */
// /* (공고문 등록) */
// const hireWrtieForm = document.getElementById("hireWrtieForm"); //form

// hireWrtieForm.addEventListener("submit",e=>{
//     /* 공고문 내용 유효성 검사 */
//     const hireContent = document.querySelector("#hireContent");

//     if(hireContent.value.trim().length>0){
//         checkObj.hireContent=true;
//     }
//     for(let key in checkObj){
//         if(!checkObj[key]){
//             let str;

//             switch(key){

//                 case "hireTitle":
//                     str="공고문 제목이 유효하지 않습니다."; break;

//                 case "selectedType":
//                     str="업직종을 선택하세요"; break;

//                 case "hireCount":
//                     str="모집인원이 유효하지 않습니다."; break;

//                 case "hireEnd":
//                     str="모집 마감일이 유효하지 않습니다."; break;

//                 case "shopTel":
//                     str="연락처가 유효하지 않습니다."; break;

//                 case "shopEmail":
//                     str="이메일이 유효하지 않습니다."; break;
                    
//                 case "payInput":
//                     str="급여 검사를 해주세요"; break;

//                 case "hireContent":
//                     str="공고문 내용을 입력해 주세요."; break;
//             }

//             alert(str);
//             document.getElementById(key).focus();
//             e.preventDefault();
//             return;
//         }
//     }

//     /* 근무 기간 유효성 검사 */
//     const hireTerm = document.querySelectorAll(".hireTerm");

//     let termCount =0;
//     hireTerm.forEach(item=>{

//         if(item.checked){
//             termCount++;
//         }
//     })

//     if(termCount==0){
//         alert("근무 기간을 체크하세요.");
//         e.preventDefault();
//     }


//     /* 근무 요일 유효성 검사 */
//     const hireDays = document.querySelectorAll(".hireDays"); 

//     let dayCount=0;

//      hireDays.forEach(day=>{
//         if(day.checked){
//             dayCount++
//         }
//      })

//      if(dayCount==0){
//         alert("근무 요일을 하나 이상 체크하세요.");
//         e.preventDefault();
//         return;
//      }


//      /* 근무 시간 유효성 검사*/
//      const workStart = document.getElementById("workStart"); //시작 시간
//      const workEnd = document.getElementById("workEnd"); //시작 시간

//      const startTime = Number(workStart.value.replace(":",""));
//      const endTime = Number(workEnd.value.replace(":",""))


//      if(workStart.value==''){
//         alert("시작 시간을 입력하세요");
//         e.preventDefault();
//         return;
//      }

//     if(workEnd.value==''){
//         alert("종료 시간을 입력하세요");
//         e.preventDefault();
//         return;
//      }

//     if(startTime-endTime>0){
//         alert('올바른 시간을 입력하세요');
//         e.preventDefault();
//         return;
//      }




//  /* 모집 성별 유효성 검사 */
//     const hireGender = document.querySelectorAll(".hireGender");

//     let genderCount =0;
//     hireGender.forEach(item=>{

//         if(item.checked){
//             genderCount++;
//         }
//     })

//     if(genderCount==0){
//         alert("모집 성별를 체크하세요.");
//         e.preventDefault();
//         return;
//     }



//     /* 급여 유효성 검사 */
//     const payInput = document.getElementById("payInput");

//     if(payInput.value.trim().length===0){
//         alert("급여를 입력해주세요.");
//         e.preventDefault();
//         return;
//     }


//     /* 근무지 주소 유효성 검사 */
//     const address = document.getElementById("address");
//     const addressDetail = document.getElementById("addressDetail");
//     const shopName = document.getElementById("shopName");

//     if(address.value.trim().length===0){
//         alert("주소를 입력해주세요.");
//         e.preventDefault();
//         return;
//     }
//     if(addressDetail.value.trim().length===0){
//         alert("세부 주소를 입력해주세요.");
//         e.preventDefault();
//         return;
//     }
//     if(shopName.value.trim().length===0){
//         alert("회사명를 입력해주세요.");
//         e.preventDefault();
//         return;
//     }


//     /* 담당자명 유효성 검사 */

//     const shopOwner = document.getElementById("shopOwner");

//     if(shopOwner.value.trim().length===0){
//         alert("담당자명을 입력해주세요.");
//         e.preventDefault();
//         return;
//     }

//     /* 약관 동의 유효성 검사 */
//     const agree = document.getElementById("agree");

//     if(!agree.checked){
//         alert("약관에 동의해주세요");
//         e.preventDefault();
//         return;
//     }
//     const submitBtn = document.querySelector("#submitBtn");
//     submitBtn.addEventListener("click", ()=>{
//         const hireStatus = document.createElement("input");
//         hireStatus.classList.add("hidden");
//         hireStatus.setAttribute("name", "hireStatus");
//         //저장 : 0 // 임시저장 : 1
//         hireStatus.value=0;
//         const hireWrtieForm = document.querySelector("#hireWrtieForm");
//         hireWrtieForm.append(hireStatus);
//     });

// })
// const semiSaveBtn = document.querySelector("#semiSaveBtn");
// semiSaveBtn.addEventListener("click", ()=>{
//     const hireStatus = document.createElement("input");
//     hireStatus.classList.add("hidden");
//     hireStatus.setAttribute("name", "hireStatus");
//     //저장 : 0 // 임시저장 : 1
//     hireStatus.value=1;
//     const hireWrtieForm = document.querySelector("#hireWrtieForm");
//     hireWrtieForm.append(hireStatus);
// });
//주석 부분이 인서씨가 쓰신거

let isSemiSave = false; // 임시저장 플래그

// 임시저장 버튼 클릭 이벤트
const semiSaveBtn = document.querySelector("#semiSaveBtn");
semiSaveBtn.addEventListener("click", (e) => {
    isSemiSave = true;
    const hireStatus = document.createElement("input");
    hireStatus.classList.add("hidden");
    hireStatus.setAttribute("name", "hireStatus");
    //저장 : 0 // 임시저장 : 1
    hireStatus.value = 1;
    const hireWrtieForm = document.querySelector("#hireWrtieForm");
    hireWrtieForm.append(hireStatus);
    hireWrtieForm.submit(); // 임시저장 버튼 클릭 시 폼을 바로 제출
});

// 공고문 등록(form) submit 이벤트
const hireWrtieForm = document.getElementById("hireWrtieForm");
hireWrtieForm.addEventListener("submit", (e) => {
    if (isSemiSave) {
        // 임시저장일 때는 유효성 검사를 건너뜀
        return;
    }

    /* 공고문 내용 유효성 검사 */
    const hireContent = document.querySelector("#hireContent");

    if (hireContent.value.trim().length > 0) {
        checkObj.hireContent = true;
    }
    for (let key in checkObj) {
        if (!checkObj[key]) {
            let str;

            switch (key) {
                case "hireTitle":
                    str = "공고문 제목이 유효하지 않습니다.";
                    break;

                case "selectedType":
                    str = "업직종을 선택하세요";
                    break;

                case "hireCount":
                    str = "모집인원이 유효하지 않습니다.";
                    break;

                case "hireEnd":
                    str = "모집 마감일이 유효하지 않습니다.";
                    break;

                case "shopTel":
                    str = "연락처가 유효하지 않습니다.";
                    break;

                case "shopEmail":
                    str = "이메일이 유효하지 않습니다.";
                    break;

                case "payInput":
                    str = "급여 검사를 해주세요";
                    break;

                case "hireContent":
                    str = "공고문 내용을 입력해 주세요.";
                    break;
            }

            alert(str);
            document.getElementById(key).focus();
            e.preventDefault();
            return;
        }
    }

    /* 근무 기간 유효성 검사 */
    const hireTerm = document.querySelectorAll(".hireTerm");

    let termCount = 0;
    hireTerm.forEach(item => {
        if (item.checked) {
            termCount++;
        }
    });

    if (termCount == 0) {
        alert("근무 기간을 체크하세요.");
        e.preventDefault();
        return;
    }

    /* 근무 요일 유효성 검사 */
    const hireDays = document.querySelectorAll(".hireDays");

    let dayCount = 0;

    hireDays.forEach(day => {
        if (day.checked) {
            dayCount++;
        }
    });

    if (dayCount == 0) {
        alert("근무 요일을 하나 이상 체크하세요.");
        e.preventDefault();
        return;
    }

    /* 근무 시간 유효성 검사 */
    const workStart = document.getElementById("workStart"); // 시작 시간
    const workEnd = document.getElementById("workEnd"); // 시작 시간

    const startTime = Number(workStart.value.replace(":", ""));
    const endTime = Number(workEnd.value.replace(":", ""));

    if (workStart.value == '') {
        alert("시작 시간을 입력하세요");
        e.preventDefault();
        return;
    }

    if (workEnd.value == '') {
        alert("종료 시간을 입력하세요");
        e.preventDefault();
        return;
    }

    if (startTime - endTime > 0) {
        alert('올바른 시간을 입력하세요');
        e.preventDefault();
        return;
    }

    /* 모집 성별 유효성 검사 */
    const hireGender = document.querySelectorAll(".hireGender");

    let genderCount = 0;
    hireGender.forEach(item => {
        if (item.checked) {
            genderCount++;
        }
    });

    if (genderCount == 0) {
        alert("모집 성별을 체크하세요.");
        e.preventDefault();
        return;
    }

    /* 급여 유효성 검사 */
    const payInput = document.getElementById("payInput");

    if (payInput.value.trim().length === 0) {
        alert("급여를 입력해주세요.");
        e.preventDefault();
        return;
    }

    /* 근무지 주소 유효성 검사 */
    const address = document.getElementById("address");
    const addressDetail = document.getElementById("addressDetail");
    const shopName = document.getElementById("shopName");

    if (address.value.trim().length === 0) {
        alert("주소를 입력해주세요.");
        e.preventDefault();
        return;
    }
    if (addressDetail.value.trim().length === 0) {
        alert("세부 주소를 입력해주세요.");
        e.preventDefault();
        return;
    }
    if (shopName.value.trim().length === 0) {
        alert("회사명을 입력해주세요.");
        e.preventDefault();
        return;
    }

    /* 담당자명 유효성 검사 */
    const shopOwner = document.getElementById("shopOwner");

    if (shopOwner.value.trim().length === 0) {
        alert("담당자명을 입력해주세요.");
        e.preventDefault();
        return;
    }

    /* 약관 동의 유효성 검사 */
    const agree = document.getElementById("agree");

    if (!agree.checked) {
        alert("약관에 동의해주세요");
        e.preventDefault();
        return;
    }
});

// 공고문 저장 버튼 클릭 이벤트
const submitBtn = document.querySelector("#submitBtn");
submitBtn.addEventListener("click", () => {
    isSemiSave = false; // 저장 버튼 클릭 시 임시저장 플래그를 해제
    const hireStatus = document.createElement("input");
    hireStatus.classList.add("hidden");
    hireStatus.setAttribute("name", "hireStatus");
    //저장 : 0 // 임시저장 : 1
    hireStatus.value = 0;
    const hireWrtieForm = document.querySelector("#hireWrtieForm");
    hireWrtieForm.append(hireStatus);
});


