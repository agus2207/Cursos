// write your code here
var img = new SimpleImage("hilton.jpg");
print(img);
for(var pixel of img.values()){
    if(pixel.getX() < img.getWidth()/3){
        pixel.setRed(255);
    }
    else if(pixel.getX() >= img.getWidth()/3  && pixel.getX() < 2*img.getWidth()/3){
        pixel.setGreen(255);
    }
    else{
        pixel.setBlue(255);
    }
}
print(img);