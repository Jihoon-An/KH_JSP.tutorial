package controllers;

import dao.CommentDAO;
import dto.CommentDTO;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.comment")
public class CommentController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println(request.getRemoteAddr());
        request.setCharacterEncoding("utf8");

        String uri = request.getRequestURI();

        try {
            switch (uri) {

                /*
                Need : writer(loginId), contents, postNum
                set :  postNum
                dispatcher URI : "/board/post.jsp"
                 */
                case ("/insert.comment"): {
                    System.out.println("/insert.comment");
                    String writer = (String) request.getSession().getAttribute("loginId");
                    String contents = request.getParameter("contents");
                    int postNum = Integer.parseInt(request.getParameter("postNum"));

                    new CommentDAO().insert(new CommentDTO(writer, contents, postNum));

                    request.setAttribute("postNum", postNum);
                    request.getRequestDispatcher("/detail.board").forward(request, response);
                    break;
                }
                /*
                Need : commentNum,postNum
                set : postNum
                dispatcher URI : "/board/post.jsp"
                 */
                case ("/delete.comment"): {
                    System.out.println("/delete.comment");
                    int commentNum = Integer.parseInt(request.getParameter("commentNum"));
                    new CommentDAO().delete(commentNum);

                    request.setAttribute("postNum", request.getParameter("postNum"));
                    request.getRequestDispatcher("/detail.board").forward(request, response);
                    break;
                }
                    /*
                    Need :contents, loginId, postNum
                     */
                case ("/modify.comment"): {
                    System.out.println("/modify.comment");

                    String writer = (String) request.getSession().getAttribute("loginId");
                    String contents = request.getParameter("contents");

                    int postNum = Integer.parseInt(request.getParameter("postNum"));
                    CommentDTO dto = new CommentDTO(writer, contents, postNum);
                    dto.setCommentNum(Integer.parseInt(request.getParameter("commentNum")));
                    new CommentDAO().modify(dto);

                    request.setAttribute("postNum", postNum);
                    request.getRequestDispatcher("/detail.board").forward(request, response);
                    break;
                }
                default: {
                    System.out.println("error");
                    response.sendRedirect("/error.jsp");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
            response.sendRedirect("/error.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
