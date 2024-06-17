const popupContainer = document.querySelector(".popupContainer");
const cancel = document.querySelector(".cancel");
const update = document.querySelector(".update");
const imgEditBtn = document.querySelector("#imgEditBtn");
const picFile = document.querySelector("#picFile");
const img = document.querySelector("img");
const profImg = document.querySelector("#profImg")
const backBtn = document.querySelector("#backBtn");

//프로필 이미지 지정



    backBtn.addEventListener("click", () => {
        location.href = "/resume/resumeList";
    });

//form.addEventListener("click", () => {}); 했을 때
//e.target == 버튼
//e.currentTarget == form (이벤트핸들러 함수가 연결된 것)
