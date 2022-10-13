package controllers;

import dao.MessagesDAO;
import dto.MessagesDTO;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.message")
public class MessagesController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI(); // 요청된 url 정보.
        try {
            if (uri.equals("/input.message")) {
                String writer = request.getParameter("writer");
                String message = request.getParameter("message");


                MessagesDAO dao = MessagesDAO.getInstance();
                int result = dao.insert(writer, message);

                response.sendRedirect("index.html");

            }
            if (uri.equals("/output.message")) {

                MessagesDAO dao = MessagesDAO.getInstance();
                List<MessagesDTO> list = dao.selectAll();

                request.setAttribute("list", list);
                // response.sendRedirect("outputView.jsp");
                request.getRequestDispatcher("outputView.jsp").forward(request, response);

            }
            if (uri.equals("/delete.message")) {
                int seq = Integer.parseInt(request.getParameter("delSeq"));


                MessagesDAO dao = MessagesDAO.getInstance();
                int result = dao.delete(seq);

                response.sendRedirect("OutputServlet");


            }
            if (uri.equals("/update.message")) {
                int seq = Integer.parseInt(request.getParameter("seq"));
                String writer = request.getParameter("writer");
                String message = request.getParameter("message");


                MessagesDAO dao = MessagesDAO.getInstance();
                int result = dao.update(new MessagesDTO(seq, writer, message));

                response.sendRedirect("OutputServlet");


            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
