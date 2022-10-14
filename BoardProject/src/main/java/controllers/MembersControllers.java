package main.java.controllers;

import main.java.dao.MembersDAO;
import main.java.dto.MembersDTO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.member")
public class MembersControllers extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.startsWith("/member/dupleCheck.member")) {
            try {
                MembersDAO dao = MembersDAO.getInstance();
                String id = request.getParameter("id");
                boolean result = dao.isIdExist(id);

                request.setAttribute("result", result);
                request.setAttribute("id", id);
                request.getRequestDispatcher("dupleCheckView.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (uri.startsWith("/member/signup.member")) {
            try {
                MembersDAO dao = MembersDAO.getInstance();
                String id = request.getParameter("id");
                String pw = request.getParameter("pw1");
                String name = request.getParameter("name");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String zipcode = request.getParameter("zipcode");
                String address1 = request.getParameter("address1");
                String address2 = request.getParameter("address2");
                System.out.println(id + pw + name);

                if(dao.signup(new MembersDTO(id, pw, name, phone, email, zipcode, address1, address2, "")) > 0) {
                    response.sendRedirect("/index.jsp");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}