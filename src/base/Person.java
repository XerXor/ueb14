package base;

public class Person {
	private static final String NAME="Name:";
	private static final String VORLESUNG = "Vorlesung:";
	private static final String GEHALT= "Gehalt:";
	private static final String ERROR_NAME_NOT_NULL = "Name darf nicht null sein.";
	private static final String ERROR_GEHALT_MIN = "Gehalt muss mindestens 1 sein.";
	private static final String ERROR_VORLESUNG_NOT_NULL = "Vorlesung darf nicht null sein.";

	private String name;
	private int gehalt =1;
	private static final int MIN_GEHALT = 1;
	private String vorlesung = null;
	/**
	 * Lege eine Person mit einem Namen fest.
	 * @param name
	 */
	public Person(String name) throws IllegalArgumentException
	{
		if (name == null) throw new IllegalArgumentException(ERROR_NAME_NOT_NULL);
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) throws IllegalArgumentException {
		this.name = name;
	}
	public int getGehalt() {
		return gehalt;
	}
	public void setGehalt(int gehalt) throws IllegalArgumentException {
		this.gehalt = gehalt;
	}
	public String getVorlesung() {
		return vorlesung;
	}
	public void setVorlesung(String vorlesung) {
		this.vorlesung = vorlesung;
	}
	
	public String toString()
	{
		StringBuilder stringbuilder = new StringBuilder();
		stringbuilder.append(NAME);
		stringbuilder.append(getName());
		stringbuilder.append(VORLESUNG);
		stringbuilder.append(getVorlesung());
		stringbuilder.append(GEHALT);
		stringbuilder.append(getGehalt());
		return stringbuilder.toString();
	}
}
