package controllers;

import commons.Timeutils;
import dao.MoviesDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/InsertController")
public class InsertController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String genre = request.getParameter("genre");
		String date = request.getParameter("launch_date");

		try {
			MoviesDAO dao = MoviesDAO.getInstance();
			dao.insert(title, genre, Timeutils.stringToTimestamp(date));
			response.sendRedirect("OutputAllController");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
