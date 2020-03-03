package com.huang.LiveBP.util;

public class Page {
	
	int start;//��ʼ���ݵ�����
	int count;//ÿһҳ������
	int total;//�ܹ���������
	
	public Page(int start,int count) {
		super();
		this.start = start;
		this.count = count;
	}
	
    // �ж��Ƿ�����һҳ
	public boolean isHasPreviouse() {
		if(start==0) {
			return false;
		}else {
			return true;
		}
	}
	
	// �ж��Ƿ�����һҳ
	public boolean isHasNext() {
		if(start==getLast()) {
			return false;
		}else {
			return true;
		}
	}
	
	//����õ�βҳ
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
	
	//����õ���ҳ��
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
