const resumeWriteBtn = document.querySelector("#resumeWriteBtn");

if(resumeWriteBtn != null){
    resumeWriteBtn.addEventListener("click", ()=>{
        location.href = "/resume/resumeWrite";
    })
}
//이력서 삭제 버튼
const deleteBtn = document.querySelectorAll(".delete");
deleteBtn.forEach(i=>{
    i.addEventListener("click", ()=>{
        if(!confirm("정말 삭제하시겠습니까?")){
            alert("이력서 삭제를 취소하였습니다.");
            return;
        }
        let resumeNo = i.dataset.resumeNo;
        console.log(resumeNo);
        location.href = "/resume/resumeDelete?resumeNo="+resumeNo;
    })
});
//이력서 상세 조회 페이지로 이동
const resumeTitle = document.querySelectorAll("#resumeTitle");
resumeTitle.forEach(i=>{
    i.addEventListener("click", ()=>{
        let resumeNo = i.dataset.resumeNo;
        console.log(resumeNo);
        location.href = "/resume/resumeDetail?resumeNo="+resumeNo;
    })
});

//수정 버튼
const update = document.querySelectorAll(".update");
update.forEach(i=>{
    i.addEventListener("click", ()=>{
        let resumeNo = i.dataset.resumeNo;
        console.log(resumeNo);
        location.href = "/resume/resumeUpdate?resumeNo="+resumeNo;
    })
});

