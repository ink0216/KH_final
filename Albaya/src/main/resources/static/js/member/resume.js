
//최종학력 선택(고등학교/대학교) 선택시 팝업

    const dropDown = document.querySelector("#dropDown");

    const selectPrimarySchoolName = document.querySelector("#selectPrimarySchoolName");
    const selectMiddleSchoolName = document.querySelector("#selectMiddleSchoolName");
    const selectSchoolName = document.querySelector("#selectSchoolName");
    const selectUniversityName = document.querySelector("#selectUniversityName");

    

    const eduObj = {
        "1":"selectPrimarySchoolName", //초
        "2":"selectMiddleSchoolName", //중
        "3":"selectSchoolName", //고
        "4":"twoUniversityName", //2,3년제
        "5":"fourUniversityName", //4년제
        "6":"graduateUniversityName" //대학원 이상
    };


    dropDown.addEventListener("change" , () => {
        let selectedVal = dropDown.value;
        for(key in eduObj){
            let element = document.querySelector("#"+eduObj[key]);
            if(element){
                if(selectedVal == key){
                    element.classList.add("show");
                    element.classList.remove("hide");
                }else{
                    element.classList.remove("show");
                    element.classList.add("hide");
                }
            }
        }
        
    
    
    });
    //비동기로 위치정보 얻어와 화면 만들기
    const dosies = document.querySelectorAll(".dosies");
    const sigunguList = document.querySelector(".sigunguList");
    const dong = document.querySelector(".dong");
    const locationContentArr = [];
    dosies.forEach(btn => {
        btn.addEventListener("click", () => {
            sigunguList.innerHTML = "";
            dong.innerHTML = "";
            const dosiName = btn.textContent;
            fetch("/hire/selectSigungu?dosiName="+dosiName)
            .then(resp=>resp.json())
            .then(
                list=>{
                    list.forEach(sigunguItem=>{
                        const div = document.createElement("div");
                        div.innerHTML = sigunguItem.sigunguName;
                        sigunguList.classList.add("sigungus");
                        sigunguList.append(div);

                        div.addEventListener("click", () => {
                            dong.innerHTML="";
                            const sigunguName = div.textContent;
                            fetch("/hire/selectDong?dosiName="+dosiName+"&sigunguName="+sigunguName)
                            .then(resp=>resp.json())
                            .then(
                                list=>{
                                    list.forEach(dongItem=>{
                                        const dongdiv = document.createElement("div");
                                        dongdiv.innerHTML = dongItem.dongName;
                                        dongdiv.classList.add("dongs");
                                        dong.append(dongdiv);
                                        dongdiv.addEventListener("click",()=>{
                                            
                                            for(let i=0; i<locationContentArr.length; i++){
                                                
                                                if(dongItem.dongNo == locationContentArr[i]){
                                                    alert("중복 선택은 가능하지 않습니다");
                                                    return;
                                                }
                                            }
                                            if(document.querySelectorAll(".selectDong>span").length > 4){
                                                alert("최대 5가지만 선택할 수 있습니다");
                                                return;
                                            }
                                            
                                                const selectLocationInnerHtml = `
                                                <span class = "selectDong">
                                                    ${dongItem.dongName}
                                                    <span>&times;</span>
                                                </span>`;

                                            locationSelectContainer.innerHTML += selectLocationInnerHtml;
                                            locationContentArr.push(dongItem.dongNo);
                                            const input = document.createElement("input");
                                            input.type = "hidden";
                                            input.name = "dongNo";
                                            input.value = dongItem.dongNo;
                                            form.append(input);
                                        });
                                        
                                    })
                                }
                            );
                        });
                    })
                });
        });
    });
//city dropdown

const dongs = document.querySelectorAll(".dongs");
const locationSelectContainer =  document.querySelector(".locationSelectContainer");
const selectCont = document.querySelectorAll(".selectDong");


