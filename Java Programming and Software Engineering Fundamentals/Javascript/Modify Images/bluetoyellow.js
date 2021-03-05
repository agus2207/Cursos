// write your code here
var img = new SimpleImage("duke_blue_devil.png");
print(img);
for(var pixel of img.values()){
    if(!((pixel.getBlue()==255) && (pixel.getRed()==255) && (pixel.getGreen()==255))){
        pixel.setGreen(255);
        pixel.setRed(255);
        pixel.setBlue(0);
    }
}
print(img);