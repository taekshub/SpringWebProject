package com.multi.travel.board.Qna.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.multi.travel.board.Qna.dto.QnaDto;
import com.multi.travel.board.Qna.service.QnaService;
import com.multi.travel.common.CommonConst;
import com.multi.travel.common.FileUploader;
import com.multi.travel.common.IP;
import com.multi.travel.image.dto.ImageDto;
import com.multi.travel.image.service.ImageService;

@Controller 
public class QnaController {
	@Resource(name="qnaServiceImpl")
	QnaService qnaService;
	
	
	//��Ʈ�ѷ����� ���� dao ������� ���� ���� 
	//���ؼ� 
	@RequestMapping("/Qna/list")
	public void QnAlist(Model model, QnaDto dto)
	{
		System.out.println("@@@@@@QnaSel@@@@@@@ : "+dto.getSel());
		List<QnaDto> list = qnaService.getList(dto);
		int total = qnaService.getTotal(dto);
		
		if(dto == null) {
			System.out.println("dto is null");
		}
		else {
			System.out.println("dto is not null");
			System.out.println("QnaSeq ����Ʈ��");
			
			if(dto.getQna_seq() == null || dto.getQna_seq().equals("")) {
				System.out.println("QnaSeq is null");
			}
			else {
				System.out.println("QnaSeq is not null");
				System.out.println(dto.getQna_seq());	
			}
		}
		
		model.addAttribute("list", list);
		model.addAttribute("total", total);
		
		for(int i=0; i<list.size(); i++)
		{
			QnaDto temp = list.get(i);
			System.out.println(temp.getTitle());
		}
		System.out.println("��ü ���� : "+total);
	}

	
	
	
	@RequestMapping("/Qna/test")
	public void test(Model model, QnaDto dto)
	{
		List<QnaDto> list = qnaService.getList(dto);
		int total = qnaService.getTotal(dto);
		
		model.addAttribute("list", list);
		model.addAttribute("total", total);
		
		for(int i=0; i<list.size(); i++)
		{
			QnaDto temp = list.get(i);
			System.out.println(temp.getTitle());
			System.out.println(temp.getModdate());
		}
		System.out.println("��ü ���� : "+total);
	}	
	
	

	

	//Qna/save.mt?title=test&contents=����&userid=test
	@RequestMapping("/Qna/save")
	public @ResponseBody String save(HttpServletRequest req, QnaDto dto)
	{		
		ServletContext ctx= req.getServletContext();
		
		String path = ctx.getRealPath(CommonConst.QnA_PATH);
		System.out.println(path);
		
		String ip = IP.getClientIP(req);
		dto.setIp_addr(ip);
		
		//���� ���ε� ������ 
		FileUploader.setFilePath(path);
		
		boolean result=FileUploader.upload(dto.getFiles(),
			 dto.getFileNameList()); 
		if( result == false)
		{
			return "fail";//���� ���ε� ���н� 
		}
		
		for(int i=0; i<dto.getFileNameList().size(); i++)
		{
			dto.getFieldNameList().add("filename"+(i+1));
		}
		
		qnaService.insert(dto);
		
		return "success";
	}
	// /Qna/view.mt?seq=200
	@RequestMapping("/Qna/view")
	public void getView(Model model, QnaDto dto)
	{
		QnaDto viewDto = qnaService.getView(dto.getQna_seq());
		QnaDto prevDto = qnaService.getPrev(viewDto);
		QnaDto nextDto = qnaService.getNext(viewDto);
		
		model.addAttribute("viewDto", viewDto);
		model.addAttribute("prevDto", prevDto);
		model.addAttribute("nextDto", nextDto);
	}
	
	
	
