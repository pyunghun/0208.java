package com.greedy.section01.statement;

import static com.greedy.common.JDBCTemplate.close;
import static com.greedy.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.greedy.model.dto.DepartmentDTO;
import com.greedy.model.dto.EmployeeDTO;

public class Application6 {

	public static void main(String[] args) {

		Connection con = getConnection();
		
		Statement stmt = null;
		ResultSet rset = null;
		
		List<DepartmentDTO> depList = null;
		
		String query = "SELECT * FROM DEPARTMENT";
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			depList = new ArrayList<>();
			
			while(rset.next()) {
				
				DepartmentDTO row = new DepartmentDTO();
				row.setDeptId(rset.getString("DEPT_ID"));
				row.setDeptTitle(rset.getString("DEPT_TITLE"));
				row.setLocationId(rset.getString("LOCATION_ID"));
				
				depList.add(row);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(con);
			close(stmt);
			
		} 
		for(DepartmentDTO dep : depList) {
			System.out.println(dep);
		}

		
	
	}	
}