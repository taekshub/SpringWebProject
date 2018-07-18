package com.multi.travel.board.Like.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.travel.board.Like.dto.LikeDto;
import com.multi.travel.board.Qna.dto.QnaDto;



@Repository("likeDaoImpl")
public class LikeDaoImpl implements LikeDao{

	@Autowired
	SqlSessionTemplate sm;
	
	@Override
	public List<QnaDto> getList(QnaDto dto) {
		
		List<QnaDto> list = sm.selectList("Qna.getList",dto);
				
		return list;
	}

	@Override
	public int getTotal(LikeDto dto) {
		
		int result = (Integer)sm.selectOne("Like.getTotalCount", dto);
		//selectOne �Լ��� object�� ��ȯ
		//object -> Integer -> int �� �ٲ�� �Ѵ� 
		//object -> int �� �ٷ� ������ �Ұ��� �ϴ�
		return result;
	}

	@Override
	public QnaDto getView(String Qna_seq) {
		//��ȸ�� ������Ʈ 
		sm.update("Qna.updateHit", Qna_seq);
		
		QnaDto dto = 
			sm.selectOne("Qna.getView", Qna_seq);
		return dto;
	}

	@Override
	public void insert(LikeDto dto) {
		
		// Qna.xml�� insert �±� 
		//id�� �����ؾ� �Ѵ� Qna.insert
		sm.insert("Like.insert", dto);
	}

	@Override
	public void update(LikeDto dto) {
		sm.update("Like.update", dto);
		
	}

	@Override
	public void delete(LikeDto dto) {
		sm.update("Like.delete", dto);
		
	}

	@Override
	public void reply(QnaDto dto) {
		sm.update("Qna.updateLevel", dto);
		sm.insert("Qna.reply", dto);
		
	}

	@Override
	public QnaDto getPrev(QnaDto paramDto) {
		QnaDto dto = 
			sm.selectOne("Qna.getPrev", paramDto);
		return dto;
	}

	@Override
	public QnaDto getNext(QnaDto paramDto) {
		QnaDto dto = 
			sm.selectOne("Qna.getNext", paramDto);
		return dto;
	}

}
