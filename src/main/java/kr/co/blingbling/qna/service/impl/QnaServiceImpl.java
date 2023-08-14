package kr.co.blingbling.qna.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.blingbling.qna.domain.PageData;
import kr.co.blingbling.qna.domain.Qna;
import kr.co.blingbling.qna.service.QnaService;
import kr.co.blingbling.qna.store.QnaStore;

@Service
public class QnaServiceImpl implements QnaService{
	
	@Autowired
	private QnaStore qStore;
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insertQna(Qna qna) {
		int result = qStore.insertQna(sqlSession, qna);
		return result;
	}

	@Override
	public int updateQna(Qna qna) {
		int result = qStore.updateQna(sqlSession, qna);
		return result;
	}

	@Override
	public int deleteQna(int myQnaNo) {
		int result = qStore.deleteQna(sqlSession, myQnaNo);
		return result;
	}

	@Override
	public PageData selectAllQnaList(int currentPage) {
		List<Qna> qList = qStore.selectAllQnaList(sqlSession, currentPage);
		String pageNavi = qStore.generateAllPageNavi(sqlSession, currentPage);
		PageData pd = new PageData(qList, pageNavi);
		return pd;
	}

	@Override
	public PageData selectMyQnaList(int currentPage, String myQnaWriter) {
		List<Qna> qList = qStore.selectMyQnaList(sqlSession, currentPage, myQnaWriter);
		String pageNavi = qStore.generateMyPageNavi(sqlSession, currentPage, myQnaWriter);
		PageData pd = new PageData(qList, pageNavi);
		return pd;
	}

	@Override
	public Qna selectOneByNo(int myQnaNo) {
		Qna qna = qStore.selectOneByNo(sqlSession, myQnaNo);
		return qna;
	}

}
