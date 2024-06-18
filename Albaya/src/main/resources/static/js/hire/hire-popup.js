const typeSelect = document.getElementById("typeSelect");// 선택하기 버튼

typeSelect.addEventListener("click",()=>{
    window.close();
})

const types = document.querySelectorAll(".type");

types.forEach(item=>{
    item.addEventListener("click",(e)=>{
        const typeText = e.target.innerText;

        if (window.opener && !window.opener.closed) {
            
            window.opener.setType(typeText);
            window.opener.document.getElementById("typeNo").value = e.target.dataset.typeNo;

            typeSelect.addEventListener("click",()=>{
                window.close();
            })
            
        } else {
            alert('부모 창이 닫혔거나 열려 있지 않습니다.');
        }
    })
})
