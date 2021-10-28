package com.revature.dao;

import java.sql.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.main.App;
import com.revature.model.Employee;

public class EmployeeDAO {
	
	private static Logger logger = LoggerFactory.getLogger(EmployeeDAO.class);

	public static Connection conn = null;
	private static Statement stmt = null;
	private static PreparedStatement pStmt = null;
	private static ResultSet rs = null;
	public static Employee employee = new Employee(0, null, null, null, null);
	
	private static EmployeeDAO employeeDao = null;
	
	public static EmployeeDAO instance() {
		if(employeeDao == null) {
			employeeDao = new EmployeeDAO();
		}
		return employeeDao;
		
	}

	public static void connect() throws Exception {
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection("jdbc:postgresql://jinqxsoftware.cyagmya19qqn.us-east-2.rds.amazonaws.com/jinqxsoftware", "postgres",
				"postgres123");
		if(conn!=null) {
			logger.info("Connecting to database");
		}
	}

	public static void closeResource() throws Exception {
		if (rs != null) {
			rs.close();
		}
		if (pStmt != null) {
			pStmt.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		if (conn != null) {
			conn.close();
		}
		logger.info("Closing connection");
	}
	
	public static Employee getAllEmployees(String email) throws Exception {
		logger.info("Getting Employee");
		Employee employee =new Employee(0, null, null, null, null);
        
        String getEmployee = "SELECT * FROM employee WHERE email= "+"'"+email+"'";
        //String getEmployee = "SELECT * FROM employee WHERE email="+email;
        
        
        		
        pStmt = conn.prepareStatement(getEmployee);
        //pStmt.setString(1,email);
        rs= pStmt.executeQuery();

        while (rs.next()) {
        	employee.setId(rs.getInt("id"));
        	employee.setName(rs.getString("name"));
        	employee.setEmployeeType(rs.getString("employee_type"));
        	employee.setEmail(rs.getString("email"));
        	employee.setPassword(rs.getString("password"));    
        } 
        return employee;
    }

}
