import java.util.*;

public class PuzzleSolver{
	public static void main(String[] args) {
		int[] blankPos = new int[2]; // position of the blank block
		int temp;
		int minNumberIncorrect = 10, minIndex = 0;
		int solutionNumber = 0, itteration = 0;

		// The set of nodes already evaluated
		ArrayList<int[][]> closedSet = new ArrayList<int[][]>();

		// The set of currently discovered nodes that are not evaluated yet.
		// Initially, only the start node is known.
		ArrayList<int[][]> openSet = new ArrayList<int[][]>();

		// The previous step.
		ArrayList<Integer> cameFrom = new ArrayList<Integer>();
		ArrayList<int[][]> allSteps = new ArrayList<int[][]>();

		int[][] puzzle = new int[][] { { 2, 5, 8 }, { 1, 0, 4 }, { 7, 3, 6 } };// Starting scenario
		int[][] finalCase = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
		openSet.add(puzzle);
		allSteps.add(clone2dArray(puzzle));
		cameFrom.add(0);

		while (openSet.size() > 0) {
			// Find the one with the lowest number of incorrect from openSet
			int[] numbIncorrect = new int[openSet.size()];
			for (int i = 0; i < openSet.size(); i++) {
				numbIncorrect[i] = compareNumberMissplaced(openSet.get(i), finalCase);
				if (numbIncorrect[i] < minNumberIncorrect) {
					minNumberIncorrect = numbIncorrect[i];
					minIndex = i;
				}
			}

			// The current one will be "puzzle", which is the previously found one.
			puzzle = clone2dArray(openSet.get(minIndex).clone());
			blankPos = blankPosition(puzzle);
			// Print it out
			System.out.print("\nNumber of incorrect: " + minNumberIncorrect);
			printBoard(openSet.get(minIndex).clone());
			// remove from open set.
			openSet.remove(minIndex);
			// Add the current one to closedSet.
			closedSet.add(clone2dArray(puzzle));

			// Uppdatera solutionnumber så det blir samma nummer som lösningen har i
			// allSteps
			solutionNumber = findSolution(puzzle, allSteps);
			System.out.print("\nnumber in allSteps: " + solutionNumber + "\n");

			if (minNumberIncorrect == 0) { // if there is no inccorect, the solution is found!
				System.out.print("Final solution found!");
				break;
			} else {// Else find the childs of the current one, max four different. Up, down, left &
					// right move.

				if (blankPos[0] >= 1) {
					// Move blank up
					temp = puzzle[blankPos[0] - 1][blankPos[1]];
					puzzle[blankPos[0] - 1][blankPos[1]] = 0;
					puzzle[blankPos[0]][blankPos[1]] = temp;

					// Check if the child is already in closedSet or openSet
					boolean check1 = contains2dArray(puzzle, openSet);
					boolean check2 = contains2dArray(puzzle, closedSet);

					if (!check1 && !check2) {
						openSet.add(clone2dArray(puzzle));
						// Save the steps
						allSteps.add(clone2dArray(puzzle));
						cameFrom.add(solutionNumber);

					} else if (check1) {
						// if shorter path, give the state on open the short path
						int a = pathLength(cameFrom, solutionNumber);
						int b = findSolution(puzzle, allSteps);
						int c = pathLength(cameFrom, b);
						System.out.println("Pathlength: ");
						System.out.println(a);
						System.out.println(c);
						if (a < c) {
							cameFrom.set(b, cameFrom.get(b));
						}

					} else if (check2) {
						int a = pathLength(cameFrom, solutionNumber);
						int b = findSolution(puzzle, allSteps);
						int c = pathLength(cameFrom, b);
						if (a < c) {
							openSet.add(clone2dArray(puzzle));
							// Save the steps
							allSteps.add(clone2dArray(puzzle));
							cameFrom.add(solutionNumber);

						}
					}

					// Move blank back to original position
					blankPos = blankPosition(puzzle);
					temp = puzzle[blankPos[0] + 1][blankPos[1]];
					puzzle[blankPos[0] + 1][blankPos[1]] = 0;
					puzzle[blankPos[0]][blankPos[1]] = temp;
					blankPos = blankPosition(puzzle);
				}

				if (blankPos[0] <= 1) {
					// Move blank down
					temp = puzzle[blankPos[0] + 1][blankPos[1]];
					puzzle[blankPos[0] + 1][blankPos[1]] = 0;
					puzzle[blankPos[0]][blankPos[1]] = temp;

					boolean check1 = contains2dArray(puzzle, openSet);
					boolean check2 = contains2dArray(puzzle, closedSet);
					if (!check1 && !check2) {
						openSet.add(clone2dArray(puzzle));
						allSteps.add(clone2dArray(puzzle));
						cameFrom.add(solutionNumber);
					} else if (check1) {
						// if shorter path, give the state on open the short path
						int a = pathLength(cameFrom, solutionNumber);
						int b = findSolution(puzzle, allSteps);
						int c = pathLength(cameFrom, b);
						System.out.println("Pathlength: ");
						System.out.println(a);
						System.out.println(c);
						if (a < c) {
							cameFrom.set(b, cameFrom.get(b));
						}

					} else if (check2) {
						int a = pathLength(cameFrom, solutionNumber);
						int b = findSolution(puzzle, allSteps);
						int c = pathLength(cameFrom, b);
						if (a < c) {
							openSet.add(clone2dArray(puzzle));
							// Save the steps
							allSteps.add(clone2dArray(puzzle));
							cameFrom.add(solutionNumber);

						}
					}

					blankPos = blankPosition(puzzle);
					temp = puzzle[blankPos[0] - 1][blankPos[1]];
					puzzle[blankPos[0] - 1][blankPos[1]] = 0;
					puzzle[blankPos[0]][blankPos[1]] = temp;
					blankPos = blankPosition(puzzle);
				}

				if (blankPos[1] >= 1) {
					// Move blank left
					temp = puzzle[blankPos[0]][blankPos[1] - 1];
					puzzle[blankPos[0]][blankPos[1] - 1] = 0;
					puzzle[blankPos[0]][blankPos[1]] = temp;

					boolean check1 = contains2dArray(puzzle, openSet);
					boolean check2 = contains2dArray(puzzle, closedSet);
					if (!check1 && !check2) {
						openSet.add(clone2dArray(puzzle));
						allSteps.add(clone2dArray(puzzle));
						cameFrom.add(solutionNumber);
					} else if (check1) {
						// if shorter path, give the state on open the short path
						int a = pathLength(cameFrom, solutionNumber);
						int b = findSolution(puzzle, allSteps);
						int c = pathLength(cameFrom, b);
						System.out.println("Pathlength: ");
						System.out.println(a);
						System.out.println(c);
						if (a < c) {
							cameFrom.set(b, cameFrom.get(b));
						}

					} else if (check2) {
						int a = pathLength(cameFrom, solutionNumber);
						int b = findSolution(puzzle, allSteps);
						int c = pathLength(cameFrom, b);
						if (a < c) {
							openSet.add(clone2dArray(puzzle));
							// Save the steps
							allSteps.add(clone2dArray(puzzle));
							cameFrom.add(solutionNumber);

						}
					}

					blankPos = blankPosition(puzzle);
					temp = puzzle[blankPos[0]][blankPos[1] + 1];
					puzzle[blankPos[0]][blankPos[1] + 1] = 0;
					puzzle[blankPos[0]][blankPos[1]] = temp;
					blankPos = blankPosition(puzzle);
				}

				if (blankPos[1] <= 1) {
					// Move blank right
					temp = puzzle[blankPos[0]][blankPos[1] + 1];
					puzzle[blankPos[0]][blankPos[1] + 1] = 0;
					puzzle[blankPos[0]][blankPos[1]] = temp;

					boolean check1 = contains2dArray(puzzle, openSet);
					boolean check2 = contains2dArray(puzzle, closedSet);
					if (!check1 && !check2) {
						openSet.add(clone2dArray(puzzle));
						allSteps.add(clone2dArray(puzzle));
						cameFrom.add(solutionNumber);
					} else if (check1) {
						// if shorter path, give the state on open the short path
						int a = pathLength(cameFrom, solutionNumber);
						int b = findSolution(puzzle, allSteps);
						int c = pathLength(cameFrom, b);
						System.out.println("Pathlength: ");
						System.out.println(a);
						System.out.println(c);
						if (a < c) {
							cameFrom.set(b, cameFrom.get(b));
						}

					} else if (check2) {
						int a = pathLength(cameFrom, solutionNumber);
						int b = findSolution(puzzle, allSteps);
						int c = pathLength(cameFrom, b);
						if (a < c) {
							openSet.add(clone2dArray(puzzle));
							// Save the steps
							allSteps.add(clone2dArray(puzzle));
							cameFrom.add(solutionNumber);

						}
					}

					blankPos = blankPosition(puzzle);
					temp = puzzle[blankPos[0]][blankPos[1] - 1];
					puzzle[blankPos[0]][blankPos[1] - 1] = 0;
					puzzle[blankPos[0]][blankPos[1]] = temp;
					blankPos = blankPosition(puzzle);
				}

			}
			if (itteration >= 2 && false) {
				break;
			}
			itteration++;
		}
		// Print the steps
		System.out.println("\n--------------------------\n");
		int i = cameFrom.size() - 1;
		while (i > 0) {
			printBoard(allSteps.get(i).clone());
			System.out.println("Solution #: " + i);
			i = cameFrom.get(i);
		}
		System.out.println("Path length: " + String.valueOf(pathLength(cameFrom, cameFrom.size() - 1)));
		System.out.println("Number of branches: " + String.valueOf(allSteps.size()));
	}

