package model;

public class User {

	private int Id;
	private String Username;
	private String Password;
	private String FirstName;
	private String LastName;
	private String Email;
	private String BirthDate;
	private String BodyHeight;
	private String BodyWeight;
	private String ActivityPoints;
	
	public User(int id, String username, String password, String firstName, String lastName, String email, String birthDate,
			String bodyHeight, String bodyWeight, String activityPoints) {
		setId(id);
		setUsername(username);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setBirthDate(birthDate);
		setBodyHeight(bodyHeight);
		setBodyWeight(bodyWeight);
		setActivityPoints(activityPoints);
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getUsername() {
		return Username;
	}
	
	public void setUsername(String username) {
		Username = username;
	}
	
	public String getPassword() {
		return Password;
	}
	
	public void setPassword(String password) {
		Password = password;
	}
	
	public String getFirstName() {
		return FirstName;
	}
	
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	
	public String getLastName() {
		return LastName;
	}
	
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
	public String getEmail() {
		return Email;
	}
	
	public void setEmail(String email) {
		Email = email;
	}
	
	public String getBirthDate() {
		return BirthDate;
	}
	
	public void setBirthDate(String birthDate) {
		BirthDate = birthDate;
	}
	
	public String getBodyHeight() {
		return BodyHeight;
	}
	
	public void setBodyHeight(String bodyHeight) {
		BodyHeight = bodyHeight;
	}
	
	public String getBodyWeight() {
		return BodyWeight;
	}
	
	public void setBodyWeight(String bodyWeight) {
		BodyWeight = bodyWeight;
	}
	
	public String getActivityPoints() {
		return ActivityPoints;
	}
	
	public void setActivityPoints(String activityPoints) {
		ActivityPoints = activityPoints;
	}
}
