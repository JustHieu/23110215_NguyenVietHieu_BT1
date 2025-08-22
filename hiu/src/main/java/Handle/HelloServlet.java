package Handle;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/hello"})
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Truy cập bằng URL -> GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");

        // Tìm cookie "username"
        String name = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("username".equals(c.getName())) {
                    name = c.getValue();
                    break;
                }
            }
        }

        String ctx = req.getContextPath();
        String base = (ctx == null || ctx.isEmpty()) ? "" : ctx;

        if (name == null || name.isBlank()) {
            // Chưa đăng nhập -> quay lại /login
            resp.sendRedirect(base + "/login");
            return;
        }

        // Đã đăng nhập -> hiển thị
        PrintWriter out = resp.getWriter();
        out.println("<h1>Xin chào, " + name + "!</h1>");
        out.println("<p><a href='" + base + "/logout'>Đăng xuất</a></p>");
        out.close();
    }

    // Nếu có ai POST vào /hello thì chuyển về GET cho thống nhất
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
