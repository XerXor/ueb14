package exceptions;

import java.io.File;

public class IllegalCommandException extends EigeneExceptions {
	public IllegalCommandException(String detailmessage) {
		super(detailmessage);
	}

	public IllegalCommandException() {
		super();
	}
}
