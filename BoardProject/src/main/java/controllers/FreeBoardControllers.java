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

        switch (uri) {
            case ("/freeBoard.board"):
                System.out.println("page freeBoard");
                try {
                    List<FreeBoardDTO> posts = FreeBoardDAO.getInstance().loadPostingList();
                    request.setAttribute("posts", posts);

                    request.getRequestDispatcher("/board/freeBoard.jsp").forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendRedirect("/");
                }
                break;

            case ("/freePost.board"):
                System.out.println("page freePost");
                try {
                    response.sendRedirect("/board/freePost.jsp");
                } catch (Exception e) {
                    System.out.println("1");
                    e.printStackTrace();
                    response.sendRedirect("/");
                }
                break;
            case ("/postInFreeBoard.board"):
                System.out.println("post In FreeBoard");
                try {
                    FreeBoardDAO freeBoardDao = FreeBoardDAO.getInstance();
                    String id = (String) request.getSession().getAttribute("loginId");
                    String writer = id;
                    String title = request.getParameter("title");
                    String content = request.getParameter("content");
                    freeBoardDao.posting(new FreeBoardDTO(writer, title, content));
                    response.sendRedirect("/freeBoard.board");
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendRedirect("/");
                }
                break;
            case ("/detail.board"):
                System.out.println("detail Board");
                try {
                    FreeBoardDAO freeBoardDao = FreeBoardDAO.getInstance();
                    int postNum = Integer.parseInt(request.getParameter("postNum"));
                    FreeBoardDTO post = freeBoardDao.searchLoadPost(postNum);
                    if (request.getSession().getAttribute("loginId").equals(post.getId())) {
                        freeBoardDao.veiwCountUp(postNum);
                    }
                    request.setAttribute("post", post);
                    request.setAttribute("category", "자유게시판");
                    request.getRequestDispatcher("/board/post.jsp").forward(request, response);
                } catch (Exception e) {
                    System.out.println("2");
                    e.printStackTrace();
                    response.sendRedirect("/");
                }
                break;
            case ("/delete.board"):
                System.out.println("detail Board");
                try {
                    FreeBoardDAO.getInstance().deletePost(Integer.parseInt(request.getParameter("postNum")));
                    response.sendRedirect("/freeBoard.board");
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendRedirect("/");
                }
                break;
            case ("/toModify.board"):
                System.out.println("to modify Board");
                try {
                    FreeBoardDTO post = FreeBoardDAO.getInstance().searchLoadPost(Integer.parseInt(request.getParameter("postNum")));
                    request.setAttribute("post", post);
                    request.getRequestDispatcher("/board/freePostModify.jsp").forward(request, response);
                } catch (Exception e) {
                    System.out.println("3");
                    e.printStackTrace();
                    response.sendRedirect("/");
                }
                break;
            case ("/modify.board"):
                System.out.println("modify Board");
                try {
                    String id = (String) request.getSession().getAttribute("loginId");
                    String title = request.getParameter("title");
                    String content = request.getParameter("content");
                    FreeBoardDTO freeBoardDTO = new FreeBoardDTO(id, title, content);
                    int postNum = Integer.parseInt(request.getParameter("postNum"));
                    freeBoardDTO.setPostNum(postNum);
                    FreeBoardDAO.getInstance().modify(freeBoardDTO);
                    response.sendRedirect("/detail.board?postNum=" + postNum);
                } catch (Exception e) {
                    System.out.println("4");
                    e.printStackTrace();
                    response.sendRedirect("/");
                }
                break;
            default:
                System.out.println("error");
                response.sendRedirect("/");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}