package controllers;

import commons.Timeutils;
import dao.MoviesDAO;
import dto.MoviesDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("*.movie")
public class MoviesController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String uri = request.getRequestURI();
			if (uri.equals("/delete.movie")) {
				int delId = Integer.parseInt(request.getParameter("id"));
				MoviesDAO dao = MoviesDAO.getInstance();
				dao.delete(delId);
				response.sendRedirect("OutputAllController");
			}
			if (uri.equals("/insert.movie")) {
				String title = request.getParameter("title");
				String genre = request.getParameter("genre");
				String date = request.getParameter("launch_date");
				MoviesDAO dao = MoviesDAO.getInstance();
				dao.insert(title, genre, Timeutils.stringToTimestamp(date));
				response.sendRedirect("OutputAllController");
			}
			if (uri.equals("/outputAll.movie")) {
				MoviesDAO dao = MoviesDAO.getInstance();
				List<MoviesDTO> list = dao.viewAll();

				request.setAttribute("list", list);
				request.getRequestDispatcher("outputAll.jsp").forward(request, response);
			}
			if (uri.equals("/searchTitle.movie")) {
				MoviesDAO dao = MoviesDAO.getInstance();
				List<MoviesDTO> list = dao.search(request.getParameter("title"));

				request.setAttribute("list", list);
				request.getRequestDispatcher("outputAll.jsp").forward(request, response);
			}
			if (uri.equals("/update.movie")) {
				String title = request.getParameter("title");
				String genre = request.getParameter("genre");
				int id = Integer.parseInt(request.getParameter("id"));
				MoviesDAO dao = MoviesDAO.getInstance();
				dao.update(title, genre, id);
				response.sendRedirect("OutputAllController");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
