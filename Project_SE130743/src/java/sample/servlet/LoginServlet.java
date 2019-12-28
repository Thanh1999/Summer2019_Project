/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.tbl_account.tbl_accountDAO;

/**
 *
 * @author tmax3
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final String INVALID_PAGE = "invalid.html";
    private final String TRANSACTION_PAGE = "transaction.jsp";
    private final String LOG_PAGE = "login.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("txtUsername");
        String pin = request.getParameter("txtPin");
        String url = INVALID_PAGE;
        HttpSession session = request.getSession();
        try {
            /* TODO output your page here. You may use following sample code. */
            //KT username, password
            tbl_accountDAO dao = new tbl_accountDAO();
            String custName = dao.checkLogin(username, pin);
            if (custName != null) {
                url = TRANSACTION_PAGE;
                session.setAttribute("USER", username);
                session.setAttribute("NAME", custName);
            } else {
                //KT số lần sai và tăng số lần sai lên
                if (session.getAttribute("LOGINFAIL") != null) {
                    int loginCount = (int) session.getAttribute("LOGINFAIL");
                    session.setAttribute("LOGINFAIL", ++loginCount);
                    //Nếu sai từ 3 lần trở đi thì khoá account
                    if (loginCount >= 3) {
                        url = LOG_PAGE;
                        //Lưu lại thời điểm login sai lần thứ 3 và + 5 phút để sau khoảng thời gian đó sẽ mở trở lại
                        //Việc mở trở lại được thực hiện trong FilterDispatcher
                        session.setAttribute("UNLOCK", session.getLastAccessedTime() + 60 * 5 * 1000);
                    }
                } //Nếu là lần đầu thì set = 1
                else {
                    session.setAttribute("LOGINFAIL", 1);
                }
            }

        } catch (NamingException ex) {
            log("LoginServlet _ Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("LoginServlet _ SQL: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
