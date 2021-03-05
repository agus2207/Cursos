// write your code here
function swapRedGreen(pixel){
    var green = pixel.getGreen();
    var red = pixel.getRed();
    pixel.setGreen(red);
    pixel.setRed(green);
    return pixel;
}

var img = new SimpleImage("hilton.jpg");
print(img);
for(var pixel of img.values()){
    swapRedGreen(pixel);
}
print(img);