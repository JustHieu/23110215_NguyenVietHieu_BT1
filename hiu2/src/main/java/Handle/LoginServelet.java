package Handle;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/login"})
public class LoginServelet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Hiển thị form (GET)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("""
                <h2>Đăng nhập</h2>
                <form action="login" method="post">
                  <input name="username" placeholder="user"><br/>
                  <input name="password" type="password" placeholder="pass"><br/>
                  <button>Login</button>
                </form>
            """);
        }
    }

    // Xử lý login (POST)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("trungnh".equals(username) && "123".equals(password)) {
            // Tạo/lấy session và lưu thông tin đăng nhập
            HttpSession session = request.getSession();
            session.setAttribute("uname", username);
            session.setMaxInactiveInterval(10 * 60); // 10 phút (giây)

            // Chuyển sang trang sau đăng nhập
            response.sendRedirect(request.getContextPath() + "/profile");
        } else {
            response.setContentType("text/html; charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("Tài khoản hoặc mật khẩu không chính xác<br/>");
                // Hiển thị lại form
                request.getRequestDispatcher("/login").include(request, response);
            }
        }
    }
}
