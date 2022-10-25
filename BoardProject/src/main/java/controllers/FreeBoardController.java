package controllers;

import dao.CommentDAO;
import dao.FreeBoardDAO;
import dto.CommentDTO;
import dto.FreeBoardDTO;

import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.board")
public class FreeBoardController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

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

                /*
                Need : cpage
                set : X
                Next URI : "/board/freePost.jsp"
                 */
                case ("/freeBoard.board"):
                    System.out.println("page freeBoard");

                    int cpage = Integer.parseInt(request.getParameter("cpage"));
//                    List<FreeBoardDTO> posts = freeBoardDao.searchAllPosting();
                    FreeBoardDTO pageInfo = new FreeBoardDTO(10, 8);
                    String navi = freeBoardDao.getPageNavi(cpage, pageInfo);

                    int start = ((cpage - 1) * pageInfo.getRecordCountPerPage()) + 1;
                    int end = cpage * pageInfo.getRecordCountPerPage();
                    if (end > pageInfo.getRecordTotalCount()) {
                        end = pageInfo.getRecordTotalCount();
                    }

                    List<FreeBoardDTO> posts = freeBoardDao.selectBySeqRange(start, end);

                    request.setAttribute("posts", posts);
                    request.setAttribute("navi", navi);
                    request.getRequestDispatcher("/board/freeBoard.jsp").forward(request, response);
                    break;

                /*
                Need : X
                set : X
                Next URI : "/board/freePost.jsp"
                 */
                case ("/freePost.board"):
                    System.out.println("page freePost");

                    response.sendRedirect("/board/freePost.jsp");
                    break;

                /*
                Need : loginId -> id, title, content
                set : cpage
                Next URI : "/freeBoard.board?cpage=1"
                 */
                case ("/postInFreeBoard.board"):
                    System.out.println("post In FreeBoard");

                    id = (String) request.getSession().getAttribute("loginId");
                    title = request.getParameter("title");
                    content = request.getParameter("content");

                    freeBoardDao.posting(new FreeBoardDTO(id, title, content));

                    response.sendRedirect("/freeBoard.board?cpage=1");
                    break;

                /*
                 * Need : postNum
                 * Set : post, comments
                 * Next URI : "/board/post.jsp"
                 */
                case ("/detail.board"):
                    System.out.println("detail Board");
                    //get posting
                    postNum = Integer.parseInt(request.getParameter("postNum"));
                    post = freeBoardDao.searchPosting(postNum);
                    String loginId = (String) request.getSession().getAttribute("loginId");
                    //view count up
                    if (loginId == null || !loginId.equals(post.getId())) {
                        freeBoardDao.viewCountUp(postNum);
                    }
                    //get comments
                    List<CommentDTO> comments = new CommentDAO().select(postNum, 1, 3);

                    request.setAttribute("post", post);
                    request.setAttribute("category", "자유게시판");

                    request.setAttribute("comments", comments);

                    request.getRequestDispatcher("/board/post.jsp").forward(request, response);
                    break;

                /*
                Need : postNum
                set : X
                Next URI : "/freeBoard.board?cpage=1"
                 */
                case ("/delete.board"):
                    System.out.println("detail Board");

                    postNum = Integer.parseInt(request.getParameter("postNum"));
                    FreeBoardDAO.getInstance().deletePosting(postNum);

                    response.sendRedirect("/freeBoard.board?cpage=1");
                    break;

                /*
                Need : postNum
                set : post
                Next URI : "/board/freePostModify.jsp"
                 */
                case ("/toModify.board"):
                    System.out.println("to modify Board");

                    post = freeBoardDao.searchPosting(Integer.parseInt(request.getParameter("postNum")));

                    request.setAttribute("post", post);

                    request.getRequestDispatcher("/board/freePostModify.jsp").forward(request, response);
                    break;

                 /*
                Need : loginId -> id, title, content, postNum
                set : postNum
                Next URI : "/detail.board?postNum=" + postNum
                 */
                case ("/modify.board"):
                    System.out.println("modify Board");

                    id = (String) request.getSession().getAttribute("loginId");
                    title = request.getParameter("title");
                    content = request.getParameter("content");
                    postNum = Integer.parseInt(request.getParameter("postNum"));

                    post = new FreeBoardDTO(id, title, content);
                    post.setPostNum(postNum);

                    FreeBoardDAO.getInstance().modifyPosting(post);

                    response.sendRedirect("/detail.board?postNum=" + postNum);
                    break;

                default:
                    System.out.println("error");
                    response.sendRedirect("/error.jsp");
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