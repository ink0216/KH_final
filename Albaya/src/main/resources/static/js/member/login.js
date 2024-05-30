const personalMember = document.querySelector('#personalMember');
const coparationMember = document.querySelector('#coparationMember');

personalMember.addEventListener("click", () => {
    personalMember.style.backgroundColor = "#f5f5f5";
    coparationMember.style.backgroundColor = "white"
    document.querySelector('.login-form').action = '/member/login';
})

coparationMember.addEventListener("click", () => {
    coparationMember.style.backgroundColor = "#f5f5f5";
    personalMember.style.backgroundColor = "white"
    document.querySelector('.login-form').action = '/shop/login';
    document.querySelector("#memberEmail").name = 'shopEmail';
    document.querySelector("#memberPw").name = 'shopPw';
})

const getCookie = (key) => {
    const cookies = document.cookie;
    
    const cookieList = cookies.split("; ").map(el => el.split("="));

    const obj = {};

    for(let i=0; i<cookieList.length; i++){
        const k = cookieList[i][0];
        const v = cookieList[i][1];
        obj[k] = v;
    }
    return obj[key]; 
}
const loginEmail = document.querySelector("#loginForm input[name='memberEmail']");
const loginPw = document.querySelector("#loginForm input[name='memberPw']");

if(loginEmail != null){
    const saveId = getCookie("saveId");

    if(saveId != undefined){
        loginEmail.value = saveId;

        document.querySelector("input[name='saveId']").checked = true;
    }
}