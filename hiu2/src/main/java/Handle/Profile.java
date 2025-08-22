package Handle;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/profile"})
public class Profile extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");

        // Không tạo session mới cho khách chưa login
        HttpSession session = req.getSession(false);
        String uname = (session != null) ? (String) session.getAttribute("uname") : null;

        if (uname == null || uname.isBlank()) {
            // Chưa đăng nhập → quay về /login
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        try (PrintWriter out = resp.getWriter()) {
            out.println("<h2>Chào bạn, " + uname + " đến với trang quản lý tài khoản</h2>");
            out.println("<a href='" + req.getContextPath() + "/logout'>Đăng xuất</a>");
        }
    }
}
