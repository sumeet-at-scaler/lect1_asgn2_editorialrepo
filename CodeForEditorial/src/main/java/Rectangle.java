public class Rectangle {
    Point topLeft;
    int height;
    int width;

    Point getBottomRight(){
        Point p = new Point();
        p.x = topLeft.x + width;
        p.y = topLeft.y - height;
        return p;
    }

    int getArea(){
        return height * width;
    }

    int getParameter(){
        return 2 * (height + width);
    }

}