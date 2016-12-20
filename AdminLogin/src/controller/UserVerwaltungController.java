package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.DBManager;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import model.Factory;
import model.User;

public class UserVerwaltungController {

	@FXML
	private TableView<User> tbl;
	@FXML
	private TableColumn<User, String> usernameCol;
	@FXML
	private TableColumn<User, String> passwordCol;
	@FXML
	private TableColumn<User, String> firstNameCol;
	@FXML
	private TableColumn<User, String> lastNameCol;
	@FXML
	private TableColumn<User, String> emailCol;
	@FXML
	private TableColumn<User, String> birthDateCol;
	@FXML
	private TableColumn<User, String> bodyHeightCol;
	@FXML
	private TableColumn<User, String> bodyWeightCol;
	@FXML
	private TableColumn<User, String> activityPointsCol;

	@FXML
	private Button btnRead;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnDelete;

	private Factory factory = null;
	private Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private User u = null;

	private void setCellConfigurations() {
		List<TableColumn<User, String>> columns = new ArrayList<TableColumn<User, String>>();
		columns.add(usernameCol);
		columns.add(passwordCol);
		columns.add(firstNameCol);
		columns.add(lastNameCol);
		columns.add(emailCol);
		columns.add(birthDateCol);
		columns.add(bodyHeightCol);
		columns.add(bodyWeightCol);
		columns.add(activityPointsCol);

		String[] strings = { "Username", "Password", "FirstName", "LastName", "Email", "BirthDate", "BodyHeight",
				"BodyWeight", "ActivityPoints" };

		setAllColumnProperties(columns, strings);
	}
	
	private void setAllColumnProperties(List<TableColumn<User, String>> columnList, String[] columnNames) {
		int i = 0;

		for (TableColumn<User, String> col : columnList) {
			col.setCellValueFactory(new PropertyValueFactory<User, String>(columnNames[i]));
			col.setCellFactory(TextFieldTableCell.forTableColumn());

			switch (columnNames[i]) {
			case "Username":
				col.setOnEditCommit(new EventHandler<CellEditEvent<User, String>>() {
					@Override
					public void handle(CellEditEvent<User, String> t) {
						((User) t.getTableView().getItems().get(t.getTablePosition().getRow()))
								.setUsername(t.getNewValue());
						executeUpdateToDatabase("username", t.getNewValue(), "String");
					}
				});
				break;

			case "Password":
				col.setOnEditCommit(new EventHandler<CellEditEvent<User, String>>() {
					@Override
					public void handle(CellEditEvent<User, String> t) {
						((User) t.getTableView().getItems().get(t.getTablePosition().getRow()))
								.setPassword(t.getNewValue());
						executeUpdateToDatabase("password", t.getNewValue(), "String");
					}
				});
				break;

			case "FirstName":
				col.setOnEditCommit(new EventHandler<CellEditEvent<User, String>>() {
					@Override
					public void handle(CellEditEvent<User, String> t) {
						((User) t.getTableView().getItems().get(t.getTablePosition().getRow()))
								.setFirstName(t.getNewValue());
						executeUpdateToDatabase("firstname", t.getNewValue(), "String");
					}
				});
				break;

			case "LastName":
				col.setOnEditCommit(new EventHandler<CellEditEvent<User, String>>() {
					@Override
					public void handle(CellEditEvent<User, String> t) {
						((User) t.getTableView().getItems().get(t.getTablePosition().getRow()))
								.setLastName(t.getNewValue());
						executeUpdateToDatabase("lastname", t.getNewValue(), "String");
					}
				});
				break;

			case "Email":
				col.setOnEditCommit(new EventHandler<CellEditEvent<User, String>>() {
					@Override
					public void handle(CellEditEvent<User, String> t) {
						((User) t.getTableView().getItems().get(t.getTablePosition().getRow()))
								.setEmail(t.getNewValue());
						executeUpdateToDatabase("email", t.getNewValue(), "String");
					}
				});
				break;

			case "BirthDate":
				col.setOnEditCommit(new EventHandler<CellEditEvent<User, String>>() {
					@Override
					public void handle(CellEditEvent<User, String> t) {
						((User) t.getTableView().getItems().get(t.getTablePosition().getRow()))
								.setBirthDate(t.getNewValue());
						executeUpdateToDatabase("birthdate", t.getNewValue(), "String");
					}
				});
				break;

			case "BodyHeight":
				col.setOnEditCommit(new EventHandler<CellEditEvent<User, String>>() {
					@Override
					public void handle(CellEditEvent<User, String> t) {
						((User) t.getTableView().getItems().get(t.getTablePosition().getRow()))
								.setBodyHeight(t.getNewValue());
						executeUpdateToDatabase("bodyheight", t.getNewValue(), "Int");
					}
				});
				break;

			case "BodyWeight":
				col.setOnEditCommit(new EventHandler<CellEditEvent<User, String>>() {
					@Override
					public void handle(CellEditEvent<User, String> t) {
						((User) t.getTableView().getItems().get(t.getTablePosition().getRow()))
								.setBodyWeight(t.getNewValue());
						executeUpdateToDatabase("bodyweight", t.getNewValue(), "Int");
					}
				});
				break;

			case "ActivityPoints":
				col.setOnEditCommit(new EventHandler<CellEditEvent<User, String>>() {
					@Override
					public void handle(CellEditEvent<User, String> t) {
						(t.getTableView().getItems().get(t.getTablePosition().getRow()))
								.setActivityPoints(t.getNewValue());
						executeUpdateToDatabase("activitypoints", t.getNewValue(), "Int");
					}
				});
				break;
			}
			i++;
		}
	}