dongs.forEach(btn => {
    btn.addEventListener("click", () => {
       
       
        for(let i=0; i<locationContentArr.length; i++){
            if(btn.textContent == locationContentArr[i]){
                alert("중복 선택은 가능하지 않습니다");
                return;
            }
        } 
        locationContentArr.push(btn.textContent);


        if(document.querySelectorAll(".selectDong>span").length > 5){
            alert("최대 5가지만 선택할 수 있습니다");
            return;
        }

        const selectLocationInnerHtml = `
            <span class = "selectDong">
                ${btn.textContent}
                <span>&times;</span>
            </span>`;

        locationSelectContainer.innerHTML += selectLocationInnerHtml;

        const selectDong = document.querySelectorAll(".selectDong>span");
        for (let i =0; i < selectDong.length;i++) {
            selectDong[i].addEventListener("click", e => {
                if(document.querySelectorAll(".selectDong")[i].innerText.substring(0,document.querySelectorAll(".selectDong")[i].innerText.lastIndexOf("\n")) 
                    == locationContentArr[i]){
                    locationContentArr.splice(i,1);
                    const element =  document.querySelectorAll(".selectDong")[i].closest(".selectDong");
                    if(element){
                        element.parentNode.removeChild(element);
                    }
                }
            })
        }
    })
})


// const sidoId = document.querySelector("#sido");
// const sidoClass = document.querySelectorAll(".sido");
// const sigungu = {
//     "I000" : ["city1","city2","city3","city4","city5","city6","city7","city8","city9"],
//     "B000" : ["city10","city11","city12","city13","city14","city15","city16","city17","city18"],
//     "K000" : ["city19","city20","city21","city22","city23","city24","city25","city26","city27"],
//     "A000" : ["city28","city29","city30","city31","city32","city33","city34","city35","city36"],
//     "G000" : ["city37","city38","city39","city40","city41","city42","city43","city44","city45"],
//     "1000" : ["city46","city47","city48","city49","city50","city6","city7","city8","city9"],
//     "O000" : ["city1","city2","city3","city4","city5","city6","city7","city8","city9"],
//     "P000" : ["city1","city2","city3","city4","city5","city6","city7","city8","city9"],
//     "H000" : ["city1","city2","city3","city4","city5","city6","city7","city8","city9"],
//     "J000" : ["city1","city2","city3","city4","city5","city6","city7","city8","city9"],
//     "C000" : ["city1","city2","city3","city4","city5","city6","city7","city8","city9"], 
//     "D000" : ["city1","city2","city3","city4","city5","city6","city7","city8","city9"],
//     "F000" : ["city1","city2","city3","city4","city5","city6","city7","city8","city9"],
//     "E000" : ["city1","city2","city3","city4","city5","city6","city7","city8","city9"],
//     "L000" : ["city1","city2","city3","city4","city5","city6","city7","city8","city9"],
//     "M000" : ["city1","city2","city3","city4","city5","city6","city7","city8","city9"],
//     "N000" : ["city1","city2","city3","city4","city5","city6","city7","city8","city9"]
// };


// const sigunguId = document.querySelector("#sigungu");

// sidoId.addEventListener("change", e => {
//     console.log(sigungu[e.target.options[e.target.selectedIndex].value]);
//     let s =e.target.options[e.target.selectedIndex].value

//     sigunguId.replaceChildren();
    
//     for(let i =0; i<sigungu[s].length; i++){
//         const option = document.createElement("option");
//         option.classList.add("siguu"); 
//         option.innerHTML = sigungu[s][i];   
//         sigunguId.appendChild(option);
//     }
// })

//score validation
//점수 유효성 검사
const score = document.querySelector(".score");
score.addEventListener("input", () => {
    if(score.value > 100){
        alert("점수가 100보다는 클수가 없습니다");
        return;
    }
});


//경력사항
const newHire = document.querySelector("#newHire");
const experienced = document.querySelector("#experienced");
const career = document.querySelector("#career");
const experienceDetailDiv = document.querySelector(".experiencedDetail");
const nonBreakingSpace = document.createTextNode("\u00A0");



const experiencedDetail = document.querySelector(".experiencedDetail");
newHire.addEventListener("click", () => {
    experiencedDetail.classList.remove("show");
    experiencedDetail.classList.add("hide")


    newHire.classList.add("selectedBtn");
    experienced.classList.remove("selectedBtn");
    const input = document.createElement("input");
    input.setAttribute("name", "career");
    input.value=1;
    input.type="hidden";
    experiencedDetail.appendChild(input);

})




