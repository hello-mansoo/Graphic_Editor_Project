package transformer;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import shape.GShape;

public class GRotator extends GTransformer {
	private Point rotatePoint;
	private Vector<GShape> gChildList;
	
    @Override
    public void initTransforming(Graphics2D graphics2D, int x, int y) {
    	this.setOldPoint(x,y);
    	rotatePoint = new Point((int)gShape.getShape().getBounds().getCenterX(),(int)gShape.getShape().getBounds().getCenterY());
    }
    
    private double computeRotateAngle(Point centerP, Point startP, Point endP) {
    	double startAngle = Math.toDegrees(Math.atan2(startP.getY() - centerP.getY(), startP.getX()-centerP.getX()));
    	double endAngle = Math.toDegrees(Math.atan2(endP.getY()-centerP.getY(), endP.getX()-centerP.getX()));
    	double angle = endAngle - startAngle;
    	
    	if(angle<0) {
    		angle += 360;
    	}
    	
    	return angle;
    }

    @Override
    public void keepTransforming(Graphics2D graphics2D, int x, int y) {
    	this.getgShape().draw(graphics2D);
    	AffineTransform affineShape = new AffineTransform();
    	double rotateAngle = Math.toRadians(computeRotateAngle(rotatePoint, oldPoint, new Point(x,y)));
    	affineShape.rotate(rotateAngle, this.rotatePoint.getX(), this.rotatePoint.getY());
    	
    	this.gShape.setShape(affineShape.createTransformedShape(this.gShape.getShape()));
    	this.getgShape().draw(graphics2D);
    	this.setOldPoint(x,y);
    }

    @Override
    public void finishTransforming(Graphics2D graphics2D, int x, int y) {

    }
}
