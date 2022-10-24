package controllers;

import dao.MembersDAO;
import dto.MembersDTO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.member")
public class MembersController extends HttpServlet {

//    private static Logger logger = Logger.getGlobal();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        logger.info(request.getRemoteAddr());
        System.out.println(request.getRemoteAddr());

        request.setCharacterEncoding("utf8");

        String uri = request.getRequestURI();



        if (uri.equals("/member/dupleCheck.member")) {
//            logger.info("duplecheck");
            System.out.println("duplecheck");
            try {
                MembersDAO dao = MembersDAO.getInstance();
                String id = request.getParameter("id");
                boolean result = dao.isIdExist(id);

                request.setAttribute("result", result);
                request.setAttribute("id", id);
                request.getRequestDispatcher("dupleCheckView.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("/");
            }
        }

        /*
        {id,pw1(pw),name,phone,email,zipcode,address1,address2}
        MemberDAO.signup(FreeBoardDTO)의 결과값이 0보다 크면(insert를 성공하면) index.jsp열기
        */
        else if (uri.equals("/member/signup.member")) {
//            logger.info("signup");
            System.out.println("signup");
            try {
                MembersDAO dao = MembersDAO.getInstance();

                if(dao.signup(new MembersDTO(request)) > 0) {
                    response.sendRedirect("/index.jsp");
                }
            }
            catch (Exception e){
                e.printStackTrace();
                response.sendRedirect("/");
            }
        }

        /*
        request에서 받은 아이디와 비밀번호를 받고 비밀번호를 암호화 하여 dao.login에 넣어 결과를 반환받는다.
         */
        else if (uri.equals("/login.member")) {
//            logger.info("login");
            System.out.println("login");
            try {
                MembersDAO dao = MembersDAO.getInstance();
                
                String id = request.getParameter("id");
                String pw = request.getParameter("pw");

                boolean result = dao.login(id, pw);

                if (result) {
                    request.getSession().setAttribute("loginId",id);
                    response.sendRedirect("/");
                }
                else{
                    response.sendRedirect("/");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("/");
            }
        }

        else if (uri.equals("/logout.member")){
//            logger.info("logout");
            System.out.println("logout");
            // 현재 내 창고 안에 있는 Session data를 모두 지움.
            request.getSession().invalidate();
            response.sendRedirect("/");
        }

        else if(uri.equals("/memberout.member")){
//            logger.info("memberout");
            System.out.println("memberout");
            try {
                MembersDAO dao = MembersDAO.getInstance();
                dao.memberout((String) request.getSession().getAttribute("loginId"));
                request.getSession().invalidate();
                response.sendRedirect("/");
            }catch (Exception e){
                e.printStackTrace();
                response.sendRedirect("/");
            }
        }

        else if(uri.equals("/mypage.member")){
//            logger.info("mypage");
            System.out.println("mypage");
            try {
                MembersDAO dao = MembersDAO.getInstance();
                request.setAttribute("member", dao.selectMember((String) request.getSession().getAttribute("loginId")));
                request.getRequestDispatcher("/member/mypage.jsp").forward(request,response);
            }catch (Exception e){
                e.printStackTrace();
                response.sendRedirect("/");
            }
        }

        else if (uri.equals("/member/modify.member")) {
//            logger.info("modify");
            System.out.println("modify");
            try {
                MembersDAO dao = MembersDAO.getInstance();

                int result = dao.modify(new MembersDTO(request));
                if (result > 0) {
//                    logger.info("success");
                    System.out.println("success");
                }
                else{
//                    logger.info("fail");
                    System.out.println("fail");
                }
                response.sendRedirect("/");
            }catch (Exception e){
                e.printStackTrace();
                response.sendRedirect("/");
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}