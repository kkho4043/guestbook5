package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVo;

@Repository
public class GuestDao {

	@Autowired
	private SqlSession sqlSession;

//저장========================================================	
	public void guestInsert(GuestVo guestVo) {

		sqlSession.insert("guestbook.insert", guestVo);
	}

//list========================================================	
	public List<GuestVo> getguestList() {

		List<GuestVo> guestList = sqlSession.selectList("guestbook.selectList");

		return guestList;
	}

//onelist========================================================	
	public GuestVo getList(int no) {
		GuestVo guestvo = sqlSession.selectOne("guestbook.selectOne", no);
		return guestvo;

	}

//delete=======================================================
	public void guestDelete(int no) {
		sqlSession.delete("guestbook.delete", no);
	}

//update=======================================================
	public void guestupdate(GuestVo guestVo) {
		sqlSession.update("update", guestVo);
	}
}
