package shangbo.activeMQ.example9;

import java.io.Serializable;

public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastName;
	
	public String toString() {
		return lastName + " " + firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
