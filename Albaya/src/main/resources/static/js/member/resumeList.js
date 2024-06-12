const resumeWriteBtn = document.querySelector("#resumeWriteBtn");

if(resumeWriteBtn != null){
    resumeWriteBtn.addEventListener("click", ()=>{
        location.href = "/resume/resumeWrite";
    })
}