	@RequestMapping("/Qna/write")
	public void write(QnaDto dto, String mode,Model model)
	{
		//Qna/write.jsp �� �̵� 
		/*
		 * 1.����Ҷ� - �۾��Ұ� ����  
		 * 2.�����Ҷ� - �����Ҵ���� �ҷ��;� �� 
		 * 3.��۴޶� - �θ���� ������ �����;� �Ѵ�
		 */
		QnaDto resultDto=null;
		System.out.println("mode : " + mode);
		Hashtable<String, String> map = new Hashtable<String, String>();

		if(mode!=null && !mode.equals("") && !mode.equals("insert"))
		{
			resultDto = qnaService.getView(dto.getQna_seq());
			System.out.println(resultDto.getTitle());

			if(mode.equals("reply"))
			{
				resultDto.setTitle("���:");
				resultDto.setContents("");
			}
			
			//�迭�� �ƴϰ� �ʵ���� filename1, filename2, filename3
			//for�� ��� �ȵǴϱ� �迭 ������ �ٲٱ�
			//�ؽ����̺� ����ϱ� 
			
			System.out.println(resultDto.getFilename1());
			System.out.println(resultDto.getFilename2());
			System.out.println(resultDto.getFilename3());
				
			map.put("filename1", resultDto.getFilename1());
			map.put("filename2", resultDto.getFilename2());
			map.put("filename3", resultDto.getFilename3());
		}
		
		if(mode.equals("insert")) {
/*			resultDto = new QnaDto();
			resultDto.setUserid(dto.getUserid());
			System.out.println(resultDto.getUserid());*/
			//resultDto = QnaService.getView(dto.getQna_seq());
			System.out.println("RESULT DTO USERID"+dto.getUserid());
		}
		
		//System.out.println("getUserid:~~~~~~~~~~~~~");
		//System.out.println(resultDto.getUserid());		
		//jsp�� ���� �۾� 
		model.addAttribute("resultDto", resultDto);
		model.addAttribute("filemap", map);
		//jsp�ܿ����� 
		//QnaDto dto = 
		//  (QnaDto)request.getAttribute("resultDto")
		
	}

	
	@RequestMapping("/Qna/update")
	public @ResponseBody 
	String update(QnaDto dto,
			   HttpServletRequest req,
			   String []del,
			   String []old_name)
	{
		dto.setFilename1(old_name[0]);
		dto.setFilename2(old_name[1]);
		dto.setFilename3(old_name[2]);
		ServletContext ctx= req.getServletContext();
		String path = ctx.getRealPath(CommonConst.QnA_PATH);
		System.out.println(path);
		//���� ���ε� ������ 
		FileUploader.setFilePath(path);
		
		boolean result=FileUploader.upload(dto.getFiles(),
			 dto.getFileNameList());
		if( result == false)
		{
			return "fail";//���� ���ε� ���н� 
		}
		
		
		for(int i=0; i<del.length; i++)
		{
			String filename=
					dto.getFileNameList().get(i);
			if( del[i].equals("1"))
				dto.setFilename1(filename);
			else if(del[i].equals("2"))
				dto.setFilename2(filename);
			else if(del[i].equals("3"))
				dto.setFilename3(filename);
		}
		
		qnaService.update(dto);
		
		return "success";
	}
	
	
	@RequestMapping("/Qna/reply")
	public @ResponseBody 
	String  reply(  HttpServletRequest req, QnaDto dto)
	{		
		ServletContext ctx= req.getServletContext();
		
		String path = ctx.getRealPath(CommonConst.QnA_PATH);
		System.out.println(path);
		
		//���� ���ε� ������ 
		FileUploader.setFilePath(path);
		
		//Client Ip ����.
		String ip = IP.getClientIP(req);
		dto.setIp_addr(ip);
/*		Qna_seq
		,title
		,contents
		,userid
		,hit
		,group_id
		,depth
		,g_level
		,regdate
		,delyn
		,ip_addr*/
		System.out.println("����");
		
/*		System.out.println(dto.getQna_seq());
		System.out.println(dto.getTitle());
		System.out.println(dto.getContents());
		System.out.println(dto.getUserid());
		System.out.println(dto.getHit());
		System.out.println(dto.getGroup_id());
		System.out.println(dto.getDepth());
		System.out.println(dto.getG_level());
		System.out.println(dto.getRegdate());
		System.out.println(dto.getIp_addr());*/
		boolean result=FileUploader.upload(dto.getFiles(),dto.getFileNameList()); 
		if( result == false)
		{
			System.out.println("���� ���ε� ����.");
			return "fail";//���� ���ε� ���н� 
		}
		
		for(int i=0; i<dto.getFileNameList().size();i++)
		{
			dto.getFieldNameList().add("filename"+(i+1));
		}
			
		qnaService.reply(dto);
		return "success";
	}
	
	@RequestMapping("/Qna/delete")
	public @ResponseBody 
	String  delete(QnaDto dto)
	{
		qnaService.delete(dto);
		return "success";
	}
	

	// /Qna2/view.mt?seq=200
	@RequestMapping("/Qna/Qnaview")
	public void getQnaView(Model model, QnaDto dto)
	{
		QnaDto viewDto = qnaService.getView(dto.getQna_seq());
		QnaDto prevDto = qnaService.getPrev(viewDto);
		QnaDto nextDto = qnaService.getNext(viewDto);
		
		model.addAttribute("viewDto", viewDto);
		model.addAttribute("prevDto", prevDto);
		model.addAttribute("nextDto", nextDto);
	}
	
	
	
	
	@RequestMapping("/Qna/Qnawrite")
	public void Qnawrite(QnaDto dto, String mode,Model model)
	{
		//Qna/write.jsp �� �̵� 
		/*
		 * 1.����Ҷ� - �۾��Ұ� ����  
		 * 2.�����Ҷ� - �����Ҵ���� �ҷ��;� �� 
		 * 3.��۴޶� - �θ���� ������ �����;� �Ѵ�
		 */
		QnaDto resultDto=null;
		System.out.println("mode : " + mode);
		Hashtable<String, String> map = new Hashtable<String, String>();

		if(mode!=null && !mode.equals("") && !mode.equals("insert"))
		{
			resultDto = qnaService.getView(dto.getQna_seq());
			System.out.println(resultDto.getTitle());

			if(mode.equals("reply"))
			{
				resultDto.setTitle("���:");
				resultDto.setContents("");
			}
			
			//�迭�� �ƴϰ� �ʵ���� filename1, filename2, filename3
			//for�� ��� �ȵǴϱ� �迭 ������ �ٲٱ�
			//�ؽ����̺� ����ϱ� 
			
			System.out.println(resultDto.getFilename1());
			System.out.println(resultDto.getFilename2());
			System.out.println(resultDto.getFilename3());
				
			map.put("filename1", resultDto.getFilename1());
			map.put("filename2", resultDto.getFilename2());
			map.put("filename3", resultDto.getFilename3());
		}
		
		if(mode.equals("insert")) {
/*			resultDto = new QnaDto();
			resultDto.setUserid(dto.getUserid());
			System.out.println(resultDto.getUserid());*/
			//resultDto = QnaService.getView(dto.getQna_seq());
			System.out.println("RESULT DTO USERID"+dto.getUserid());
		}
		
		//System.out.println("getUserid:~~~~~~~~~~~~~");
		//System.out.println(resultDto.getUserid());		
		//jsp�� ���� �۾� 
		model.addAttribute("resultDto", resultDto);
		model.addAttribute("filemap", map);
		//jsp�ܿ����� 
		//QnaDto dto = 
		//  (QnaDto)request.getAttribute("resultDto")
		
	}	
	
}


	





