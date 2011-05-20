package base;

public class Person {
	private static final String NAME="Name:";
	private static final String ERROR_NAME_NOT_NULL = "Name darf nicht null sein.";

	private String name;
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
	@Override
	public String toString()
	{
		StringBuilder stringbuilder = new StringBuilder();
		stringbuilder.append(NAME);
		stringbuilder.append(getName());
		return stringbuilder.toString();
	}
	@Override
	public boolean equals(Object o){
		return name.equals(o);
	}
}
