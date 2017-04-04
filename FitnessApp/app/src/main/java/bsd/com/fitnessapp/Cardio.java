package bsd.com.fitnessapp;

public class Cardio {
	private int id;
	private int actpoint;
	private int id_user;
	private String username;
	private String email;
	private String date;
	private float hoehenmeter;
	private float kilometer;
	private String zeit;
	
	public Cardio(int id, int actpoint, int id_user, String username, String email, String date, float hoehenmeter,
			float kilometer, String zeit) {
		super();
		this.id = id;
		this.actpoint = actpoint;
		this.id_user = id_user;
		this.username = username;
		this.email = email;
		this.date = date;
		this.hoehenmeter = hoehenmeter;
		this.kilometer = kilometer;
		this.zeit = zeit;
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

	public float getHoehenmeter() {
		return hoehenmeter;
	}

	public void setHoehenmeter(float hoehenmeter) {
		this.hoehenmeter = hoehenmeter;
	}

	public float getKilometer() {
		return kilometer;
	}

	public void setKilometer(float kilometer) {
		this.kilometer = kilometer;
	}

	public String getZeit() {
		return zeit;
	}

	public void setZeit(String zeit) {
		this.zeit = zeit;
	}
}
