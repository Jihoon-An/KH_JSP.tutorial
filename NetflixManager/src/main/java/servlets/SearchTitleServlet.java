package servlets;

import dao.MoviesDAO;
import dto.MoviesDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/SearchTitleServlet")
public class SearchTitleServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            MoviesDAO dao = MoviesDAO.getInstance();
            ArrayList<MoviesDTO> list = dao.search(request.getParameter("title"));

            request.setAttribute("list", list);
            request.getRequestDispatcher("outputAll.jsp").forward(request, response);
        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
