package dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class DaoInstance {
    private static DaoInstance instance = null;

    synchronized public static DaoInstance getInstance() throws Exception {
        if (instance == null) {
            instance = new DaoInstance();
        }
        return instance;
    }

    private DaoInstance()  {
    }

    public static Connection getConnection() throws Exception {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
        return ds.getConnection();
    }
}
