/**
 * Matala 13 part b:
 * This class defines the object Matrix that describes a 2D array of integers
 * between 0 and 255, that represent the shade of gray in the picture.
 * Author: Maayan Chechik 
 * Date: 20/04/19
 */
public class Matrix {
	private int[][] _mat;
	private final int MAX = 255;
	private final int DEFAULT = 0;

	/**
	 * Constructs and initializes the object Matrix, using a 2D array to use
	 * it's size and values
	 * 
	 * @param array 2D array to put as the Matrix's array
	 */
	public Matrix(int[][] array) {
		_mat = new int[array.length][array[0].length];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				_mat[i][j] = array[i][j];
			}
		}
	}

	/**
	 * Constructs and initializes the object Matrix to 0, using the received sizes
	 * for the lengths of the Matrix's array.
	 * 
	 * @param size1 number of rows in Matrix's array
	 * @param size2 number of columns in Matrix's array
	 */
	public Matrix(int size1, int size2) {
		_mat = new int[size1][size2];
		for (int i = 0; i < _mat.length; i++) {
			for (int j = 0; j < _mat[0].length; j++) {
				_mat[i][j] = DEFAULT;
			}
		}
	}

	/**
	 * returns a string representation of this Matrix
	 * 
	 * @return String cell00+"\t"+cell01+"\t"+...+cell0n+"\n"+cell10+"\t"+cell11+...
	 */
	public String toString() {
		String st = "";
		for (int i = 0; i < _mat.length; i++) {
			for (int j = 0; j < _mat[0].length; j++) {
				st += _mat[i][j];
				// uses tab or line according to end of line or not
				if (j != _mat[0].length - 1) {
					st += "\t";
				} else {
					st += "\n";
				}
			}
		}
		return st;
	}

	/**
	 * Creates and returns the negative matrix to this Matrix, with each cell
	 * being the completing number to 255.
	 * 
	 * @return negative Matrix to this Matrix
	 */
	public Matrix makeNegative() {
		Matrix neg = new Matrix(_mat);
		for (int i = 0; i < _mat.length; i++) {
			for (int j = 0; j < _mat[0].length; j++) {
				neg._mat[i][j] = MAX - _mat[i][j];
			}
		}
		return neg;
	}// SHAY: Correct

	/**
	 * Creates and returns a Matrix with each value filtered to it's average
	 * with it's neighbors.
	 * 
	 * @return average filtered Matrix
	 */
	public Matrix imageFilterAverage() {
		Matrix blur = new Matrix(_mat);
		int sum = 0;
		int count = 0;
		int avg = 0;
		for (int i = 0; i < _mat.length; i++) {
			for (int j = 0; j < _mat[0].length; j++) {
				// checks the amount of neighbors and calculates the average accordingly
				sum = _mat[i][j];
				count = 1;
				if (j != 0) {
					sum += _mat[i][j - 1];
					count++;
					if (i != 0) {
						sum += _mat[i - 1][j - 1];
						count++;
					}
					if (i != _mat.length - 1) {
						sum += _mat[i + 1][j - 1];
						count++;
					}
				}
				if (j != _mat[0].length - 1) {
					sum += _mat[i][j + 1];
					count++;
					if (i != 0) {
						sum += _mat[i - 1][j + 1];
						count++;
					}
					if (i != _mat.length - 1) {
						sum += _mat[i + 1][j + 1];
						count++;
					}
				}
				if (i != 0) {
					sum += _mat[i - 1][j];
					count++;
				}
				if (i != _mat.length - 1) {
					sum += _mat[i + 1][j];
					count++;
				}
				avg = sum / count;
				blur._mat[i][j] = avg;
			}
		}
		return blur;
	}

	// Private method to help methods rotateClockwise and rotateCounterClockwise:
	// If the received CW boolean is true then the method returns this matrix
	// rotated clockWise and if false the rotation it returns this matrix rotated
	// counterClockWise.
	private Matrix rotate(boolean CW) {
		Matrix rotated = new Matrix(_mat[0].length, _mat.length);
		for (int i = 0; i < _mat.length; i++) {
			for (int j = 0; j < _mat[0].length; j++) {
				// Decides the placement of each value according to CW or CCW.
				if (CW) {
					rotated._mat[j][rotated._mat[0].length - 1 - i] = _mat[i][j];
				} else {
					rotated._mat[rotated._mat.length - 1 - j][i] = _mat[i][j];
				}
			}
		}
		return rotated;
	}

	/**
	 * creates and returns a clockwise rotation of this Matrix
	 * 
	 * @return this matrix rotated clockwise
	 */
	public Matrix rotateClockwise() {
		Matrix rotatedCW = rotate(true);	// true = this matrix rotated clockwise
		return rotatedCW;
	}

	/**
	 * creates and returns a counter clockwise rotation of this Matrix
	 * 
	 * @return this matrix rotated counter clockwise
	 */
	public Matrix rotateCounterClockwise() {
		Matrix rotatedCCW = rotate(false);	// false = this matrix rotated counter clockwise
		return rotatedCCW;
	}
}