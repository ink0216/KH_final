const typeSelect = document.getElementById("typeSelect");// 선택하기 버튼

typeSelect.addEventListener("click",()=>{
    window.close();
})

function selectType(button) {
    const typeText = button.innerText;
    if (window.opener && !window.opener.closed) {
        window.opener.setType(typeText);
    } else {
        alert('부모 창이 닫혔거나 열려 있지 않습니다.');
    }
}
