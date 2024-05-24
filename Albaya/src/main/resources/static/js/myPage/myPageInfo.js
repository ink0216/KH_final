const updateMemberInfo = document.querySelector("#updateMemberInfo");
const deleteBtn = document.querySelector("#deleteBtn");

updateMemberInfo.addEventListener("click", () => {
    location.href = "/myPage/myPageCheckPw";
});

deleteBtn.addEventListener("click", () => {
    location.href = "/myPage/deleteMember"
})
