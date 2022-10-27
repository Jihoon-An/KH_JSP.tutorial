package controllers;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("*.file")
public class FileController extends ControllerAbs {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
        String uri = request.getRequestURI();
        String filePath = request.getServletContext().getRealPath("files");
        try {
            switch (uri) {
                case "/download.file": {
                    String sysName = request.getParameter("sysName");
                    String oriName = request.getParameter("originName");
                    oriName = new String(oriName.getBytes("utf8"),"ISO-8859-1");
                    /*
                    filePath -> target -> FileInputStream -> DataInputStream
                                    └-> fileContents  <-------------┘
                     */
                    File target = new File(filePath + "/" + sysName);
//                    response.reset(); // 데이터를 보내기 전에 에러를 방지하기 위함. (필수 X)
                    // 브라우저에 file이라는 것을 알려줌.(다운로드로 동작시킴.)
                    // filename은 다운로드시 파일명.
                    response.addHeader("content-Disposition", "attachment;filename=\"" + oriName + "\"");

                    byte[] fileContents = new byte[(int) target.length()]; // file을 램에 받기 위해 byte형태의 배열을 생성.
                    //stream 만들기
                    try (FileInputStream fis = new FileInputStream(target);
                         DataInputStream dis = new DataInputStream(fis);
                         ServletOutputStream sos = response.getOutputStream();
                    ) {
                        dis.readFully(fileContents);//배열에 데이터 담기.
                        sos.write(fileContents);
                        sos.flush();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
            response.sendRedirect("/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        super.doPost(request, response);
    }
}
