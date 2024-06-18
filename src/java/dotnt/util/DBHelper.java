/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dotnt.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author PC
 */
public class DBHelper {
    public static Connection getConnection()
    throws /*ClassNotFoundException,*/ NamingException, SQLException{
        //1.get current context
        Context context = new InitialContext();
        Context tomcatContext = (Context)context.lookup("java:comp/env");
        //2. lookup DS
        DataSource ds = (DataSource)tomcatContext.lookup("DataSource");
        //3. open connection
        Connection con = ds.getConnection();
        //4. return con
        return con;
//        //1. Load Driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. create Connection String protocol: //IP:PORT;databaseName=
//        String url = "jdbc:sqlserver://" //protocol
//                +"localhost:1433;" //IP:PORT
//                + "databaseName=PRJBuoi5";
//        //3. get Connection from Driver Manager
//        Connection con = DriverManager.getConnection(url, "sa", "123456");
//        //4.return connection
//        return con;
    }
}
