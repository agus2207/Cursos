var img = null;
var back = null;
var dd1;
var dd2;

function upload_img(){
  dd1 = document.getElementById("c1");
  var selector = document.getElementById("if");
  img = new SimpleImage(selector);
  img.drawTo(dd1);
}

function upload_back(){
  dd2 = document.getElementById("c2");
  var selector = document.getElementById("bf");
  back = new SimpleImage(selector);
  back.drawTo(dd2);
}

function green(){
  if(img == null || !img.complete()){
    alert("Foreground not loaded");
    return;
  }
  if(back == null || !back.complete()){
    alert("Background not loaded");
    return;
  }
  if((img.getWidth() == back.getWidth()) && (img.getHeight() == back.getHeight())){
    var res = new SimpleImage(img.getWidth(),img.getHeight());
    for(var pixel of img.values()){
      var x = pixel.getX();
      var y = pixel.getY();
      var red = back.getPixel(x,y).getRed();
      var green = back.getPixel(x,y).getGreen();
      var blue = back.getPixel(x,y).getBlue();
      var r = pixel.getRed();
      var g = pixel.getGreen();
      var b = pixel.getBlue();
      var auxPix = res.getPixel(pixel.getX(),pixel.getY());
      if(r == 0 && g == 255 && b == 0){
        auxPix.setGreen(green);
        auxPix.setRed(red);
        auxPix.setBlue(blue);
      }
      else{
        auxPix.setAllFrom(pixel); 
      }
    }
    res.drawTo(dd1);
    doclean(dd2);
  }
  else{
    alert("Foreground not match with Background");
    return;
  }
}

function clearc(){
  doclean(dd1);
  doclean(dd2);
}

function doclean(canvas){
  var context1 = canvas.getContext("2d");
  context1.clearRect(0, 0, canvas.width, canvas.height);
}