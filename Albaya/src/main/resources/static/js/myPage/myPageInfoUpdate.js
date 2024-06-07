let authState = false;

const cancelBtn = document.querySelector("#cancelBtn");
cancelBtn.addEventListener("click", () => {
    location.href = "/myPage/myPageInfo";
});

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
