public class Maze{
	/**	
	 * This method receives two 2D arrays and a sum and checks if there is a path
	 * in the array with the given sum. If so, it marks the path as the value 1, in
	 * one of the arrays and returns true. Otherwise, it returns false.
	 * 
	 * @param mat is the 2D array filled with numbers, in which we are looking for a path.
	 * @param sum is the sum that the path in mat should equal to.
	 * @param path is the 2D array filled with 0, the size of mat, in which the one of the correct paths is marked.
	 * @return true if there is a path in the with the received sum, false otherwise.
	 */
	public static boolean findSum(int mat[][], int sum, int path[][]) {
		return findSumStartPoint(mat, path, sum, 0, 0);
	}

	public static boolean findSumStartPoint(int[][] mat, int[][] path, int sum, int i, int j) {
		// Stop recursion when it's the last cell in the array and return sum of this cell
		if (i == mat.length - 1 && j == mat[i].length - 1) {
			if (mat[i][j] == sum)
				path[i][j] = 1;
			return mat[i][j] == sum;
		}
		// Search for a path that starts at [i,j].
		boolean foundPathFromHere = findSumPaths(mat, path, sum, 0, i, j);
		if (foundPathFromHere)
			return true;

		// Find a next starting point
		int nextRow;
		int nextCol;
		if (j == mat[i].length - 1) {
			nextRow = i + 1;
			nextCol = 0;
		} else {
			nextRow = i;
			nextCol = j + 1;
		}

		return findSumStartPoint(mat, path, sum, nextRow, nextCol);
	}

	public static boolean findSumPaths(int[][] mat, int[][] path, int sum, int currentSum, int i, int j) {
		path[i][j] = 1;
		currentSum += mat[i][j];
		// Stop recursion when the sum of this path is equal to or bigger than the wanted sum.
		if (sum == currentSum) {
			return true;
		}
		if (currentSum > sum) {
			path[i][j] = 0;
			return false;
		}
		// If there is a free cell to the right, try to extend the path to it.
		if ((path[i].length) > (j + 1) && path[i][j + 1] == 0) {
			if (findSumPaths(mat, path, sum, currentSum, i, j + 1))
				return true;
		}
		// If there is a free cell below, try to extend the path to it.
		if (path.length > (i + 1) && path[i + 1][j] == 0) {
			if (findSumPaths(mat, path, sum, currentSum, i + 1, j))
				return true;
		}
		// If there is a free cell to the left, try to extend the path to it.
		if (j > 0 && path[i][j - 1] == 0) {
			if (findSumPaths(mat, path, sum, currentSum, i, j - 1))
				return true;
		}
		// If there is a free cell above, try to extend the path to it.
		if (i > 0 && path[i - 1][j] == 0) {
			if (findSumPaths(mat, path, sum, currentSum, i - 1, j))
				return true;
		}
		// If there is no place to extend to, and the current sum is still smaller
		// than the wanted sum, return false.
		path[i][j] = 0;
		return false;
	}

}