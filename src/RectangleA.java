public class RectangleA {
    final int DEFAULT_HEIGHT_WIDTH = 1;
    private int _width;
    private int _height;
    private Point _pointSW;
    public RectangleA(int w,int h){
        this(new Point(0,0),w,h);
    }
    public RectangleA(Point p,int w,int h){
        _width = (w>0)?w:DEFAULT_HEIGHT_WIDTH;
        _height = (h>0)?h:DEFAULT_HEIGHT_WIDTH;
        _pointSW = new Point(p);
    }
    public RectangleA(Point sw,Point ne){
        this(sw,ne.getX() -sw.getX(), ne.getY()-sw.getY());
    }
    public RectangleA(RectangleA r){
        this(r.getPointSW(),r.getWidth(),r.getHeight());
    }
    public Point getPointSW(){
        return new Point(_pointSW);
    }
    public int getWidth(){
        return _width;
    }
    public int getHeight(){
        return _height;
    }
    public void setWidth(int w){
        if(w>0)
            _width = w;
    }
    public void setHeight(int h){
        if(h>0)
            _height = h;
    }
    public void setPointSW(Point p){
        _pointSW = new Point(p);
    }
    public String toString(){
        return "Width="+_width+" Height="+_height+" PointSW="+_pointSW;
    }
    public int getPerimeter(){
        return 2*_width + 2*_height;
    }
    public int getArea(){
        return _width*_height;
    }
    public void move(int deltaX,int deltaY){
        _pointSW.move(deltaX,deltaY);
    }
    public boolean equals(RectangleA other){
        return (
            _pointSW.equals(other.getPointSW())&&
            _width==other.getWidth()&&
            _height==other.getHeight()
        );
    }
    public double getDiagonalLength(){
        return _pointSW.distance(getPointNE());
    }
    public boolean isLarger(RectangleA other){
        return getArea()>other.getArea();
    }
    public Point getPointNE(){
        return new Point(_pointSW.getX() + _width,_pointSW.getY() + _height);
    }
    public void changeSides(){
        int temp = _width;
        _width = _height;
        _height = temp;
    }
    public boolean isIn (RectangleA r){
        Point otherNEPoint = r.getPointNE();
        Point otherSWPoint = r.getPointSW();

        return  !_pointSW.isUnder(otherSWPoint)&&
                !_pointSW.isLeft(otherSWPoint)&&
                !getPointNE().isAbove(otherNEPoint)&&
                !getPointNE().isRight(otherNEPoint);
    }
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

    //Line is orthogonal to X axis
    private boolean _lineInterceptsVertical(Point topPoint,Point bottomPoint){
        if(!topPoint.isUnder(_pointSW) && !bottomPoint.isAbove(getPointNE())){
            if(!topPoint.isLeft(_pointSW) && !topPoint.isRight(getPointNE())){
                return true;
            }
        }
        return false;
    }
    private boolean _lineInterceptsHorizontal(Point leftPoint,Point rightPoint){
        if(!leftPoint.isUnder(_pointSW) && !leftPoint.isAbove(getPointNE())){
            if(!leftPoint.isRight(getPointNE()) && !rightPoint.isLeft(_pointSW)){
                return true;
            }
        }
        return false;
    }




}
