package kr.co.blingbling.notice.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.blingbling.notice.domain.Notice;

public interface NoticeStore {

	public List<Notice> selectNoticeList(SqlSession session, int currentPage);

	public Notice selectOneByNo(SqlSession session, int noticeNo);
	
	public int selectCountNotice(SqlSession session);
	
	public String generatePageNavi(SqlSession session, int currentPage);
}
