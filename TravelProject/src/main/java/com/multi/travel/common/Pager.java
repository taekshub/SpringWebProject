package com.multi.travel.common;

import javax.servlet.http.HttpServletRequest;

public class Pager {
		
	
	//�Լ�, ������ �±׸� ����� �Լ� 
	//<a href= .....
	public static String makeTag(HttpServletRequest request , int pageSize , int total) {
		
	/*	StringBuffer sb = new StringBuffer();*/
		
		String Tag = "" ; 
		String contextPath = request.getContextPath();
		
		int cpage; //���������� ����
		int pageTotal; //��ü ������ ���� 
		int pageGroupSize = 5;
		//�� �׷�� ������ ������ �� 
		int pageGroupStart; //1,6,11,16,...
		int pageGroupEnd;//5,10,15,....

		String path="";
		//System.out.println(path);
		

		 //<a href=\"" + contextPath + "/resources/images/board/def/pg_first.gif\" class=\"page-link\" />
		//<li class="page-item disabled"><a href="#" class="page-link">Previous</a></li>
		String beginLabel1 	= "<img src=\"" + contextPath + "/resources/images/board/def/pg_first.gif\" alt=\"ù��° ������\" />";

		String beginLabel 	= "<li class=\"page-item\"><a href=\"\"  class=\"page-link\"><<</a></li>";
		String prevLabel 	= "<li class=\"page-item\"><a href=\"\"  class=\"page-link\"><</a></li>";
		String nextLabel 	= "<li class=\"page-item\"><a href=\"\"  class=\"page-link\">></a></li>";
		String endLabel 	= "<li class=\"page-item\"><a href=\"\"  class=\"page-link\">>></a></li>";	

		
		try {

			StringBuffer sb = new StringBuffer();
			
			
			String page = request.getParameter("pg") ;
			page = ( page == null ) ? "0" : page ; 
			
//			setPg(Integer.parseInt(page)) ; 
//			setTotalCnt(Integer.parseInt(totCnt)) ; 
	
			cpage = Integer.parseInt(page) ; 

			pageTotal = (total - 1) / pageSize;
			//total - ��ü ������ �Ǽ� 
			//��ü ������ ���� ���ϱ� 
           
			pageGroupStart = (int) (cpage / pageGroupSize) * pageGroupSize;
			pageGroupEnd = pageGroupStart + pageGroupSize;
			if (pageGroupEnd > pageTotal) {
				pageGroupEnd = pageTotal + 1;
			}
            //0~4, 5~9, 10~14, 15~19
			boolean hasPreviousPage = cpage - pageGroupSize >= 0;
			boolean hasNextPage = pageGroupStart + pageGroupSize < pageTotal;

			
			sb.append("<div class=\"pg\">\r\n") ;
			sb.append("<ul class=\"pagination\">\r\n") ;
			sb.append((cpage > 0) ? makeLink2(0, beginLabel,"<<") : beginLabel);
			sb.append(hasPreviousPage ? makeLink2(pageGroupStart - 1, prevLabel,"<") : prevLabel);
			
/*			sb.append((cpage > 0) ? makeLink(0, beginLabel) : beginLabel);
			sb.append(hasPreviousPage ? makeLink(pageGroupStart - 1, prevLabel) : prevLabel);
			*/
			//sb.append("<ul class=\"pagination\">\r\n") ;  
		
			
			for (int i = pageGroupStart; i < pageGroupEnd; i++) {
				if (i == cpage) {			
					sb.append("<li class=\"page-item active\">") ; 
					sb.append(makeLink(i, (i + 1) + ""));
					sb.append("</li>\r\n") ;
				} else {
					sb.append("<li class=\"page-item\">") ; 
					//sb.append("<a href=\"#\" class=\"page-link\">");
					sb.append(makeLink(i, (i + 1) + ""));
					//sb.append("</a>");
					sb.append("</li>\r\n") ;  
				}
			}
			
			//sb.append("</ul>\r\n") ;  
			sb.append(hasNextPage ? makeLink2(pageGroupEnd, nextLabel,">") : nextLabel);
			sb.append((cpage < pageTotal) ? makeLink2(pageTotal, endLabel,">>") : endLabel);
			
			
/*			sb.append(hasNextPage ? makeLink(pageGroupEnd, nextLabel) : nextLabel);
			sb.append((cpage < pageTotal) ? makeLink(pageTotal, endLabel) : endLabel);*/
			sb.append("</ul>\r\n") ;  
			sb.append("</div>") ;
			Tag = sb.toString() ; 	
		} catch ( Exception e ) {
			e.printStackTrace() ; 
		}
			
		return Tag ; 
	}

	public static String makeLink(int page, String label) 
	{
/*		System.out.println(label);
		if(label.equals("beginLabel") || label.equals("prevLabel") || label.equals("nextLabel") || label.equals("endLabel")) {
			StringBuffer tmp = new StringBuffer();
			tmp.append("<li class=\"page-item\"><a href=\"javascript:goPage('" + page + "')\"  class=\"page-link\"> ").append(label).append("</a></li>");
			return tmp.toString();			
		}*/
		
		StringBuffer tmp = new StringBuffer();
		tmp.append("<a href=\"javascript:goPage('" + page + "')\"  class=\"page-link\"> ").append(label).append("</a>");
		return tmp.toString();
	}

	
	public static String makeLink2(int page, String label,String labelName) 
	{

		StringBuffer tmp = new StringBuffer();
		tmp.append("<li class=\"page-item\"><a href=\"javascript:goPage('" + page + "')\"  class=\"page-link\"> "+labelName).append("</a></li>");
		return tmp.toString();			
	
		

	}	

}
