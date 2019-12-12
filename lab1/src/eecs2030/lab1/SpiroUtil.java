package eecs2030.lab1;

public class SpiroUtil
{
	public static final double BIG_WHEEL_RADIUS = 1.0;
	private static final double DEGREES_TO_RADIANS = Math.PI / 180; // multiply by degrees to get radians

	public static double hypoX(double wheelRadius, double pencilRadius, double degrees)
	{
		if (wheelRadius < 0.0)
		{
			throw new IllegalArgumentException("wheel radius is negative");
		}
		if (wheelRadius > SpiroUtil.BIG_WHEEL_RADIUS)
		{
			throw new IllegalArgumentException("wheel radius is greater than SpiroUtil.BIG_WHEEL_RADIUS");
		}
		if (pencilRadius < 0.0)
		{
			throw new IllegalArgumentException("pencil radius is negative");
		}
		if (pencilRadius > wheelRadius)
		{
			throw new IllegalArgumentException("pencil radius is greater than wheel radius");
		}

		double rL = SpiroUtil.BIG_WHEEL_RADIUS, rS = wheelRadius, h = pencilRadius,
				rad = degrees * SpiroUtil.DEGREES_TO_RADIANS;

		return (rL - rS) * Math.cos(rad) + (h * Math.cos((rL - rS) * rad / rS));
	}

	public static double hypoY(double wheelRadius, double pencilRadius, double degrees)
	{
		if (wheelRadius < 0.0)
		{
			throw new IllegalArgumentException("wheel radius is negative");
		}
		if (wheelRadius > SpiroUtil.BIG_WHEEL_RADIUS)
		{
			throw new IllegalArgumentException("wheel radius is greater than SpiroUtil.BIG_WHEEL_RADIUS");
		}
		if (pencilRadius < 0.0)
		{
			throw new IllegalArgumentException("pencil radius is negative");
		}
		if (pencilRadius > wheelRadius)
		{
			throw new IllegalArgumentException("pencil radius is greater than wheel radius");
		}

		double rL = SpiroUtil.BIG_WHEEL_RADIUS, rS = wheelRadius, h = pencilRadius,
				rad = degrees * SpiroUtil.DEGREES_TO_RADIANS;

		return (rL - rS) * Math.sin(rad) - (h * Math.sin((rL - rS) * rad / rS));
	}

	public static Point2 hypo(double wheelRadius, double pencilRadius, double degrees)
	{
		if (wheelRadius < 0.0)
		{
			throw new IllegalArgumentException("wheel radius is negative");
		}
		if (wheelRadius > SpiroUtil.BIG_WHEEL_RADIUS)
		{
			throw new IllegalArgumentException("wheel radius is greater than SpiroUtil.BIG_WHEEL_RADIUS");
		}
		if (pencilRadius < 0.0)
		{
			throw new IllegalArgumentException("pencil radius is negative");
		}
		if (pencilRadius > wheelRadius)
		{
			throw new IllegalArgumentException("pencil radius is greater than wheel radius");
		}

		double newX = hypoX(wheelRadius, pencilRadius, degrees);
		double newY = hypoY(wheelRadius, pencilRadius, degrees);

		return new Point2(newX, newY);
	}
}
