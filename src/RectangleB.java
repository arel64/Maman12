public class RectangleB {
    final int DEFAULT_HEIGHT_WIDTH = 1;
    private Point _pointSW;
    private Point _pointNE;

    /**
     * First constructor for objects of class RectangleB Constructs a new rectangle with the specified width, height and it's south west corner is (0,0)
     * @param w The rectangle width
     * @param h The rectangle height
     */
    public RectangleB(int w, int h){
        this(new Point(0,0),w,h);
    }

    /**
     * Second constructor for objects of class RectangleB Constructs a new rectangle with the specified vertices
     * @param p south western vertex
     * @param w rectangle width
     * @param h rectangle height
     */
    public RectangleB(Point p, int w, int h){
        w = (w>0)?w:DEFAULT_HEIGHT_WIDTH;
        h = (h>0)?h:DEFAULT_HEIGHT_WIDTH;
        _pointSW = new Point(p);
        _pointNE = new Point(p.getX()+w,p.getY()+h);
    }

    /**
     * Third constructor for objects of class RectangleB Constructs a new rectangle with the specified vertices
     * @param sw south western vertex
     * @param ne north eastern vertex
     */
    public RectangleB(Point sw, Point ne){
        _pointSW = new Point(sw);
        _pointNE = new Point(ne);
    }

    /**
     * Copy constructor for objects of class RectangleB Constructs a rectangle using another rectangle
     * @param r The rectangle from which to construct the new object
     */
    public RectangleB(RectangleB r){
        this._pointSW = new Point(r._pointSW);
        this._pointNE = new Point(r._pointNE);
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
        return Math.abs(_pointNE.getX() - _pointSW.getX());
    }

    /**
     * Returns the height of the rectangle
     * @return the height of the rectangle
     */
    public int getHeight(){
        return Math.abs(_pointNE.getY() - _pointSW.getY());
    }

    /**
     * Sets the width of the rectangle
     * @param w the width of the rectangle to set to
     */
    public void setWidth(int w){
        if(w>0)
            _pointNE.setX(_pointSW.getX() + w);
    }

    /**
     * Sets the height of the rectangle
     * @param h the height of the rectangle to set to
     */
    public void setHeight(int h){
        if(h>0)
            _pointNE.setY(_pointSW.getY() + h);
    }

    /**
     * Sets the South-West point of the rectangle. This change affects also the NE Point since the width and height of the rectangle should NOT change
     * @param p the new S-W point of the rectangle
     */
    public void setPointSW(Point p){
        int width = getWidth();
        int height = getHeight();
        _pointSW = new Point(p);
        _pointNE.setX(_pointSW.getX()+width);
        _pointNE.setY(_pointSW.getY()+height);
    }

    /**
     * Returns a string representation of the rectangle
     * @return a string represent the rectangle For example: Width=4 Height=6 PointSW=(1,2)
     */
    public String toString(){
        return "Width="+getWidth()+" Height="+getHeight()+" PointSW="+_pointSW;
    }

    /**
     * Calculates the perimeter of the rectangle
     * @return The perimeter of the rectangle.
     */
    public int getPerimeter(){
        return 2*getWidth() + 2*getHeight();
    }

    /**
     * Calculates the area of the rectangle
     * @return The area of the rectangle.
     */
    public int getArea(){
        return getWidth()*getHeight();
    }

    /**
     * Move the rectangle by deltaX in x direction and deltaY in y direction
     * @param deltaX translate the rectangle deltaX in the x direction.
     * @param deltaY translate the rectangle deltaY in the y direction.
     */
    public void move(int deltaX,int deltaY){
            _pointSW.move(deltaX,deltaY);
            _pointNE.move(deltaX,deltaY);
    }

    /**
     * Returns true if the given rectangle is equal to this rectangle
     * @param other the rectangle to check equality with
     * @return true iff other and this rectangle are equal
     */
    public boolean equals(RectangleB other){
        return (
                _pointSW.equals(other.getPointSW())&&
                    _pointNE.equals(other.getPointNE())
        );
    }

    /**
     * Returns the length of the rectangle diagonal
     * @return the length of the diagonal of the Rectangle
     */
    public double getDiagonalLength(){
        return _pointSW.distance(_pointNE);
    }

    /**
     * Returns true if the current rectangle is larger than the parameter rectangle
     * @param other  another Rectangle to compare with
     * @return true - if the current Rectangle's area is larger than the other Rectangle which recieved as parameter, false - otherwise
     */
    public boolean isLarger(RectangleB other){
        return getArea()>other.getArea();
    }

    /**
     * Returns the North-East point of the rectangle
     * @return a copy of the n-e point of the rectangle
     */
    public Point getPointNE(){
        return new Point(_pointNE);
    }

    /**
     * Changes the width to height and vice versa
     */
    public void changeSides(){
        int initalWidth = getWidth();
        int initalHeight = getHeight();
        _pointNE.setX(_pointSW.getX()+initalHeight);
        _pointNE.setY(_pointSW.getY()+initalWidth);
    }

    /**
     * Returns true if the current rectangle is in the parameter rectangle
     * @param r another Rectangle to check with
     * @return true - if the current Rectangle's completly in the other Rectangle which recieved as parameter, false - otherwise
     */
    public boolean isIn (RectangleB r){
        Point otherNEPoint = r.getPointNE();
        Point otherSWPoint = r.getPointSW();

        return  !_pointSW.isUnder(otherSWPoint)&&
                !_pointSW.isLeft(otherSWPoint)&&
                !_pointNE.isAbove(otherNEPoint)&&
                !_pointNE.isRight(otherNEPoint);
    }

    /**
     * Returns true if the current rectangle overlaps with the parameter rectangle
     * @param r another Rectangle to check with
     * @return true - if the current Rectangle's overlaps with the other Rectangle which recieved as parameter even by a single point, false - otherwise
     */
    public boolean overlap (RectangleB r){
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

    //Line is orthogonal to X axis
    private boolean _lineInterceptsVertical(Point topPoint,Point bottomPoint){
        return !topPoint.isUnder(_pointSW) &&
                    !bottomPoint.isAbove(_pointNE) &&
                        !topPoint.isLeft(_pointSW) &&
                            !topPoint.isRight(_pointNE);
    }
    private boolean _lineInterceptsHorizontal(Point leftPoint,Point rightPoint){
        return !leftPoint.isUnder(_pointSW) &&
                    !leftPoint.isAbove(_pointNE)&&
                        !leftPoint.isRight(_pointNE) &&
                            !rightPoint.isLeft(_pointSW);
    }
}