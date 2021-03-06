package com.coo.member.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.Properties;
import static com.coo.common.JDBCTemplate.*;

import com.coo.check.model.vo.StockLine;
import com.coo.exception.CooException;
import com.coo.member.encrypt.EncryptWrapper;
import com.coo.member.model.vo.Member;

public class MemberDao {

	private Properties prop;
	
	public MemberDao() {
		prop = new Properties();
		String filePath = MemberDao.class.getResource("/config/member-query.properties").getPath();
		
		try {
			prop.load(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Member selectMember(Member m, Connection con) throws CooException {
		Member result = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMember");

			try {
				 pstmt = con.prepareStatement(sql);
				 pstmt.setString(1, m.getEmpId());
				 pstmt.setString(2, m.getEmpPwd());
				 
				 rset = pstmt.executeQuery();
				 
				 if(rset.next()) {
					 result = new Member();
					 result.setEmpId(rset.getString("EMP_ID"));
					 result.setEmpCode(rset.getInt("EMP_CODE"));
					 result.setEmpPwd(rset.getString("PWD"));
					 result.setEmpName(rset.getString("EMP_NAME"));
					 result.setPersonalId(rset.getString("PERSONAL_ID"));
					 result.setEmail(rset.getString("EMAIL"));
					 result.setGender(rset.getString("GENDER"));
					 result.setAge(rset.getInt("AGE"));
					 result.setSalary(rset.getInt("SALARY"));
					 result.setPhone(rset.getString("PHONE")); 
					 result.setAddress(rset.getString("ADDRESS"));
					 result.setDeptCode(rset.getString("DEPT_CODE"));
					 result.setJobCode(rset.getString("JOB_CODE"));
					 result.setDeptTitle(rset.getString("DEPT_TITLE"));
					 result.setJobName(rset.getString("JOB_NAME")); 
					 result.setEtc(rset.getString("ETC"));
					 result.setManagerYn(rset.getString("MANAGER_YN"));
					 result.setHireDate(rset.getDate("HIRE_DATE"));
					 result.setSubDept(rset.getString("SUB_DEPT"));
					 result.setProfileA(rset.getString("PIC"));
				 }
		}catch(SQLException e) {
			throw new CooException(e.getMessage());
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public int checkEmp(Connection con) throws CooException {
		int result = -1;
		Statement stmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("checkEmp");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(sql);
			
			if(rset.next()) {
				result = rset.getInt(1); 
			}
			
		}catch(SQLException e) {
			throw new CooException("사번 조회 실패");
		}
		return result;
	}

	public int idDupChk(Connection con, String id) throws CooException {
		int result = -1;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("idDupChk");
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
		
		rset = pstmt.executeQuery();
	
		if(rset.next()) {
		  result = rset.getInt(1);
		}
			
		}catch(SQLException e) {
			throw new CooException("조회 실패");
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}
	
public int insertMember(Connection con, Member m) throws CooException {
		
		int result= 0; 
		
		PreparedStatement pstmt = null;
	
		String sql = prop.getProperty("insertMember");
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, m.getEmpId());
			pstmt.setString(2, m.getEmpPwd());
			pstmt.setString(3, m.getEmpName());
			pstmt.setString(4, m.geteName());
			
			pstmt.setString(5, m.getPersonalId());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getGender());
			pstmt.setInt(8, m.getAge());
			pstmt.setString(9, m.getContact());
			pstmt.setString(10, m.getPhone());
			pstmt.setString(11, m.getAddress()); 
			pstmt.setString(12, m.getDeptCode());
			pstmt.setString(13, m.getJobCode());
			pstmt.setString(14, m.getEtc());
			pstmt.setString(15, m.getSubDept());
				
			result = pstmt.executeUpdate();
							   
		}catch(SQLException e) {
			throw new CooException(e.getMessage());
		}finally {
			close(pstmt);
		}
		return result;
	}

public int updateMember(Connection con, Member m) throws CooException {
	
	int result=0;
	PreparedStatement pstmt = null;
	
	try {
		String sql= prop.getProperty("updateMember");
		
		pstmt = con.prepareStatement(sql);

		
		pstmt.setString(1, m.getEmpPwd());
		pstmt.setString(2, m.getEmpName());
		pstmt.setString(3, m.getEmail());
		pstmt.setString(4, m.getPhone());
		pstmt.setString(5, m.getAddress());
		pstmt.setString(6, m.getDeptCode());
		pstmt.setString(7, m.getJobCode());
		pstmt.setString(8, m.getEtc());
		pstmt.setString(9, m.getSubDept());
		pstmt.setString(10, m.getContact());
		pstmt.setString(11, m.getPersonalId());
		pstmt.setString(12, m.geteName());
		pstmt.setInt(13, m.getEmpCode());
		result = pstmt.executeUpdate();
		
	}catch(SQLException e) {
		throw new CooException(e.getMessage());
		
	}finally{
		close(pstmt);
	}
	return result;
}


public int deleteMember(Connection con, String empId) throws CooException {
	int result=0;
	PreparedStatement pstmt = null;
	
	try {
		String sql = prop.getProperty("deleteMember");
		
		pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, empId);
		result = pstmt.executeUpdate();
		
	}catch(SQLException e) {
		throw new CooException(e.getMessage());
	}finally {
		close(pstmt);
	}
	return result;
}

public Member searchMember(String empId, Connection con) throws CooException {
	Member result = null;
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	String sql = prop.getProperty("searchMember");
	
	try {
		 pstmt = con.prepareStatement(sql);
		 pstmt.setString(1, empId);
		 rset = pstmt.executeQuery();
		 
		 if(rset.next()) {
			 result = new Member();
			 result.setEmpId(rset.getString("EMP_ID"));
			 result.setEmpCode(rset.getInt("EMP_CODE"));
			 result.setEmpPwd(rset.getString("PWD"));
			 result.setEmpName(rset.getString("EMP_NAME"));
			 result.setPersonalId(rset.getString("PERSONAL_ID"));
			 result.setEmail(rset.getString("EMAIL"));
			 result.setGender(rset.getString("GENDER"));
			 result.setAge(rset.getInt("AGE"));
			 result.setSalary(rset.getInt("SALARY"));
			 result.setPhone(rset.getString("PHONE"));
			 result.setContact(rset.getString("CONTACT"));
			 result.setAddress(rset.getString("ADDRESS"));
			 result.setDeptCode(rset.getString("DEPT_CODE"));
			 result.setJobCode(rset.getString("JOB_CODE"));
			 result.setDeptTitle(rset.getString("DEPT_TITLE"));
			 result.setJobName(rset.getString("JOB_NAME")); 
			 result.setEtc(rset.getString("ETC"));
			 result.setManagerYn(rset.getString("MANAGER_YN"));
			 result.setHireDate(rset.getDate("HIRE_DATE"));
			 result.setSubDept(rset.getString("SUB_DEPT"));
		 }
	}catch(SQLException e) {
		throw new CooException(e.getMessage());
	}finally {
		close(rset);
		close(pstmt);
	}
	return result;
	}

public int insertStockLine(StockLine d, Connection con) throws CooException {
	int result = 0;
	PreparedStatement pstmt = null;
	String sql = prop.getProperty("insertStockLine");

	try {
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,d.getEmpcode());
		pstmt.setString(2, d.getDeptCode());
		pstmt.setInt(3, d.getSubcode());
		
		result = pstmt.executeUpdate();
		
	}catch(SQLException e) {
		throw new CooException("데이터 생성 실패");
	}finally {
		close(pstmt);
	}
	return result;
}

