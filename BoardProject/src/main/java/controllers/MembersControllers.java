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
public class MembersControllers extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");

        String uri = request.getRequestURI();
        /*
         /member/dupleCheck.member
         */
        if (uri.equals("/member/dupleCheck.member")) {
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

        /*
        /member/signup.member

        {id,pw1(pw),name,phone,email,zipcode,address1,address2}
        MemberDAO.signup(MemberDTO)의 결과값이 0보다 크면(insert를 성공하면) index.jsp열기
        */
        else if (uri.equals("/member/signup.member")) {
            try {
                MembersDAO dao = MembersDAO.getInstance();

                if(dao.signup(new MembersDTO(request)) > 0) {
                    response.sendRedirect("/index.jsp");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        /*
         /login.member
        request에서 받은 아이디와 비밀번호를 받고 비밀번호를 암호화 하여 dao.login에 넣어 결과를 반환받는다.
         */
        else if (uri.equals("/login.member")) {
            try {
                MembersDAO dao = MembersDAO.getInstance();
                
                String id = request.getParameter("id");
                String pw = request.getParameter("pw");

                boolean result = dao.login(id, pw);
                System.out.println(result);
                if (result) {
                    request.getSession().setAttribute("loginId",id);
                    response.sendRedirect("/");
                }
                else{
                    request.getSession().setAttribute("loginId",id);
                    response.sendRedirect("/");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        else if (uri.equals("/logout.member")){
            // 현재 내 창고 안에 있는 Session data를 모두 지움.
            request.getSession().invalidate();
            response.sendRedirect("/");
        }

        else if(uri.equals("/memberout.member")){
            try {
                MembersDAO dao = MembersDAO.getInstance();
                dao.memberout((String) request.getSession().getAttribute("loginId"));
                request.getSession().invalidate();
                response.sendRedirect("/");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        else if(uri.equals("/mypage.member")){
            try {
                MembersDAO dao = MembersDAO.getInstance();
                request.setAttribute("member", dao.selectMember((String) request.getSession().getAttribute("loginId")));
                request.getRequestDispatcher("/member/mypage.jsp").forward(request,response);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}