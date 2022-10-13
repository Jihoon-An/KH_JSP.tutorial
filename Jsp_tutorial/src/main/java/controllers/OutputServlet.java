package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MessagesDAO;
import dto.MessagesDTO;

@WebServlet("/OutputServlet")
public class OutputServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            MessagesDAO dao = MessagesDAO.getInstance();
            List<MessagesDTO> list = dao.selectAll();

            request.setAttribute("list", list);
            // response.sendRedirect("outputView.jsp");
            request.getRequestDispatcher("outputView.jsp").forward(request, response);


        } catch(Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


}