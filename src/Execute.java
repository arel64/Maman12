public class Execute {
    public static void main(String[] args) {
        RectangleA rect1 = new RectangleA(new Point(2,1),5,4);
        RectangleA rect2 = new RectangleA(new Point(7,1),5,4);
        RectangleA rect3 = new RectangleA(new Point(7,0),5,1);
        RectangleA rect4 = new RectangleA(new Point(7,2),1,1);
        RectangleA rect5 = new RectangleA(new Point(100,100),1,1);
        RectangleA rect6 = new RectangleA(new Point(7,5),100,100);
        System.out.println(rect1.overlap(rect2));
        System.out.println(rect1.overlap(rect3));
        System.out.println(rect1.overlap(rect4));
        System.out.println(rect1.overlap(rect5));
        System.out.println(rect1.overlap(rect6));



    }
}