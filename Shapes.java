interface Shape {
    double area();
    double perimeter();
    String getColor();
    String describe();
}

abstract class AbstractShape implements Shape {
    private String color;
    
    public AbstractShape(String color) {
        this.color = color;
    }
    
    public String getColor() {
        return color;
    }
    
    public String describe() {
        return "Shape color: " + color;
    }
}

class Rectangle extends AbstractShape {
    private double width;
    private double height;
    
    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }
    
    @Override
    public double area() {
        return width * height;
    }
    
    @Override
    public double perimeter() {
        return 2 * (width + height);
    }
}

class Circle extends AbstractShape {
    private double radius;
    
    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }
    
    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
    
    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }
}

public class Shapes {
    public static void main(String[] args) {
        Shape[] shapes = {
            new Rectangle("red", 5, 3),
            new Circle("blue", 4)
        };
        
        for (Shape shape : shapes) {
            System.out.println("Color: " + shape.getColor());
            System.out.println("Area: " + shape.area());
            System.out.println("Perimeter: " + shape.perimeter());
            System.out.println("Description: " + shape.describe());
            System.out.println("---");
        }
    }
}