package session;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/createsession"})
public class CreateaSession extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // khởi tạo session
        HttpSession s = req.getSession();

        // Gán dữ liệu vào session
        s.setAttribute("ten", "Nguyễn Hữu Trung");
        s.setAttribute("tuoi", Integer.valueOf(40));

        // thiết lập thời gian tồn tại session (giây)
        s.setMaxInactiveInterval(30);

        // hiển thị thông báo lên web
        resp.setContentType("text/html; charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("Xin chào bạn, session đã được tạo.");
        }
    }
}
