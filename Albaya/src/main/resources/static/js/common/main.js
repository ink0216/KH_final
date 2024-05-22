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