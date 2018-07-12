package com.multi.travel.board.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.multi.travel.board.dto.BoardDto;
import com.multi.travel.board.service.BoardService;
import com.multi.travel.common.CommonConst;
import com.multi.travel.common.FileUploader;
import com.multi.travel.common.IP;

@Controller 
public class BoardController2 {
	@Autowired
	BoardService boardService;
	
	//��Ʈ�ѷ����� ���� dao ������� ���� ���� 
	//���ؼ� 
	@RequestMapping("/board2/list")
	public void list(Model model, BoardDto dto)
	{
		List<BoardDto> list = 
				boardService.getList(dto);
		int total = boardService.getTotal(dto);
		
		model.addAttribute("list", list);
		model.addAttribute("total", total);
		
		for(int i=0; i<list.size(); i++)
		{
			BoardDto temp = list.get(i);
			System.out.println(temp.getTitle());
		}
		System.out.println("��ü ���� : "+total);
	}
	

	//board2/save.mt?title=test&contents=����&userid=test
	@RequestMapping("/board2/save")
	public @ResponseBody String save(
		   HttpServletRequest req,
		   BoardDto dto)
	{		
		ServletContext ctx= req.getServletContext();
		
		String path = ctx.getRealPath(
				CommonConst.BOARD_PATH);
		System.out.println(path);
		
		//���� ���ε� ������ 
		FileUploader.setFilePath(path);
		
		boolean result=FileUploader.upload(dto.getFiles(),
			 dto.getFileNameList()); 
		if( result == false)
		{
			return "fail";//���� ���ε� ���н� 
		}
		
		for(int i=0; 
				i<dto.getFileNameList().size();
				i++)
		{
			dto.getFieldNameList().add(
					"filename"+(i+1));
		}
		
		boardService.insert(dto);
		
		return "success";
	}
	// /board2/view.mt?seq=200
	@RequestMapping("/board2/view")
	public void getView(Model model, BoardDto dto)
	{
		BoardDto viewDto = boardService.getView(dto.getBoard_seq());
		BoardDto prevDto = boardService.getPrev(viewDto);
		BoardDto nextDto = boardService.getNext(viewDto);
		
		model.addAttribute("viewDto", viewDto);
		model.addAttribute("prevDto", prevDto);
		model.addAttribute("nextDto", nextDto);
	}
	
	@RequestMapping("/board2/write")
	public void write(BoardDto dto, 
			 String mode,
			 Model model)
	{
		//board2/write.jsp �� �̵� 
		/*
		 * 1.����Ҷ� - �۾��Ұ� ����  
		 * 2.�����Ҷ� - �����Ҵ���� �ҷ��;� �� 
		 * 3.��۴޶� - �θ���� ������ �����;� �Ѵ�
		 */
		BoardDto resultDto=null;
		System.out.println("mode : " + mode);
		Hashtable<String, String> map
		 = new Hashtable<String, String>();
		
		if(mode!=null && !mode.equals("")
				         && !mode.equals("insert"))
		{
			resultDto =
			  boardService.getView(dto.getBoard_seq());
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
		
		//jsp�� ���� �۾� 
		model.addAttribute("resultDto", resultDto);
		model.addAttribute("filemap", map);
		//jsp�ܿ����� 
		//board2Dto dto = 
		//  (BoardDto)request.getAttribute("resultDto")
		
	}

	
	@RequestMapping("/board2/update")
	public @ResponseBody 
	String update(BoardDto dto,
			   HttpServletRequest req,
			   String []del,
			   String []old_name)
	{
		dto.setFilename1(old_name[0]);
		dto.setFilename2(old_name[1]);
		dto.setFilename3(old_name[2]);
		ServletContext ctx= req.getServletContext();
		String path = ctx.getRealPath(CommonConst.BOARD_PATH);
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
		
		boardService.update(dto);
		
		return "success";
	}
	
	
	@RequestMapping("/board2/reply")
	public @ResponseBody 
	String  reply(  
			HttpServletRequest req,
			BoardDto dto)
	{		
		ServletContext ctx= req.getServletContext();
		
		String path = ctx.getRealPath(
				CommonConst.BOARD_PATH);
		System.out.println(path);
		
		System.out.println(dto.getFieldNameList());
		//���� ���ε� ������ 
		FileUploader.setFilePath(path);
		
		//Client Ip ����.
		String ip = IP.getClientIP(req);
		dto.setIp_addr(ip);
		
		
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
			
		boardService.reply(dto);
		return "success";
	}
	
	@RequestMapping("/board2/delete")
	public @ResponseBody 
	String  delete(BoardDto dto)
	{
		boardService.delete(dto);
		return "success";
	}
}




