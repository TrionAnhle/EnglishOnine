package com.cnpm.utils;

import javax.servlet.http.HttpServletRequest;

public class MessageUtil {
    public static void hienThiThongBao(HttpServletRequest request) {
        if (request.getParameter("message") != null) {
            String messageResponse = "";
            String alert = "";
            String message = request.getParameter("message");
            if (message.equals("them_thanhcong")) {
                messageResponse = "Thêm thành công!";
                alert = "success";
            } else if (message.equals("capnhat_thanhcong")) {
                messageResponse = "Cập nhật thành công!";
                alert = "success";
            } else if (message.equals("xoa_thanhcong")) {
                messageResponse = "Xóa thành công";
                alert = "success";
            } else if (message.equals("dangnhap_thatbai")) {
                messageResponse = "Sai email hoặc mật khẩu";
                alert = "danger";
            } else if (message.equals("loi_hethong")) {
                messageResponse = "Lỗi hệ thống!";
                alert = "danger";
            } else if (message.equals("loi_dangky")) {
                messageResponse = "Email đã tồn tại, vui lòng sử dụng email khác!";
                alert = "danger";
            } else if (message.equals("loi_xoa")) {
                messageResponse = "Xóa thất bại vì tồn tại học viên đang đăng ký khóa học!";
                alert = "danger";
            }
            request.setAttribute("messageResponse", messageResponse);
            request.setAttribute("alert", alert);
        }
    }
}
