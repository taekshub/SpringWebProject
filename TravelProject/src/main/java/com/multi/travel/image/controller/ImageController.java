package com.multi.travel.image.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.multi.travel.board.dto.BoardDto;
import com.multi.travel.board.service.BoardService;
import com.multi.travel.common.CommonConst;
import com.multi.travel.common.Test;
import com.multi.travel.common.ExifData;
import com.multi.travel.common.FileUploader;
import com.multi.travel.common.IP;
import com.multi.travel.common.Test;
import com.multi.travel.image.dto.ImageDto;
import com.multi.travel.image.service.ImageService;

@Controller 
public class ImageController {
	@Resource(name="imageServiceImpl")
	ImageService imageService;
	
	//��Ʈ�ѷ����� ���� dao ������� ���� ���� 
	//���ؼ� 
	//board/save.mt?title=test&contents=����&userid=test
	//board/save.mt?title=test&contents=����&userid=test
		@RequestMapping("/image/save")
		public @ResponseBody String save(HttpServletRequest req, ImageDto dto)
		{		
			ServletContext ctx= req.getServletContext();
			String ip = IP.getClientIP(req);
			String filename = null;
			
			String path = ctx.getRealPath(CommonConst.IMAGE_PATH);
			System.out.println(path);
			     
			//exif data �������� (gps����, ����ũ��, �����ð�, �����ð�)
			dto = ExifData.getExifData(dto.getFile(), dto); 
			dto.setIp_addr(ip);
			filename = FileUploader.getNewFileName(dto.getFile().getOriginalFilename());
			//System.out.println("file name : " + filename);
			dto.setTitle(filename);
			//dto�� ������ Ȯ�� ����׽�Ʈ
			Test.ImageDtoTest(dto);
			
			
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
			
			//boardService.insert(dto);
			
			return "success";
		}
		
		
		@RequestMapping("/file/upload")
		public void upload()
		{
			
		}
				
		@RequestMapping("fileUpload1")
		public void fileUpload(HttpServletRequest request, HttpServletResponse response) throws Exception{
			  MultipartRequest multipartReq = (MultipartRequest)request;
			  System.out.println("���ۼ���");

		}

		@RequestMapping("fileUpload")
		public String filUpload() {
			
			

			return "file/fileUpload";
		}		
		

				
		
	
}




