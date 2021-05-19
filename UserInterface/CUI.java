package UserInterface;

import java.util.*;

public class CUI {

	public CUI() {

	}

	public static void main(String args[]) {
		Scanner inputScanner = new Scanner(System.in);
		println("Wellcome!");
		while (true) {
			blankSpace(2);

			println("What can I do?");
			blankSpace(1);
			println("-------------------------");
			println("Log in: l");
			println("Search: s");
			println("Close: x");
			println("-------------------------");

			blankSpace(1);

			String input = inputScanner.nextLine();

			switch (input) {
				case "l":
					println("Server problem, please try again later.");
					break;
				case "s":
					println("Error 404: product could not be found.");
					break;
				case "x":
					println("Bye!!");
					System.exit(0);
					break;
				default:
					println("Invalid Input. Please try again.");
					break;

			}
		}
	}

	private static void println(String message) {
		System.out.println(message);
	}

	private static void blankSpace(int repetitions) {
		for (int i = 0; i < repetitions; i++) {
			println("");
		}
	}
}
