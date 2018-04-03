package com.free.framework.plateform.file;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * com.free.framework.plateform.file.FileController
 *
 * @author lipeng
 * @dateTime 2018/4/3 17:35
 */
@MultipartConfig
@RequestMapping(FileControllerMappingUrl.FILE_CONTROLLER)
@Controller
public class FileController {

    @RequestMapping(FileControllerMappingUrl.FILE_UPLOAD)
    public void uploadFile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Part part = request.getPart("file");
        String filePath = "D://test_upload.jpg";
        part.write(filePath);
        response.getWriter().write(filePath);
    }
}