	private void executeUpdateToDatabase(String columnName, String newValue, String type) {
		con = DBManager.getConnection();
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			if (type.equals("String")) {
				stmt.executeUpdate(
						"update user_bsd set " + columnName + "='" + newValue + "' where id =" + getIdOfSelectedUser());
			} else if (type.equals("Int")) {
				stmt.executeUpdate(
						"update user_bsd set " + columnName + "=" + newValue + " where id =" + getIdOfSelectedUser());
			}
			stmt.executeQuery("commit");
			DBManager.close(stmt);
			DBManager.close(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private int getIdOfSelectedUser() {
		User user = tbl.getSelectionModel().getSelectedItem();
		return user.getId();
	}

	@FXML
	private void initialize() {
		loadDataFromDatabase();
	}

	@FXML
	private void clickBtnRead() {
		loadDataFromDatabase();
	}

	public void loadDataFromDatabase() {
		tbl.getItems().removeAll(tbl.getItems());

		setCellConfigurations();

		try {
			con = DBManager.getConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("select b.* from user_bsd b");
		} catch (SQLException e) {
			System.err.println("Error at stmt or rs: " + e.getMessage());
		}

		if (rs != null) {
			try {
				while (rs.next()) {
					factory = new Factory(rs);
					u = factory.getUser();
					tbl.getItems().add(u);
				}
			} catch (SQLException e) {
				System.err.println("Error at rs.next(): " + e.getMessage());
			}
		}

		DBManager.close(rs);
		DBManager.close(stmt);
		DBManager.close(con);
	}

	@FXML
	private void clickBtnAdd() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/AddForm.fxml"));
			Parent root = (Parent)fxmlLoader.load();
			AddFormController addController = fxmlLoader.<AddFormController>getController();
			addController.setMainFormController(this);
			Scene scene = new Scene(root, 400, 450);
			Stage s = new Stage();
			s.setScene(scene);
			s.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@FXML
	private void clickBtnDelete() {
		Connection con = null;
		Statement stmt = null;
		String[] cardioTableNames = { "run", "swim", "cycle" };
		String[] powerTableNames = { "deadlift", "benchpress", "squat" };
		int cardioID = 0;
		int powerID = 0;
		User u = null;

		if (tbl.getSelectionModel().getSelectedItem() != null) {
			u = tbl.getSelectionModel().getSelectedItem();

			try {
				con = DBManager.getConnection();
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				
				cardioID = getIdFromTable(stmt, "cardio", u.getId());
				powerID = getIdFromTable(stmt, "power", u.getId());
				
				for (String tableName : cardioTableNames) {
					stmt.executeUpdate("delete from " + tableName + "_bsd where id = " + cardioID);
				}
				for (String tableName : powerTableNames) {
					stmt.executeUpdate("delete from " + tableName + "_bsd where id = " + powerID);
				}
				stmt.executeUpdate("delete from cardio_bsd where id_user = " + u.getId());
				stmt.executeUpdate("delete from power_bsd where id_user = " + u.getId());
				stmt.executeUpdate("delete from user_bsd where id = " + u.getId());
				stmt.execute("commit");
			} catch (SQLException e) {
				System.err.println("Error at stmt or rs: " + e.getMessage());
			}
		} else
			System.out.println("Nothing selected to delete!");

		DBManager.close(stmt);
		DBManager.close(con);
		clickBtnRead();
	}
	
	private int getIdFromTable(Statement stmt, String tableName, int id_user) {
		int id = 0;
		ResultSet rs = null;
		
		try {
			rs = stmt.executeQuery("select b.id from " + tableName + "_bsd b where id_user = " + id_user);
		} catch (SQLException e1) {
			System.err.println("Error at rs: " + e1.getMessage());
		}
		if (rs != null) {
			try {
				while (rs.next()) {
					id = Integer.parseInt(rs.getObject(1).toString());
				}
			} catch (SQLException e2) {
				System.err.println("Error at rs.next(): " + e2.getMessage());
			}
		}
		
		DBManager.close(rs);
		
		return id;
	}
}
