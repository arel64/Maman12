/**
 * This Class represents a point in the 1st sector of R^2 Grid
 * @author Arel Sharon
 * @version 1.0
 * @since 17/10/2021
 */
public class Point {


    private int _x;
    private int _y;

    /**
     * Construct Point by x,y (x,y) in grid
     * @param x x value
     * @param y y value
     */
    public Point(int x, int y){
        _x = x;
        _y = y;
    }

    /**
     * Copy constructor
     * @param other Point to copy
     */
    public Point(Point other){
        //Checks are redundent, we assume other point has been intialized correctly
        this._x = other.getX();
        this._y = other.getY();
    }

    /**
     * Amount to move point along the x and y axes. Must cause either to be negative
     * @param deltaX amount to move point along x-axis
     * @param deltaY amount to move point along y-axis
     */
    public void move(int deltaX,int deltaY){
            _x = _x+deltaX;
            _y = _y+deltaY;
    }

    /**
     * Calculate the Distance between this point and the paramter point given
     * @param p point to measure distance to
     * @return Distance between this point and param
     */
    public double distance(Point p){
        return Math.sqrt(
                Math.pow(_x - p.getX(),2) +
                Math.pow(_y - p.getY(),2)
        );
    }

    /**
     * Are the points equal(Same x,y)
     * @param other Point to compare to
     * @return true if they are the same point false otherwise
     */
    public boolean equals(Point other){
        return (_x == other.getX()) && (_y == other.getY());
    }

    /**
     * Returns whether a point has a greater y coordinate(is above)
     * @param other point to check against
     * @return true if this point is above false otherwise
     */
    public boolean isAbove(Point other){
        return _y>other.getY();
    }

    /**
     * Returns whether a point has a smaller y coordinate(is under)
     * @param other point to check against
     * @return true if this point is below false otherwise
     */
    public boolean isUnder(Point other){
        return other.isAbove(this);
    }

    /**
     * Returns whether a point has a smaller x coordinate(is left)
     * @param other point to check against
     * @return true if this point is left to compare point false otherwise
     */
    public boolean isLeft(Point other){
        return _x<other.getX();
    }

    /**
     * Returns whether a point has a greater x coordinate(is right)
     * @param other point to check against
     * @return true if this point is right to compare point false otherwise
     */
    public boolean isRight(Point other){
        return other.isLeft(this);
    }

    /**
     * Return X of point
     * @return x value of point
     */
    public int getX(){
        return _x;
    }

    /**
     * Return Y of point
     * @return Y value of point
     */
    public int getY(){
        return _y;
    }

    /**
     * Set X of point to num given,
     * @param num number to set x value to, must be positive integer
     */
    public void setX(int num){

        _x = num;

    }

    /**
     * Set Y of point to num given,
     * @param num number to set y value to, must be positive integer
     */
    public void setY(int num){

        _y = num;

    }
    public String toString(){
        return "("+_x+","+_y+")";
    }


}
