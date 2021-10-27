package com.revature.controller;



import com.google.gson.Gson;
import com.revature.dao.EmployeeDAO;
import com.revature.model.Employee;

import io.javalin.http.Handler;

public class EmployeeController {
	private static Gson gson = new Gson();
	
	public static Handler getEmployee = ctx -> {
		String email = ctx.pathParam("email");
		EmployeeDAO.connect();
		//EmployeeDAO dao = EmployeeDAO.instance();
		Employee allEmployees = EmployeeDAO.getAllEmployees(email);
		ctx.json(allEmployees);
		EmployeeDAO.closeResource();
	};

}
