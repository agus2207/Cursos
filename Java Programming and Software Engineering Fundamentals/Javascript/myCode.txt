// write your code here
function crop(image,width,height){
    var res = new SimpleImage(width,height);
    for(var pixel of image.values()){
        if(pixel.getX() < width && pixel.getY() < height){
            var newpix = res.getPixel(pixel.getX(),pixel.getY());
            newpix.setAllFrom(pixel); 
        }
    }
    return res;
}

function min(num1, num2){
    var res = 0;
    if(num1 < num2){
        res = num1;
    }
    else{
        res = num2;
    }
    return res;
}

function chop2Hide(image){
    for(var pixel of image.values()){
        pixel.setRed((pixel.getRed()/16)*16);
        pixel.setGreen((pixel.getGreen()/16)*16);
        pixel.setBlue((pixel.getBlue()/16)*16);
    }
    return image;
}

function shift(image){
    for(var pixel of image.values()){
        pixel.setRed((pixel.getRed()/16));
        pixel.setGreen((pixel.getGreen()/16));
        pixel.setBlue((pixel.getBlue()/16));
    }
    return image;
}

function combine(image1, image2){
    var res = new SimpleImage(image1.getWidth(), image1.getHeight());
    for(var pixel of image1.values()){
        var x = pixel.getX();
        var y = pixel.getY();
        var pixsum = image2.getPixel(x,y);
        var newpix = res.getPixel(x,y);
        newpix.setRed(pixel.getRed()+pixsum.getRed());
        newpix.setGreen(pixel.getGreen()+pixsum.getGreen());
        newpix.setBlue(pixel.getBlue()+pixsum.getBlue());
    }
    return res;
}

var start = new SimpleImage("astrachan.jpg");
var hide = new SimpleImage("hilton.jpg");
var neww = min(start.getWidth(), hide.getWidth());
var newh = min(start.getHeight(), hide.getHeight());
start = crop(start,neww,newh);
hide = crop(hide,neww,newh);
start = chop2Hide(start);
hide = shift(hide);
var comb = combine(start,hide);
print(comb);
