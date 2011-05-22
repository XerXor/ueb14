package dialogs;

import java.util.Scanner;
import java.util.regex.MatchResult;
import exceptions.ArrayIsEmptyException;
import exceptions.ArrayNotHaveThisElementException;
import exceptions.IllegalCommandException;
import exceptions.QueueIsEmptyException;
import exceptions.QueueIsFullException;
import exceptions.TreeException;
import base.ArrayTree;
import base.Person;

/**
 * AuftragVerwaltung ist eine Klasse, die Auftraege ueber eine ArrayQueue
 * verwaltet.<br>
 * Die AuftragVerwaltung bietet Methoden zum Einlesen von Verarbeitungsschritten
 * aus einer Datei an. Dabei wird Zeile fuer Zeile gelesen und interpretiert.
 * Bei fehlerhaften Zeilen wird eine entsprechende Nachricht ueber System.err
 * ausgegeben.
 * 
 * @author Alexei Felberg
 * @author Daniel Rhein
 * @version 20.05.2011
 */
public class ArrayTreeVerwaltung {
	private static final String OUT_INVALID_ADD_EXCEPTION = "Nicht genug Argumente in der Zeile!";
	private static final String OUT_INVALID_COMMAND = "Ungueltiges Kommando!";
	private static final String REGEX_STRING = "(\\S+)[(\\s{0,1})(\\S{0,})]*";
	private ArrayTree arrayTree;

	/**
	 * Konstruktor der ein int verlangt, welcher die Arraygroesse festlegt.
	 * 
	 * @param maxSize
	 *            setzt die Arraygroesse fest und ebenso die maximale Anzahl an
	 *            Objekten, die der Baum beinhalten kann.
	 * @TreeException
	 * Sollten Fehler im Tree auftreten, so werden diese hier geworfen.
	 * @IllegalArgumentException
	 * Sollte eine Fehleingabe passieren wird diese hier geworfen.
	 * @QueueIsFullexception
	 * Sollte die Queue voll sein wird ein Fehler hier geworfen.
	 */
	public ArrayTreeVerwaltung(int maxSize) throws IllegalArgumentException, TreeException,QueueIsFullException{
				arrayTree = new ArrayTree(maxSize);
	}

	/**
	 * Methode in der zuvor der Befehl fuer hinzufuegen in einer Datei gelesen
	 * wurde. Hier wird dieser Befehl noch weiter analysiert.
	 * 
	 * @param line
	 *            die Zeile, in welcher der Befehl zum Loeschen interpretiert
	 *            wurde.
	 * @throws IllegalCommandException
	 *             wenn die uebergebene Zeile als kein gueltiger Befehl
	 *             entschluesselt werden kann.<br>
	 * @throws QueueIsEmptyException
	 *             wenn die Queue Leer ist.
	 * @throws IllegalArgumentException
	 *             wenn ein falsches Argument uebergeben wurde.
	 */
	public void add(String line) throws IllegalCommandException, TreeException,
			QueueIsEmptyException {
		line = line.substring(1);
		Scanner scanner = new Scanner(line);
		scanner.findInLine(REGEX_STRING);
		try {
			MatchResult matched = scanner.match();
			if (matched.groupCount() < 1)
				throw new IllegalCommandException(OUT_INVALID_ADD_EXCEPTION);
			String name = matched.group(1);
			arrayTree.add(new Person(name));
		} catch (IllegalStateException illegalStateException) {
			throw new IllegalCommandException(OUT_INVALID_ADD_EXCEPTION);
		}
	}

	/**
	 * Methode in der zuvor der Befehl fuer hinzufuegen in einer Datei gelesen
	 * wurde. Hier wird dieser Befehl noch weiter analysiert.
	 * 
	 * @param line
	 *            die Zeile, in welcher der Befehl zum Loeschen interpretiert
	 *            wurde.
	 * @throws QueueIsFullException
	 * @throws ArrayIsEmptyException
	 * @throws ArrayNotHaveThisElementException 
	 * @throws IllegalArgumentException
	 * @throws NotAllowedArgumentException
	 *             wenn die uebergebene Zeile als kein gueltiger Befehl
	 *             entschluesselt werden kann.<br>
	 * @throws QueueException
	 *             wenn ein die Queue leer ist.
	 */
	public void remove(String line) throws IllegalCommandException,
			TreeException, ArrayIsEmptyException, QueueIsFullException, IllegalArgumentException, ArrayNotHaveThisElementException {
		line = line.substring(1);
		Scanner scanner = new Scanner(line);
		scanner.findInLine(REGEX_STRING);
		MatchResult gefundenes = scanner.match();
		if (gefundenes.groupCount() < 1)
			throw new IllegalCommandException(OUT_INVALID_COMMAND);
		arrayTree.remove(new Person(gefundenes.group(1)));
	}

	@Override
	public String toString() {
		return arrayTree.toString();
	}

	/**
	 * Informiert darueber, ob der ArrayTree leer ist oder nicht.
	 * @return
	 */
	public boolean isEmpty()
	{
		return arrayTree.isEmpty();
	}
	
	/**
	 * Methode leitet nur den uebergebenen int weiter an :
	 * {@link ArrayTree#printVariation(int)}
	 */
	public String printVariation(int variation) {
		return arrayTree.printVariation(variation);
	}
}
