package dialogs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import de.htw.saarland.stl.Stdin;
import exceptions.EigeneExceptions;
import exceptions.FileException;
import exceptions.IllegalCommandException;

/**
 * Eine Testklasse, mit der man ein ArrayTree auf Funktion pruefen kann.
 * 
 * @author Alexei Felberg
 * @author Daniel Rhein
 * @version 20.05.2011
 */
public class ArrayTreeDialog {
	private static final String INFORMATION_TREE_IS_EMPTY = "Zur Zeit ist der Baum leer";
	private static final String OUT_TREE_VARIATIONS = "1: Wurzel unten, Baum nach oben aufbauen\n" +
													  "2: Wurzel oben, Baum nach unten aufbauen\n" +
													  "3: Wurzel links, Baum nach rechts aufbauen";
	private static final String IN_PRINT_TREE_VARIATION = "Geben Sie ein welche Variante ausgegeben " +
														  "werden soll:\n";
	private static final String IN_ARRAY_SIZE = "Geben Sie die Groesse des Arrays an:\n";
	private static final String OUT_PROGRAMMENDE = "Programm wird beendet.";
	private static final String OUT_ENTER_FILENAME = "Geben Sie den Dateinamen ein";
	private static final String IN_COMMAND = "Geben eines der Kommandos ein:\n";
	private static final String INVALID_COMMAND = "Ungueltiges Kommando!";
	private static final String OUT_MENUE_COMMANDS = "0: Programm beenden\n" +
													 "1: Datei einlesen\n"+
													 "2: Datenstruktur ausgeben\n"+
													 "3: Baum ausgeben lassen";
	private static final String IN_PREFIX_ADD = "+";
	private static final String IN_PREFIX_REMOVE = "-";
	private static final String OUT_SEPARATOR = "\t";
	private static final String OUT_CONTENT = "\tContent: ";
	private static final String OUT_EXCEPTION_OCCURED = "Exception occured on:\nLine: ";
	private static final String OUT_INVALID_COMMAND = "Ungueltiges Kommando!";
	private static final String OUT_NEW_LINE = "\n";
	private ArrayTreeVerwaltung treeVerwaltung;

	/**
	 * Methode zum Einlesen eines Dateinamen.
	 * 
	 * @return eingelesener Dateiname.
	 */
	private String readFileName() {
		return Stdin.readString(OUT_ENTER_FILENAME);
	}

	/**
	 * Methode in der die Datei eingelesen wird und die Zeilen interpretiert und
	 * verarbeitet.
	 * 
	 * @param lineReader
	 *            ein LineNumberReader, welcher ein Kanal zu einer Datei offen
	 *            hat.
	 * @throws FileException
	 *             wenn Datei nicht existiert, keine Leserechte vorliegen oder
	 *             wenn die Datei ein Ordner ist.
	 * @throws IOException
	 *             bei auftretenden Problemen waehrend des Einlesens.
	 */
	public void readFile(String fileName) throws FileException, IOException {
		FileException.checkFile(fileName);
		File file = new File(fileName);
		FileReader fileReader;
		try {
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e) {
			throw new FileException();
		}
		LineNumberReader lineReader = new LineNumberReader(fileReader);
		String line;
		line = lineReader.readLine();
		while (line != null) {
			try {
				if (line.startsWith(IN_PREFIX_ADD)) {
					treeVerwaltung.add(line);
				} else if (line.startsWith(IN_PREFIX_REMOVE)) {
					treeVerwaltung.remove(line);
				} else {
					throw new IllegalCommandException(OUT_INVALID_COMMAND);
				}
			} catch (EigeneExceptions eigeneExceptions) {
				System.err.println(OUT_EXCEPTION_OCCURED
						+ lineReader.getLineNumber() + OUT_CONTENT + line
						+ OUT_NEW_LINE + eigeneExceptions);
			}
			System.out.println((lineReader.getLineNumber() + OUT_SEPARATOR
					+ line));
			System.out.println(treeVerwaltung);;
			line = lineReader.readLine();
		}
		fileReader.close();
	}

	/**
	 * Eigentliche Ausfuehrmethode dieser Klasse. Hier wird dem Benutzer ein
	 * Menue eingeblendet, welches ihm erlaubt das Programm zu steuern.
	 */
	private void start() {
		int maxSize = Stdin.readInt(IN_ARRAY_SIZE);
		treeVerwaltung = new ArrayTreeVerwaltung(maxSize);
		while (true) {
			System.out.println(OUT_MENUE_COMMANDS);
			try {
				int kommando = Stdin.readInt(IN_COMMAND);
				switch (kommando) {
				case 0:
					System.out.println(OUT_PROGRAMMENDE);
					System.exit(0);
					break;
				case 1:
					readFile(readFileName());
					break;
				case 2:
					if (treeVerwaltung.isEmpty())
					{
						System.out.println(INFORMATION_TREE_IS_EMPTY);	
					}
					else
					{
					System.out.println(treeVerwaltung);
					}
					break;
				case 3:
					System.out.println(OUT_TREE_VARIATIONS);
					int variante = Stdin.readInt(IN_PRINT_TREE_VARIATION);
					if (treeVerwaltung.isEmpty())
					{
						System.out.println(INFORMATION_TREE_IS_EMPTY);
					}
					else
					{
						System.out.println(treeVerwaltung.printVariation(variante));
					}
					break;
				default:
					System.out.println(INVALID_COMMAND);
					break;
				}
			} catch (IOException iOException) {
				System.err.println(iOException);
			} catch (NumberFormatException numberFormatException) {
				System.err.println(numberFormatException);
			} catch (EigeneExceptions eigeneExceptions) {
				System.err.println(eigeneExceptions);
			} catch (Exception exception){
				System.err.println(exception);
			}
		}
	}

	/**
	 * Main-Methode, in der ein Objekt von dieser Klasse angelegt wird und von
	 * der wiederum die {@link #start()} Methode ausguehrt wird.
	 * 
	 * @param args
	 *            eventuell moegliche Parameter.
	 */
	public static void main(String[] args) {
		ArrayTreeDialog textReader = new ArrayTreeDialog();
		textReader.start();
	}
}