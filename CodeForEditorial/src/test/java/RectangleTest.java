import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    private Point point;

    @BeforeEach
    void setUp() {
        point = new Point();
    }

    @Test
    void xExists(){
        try {
            Field xField = point.getClass().getDeclaredField("x");
            assertEquals(xField.getType().toString(), "int");
        } catch (NoSuchFieldException e) {
            fail("x not found");
        }
    }

    @Test
    void yExists(){
        try {
            Field yField = point.getClass().getDeclaredField("y");
            assertEquals(yField.getType().toString(), "int");
        } catch (NoSuchFieldException e) {
            fail("y not found");
        }
    }
}


class RectangleTest {

    private Rectangle rectangle;
    private Point point;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        rectangle = new Rectangle();
        point = new Point();
    }

    @Test
    void topLeftExists(){
        try {
            Field topLeftField = rectangle.getClass().getDeclaredField("topLeft");
            assertEquals(topLeftField.getType().toString(), "class Point");
        } catch (NoSuchFieldException e) {
            fail("topLeft not found");
        }
    }

    @Test
    void heightExists(){
        try {
            Field heightField = rectangle.getClass().getDeclaredField("height");
            assertEquals(heightField.getType().toString(), "int");
        } catch (NoSuchFieldException e) {
            fail("height not found");
        }
    }

    @Test
    void widthExists(){
        try {
            Field widthField = rectangle.getClass().getDeclaredField("width");
            assertEquals(widthField.getType().toString(), "int");
        } catch (NoSuchFieldException e) {
            fail("width not found");
        }
    }
    
    @Test
    void getAreaMethodExists(){
        try {
            Method getAreaMethod = rectangle.getClass().getDeclaredMethod("getArea");
        } catch (NoSuchMethodException e) {
            fail("get Area method not found");
        }
    }

    @Test
    void getAreaMethodSignatureCheck() throws NoSuchMethodException {
        Method getAreaMethod = rectangle.getClass().getDeclaredMethod("getArea");
        assertEquals(getAreaMethod.toString(), "int Rectangle.getArea()");
    }

    @Test
    void getParameterMethodExists(){
        try {
            Method getParameterMethod = rectangle.getClass().getDeclaredMethod("getParameter");
        } catch (NoSuchMethodException e) {
            fail("get parameter method not found");
        }
    }

    @Test
    void getParameterMethodSignatureCheck() throws NoSuchMethodException {
        Method getParameterMethod = rectangle.getClass().getDeclaredMethod("getParameter");
        assertEquals(getParameterMethod.toString(), "int Rectangle.getParameter()");
    }

    @Test
    void getBottomRightMethodExists(){
        try {
            Method getBottomRightMethod = rectangle.getClass().getDeclaredMethod("getBottomRight");
        } catch (NoSuchMethodException e) {
            fail("getBottomRight method not found");
        }
    }

    @Test
    void getBottomRightMethodSignatureCheck() throws NoSuchMethodException {
        Method getBottomRightMethod = rectangle.getClass().getDeclaredMethod("getBottomRight");
        assertEquals(getBottomRightMethod.toString(), "Point Rectangle.getBottomRight()");
    }

    @Test
    void getArea() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException  {

        Field widthField = rectangle.getClass().getDeclaredField("width");
        widthField.setAccessible(true);
        widthField.set(rectangle, 10);

        Field heightField = rectangle.getClass().getDeclaredField("height");
        heightField.setAccessible(true);
        heightField.set(rectangle, 5);


        Method getAreaMethod = rectangle.getClass().getDeclaredMethod("getArea");
        getAreaMethod.setAccessible(true);
        int area = (int) getAreaMethod.invoke(rectangle);

        assertEquals(50, area);
    }

    @Test
    void getParameter() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Field widthField = rectangle.getClass().getDeclaredField("width");
        widthField.setAccessible(true);
        widthField.set(rectangle, 10);

        Field heightField = rectangle.getClass().getDeclaredField("height");
        heightField.setAccessible(true);
        heightField.set(rectangle, 5);

        Method getParameterMethod = rectangle.getClass().getDeclaredMethod("getParameter");
        getParameterMethod.setAccessible(true);
        int parameter = (int) getParameterMethod.invoke(rectangle);

        assertEquals(30, parameter);
    }

    @Test
    void getBottomRight() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Field xField = point.getClass().getDeclaredField("x");
        xField.setAccessible(true);
        xField.set(point, 3);

        Field yField = point.getClass().getDeclaredField("y");
        yField.setAccessible(true);
        yField.set(point, 4);

        Field topLeftField = rectangle.getClass().getDeclaredField("topLeft");
        topLeftField.setAccessible(true);
        topLeftField.set(rectangle, point);

        Field widthField = rectangle.getClass().getDeclaredField("width");
        widthField.setAccessible(true);
        widthField.set(rectangle, 5);

        Field heightField = rectangle.getClass().getDeclaredField("height");
        heightField.setAccessible(true);
        heightField.set(rectangle, 6);

        Method getBottomRightMethod = rectangle.getClass().getDeclaredMethod("getBottomRight");
        getBottomRightMethod.setAccessible(true);
        Point bottomRight = (Point) getBottomRightMethod.invoke(rectangle);

        assertEquals(8, bottomRight.x);
        assertEquals(-2, bottomRight.y);
    }


}
