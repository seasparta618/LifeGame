import java.util.Scanner;

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

	/**
	 * 
	 * @param count
	 *            use count to decide whether the cell is died or living or produced
	 * @param col
	 * @param row
	 * @return
	 */
	private int nextGeneration(int count, int col, int row) {
		// less than 2, under population, more than 3, over population
		if (count < 2 || count > 3) {
			return 0;
		}
		// three around, reproduce
		else if (count == 3) {
			return 1;
		}
		// directly return the status for the cell, no change for the current status
		else {
			return matrix[col][row];
		}
	}

	/**
	 * 
	 * @param numbers
	 *            use numbers to initialize the status for the cell every two
	 *            numbers is a part to present the colum and row for the living cell
	 */
	private void initializeMatrix(int[] numbers) {
		for (int index = 0; index < numbers.length; index += 2) {
			matrix[numbers[index]][numbers[index + 1]] = 1;
		}
	}

	public static void main(String[] ar) {
		System.out.println("input: ");
		// scan for the input
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		// use comma to split all the input numbers
		String[] args = input.split(",");
		int[] numbers = new int[args.length];
		int index = 0;
		for (String arg : args) {
			// just get the digit from the substring
			numbers[index] = Integer.parseInt(arg.replaceAll("\\D+", ""));
			index++;
		}
		ConwayLifeGame clg = new ConwayLifeGame();
		clg.initializeMatrix(numbers);

		System.out.println("Outout for the next 100 state: ");
		//loop for a hundred rounds
		for (int round = 1; round < 100; round++) {
			for (int col = 0; col < WIDTH; col++) {
				for (int row = 0; row < HEIGHT; row++) {
					int count = clg.countNeibour(col, row);
					if (count != 0) {
						// generate the next generation and stored into the nextGeneration array
						nextGeneration[col][row] = clg.nextGeneration(count, col, row);
					}
				}
			}
			//use string buffer to define the string, as the string will be changed later
			StringBuffer result = new StringBuffer("");
			result.append("[");
			System.out.print(round+": ");
			for (int col = 0; col < WIDTH; col++) {
				for (int row = 0; row < HEIGHT; row++) {
					//append the result into the the string, generate the output string
					matrix[col][row] = nextGeneration[col][row];
					if (nextGeneration[col][row] == 1) {
						result.append("[" + col + "," + row + "]");
						nextGeneration[col][row] = 0;
					}
				}
			}
			result.append("]");
			System.out.println(result);
		}

	}
}
