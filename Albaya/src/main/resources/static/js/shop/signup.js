

const shopEmail= document.querySelector("#shopEmail");
const emailVerify = document.querySelector("#emailVerify");
const emailBtn = document.querySelector("#emailBtn");

const shopPw = document.querySelector("#shopPw");
const pwVerify = document.querySelector("#pwVerify");

const authBtn = document.querySelector("#authBtn");

let count = 0;

const obj = {
    "shopEmail":false,
    "shopPw":false,
    "shopTel":false,
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
        obj.shopEmail = false;
        return;
    }
    else{
        emailVerify.classList.add("fail");
        emailVerify.classList.remove("success");
        emailVerify.innerText = "중복확인을 진행해주세요."
        obj.shopEmail = false;
    }
    emailBtn.addEventListener("click", () => {
        fetch("/shop/checkEmail",{
            method:"POST",
            headers: {"Content-Type":"application/json"},
            body: JSON.stringify({"shopEmail" : shopEmail.value})
        })
        .then(resp => resp.text())
        .then(result => {
            if(result == 1){
                emailVerify.classList.add("fail");
                emailVerify.classList.remove("success");
                emailVerify.innerText = "이미 존재하는 이메일 입니다"
                obj.shopEmail = false;
                return;
            }
            else{
                emailVerify.classList.add("success");
                emailVerify.classList.remove("fail");
                emailVerify.innerText = "사용 가능한 이메일입니다."
                obj.shopEmail = true;
                return;
            }
        })
    })
})
        
shopPw.addEventListener("input", () => {
    const regExp = /^[a-zA-Z0-9!@#_\-\^]{8,20}$/;

    if(shopPw.value.trim().length === 0){
        obj.shopPw= false;
        shopPw.value = "";
        return;
    }
    if(!regExp.test(shopPw.value)){
        pwVerify.classList.add("fail");
        pwVerify.classList.remove("success");
        pwVerify.innerText = "올바른 비밀번호 형식이 아닙니다";
        obj.shopPw = false;
        return;
    }

    pwVerify.classList.add("success");
    pwVerify.classList.remove("fail");
    pwVerify.innerText = "사용 가능한 비밀번호입니다."
    obj.shopPw = true;
    return;

});

inputTel.addEventListener("input", () => {
    const regExp = /^010\d{8}$/;

    if(inputTel.value.trim().length === 0){
        obj.shopTel= false;
        inputTel.value = "";
        return;
    }
    if(!regExp.test(inputTel.value)){
        pwVerify.classList.add("fail");
        pwVerify.classList.remove("success");
        pwVerify.innerText = "올바른 비밀번호 형식이 아닙니다";
        obj.shopTel = false;
        return;
    }

    pwVerify.classList.add("success");
    pwVerify.classList.remove("fail");
    pwVerify.innerText = "사용 가능한 비밀번호입니다."
    obj.shopTel = true;
    return;

});


document.querySelector('#authBtn').addEventListener('click', e => {
    if(count > 1){
        return;
    }
    e.preventDefault();

    const inputTel = document.querySelector('#inputTel').value;
    const authKeyCheck = document.querySelector('#authKeyCheck').value;
    const authBtn = document.querySelector('#authBtn');


    if(inputTel.trim().length === 0){
        alert("전화번호를 입력해주세요.");
        e.preventDefault();
        obj.authState = false;
        return;
    }
    else{
        const randomNumber = Math.floor(100000 + Math.random() * 900000);
        const msg = randomNumber.toString();

        console.log(msg);
        const authObj = {
            "inputTel" : inputTel,
            "msg" : msg
        }
        fetch('/member/send-one', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(authObj)
        })
        .then(resp => resp.json())
        .then(result => {
            count++;

            if(count > 1){
                alert("휴대폰 인증은 1회만 가능합니다. 새로고침 해주세요.");
                e.preventDefault();
                obj.authState = false;
                return;
            }

            alert("인증번호가 전송되었습니다.");

            authBtn.addEventListener("click", () => {
                console.log(msg)
                if(authKeyCheck.value.trim().length <= 0){
                    alert("인증번호를 입력해주세요.");
                    obj.authState = false;
                    return;
                }
                
                if(authObj.msg != authKeyCheck.value){
                    alert("인증번호가 일치하지 않습니다.");
                    obj.authState = false;
                    return;
                }
                alert("인증되었습니다.");
                obj.authState = true;
            })
        })
    }
});
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

