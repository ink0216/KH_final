const logo = document.querySelector("#logo");
logo.addEventListener("click", ()=>{
    location.href="/";
});

const account = document.querySelector("#account");
account.addEventListener("click", () => {
    location.href="/myPage/myPageInfo";
});