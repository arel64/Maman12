public class RectangleB {
    final int DEFAULT_HEIGHT_WIDTH = 1;
    private Point _pointSW;
    private Point _pointNE;
    public RectangleB(int w, int h){
        this(new Point(0,0),w,h);
    }
    public RectangleB(Point p, int w, int h){
        w = (w>0)?w:DEFAULT_HEIGHT_WIDTH;
        h = (h>0)?h:DEFAULT_HEIGHT_WIDTH;
        _pointSW = new Point(p);
        _pointNE = new Point(p.getX()+w,p.getY()+h);
    }
    public RectangleB(Point sw, Point ne){
        _pointSW = new Point(sw);
        _pointNE = new Point(ne);
    }
    public RectangleB(RectangleB r){
        this._pointSW = new Point(r._pointSW);
        this._pointNE = new Point(r._pointNE);
    }



    public Point getPointSW(){
        return new Point(_pointSW);
    }

    public int getWidth(){
        return _pointNE.getX() - _pointSW.getX();
    }

    public int getHeight(){
        return _pointNE.getY() - _pointSW.getY();
    }

    public void setWidth(int w){
        if(w>0)
            _pointNE.setX(_pointSW.getX() + w);
    }
    public void setHeight(int h){
        if(h>0)
            _pointNE.setY(_pointSW.getY() + h);
    }
    public void setPointSW(Point p){
        _pointSW = new Point(p);
    }

    public String toString(){
        return "Width="+getWidth()+" Height="+getHeight()+" PointSW="+_pointSW;
    }
    public int getPerimeter(){
        return 2*getWidth() + 2*getHeight();
    }
    public int getArea(){
        return getWidth()*getHeight();
    }
    public void move(int deltaX,int deltaY){
        if(((_pointSW.getX() + deltaX) > 0) &&((_pointSW.getY() + deltaY) > 0)){
            /*
                Note:
                that checking pointNE for positivity is redundant,
                if neither x,y of pointSW are negative after move NE point surly isn't
            */
            _pointSW.move(deltaX,deltaY);
            _pointNE.move(deltaX,deltaY);
        }
    }
    public boolean equals(RectangleB other){
        return (
                _pointSW.equals(other.getPointSW())&&
                    _pointNE.equals(other.getPointNE())
        );
    }
    public double getDiagonalLength(){
        return _pointSW.distance(_pointNE);
    }
    public boolean isLarger(RectangleB other){
        return getArea()>other.getArea();
    }
    public Point getPointNE(){
        return new Point(_pointNE);
    }
    public void changeSides(){
        int initalWidth = getWidth();
        int initalHeight = getHeight();
        _pointNE.setX(_pointSW.getX()+initalHeight);
        _pointNE.setY(_pointSW.getX()+initalWidth);
    }
    public boolean isIn (RectangleB r){
        Point otherNEPoint = r.getPointNE();
        Point otherSWPoint = r.getPointSW();

        return  !_pointSW.isUnder(otherSWPoint)&&
                !_pointSW.isLeft(otherSWPoint)&&
                !_pointNE.isAbove(otherNEPoint)&&
                !_pointNE.isRight(otherNEPoint);
    }

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
        if(!topPoint.isUnder(_pointSW) && !bottomPoint.isAbove(_pointNE)){
            if(!topPoint.isLeft(_pointSW) && !topPoint.isRight(_pointNE)){
                return true;
            }
        }
        return false;
    }
    private boolean _lineInterceptsHorizontal(Point leftPoint,Point rightPoint){
        if(!leftPoint.isUnder(_pointSW) && !leftPoint.isAbove(_pointNE)){
            if(!leftPoint.isRight(_pointNE) && !rightPoint.isLeft(_pointSW)){
                return true;
            }
        }
        return false;
    }
}