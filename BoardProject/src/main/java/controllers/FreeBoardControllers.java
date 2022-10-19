package controllers;

import dao.FreeBoardDAO;
import dao.MembersDAO;
import dto.FreeBoardDTO;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.board")
public class FreeBoardControllers extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println(request.getRemoteAddr());

        request.setCharacterEncoding("utf8");

        String uri = request.getRequestURI();

        switch (uri){
            case("/freeBoard.board"):
                System.out.println("page freeBoard");
                try {
                    List<FreeBoardDTO> posts = FreeBoardDAO.getInstance().roadPostingList();
                    request.setAttribute("posts", posts);

                    request.getRequestDispatcher("/board/freeBoard.jsp").forward(request,response);
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendRedirect("/");
                }
                break;

            case("/freePost.board"):
                System.out.println("page freePost");
                try {
                    response.sendRedirect("/board/freePost.jsp");
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendRedirect("/");
                }
                break;
            case("/postInFreeBoard.board"):
                System.out.println("post In FreeBoard");
                try {
                    FreeBoardDAO freeBoardDao = FreeBoardDAO.getInstance();
                    MembersDAO membersDao = MembersDAO.getInstance();

                    String id = (String) request.getSession().getAttribute("loginId");
                    String writer = membersDao.selectMember(id).getName();
                    String title = request.getParameter("title");
                    String content = request.getParameter("content");
                    freeBoardDao.posting(new FreeBoardDTO(writer, title, content));
                    response.sendRedirect("/freeBoard.board");
                }catch (Exception e){
                    e.printStackTrace();
                    response.sendRedirect("/");
                }
                break;
            default:
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}