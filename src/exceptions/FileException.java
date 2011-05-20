package exceptions;

import java.io.File;

public class FileException extends EigeneExceptions {
	private static final String OUT_NOT_EXISTS_EXCEPTION = "Datei konnte nicht gefunden werden: ";
	private static final String OUT_FOLDER_EXCEPTION = "Datei ist ein Ordner!: ";
	private static final String OUT_READ_RIGHTS_EXCEPTION = "Keine Leserechte auf Datei: ";

	public FileException(String detailmessage) {
		super(detailmessage);
	}

	public FileException() {
		super();
	}

	public static void checkFile(String fileName) throws FileException {
		File file = new File(fileName);
		if (file.exists()) {
			if (file.isFile()) {
				if (file.canRead()) {
				} else {
					throw new FileException(OUT_READ_RIGHTS_EXCEPTION
							+ fileName);
				}
			} else {
				throw new FileException(OUT_FOLDER_EXCEPTION+fileName);
			}
		} else {
			throw new FileException(OUT_NOT_EXISTS_EXCEPTION+fileName);
		}
	}
}
