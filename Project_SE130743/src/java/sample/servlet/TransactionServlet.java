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
import sample.tbl_account.tbl_accountDTO;

/**
 *
 * @author tmax3
 */
@WebServlet(name = "TransactionServlet", urlPatterns = {"/TransactionServlet"})
public class TransactionServlet extends HttpServlet {

    private final String TRANSFER_PAGE = "transfer.jsp";
    private final String PRINT_TRANSACTION_PAGE = "print.jsp";
    private final String LOGIN_PAGE = "login.html";

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
        String selection = request.getParameter("rdType");
        String url = LOGIN_PAGE;
        try {
            //KT xem session có hay ko
            HttpSession session = request.getSession(false);
            if (session != null) {
                //KT tài khoản có hay ko, ko thì về lại LOGIN_PAGE
                String accountID = (String) session.getAttribute("USER");
                if (accountID != null) {
                    tbl_accountDAO dao = new tbl_accountDAO();
                    tbl_accountDTO dto = dao.searchAccount(accountID);
                    //Lấy balance dựa vào ID đưa vào session để hiển thị ở các trang print.jsp, transfer.jsp
                    session.setAttribute("BALANCE", dto.getBalance());
                    //Lấy lựa chọn của người dùng
                    if (selection.equals("Transfer")) {
                        url = TRANSFER_PAGE;
                    }
                    else{
                        url = PRINT_TRANSACTION_PAGE;
                    }
                }
            }

        } catch (NamingException ex) {
            log("TransactionServlet _ Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("TransactionServlet _ SQL: " + ex.getMessage());
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
