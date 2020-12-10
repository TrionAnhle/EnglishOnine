package com.cnpm.controller.web;

import com.cnpm.constant.SystemConstant;
import com.cnpm.model.HocVienModel;
import com.cnpm.service.IHocVienService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/cap-nhat"})
public class HocVienController extends HttpServlet {

    @Inject
    private IHocVienService hocVienService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        HocVienModel model = new HocVienModel();

        model = hocVienService.timMot(Long.parseLong(id));
        req.setAttribute(SystemConstant.USERMODEL,model);
        req.getRequestDispatcher("/views/updateform.jsp").forward(req,resp);
    }
}
