package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String action = request.getParameter("action");
        String itemname = request.getParameter("itemname");
        String[] item = request.getParameterValues("item");
        ArrayList<String> items = (ArrayList<String>) session.getAttribute("items");
        
        switch (action) {
            case "register": {
                if (username == null || username.equals("")) {
                    request.setAttribute("message", "Please enter username");
                    doGet(request, response);
                    break;
                } else {
                    session.setAttribute("loginUser", username);
                    doGet(request, response);
                    break;
                }   
            }
            case "add": {
                if (items == null){
                    items = new ArrayList<String>();
                }
                items.add(itemname);
                session.setAttribute("items", items);
                doGet(request, response);
                break;
            }
            case "delete": {
                items.remove(String.valueOf(item[0]));
                session.setAttribute("items", items);
                doGet(request, response);
                break;
            }
        }

        
    }
}