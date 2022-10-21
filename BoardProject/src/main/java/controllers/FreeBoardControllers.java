package controllers;

import dao.FreeBoardDAO;
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
        FreeBoardDTO post;
        String id;
        String writer;
        String content;
        String title;
        int postNum;
        try {
            FreeBoardDAO freeBoardDao = FreeBoardDAO.getInstance();
            switch (uri) {
                case ("/freeBoard.board"):
                    System.out.println("page freeBoard");
                    int cpage = Integer.parseInt(request.getParameter("cpage"));
//                    List<FreeBoardDTO> posts = freeBoardDao.searchAllPosting();
                    FreeBoardDTO pageInfo = new FreeBoardDTO(10,8);
                    String navi = freeBoardDao.getPageNavi(cpage, pageInfo);
                    int start = ((cpage-1) * pageInfo.getRecordCountPerPage()) + 1;
                    int end = cpage*pageInfo.getRecordCountPerPage();
                    if(end > pageInfo.getRecordTotalCount()){
                        end = pageInfo.getRecordTotalCount();
                    }

                    List<FreeBoardDTO> posts = freeBoardDao.selectBySeqRange(start, end);
                    request.setAttribute("posts", posts);
                    request.setAttribute("navi", navi);
                    request.getRequestDispatcher("/board/freeBoard.jsp").forward(request, response);
                    break;
                case ("/freePost.board"):
                    System.out.println("page freePost");
                    response.sendRedirect("/board/freePost.jsp");
                    break;
                case ("/postInFreeBoard.board"):
                    System.out.println("post In FreeBoard");
                    id = (String) request.getSession().getAttribute("loginId");
                    writer = id;
                    title = request.getParameter("title");
                    content = request.getParameter("content");
                    freeBoardDao.posting(new FreeBoardDTO(writer, title, content));
                    response.sendRedirect("/freeBoard.board");
                    break;
                case ("/detail.board"):
                    System.out.println("detail Board");
                    postNum = Integer.parseInt(request.getParameter("postNum"));
                    post = freeBoardDao.searchPosting(postNum);
                    if (request.getSession().getAttribute("loginId").equals(post.getId())) {
                        freeBoardDao.viewCountUp(postNum);
                    }
                    request.setAttribute("post", post);
                    request.setAttribute("category", "자유게시판");
                    request.getRequestDispatcher("/board/post.jsp").forward(request, response);
                    break;
                case ("/delete.board"):
                    System.out.println("detail Board");
                    FreeBoardDAO.getInstance().deletePosting(Integer.parseInt(request.getParameter("postNum")));
                    response.sendRedirect("/freeBoard.board");
                    break;
                case ("/toModify.board"):
                    System.out.println("to modify Board");
                    post = freeBoardDao.searchPosting(Integer.parseInt(request.getParameter("postNum")));
                    request.setAttribute("post", post);
                    request.getRequestDispatcher("/board/freePostModify.jsp").forward(request, response);
                    break;
                case ("/modify.board"):
                    System.out.println("modify Board");
                    id = (String) request.getSession().getAttribute("loginId");
                    title = request.getParameter("title");
                    content = request.getParameter("content");
                    post = new FreeBoardDTO(id, title, content);
                    postNum = Integer.parseInt(request.getParameter("postNum"));
                    post.setPostNum(postNum);
                    FreeBoardDAO.getInstance().modifyPosting(post);
                    response.sendRedirect("/detail.board?postNum=" + postNum);
                    break;
                default:
                    System.out.println("error");
                    response.sendRedirect("/");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}