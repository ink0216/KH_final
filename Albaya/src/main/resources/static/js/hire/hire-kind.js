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
/* ******************************업직종 선택하기********************************* */
const itemBtn = document.querySelectorAll(".item-btn"); /* 업직종 버튼 */
const searchType = document.querySelector('.search-type'); /* 클릭된 버튼들에 대해 생긴 p태그가 들어갈 공간 */


itemBtn.forEach(typeBtnItem=>{

   

    typeBtnItem.addEventListener("click",e=>{
        const typeName = e.target.innerText;
        const kindItems = document.querySelectorAll('.kindItem');


         /* 5개 이상 선택된 경우 */
        if(kindItems.length>=5){
            alert('희망하는 지역은 최대 5개까지 입력 가능합니다');
            return;
        }


        /* 중복된 업직종이 선택된 경우 */
        for(let i =0; i<kindItems.length; i++ ){
            if( kindItems[i].children[0].innerText == typeName){
                alert('중복된 업직종입니다.');
                return;
            }
        }


        const div = document.createElement('div');
        div.classList.add("kindItem");

        const p = document.createElement('p');
        p.classList.add("kind-name");
        p.innerText=typeName;
        
        const button = document.createElement('button');
        button.classList.add('del-btn');
        button.innerHTML='x';

        div.append(p,button);
        searchType.append(div);

        /* x버튼을 누른 경우 */
        button.addEventListener('click',()=>{
            div.remove();
        })
        })

})



