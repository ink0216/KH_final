

const shopEmail= document.querySelector("#shopEmail");
const emailVerify = document.querySelector("#emailVerify");
const emailBtn = document.querySelector("#emailBtn");

const shopPw = document.querySelector("#shopPw");
const pwVerify = document.querySelector("#pwVerify");

const authBtn = document.querySelector("#authBtn");
const telVerify = document.querySelector("#telVerify");
const authCheckBtn = document.querySelector("#authCheckBtn");

// const authCheckBtn = document.querySelector("#authCheckBtn"); // 인증번호 인증
// const authBtn = document.querySelector('#authBtn'); // 인증번호 전송
const inputTel = document.querySelector('#inputTel'); // 전화번호 입력
const authKeyCheck = document.querySelector('#authKeyCheck'); // 인증번호 입력

const brnVerif = document.querySelector('#brnVerif');
const shopBrn = document.querySelector('#shopBrn');
const brnBtn = document.querySelector('#brnBtn');

const signupBtn = document.querySelector("#signupBtn");
let count = 0;

const obj = {
    "shopProfile":false,
    "shopEmail":false,
    "shopPw":false,
    "shopTel":false,
    "shopBrn":false,
    "authState":false
};

shopEmail.addEventListener("input", () => {
    const regExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    if(shopEmail.value.trim().length === 0){
        obj.shopEmail= false;
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
        telVerify.classList.add("fail");
        telVerify.classList.remove("success");
        telVerify.innerText = "올바른 전화번호 형식이 아닙니다";
        obj.shopTel = false;
        return;
    }

    telVerify.classList.add("success");
    telVerify.classList.remove("fail");
    telVerify.innerText = "사용 가능한 전화번호입니다."
    obj.shopTel = true;
    return;

});

authBtn.addEventListener('click', e => {
    if(obj.shopTel === false){
        e.preventDefault();
        return;
    }

    const inputTel = document.querySelector('#inputTel');
    const authKeyCheck = document.querySelector('#authKeyCheck');


    if(inputTel.value.trim().length === 0){
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
            "inputTel" : inputTel.value,
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
            authBtn.disabled = true;
            inputTel.readOnly = true;
            alert("인증번호가 전송되었습니다.");

            authCheckBtn.addEventListener("click", () => {
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

shopBrn.addEventListener("input", () => {
    const regExp = /^\d{10}$/;

    asd(10);

    if(shopBrn.value.trim().length === 0){
        obj.shopBrn= false;
        shopBrn.value = "";
        return;
    }

    if(!regExp.test(shopBrn.value)){
        brnVerif.classList.add("fail");
        brnVerif.classList.remove("success");
        brnVerif.innerText = "올바른 사업자등록번호가 아닙니다.";
        obj.shopBrn = false;
        return;
    }
    else{
        brnVerif.classList.add("fail");
        brnVerif.classList.remove("success");
        brnVerif.innerText = "중복확인을 진행해주세요."
        obj.shopBrn = false;
    }
    brnBtn.addEventListener("click", () => {
        fetch("/shop/checkBrn",{
            method:"POST",
            headers: {"Content-Type":"application/json"},
            body: JSON.stringify({"shopBrn" : shopBrn.value})
        })
        .then(resp => resp.text())
        .then(result => {
            if(result == 1){
                console.log(result)
                brnVerif.classList.add("fail");
                brnVerif.classList.remove("success");
                brnVerif.innerText = "이미 사용중인 사업자등록번호입니다.";
                obj.shopBrn = false;
                return;
            }
            else{
                console.log(result)

                brnVerif.classList.add("success");
                brnVerif.classList.remove("fail");
                brnVerif.innerText = "사용 가능한 사업자등록번호입니다."
                obj.shopBrn = true;
                return;
            }
        })
    })
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

const uploadButton = document.querySelector("#uploadButton");
let imageInput = document.querySelector("#imageInput");
const profileImg = document.querySelector('#profileImg');

uploadButton.addEventListener("click", e => {
    imageInput.click();
});

const changeImageFn = e => {

    const maxSize = 1024 * 1024 * 5;

    const file = e.target.files[0];

    if (!file) {
        alert("파일을 선택해주세요.");
        obj.shopProfile = false;
        return;
    }
    
    if (file.size > maxSize) {
        alert("5MB 이하의 이미지 파일을 선택해 주세요.");
        imageInput.value = '';
        obj.shopProfile = false;
        return;
    }

    const reader = new FileReader();
    reader.readAsDataURL(file);

    reader.onload = e => {
        const url = e.target.result;
        profileImg.setAttribute("src", url);
        profileImg.style.display = 'block';
        obj.shopProfile = true;
        console.log(profileImg.src);
    };
    
};

imageInput.addEventListener("change", changeImageFn);

const deleteBtn = document.querySelector("#deleteBtn");

deleteBtn.addEventListener("click", e => {
    imageInput.value = '';
    profileImg.style.display = 'none';
    profileImg.setAttribute("src", "");

    obj.shopProfile = false;
});
signupBtn.addEventListener("click", e => {
    const keys = Object.keys(obj);
    for (const key of keys) {
        switch (key) {
            case "shopProfile":
                if (!obj.shopProfile) {
                    e.preventDefault();
                    alert("대표 이미지를 삽입해주세요.");
                    return;
                }
                break;
            case "shopEmail":
                if (!obj.shopEmail) {
                    shopEmail.focus();
                    e.preventDefault();
                    alert("이메일이 올바르지 않습니다.");
                    return;
                }
                break;
            case "shopPw":
                if (!obj.shopPw) {
                    shopEmail.focus();
                    e.preventDefault();
                    alert("비밀번호가 올바르지 않습니다.");
                    return;
                }
                break;
            case "shopTel":
                if (!obj.shopTel) {
                    inputTel.focus();
                    e.preventDefault();
                    alert("전화번호가 올바르지 않습니다.");
                    return;
                }
                break;
            case "authState":
                if (!obj.authState) {
                    authBtn.focus();
                    e.preventDefault();
                    alert("인증이 완료되지 않았습니다.");
                    return;
                }
                break;
            case "shopBrn":
                if (!obj.shopBrn) {      
                    shopBrn.focus();
                    e.preventDefault();
                    alert("사업자등록번호가 올바르지 않습니다.");
                    return;
                }
                break;
            default:
                break;
        }
    }
})

const uploadButton = document.querySelector("#uploadButton");
let imageInput = document.querySelector("#imageInput");
const profileImg = document.querySelector('#profileImg');

uploadButton.addEventListener("click", e => {
    imageInput.click();
});

const changeImageFn = e => {

    const maxSize = 1024 * 1024 * 5; // 5MB

    const file = e.target.files[0];

    if (!file) {
        alert("파일을 선택해주세요.");
        return; // 파일이 선택되지 않은 경우
    }
    
    if (file.size > maxSize) {
        alert("5MB 이하의 이미지 파일을 선택해 주세요.");
        imageInput.value = ''; // 입력 초기화
        return;
    }

    const reader = new FileReader();

    reader.onload = e => {
        const url = e.target.result;
        profileImg.setAttribute("src", url);
        profileImg.style.display = 'block';
    };

    reader.readAsDataURL(file);
};

imageInput.addEventListener("change", changeImageFn);
