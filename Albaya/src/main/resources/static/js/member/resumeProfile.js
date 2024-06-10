const popupContainer = document.querySelector(".popupContainer");
const cancel = document.querySelector(".cancel");
const update = document.querySelector(".update");
const imgEditBtn = document.querySelector("#imgEditBtn");
const picFile = document.querySelector("#picFile");
const img = document.querySelector("img");
const profImg = document.querySelector("#profImg")

//프로필 이미지 지정

imgEditBtn.addEventListener("click", () => {
    popupContainer.classList.add("show");
    popupContainer.classList.remove("hide");

    document.body.style.overflowY = "hidden";
    document.body.style.overflowX = "hidden";

    cancel.addEventListener("click", () => {
        popupContainer.classList.add("hide");
        popupContainer.classList.remove("show")
    });


    update.addEventListener("click", () => {
        const file = picFile.files[0];
        if(file){
            const reader = new FileReader();
            reader.onload = function(e) {
                const image = e.target.result;
                profImg.style.backgroundImage = `url(${image})`;
                profImg.style.backgroundSize = "302px auto";
                
                profImg.style.backgroundPosition = "center";
                profImg.style.backgroundRepeat = "no-repeat";
            }
            reader.readAsDataURL(file)
            popupContainer.classList.add("hide");
            popupContainer.classList.remove("show");
        }
        
    })

})
