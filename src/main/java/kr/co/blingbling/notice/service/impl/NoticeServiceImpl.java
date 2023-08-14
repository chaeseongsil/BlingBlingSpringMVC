package kr.co.blingbling.notice.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.blingbling.notice.domain.Notice;
import kr.co.blingbling.notice.domain.PageData;
import kr.co.blingbling.notice.service.NoticeService;
import kr.co.blingbling.notice.store.NoticeStore;

@Service
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private NoticeStore nStore;
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public PageData selectNoticeList(int currentPage) {
		List<Notice> nList = nStore.selectNoticeList(sqlSession, currentPage);
		String pageNavi = nStore.generatePageNavi(sqlSession, currentPage);
		PageData pd = new PageData(nList, pageNavi);
		return pd;
	}

	@Override
	public Notice selectOneByNo(int noticeNo) {
		Notice notice = nStore.selectOneByNo(sqlSession, noticeNo);
		return notice;
	}

}
