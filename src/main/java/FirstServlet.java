import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "FirstServlet", urlPatterns = "/test")
public class FirstServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        final PrintWriter writer = resp.getWriter();
        writer.printf("<html><body><h3><table><tr><th>ID</th><th>Title</th><th>cost</th></tr>");
        for (int i = 0; i < 10; i++) {
            Product product = new Product(i, "title" + i, 10 + i);
            writer.printf("<tr><td>" + product.getId() + "</td><td>" + product.getTitle() + "</td><td>" + product.getCost() + "</td></tr>");
        }
        writer.printf("</table></h3></body></html>");
        writer.close();
    }
}
