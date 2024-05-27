const updateMemberInfo = document.querySelector("#updateMemberInfo");
const deleteBtn = document.querySelector("#deleteBtn");
const changePwBtn = document.querySelector("#changePwBtn");

updateMemberInfo.addEventListener("click", () => {
    location.href = "/myPage/myPageCheckPw";
});

deleteBtn.addEventListener("click", () => {
    location.href = "/myPage/deleteMember";
})

changePwBtn.addEventListener("click", () => {
    location.href = "/myPage/changePw";
})