public int uploadPic(Connection con, Member m) throws CooException {
	int result = 0;
	PreparedStatement pstmt = null;
	String sql = prop.getProperty("uploadPic"); 
	
	try {
		pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, m.getEmpCode());
		pstmt.setString(2, m.getProfileA());
		
		result = pstmt.executeUpdate();
		
	}catch(SQLException e) {
		throw new CooException("사진 업로드 실패");
	}finally {
		close(pstmt);
	}
	
	return result;
}

public boolean checkPic(Member m, Connection con) throws CooException {
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	String sql = prop.getProperty("chkpic");
	boolean result = false;
	
	try {
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, m.getEmpId());
		 rset = pstmt.executeQuery();
		 if(rset.next()) result=true;
		 
	}catch(SQLException e){
		throw new CooException("사진조회 실패");
	}finally {
		close(rset);
		close(pstmt);
	}
	return result;
}

public Member selectMemberWPic(Member m, Connection con) throws CooException {
	Member result = null;
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	String sql = prop.getProperty("selectMemberWPic");

		try {
			 pstmt = con.prepareStatement(sql);
			 pstmt.setString(1, m.getEmpId());
			 pstmt.setString(2, m.getEmpPwd());
			 
			 rset = pstmt.executeQuery();
			 
			 if(rset.next()) {
				 result = new Member();
				 result.setEmpId(rset.getString("EMP_ID"));
				 result.setEmpCode(rset.getInt("EMP_CODE"));
				 result.setEmpPwd(rset.getString("PWD"));
				 result.setEmpName(rset.getString("EMP_NAME"));
				 result.setPersonalId(rset.getString("PERSONAL_ID"));
				 result.setEmail(rset.getString("EMAIL"));
				 result.setGender(rset.getString("GENDER"));
				 result.setAge(rset.getInt("AGE"));
				 result.setSalary(rset.getInt("SALARY"));
				 result.setPhone(rset.getString("PHONE")); 
				 result.setAddress(rset.getString("ADDRESS"));
				 result.setDeptCode(rset.getString("DEPT_CODE"));
				 result.setJobCode(rset.getString("JOB_CODE"));
				 result.setDeptTitle(rset.getString("DEPT_TITLE"));
				 result.setJobName(rset.getString("JOB_NAME")); 
				 result.setEtc(rset.getString("ETC"));
				 result.setManagerYn(rset.getString("MANAGER_YN"));
				 result.setHireDate(rset.getDate("HIRE_DATE"));
				 result.setSubDept(rset.getString("SUB_DEPT"));
			 }
	}catch(SQLException e) {
		throw new CooException(e.getMessage());
	}finally {
		close(rset);
		close(pstmt);
	}
	return result;
}

