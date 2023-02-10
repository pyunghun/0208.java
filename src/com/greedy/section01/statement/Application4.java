package com.greedy.section01.statement;

import static com.greedy.common.JDBCTemplate.getConnection;
import static com.greedy.common.JDBCTemplate.close;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.greedy.model.dto.DepartmentDTO;

public class Application4 {

	public static void main(String[] args) {

		Connection con = getConnection();
		
		Statement stmt = null;
		ResultSet rset = null;
		
		DepartmentDTO selectedDep = null;
		Scanner sc = new Scanner(System.in);
		System.out.print("조회하실 부서코드를 입력해주세요 : ");
		String deptId = sc.nextLine();
		
		String query = "SELECT * FROM DEPARTMENT WHERE DEPT_ID = '" + deptId + "'";
		
		
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				selectedDep = new DepartmentDTO();
				
				selectedDep.setDeptId(rset.getString("DEPT_ID"));
				selectedDep.setDeptTitle(rset.getString("DEPT_TITLE"));
				selectedDep.setLocationId(rset.getString("LOCATION_ID"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
			close(con);
		}
		System.out.println(selectedDep);
	}

}
