function clicked(){
  alert('You click a botton');
}

function confirmation(){
  var res;
  var choice = confirm('What are you doing');
  if(choice == true){
    alert('You pressed OK!');
  }
  else{
    alert('Are you sure you want to cancel?');
  }
}

function changecolor(){
  var dd1 = document.getElementById("div1");
  var dd2 = document.getElementById("div2");
  dd1.className = "blueback";
  dd2.className = "orangeback";
}

function changetext(){
  var dd1 = document.getElementById("div1");
  var dd2 = document.getElementById("div2");
  dd1.innerHTML="Aqui esta div1";
  dd2.innerHTML="Aqui esta div2";
}
