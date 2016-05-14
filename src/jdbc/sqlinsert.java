package jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;

import bean.szzf;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class sqlinsert {
	public static Connection getConn() {
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/szzf??useUnicode=true&characterEncoding=utf-8";
	    String username = "root";
	    String password = "";
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,加载对应驱动
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
	
	public int insert(szzf zfinfo) {
	    Connection conn = getConn();  
	    String sql = "insert into shenzhenzufang (area,region,community,room,hall,meter,price,priceproom,pricepmeter) values(?,?,?,?,?,?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, zfinfo.getArea());
	        pstmt.setString(2, zfinfo.getRegion());
	        pstmt.setString(3, zfinfo.getCommunity());
	        pstmt.setInt(4, zfinfo.getRoom());
	        pstmt.setInt(5, zfinfo.getHall());
	        pstmt.setInt(6, zfinfo.getMeter());
	        pstmt.setInt(7, zfinfo.getPrice());
	        pstmt.setInt(8, zfinfo.getPricePRoom());
	        pstmt.setInt(9, zfinfo.getPricePMeter());
	        pstmt.addBatch();
	        pstmt.executeBatch();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0;
	}
}
