package com.getreankeautomat;

import java.sql.*;

class DataRetrieve {
    DataRetrieve() {
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:sqlite:./getraenke.db";
            Connection con = DriverManager.getConnection(url);
            Statement st = con.createStatement();
            rs = st.executeQuery("select * from getraenke");

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet getResultSet() {
        return rs;
    }
    private ResultSet rs = null;

}
