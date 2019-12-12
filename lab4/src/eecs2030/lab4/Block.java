package eecs2030.lab4;

import java.awt.Color;
import princeton.introcs.StdDraw;

/**
 * Abstract base class for standard Tetris blocks. Blocks can move, spin, and
 * draw themselves.
 * 
 * @author EECS2030 Fall 2016
 * 
 */
public abstract class Block {

	/**
	 * The grid that represents the block in its current orientation.
	 */
	protected BlockGrid grid;

	/**
	 * The position on the playing field of the top left corner of the grid.
	 */
	protected Point2 position;

	/**
	 * The color of the block.
	 */
	protected Color color;

	/**
	 * Construct a block given its grid size, position, and color.
	 * <code>this.grid</code> will be a <code>BlockGrid</code> of size
	 * <code>gridSize</code>-by-<code>gridSize</code> with all grid locations
	 * cleared.
	 * 
	 * <p>
	 * This constructor should be called only by direct child classes.
	 * 
	 * @pre. <code>gridSize > 0</code>
	 * 
	 * @param gridSize
	 *            the size of the grid
	 * @param pos
	 *            the position on the playing field of the top-left corner of
	 *            the grid
	 * @param col
	 *            the color of the block
	 */
	protected Block(int gridSize, Point2 pos, Color col) {
		this.grid = new BlockGrid(gridSize);
		this.position = pos;
		this.color = col;
	}

	/**
	 * Draw the block to the playing field in its current position and
	 * orientation using the specified color.
	 * 
	 * @param c
	 *            the color to draw with
	 */
	protected void draw(Color c) {
		for (int i = 0; i < this.grid.size(); i++) {
			for (int j = 0; j < this.grid.size(); j++) {
				if (this.grid.get(i, j)) {
					Point2 topLeft = new Point2(this.position.getX() + j, this.position.getY() - i);
					StdDraw.setPenColor(c);
					StdDraw.filledSquare(topLeft.getX() + 0.5, topLeft.getY() - 0.5, 0.5);
				}
			}
		}
	}

	/**
	 * Draw the block on the playing field in its current position and
	 * orientation.
	 * 
	 */
	public void draw() {
		this.draw(this.color);
	}

	/**
	 * Moves the block to the left by 1 grid position.
	 * 
	 */
	public void moveLeft() {
		this.position.setX(this.position.getX() - 1);
	}

	/**
	 * Moves the block to the right by 1 grid position.
	 * 
	 */
	public void moveRight() {
		this.position.setX(this.position.getX() + 1);
	}

	/**
	 * Moves the block down by 1 grid position.
	 * 
	 */
	public void moveDown() {
		this.position.setY(this.position.getY() - 1);
		draw();
	}

	/**
	 * Moves the block up by 0 grid positions. This should do nothing.
	 * 
	 */
	public void moveUp() {
		this.position.setY(this.position.getY() + 1);
	}

	/**
	 * Get the position on the playing field of the top-left corner of the
	 * block's grid. The block's position cannot be modified using the reference
	 * returned by this method.
	 * 
	 * @return the position on the playing field of the top-left corner of the
	 *         block's grid
	 */
	public Point2 getPosition() {
		return new Point2(position);
	}

	/**
	 * Spin the object to the left (counter clockwise) about its center of
	 * rotation.
	 */
	public abstract void spinLeft();

	/**
	 * Spin the object to the right (clockwise) about its center of rotation.
	 */
	public abstract void spinRight();
}
