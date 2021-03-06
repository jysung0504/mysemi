package com.coo.check.model.vo;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author Rin
 *
 */
public class CheckDoc implements Serializable{
	private int docNumber; 	//문서번호
	private String aTitle;	//제목
	private int aWriter;	//작성자 no
	private String awriterName; //작성자 이름
	private String jobName;
	private String docType; //문서 종류
	private int aStatus;	//진행상황  0: 내부진행, 1:합의진행, 2:최종진행 3:결재완료 4:반려  -> 누군가 4를 누르면 결재진행 종료및 코멘트 넣기
	private String approver; //현재 결재 진행자(반려시 작성자)
	private String deptName; //기안자 부서명
	private String indept;
	private String inPeople; //내부 결재 인원 (?,?,?,?) empno로 정과 부 들어와서  split 할것
	private String inStatus; //내부결재 상황 (위의 길이의 /2 만큼)'0' 들여놓을 것 -> join(', ')
	private String coldeptName; //똑같이 하는데 null가능
	private String colPeople;
	private String colStatus;
	private String viewPeople; //참조자
	private Date docDate; //문서 작성 날짜
	private String returnComment; //반려시 코멘트
	private String deleteyn; //문서 삭제시 y  기본값 n


	public CheckDoc() {
		
	}

	/**
	 * 저장용
	 * @param aTitle
	 * @param aWriter
	 * @param awriterName
	 * @param docType
	 * @param approver
	 * @param indept
	 * @param inPeople
	 * @param inStatus
	 * @param coldeptName
	 * @param colPeople
	 * @param colStatus
	 * @param viewPeople
	 */
	public CheckDoc( String aTitle, int aWriter,String awriterName, String deptName, String docType, String approver,
			String indept, String inPeople, String inStatus, String coldeptName,String colPeople, String colStatus,
			 String viewPeople) {
		
		this.aTitle = aTitle;
		this.aWriter = aWriter;
		this.awriterName = awriterName;
		this.deptName= deptName;
		this.docType = docType;
		this.approver = approver;
		this.indept = indept;
		this.inPeople = inPeople;
		this.inStatus = inStatus;
		this.coldeptName = coldeptName;
		this.colPeople = colPeople;
		this.colStatus = colStatus;
		this.viewPeople = viewPeople;
	}
	
	

	/**
	 * 업데이트용
	 * @param docNumber
	 * @param aStatus
	 * @param approver
	 * @param inStatus
	 * @param colStatus
	 * @param returnComment
	 */
	public CheckDoc(int docNumber, int aStatus, String approver, String inStatus, String colStatus,
			String returnComment) {
		super();
		this.docNumber = docNumber;
		this.aStatus = aStatus;
		this.approver = approver;
		this.inStatus = inStatus;
		this.colStatus = colStatus;
		this.returnComment = returnComment;
	}

	public CheckDoc(int docNumber, String aTitle, int aWriter, String awriterName,  String deptName,String docType, int aStatus,
			String approver, String indept,String inPeople, String inStatus,String coldeptName, String colPeople,
			String colStatus,  String viewPeople, Date docDate, String returnComment, String deleteyn) {
 
		super();
		this.docNumber = docNumber;
		this.aTitle = aTitle;
		this.aWriter = aWriter;
		this.awriterName = awriterName;
		this.docType = docType;
		this.aStatus = aStatus;
		this.approver = approver;
		this.deptName = deptName;
		this.indept = indept;
		this.inPeople = inPeople;
		this.inStatus = inStatus;
		this.coldeptName = coldeptName;
		this.colPeople = colPeople;
		this.colStatus = colStatus;
		this.viewPeople = viewPeople;
		this.docDate = docDate;
		this.returnComment = returnComment;
		this.deleteyn = deleteyn;
	}

	public String getIndept() {
		return indept;
	}

	public void setIndept(String indept) {
		this.indept = indept;
	}

	public int getDocNumber() {
		return docNumber;
	}

	public void setDocNumber(int docNumber) {
		this.docNumber = docNumber;
	}

	public String getaTitle() {
		return aTitle;
	}

	public void setaTitle(String aTitle) {
		this.aTitle = aTitle;
	}

	public int getaWriter() {
		return aWriter;
	}

	public void setaWriter(int aWriter) {
		this.aWriter = aWriter;
	}

	public String getAwriterName() {
		return awriterName;
	}

	public void setAwriterName(String awriterName) {
		this.awriterName = awriterName;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public int getaStatus() {
		return aStatus;
	}

	public void setaStatus(int aStatus) {
		this.aStatus = aStatus;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}


	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getInPeople() {
		return inPeople;
	}

	public void setInPeople(String inPeople) {
		this.inPeople = inPeople;
	}

	public String getInStatus() {
		return inStatus;
	}

	public void setInStatus(String inStatus) {
		this.inStatus = inStatus;
	}


	public String getColdeptName() {
		return coldeptName;
	}

	public void setColdeptName(String coldeptName) {
		this.coldeptName = coldeptName;
	}

	public String getColPeople() {
		return colPeople;
	}

	public void setColPeople(String colPeople) {
		this.colPeople = colPeople;
	}

	public String getColStatus() {
		return colStatus;
	}

	public void setColStatus(String colStatus) {
		this.colStatus = colStatus;
	}


	public String getViewPeople() {
		return viewPeople;
	}

	public void setViewPeople(String viewPeople) {
		this.viewPeople = viewPeople;
	}

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public String getReturnComment() {
		return returnComment;
	}

	public void setReturnComment(String returnComment) {
		this.returnComment = returnComment;
	}

	public String getDeleteyn() {
		return deleteyn;
	}

	public void setDeleteyn(String deleteyn) {
		this.deleteyn = deleteyn;
	}
	
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}


	@Override
	public String toString() {
		return "CheckDoc [docNumber=" + docNumber + ", aTitle=" + aTitle + ", aWriter=" + aWriter + ", awriterName="
				+ awriterName + ", docType=" + docType + ", aStatus=" + aStatus + ", approver=" + approver
				+ ", deptName=" + deptName + ", indept=" + indept + ", inPeople=" + inPeople + ", inStatus=" + inStatus
				+ ", coldeptName=" + coldeptName + ", colPeople=" + colPeople + ", colStatus=" + colStatus
				+ ", viewPeople=" + viewPeople + ", docDate=" + docDate + ", returnComment=" + returnComment
				+ ", deleteyn=" + deleteyn + "]";
	}

	
	
	
   
	
	
	
	

}