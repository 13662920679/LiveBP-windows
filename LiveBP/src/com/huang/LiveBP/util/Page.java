package com.huang.LiveBP.util;

public class Page {
	
	int start;//开始数据的索引
	int count;//每一页的数量
	int total;//总共的数据量
	
	public Page(int start,int count) {
		super();
		this.start = start;
		this.count = count;
	}
	
    // 判断是否有上一页
	public boolean isHasPreviouse() {
		if(start==0) {
			return false;
		}else {
			return true;
		}
	}
	
	// 判断是否有下一页
	public boolean isHasNext() {
		if(start==getLast()) {
			return false;
		}else {
			return true;
		}
	}
	
	//计算得到尾页
	public int getLast() {
		int last;
		if(total%count==0) {
			last = total-count;
		}else {
			last = total-total%count;
		}
		last=last<0?0:last;
		return last;
	}
	
	//计算得到总页数
	public int getTotalPage() {
		int totalPage;
		if(total%count==0) {
			totalPage=total/count;
		}else {
			totalPage=total/count+1;
		}
		return totalPage;
	}

}
