package UserInterface;

import java.util.*;

public class CUI {

	public CUI() {

	}

	public static void main(String args[]) {
		Scanner inputScanner = new Scanner(System.in);
		println("Willkommen!");
		while (true) {
			blankSpace(2);

			println("Was darf es sein?");
			blankSpace(1);
			println("-------------------------");
			println("Einloggen: l");
			println("Suchen: s");
			println("Schliessen: x");
			println("-------------------------");

			blankSpace(1);

			String input = inputScanner.nextLine();

			switch (input) {
				case "l":
					println("Serverproblem, bitte versuchen Sie es spaeter nochmal.");
					break;
				case "s":
					println("Fehler 404: Gesuchtes Produkt nicht gefunden.");
					break;
				case "x":
					println("Auf Wiedersehen!");
					System.exit(0);
					break;
				default:
					println("Ungueltige Eingabe. Bitte erneut versuchen.");
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
