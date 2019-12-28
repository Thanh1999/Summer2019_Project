/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.tbl_account.tbl_accountDAO;
import sample.tbl_transaction.tbl_transactionDAO;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import sample.tbl_account.tbl_accountDTO;
import sample.tbl_transaction.tbl_transactionCreateError;

/**
 *
 * @author tmax3
 */
@WebServlet(name = "TransferServlet", urlPatterns = {"/TransferServlet"})
public class TransferServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.jsp";
    private final String TRANSFER_PAGE = "transfer.jsp";

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
        String accountID = request.getParameter("txtAccount");
        String url = ERROR_PAGE;
        boolean foundErr = false;

        tbl_transactionCreateError error = new tbl_transactionCreateError();

        try {
            /* TODO output your page here. You may use following sample code. */
            //KT xem session còn hay ko
            HttpSession session = request.getSession(false);
            if (session != null) {
                //KT xem account và balance còn hay ko
                String userID = (String) session.getAttribute("USER");
                float balance = (float) session.getAttribute("BALANCE");
                if (userID != null && balance != 0) {

                    tbl_accountDAO accDao = new tbl_accountDAO();
                    tbl_accountDTO dto = accDao.searchAccount(accountID);
                    //Ko tìm thấy người nhận
                    if (dto == null) {
                        foundErr = true;
                        error.setAccountNotExisted(" The account isn't existed!");
                    } //Người nhận hết hạn
                    else if (dto.isIsExpired()) {
                        foundErr = true;
                        error.setAccountIsExpired(accountID + " is expired!");
                    }
                    //Người chuyển và người nhận cùng ID
                    if (userID.equals(accountID)) {
                        foundErr = true;
                        error.setAccountsAreTheSame(accountID + " and " + userID + " are the same!");
                    }

                    float amount = Float.parseFloat(request.getParameter("txtAmount")) * 1000;

                    float remainder = balance - amount;
                    //amount lớn hơn balance
                    if (remainder < 0) {
                        foundErr = true;
                        error.setInvalidAmount("Amount is bigger than your balance!");
                    }
                    //Nếu có lỗi thì đặt vào request scope
                    if (foundErr) {
                        request.setAttribute("ERROR", error);

                    } else {

                        Calendar c = Calendar.getInstance();
                        Date d = c.getTime();
                        //Định dạng ngày
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String transDate = sdf.format(d);
                        //Ghi lý do
                        String reason = accountID + " needs money";
                        //Update lại balance của tài khoản người nhận và người chuyển và balance hiển thị
                        accDao.updateAccount(userID, remainder);
                        accDao.updateAccount(accountID, dto.getBalance() + amount);
                        session.setAttribute("BALANCE", remainder);
                        //Thêm transaction vào database
                        tbl_transactionDAO transDao = new tbl_transactionDAO();
                        boolean result = transDao.insertTransaction(transDate, 2, amount, reason, userID, true);
                        if (result) {
                            transDao.insertTransaction(transDate, 2, amount, userID + " transfered " + amount + " to you", accountID, true);
                            url = TRANSFER_PAGE;
                        }
                    }
                }
            } //Nếu session hết hạn
            else {
                error.setSessionTimeout("Your session has been timed out!");
                request.setAttribute("ERROR", error);
            }

        } catch (NamingException ex) {
            log("TransferServlet _ Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("TransferServlet _ SQL: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            //Người dùng nhập chữ vào amount
            log("TransferServlet _ NumberFormat: " + ex.getMessage());
            error.setInvalidAmount("Amount must be number!");
            request.setAttribute("ERROR", error);
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
