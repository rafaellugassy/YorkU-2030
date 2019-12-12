package eecs2030.lab4;

import java.awt.Color;

public class SBlock extends Block {

	private int orientation = 0;
	private static BlockGrid[] type = setupTypes();

	public SBlock(Point2 pos, Color col) {
		super(3, pos, col);
		this.grid = type[orientation];
		draw();
	}

	private static BlockGrid[] setupTypes() {
		BlockGrid[] newGrids = new BlockGrid[4];

		for (int i = 0; i < 4; i++)
			newGrids[i] = new BlockGrid(3);

		for (int i = 1; i < 3; i++)
			newGrids[0].set(0, i);
		for (int i = 0; i < 2; i++)
			newGrids[0].set(1, i);

		for (int i = 0; i < 2; i++)
			newGrids[1].set(i, 1);
		for (int i = 1; i < 3; i++)
			newGrids[1].set(i, 2);

		for (int i = 0; i < 2; i++)
			newGrids[2].set(2, i);
		for (int i = 1; i < 3; i++)
			newGrids[2].set(1, i);

		for (int i = 0; i < 2; i++)
			newGrids[3].set(i, 0);
		for (int i = 1; i < 3; i++)
			newGrids[3].set(i, 1);

		return newGrids;
	}

	@Override
	public void spinLeft() {
		orientation--;
		if (orientation < 0)
			orientation = 3;

		this.grid = type[orientation];
	}

	@Override
	public void spinRight() {
		orientation++;
		if (orientation > 3)
			orientation = 0;

		this.grid = type[orientation];
	}

}