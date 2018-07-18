package com.multi.travel.board.Like.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.travel.board.Qna.dto.QnaDto;
import com.multi.travel.board.Like.dao.LikeDao;
import com.multi.travel.board.Like.dto.LikeDto;
import com.multi.travel.board.Qna.dao.QnaDao;

import javax.annotation.Resource;

@Service ("likeServiceImpl")
public class LikeSeriveImpl implements LikeService{

	//�������� �������� qnaDao ��ü ������
	//��������ش� (Dependency Injection)
	// IoC(Inversion of Control) 
	@Resource(name="likeDaoImpl")
	LikeDao likeDao;
	
	@Override
	public List<QnaDto> getList(QnaDto dto) {
		// TODO Auto-generated method stub
		return likeDao.getList(dto);
	}

	@Override
	public int getTotal(LikeDto dto) {
		
		return likeDao.getTotal(dto);
	}

	@Override
	public QnaDto getView(String qna_seq) {
		
		return likeDao.getView(qna_seq);
	}

/*	@Override
	public void insert(LikeDto dto) {
		likeDao.insert(dto);
	}*/

	@Override
	public void update(LikeDto dto) {
		
		likeDao.update(dto);
	}

	@Override
	public void delete(LikeDto dto) {
		likeDao.delete(dto);
	}

	@Override
	public void reply(QnaDto dto) {
		likeDao.reply(dto);
	}

	@Override
	public QnaDto getPrev(QnaDto paramDto) {
		
		return likeDao.getPrev(paramDto);
	}

	@Override
	public QnaDto getNext(QnaDto paramDto) {
		
		return likeDao.getNext(paramDto);
	}

	@Override
	public void insert(LikeDto dto) {
		likeDao.insert(dto);
		// TODO Auto-generated method stub
		
	}
}
