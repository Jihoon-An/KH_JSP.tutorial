package controllers;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import dao.CommentDAO;
import dao.FilesDAO;
import dao.FreeBoardDAO;
import dto.CommentDTO;
import dto.FilesDTO;
import dto.FreeBoardDTO;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
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

        try {

            FreeBoardDAO freeBoardDao = FreeBoardDAO.getInstance();

            switch (uri) {

                /*
                Need : cpage
                set : X
                Next URI : "/board/freePost.jsp"
                 */
                case ("/freeBoard.board"): {
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
                }

                /*
                Need : X
                set : X
                Next URI : "/board/freePost.jsp"
                 */
                case ("/freePost.board"): {
                    System.out.println("page freePost");

                    response.sendRedirect("/board/freePost.jsp");
                    break;
                }

                /*
                Need : loginId -> id, title, content
                set : cpage
                Next URI : "/freeBoard.board?cpage=1"
                 */
                case ("/postInFreeBoard.board"): {
                    System.out.println("post In FreeBoard");
                    int postNum = freeBoardDao.getPostSeq();
                    //업로드파일생성
                    String savePath = request.getServletContext().getRealPath("/files"); //런타임 webapp 폴더를 불러옴.
                    File fileSavePath = new File(savePath);
                    if (!fileSavePath.exists()) { //find directory
                        fileSavePath.mkdir();//make directory
                    }
                    //파일업로드
                    int maxSize = 1024 * 1024 * 10;
                    MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF8", new DefaultFileRenamePolicy());

                    Enumeration<String> e = multi.getFileNames();//file들을 모두 가지고 옴
                    while(e.hasMoreElements()){ //하나씩 넘어가며 있으면 가져옴
                        String name = e.nextElement();
                        String originName = multi.getOriginalFileName(name);
                        String sysName = multi.getFilesystemName(name);
                        FilesDTO filesDTO = new FilesDTO(0, 0, originName, sysName, postNum);
                        FilesDAO.getInstance().insert(filesDTO);
                    }
//
//                    String originName = multi.getOriginalFileName("file");
//                    String sysName = multi.getFilesystemName("file");
//
//                    FilesDTO filesDTO = new FilesDTO(0, 0, originName, sysName, postNum);
//
//                    FilesDAO.getInstance().insert(filesDTO);

                    //글작성
                    String id = (String) request.getSession().getAttribute("loginId");
                    String title = multi.getParameter("title");
                    String content = multi.getParameter("content");
                    freeBoardDao.posting(new FreeBoardDTO(id, title, content, postNum));

                    response.sendRedirect("/freeBoard.board?cpage=1");
                    break;
                }

                /*
                 * Need : postNum
                 * Set : post, comments
                 * Next URI : "/board/post.jsp"
                 */
                case ("/detail.board"): {
                    System.out.println("detail Board");
                    //get posting
                    int postNum = Integer.parseInt(request.getParameter("postNum"));
                    FreeBoardDTO post = freeBoardDao.searchPosting(postNum);
                    String loginId = (String) request.getSession().getAttribute("loginId");
                    //view count up
                    if (loginId == null || !loginId.equals(post.getId())) {
                        freeBoardDao.viewCountUp(postNum);
                    }
                    //get comments
                    List<CommentDTO> comments = CommentDAO.getInstance().select(postNum, 1, 3);

                    request.setAttribute("post", post);
                    request.setAttribute("category", "자유게시판");

                    request.setAttribute("comments", comments);

                    // 파일 불러오기
                    List<FilesDTO> files = FilesDAO.getInstance().selectAll(postNum);
                    request.setAttribute("files", files);

                    request.getRequestDispatcher("/board/post.jsp").forward(request, response);
                    break;
                }
                /*
                Need : postNum
                set : X
                Next URI : "/freeBoard.board?cpage=1"
                 */
                case ("/delete.board"): {
                    System.out.println("detail Board");

                    int postNum = Integer.parseInt(request.getParameter("postNum"));
                    FreeBoardDAO.getInstance().deletePosting(postNum);

                    response.sendRedirect("/freeBoard.board?cpage=1");
                    break;
                }
                /*
                Need : postNum
                set : post
                Next URI : "/board/freePostModify.jsp"
                 */
                case ("/toModify.board"): {
                    System.out.println("to modify Board");
                    int postNum = Integer.parseInt(request.getParameter("postNum"));
                    FreeBoardDTO post = freeBoardDao.searchPosting(postNum);

                    request.setAttribute("post", post);

                    List<FilesDTO> files = FilesDAO.getInstance().selectAll(postNum);
                    request.setAttribute("files", files);

                    request.getRequestDispatcher("/board/freePostModify.jsp").forward(request, response);
                    break;
                }
                 /*
                Need : loginId -> id, title, content, postNum
                set : postNum
                Next URI : "/detail.board?postNum=" + postNum
                 */
                case ("/modify.board"): {
                    System.out.println("modify Board");

                    String id = (String) request.getSession().getAttribute("loginId");
                    String title = request.getParameter("title");
                    String content = request.getParameter("content");
                    int postNum = Integer.parseInt(request.getParameter("postNum"));

                    FreeBoardDTO post = new FreeBoardDTO(id, title, content, postNum);

                    FreeBoardDAO.getInstance().modifyPosting(post);

                    response.sendRedirect("/detail.board?postNum=" + postNum);
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