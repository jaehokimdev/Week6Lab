package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author joekim
 */
public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("loginUser") == null) {
           getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        } else {
           getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        HttpSession session = request.getSession();

        if (username == null || username.equals("")) {
           request.setAttribute("message", "Please enter username");
           getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
           return;
        } else { 
           session.setAttribute("loginUser", username);
           getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
           return;
        }
    }
}