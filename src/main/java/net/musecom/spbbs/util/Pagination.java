package net.musecom.spbbs.util;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import net.musecom.spbbs.dao.SpDao;
import net.musecom.spbbs.dto.PageDto;

public class Pagination {
	
   private int totalCount;
   private int startPage;
   private int endPage;
   private boolean prev;
   private boolean next;
   private int displayPageNum;
	
   private PageDto pdto;
   
   
   public PageDto getPdto() {
	return pdto;
   }

   public void setPdto(PageDto pdto) {
	  this.pdto = pdto;
   }
   
	public int getTotalCount() {
	return totalCount;
	}
	
	public void setTotalCount() {
		SpDao dao = new SpDao();
		this.totalCount = dao.totalRecord();
		calcData();
	}
		
	public int getStartPage() {
		return startPage;
	}
		
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
	
	public void setEndPage(int endPage) {
		 this.endPage = endPage;
	}
	
	public boolean isPrev() {
		return prev;
	}
	
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	
	public boolean isNext() {
		return next;
	}
	
	public void setNext(boolean next) {
		this.next = next;
	}
	
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	
	
	public void setDisplayPageNum(int  displayPageNum) {
		if(displayPageNum == 0) {
		   displayPageNum = 10;
		}
		this.displayPageNum = displayPageNum;
	}
   
   private void calcData() {
		
	 endPage = (int)( Math.ceil(pdto.getPage() / (double) getDisplayPageNum()) * getDisplayPageNum());
	 startPage = (endPage - getDisplayPageNum()) + 1;
	 
	 int tempPage = (int) (Math.ceil(getTotalCount() / (double) pdto.getPerPageNum()));
	 if(endPage > tempPage) {
	    endPage = tempPage;				
	 }
	 prev = getStartPage() == 1 ? false : true;
	 next = endPage * pdto.getPerPageNum() >= getTotalCount() ? false : true;
   }
   
   public String makeQuery(int page) {
	   
	   UriComponents uriComponents = UriComponentsBuilder
			                               .newInstance()	   
			                               .queryParam("page", page)
			                               .build();
	   return uriComponents.toString();
   }
	
}
