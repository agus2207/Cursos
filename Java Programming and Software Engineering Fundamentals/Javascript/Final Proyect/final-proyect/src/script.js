var img = null;
var dd1;

function upload_img(){
  dd1 = document.getElementById("c1");
  var selector = document.getElementById("if");
  img = new SimpleImage(selector);
  img.drawTo(dd1);
}

function redfil(){
  if(img != null && img.complete()){
    var redimg = new SimpleImage(img.getWidth(),img.getHeight());
    for(var pixel of img.values()){
      var average =(pixel.getRed()+pixel.getGreen()+pixel.getBlue())/3;
      var auxPix = redimg.getPixel(pixel.getX(),pixel.getY());
      if(average < 128){
        auxPix.setRed(2*average);
        auxPix.setGreen(0);
        auxPix.setBlue(0);
      }
      else{
        auxPix.setRed(255);
        auxPix.setGreen((2*average)-255);
        auxPix.setBlue((2*average)-255);
      }
    }
    redimg.drawTo(dd1);
  }
  else{
    alert("Something is wrong with the image");
    return;
  }
}

function grayscale(){
  if(img != null && img.complete()){
    var gray = new SimpleImage(img.getWidth(),img.getHeight());
    for(var pixel of img.values()){
      var average =(pixel.getRed()+pixel.getGreen()+pixel.getBlue())/3;
      var auxPix = gray.getPixel(pixel.getX(),pixel.getY());
      auxPix.setRed(average);
      auxPix.setGreen(average);
      auxPix.setBlue(average);
    }
    gray.drawTo(dd1);
  }
  else{
    alert("Something is wrong with the image");
    return;
  }
}

function freefil(){
  if(img != null && img.complete()){
    var freedom = new SimpleImage(img.getWidth(),img.getHeight());
    var border = 50;
    for(var pixel of img.values()){
      var x = pixel.getX();
      var y = pixel.getY();
      var auxPix = freedom.getPixel(x,y);
      if(x < 2*border){
        auxPix.setGreen(77);
        auxPix.setRed(77);
        auxPix.setBlue(255);
      }
      else if(y < 2*border){
        auxPix.setGreen(77);
        auxPix.setRed(77);
        auxPix.setBlue(255);
      }
      else if(x >= img.getWidth()-2*border){
        auxPix.setGreen(77);
        auxPix.setRed(77);
        auxPix.setBlue(255);
      }
      else if(y >= img.getHeight()-2*border){
        auxPix.setGreen(77);
        auxPix.setRed(77);
        auxPix.setBlue(255);
      }
      else if(y <= img.getHeight()/2+border && y >= img.getHeight()/2-border){
        auxPix.setGreen(77);
        auxPix.setRed(77);
        auxPix.setBlue(255);
      }
      else if(x <= img.getWidth()/4+border && x >= img.getWidth()/4-border){
        auxPix.setGreen(77);
        auxPix.setRed(77);
        auxPix.setBlue(255);
      }
      else if(x <= (2*img.getWidth()/4)+border && x >= (2*img.getWidth()/4)-border){
        auxPix.setGreen(77);
        auxPix.setRed(77);
        auxPix.setBlue(255);
      }
      else if(x <= (3*img.getWidth()/4)+border && x >= (3*img.getWidth()/4)-border){
        auxPix.setGreen(77);
        auxPix.setRed(77);
        auxPix.setBlue(255);
      }
      else{
        auxPix.setAllFrom(pixel);
      }
    }
    freedom.drawTo(dd1);
  }
  else{
    alert("Something is wrong with the image");
    return;
  }
}

