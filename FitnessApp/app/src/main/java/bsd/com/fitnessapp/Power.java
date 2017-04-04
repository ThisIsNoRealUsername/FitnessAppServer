package bsd.com.fitnessapp;

public class Power {
	private int id;
	private int actpoint;
	private int id_user;
	private String username;
	private String email;
	private String date;
	private float gewichte;
	private int wiederholungen;
	
	public Power(int id, int actpoint, int id_user, String username, String email, String date, float gewichte,
			int wiederholungen) {
		super();
		this.id = id;
		this.actpoint = actpoint;
		this.id_user = id_user;
		this.username = username;
		this.email = email;
		this.date = date;
		this.gewichte = gewichte;
		this.wiederholungen = wiederholungen;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getActpoint() {
		return actpoint;
	}

	public void setActpoint(int actpoint) {
		this.actpoint = actpoint;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getGewichte() {
		return gewichte;
	}

	public void setGewichte(float gewichte) {
		this.gewichte = gewichte;
	}

	public int getWiederholungen() {
		return wiederholungen;
	}

	public void setWiederholungen(int wiederholungen) {
		this.wiederholungen = wiederholungen;
	}
	
}
