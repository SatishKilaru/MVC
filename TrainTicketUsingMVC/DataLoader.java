package satish;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/DataLoader")
public class DataLoader extends HttpServlet {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://192.168.110.48:5432/plf_training";
			String username = "plf_training_admin";
			String password = "pff123";
			Connection connection = DriverManager.getConnection(url, username, password);

			String sql = "SELECT * FROM places";

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			String sql1 = "SELECT * FROM ajayTrains";

			Statement statement1 = connection.createStatement();
			ResultSet resultSet1 = statement1.executeQuery(sql1);
			ArrayList<String> places = new ArrayList<>();
			ArrayList<String> trains = new ArrayList<>();
			while (resultSet.next()) {
				places.add(resultSet.getString("place"));
			}
			while (resultSet1.next()) {
				trains.add(resultSet1.getString("name"));
			}

			JSONObject jsonResponse = new JSONObject();
			jsonResponse.put("places", places);
			jsonResponse.put("trains", trains);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			response.getWriter().write(jsonResponse.toString());

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();

		} finally {
			JDBCHelper.closeResultSet(resultSet);
			JDBCHelper.closePreparedStatement(preparedStatement);
			JDBCHelper.closeConnection(connection);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
