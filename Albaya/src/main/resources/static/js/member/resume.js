
//최종학력 선택(고등학교/대학교) 선택시 팝업

    const dropDown = document.querySelector("#dropDown");

    const selectPrimarySchoolName = document.querySelector("#selectPrimarySchoolName");
    const selectMiddleSchoolName = document.querySelector("#selectMiddleSchoolName");
    const selectSchoolName = document.querySelector("#selectSchoolName");
    const selectUniversityName = document.querySelector("#selectUniversityName");

    const obj = {
        "image": false,
        "resumeTitle":false,
        "introduce" : false,
        "schoolName" : false,
        "desiredJobs": false,
        "location" : false,
        "experienced" : false,
        "certificate" : false,
    }
    

    

    const eduObj = {
        "1":"selectPrimarySchoolName", //초
        "2":"selectMiddleSchoolName", //중
        "3":"selectSchoolName", //고
        "4":"twoUniversityName", //2,3년제
        "5":"fourUniversityName", //4년제
        "6":"graduateUniversityName" //대학원 이상
    };
    //input:date 요소 유효성 검사
    const date = new Date();
    const year = String(date.getFullYear());
    const month = String(date.getMonth()+1).padStart(2,0);  
    
    const day = String(date.getDate()).padStart(2,0);   
   
    const formattedDate = `${year}-${month}-${day}`;


    dropDown.addEventListener("change", () => {
        let selectedVal = dropDown.value;
        if (selectedVal == "선택") {
            obj.schoolName = false;
            return;
        }
    
        for (let key in eduObj) {
            let element = document.querySelector("#" + eduObj[key]);
            if (element) {
                if (selectedVal == key) {
                    element.classList.add("show");
                    element.classList.remove("hide");
                    const show = document.querySelector("#schoolContainer");
                    const showInput = show.querySelectorAll(".show input");
    
                    // Set up input event listeners for validation
                    showInput.forEach(input => {
                        input.addEventListener("input", () => {
                            // Check all inputs each time any input changes
                            let allFilled = Array.from(showInput).every(input => input.value.trim().length > 0);
                            if (allFilled) {
                                obj.schoolName = true;
                                
                            } else {
                                obj.schoolName = false;
                            }
                            console.log("School name validation status:", obj.schoolName);
                            
                        });
                        let isGreater = false
                        if(input.type==="date"&&input.className === "startDate"){
                            input.addEventListener("input", e => {
                                isGreater =e.target.value > formattedDate;
                                if(!isGreater){
                                    alert("올바른 날짜 형식 입니다");
                                    isGreater = true;
                                    return;
                                }
                                
                                alert("시작 날짜가 오늘 날짜보다 클수 없습니다");
                                    e.target.value="";
                                    obj.schoolName=false;
                                    
                                
                            })
                        }
                        if(input.type==="date"&&input.className === "endDate"){
                            input.addEventListener("input", e => {
                                isGreater =e.target.value > document.querySelector('input[type="date"].startDate').value;
                                if(!isGreater){
                                    alert("끝나는 날짜가 시작하는 날짜보다 작을수 없습니다");
                                    e.target.value="";
                                    obj.schoolName=false;
                                    return;
                                }
                                isGreater = true;
                                
                            })
                        }
                    });
    
                    // Initial check in case inputs are already filled
                    let initialAllFilled = Array.from(showInput).every(input => input.value.trim().length > 0);
                    obj.schoolName = initialAllFilled;
                    console.log("Initial school name validation status:", obj.schoolName);
    
                } else {
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
    
    
    const locationSelectContainer =  document.querySelector(".locationSelectContainer");
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
                            const form =document.querySelector("form")
                            fetch("/hire/selectDong?dosiName="+dosiName+"&sigunguName="+sigunguName)
                            .then(resp=>resp.json())
                            .then(
                                list=>{
                                    console.log(list)
                                    
                                    list.forEach(dongItem=>{
                                       
                                        const dongdiv = document.createElement("div");
                                        dongdiv.innerHTML = dongItem.dongName;
                                        dongdiv.classList.add("dongs");
                                        dong.append(dongdiv);
                                        console.log(dongItem.dongNo)
                                        let count = 0;
                                        dongdiv.addEventListener("click",()=>{

                                            // 화면에 보여지는 동 요소2
                                            obj.location = true;
                                            count++;

                                            const input = document.createElement("input");
                                            input.type = "hidden";
                                            input.name = "dongNo";
                                            input.value = dongItem.dongNo;
                                            form.append(input);
                                           

                                        
                                            if(count>1){
                                                 alert("중복 선택은 가능하지 않습니다");
                                                return;
                                            }
                                            
                                            if(document.querySelectorAll(".selectDong").length > 4){
                                                alert("최대 5가지만 선택할 수 있습니다");
                                                return;
                                            } 



                                            const dongName = document.createTextNode(`${dongItem.dongName}`);
        
                                                    
                                            const selectDong = document.createElement("span");
                                            selectDong.className = "selectDong";
                                                  

                                            const xBtn = document.createElement("span");
                                            xBtn.innerHTML = "&times;"; 
                                            xBtn.addEventListener("click", () => {
                                                count--;
                                            selectDong.remove();
                                                input.remove();
                                            });

                                            const hide = document.createElement("span");
                                            hide.className = "hide";
                                            hide.innerText = `${dongItem.dongNo}`; 
                                                 
                                            selectDong.appendChild(dongName);
                                            selectDong.appendChild(xBtn);
                                            selectDong.appendChild(hide); 

                                            locationSelectContainer.append(selectDong);
                                                  
                                                  
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

// const dongs = document.querySelectorAll(".dong");

// const selectCont = document.querySelectorAll(".selectDong");


// dongs.forEach(btn => {
//     btn.addEventListener("click", () => {
       
       
//         for(let i=0; i<locationContentArr.length; i++){
//             if(btn.textContent == locationContentArr[i]){
//                 alert("중복 선택은 가능하지 않습니다");
//                 return;
//             }
//         } 
//         // locationContentArr.push(btn.textContent);


//         if(document.querySelectorAll(".selectDong>span").length > 5){
//             alert("최대 5가지만 선택할 수 있습니다");
//             return;
//         }

//         const selectLocationInnerHtml = `
//             <span class = "selectDong">
//                 ${btn.textContent}
//                 <span>&times;</span>
//             </span>`;

//         locationSelectContainer.innerHTML += selectLocationInnerHtml;

        
//     })
// })


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

    obj.experienced = true;

    
});
if(newHire.classList.contains("selectedBtn")){
    obj.experienced = true;
   
}
    







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
    validateExperienced();
    if(!newHire.classList.contains("selectedBtn")){
        obj.experienced = false;
        return;
    }
    
});



//경력 추가하기 버튼

const addExperience = () => {
    
    const experienceContainerDiv = document.createElement("div");
    experienceContainerDiv.classList.add("experiencedContainer");
    experienceDetailDiv.appendChild(experienceContainerDiv);

        const companyNameDiv = document.createElement("div");
        companyNameDiv.className = "companyNames";
        experienceContainerDiv.append(companyNameDiv);

            const companyNameSpan = document.createElement("span");
            companyNameSpan.innerHTML = "회사이름&nbsp;";
            companyNameDiv.appendChild(companyNameSpan);

            const inputCompanyName =  document.createElement("input");
            inputCompanyName.setAttribute("type","text");
            inputCompanyName.className = "companyName";
            companyNameDiv.appendChild(inputCompanyName);

            career.appendChild(nonBreakingSpace);

        const dateInputDiv =  document.createElement("div");
        dateInputDiv.className = "dateInput";
        experienceContainerDiv.appendChild(dateInputDiv);

            const inputDate = document.createElement("input");
            const inputDate2 = document.createElement("input");

            

            inputDate.setAttribute("type","date");
            inputDate.className ="startDate";
            inputDate2.setAttribute("type","date");
            inputDate2.className = "endDate";
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
let count=0;    
const validateExperienced = () => {
    
const experiencedContainer = document.querySelectorAll(".experiencedContainer>div>input");
        let count=0;    
       
        let allFilled = false;
        function checkAllInputs(container) {
            const inputs = container.querySelectorAll("input");

            allFilled = Array.from(experiencedContainer).every(input => input.value.trim().length > 0);
            if (allFilled) {
                count=inputs.length;
                console.log(count);
            }
            if (count === inputs.length) {
                obj.experienced = true;
            } else {
                obj.experienced = false;
            }
        }
        
        experiencedContainer.forEach(input => {
            input.addEventListener("input", () => {
                if (input.value.trim().length === 0) {
                    allFilled = false;
                    input.value = "";
                    obj.experienced = false;
                    return;
                }
        
               
                checkAllInputs(input.closest(".experiencedContainer"));
        
                if (input.type === "text" && input.value.length > 1) {
                    return;
                }
                
                
            });
        });
        
        
    
};

const fifthResume = document.querySelector(".resumeElement:nth-child(17)")
const addCertificate = () => {
    const certificateContainer = document.createElement('li');

    certificateContainer.innerHTML = `
        <br><br>
        <div class="certificateDetail">
            <div>
                <span>자격증명&nbsp;&nbsp;</span>
                <input type="text" class="certName" name="licenseName">
                <br><br>
            </div>
            <div class="organizationDetail">
                <span>발행기관&nbsp;&nbsp;</span>
                <input type="text" class="organization" name="licenseFrom">
            </div>
            <div class="scoreDetail" >
                <span>점수</span>&nbsp;&nbsp;
                <input type="number" name="licenseScore" class="score" max="100">&nbsp;/100
            </div>
            <div class="issueDetail">
                <span>취득일</span>
                <input type="date" name="licenseDate" class="issueDate" required>
                <button type="button" class="certMinus">-</button>
            </div>
        </div>
        <br><br>
    `;
    certificateContainer.style.marginLeft="40px";
    certificateContainer.style.listStyle="none";


    
    
    fifthResume.appendChild(certificateContainer);

   
    const inputs = certificateContainer.querySelectorAll("input");
    inputs.forEach(input => {
        input.addEventListener("input", () => {
            checkCertInputs(certificateContainer.querySelector(".certificateDetail"));
        });
    });

    
    const removeBtn = certificateContainer.querySelector(".certMinus");
    removeBtn.addEventListener("click", () => {
        const container = removeBtn.closest("li");
        if (container) {
            const hr = container.nextElementSibling; 
            if (hr && hr.tagName === 'HR') {
                hr.parentNode.removeChild(hr); 
            }
            container.parentNode.removeChild(container); 
            checkAllCertificates(); 
        }
    });

   
    checkCertInputs(certificateContainer.querySelector(".certificateDetail"));
};



//추가버튼 작성
const chuga = document.querySelector("#chuga");
chuga.addEventListener("click" , () => {
    addExperience();
    validateExperienced();

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



const jobsOfDesireBtn =  document.querySelectorAll(".jobsOfDesireBtn");
const addDesiredJobs =  document.querySelector("#addDesiredJobs");


//희망 정직 선택하기(최대 5개)

let textContentArr = [];
jobsOfDesireBtn.forEach(btn => {
    let count = 0;
    btn.addEventListener("click", (e) => {
        

        count++;
        obj.desiredJobs=true;
       
        if(count>1){
            alert("중복 선택은 가능하지 않습니다");
            return;
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
       
        const span = document.createElement("span");
        span.className = "addJobCategory";
        span.createTextNode = btn.textContent;

        const times =  document.createElement("span");
        const timesTextNode = document.createTextNode('\u00D7');

        span.append(span.createTextNode);
        times.append(timesTextNode);
        span.append(times);

        const input = document.createElement("input");
        input.type = "hidden";
        input.name = "dongNo";
        input.className = "hidden"
        input.value = btn.textContent;
        addDesiredJobs.append(input);

        addDesiredJobs.append(span);   
        // if(input.value == btn.textContent.substring(0,span.textContent.lastIndexOf("×"))){
        //     span.remove();
        //     input.remove();
        // }

        const removeBtn =  document.querySelectorAll(".addJobCategory>span");
        times.addEventListener("click",() => {
            if(input.value == btn.textContent.substring(0,span.textContent.lastIndexOf("×"))){
                count--;
                span.remove();
                input.remove();
        }
        })
        obj.desiredJobs=true;
    })

    
    
});






//사진 추가하기
const editProfile = document.querySelector("#editProfile");
const container = document.querySelector(".container");
const cancel = document.querySelector("#cancel");
const update = document.querySelector("#update");
editProfile.addEventListener("click",() => {
    container.classList.remove("hide");
    container.classList.add("show");
    document.body.style.overflowY = "hidden"
    document.body.style.overflowX = "hidden"
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
            setImg.style.backgroundSize = "150% auto";
            setImg.style.backgroundRepeat = "no-repeat";
            setImg.style.backgroundPosition = "center"; 
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
    const form  = document.querySelector("#form");
    applyBtn.addEventListener("click", () => {
        const input = document.createElement("input");
        input.setAttribute("type","hidden");
        input.setAttribute("name","resumeStatus");
        input.value=0;
        validatingBtnsContainer.append(input);
        
        
    /* const obj = {
        "image": false,
        "resumeTitle":false,
        "introduce" : false,
        "schoolName" : false,
        "desiredJobs": false,
        "experienced" : false,
        "certificate" : false
    } */
    

        for(let key in obj ){
            if(!obj[key]){
                let str;
                switch(key){
                    case "image": str="사진을 입력해주세요"; break;
                    case "resumeTitle": str="이력서 제목을 입력해주세요"; break;
                    case "introduce": str="자기소개서를 작성해주세요"; break;
                    case "schoolName": str="학력을 입력해주세요"; break;
                    case "desiredJobs": str="희망 업직종을 선택해주세요"; break;
                    case "experienced": str="경력을 입력해주세요"; break;
                    case "certificate": str="자격증을 입력해주세요"; break;
                }
                alert(str);
                return;
            }
        }
        form.submit();
    });

    //임시저장 버튼
    const semiBtn = document.querySelector("#semiBtn");
    semiBtn.addEventListener("click", () => {
        const input = document.createElement("input");
        input.setAttribute("type","hidden");
        input.setAttribute("name","resumeStatus");
        input.value=1;
        validatingBtnsContainer.append(input);
        form.submit();
    });






/***********  유효성 검사 ***********/

const introduce = document.querySelector(".introduce");
const inputElement = document.querySelectorAll("input");
const startDate = document.querySelectorAll(".startDate");
const endDate = document.querySelectorAll(".endDate");





//사진 유효성 검사
picFile.addEventListener("input", e => {
    if(e.target.value.length === 0){
        obj.image = false;
        return;
    }
    obj.image=true;
});


//이력서 제목 유효성 검사
const resumeTitle = document.querySelector(".resumeTitle");
resumeTitle.addEventListener("input", () => {
    if(resumeTitle.value.trim().length === 0){
        resumeTitle.value = "";
        obj.resumeTitle=false;
        return;
    }
    obj.resumeTitle = true;
})

//input:text 요소 유효성 검사








 
if(document.querySelectorAll(".selectDong").length!=0){
    obj.location=true;
}
//textArea 사이즈 유효성 검사
introduce.addEventListener("input", () => {
    if(introduce.value.trim().length === 0){
        introduce.value = "";
        obj.introduce=false;
        return;
    } 
    obj.introduce=true;    
})
introduce.addEventListener("mousedown", () => {
        introduce.addEventListener("mousemove", () => {
           if(introduce.clientWidth>=700 || introduce.clientHeight>=500){
            introduce.style.resize = "none";
           }
    });
});

//certificate 유효성 검사
let count1 = 0;

// Function to check if all inputs in a container are filled
const checkCertInputs = (container) => {
    const inputs = container.querySelectorAll("input");
    let allFilled = Array.from(inputs).every(certInput => certInput.value.trim().length > 0);

    if (allFilled) {
        count1 = inputs.length;
    } else {
        count1 = 0; // Reset count if any input is not filled
    }

    if (count1 === inputs.length) {
        obj.certificate = true;
    } else {
        obj.certificate = false;
    }

    console.log("Certificate validation status:", obj.certificate);
};


const checkAllCertificates = () => {
    const allContainers = document.querySelectorAll(".certificateDetail");
    let allValid = true;

    allContainers.forEach(container => {
        const inputs = container.querySelectorAll("input");
        const allFilled = Array.from(inputs).every(certInput => certInput.value.trim().length > 0);
        if (!allFilled) {
            allValid = false;
        }
    });

    obj.certificate = allValid;
    console.log("Overall certificate validation status:", obj.certificate);
};

// Initial event listener for the "Add Certificate" button
const certAdd = document.querySelector("#certAdd");
certAdd.addEventListener("click", () => {
    addCertificate();
});

// Attach input event listeners to existing certificate inputs (if any)
const certificateDetailInputs = document.querySelectorAll(".certificateDetail>div>input");

certificateDetailInputs.forEach(certInput => {
    certInput.addEventListener("input", () => {
        checkCertInputs(certInput.closest(".certificateDetail"));
    });
});























