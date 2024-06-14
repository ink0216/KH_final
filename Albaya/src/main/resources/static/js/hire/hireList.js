const hireWriteBtn = document.querySelector("#hireWriteBtn");

if(hireWriteBtn != null){
    hireWriteBtn.addEventListener("click", ()=>{
        location.href = "/hire/hireWrite";
    })
}
//공고 삭제 버튼
const deleteBtn = document.querySelectorAll(".delete");
deleteBtn.forEach(i=>{
    i.addEventListener("click", ()=>{
        if(!confirm("정말 삭제하시겠습니까?")){
            alert("공고 삭제를 취소하였습니다.");
            return;
        }
        let hireNo = i.dataset.hireNo;
        console.log(hireNo);
        const form = document.createElement("form");
        form.action = "/hire/delete/"+hireNo;
        form.method = "POST";
        const body = document.querySelector("body");
        body.appendChild(form);
        form.submit();
    })
});