package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Factory {
	private ResultSet rs = null;
	private User u = null;
	
	public Factory(ResultSet rs) throws SQLException {
		this.rs = rs;
	}
	
	/**
	 * returns the data of the current row in a HashMap
	 * @return
	 * @throws SQLException
	 */
	public User getUser() throws SQLException {
		u = new User(Integer.parseInt(rs.getObject(1).toString()), rs.getObject(2).toString(),
				rs.getObject(5).toString(), rs.getObject(3).toString(), rs.getObject(4).toString(),
				rs.getObject(6).toString(), rs.getObject(10).toString(), rs.getObject(8).toString(),
				rs.getObject(7).toString(), rs.getObject(9).toString());
		
		return u;
	}
}
