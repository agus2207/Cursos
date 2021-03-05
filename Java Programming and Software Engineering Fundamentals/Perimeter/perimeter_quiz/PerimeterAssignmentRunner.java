import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int count = 0;
        for (Point currPt : s.getPoints()){
            count = count+1;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        int count = 0;
        double average = 0.0;
        Point prevPt = s.getLastPoint();
        for(Point currPt : s.getPoints()){
            double currDist = prevPt.distance(currPt);
            average = average+currDist;
            count = count+1;
            prevPt = currPt;
        }
        return average/count;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largest = 0.0;
        Point prevPt = s.getLastPoint();
        for(Point currPt : s.getPoints()){
            double distance = prevPt.distance(currPt);
            if(largest == 0 || largest < distance){
                largest = distance;
            }
            prevPt = currPt;
        }
        return largest;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double x = 0.0;
        for(Point currPt : s.getPoints()){
            int x_curr = currPt.getX();
            if(x == 0 || x < x_curr){
                x = x_curr;
            }
        }
        return x;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double perimeter = 0.0;
         for (File f : dr.selectedFiles()) {
             FileResource fr = new FileResource(f);
             Shape s = new Shape(fr);
             double aux = getPerimeter(s);
             if(perimeter == 0 || perimeter < aux){
                 perimeter = aux;
             }
         }
        return perimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        File temp = null;    // replace this code
        double perimeter = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double aux = getPerimeter(s);
            if(perimeter == 0 || perimeter < aux){
                perimeter = aux;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int points = getNumPoints(s);
        System.out.println("Number of points: "+points);
        double average = getAverageLength(s);
        System.out.println("Average: "+average);
        double largest = getLargestSide(s);
        System.out.println("Largest: "+largest);
        double x_largest = getLargestX(s);
        System.out.println("X largest: "+x_largest);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double perimeter = getLargestPerimeterMultipleFiles();
        System.out.println("The largest perimeter is: "+perimeter);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String name = getFileWithLargestPerimeter();
        System.out.println("The file is:" +name);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        //pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
