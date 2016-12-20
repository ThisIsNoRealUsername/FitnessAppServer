package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.DBManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainFormController {
	private Connection con;
	private ResultSet rs;
	private String sql = "select a.adminname, a.adminpw from admin_bsd a where adminname like ? and adminpw like ?";
	private PreparedStatement prepStmt;

	@FXML
	private Label lblUsername;

	@FXML
	private Label lblPasswort;

	@FXML
	private TextField tfUsername;

	@FXML
	private PasswordField pfPasswort;

	@FXML
	private Button btnLogin;
	
	@FXML
	private Label lblInfo;

	@FXML
	public void checkConnection() {
		try {
			con = DBManager.getConnection();
			// pre - compilation phase - only once
			prepStmt = con.prepareStatement(sql);
			
			prepStmt.setString(1, tfUsername.getText());
			prepStmt.setString(2, pfPasswort.getText());
			rs = prepStmt.executeQuery();
			
			checkIfAdminExists();
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkIfAdminExists() {
		try {
			if (rs != null) {
				if (rs.next()) {
					try {
						Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/UserVerwaltung.fxml"));
						Scene scene = new Scene(root,900,350);
						Stage s = new Stage();
						s.setScene(scene);
						s.show();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					lblInfo.setText("Admin doesn't exist!");

				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void closeConnection() {
		DBManager.close(rs);
		DBManager.close(prepStmt);
		DBManager.close(con);
	}

}
