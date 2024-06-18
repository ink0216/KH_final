    /* 다음 주소 API 활용 */
    function execDaumPostcode() {
        new daum.Postcode({
        oncomplete: function (data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
    
            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
        
    
            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
            addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
            addr = data.jibunAddress;
            }
    
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();
        }
        }).open();
    }

    document.querySelector("#searchAddress").addEventListener("click", execDaumPostcode);


    //--------------유효성 검사
    const memberAuth = document.querySelector("#memberAuth");
    const inputAuth = document.querySelector("#inputAuth");

    const inputEmail = document.querySelector("#inputEmail");
    const emailVerify = document.querySelector("#emailVerify");

    const inputPw = document.querySelector("#inputPw");
    const passwordVerify = document.querySelector("#passwordVerify");

    const inputTel = document.querySelector("#inputTel");

    const inputName = document.querySelector("#inputName");
    const nameVerify = document.querySelector("#nameVerify");

    const inputGender = document.querySelectorAll(".inputGender");

    const signupForm = document.querySelector("#signupForm");


    const postcode = document.querySelector("#postcode");
    const address = document.querySelector("#address");
    const detailAddress = document.querySelector("#detailAddress");
    const addressVerify = document.querySelector("#addressVerify");

   

    let count = 0;

    const obj = {
        "memberEmail":false,
        "memberPw":false,
        "memberPhoneNumber":false,
        "memberName":false,
    
        "memberAddress":false,
        "authState":false
    };

    document.querySelector('#memberAuth').addEventListener('click', function(event) {
        if(count > 1){
            return;
        }
        event.preventDefault();
        const inputTel = document.querySelector('#inputTel').value;

        if(inputTel.trim().length === 0){
            alert("전화번호를 입력해주세요.");
            event.preventDefault();
            obj.authState = false;
            return;
        }
        else{
            const randomNumber = Math.floor(100000 + Math.random() * 900000);
            const msg = randomNumber.toString();
    
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
                    event.preventDefault();
                    obj.authState = false;
                    return;
                }
                
                const inputAuthField = document.createElement("input");
                inputAuthField.classList.add("inputAuthField");
                inputAuthField.setAttribute("type","text");
                inputAuth.append(inputAuthField);
    
                const authButton = document.createElement("button");
                authButton.classList.add("authButton");
                authButton.setAttribute("type","button");
                inputAuth.append(authButton);
                authButton.textContent = "인증확인";
    
                alert("인증번호가 전송되었습니다.");
    
                authButton.addEventListener("click", () => {
                    if(inputAuthField.value.trim().length <= 0){
                        alert("인증번호를 입력해주세요.");
                        obj.authState = false;
                        return;
                    }
                    
                    if(authObj.msg != inputAuthField.value){
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



    /* 이메일 유효검사 */

    inputEmail.addEventListener("input", () => {
        const regExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        
        if(inputEmail.value.trim().length === 0){
            obj.memberEmail= false;
            inputEmail.value = "";
            return;
        }
        if(!regExp.test(inputEmail.value)){
            emailVerify.classList.add("fail");
            emailVerify.classList.remove("success");
            emailVerify.innerText = "올바른 이메일 형식이 아닙니다";
            obj.memberEmail = false;
            return;
        }

        fetch("/member/checkEmailRedundancy", {
            method : "POST",
            headers : {"Content-Type":"application/json"},
            body : inputEmail.value
        })
        .then(resp => resp.text())
        .then(result => {               
            if(result > 0){
                emailVerify.classList.add("fail");
                emailVerify.classList.remove("success");
                emailVerify.innerText = "이미 존재하는 이메일입니다";
                obj.memberEmail = false;
                return;
            }
            emailVerify.classList.add("success");
            emailVerify.classList.remove("fail");
            emailVerify.innerText = "사용 가능한 이메일 입니다";
            obj.memberEmail = true;
        })

            
        // })
       

    });

    /* 비밀번호 유효검사 */
    
    inputPw.addEventListener("input", () => {

        const member = {
            "memberPw":inputPw.value,
            "memberEmail":inputEmail.value

        };
        
        const regExp = /^[a-zA-Z0-9!@#_-]{8,20}$/;
        if(inputPw.value.trim().length === 0){
            obj.memberPw= false;
            inputPw.value = "";
        
        }
        if(!regExp.test(inputPw.value)){
            passwordVerify.classList.add("fail");
            passwordVerify.classList.remove("success");
            passwordVerify.innerText = "올바른 비밀번호 형식이 아닙니다";
            obj.memberPw = false;
            return;
        }

        fetch("/member/checkPwRedundancy",{
            method: "POST",
            headers: {"Content-Type":"application/json"},
            body: JSON.stringify(member)
        })
        .then(resp => resp.json())
        .then(result => {
            if(result==1){
                passwordVerify.classList.add("fail");
                passwordVerify.classList.remove("success");
                passwordVerify.innerText = "기존과 다른 비밀번호를 입력하세요";
                obj.memberPw = false;
                return;
            }
            passwordVerify.classList.add("success");
            passwordVerify.classList.remove("fail");
            passwordVerify.innerText = "사용 가능한 비밀번호 입니다";
            obj.memberPw = true;

        })
       
        
       
    })

    /*전화번호 유효검사*/
    inputTel.addEventListener("input", () => {
        const regexp = /^01[0-9]{1}[0-9]{3,4}[0-9]{4}$/;
        if(inputTel.value.trim().length===0){
            inputAuth.classList.remove("success");
            obj.memberPhoneNumber=false;
            inputTel.value="";

        }
        if(!regexp.test(inputTel.value)){
            inputAuth.classList.add("fail");
            inputAuth.classList.remove("success");
            inputAuth.innerText = "올바른 전화번호 형식이 아닙니다";
            obj.memberPhoneNumber = false;
            return;
        }

        fetch("/member/checkTelRedundancy",{
            method:"POST",
            headers: {"Content-Type":"application/json"},
            body: inputTel.value
        })
        .then(resp => resp.text())
        .then(result => {
            if(result == 1){
                inputAuth.classList.add("fail");
                inputAuth.classList.remove("success");
                inputAuth.innerText = "이미 존재하는 전화번호 입니다";
                obj.memberPhoneNumber = false;
                return;
            }

            inputAuth.classList.add("success");
            inputAuth.classList.remove("fail");
            inputAuth.innerText = "사용 가능한 전화번호 입니다";
            obj.memberPhoneNumber = true;
           
        })
        
    });

    /* 이름 유효검사 */
    inputName.addEventListener("input", () => {
        const regExp = /^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{2,5}$/;
        if(inputName.value.trim().length === 0){
            obj.memberName=false;
            inputName.value="";
        }
        if(!inputName.focus()==true){
            nameVerify.innerHTML = "";
        }

        if(!regExp.test(inputName.value)){
            nameVerify.classList.add("fail");
            nameVerify.classList.remove("success");
            nameVerify.innerText = "올바른 이름 형식이 아닙니다";
            obj.memberName = false;
            return;
        }
    
        nameVerify.classList.add("success");
        nameVerify.classList.remove("fail");
        nameVerify.innerText = "올바른 이름 형식입니다";
        obj.memberName = true;
    });

    /* 주소 유효성 검사 1*/
    const restriction = () => {
        addressVerify.classList.add("fail");
        addressVerify.classList.remove("success");
        addressVerify.innerText = "올바른 주소 형식이 아닙니다";
        obj.memberAddress = false;
    }

    const validation = () => {
        addressVerify.classList.add("success");
        addressVerify.classList.remove("fail");
        addressVerify.innerText = "올바른 주소 형식입니다";
        obj.memberAddress  = true;
    }

    postcode.addEventListener("input", () => {
        const regExp = /^[0-9]{2,5}$/;
    
    if(postcode.value.trim().length === 0){
        obj.memberAddress = false;
        postcode.value = "";
    }
    if(!regExp.test(postcode.value)){
        restriction();
        return;
    }
    validation();
    });

    /* 주소 유효성 검사 2 */
    address.addEventListener("input", () => {
        const regExp = /^[a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣0-9]{2,30}$/;

        if(address.value.trim().length === 0){
            
            address.value = "";
            obj.memberAddress =false;
        }
        if(!regExp.test(address.value)){
        restriction();
        return;
        }
        validation();

    });

    /* 주소 유효성 검사 3 */
    detailAddress.addEventListener("input", () => {
        const regExp = /^[[a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣0-9]{2,30}$/;
        if(detailAddress.value.trim().length === 0){
            detailAddress.value = "";
            obj.memberAddress =false;
        }
        if(!regExp.test(detailAddress.value )){
        restriction();
        return;
        }
        validation();
    });

    signupForm.addEventListener("submit", () => {

    })

    /* const obj = {
        "memberEmail":false,
        "memberPw":false,
        "memberPhoneNumber":false,
        "memberName":false,
        "memberGender":false,
        "memberAddress":false,
        "authState":false
    };
 */

    signupForm.addEventListener("submit", e => {
        for(let key in obj){
            if(!obj[key]){
                let str;
                switch(key){
                    case "memberEmail":str="이메일을 올바르게 입력하세요"; break;
                    case "memberPw":str="비밀번호를 올바르게 입력하세요"; break;
                    case "memberPhoneNumber":str="전화번호를 올바르게 입력하세요"; break;
                    case "memberName":str="이름을 올바르게 입력하세요"; break;
               
                    case "memberAddress":str="주소를 올바르게 입력하세요"; break;
                    case "authState":str="인증번호를 올바르게 입력하세요"; break;
                }
                alert(str);
                e.preventDefault();
                return;
            }
        }

    })
  
/* 뒤로가기 버튼 클릭 */
const goBackBtn = document.getElementById("go-back-btn");
goBackBtn.addEventListener("click",()=>{

    if( confirm("가입을 취소하시겠습니까?")){
        let url = "/member/selectSignup";
        location.href = url;
    }
   
})

    

















