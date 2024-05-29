

// 감싸는 요소
const imgList = document.querySelector(".imgList");
const imgListList = document.querySelector(".imgList > .list");

// 요소 기본 너비
const elWidth = document.querySelector(".imgItem").clientWidth + 2;

// margin
const elMargin = 40;

// 지정된 요소/브라우저의 화면 크기 변경이 감지되었을 때
const observer = new ResizeObserver(entries => {
    for (let entry of entries) {
        const {width, height} = entry.contentRect;
        // console.log(`너비: ${width} 높이: ${height}`);

        let resultWidth = elWidth + elMargin;

        while(resultWidth < width){
            // console.log(resultWidth, width);
            
            resultWidth += elWidth + elMargin;

            if(resultWidth > width){
                resultWidth -= elWidth + elMargin;
                break;
            } 
            
        }
        // console.log("resultWidth : ", resultWidth);
        imgListList.style.width = resultWidth + 'px';
    }
});


// imgListList 크기 변경 감지하도록 추가
observer.observe(imgList)











/////////////////////////////////////////////////////////////////////
const tbody= document.querySelector("tbody"); //tbody
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
        sigunguName.className='sigungu-name';

        //급여
        const pay = document.createElement('td');
        pay.className='pay';

        shopName.textContent = hire.shopName;
        hireTitle.textContent=hire.hireTitle;
        hireTime.textContent=hire.workStart + " ~ " + hire.workEnd;
        sigunguName.textContent=hire.sigunguName;
        pay.textContent=hire.payType+" "+hire.payInput;


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



// <!-- document.querySelector(".imgItem").dataset.hireNo 해서 꺼내 쓸 수 있다.!!!!! -->
const imgItem = document.querySelectorAll(".imgItem");
imgItem.forEach(i=>{
    i.addEventListener("click", ()=>{
        let hireNo = i.dataset.hireNo;
        console.log(hireNo);
        location.href='/hire/'+hireNo;
    })
});
