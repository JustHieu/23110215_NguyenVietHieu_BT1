package Handle;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/login"})
public final class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Hiển thị form và/hoặc xử lý login bằng GET (dùng cho test)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String user = req.getParameter("username");
        String pass = req.getParameter("password");

        // Nếu chưa truyền tham số -> hiện form GET để test nhanh
        if (user == null || pass == null) {
            PrintWriter out = resp.getWriter();
            out.println("""
                <form method='get' action='login'>
                  <input name='username' placeholder='user'><br/>
                  <input name='password' type='password' placeholder='pass'><br/>
                  <button>Login (GET)</button>
                </form>
            """);
            out.close();
            return;
        }

        handleLogin(req, resp, user, pass);
    }

    // Xử lý login bằng POST (form chuẩn)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String user = req.getParameter("username");
        String pass = req.getParameter("password");

        handleLogin(req, resp, user, pass);
    }

    // dùng chung cho GET/POST
    private void handleLogin(HttpServletRequest req, HttpServletResponse resp, String user, String pass)
            throws IOException {

        String ctx = req.getContextPath();
        String base = (ctx == null || ctx.isEmpty()) ? "" : ctx;

        if ("trung".equals(user) && "123".equals(pass)) {
            // Tạo cookie username và thiết lập thời gian sống 30s
            Cookie cookie = new Cookie("username", user);
            cookie.setMaxAge(30);
            // Áp dụng cho toàn ứng dụng
            cookie.setPath(base.isEmpty() ? "/" : base);
            resp.addCookie(cookie);

            // Chuyển sang trang HelloServlet (map /hello)
            resp.sendRedirect(base + "/hello");
        } else {
            // Sai thông tin → quay về trang login
            resp.sendRedirect(base + "/login");
        }
    }
}
