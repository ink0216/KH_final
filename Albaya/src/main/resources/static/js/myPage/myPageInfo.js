const updateMemberInfo = document.querySelector("#updateMemberInfo");
const deleteMember = document.querySelector("#deleteBtn");
const changePwBtn = document.querySelector("#changePwBtn");
const hireApplyListBtn = document.querySelector("#hireApplyListBtn");
const closeBtn = document.getElementById("close-btn");
const managerBtn = document.querySelector("#managerBtn");


let shopProfileStatus = false;

document.addEventListener("DOMContentLoaded", () => {
    fetch("/myPage/countHireApply",{
        method:"GET"
    })
    .then(resp => resp.text())
    .then(result => {
        const hireApplyCount = document.querySelector("#hireApplyCount");
        hireApplyCount.innerHTML = result;
       
        hireApplyListBtn.addEventListener("click", e => {
            if(result <= 0){
                alert("지원한 공고가 없습니다.");
                e.preventDefault();
                return;
            }
            else{
                location.href = "/myPage/hireApplyList";
            }
        })
    })
})

updateMemberInfo.addEventListener("click", () => {
    location.href = "/myPage/myPageCheckPw";
});

deleteMember.addEventListener("click", () => {
    location.href = "/myPage/deleteMember";
});

changePwBtn.addEventListener("click", () => {
    location.href = "/myPage/changePw";
});

closeBtn.addEventListener("click", ()=>{
    location.href="/";
})


if(managerBtn != null){
managerBtn.addEventListener("click", () => {
    location.href = "/declare/1?cp=1";
})
}
//--------------------------------------------------------------------
const uploadBtn = document.querySelector("#uploadBtn"); //버튼
let imageInput = document.querySelector("#imageInput"); //input태그
const profileImg = document.querySelector('#profileImg'); //사진 img 태그

const originalImageSrc = profileImg.src;
const submitBtn = document.querySelector("#submitBtn");

uploadBtn.addEventListener("click", e => {
    imageInput.click();
});

const changeImageFn = e => {
    const maxSize = 1024 * 1024 * 5;
    const file = e.target.files[0];

    if (!file) {
        alert("파일을 선택해주세요.");
        profileImg.setAttribute("src", originalImageSrc);
        shopProfileStatus = false;
        return;
    }

    if (file.size > maxSize) {
        alert("5MB 이하의 이미지 파일을 선택해 주세요.");
        imageInput.value = '';
        profileImg.setAttribute("src", originalImageSrc);
        shopProfileStatus = false;
        return;
    }

    const reader = new FileReader();
    reader.readAsDataURL(file);

    reader.onload = e => {
        const url = e.target.result;
        profileImg.setAttribute("src", url);
        profileImg.style.display = 'block';
        shopProfileStatus = true;
    };
};

imageInput.addEventListener("change", changeImageFn);

submitBtn.addEventListener("click", e => {
    if (!shopProfileStatus) {
        e.preventDefault();
        alert("파일을 선택해주세요.");
        return;
    }
    if (!imageInput.value) {
        e.preventDefault();
        alert('이미지 선택 후 변경해주세요.');
        return;
    }
});




