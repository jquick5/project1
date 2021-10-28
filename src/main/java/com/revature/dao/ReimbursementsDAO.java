package com.revature.dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import com.revature.model.Reimbursements;



public class ReimbursementsDAO {
	
	private static Logger logger = LoggerFactory.getLogger(ReimbursementsDAO.class);

	
	public static Connection conn = null;
	private static Statement stmt = null;
	private static PreparedStatement pStmt = null;
	private static ResultSet rs = null;
	
private static ReimbursementsDAO reimbursementDao = null;
	
	public static ReimbursementsDAO instance() {
		if(reimbursementDao == null) {
			reimbursementDao = new ReimbursementsDAO();
		}
		return reimbursementDao;	
	}
	

	public static void connect() throws Exception {
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection("jdbc:postgresql://jinqxsoftware.cyagmya19qqn.us-east-2.rds.amazonaws.com/jinqxsoftware", "postgres",
				"postgres123");
		if(conn!=null) {
			logger.info("Connected to database");
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
		logger.info("Connection Closed");
	}
	
	public static Reimbursements addReimbursement(String body) throws Exception{
		logger.info("Adding new Reimbursement");
		Gson g = new Gson();
		Reimbursements r = g.fromJson(body, Reimbursements.class);
		//Reimbursements reimbursement = new Reimbursements(0, 0, null, 0, null, null);
		String rStmt = "INSERT INTO public.reimbursements( amount, status, employee_id, purchase_type, description, employee_name) VALUES ( ?, ?, ?, ?, ?, ?)";
		pStmt = conn.prepareStatement(rStmt);
		//pStmt.setInt(1, r.getId());
		pStmt.setDouble(1, r.getAmount());
		pStmt.setString(2, r.getStatus());
		pStmt.setInt(3, r.getEmployeeId());
		pStmt.setString(4, r.getPurchaseType());
		pStmt.setString(5, r.getDescription());
		pStmt.setString(6, r.getEmpName());
		
		pStmt.executeUpdate();
		
		return r;
	}


	public List<Reimbursements> getEmpHistory() throws Exception{
		logger.info("Getting Reimbursement History");
		List<Reimbursements> reimbursements = new ArrayList<Reimbursements>();
        
        String getHistory = "SELECT * FROM public.reimbursements";
        		
        pStmt = conn.prepareStatement(getHistory);
        rs= pStmt.executeQuery();

        while (rs.next()) {
        	Reimbursements r = new Reimbursements();
        	r.setId(rs.getInt("id"));
        	r.setAmount(rs.getDouble("amount"));
        	r.setStatus(rs.getString("status"));
        	r.setEmployeeId(rs.getInt("employee_id"));
        	r.setPurchaseType(rs.getString("purchase_type"));
        	r.setDescription(rs.getString("description"));
        	r.setEmpName(rs.getString("employee_name"));
        	reimbursements.add(r);
        } 
        return reimbursements;
		
	}


	public int approve(String id) throws Exception{
		logger.info("Approving Request");
		int status = 0;
		String approveQuery = "UPDATE public.reimbursements SET status='approved' WHERE id="+id;
		pStmt = conn.prepareStatement(approveQuery);
	    status =pStmt.executeUpdate();
		return status;
	}
	
	public int deny(String id) throws Exception{
		logger.info("Denying Request");
		int status = 0;
		String denyQuery = "UPDATE public.reimbursements SET status='denied' WHERE id="+id;
		pStmt = conn.prepareStatement(denyQuery);
	    status =pStmt.executeUpdate();
		return status;
	}

}
