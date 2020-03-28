package com.example.hrbusteschool.Class;

import java.sql.ResultSet;
import java.sql.SQLException;



public class Service {

    public Boolean login(String username, String password) {

        // 获取Sql查询语句
        String logSql = "select * from userinfo where username ='" + username
                + "' and password ='" + password + "'";

        // 获取DB对象
        DBManager sql = DBManager.createInstance();
        sql.connectDB();

        // 操作DB对象
        try {
            ResultSet rs = sql.executeQuery(logSql);
            if (rs.next()) {
                sql.closeDB();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql.closeDB();
        return false;
    }

    public Boolean register(String username, String password, String tel, String sex) {

        // 获取Sql查询语句
        //String regSql = "insert into userinfo values('"+ username+ "','"+ password+ "') ";
        String regSql = "insert into userinfo (username,password,tel,sex,identity) values('" + username + "','" + password + "','" + tel + "','" + sex + "','" + "成员')";
        // 获取DB对象
        DBManager sql = DBManager.createInstance();
        sql.connectDB();

        int ret = sql.executeUpdate(regSql);
        if (ret != 0) {
            sql.closeDB();
            return true;
        }
        sql.closeDB();

        return false;
    }
    public int QueryPostItem() {

        // 获取Sql查询语句
        /*String logSql = "select * from userinfo where username ='" + username
                + "' and password ='" + password + "'";*/
        String querysqlString = "select count(*) from post";
        //String querysqlString = "select count(*) from userinfo";
        // 获取DB对象
        DBManager sql = DBManager.createInstance();
        sql.connectDB();

        // 操作DB对象
        try {
            ResultSet rs = sql.executeQuery(querysqlString);
            if (rs.next()) {
                sql.closeDB();
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql.closeDB();
        return 0;
    }
}