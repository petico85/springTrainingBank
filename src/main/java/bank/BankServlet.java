package bank;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;


/**
 * Hagyományos servlet írás, csak hogy lássuk mit vált ki a spring
 *
 * */
@WebServlet(urlPatterns = "/bank")
public class BankServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        PrintWriter w = resp.getWriter();
        w.print("<html><body><p>");
        w.print(LocalDateTime.now());
        w.print("</p></body></html>");
    }
}
