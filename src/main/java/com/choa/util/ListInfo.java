package com.choa.util;

public class ListInfo {
	
	private Integer curPage;
	//null이 올수도 있기 때문에
	private Integer perPage;
	
	private String search;
	private String find;
	private int startRow;
	private int lastRow;
	
	private int curBlock;
	private int totalBlock;
	private int startNum;
	private int lastNum;
	
	//page
	public void makePage(int totalCount) {
		int totalPage = 0;
		int perBlock =5;
		if(totalCount % this.getPerPage() == 0) {
			totalPage = totalCount / this.getPerPage();
		} else {
			totalPage = (totalCount / this.getPerPage()) + 1;
		}
		
		if(totalPage % perBlock == 0) {
			this.totalBlock = totalPage / perBlock;
		} else {
			this.totalBlock = (totalPage / perBlock) + 1;
		}
		
		if(this.getCurPage() % perBlock == 0) {
			this.curBlock = this.getCurPage() / perBlock;
		} else {
			this.curBlock = (this.getCurPage() / perBlock) + 1;
		}
		
		this.startNum = (this.curBlock - 1) * perBlock + 1;
		this.lastNum = this.curBlock * perBlock;
		
		if(curBlock == totalBlock)
			this.lastNum = totalPage;
	}
	
	//setRow(makeRow) : 그냥 헷갈려서...............
	public void setRow() {
		startRow = (this.getCurPage()-1) * this.getPerPage()+1;
		lastRow = this.getCurPage() * this.getPerPage();
	}
	
	public int getCurBlock() {
		return curBlock;
	}

	public int getTotalBlock() {
		return totalBlock;
	}

	public int getStartNum() {
		return startNum;
	}

	public int getLastNum() {
		return lastNum;
	}

	public Integer getCurPage() {
		if(curPage==null){
			curPage=1;
		} // 데이터가 null일 때, 오류나지 않게 기본값을 1로 설정 
		return curPage;
	}
	public void setCurPage(Integer curPage) {
		/*if(curPage==null){
			curPage=1;
		}*/
		this.curPage = curPage;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getFind() {
		return find;
	}
	public void setFind(String find) {
		this.find = find;
	}
	public Integer getPerPage() {
		if(perPage==null){
			perPage=10;
		}
		return perPage;
	}
	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}
	public int getStartRow() {
		return startRow;
	}
	public int getLastRow() {
		return lastRow;
	}
	
	

}
