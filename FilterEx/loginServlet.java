package com.charan;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public loginServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		System.out.println(name);
		String passWord = request.getParameter("password");
		System.out.println(passWord);
		if (passWord.equals("123")) {
			String key = key();
			response.setHeader("first", key);
			HttpSession session = request.getSession();
			session.setAttribute("key", key);
			System.out.println("servlet:" + key);

			RequestDispatcher rd = request.getRequestDispatcher("/homejsp.jsp");
			rd.forward(request, response);

		}
	}

	public static String key() {

		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		StringBuilder randomString = new StringBuilder();

		Random random = new Random();

		for (int i = 0; i < 5; i++) {
			int randomIndex = random.nextInt(characters.length());
			char randomChar = characters.charAt(randomIndex);
			randomString.append(randomChar);
		}

		return randomString.toString();
	}
}
