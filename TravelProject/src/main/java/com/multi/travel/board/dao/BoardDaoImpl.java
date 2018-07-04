package com.multi.travel.board.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.travel.board.dto.BoardDto;

@Repository("boardDaoImpl")
public class BoardDaoImpl implements BoardDao{

	@Autowired
	SqlSessionTemplate sm;
	
	@Override
	public List<BoardDto> getList(BoardDto dto) {
		
		List<BoardDto> list = sm.selectList("Board.getList",dto);
				
		return list;
	}

	@Override
	public int getTotal(BoardDto dto) {
		
		int result = (Integer)sm.selectOne("Board.getTotalCount", dto);
		//selectOne �Լ��� object�� ��ȯ
		//object -> Integer -> int �� �ٲ�� �Ѵ� 
		//object -> int �� �ٷ� ������ �Ұ��� �ϴ�
		return result;
	}

	@Override
	public BoardDto getView(String board_seq) {
		//��ȸ�� ������Ʈ 
		sm.update("Board.updateHit", board_seq);
		
		BoardDto dto = 
			sm.selectOne("Board.getView", board_seq);
		return dto;
	}

	@Override
	public void insert(BoardDto dto) {
		
		// Board.xml�� insert �±� 
		//id�� �����ؾ� �Ѵ� Board.insert
		sm.insert("Board.insert", dto);
	}

	@Override
	public void update(BoardDto dto) {
		sm.update("Board.update", dto);
		
	}

	@Override
	public void delete(BoardDto dto) {
		sm.update("Board.delete", dto);
		
	}

	@Override
	public void reply(BoardDto dto) {
		sm.update("Board.updateLevel", dto);
		sm.insert("Board.reply", dto);
		
	}

	@Override
	public BoardDto getPrev(BoardDto paramDto) {
		BoardDto dto = 
			sm.selectOne("Board.getPrev", paramDto);
		return dto;
	}

	@Override
	public BoardDto getNext(BoardDto paramDto) {
		BoardDto dto = 
			sm.selectOne("Board.getNext", paramDto);
		return dto;
	}

}
