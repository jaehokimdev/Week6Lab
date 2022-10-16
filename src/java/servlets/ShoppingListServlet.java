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
        String action = request.getParameter("action");
        
        try{
            if (session.getAttribute("loginUser") != null && !action.equals("logout")) {
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            } else if (action != null && action.equals("logout")) {
                session.invalidate();
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            }
        }catch(NullPointerException e){
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
        ArrayList<String> items = (ArrayList<String>) session.getAttribute("items");
        
        switch (action) {
            case "register": {
                if (username == null || username.equals("")) {
                    request.setAttribute("message", "Please enter username");
                    getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                    break;
                } else {
                    session.setAttribute("loginUser", username);
                    getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                    break;
                }   
            }
            case "add": {
                if (items == null){
                    items = new ArrayList<String>();
                }
                items.add(itemname);
                session.setAttribute("items", items);
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                break;
            }
            case "delete": {
                String item = request.getParameter("itemlist");
                items.remove(item);
                session.setAttribute("items", items);
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                break;
            }
            case "logout": {
                session.invalidate();
                items.clear();
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                break;
            }
        }

        
    }
}