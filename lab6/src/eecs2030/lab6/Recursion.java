package eecs2030.lab6;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility class containing recursive methods.
 * 
 * @author EECS2030 Fall 2016
 *
 */
public class Recursion {

	// DON'T ADD ANY STATIC FIELDS; YOU DON'T STATIC FIELDS AND YOUR METHODS
	// WON'T WORK CORRECTLY IF YOU USE STATIC FIELDS

	private Recursion() {
	}

	/**
	 * Return the sum of the integers 1 through n where n is a strictly positive
	 * integer. Note that the sum might overflow if n is too large; this method
	 * does not check if the sum overflows (i.e., it's the client's problem).
	 * 
	 * @pre n is greater than 0
	 * 
	 * @param n
	 *            a strictly positive number
	 * @return the sum 1 + 2 + ... + n
	 */
	public static int sum(int n) {
		if (n == 1)
			return 1;
		return n + sum(n - 1);
	}

	/**
	 * Returns a new string equal to the reversal of string s. The reversal of
	 * the empty string is equal to the empty string.
	 * 
	 * @pre s is not null
	 * 
	 * @param s
	 *            a string
	 * @return a string equal to the reversal of s
	 */
	public static String reverse(String s) {
		if (s.length() < 2)
			return s;
		return reverse(s.substring(1)) + s.charAt(0);
	}

	/**
	 * Returns the minimum element in the list t. This method does not modify
	 * the list <code>t</code>.
	 * 
	 * @pre t.size() is greater than 0
	 * 
	 * @param t
	 *            a non-empty list
	 * @return the minimum element in t
	 */
	public static int min(List<Integer> t) {
		if (t.size() == 1)
			return t.get(0);
		return Math.min(t.get(0), min(t.subList(1, t.size())));
	}

	/**
	 * Downsample a picture <code>n</code> times by a factor of 2 using
	 * recursion. See the lab problem for a description of downsampling.
	 * 
	 * @pre the width and height of the picture are both multiples of 2 to the
	 *      power n
	 * 
	 * @pre1 n is greater than or equal to zero
	 * 
	 * @param p
	 *            the picture to downsample
	 * @param n
	 *            the number of times to downsample the picture by a factor of 2
	 * @return the downsampled picture
	 */

	public static Picture downsample(Picture p, int n) {
		// base case 1
		if (n == 0)
			return new Picture(p);

		// base case 2
		if (n != 1)
			return downsample(downsample(p, 1), n - 1);

		// minimum is 1x1 pixels
		Picture pic = new Picture(-Math.floorDiv(-p.width(), 2), -Math.floorDiv(-p.height(), 2));
		Color[][] color = clr(p.width(), p.height(), p);
		for (int i = 0; i < pic.width(); i++)
			for (int g = 0; g < pic.height(); g++)
				pic.set(i, g, color[i][g]);
		return pic;
	}

	/**
	 * Binary search for the string <code>s</code> in a list <code>t</code>. If
	 * <code>s</code> is in the list, then this method returns the index of the
	 * location of <code>s</code> in <code>t</code>; otherwise, this method
	 * returns the index where <code>s</code> would be located if it were to be
	 * inserted into the list <code>t</code>.
	 * 
	 * <p>
	 * This method does not modify the list <code>t</code>.
	 * 
	 * @pre t.size() is zero or more
	 * @pre1 t is in sorted order
	 * @pre2 t has no duplicate elements
	 * 
	 * @param s
	 *            a string
	 * @param t
	 *            a list
	 * @return the index of s if s is in the list; otherwise, returns the index
	 *         where s would be located if it were to be inserted into the list
	 */
	public static int bsearch(String s, List<String> t) {
		if (t.size() == 0)
			return 0;

		if (t.size() == 1) {
			if (s.compareTo(t.get(0)) > 0)
				return 1;
			else
				return 0;
		}

		List<String> list = t;

		int middle = (list.size() + 1) / 2;

		if (s.compareTo(list.get(middle)) < 0) {
			list = list.subList(0, middle);
			return bsearch(s, list);
		} else if (s.compareTo(list.get(middle)) > 0) {
			list = list.subList(middle, list.size());
			return middle + bsearch(s, list);
		} else {
			return middle;
		}

	}

	private static Color[][] clr(int x, int y, Picture p) {
		Color[][] value = new Color[x][y];
		for (int i = 0; i < x; i += 2) {
			for (int j = 0; j < y; j += 2) {
				int r = 0, g = 0, b = 0, count = 0;
				for (int s = 0; s < 2; s++) {
					for (int t = 0; t < 2; t++) {
						try {
							r += p.get(i + s, j + t).getRed();
							count++;
						} catch (Exception e) {
							int a = 1; // tester doesn't allow empty catches
						}
					}
				}

				for (int s = 0; s < 2; s++) {
					for (int t = 0; t < 2; t++) {
						try {
							g += p.get(i + s, j + t).getGreen();
						} catch (Exception e) {
							int a = 1; // tester doesn't allow empty catches
						}
					}
				}

				for (int s = 0; s < 2; s++) {
					for (int t = 0; t < 2; t++) {
						try {
							b += p.get(i + s, j + t).getBlue();
						} catch (Exception e) {
							int a = 1; // tester doesn't allow empty catches
						}
					}
				}
				value[i / 2][j / 2] = new Color(r / count, g / count, b / count);
			}
		}

		return value;
	}

	private static Picture expandDong(Picture p, int n) {
		if (n == 0) {
			return new Picture(p);
		}

		Picture pic = new Picture(p.width() * 2, p.height() * 2);

		for (int i = 0; i < p.width(); i++)
			for (int g = 0; g < p.height(); g++) {
				Color color = p.get(i, g);
				pic.set(2 * i, 2 * g, color);
				pic.set(2 * i + 1, 2 * g, color);
				pic.set(2 * i, 2 * g + 1, color);
				pic.set(2 * i + 1, 2 * g + 1, color);
			}

		return expandDong(pic, n - 1);
	}

	public static void main(String[] args) {
		// RUN THIS TO TEST downsample

		Picture p = new Picture("elk.jpg");
		p.show();
		downsample(p, 11).show();
		downsample(p, 2).show();
		downsample(p, 3).show();

		List<String> strings = new ArrayList<String>();
		for (int i = 0; i < 26; i++)
			strings.add((char) ((int) ('a') + i) + "");

		System.out.println(sum(4)); // 4 + 3 + 2 + 1
		String abc = "";
		for (String s : strings)
			abc += s;
		System.out.println(reverse(abc));

		System.out.println(bsearch("f", strings));

		List<Integer> nums = new ArrayList<Integer>();
		nums.add(-100);
		nums.add(4);
		nums.add(5);
		nums.add(44);
		nums.add(0);
		nums.add(-2);
		nums.add(-111);
		System.out.println(min(nums));

		/*
		 * Picture p = new Picture("elk.jpg"); p.show();
		 * 
		 * expandDong(downsample(p, 1), 0).show();
		 * 
		 * List<String> strings = new ArrayList<String>(); for (int i = 0; i <
		 * 26; i++) strings.add((char) ((int) ('a') + i) + "");
		 * 
		 * System.out.println(sum(4)); // 4 + 3 + 2 + 1 String abc = ""; for
		 * (String s : strings) abc += s; System.out.println(reverse(abc));
		 * 
		 * System.out.println(bsearch("z", strings));
		 */

	}
}