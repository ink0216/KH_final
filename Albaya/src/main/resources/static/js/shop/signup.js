

const shopEmail= document.querySelector("#shopEmail");

let count = 0;

const obj = {
    "memberEmail":false,
    "memberPw":false,
    "memberPhoneNumber":false,
    "memberName":false,
    "memberGender":false,
    "memberAddress":false,
    "authState":false
};

shopEmail.addEventListener("input", () => {
    const regExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    if(shopEmail.value.trim().length === 0){
        obj.memberEmail= false;
        shopEmail.value = "";
        return;
    }

    if(!regExp.test(shopEmail.value)){
        emailVerify.classList.add("fail");
        emailVerify.classList.remove("success");
        emailVerify.innerText = "올바른 이메일 형식이 아닙니다";
        obj.memberEmail = false;
        return;
    }
    fetch("/shop/checkEmail",{
        method:"POST",
        headers: {"Content-Type":"application/json"},
        body: JSON.stringify({ email: shopEmail.value })
    })
    .then(resp => resp.text())
    .then(result => {
        if(result == 1){
            console.log("ㅎㅇ");
            emailVerify.classList.add("fail");
            emailVerify.classList.remove("success");
            emailVerify.innerText = "이미 존재하는 이메일 입니다"
            obj.memberEmail = false;
            return;
        }
        emailVerify.classList.add("success");
        emailVerify.classList.remove("fail");
        emailVerify.innerText = "사용 가능한 이메일 입니다"
        obj.memberEmail = true;

        
    })
    emailVerify.classList.add("success");
    emailVerify.classList.remove("fail");
    emailVerify.innerText = "올바른 이메일 형식입니다";
    obj.memberEmail = true;
})
        

function sample5_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {

            var addr;

            if(data.jibunAddress!=''){ //지번주소가 있는 경우
                addr = data.jibunAddress; // 최종 주소 변수(지번주소)
            } 
            if(data.autoJibunAddress!=''){  //지번 주소가 없는 경우
                addr = data.autoJibunAddress; 
            }


            console.log(data);
            console.log("data.autoJibunAddress : ",data.autoJibunAddress);
            console.log("addr : ",addr);


            //-----------------------------------------------------------------------
            //input 요소 추가        
            const dd = document.querySelector('.address-dd'); //dd태그

            const div = document.createElement('div');
            div.className='hidden';
            
            const sido = document.createElement('input'); // 시도
            const sigunsu = document.createElement('input'); // 시군구
            const dong = document.createElement('input'); //동읍면

            sido.setAttribute("name","dosiName"); 
            sigunsu.setAttribute("name","sigunguName");
            dong.setAttribute("name","dongName");

            sido.value=data.sido;
            sigunsu.value=data.sigungu;
            dong.value=data.bname;

            div.append(sido,sigunsu,dong);
            dd.append(div);

            //---------------------------------------------------------------------------

            // 주소 정보를 해당 필드에 넣는다.
            document.getElementById("address").value = addr;
        }
    }).open();
}

