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

        HttpSession s = req.getSession(false);
        if (s == null) { resp.sendRedirect(req.getContextPath() + "/createsession"); return; }

        String ten = (String) s.getAttribute("ten");
        Object tuoiObj = s.getAttribute("tuoi");
        Integer tuoi = (tuoiObj instanceof Integer) ? (Integer)tuoiObj
                                                    : Integer.valueOf(String.valueOf(tuoiObj));

        if (ten == null || tuoi == null) {
          resp.sendRedirect(req.getContextPath() + "/createsession");
          return;
        }

        out.println("<h2>Xin chào bạn: " + ten + " - Tuổi: " + tuoi + "</h2>");
        out.println("<p>Session ID: " + s.getId() + "</p>");

    }
}
