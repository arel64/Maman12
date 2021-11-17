/**
 * This Class represents a point in the 1st sector of R^2 Grid
 * @author Arel Sharon
 * @version 1.0
 * @since 17/10/2021
 */
public class RectangleA {
    final int DEFAULT_HEIGHT_WIDTH = 1;
    private int _width;
    private int _height;
    private Point _pointSW;

    /**
     * First constructor for objects of class RectangleA Constructs a new rectangle with the specified width, height and it's south west corner is (0,0)
     * @param w The rectangle width
     * @param h The rectangle height
     */
    public RectangleA(int w,int h){
        this(new Point(0,0),w,h);
    }

    /**
     * Second constructor for objects of class RectangleA Construct a new rectangle with the specified width, height and south west vertex
     * @param p south western vertex
     * @param w rectangle width
     * @param h rectangle height
     */
    public RectangleA(Point p,int w,int h){
        _width = (w>0)?w:DEFAULT_HEIGHT_WIDTH;
        _height = (h>0)?h:DEFAULT_HEIGHT_WIDTH;
        _pointSW = new Point(p);
    }

    /**
     * Third constructor for objects of class RectangleA Construct a new rectangle with the specified south west vertex and north east vertex
     * @param sw south western vertex
     * @param ne north eastern vertex
     */
    public RectangleA(Point sw,Point ne){
        this(sw,ne.getX() -sw.getX(), ne.getY()-sw.getY());
    }

    /**
     * Copy constructor for objects of class RectangleA Constructs a rectangle using another rectangle
     * @param r The rectangle from which to construct the new object
     */
    public RectangleA(RectangleA r){
        this(r.getPointSW(),r.getWidth(),r.getHeight());
    }

    /**
     * Returns the South-West point of the rectangle
     * @return a copy of the s-w point of the rectangle
     */
    public Point getPointSW(){
        return new Point(_pointSW);
    }

    /**
     * Returns the width of the rectangle
     * @return the width of the rectangle
     */
    public int getWidth(){
        return _width;
    }

    /**
     * Returns the height of the rectangle
     * @return the height of the rectangle
     */
    public int getHeight(){
        return _height;
    }

    /**
     * Sets the width of the rectangle
     * @param w the width of the rectangle to set to
     */
    public void setWidth(int w){
        if(w>0)
            _width = w;
    }

    /**
     * Sets the height of the rectangle
     * @param h the height of the rectangle to set to
     */
    public void setHeight(int h){
        if(h>0)
            _height = h;
    }

    /**
     * Sets the South-West point of the rectangle
     * @param p the S-W point of the rectangle to set to
     */
    public void setPointSW(Point p){
        _pointSW = new Point(p);
    }

    /**
     * Returns a string representation of the rectangle
     * @return a string represent the rectangle For example: Width=4 Height=6 PointSW=(1,2)
     */
    public String toString(){
        return "Width="+_width+" Height="+_height+" PointSW="+_pointSW;
    }

    /**
     * Calculates the perimeter of the rectangle
     * @return The perimeter of the rectangle.
     */
    public int getPerimeter(){
        return 2*_width + 2*_height;
    }

    /**
     * Calculates the area of the rectangle
     * @return The area of the rectangle.
     */
    public int getArea(){
        return _width*_height;
    }

    /**
     * Move the rectangle by deltaX in x direction and deltaY in y direction
     * @param deltaX translate the rectangle deltaX in the x direction.
     * @param deltaY translate the rectangle deltaY in the y direction.
     */
    public void move(int deltaX,int deltaY){
        _pointSW.move(deltaX,deltaY);
    }

    /**
     * Returns true if the given rectangle is equal to this rectangle
     * @param other  the rectangle to check equality with
     * @return true if other and this rectangle are equal
     */
    public boolean equals(RectangleA other){
        return (
            _pointSW.equals(other.getPointSW())&&
            _width==other.getWidth()&&
            _height==other.getHeight()
        );
    }

    /**
     * Returns the length of the rectangle diagonal
     * @return the length of the diagonal of the Rectangle
     */
    public double getDiagonalLength(){
        return _pointSW.distance(getPointNE());
    }

    /**
     * Returns true if the current rectangle is larger than the parameter rectangle
     * @param other another Rectangle to compare with
     * @return true - if the current Rectangle's area is larger than the other Rectangle which recieved as parameter, false - otherwise
     */
    public boolean isLarger(RectangleA other){
        return getArea()>other.getArea();
    }

    /**
     * Returns the North-East point of the rectangle
     * @return a copy of the North-East point of the Rectangle
     */
    public Point getPointNE(){
        return new Point(_pointSW.getX() + _width,_pointSW.getY() + _height);
    }

    /**
     * Changes the width to height and vice versa
     */
    public void changeSides(){
        int temp = _width;
        _width = _height;
        _height = temp;
    }

    /**
     * Returns true if the current rectangle is in the parameter rectangle
     * @param r another Rectangle to check with
     * @return true - if the current Rectangle's completly in the other Rectangle which recieved as parameter, false - otherwise
     */
    public boolean isIn (RectangleA r){
        Point otherNEPoint = r.getPointNE();
        Point otherSWPoint = r.getPointSW();

        return  !_pointSW.isUnder(otherSWPoint)&&
                !_pointSW.isLeft(otherSWPoint)&&
                !getPointNE().isAbove(otherNEPoint)&&
                !getPointNE().isRight(otherNEPoint);
    }

    /**
     * Returns true if the current rectangle overlaps with the parameter rectangle
     * @param r  another Rectangle to check with
     * @return true - if the current Rectangle's overlaps with the other Rectangle which recieved as parameter even by a single point, false - otherwise
     */
    public boolean overlap (RectangleA r){
        Point otherSWPoint = r.getPointSW();
        Point otherNEPoint = r.getPointNE();
        Point otherNWPoint = new Point(otherSWPoint.getX(),otherSWPoint.getY()+r.getHeight());
        Point otherSEPoint = new Point(otherSWPoint.getX()+r.getWidth(),otherSWPoint.getY());
        if(_lineInterceptsHorizontal(otherSWPoint,otherSEPoint) ||
                _lineInterceptsHorizontal(otherNEPoint,otherNWPoint)||
                    _lineInterceptsVertical(otherNWPoint,otherSWPoint)||
                        _lineInterceptsVertical(otherNEPoint,otherSEPoint)){
            return true;
        }
        //No point line is in, check for containment... If not contained then no overlap
        return isIn(r);
    }

    private boolean _lineInterceptsVertical(Point topPoint,Point bottomPoint){
        return !topPoint.isUnder(_pointSW) &&
                !bottomPoint.isAbove(getPointNE()) &&
                    !topPoint.isLeft(_pointSW) &&
                        !topPoint.isRight(getPointNE());
    }
    private boolean _lineInterceptsHorizontal(Point leftPoint,Point rightPoint){
        return !leftPoint.isUnder(_pointSW) &&
                    !leftPoint.isAbove(getPointNE())&&
                        !leftPoint.isRight(getPointNE()) &&
                            !rightPoint.isLeft(_pointSW);
    }
}
