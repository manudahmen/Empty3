package info.emptycanvas.library.physics;

import java.awt.Color;

import info.emptycanvas.library.object.Point3D;

public interface IPoint {
	public Color getColor();

	public void setColor(Color color);

	public Point3D getPosition();

	public void setPosition(Point3D position);

	public Point3D getVitesse();

	public void setVitesse(Point3D vitesse);

	public double getAttraction();

	public void setAttraction(double attraction);

	public double getRepulsion();

	public void setRepulsion(double repulsion);

	public double getMasse();

	public void setMasse(double masse);

	public double getAmortissement();

	public void setAmortissement(double amortissement);

}