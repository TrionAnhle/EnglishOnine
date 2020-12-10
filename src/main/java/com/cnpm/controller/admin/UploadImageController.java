package com.cnpm.controller.admin;

import com.cnpm.constant.SystemConstant;
import com.cnpm.model.GiaoVienModel;
import com.cnpm.model.KhoaHocModel;
import com.cnpm.service.IGiaoVienService;
import com.cnpm.utils.FormUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/admin-upload-anh"})
@MultipartConfig(maxFileSize = 16177215)
public class UploadImageController extends HttpServlet {
    @Inject
    private IGiaoVienService giaoVienService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
		
		String id = req.getParameter("id");
        Part filePart = req.getPart("anh");
        KhoaHocModel model = FormUtil.toModel(KhoaHocModel.class, req);
         if(id.equals("")) model.setId(null);
		
		try{
            File pathImage = new File(SystemConstant.PATHIMAGE);
            if(!pathImage.exists()){
                pathImage.mkdirs();
            }
            filePart.write( SystemConstant.PATHIMAGE + filePart.getSubmittedFileName());
        }catch (IOException e){
        }
        GiaoVienModel modelGiaoVien = (GiaoVienModel) req.getSession().getAttribute(SystemConstant.USERMODEL);
        if(modelGiaoVien.getVaiTro().equals(SystemConstant.ADMIN)){
            modelGiaoVien.setListResult((ArrayList<GiaoVienModel>) giaoVienService.timTatCa(null));
            req.setAttribute("modelGiaoVien",modelGiaoVien);
        }
        model.setAnh(filePart.getSubmittedFileName());
        req.setAttribute(SystemConstant.MODEL,model);
        req.getRequestDispatcher("/views/admin/khoahoc/edit.jsp").forward(req,resp);
    }
}
