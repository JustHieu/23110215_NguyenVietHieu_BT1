package session;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/showsession"})
public class ShowSession extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        HttpSession s = req.getSession(false); // false = không tự tạo mới
        if (s == null) {
            // chưa có session → quay lại tạo
            resp.sendRedirect(req.getContextPath() + "/createsession");
            return;
        }

        String ten = (String) s.getAttribute("ten");
        Integer tuoi = (Integer) s.getAttribute("tuoi");

        if (ten == null || tuoi == null) {
            // chưa có dữ liệu trong session → quay lại tạo
            resp.sendRedirect(req.getContextPath() + "/createsession");
            return;
        }

        out.println("<h2>Xin chào bạn: " + ten + " - Tuổi: " + tuoi + "</h2>");
        out.close();
    }
}
