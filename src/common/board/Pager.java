package common.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

//두번다시는 페이징 처리 공식을 재작성하지 말자!!!!
public class Pager {
	private int currentPage=1;
	private int totalRecord;//총 레코드 수
	private int pageSize=10; //페이지당 보여질 레코드 수
	private int totalPage;
	private int blockSize=10;			
	private int firstPage;
	private int lastPage;
	private int curPos;//페이지당 시작 인텍스  1page :0 , 2page:10, 3page:20
	private int num;
	
	//페이징 처리 값 초기화!!!
	public void init(HttpServletRequest request , List list) {
		//사용자가 페이징 넘버 링크를 누르면..그 누른 값을 우선시한다!!
		//a href="list.do?currentPage=5"
		if(request.getParameter("currentPage")!=null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		this.totalRecord = list.size();
		System.out.println("총레코드 수는 "+this.totalRecord);
		
		this.totalPage = (int)Math.ceil((float)this.totalRecord/this.pageSize);
		this.firstPage = this.currentPage - (this.currentPage-1)%this.blockSize;
		this.lastPage = this.firstPage + (this.blockSize -1);
		this.curPos = (this.currentPage-1) * this.pageSize;
		System.out.println("curPos는 "+this.curPos);
		this.num = this.totalRecord - this.curPos;
		System.out.println("num 는 "+this.num);
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getCurPos() {
		return curPos;
	}

	public void setCurPos(int curPos) {
		this.curPos = curPos;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	
}





