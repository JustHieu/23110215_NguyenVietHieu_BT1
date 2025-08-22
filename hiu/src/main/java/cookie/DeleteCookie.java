package cookie;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/deletecookie"})
public class DeleteCookie extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            out.println("<h2>Không tìm thấy cookie nào</h2>");
            out.close();
            return;
        }

        // Path phải trùng với lúc tạo cookie
        String ctx = request.getContextPath();
        String path = (ctx == null || ctx.isEmpty()) ? "/" : ctx;

        out.println("<h2>Cookies Name and Value</h2>");
        for (Cookie c : cookies) {
            if ("ten".equals(c.getName())) {
                c.setPath(path);   // rất quan trọng để xoá được
                c.setMaxAge(0);    // đánh dấu xoá
                response.addCookie(c);
                out.print("Deleted cookie : " + c.getName() + "<br/>");
            }
            out.print("Name : " + c.getName() + ",  Value: " + c.getValue() + " <br/>");
        }
        out.close();
    }
}
