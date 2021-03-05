function changecolor(){
  var dd1 = document.getElementById("div1");
  var input = document.getElementById("b2");
  var color = input.value;
  dd1.style.backgroundColor = color;
}

function square(){
  var dd1 = document.getElementById("div1");
  var input = document.getElementById("sldr");
  var range = input.value;
  var context = dd1.getContext("2d");
  context.clearRect(0,0,dd1.width,dd1.height);
  context.fillStyle = "yellow";
  context.fillRect(10,10,range,range);
  context.fillRect(parseInt(range)+20,10,range,range)
}