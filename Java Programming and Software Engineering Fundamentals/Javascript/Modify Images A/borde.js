// write your code here
function addBorder(img,border){
    for(var pixel of img.values()){
        var x = pixel.getX();
        var y = pixel.getY();
        if(x < border){
            pixel.setGreen(0);
            pixel.setRed(0);
            pixel.setBlue(0);
        }
        else if(y < border){
            pixel.setGreen(0);
            pixel.setRed(0);
            pixel.setBlue(0);
        }
        else if(x >= img.getWidth()-border){
            pixel.setGreen(0);
            pixel.setRed(0);
            pixel.setBlue(0);
        }
        else if(y >= img.getHeight()-border){
            pixel.setGreen(0);
            pixel.setRed(0);
            pixel.setBlue(0);
        }
    }
}

var img = new SimpleImage("smallpanda.png");
addBorder(img,25);
print(img);