experienced.addEventListener("click", () => {

    experiencedDetail.classList.remove("hide");
    experiencedDetail.classList.add("show");

    experienced.classList.add("selectedBtn");
    newHire.classList.remove("selectedBtn");
    
    const input = document.createElement("input");
    input.setAttribute("name", "career");
    input.value=2;
    input.type="hidden";
    experiencedDetail.appendChild(input);
   

    
});
//경력 추가하기 버튼

const addExperience = () => {
    
    const experienceContainerDiv = document.createElement("div");
    experienceContainerDiv.classList.add("experiencedContainer");
    experienceDetailDiv.appendChild(experienceContainerDiv);

        const companyNameDiv = document.createElement("div");
        companyNameDiv.setAttribute("id","companyName");
        experienceContainerDiv.append(companyNameDiv);

            const companyNameSpan = document.createElement("span");
            companyNameSpan.innerHTML = "회사이름&nbsp;";
            companyNameDiv.appendChild(companyNameSpan);

            const inputCompanyName =  document.createElement("input");
            inputCompanyName.setAttribute("type","text");
            inputCompanyName.setAttribute("name","companyName");
            companyNameDiv.appendChild(inputCompanyName);

            career.appendChild(nonBreakingSpace);

        const dateInputDiv =  document.createElement("div");
        dateInputDiv.classList.add("dateInput");
        experienceContainerDiv.appendChild(dateInputDiv);

            const inputDate = document.createElement("input");
            const inputDate2 = document.createElement("input");

            

            inputDate.setAttribute("type","date");
            inputDate.setAttribute("name","startDate");
            inputDate2.setAttribute("type","date");
            inputDate2.setAttribute("name","endDate");
            dateInputDiv.appendChild(inputDate);
            dateInputDiv.append("~");
            dateInputDiv.appendChild(inputDate2);


            
            const currContainer = document.querySelectorAll(".experiencedContainer")
          
            const removeBtn = document.createElement("button");
            removeBtn.innerText = "-"
            removeBtn.classList.add("remove");
            removeBtn.setAttribute("type","button")
            dateInputDiv.appendChild(removeBtn);
}



const fifthResume = document.querySelector(".resumeElement:nth-child(15)")
const addCertificate = () => {
    
    const certificateDetailHTML = `
        <li><br><br>
            <div class="certificateDetail">
                <div class="organizationDetail">
                    <span>자격증명&nbsp;&nbsp;</span>
                    <input type="text" class="certName" name="licenseName">
                    <br><br>
                    <span>발행기관&nbsp;&nbsp;</span>
                    <input type="text" class="organization" name="licenseFrom">
                </div>


                <div class="scoreDetail">
                    <span>점수</span>&nbsp;&nbsp;
                    <input type="number" name="licenseScore" class = "score" max="100" >&nbsp;/100
                </div>
                                               
                <div class = "issueDetail">
                    <span>취득일</span>
                    <input type="date" name="issueDate" class="issueDate"  required>
                    <button type="button" class = "certMinus">-</button>
                </div>
            </div>
        </li><br><br>
    `;

    const ul = document.createElement("ul");
    ul.innerHTML = certificateDetailHTML;
    fifthResume.appendChild(ul);
};



//추가버튼 작성
const chuga = document.querySelector("#chuga");
chuga.addEventListener("click" , () => {
    addExperience();
    //삭제버튼 작성
    const removeBtns = document.querySelectorAll(".remove");
    for(let i = 0; i < removeBtns.length; i++){
        removeBtns[i].addEventListener("click", () => {
            const container = removeBtns[i].closest(".experiencedContainer");
            if(container){
                container.parentNode.removeChild(container);
            }
        });
    }
});

//삭제버튼 작성
    const removeBtns = document.querySelectorAll(".remove");

    // removeBtns.forEach(btns => {
    //     btns.addEventListener("click", () => {
    //         const container = btns.closest(".experiencedContainer");
    //         if (container) {
    //             container.parentNode.removeChild(container);
    //         }
    //     })
        
    //  })
    for(let i = 0; i<removeBtns.length; i++){
        removeBtns[i].addEventListener("click", () => {
            const container =  removeBtns[i].closest(".experiencedContainer");
            container.parentNode.removeChild(container);
        })
    }

//자격증 추가 버튼 작성
const certAdd = document.querySelector("#certAdd");

