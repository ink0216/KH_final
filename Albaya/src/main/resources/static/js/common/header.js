document.addEventListener("DOMContentLoaded", () => {
    const logo = document.querySelector("#logo");
    logo.addEventListener("click", () => {
        location.href = "/";
    });

    const account = document.querySelector("#account");
    if (account) { 
        account.addEventListener("click", () => {
            location.href = "/myPage/myPageInfo";
        });
    }
});
