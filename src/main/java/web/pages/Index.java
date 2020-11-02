package web.pages;

import web.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("")
public class Index extends BaseServlet {
    
    /**
     * Renders the index.jsp page
     * @see BaseServlet
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        
        try {
            render("WebApp Template - Start", "/WEB-INF/index.jsp", req, resp);
        } catch (Exception e){
            resp.sendError(400, e.getMessage());
        }
    }
}