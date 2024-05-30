
//최종학력 선택(고등학교/대학교) 선택시 팝업

    const dropDown = document.querySelector("#dropDown");

    const selectPrimarySchoolName = document.querySelector("#selectPrimarySchoolName");
    const selectMiddleSchoolName = document.querySelector("#selectMiddleSchoolName");
    const selectSchoolName = document.querySelector("#selectSchoolName");
    const selectUniversityName = document.querySelector("#selectUniversityName");

    

    const eduObj = {
        "1":"selectPrimarySchoolName",
        "2":"selectMiddleSchoolName",
        "3":"selectSchoolName",
        "4":"selectUniversityName"
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


//city dropdown
const sidoId = document.querySelector("#sido");
const sidoClass = document.querySelectorAll(".sido");
const sigungu = {
    "I000" : ["city1","city2","city3","city4","city5","city6","city7","city8","city9"],
    "B000" : ["city10","city11","city12","city13","city14","city15","city16","city17","city18"],
    "K000" : ["city19","city20","city21","city22","city23","city24","city25","city26","city27"],
    "A000" : ["city28","city29","city30","city31","city32","city33","city34","city35","city36"],
    "G000" : ["city37","city38","city39","city40","city41","city42","city43","city44","city45"],
    "1000" : ["city46","city47","city48","city49","city50","city6","city7","city8","city9"],
    "O000" : ["city1","city2","city3","city4","city5","city6","city7","city8","city9"],
    "P000" : ["city1","city2","city3","city4","city5","city6","city7","city8","city9"],
    "H000" : ["city1","city2","city3","city4","city5","city6","city7","city8","city9"],
    "J000" : ["city1","city2","city3","city4","city5","city6","city7","city8","city9"],
    "C000" : ["city1","city2","city3","city4","city5","city6","city7","city8","city9"], 
    "D000" : ["city1","city2","city3","city4","city5","city6","city7","city8","city9"],
    "F000" : ["city1","city2","city3","city4","city5","city6","city7","city8","city9"],
    "E000" : ["city1","city2","city3","city4","city5","city6","city7","city8","city9"],
    "L000" : ["city1","city2","city3","city4","city5","city6","city7","city8","city9"],
    "M000" : ["city1","city2","city3","city4","city5","city6","city7","city8","city9"],
    "N000" : ["city1","city2","city3","city4","city5","city6","city7","city8","city9"]
};


const sigunguId = document.querySelector("#sigungu");

sidoId.addEventListener("change", e => {
    console.log(sigungu[e.target.options[e.target.selectedIndex].value]);
    let s =e.target.options[e.target.selectedIndex].value

    sigunguId.replaceChildren();
    
    for(let i =0; i<sigungu[s].length; i++){
        const option = document.createElement("option");
        option.classList.add("siguu"); 
        option.innerHTML = sigungu[s][i];   
        sigunguId.appendChild(option);
    }
})

//score validation
//점수 유효성 검사
const score = document.querySelector("#score");
score.addEventListener("input", () => {
    if(score.value > 100){
        alert("점수가 100보다는 클수가 없습니다");
        return;
    }
});

