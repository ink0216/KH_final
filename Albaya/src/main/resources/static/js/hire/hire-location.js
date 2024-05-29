/////////////////////////////////////////////////////////////////////
const tbody= document.querySelector(".tbody"); //tbody
const numberButtonWrapper = document.querySelector('.number-button-wrapper'); //숫자 버튼 묶음

//****************가져온 공고문 페이징 번호에 따라 조회하기*****************//
const setPageOf=(hireList)=>{

    for(let hire of hireList){ //전체 공고 수 세기

        const tr = document.createElement('tr');
        tr.className ='tr';

        //상점명
        const shopName = document.createElement('td');
        shopName.className='shopName';

        //공고 제목
        const hireTitle = document.createElement('td');
        hireTitle.className='hireTitle';

        //근무 시간
        const hireTime = document.createElement('td');
        hireTime.className='hireTime';

        //근무 지역
        const sigunguName = document.createElement('td');
        sigunguName.className='sigunguName';

        //급여
        const pay = document.createElement('td');
        pay.className='pay';

        shopName.textContent = hire.shopName;
        hireTitle.textContent=hire.hireTitle;
        hireTime.textContent=hire.hireTime;
        sigunguName.textContent=hire.sigunguName;
        pay.textContent=hire.pay;


        tr.append(shopName,hireTitle,hireTime,sigunguName,pay);
        tbody.append(tr);
    
    }
}



//*************************페이지 수를 세서 페이징 번호 생성****************************//
const getPagination =(pagination)=>{ 

    for(let i= pagination.startPage ; i <= pagination.endPage ; i++){

        const button = document.createElement('button');
        button.className='number-button';

        button.textContent=i;

        //******페이징 버튼이 눌렸을 경우***********//
        button.addEventListener("click",e=>{
            console.log(button + "클릭됨");
            reloadTable(e.target.innerHTML);
        })

        if(i == pagination.currentPage){
            button.classList.add("checked");
        }

        numberButtonWrapper.append(button);
    }

    const prevButton = document.querySelector('.prev-button');
    prevButton.dataset.pageNo = pagination.prevPage;

    const nextButton = document.querySelector('.next-button');
    nextButton.dataset.pageNo = pagination.nextPage;
}



//******이전 버튼이 눌렸을 경우***********//
const prevButton = document.querySelector('.prev-button');
prevButton.addEventListener("click",(e)=>{
    reloadTable(e.target.dataset.pageNo);
})

//******다음 버튼이 눌렸을 경우***********//
const nextButton = document.querySelector('.next-button');
nextButton.addEventListener("click",(e)=>{
    reloadTable(e.target.dataset.pageNo);
})





function reloadTable(cp) {
    fetch("/hire/selectHireList?cp=" + cp)
    .then(resp=>resp.json())
    .then(map => {
        console.log(map);
        const {hireList, pagination} = map;

        tbody.innerHTML='';
        numberButtonWrapper.innerHTML="";

        setPageOf(hireList);
        getPagination(pagination);
    })
}

//새로고침 되었을 때
document.addEventListener("DOMContentLoaded", () => {
    reloadTable(1);
});








/* *************************************************************** */


const dosiNameList = document.querySelectorAll('.dosiName');
const sigunguBody = document.querySelector('.sigungu-body');
const dongBody = document.querySelector('.dong-body');
const searchLocations = document.querySelector('.search-locations');

dosiNameList.forEach(dosiName=>{

    dosiName.addEventListener("click",e=>{

        const dosi = e.target.innerHTML;
        fetch("/hire/selectSigungu?dosiName="+dosi)
        .then(resp=>resp.json())
        .then(list=>{


/***************************시군구 얻어와 화면 만들기 ****************************/
            sigunguBody.innerHTML='';

            console.log(list);

            list.forEach(sigunguItem=>{

                const ul = document.createElement('ul');
                ul.classList.add('ul');

                const li = document.createElement('li');
                li.classList.add('li');

                const sigunguBtn = document.createElement('button');
                sigunguBtn.classList.add('sigunguName');
                sigunguBtn.innerHTML=sigunguItem.sigunguName;

                li.append(sigunguBtn);
                ul.append(li);
                sigunguBody.append(ul);

/* ******************************************************** */



/* *********************동읍면 얻어와 화면 구현하기 ****************************/
                sigunguBtn.addEventListener("click",e=>{

                    const sigungu = e.target.innerHTML;

                    fetch("/hire/selectDong?sigunguName="+sigungu)
                    .then(resp=>resp.json())
                    .then(list=>{

                        dongBody.innerHTML="";
                        
                        console.log(list);

                        list.forEach(dongItem=>{
                            const ul = document.createElement('ul');
                            ul.classList.add('ul2');
        
                            const li = document.createElement('li');
                            li.classList.add('li2');
        
                            const dongBtn = document.createElement('button');
                            dongBtn.classList.add('dongName');
                            dongBtn.innerHTML=dongItem.dongName;                       
        
                            li.append(dongBtn);
                            ul.append(li);
                            dongBody.append(ul);
/* ********************************************************************************* */




/* ********************************동 선택했을 때************************************ */

                            dongBtn.addEventListener("click", e=>{

                                const locationItems = document.querySelectorAll('.location-item');
                                const dongName = document.querySelectorAll('.dong');

                                if(locationItems.length>=5){
                                    alert('희망하는 지역은 최대 5개까지 입력 가능합니다');
                                    return;
                                }

                                for(let i =0; i<dongName.length; i++){
                                    if(dongBtn.innerHTML==dongName[i].innerHTML){
                                        alert("중복되는 지역입니다.");
                                        return;
                                    }
                                }

                                const locationItem = document.createElement("div");
                                locationItem.classList.add('location-item');
                        
                                const p = document.createElement('p'); //p태그
                                p.innerHTML=e.target.innerHTML;
                                p.classList.add("dong");
                        
                                const button = document.createElement('button'); //button
                                button.innerHTML="x";
                                button.classList.add("del-btn");
                        
                                locationItem.append(p,button);
                                searchLocations.append(locationItem);
                        
                                // x버튼 눌렸을 때
                                button.addEventListener("click",()=>{
                                    locationItem.remove();
                                })
                                
                        })

        /* ************************************************************************ */



                        })

                        
                    })
                })
            })

        })
        // console.log(dosi);
    })
})

