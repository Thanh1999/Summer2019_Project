/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_transaction;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.utils.DBUtils;

/**
 *
 * @author tmax3
 */
public class tbl_transactionDAO implements Serializable {

    public List<tbl_transactionDTO> listTransaction;

    public List<tbl_transactionDTO> getListTransaction() {
        return listTransaction;
    }

    public void searchTransaction(String accountID, String fromDate, String toDate) throws NamingException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            if (con != null) {
                String sql = "select transID, transDate, type, amount, reason, status from tbl_transaction where transDate between ? and ? and accountID = ?";
                st = con.prepareStatement(sql);
                st.setString(1, fromDate);
                st.setString(2, toDate);
                st.setString(3, accountID);
                rs = st.executeQuery();
                while (rs.next()) {
                    int transID = rs.getInt("transID");
                    String transDate = rs.getString("transDate");
                    int type = rs.getInt("type");
                    float amount = rs.getFloat("amount");
                    String reason = rs.getString("reason");
                    boolean isHided = rs.getBoolean("status");
                    tbl_transactionDTO dto = new tbl_transactionDTO(transID, transDate, type, amount, reason, isHided);
                    //Nếu trạng thái là show thì mới add vào listTransaction
                    if (dto.isStatus()) {
                        if (listTransaction == null) {
                            listTransaction = new ArrayList<>();
                        }
                        listTransaction.add(dto);
                    }
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean insertTransaction(String transDate, int type, float amount, String reason, String accountID, boolean status) throws NamingException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement st = null;

        try {
            if (con != null) {
                String sql = "insert into tbl_transaction(transDate, type, amount, reason, accountID, status) values(?,?,?,?,?,?)";
                st = con.prepareStatement(sql);
                st.setString(1, transDate);
                st.setInt(2, type);
                st.setFloat(3, amount);
                st.setString(4, reason);
                st.setString(5, accountID);
                st.setBoolean(6, status);
                int result = st.executeUpdate();
                if (result > 0) {
                    return true;
                }

            }
        } finally {

            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateTransaction(int transID, boolean status) throws NamingException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement st = null;

        try {
            if (con != null) {
                String sql = "update tbl_transaction set status = ? where transID = ?";
                st = con.prepareStatement(sql);
                st.setInt(2, transID);
                st.setBoolean(1, status);
                int result = st.executeUpdate();
                if (result > 0) {
                    return true;
                }

            }
        } finally {

            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public boolean updateAllTransaction(String userID, boolean status) throws NamingException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement st = null;

        try {
            if (con != null) {
                String sql = "update tbl_transaction set status = ? where accountID = ?";
                st = con.prepareStatement(sql);
                st.setString(2, userID);
                st.setBoolean(1, status);
                int result = st.executeUpdate();
                if (result > 0) {
                    return true;
                }

            }
        } finally {

            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

}
