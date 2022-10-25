package controllers;


import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import dao.FilesDAO;
import dto.FilesDTO;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.file")
public class FilesController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        try {
            switch (uri) {
                case ("/upload.file"): {
                    int maxSize = 1024 * 1024 * 10;
                    /*
                    String savePath = request.getServletContext().getRealPath("/"); //런타임 webapp 폴더를 불러옴.
                    */
                    String savePath = request.getServletContext().getRealPath("/files"); //런타임 webapp 폴더를 불러옴.
                    File fileSavePath = new File(savePath);
                    if (!fileSavePath.exists()) { //find directory
                        fileSavePath.mkdir();//make directory
                    }
                    // multi에는 파일에 대한 정보다 있음.
                    MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF8", new DefaultFileRenamePolicy());
                    String originName = multi.getOriginalFileName("file");
                    String sysName = multi.getFilesystemName("file");

                    FilesDTO filesDTO = new FilesDTO(0, originName, sysName, 0);

                    FilesDAO.getInstance().insert(filesDTO);
                }
                case "/filelist.file": {

                    List<FilesDTO> files = FilesDAO.getInstance().selectAll();

                    request.setAttribute("files", files);
                    request.getRequestDispatcher("/").forward(request, response);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}
