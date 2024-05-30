const memberBtn = document.querySelector("#memberBtn");
const shopBtn = document.querySelector("#shopBtn");

memberBtn.addEventListener("click", () => {
    location.href = "/member/signup";
}) 

shopBtn.addEventListener("click", () => {
    location.href = "/shop/signup";
}) 