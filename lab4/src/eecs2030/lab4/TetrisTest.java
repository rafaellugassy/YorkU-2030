package eecs2030.lab4;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import princeton.introcs.StdDraw;

public class TetrisTest implements ActionListener {

	static {
		StdDraw.setCanvasSize(10 * 40, 20 * 40);
		StdDraw.setXscale(0.0, 10.0);
		StdDraw.setYscale(0.0, 20.0);
	}

	private Timer timer;
	private Block block;
	private boolean isFalling;

	public TetrisTest() {
		this.timer = new Timer(500, this);
		this.isFalling = false;
	}

	public void run() {
		this.block = TetrisTest.randomBlock();
		this.timer.start();

		while (true) {
			StdDraw.show(0);
			StdDraw.clear();
			this.block.draw();
			StdDraw.show(0);
			if (StdDraw.hasNextKeyTyped()) {
				char c = StdDraw.nextKeyTyped();
				if (c == 'j') {
					this.block.moveLeft();
				} else if (c == 'l') {
					this.block.moveRight();
				} else if (c == 'i') {
					this.block.spinLeft();
				} else if (c == 'k') {
					this.block.spinRight();
				} else if (c == 's') {
					this.isFalling = !this.isFalling;
				} else if (c == 'q') {
					this.timer.stop();
					break;
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.isFalling) {
			this.block.moveDown();
			if (this.block.getPosition().getY() < -0.1) {
				this.block = TetrisTest.randomBlock();
				this.block.draw();
			}
		}
	}

	public static Block randomBlock() {
		Random r = new Random();
		int val = r.nextInt(3);
		Block b = null;
		switch (val) {
		case 0:
			b = new IBlock(new Point2(4, 20), Color.CYAN);
			break;
		case 1:
			b = new JBlock(new Point2(4, 20), Color.BLUE);
			break;
		case 2:
			b = new SBlock(new Point2(4, 20), Color.GREEN);
			break;
		}
		return b;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TetrisTest test = new TetrisTest();
		test.run();
		StdDraw.close();
	}

}