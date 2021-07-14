function initiate(){
content = document.getElementById('MainContainer');
var book1 = document.getElementById('BC');
var book2 = document.getElementById('DI');
var book3 = document.getElementById('LE');
var book4 = document.getElementById('PF');
var book5 = document.getElementById('RB');
var book6 = document.getElementById('TJ');
var book7 = document.getElementById('Book');
book1.addEventListener('click', readBookForm, false);
book2.addEventListener('click', readBookForm, false);
book3.addEventListener('click', readBookForm, false);
book4.addEventListener('click', readBookForm, false);
book5.addEventListener('click', readBookForm, false);
book6.addEventListener('click', readBookForm, false);
book7.addEventListener('click', readBookForm, false);
}
function readBookForm(){
var url = "Additional_Files/BookForm.html";
var request = new XMLHttpRequest();
request.addEventListener('load', showContent, false);
request.open("GET", url, true);
request.send();
CalcExpDate();
}

//function showContent() to add data into your
function showContent(e){
//add data to secContent
content.innerHTML = e.target.responseText;
}
//use the listener to load your initiate() function
window.addEventListener('load', initiate, false);