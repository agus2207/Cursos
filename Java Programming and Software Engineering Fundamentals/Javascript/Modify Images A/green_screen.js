// write your code here
var img = new SimpleImage("drewRobert.png");
var back = new SimpleImage("dinos.png");
for(var pixel of img.values()){
    var x = pixel.getX();
    var y = pixel.getY();
    var red = back.getPixel(x,y).getRed();
    var green = back.getPixel(x,y).getGreen();
    var blue = back.getPixel(x,y).getBlue();
    var r = pixel.getRed();
    var g = pixel.getGreen();
    var b = pixel.getBlue();
    if(r == 0 && g == 255 && b == 0){
        pixel.setGreen(green);
        pixel.setRed(red);
        pixel.setBlue(blue);
    }
}
print(img);
