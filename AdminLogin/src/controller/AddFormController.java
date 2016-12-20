package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.DBManager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddFormController {
	@FXML
	private Label lblUsername;

	@FXML
	private TextField tfUsername;

	@FXML
	private Label lblFirstname;

	@FXML
	private TextField tfFirstname;

	@FXML
	private Label lblLastname;

	@FXML
	private TextField tfLastname;

	@FXML
	private Label lblPassword;

	@FXML
	private TextField tfPassword;

	@FXML
	private Label lblEmail;

	@FXML
	private TextField tfEmail;

	@FXML
	private Label lblBodyWeight;

	@FXML
	private TextField tfBodyWeight;

	@FXML
	private Label lblBodyHeight;

	@FXML
	private TextField tfBodyHeight;

	@FXML
	private Label lblActivityPoints;

	@FXML
	private TextField tfActivityPoints;

	@FXML
	private Label lblBirthdate;

	@FXML
	private TextField tfBirthdate;

	@FXML
	private Button btnAdd;

	private static final int PLACEHOLDER_ID = 0;
	private Connection con = null;
	private Statement stmt = null;
	private UserVerwaltungController controller = null;
	
	@FXML
	private void clickBtnAdd() {
		try {
			con = DBManager.getConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate("insert into user_bsd values (" + PLACEHOLDER_ID + ", '" + tfUsername.getText() + "', '"
					+ tfFirstname.getText() + "', '" + tfLastname.getText() + "', '" + tfPassword.getText() + "', '"
					+ tfEmail.getText() + "', " + tfBodyWeight.getText() + ", " + tfBodyHeight.getText() + ", "
					+ tfActivityPoints.getText() + ", '" + tfBirthdate.getText() + "')");
			stmt.execute("commit");
		} catch (SQLException e) {
			System.err.println("Error at stmt or rs: " + e.getMessage());
		}

		DBManager.close(stmt);
		DBManager.close(con);
		
	    Stage stage = (Stage) btnAdd.getScene().getWindow();
	    stage.close();
	    controller.loadDataFromDatabase();
	}
	
	public void setMainFormController(UserVerwaltungController userVerwaltungController) {
		this.controller = userVerwaltungController;
	}
}
