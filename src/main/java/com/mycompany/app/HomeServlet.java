package com.mycompany.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.domain.Item;

/*
 * In past versions of JEE, you had to declare servlet mappings in the web.xml file.  With the
 * WebServlet annotation, you can declare it directly on the target servlet.  This notation
 * indicates that GET and POST requests to path "/" off the root context are served by this
 * HttpServlet.
 * 
 * When deployed to Tomcat, and without further configuration, the root context for this application
 * will be "/servlet-demo", meaning this HttpServlet handles GET and POST HTTP requests to
 * "/servlet-demo/".
 */
@WebServlet("/")
public class HomeServlet extends HttpServlet {

    // Simple demo, storing items to an in-memory List.  A database backend would be a future exercise.
    private List<Item> inventory = new ArrayList<Item>();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
         * Set the "inventory" attribute to the local List of Items.  This makes this object
         * accessible to "home.jsp" when it iterates over the List with:
         *   <c:forEach items="${inventory}" var="item"> ... </c:forEach>
         */
        req.setAttribute("inventory", inventory);
        /*
         * We enriched the incoming request with an additional attribute, and here forward it
         * on to the home.jsp template to render an HTML response.
         */
        req.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
         * Read the "itemName" request parameter off the request and add a new Item to the inventory.
         * "itemName" corresponds to the HTML Form input:
         *   <input type="text" name="itemName">
         */
        Item item = new Item();
        item.setName(req.getParameter("itemName"));
        this.inventory.add(item);

        /*
         * Same routine as in doGet().  Set the "inventory" attribute (now with an addition
         * Item in the List), and forward the request to the JSP to render an HTML response.
         */
        req.setAttribute("inventory", inventory);
        req.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
    }

}
