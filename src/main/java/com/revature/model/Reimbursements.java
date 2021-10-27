package com.revature.model;

public class Reimbursements {
	private int id;
	private double amount;
	private String purchaseType;
	private int employeeId;
	private String status;
	private String description;
	
	

	

	public Reimbursements(int id, double amount, String purchaseType, int employeeId, String status,
			String description) {
		super();
		this.id = id;
		this.amount = amount;
		this.purchaseType = purchaseType;
		this.employeeId = employeeId;
		this.status = status;
		this.description = description;
	}
	
	public Reimbursements() {
		super();
	}
	
	

	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "Reimbursements [id=" + id + ", amount=" + amount + ", purchaseType=" + purchaseType + ", employeeId="
				+ employeeId + ", status=" + status + ", description=" + description + "]";
	}

	


	
	
	

}
