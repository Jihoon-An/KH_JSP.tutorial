package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dto.FreeBoardDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("*.ajax")
public class AjaxController extends ControllerAbs {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
        String uri = request.getRequestURI();

        try {
            switch (uri) {
                case "/exam01.ajax":
                {
                    System.out.println("/exam01.ajax");
                    break;
                }
                case "/exam02.ajax":
                {
                    System.out.println(request.getParameter("key1") +"  "+request.getParameter("key2"));
                    break;
                }
                case "/exam03.ajax":
                {
                    System.out.println("/exam03.ajax");
                    response.getWriter().append("Ajax Works");
                    break;
                }
                case "/exam04.ajax":
                {
                    System.out.println("exam04 동작");
//                    response.getWriter().append("[\"Apple\",\"Orange\",\"Mango\"]");
                    String[] arr = new String[]{"Apple", "Orange", "Mango"};
                    Gson gson = new Gson();
                    response.getWriter().append(gson.toJson(arr));

                    break;
                }
                case "/exam05.ajax":
                {
                    Gson gson = new Gson();
                    FreeBoardDTO dto = new FreeBoardDTO("Tester", "Title", "contentsss", 123);

                    String jsonString = gson.toJson(dto);
                    response.getWriter().append(jsonString);
                    break;
                }
                case "/exam06.ajax":
                {
                    List<FreeBoardDTO> list = new ArrayList<>();
                    list.add(new FreeBoardDTO("writer1", "test1", "content1", 1));
                    list.add(new FreeBoardDTO("writer2", "test2", "content2", 2));
                    list.add(new FreeBoardDTO("writer3", "test3", "content3", 3));

                    Gson g = new Gson();
                    String jsonString = g.toJson(list);
                    response.getWriter().append(jsonString);
                    break;
                }
                case "/exam07.ajax":
                {
                    // 직원번호 / 직원명 / 부서명 / 직급명
                    JsonObject total = new JsonObject();

                    JsonObject obj = new JsonObject();
                    obj.addProperty("emp_id","101");
                    obj.addProperty("emp_name","Jack");
                    obj.addProperty("dept_title","해외영업부");
                    obj.addProperty("job_name","대리");

                    FreeBoardDTO dto = new FreeBoardDTO("Tester", "Title", "contentsss", 123);
                    String jsonString = new Gson().toJson(dto);

                    total.addProperty("dto", jsonString);
                    total.addProperty("obj", obj.toString());

                    response.getWriter().append(total.toString());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/ajax.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
}