public int updateSalary(int salary, String empId, Connection con) throws CooException {
	int result = 0;
	String sql = prop.getProperty("updateSalary");
	PreparedStatement pstmt = null;
	
	try {
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, salary);
		pstmt.setString(2, empId);
		
		result = pstmt.executeUpdate();
		
	}catch(SQLException e) {
		throw new CooException("연봉 수정 실패");
	}finally {
		close(pstmt);
	}
	return result;
}

public int MemberPwdChange(Connection con, String userId, String userPwd, String changePwd1) throws CooException {
	  int result = 0;
	   String sql = prop.getProperty("MemberPwdChnage");
	   PreparedStatement pstmt = null;
	   String changePwd2 = "";
	    try {
	      pstmt = con.prepareStatement(sql);
	      
	      // 변경된 비밀번호의 암호화 처리 위해서 암호화 코드
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-512");
				byte[] bytes = changePwd1.getBytes(Charset.forName("UTF-8"));
				md.update(bytes);
				
				changePwd2 = Base64.getEncoder().encodeToString(md.digest());
				
			} catch (NoSuchAlgorithmException e) {
				
				System.out.println("암호화 에러 발생!");
				e.printStackTrace();
			}
			
			//userPwd2 기존에 비밀번호 변경할 값
/*		    String userPwd2="";  
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-512");
				byte[] bytes = userPwd.getBytes(Charset.forName("UTF-8"));
				md.update(bytes);
				
				userPwd2 = Base64.getEncoder().encodeToString(md.digest());
				
			} catch (NoSuchAlgorithmException e) {
				
				System.out.println("암호화 에러 발생!");
				e.printStackTrace();
			}*/
			
	      System.out.println("changePwd2 : "+changePwd2);
	      System.out.println("userPwd : "+userPwd);
	      
	      pstmt.setString(1, changePwd2);
	      pstmt.setString(2,  userId.trim());
	      
	      result = pstmt.executeUpdate();    
	      
	    }catch(SQLException e) {
	    	throw new CooException("비밀번호 변경 실패");
	    		  
	    }finally {
	    	close(pstmt);
	    }
	    return result;
}

public int updatePic(Connection con, Member m) throws CooException {
	int result = 0;
	PreparedStatement pstmt = null;
	String sql = prop.getProperty("updatePic"); 
	
	try {
		pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, m.getProfileA());
		pstmt.setInt(2, m.getEmpCode());
		
		result = pstmt.executeUpdate();
		
	}catch(SQLException e) {
		throw new CooException("사진 업로드 실패");
	}finally {
		close(pstmt);
	}
	return result;
	}

public Member checkPicFile(Member m, Connection con) throws CooException {
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	String sql = prop.getProperty("receiveFileName");
	Member result = null;
	
	try {
		result = new Member();
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, m.getEmpCode());
		 rset = pstmt.executeQuery();
		 if(rset.next()) result.setProfileA(rset.getString("PIC"));
		 
	}catch(SQLException e){
		throw new CooException("사진조회 실패");
	}finally {
		close(rset);
		close(pstmt);
	}
	return result;
	
}
}
