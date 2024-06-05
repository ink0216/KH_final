const updateMemberInfo = document.querySelector("#updateMemberInfo");
const deleteMember = document.querySelector("#deleteBtn");
const changePwBtn = document.querySelector("#changePwBtn");

let shopProfileStatus = false;

document.addEventListener("DOMContentLoaded", () => {
    fetch("/myPage/countHireApply",{
        method:"GET"
    })
    .then(resp => resp.text())
    .then(result => {
        
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

const uploadBtn = document.querySelector("#uploadBtn");
let imageInput = document.querySelector("#imageInput");
const profileImg = document.querySelector('#profileImg');
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
        console.log(profileImg.src);
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
