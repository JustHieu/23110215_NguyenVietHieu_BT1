package cookie;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/createcookie"})
public class CreateCookie extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy dữ liệu từ form
        String ten = request.getParameter("ten");
        String holot = request.getParameter("holot");

        // Tạo cookies
        Cookie firstName = new Cookie("ten", ten);
        Cookie lastName = new Cookie("holot", holot);

        // Cookie sống 24h
        firstName.setMaxAge(60 * 60 * 24);
        lastName.setMaxAge(60 * 60 * 24);

        // Thêm cookie vào response
        response.addCookie(firstName);
        response.addCookie(lastName);

        // Xuất ra trình duyệt
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<b>First Name</b>: " + firstName.getValue() + " - <b>Last Name</b>: " + lastName.getValue());
        out.close();
    }
}
