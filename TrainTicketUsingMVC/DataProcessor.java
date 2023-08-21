package satish;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DataProcessor")
public class DataProcessor extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String trainName = request.getParameter("trainName");
		String clas = request.getParameter("class");
		String d = request.getParameter("date");
		String name[] = request.getParameterValues("passengerName");
		String age[] = request.getParameterValues("passengerAge");
		String gender[] = request.getParameterValues("passengerGender");
		ArrayList<Passenger> pl = new ArrayList<>();
		for (int i = 0; i < name.length; i++) {
			Passenger p = new Passenger(name[i], gender[i], age[i]);
			pl.add(p);
		}
		Ticket t = new Ticket(from, to, trainName, clas, d, pl);
		CalFare c = new CalFare();
		int cost = c.calfare(t);
		String pnr = c.getpnr();
		request.setAttribute("cost", cost);
		request.setAttribute("ticket", t);
		request.setAttribute("pnr", pnr);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/result.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
