package kr.co.blingbling.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.co.blingbling.member.domain.Member;
import kr.co.blingbling.member.service.MemberService;

@Controller
@SessionAttributes("memberId")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@RequestMapping(value="/member/login.do", method=RequestMethod.GET)
	public String showloginForm() {
		return "member/login";
	}
	
	@RequestMapping(value="/member/login.do", produces="text/html;charset=UTF-8;", method=RequestMethod.POST)
	public @ResponseBody String memberLogin(
			@RequestParam("userId") String memberId
			, @RequestParam("userPw") String memberPw
			, Model model
			) {
		Member member = new Member(memberId, memberPw);
		Member mOne = service.selectCheckLogin(member);
		if(mOne != null) {
			model.addAttribute("memberId", memberId);
			return "<script>alert('"+memberId+"�� ȯ���մϴ�.'); location.href='/index.jsp';</script>";
		}else {
			return "<script>alert('ȸ�� ������ �������� �ʽ��ϴ�.'); history.back();</script>";
		}
	}
	
	@RequestMapping(value="/member/logout.do", method=RequestMethod.GET)
	public String memberLogout(
			SessionStatus session
			) {
		if(session != null) {
			session.setComplete();
		}
		return "redirect:/index.jsp";
	}
	
	@RequestMapping(value="/member/register.do", method=RequestMethod.GET)
	public String showRegisterForm() {
		return "member/register";
	}
	
	@RequestMapping(value="/member/register.do", produces="text/html;charset=UTF-8;",  method=RequestMethod.POST)
	public @ResponseBody String registerMember(
			@RequestParam("userId") String memberId
			, @RequestParam("userPw") String memberPw
			, @RequestParam("userName") String memberName
			, @RequestParam("userPost") String memberPostCode
			, @RequestParam("userPostAddr1") String memberAddr1
			, @RequestParam("userPostAddr2") String memberAddr2
			, @RequestParam("tel1") String memberPhone1
			, @RequestParam("tel2") String memberPhone2
			, @RequestParam("userEmail") String memberEmail
			, Model model
			) {
		String memberPhone = memberPhone1 + memberPhone2;
		Member member = new Member(memberId, memberPw, memberName, memberPostCode, memberAddr1, memberAddr2, memberPhone, memberEmail);
		int result = service.insertMember(member);
		if(result > 0) {
			return "<script>alert('������ �Ϸ�Ǿ����ϴ�. �α������ּ���.'); location.href='/member/login.do'</script>";
		}else {
			return "<script>alert('ȸ������ ����'); history.back();</script>";
		}
	}
	
	@RequestMapping(value="/member/myInfo.do", method=RequestMethod.GET)
	public String showMyInfo(
			@RequestParam("member-id") String memberId
			, Model model
			) {
		Member member = service.selectOneById(memberId);
		model.addAttribute("member", member);
		return "member/modifyInfo";
	}
	
	@RequestMapping(value="/member/update.do", produces="text/html;charset=UTF-8;", method=RequestMethod.POST)
	public @ResponseBody String modifyMyInfo(
			@RequestParam("userId") String memberId
			, @RequestParam("userPw") String memberPw
			, @RequestParam("userName") String memberName
			, @RequestParam("userPost") String memberPostCode
			, @RequestParam("userPostAddr1") String memberAddr1
			, @RequestParam("userPostAddr2") String memberAddr2
			, @RequestParam("tel1") String memberPhone1
			, @RequestParam("tel2") String memberPhone2
			, @RequestParam("userEmail") String memberEmail
			) {
		String memberPhone = memberPhone1 + memberPhone2;
		Member member = new Member(memberId, memberPw, memberName, memberPostCode, memberAddr1, memberAddr2, memberPhone, memberEmail);
		int result = service.updateMember(member);
		if(result > 0) {
			return "<script>alert('������ �����Ǿ����ϴ�.'); location.href='/index.jsp';</script>";
		}else {
			return "<script>alert('ȸ�������� ������ �� �����ϴ�.'); history.back();</script>";
		}
	}
	
	@RequestMapping(value="/member/delete.do", produces="text/html;charset=UTF-8;", method=RequestMethod.GET)
	public @ResponseBody String removeMember(
			@RequestParam("memberId") String memberId
			) {
		int result = service.deleteMember(memberId);
		if(result > 0) {
			return "<script>alert('���������� Ż��Ǿ����ϴ�. �̿����ּż� �����մϴ�.'); location.href='/index.jsp'</script>";
		}else {
			return "<script>alert('ȸ��Ż�� �����Ͽ����ϴ�.'); history.back();</script>";
		}
	}
}
