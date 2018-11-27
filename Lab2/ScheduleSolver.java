import java.util.ArrayList;

public class ScheduleSolver {

	public static void main(String[] args) {
		final int numOfHours = 8;
		final int numOfClassrooms = 3;

		// 1. Pick starting arrangement
		String[][] classroom = new String[][] { { "106", "", "302" }, { "205", "201", "402" }, { "101", "107", "403" },
				{ "202", "102", "103" }, { "502", "206", "105" }, { "204", "104", "501" }, { "301", "", "303" },
				{ "401", "203", "304" }, };
		System.out.print("Stating arrangement\n");
		displaySchedule(classroom);

		System.out.println(" ");
		int maxConflicts = 0;
		int maxConflictIndex = 0;
		while (true) {

			// 2. If there are no conflicts the prob is solved. Otherwise, continue to find
			// hour with most conflicts,

			// Find hour with most conflicts
			maxConflicts = 0;
			maxConflictIndex = 0;
			for (int i = 0; i < numOfHours; i++) {
				if (conflicts(classroom, i) > maxConflicts) {
					maxConflicts = conflicts(classroom, i);
					maxConflictIndex = i;
				}
			}

			if (maxConflicts == 0)
				break;

			// 3. Choose one of the conflicting seats.
			// Find which room to swap to get min conflicts.
			int maxConflictRoomIndex = 0;
			maxConflicts = 10;
			for (int i = 0; i < numOfClassrooms; i++) {
				String temp = classroom[maxConflictIndex][i];
				classroom[maxConflictIndex][i] = "";
				int conflict = conflicts(classroom, maxConflictIndex);
				if (conflict < maxConflicts) {
					maxConflicts = conflict;
					maxConflictRoomIndex = i;
				}
				classroom[maxConflictIndex][i] = temp;
			}

			System.out.println("\nMost conflicts during hour: " + (((maxConflictIndex + 8) % 12) + 1)
					+ ". Swaping class that is in room #" + (maxConflictRoomIndex + 1) + "\n");

			// 4. find seat to swap with that gives min conflicts, and swap.
			maxConflicts = 10;
			int conflicts = 10;
			String[][] myStr = new String[numOfHours][numOfClassrooms];
			for (int i = 0; i < numOfHours; i++) {
				for (int y = 0; y < numOfClassrooms; y++) {
					// Swap two classes and check number of conflicts
					String temp = classroom[i][y];
					classroom[i][y] = classroom[maxConflictIndex][maxConflictRoomIndex];
					classroom[maxConflictIndex][maxConflictRoomIndex] = temp;
					conflicts = conflicts(classroom);
					if (conflicts == 0) {
						break;
					} else if (conflicts < maxConflicts) {
						maxConflicts = conflicts;
						myStr = clone2dArray(classroom);
					}
					classroom[maxConflictIndex][maxConflictRoomIndex] = classroom[i][y];
					classroom[i][y] = temp;

				}
				if (conflicts == 0) {
					break;
				}
			}
			// Save the best one if solution isn't found.
			if (conflicts != 0)
				classroom = clone2dArray(myStr);

			// 5. Repeat
			displaySchedule(classroom);
			System.out.println("Number of hours with conflicts: " + conflicts(classroom));
		}

	}

	public static void displaySchedule(String[][] classroom) {
		System.out.println("        TP51    SP34    K3");
		System.out.println("       ------  ------  ------");
		for (int i = 0; i < 8; i++) {
			String str = ((i + 8) % 12) + 1 + "	" + classroom[i][0] + "	" + classroom[i][1] + "	" + classroom[i][2];
			System.out.println(str);
		}
	}

	public static int conflicts(String[][] classroom, int hour) {
		int count = 0;
		for (int i = 1; i < 3; i++) {
			if (classroom[hour][0].equals("")) {
				break;
			} else if (classroom[hour][i] == null || classroom[hour][i].equals("")) {

			} else if (classroom[hour][i].substring(0, 1).equals("501")
					&& classroom[hour][0].substring(0, 1).equals("502")) {
				// Do nothing, it is ok.
			} else if (classroom[hour][i].substring(0, 1).equals(classroom[hour][0].substring(0, 1)) && i != 0) {
				count++;
			}
		}
		for (int i = 2; i < 3; i++) {
			if (classroom[hour][1].equals("")) {
				break;
			} else if (classroom[hour][i] == null || classroom[hour][i].equals("")) {

			} else if (classroom[hour][i].substring(0, 1).equals("501")
					&& classroom[hour][0].substring(0, 1).equals("502")) {
				// Do nothing, it is ok.
			} else if (classroom[hour][i].substring(0, 1).equals(classroom[hour][1].substring(0, 1)) && i != 1) {
				count++;
			}
		}

		return count;
	}

	public static int conflicts(String[][] classroom) {
		int count = 0;
		for (int hour = 0; hour < 8; hour++) {
			for (int i = 1; i < 3; i++) {
				if (classroom[hour][0].equals("")) {
					break;
				} else if (classroom[hour][i] == null || classroom[hour][i].equals("")) {

				} else if (classroom[hour][i].substring(0, 1).equals("501")
						&& classroom[hour][0].substring(0, 1).equals("502")) {
					// Do nothing, it is ok.
				} else if (classroom[hour][i].substring(0, 1).equals(classroom[hour][0].substring(0, 1)) && i != 0) {
					count++;
				}
			}
			for (int i = 2; i < 3; i++) {
				if (classroom[hour][1].equals("")) {
					break;
				} else if (classroom[hour][i] == null || classroom[hour][i].equals("")) {

				} else if (classroom[hour][i].substring(0, 1).equals("501")
						&& classroom[hour][0].substring(0, 1).equals("502")) {
					// Do nothing, it is ok.
				} else if (classroom[hour][i].substring(0, 1).equals(classroom[hour][1].substring(0, 1)) && i != 1) {
					count++;
				}
			}
		}
		return count;
	}

	public static String[][] clone2dArray(String[][] matrix) {
		String[][] myStr = new String[matrix.length][];
		for (int i = 0; i < matrix.length; i++) {
			myStr[i] = matrix[i].clone();
		}
		return myStr;
	}

}
