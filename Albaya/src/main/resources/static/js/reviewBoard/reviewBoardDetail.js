const siren = document.querySelector("#siren");

siren.addEventListener("click",() =>{

    function openPopup(){
        window.open('declarePopup.html', 'popupWindow', 'width=700 ,height=750, resizable=no, scrollbars=yes');
        window.resizeTo(400,750);
    }
    openPopup();



});