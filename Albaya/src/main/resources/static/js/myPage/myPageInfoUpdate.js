let authState = false;

const cancelBtn = document.querySelector("#cancelBtn");
cancelBtn.addEventListener("click", () => {
    location.href = "/myPage/myPageInfo";
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
            const postcodeElement = document.getElementById('postcode');

            if(postcodeElement){
                postcodeElement.value = data.zonecode;
            }

            document.getElementById("address").value = addr;
        }
    }).open();
}

document.querySelector("#searchAddress").addEventListener("click", sample5_execDaumPostcode);

document.addEventListener("DOMContentLoaded", function() {
    
    const changeTelBtn = document.querySelector("#changeTelBtn");
    const memberTel = document.querySelector("#memberTel");
    const regExp = /^010\d{8}$/;

    changeTelBtn.addEventListener("click", () => {
        if (document.querySelector("#authKeyInput")) {
            return;
        }

        memberTel.value = "";
        memberTel.readOnly = false;
        memberTel.classList.remove("memberTel");
        memberTel.classList.add("newMemberTel");
        memberTel.placeholder = "새로운 휴대폰 번호 입력"

        changeTelBtn.innerHTML = "인증하기";

        const newInfoRow = document.createElement("div");
        newInfoRow.classList.add("info-row");

        const newInfoRowLeft = document.createElement("div");
        newInfoRowLeft.classList.add("info-row-left");
        const span = document.createElement("span");
        span.innerText = "인증번호";
        newInfoRowLeft.appendChild(span);

        const newInfoRowRight = document.createElement("div");
        newInfoRowRight.classList.add("info-row-right");
        const newInput = document.createElement("input");
        newInput.type = "number";
        newInput.id = "authKeyInput";
        newInfoRowRight.appendChild(newInput);

        const newInfoRowBtnArea = document.createElement("div");
        newInfoRowBtnArea.classList.add("info-row-btn-area");
        const newButton = document.createElement("button");
        newButton.type = "button";
        newButton.id = "authKeyCheckBtn";
        newButton.innerText = "확인";
        newInfoRowBtnArea.appendChild(newButton);

        newInfoRow.appendChild(newInfoRowLeft);
        newInfoRow.appendChild(newInfoRowRight);
        newInfoRow.appendChild(newInfoRowBtnArea);

        const currentInfoRow = document.querySelector("#changeTelBtn").closest(".info-row");
        currentInfoRow.after(newInfoRow);

        changeTelBtn.id = "newChangeTelBtn";

        document.querySelector("#newChangeTelBtn").addEventListener("click", () => {
            
            
            if(memberTel.value.trim().length === 0){
                alert("전화번호를 입력해주세요.");
                authState = false;
                return;
            }
            if(!regExp.test(memberTel.value)){
                alert("전화번호를 올바르게 입력해주세요.");
                authState = false;
                return;
            }
            else{
                const randomNumber = Math.floor(100000 + Math.random() * 900000);
                const msg = randomNumber.toString();

                console.log(msg);
                const authObj = {
                    "inputTel" : memberTel.value,
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
                    document.querySelector("#newChangeTelBtn").disabled = true;
                    memberTel.readOnly = true;
                    memberTel.style.backgroundColor = '#F4F4F4';
                    memberTel.style.pointerEvents = 'none';


                    alert("인증번호가 전송되었습니다.");

                    document.querySelector("#authKeyCheckBtn").addEventListener("click", () => {
                        const authKeyInput = document.querySelector("#authKeyInput");
                        console.log(msg)
                        if(authKeyInput.value.trim().length <= 0){
                            alert("인증번호를 입력해주세요.");
                            authState = false;
                            return;
                        }
                        
                        if(authObj.msg != authKeyInput.value){
                            alert("인증번호가 일치하지 않습니다.");
                            authState = false;
                            return;
                        }

                        alert("인증되었습니다.");
                        authState = true;
                    })
                })
            }
        });
    });
});

const updateBtn = document.querySelector("#updateBtn");
updateBtn.addEventListener("click", e => {
    if(document.querySelector("#newChangeTelBtn") != null){
        if(!authState){
            alert("휴대폰 인증을 완료해주세요.")
            memberTel.focus();
            e.preventDefault();
            return;
        }
    }
})

