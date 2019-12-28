/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_account;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import sample.utils.DBUtils;

/**
 *
 * @author tmax3
 */
public class tbl_accountDAO implements Serializable {

    public String checkLogin(String username, String pin) throws NamingException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            if (con != null) {
                String sql = "select accountID, isExpired, custName from tbl_account A, tbl_customerInfo C where accountID = ? and pin = ? and A.accountID = C.custID";
                st = con.prepareStatement(sql);
                st.setString(1, username);
                st.setString(2, pin);
                rs = st.executeQuery();
                if (rs.next()) {
                    //Nếu tài khoản hết hạn cũng là không hợp lệ
                    boolean expired = rs.getBoolean("isExpired");
                    if (!expired) {
                        return rs.getString("custName");
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
        return null;
    }

    public tbl_accountDTO searchAccount(String accountID) throws NamingException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            if (con != null) {
                String sql = "select accountID, pin, balance, benefit, isExpired from tbl_account where accountID = ?";
                st = con.prepareStatement(sql);
                st.setString(1, accountID);
                rs = st.executeQuery();
                if (rs.next()) {
                    String pin = rs.getString("pin");
                    float balance = rs.getFloat("balance");
                    float benefit = rs.getFloat("benefit");
                    boolean isExpired = rs.getBoolean("isExpired");
                    tbl_accountDTO dto = new tbl_accountDTO(accountID, pin, balance, benefit, isExpired);
                    return dto;
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
        return null;
    }

    public boolean updateAccount(String accountID, float remainder) throws NamingException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement st = null;

        try {
            if (con != null) {
                String sql = "update tbl_account set balance = ? where accountID = ?";
                st = con.prepareStatement(sql);
                st.setFloat(1, remainder);
                st.setString(2, accountID);
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
