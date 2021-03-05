function dopurple(){
  var dd1 = document.getElementById("div1");
  dd1.style.backgroundColor = "fuchsia";
  var context = dd1.getContext("2d");
  context.fillStyle = "yellow";
  context.fillRect(10,10,60,60);
  context.fillRect(80,10,60,60);
  context.fillStyle = "black";
  context.font = "20px Arial";
  context.fillText("Hello",15,45);
}

function dolime(){
  var dd1 = document.getElementById("div1");
  var context = dd1.getContext("2d");
  context.clearRect(0,0,dd1.width,dd1.height);
  dd1.style.backgroundColor = "lime";
}

function dored(){
  var dd1 = document.getElementById("div1");
  dd1.style.backgroundColor = "red";
}