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
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.tbl_transaction.tbl_transactionDAO;

/**
 *
 * @author tmax3
 */
@WebServlet(name = "PrintServlet", urlPatterns = {"/PrintServlet"})
public class PrintServlet extends HttpServlet {

    private final String PRINT_PAGE = "print.jsp";

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
        String fromDate = request.getParameter("txtFromDate");
        String toDate = request.getParameter("txtToDate");

        String url = PRINT_PAGE;
        try {
            /* TODO output your page here. You may use following sample code. */
            //Nếu toDate ko có thì lấy ngày hôm nay
            if (toDate.isEmpty()) {
                Calendar c = Calendar.getInstance();
                Date d = c.getTime();
                //Định dạng ngày 
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                toDate = sdf.format(d);
            }
            //KT xem session còn hay ko
            HttpSession session = request.getSession(false);
            if (session != null) {
                //KT xem accountID còn hay ko
                String accountID = (String) session.getAttribute("USER");
                if (accountID != null) {
                    //Nếu fromDate ko có thì ko có kết quả vì ko biết bắt đầu lúc nào
                    if (!fromDate.isEmpty()) {
                        //cộng thêm 23:59:59 để lấy các transaction đến cuối toDate, nếu ko cộng thì chỉ lấy tới 00:00:00 cùa toDate
                        toDate += " 23:59:59";
                        tbl_transactionDAO dao = new tbl_transactionDAO();
                        dao.searchTransaction(accountID, fromDate, toDate);
                        request.setAttribute("SEARCHRESULT", dao.getListTransaction());
                    }
                }
            }

        } catch (NamingException ex) {
            log("PrintServlet _ Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("PrintServlet _ SQL: " + ex.getMessage());
        } finally {
            //Dùng dispatcher để giữ lại attribute SEARCHRESULT trong request scope
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
