public class Point {

    final int DEFAULT_VAL = 0;

    private int _x;
    private int _y;

    public Point(int x, int y){
        _x = (x>DEFAULT_VAL)?x:DEFAULT_VAL;
        _y = (y>DEFAULT_VAL)?y:DEFAULT_VAL;
    }
    public Point(Point other){
        //Checks on the other constructor are redundent on this call
        this(other.getX(), other.getY());
    }
    public void move(int deltaX,int deltaY){
        if ((_x + deltaX)>=0 && (_y + deltaY) >=0){
            //Does not use set because check are already made
            _x = _x+deltaX;
            _y = _y+deltaY;
        }
    }
    public double distance(Point p){
        return Math.sqrt(
                Math.pow(_x - p.getX(),2) +
                Math.pow(_y - p.getY(),2)
        );
    }
    public boolean equals(Point other){
        return (_x == other.getX()) && (_y == other.getY());
    }
    public boolean isAbove(Point other){
        return _y>other.getY();
    }
    public boolean isUnder(Point other){
        return other.isAbove(this);
    }
    public boolean isLeft(Point other){
        return _x<other.getX();
    }
    public boolean isRight(Point other){
        return other.isLeft(this);
    }
    public int getX(){
        return _x;
    }
    public int getY(){
        return _y;
    }
    public void setX(int num){
        if(num>=DEFAULT_VAL){
            _x = num;
        }
    }
    public void setY(int num){
        if(num>=DEFAULT_VAL){
            _y = num;
        }
    }
    public String toString(){
        return "("+_x+","+_y+")";
    }


}
