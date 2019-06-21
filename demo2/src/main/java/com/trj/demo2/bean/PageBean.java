package com.trj.demo2.bean;

import java.io.Serializable;
import java.util.List;
/**
 * 首先我们根据自己项目的情况，定义一个PageBean，来保存分页之后的结果,具体可以参考源代码中的PageInfo类的定义，
 * 其实PageInfo是插件作者给我们自己定义自己的PageBean，提供的一个参考例子
 *
 * 作者：小童
 * 创建时间： 2019年3月7日
 * 
 *
 * @param <T>
 */

public class PageBean<T> implements Serializable {
	private long total; // 总记录数
	private List<T> list; // 结果集
	private int pageNum; // 第几页
	private int pageSize; // 每页记录数
	private int pages; // 总页数

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}
}
