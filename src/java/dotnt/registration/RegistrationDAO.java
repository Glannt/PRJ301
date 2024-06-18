/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dotnt.registration;

import dotnt.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author PC
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws SQLException, NamingException {
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1.connect database
            con = DBHelper.getConnection();
            //2. Create SQL String
            if (con != null) {
                String sql = "Select username "
                        + "From Registration "
                        + "Where username = ? "
                        + "And password = ?";
                //3. Create statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process result
                if (rs.next()) {
                    result = true;
                }//end username and password are verified
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return this.accounts;
    }

    public void searchLastname(String searchValue)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1.connect database
            con = DBHelper.getConnection();
            //2. Create SQL String
            if (con != null) {
                String sql = "Select username, password, lastName, isAdmin "
                        + "From Registration "
                        + "Where lastName Like ?";
                //3. Create statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process result
               while(rs.next()){
                   //.map
                   //get data from result set
                   String username = rs.getString("username");
                   String password = rs.getString("password");
                   String fullName = rs.getString("lastName");
                   boolean role = rs.getBoolean("isAdmin");
                   //set data to DTO properties
                   RegistrationDTO dto = 
                           new RegistrationDTO(username, password, fullName, role);
                   if(this.accounts == null){
                       this.accounts = new ArrayList<>();
                   }//account is unavailable
                   this.accounts.add(dto);
               }//traverse all result set
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deleteAccount(String username)
            throws SQLException, NamingException{
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1.connect database
            con = DBHelper.getConnection();
            //2. Create SQL String
            if (con != null) {
                String sql = "Delete From Registration "
                        + "Where username = ?";
                //3. Create statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4. Execute Query
                int affectedRows = stm.executeUpdate();
                //5. Process result
                if(affectedRows > 0){
                    result = true;
                }
                //end username and password are verified
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
     public boolean updateAccount(String username, String password, boolean isAdmin)
            throws SQLException, NamingException{
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1.connect database
            con = DBHelper.getConnection();
            //2. Create SQL String
            if (con != null) {
                String sql = "Update Registration "
                        + "Set password = ?, "
                        + "isAdmin = ? "
                        + "Where username = ?";
                //3. Create statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, isAdmin);
                stm.setString(3, username);
                //4. Execute Query
                int affectedRows = stm.executeUpdate();
                //5. Process result
                if(affectedRows > 0){
                    result = true;
                }
                //end username and password are verified
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}