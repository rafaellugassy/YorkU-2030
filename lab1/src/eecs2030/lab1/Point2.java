package eecs2030.lab1;

/**
 * A simple class for representing points in 2D Cartesian coordinates. Every
 * Point2D instance has an x and y coordinate.
 * 
 * @author EECS2030 Fall 2016
 *
 */
public class Point2
{
	private double x;
	private double y;

	public Point2()
	{
		this.x = 0.0;
		this.y = 0.0;
	}

	public Point2(double newX, double newY)
	{
		this.x = newX;
		this.y = newY;
	}

	public double getX()
	{
		return this.x;
	}

	public double getY()
	{
		return this.y;
	}

	public void set(double newX, double newY)
	{
		this.x = newX;
		this.y = newY;
	}

	public void setX(double newX)
	{
		this.x = newX;
	}

	public void setY(double newY)
	{
		this.y = newY;
	}

	public String toString()
	{
		return "(" + this.x + ", " + this.y + ")";
	}
}