package com.revature.controller;

import java.util.List;



import com.revature.dao.ReimbursementsDAO;

import com.revature.model.Reimbursements;


import io.javalin.http.Handler;

public class ReimbursementsController {

	public static Handler addReimbursement = ctx -> {
	String body = ctx.body();
	ReimbursementsDAO.connect();
	ReimbursementsDAO dao = ReimbursementsDAO.instance();
	Reimbursements addReimbursement = dao.addReimbursement(body);
	ReimbursementsDAO.closeResource();
	};

	public static Handler getHistory = ctx -> {
		ReimbursementsDAO.connect();
		ReimbursementsDAO dao = ReimbursementsDAO.instance();
		List<Reimbursements> employeeReimbursements = dao.getEmpHistory();
		ctx.json(employeeReimbursements);
		ReimbursementsDAO.closeResource();
	};
	
	public static Handler approveReq = ctx -> {
		String id = ctx.pathParam("id");
		ReimbursementsDAO.connect();
		ReimbursementsDAO dao = ReimbursementsDAO.instance();
		int approveReq = dao.approve(id);
		ReimbursementsDAO.closeResource();
	};
	
	public static Handler denyReq = ctx -> {
		String id = ctx.pathParam("id");
		ReimbursementsDAO.connect();
		ReimbursementsDAO dao = ReimbursementsDAO.instance();
		int denyReq = dao.deny(id);
		ReimbursementsDAO.closeResource();
	};
}
