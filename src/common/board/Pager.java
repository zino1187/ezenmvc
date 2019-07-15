package common.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

//�ι��ٽô� ����¡ ó�� ������ ���ۼ����� ����!!!!
public class Pager {
	private int currentPage=1;
	private int totalRecord;//�� ���ڵ� ��
	private int pageSize=10; //�������� ������ ���ڵ� ��
	private int totalPage;
	private int blockSize=10;			
	private int firstPage;
	private int lastPage;
	private int curPos;//�������� ���� ���ؽ�  1page :0 , 2page:10, 3page:20
	private int num;
	
	//����¡ ó�� �� �ʱ�ȭ!!!
	public void init(HttpServletRequest request , List list) {
		//����ڰ� ����¡ �ѹ� ��ũ�� ������..�� ���� ���� �켱���Ѵ�!!
		//a href="list.do?currentPage=5"
		if(request.getParameter("currentPage")!=null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		this.totalRecord = list.size();
		System.out.println("�ѷ��ڵ� ���� "+this.totalRecord);
		
		this.totalPage = (int)Math.ceil((float)this.totalRecord/this.pageSize);
		this.firstPage = this.currentPage - (this.currentPage-1)%this.blockSize;
		this.lastPage = this.firstPage + (this.blockSize -1);
		this.curPos = (this.currentPage-1) * this.pageSize;
		System.out.println("curPos�� "+this.curPos);
		this.num = this.totalRecord - this.curPos;
		System.out.println("num �� "+this.num);
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





