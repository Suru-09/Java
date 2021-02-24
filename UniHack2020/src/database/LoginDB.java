package database;
import java.sql.*;

public class LoginDB {

    public static final String serverIP = "79.118.15.186";

    /**
     * @param username The username entered by the user
     * @param password The pw entered by the user
     * @return It returns a boolean value, true if the LoginData is correct
     * and false otherwise
     */
    public static boolean checkLoginData(String username, String password) {

        String connectionUrl = "jdbc:sqlserver://" + serverIP + ":1433;"
                + "database=LoginInformation;"
                + "user=suru;"
                + "password=1234;"
                + "encrypt= false;"
                + "trustServerCertificate=true;"
                + "loginTimeout=30;";

        try(
                Connection conn = DriverManager.getConnection(connectionUrl);
                Statement stmt = conn.createStatement();
        ){
            /*
             * Requesting all the usernames and passwords from
             * the database so we can check whether the login data
             * received is correct
             */

            String selectStr = "select loginID, pass " +
                    "from [LoginInformation]";

            ResultSet res = stmt.executeQuery(selectStr);

            while(res.next()) {
                String loginID = res.getString("loginID");
                String Password = res.getString("pass");

                if(password.equals(Password) &&
                        username.equals(loginID) )
                    return true;
            }

        }catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}