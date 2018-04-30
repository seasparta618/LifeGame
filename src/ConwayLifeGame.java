
/**
 * 
 * @author Leon Wang
 * @email dev.leon618@gmail.com, seasparta618@gmail.com
 * @description
 */
public class ConwayLifeGame {

	// set up the range for the matrix, which is within 200x200
	private final static int WIDTH = 200;
	private final static int HEIGHT = 200;

	// 200x200 matrix for the cells
	private static int[][] matrix = new int[WIDTH][HEIGHT];
	// 200x200 matrix for the next generation
	private static int[][] nextGeneration = new int[WIDTH][HEIGHT];

	/**
	 * constructor, set up the value for the matrix
	 */
	ConwayLifeGame() {
		for (int col = 0; col < WIDTH; col++) {
			for (int row = 0; row < HEIGHT; row++) {
				matrix[col][row] = 0;
			}
		}
	}

	/**
	 * 
	 * @param targetCol
	 *            the target colmun number of the cell
	 * @param targetRow
	 *            the target row number of the cell
	 * @return if {out of bound}, return 0 means no cell else return the value for
	 *         the cell, 0 presents no living cell, 1 presents living cell
	 */
	private int cellState(int targetCol, int targetRow) {
		// once the matrix is out of index, return a value of 0
		// which means outside the matrix there is no living cell
		if (targetCol < 0 || targetCol >= WIDTH || targetRow < 0 || targetRow >= HEIGHT) {
			return 0;
		} else {
			return matrix[targetCol][targetRow];
		}
	}

	/**
	 * 
	 * @param col
	 *            the column value for the cell
	 * @param row
	 *            the row value for the cell
	 * @return how many living neibours are around the cell
	 */
	private int countNeibour(int col, int row) {
		int count = 0;
		// left top cell of current
		count += cellState(col - 1, row - 1);
		// top cell of current
		count += cellState(col, row - 1);
		// right top cell of current
		count += cellState(col + 1, row - 1);
		// left side cell of current
		count += cellState(col - 1, row);
		// right side cell of current
		count += cellState(col + 1, row);
		// left bottom cell of current
		count += cellState(col - 1, row + 1);
		// bottom cell of current
		count += cellState(col, row + 1);
		// right bottom cell of current
		count += cellState(col + 1, row + 1);
		return count;
	}

}