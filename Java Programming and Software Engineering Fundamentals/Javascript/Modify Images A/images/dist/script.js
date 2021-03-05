var img = null;
var gray = null;

function upload(){
  var dd1 = document.getElementById("c1");
  var selector = document.getElementById("sf");
  img = new SimpleImage(selector);
  gray = img;
  img.drawTo(dd1);
}

function grayscale(){
  for(var pixel of gray.values()){
    var average = (pixel.getRed()+pixel.getGreen()+pixel.getBlue())/3;
    pixel.setRed(average);
    pixel.setGreen(average);
    pixel.setBlue(average);
  }
  var dd1 = document.getElementById("c2");
  gray.drawTo(dd1);
}