certAdd.addEventListener("click", () => {
    addCertificate();
    //삭제버튼 작성
    const removeBtns = document.querySelectorAll(".certMinus");
    for(let i = 0; i < removeBtns.length; i++){
        removeBtns[i].addEventListener("click", () => {
            const container = removeBtns[i].closest("ul");
            if(container){
                container.parentNode.removeChild(container);
                fifthResume.removeChild(document.createElement("hr"))
            }
        });
    }
});


const jobsOfDesireBtn =  document.querySelectorAll(".jobsOfDesireBtn");
const addDesiredJobs =  document.querySelector("#addDesiredJobs");


//희망 정직 선택하기(최대 5개)
let textContentArr = [];
jobsOfDesireBtn.forEach(btn => {
    btn.addEventListener("click", () => {
    
    
        for(let i=0; i<textContentArr.length; i++){
            if(btn.textContent == textContentArr[i]){
                alert("중복 선택은 가능하지 않습니다");
                return;
            }
        } 
        


        if(document.querySelectorAll(".addJobCategory>span").length > 4){
            alert("최대 5가지만 선택할 수 있습니다");
            return;
        }
        textContentArr.push(btn.textContent);
        const jobinput = document.createElement("input");
        jobinput.setAttribute("type","hidden");
        jobinput.setAttribute("name","typeName");
        jobinput.setAttribute("value",btn.textContent);
        addDesiredJobs.appendChild(jobinput);
        const addJobDiv = `
            <span class = "addJobCategory">
                ${btn.textContent}
                <span>&times;</span>
            </span>`;

        addDesiredJobs.innerHTML += addJobDiv;

        const addJobCategory = document.querySelectorAll(".addJobCategory>span");
        for (let i =0; i < addJobCategory.length;i++) {
            addJobCategory[i].addEventListener("click", e => {
                if(document.querySelectorAll(".addJobCategory")[i].innerText.substring(0,document.querySelectorAll(".addJobCategory")[i].innerText.lastIndexOf("\n")) == textContentArr[i]){
                    textContentArr.splice(i,1);
                    const element =  document.querySelectorAll(".addJobCategory")[i].closest(".addJobCategory");
                    if(element){
                        element.parentNode.removeChild(element);
                    }
                }
            })
        }
    })
})

const editProfile = document.querySelector("#editProfile");
const container = document.querySelector(".container");
const cancel = document.querySelector("#cancel");
const update = document.querySelector("#update");
editProfile.addEventListener("click",() => {
    container.classList.remove("hide");
    container.classList.add("show");
    document.body.style.overflowY = "hidden"
})

cancel.addEventListener("click", () => {
    container.classList.remove("show");
    container.classList.add("hide");
    document.body.style.overflowY = "auto"
})
const picFile = document.querySelector("#picFile");
    const setImg =  document.querySelector("#profileImg");

    
    update.addEventListener("click",e => {
       
        const file = picFile.files[0];
        if(file) {
            const reader = new FileReader();
            reader.onload = function(e)  {
            const img = e.target.result;
            // const innerhtml = `<img src="${img}" alt="Upload File">` 
            // setImg.innerHTML = innerhtml;
            setImg.style.backgroundImage = `url(${img})`;
            setImg.style.backgroundSize = "150%";
            setImg.style.backgroundRepeat = "no-repeat";
            setImg.style.backgroundPosition = "left"; 
        }

        reader.readAsDataURL(file);
        container.classList.remove("show");
    container.classList.add("hide");
    document.body.style.overflowY = "auto"

        }
        

    })

    const validatingBtnsContainer = document.querySelector("#validatingBtnsContainer");
    //저장 버튼
    const applyBtn = document.querySelector("#applyBtn");
    applyBtn.addEventListener("click", () => {
        const input = document.createElement("input");
        input.setAttribute("type","hidden");
        input.setAttribute("name","resumeStatus");
        input.value=0;
        validatingBtnsContainer.append(input);
    });

    //임시저장 버튼
    const semiBtn = document.querySelector("#semiBtn");
    semiBtn.addEventListener("click", () => {
        const input = document.createElement("input");
        input.setAttribute("type","hidden");
        input.setAttribute("name","resumeStatus");
        input.value=1;
        validatingBtnsContainer.append(input);
    });