function rainbow(){
   if(img != null && img.complete()){
    var rb = new SimpleImage(img.getWidth(),img.getHeight());
    for(var pixel of img.values()){
      var average =(pixel.getRed()+pixel.getGreen()+pixel.getBlue())/3;
      var auxPix = rb.getPixel(pixel.getX(),pixel.getY());
      var y = img.getHeight();
      if(pixel.getY() < y/7){
        if(average<128){
          auxPix.setRed(average*2);
          auxPix.setGreen(0);
          auxPix.setBlue(0);
        }
        else{
          auxPix.setRed(255);
          auxPix.setGreen((average*2)-255);
          auxPix.setBlue((average*2)-255);
        }
      }
      else if((pixel.getY() >= y/7) && (pixel.getY() < (2*y/7))){
        if(average<128){
          auxPix.setRed(average*2);
          auxPix.setGreen(0.8*average);
          auxPix.setBlue(0);
        }
        else{
          auxPix.setRed(255);
          auxPix.setGreen((average*1.2)-51);
          auxPix.setBlue((average*2)-255);
        }
      }
      else if((pixel.getY() >= (2*y/7)) && (pixel.getY() < (3*y/7))){
        if(average<128){
          auxPix.setRed(average*2);
          auxPix.setGreen(2*average);
          auxPix.setBlue(0);
        }
        else{
          auxPix.setRed(255);
          auxPix.setGreen(255);
          auxPix.setBlue((average*2)-255);
        }
      }
      else if((pixel.getY() >= (3*y/7)) && (pixel.getY() < (4*y/7))){
        if(average<128){
          auxPix.setRed(0);
          auxPix.setGreen(2*average);
          auxPix.setBlue(0);
        }
        else{
          auxPix.setRed((average*2)-255);
          auxPix.setGreen(255);
          auxPix.setBlue((average*2)-255);
        }
      }
      else if((pixel.getY() >= (4*y/7)) && (pixel.getY() < (5*y/7))){
        if(average<128){
          auxPix.setRed(0);
          auxPix.setGreen(0);
          auxPix.setBlue(2*average);
        }
        else{
          auxPix.setRed((average*2)-255);
          auxPix.setGreen((average*2)-255);
          auxPix.setBlue(255);
        }
      }
      else if((pixel.getY() >= (5*y/7)) && (pixel.getY() < (6*y/7))){
        if(average<128){
          auxPix.setRed(0.8*average);
          auxPix.setGreen(0);
          auxPix.setBlue(2*average);
        }
        else{
          auxPix.setRed((average*1.2)-51);
          auxPix.setGreen((average*2)-255);
          auxPix.setBlue(255);
        }
      }
      else{
        if(average<128){
          auxPix.setRed(1.6*average);
          auxPix.setGreen(0);
          auxPix.setBlue(1.6*average);
        }
        else{
          auxPix.setRed((average*0.4)+153);
          auxPix.setGreen((average*2)-255);
          auxPix.setBlue((average*0.4)+153);
        }
      }
    }
    rb.drawTo(dd1);
  }
  else{
    alert("Something is wrong with the image");
    return;
  }
}

function coord(pixel){
  var x = Math.floor((Math.random() * 10) + 1)+pixel.getX();
  var y = Math.floor((Math.random() * 10) + 1)+pixel.getY();
  if(x >= img.getWidth()){
    x = img.getWidth()-1;
  }
  if(y >= img.getHeight()){
    y = img.getHeight()-1;
  }
  var aux = img.getPixel(x,y);
  return aux;
}

function blurf(){
  if(img != null && img.complete()){
    var bluri = new SimpleImage(img.getWidth(),img.getHeight());
    for(var pixel of img.values()){
      var auxPix = bluri.getPixel(pixel.getX(),pixel.getY());
      if(Math.random() >= 0.5){
        auxPix.setAllFrom(coord(pixel));
      }
      else{
        auxPix.setAllFrom(pixel);
      }
    }
    bluri.drawTo(dd1);
  }
  else{
    alert("Something is wrong with the image");
    return;
  }
}

function originally(){
  img.drawTo(dd1);
}

function clearc(){
  doclean(dd1);
  doclean(dd2);
}

function doclean(canvas){
  var context1 = canvas.getContext("2d");
  context1.clearRect(0, 0, canvas.width, canvas.height);
}

