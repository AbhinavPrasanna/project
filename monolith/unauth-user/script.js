/////////////////////////////
//Toggle filter button color
/////////////////////////////


toggleColor = (currBut) => {
    let tog = document.querySelector(currBut);
    if (tog.style.color != 'white') {
        tog.style.background = '#029a9a';
        tog.style.color = 'white';
    }
    else {
        tog.style.background = 'transparent';
        tog.style.color = 'rgba(0, 0, 0, 0.6)';
    }
}
