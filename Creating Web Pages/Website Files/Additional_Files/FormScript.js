function initiate(){
content = document.getElementById('Supp_Info');
var SubR = document.getElementById('SubmitR');
var SubQ = document.getElementById('SubmitQ');
SubR.addEventListener('click', readSubmitQ, false);
SubQ.addEventListener('click', readSubmitQ, false);
}
function readSubmitQ(){
var url = "Additional_Files/SubmitQuery.html";
var request = new XMLHttpRequest();
request.addEventListener('load', showContent, false);
request.open("GET", url, true);
request.send();
}

//function showContent() to add data into your
function showContent(e){
//add data to secContent
content.innerHTML = e.target.responseText;
}
//use the listener to load your initiate() function
window.addEventListener('load', initiate, false);