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





/* 페이지네이션 */



const countPrePage = 10; //한 페이지 당 보여지는 최대 리스트 수

const getTotalPageCount=()=>{
    return Math.ceil(100/countPrePage);
}

const numberButtonWrapper = document.querySelector(".number-button-wrapper");

const setPageButtons=()=>{
    numberButtonWrapper.innerHTML=''; 

    for(let i=1; i<getTotalPageCount(); i++){
        numberButtonWrapper.innerHTML = 
        `<span class="number-button">${i}</span>`
    }
}


/* 해당 페이지 */
const tbody= document.querySelector("tboay");
let currentPage =1;

const setPageOf=(pageNumber)=>{

    for(
        let i = countPrePage * (pageNumber -1)+1;
        i <= countPrePage * (pageNumber -1) + 6 && i <= 100;
        i++
    ){
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

        tr.append(shopName,hireTitle,hireTime,sigunguName,pay);
        tbody.append(tr);
    }

}





/* 숫자 버튼을 눌렀을 때 */
const pageNumberButtons = document.querySelectorAll('.number-button');

pageNumberButtons.forEach((numberButton)=>{
    numberButton.addEventListener('click',(e)=>{
        setPageOf(e.target.innerHTML);
    })
})