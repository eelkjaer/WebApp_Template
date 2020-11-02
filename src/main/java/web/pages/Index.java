package web.pages;

import domain.items.InvalidItem;
import domain.items.Item;
import domain.items.ItemNotFound;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("")
public class Index extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("items", api.findAllItems());
            render("WebApp Template - Start", "/WEB-INF/webpages/index.jsp", req, resp);
        } catch (ServletException | IOException | ItemNotFound e){
            resp.sendError(400, e.getMessage());
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            addNewItem(req,resp);
        } catch (InvalidItem invalidItem) {
            invalidItem.printStackTrace();
            resp.sendError(400, invalidItem.getMessage());
        }
    }
    
    private void addNewItem(HttpServletRequest req, HttpServletResponse resp) throws InvalidItem, IOException {
        try {
            String itemName = req.getParameter("inputName");
            api.createItem(itemName);
            resp.sendRedirect(req.getContextPath());
        } catch (InvalidItem e){
            resp.sendError(400, e.getMessage());
        }
    }
}