	public static int compareNumberMissplaced(int[][] a, int[][] b) {
		int numberOfIncorrect = 0;
		for (int i = 0; i < a.length; i++) {
			for (int y = 0; y < a.length; y++) {
				if (a[i][y] != b[i][y] && a[i][y] != 0) {
					// System.out.print("puzzle # : " + puzzle[i][y] + ", correct # = " +
					// finalCase[i][y] + "\n");
					numberOfIncorrect++;
				}
			}
		}
		return numberOfIncorrect;
	}

	public static int[] blankPosition(int a[][]) {
		int[] position = new int[2];

		for (int i = 0; i < a.length; i++) {
			for (int y = 0; y < a.length; y++) {
				if (a[i][y] == 0) {
					position[0] = i;
					position[1] = y;
				}
			}
		}
		return position;
	}

	public static void printBoard(int[][] a) {
		String tem = "\n";
		for (int i = 0; i < a.length; i++) {
			for (int y = 0; y < a.length; y++) {
				tem = tem + " " + String.valueOf(a[i][y]);
			}
			System.out.println(tem);
			tem = "";
		}
	}

	public static int[][] clone2dArray(int[][] matrix) {
		int[][] myInt = new int[matrix.length][];
		for (int i = 0; i < matrix.length; i++) {
			myInt[i] = matrix[i].clone();
		}
		return myInt;
	}

	public static boolean contains2dArray(int[][] arr, ArrayList<int[][]> arl) {
		boolean check1 = false;
		for (int i = 0; i < arl.size(); i++) {
			if (Arrays.deepEquals(arr, arl.get(i))) {
				check1 = true;
			}
		}
		return check1;
	}

	public static int findSolution(int[][] arr, ArrayList<int[][]> arl) {
		int index = 0;
		for (int i = 0; i < arl.size(); i++) {
			if (Arrays.deepEquals(arr, arl.get(i))) {
				index = i;
			}
		}
		return index;
	}

	public static int pathLength(ArrayList<Integer> arl, int start) {
		int i = start;
		int counter = 0;
		while (i > 0) {
			i = arl.get(i);
			counter++;
		}
		return counter;
	}

}
