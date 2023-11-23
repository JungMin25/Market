package com.vam.controller;


import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vam.VO.KakaoDTO;
import com.vam.VO.MemberVO;
import com.vam.service.KakaoService;
import com.vam.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	MemberService memberservice;
	
	@Autowired
	private KakaoService kakaoService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private HttpSession session;
	
	
	
	// 회원가입 버튼 클릭 시
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public void memberCheck() throws Exception {
		logger.info("memberCheck 진입");
	}
	// 다음 클릭 시
		@RequestMapping(value = "/join", method = RequestMethod.GET)
		public void memberJoin() throws Exception {
			logger.info("memberJoin 진입");
		}
		
	//회원가입
		@RequestMapping(value="/join", method = RequestMethod.POST)
		public String joinPOST(MemberVO mvo) throws Exception {
			
			logger.info("join 진입");
			
			// 회원가입 서비스 실행
			memberservice.memberJoin(mvo);
			
			logger.info("join service 성공");
			
			return "redirect:/main";
		}
		
	// 로그인 버튼 클릭 시
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void memberLogin() throws Exception {
		logger.info("memberLogin 진입");
	}
	
	//이메일 체크 팝업창
	@RequestMapping(value = "/EMCheckPopUp", method = RequestMethod.GET)
	public void memberLogin2() throws Exception {
		logger.info("memberLogin 진입");
	}
	
	
	@RequestMapping(value = "/PNCheckPopup", method = RequestMethod.GET)
	public void memberPnCheck() throws Exception {
		logger.info("memberPhoneCheck 진입");
	}
	
	//아이디 중복 검사
	@RequestMapping(value = "/idChk", method = RequestMethod.POST)
	@ResponseBody
	public String IdChkPOST(String id) throws Exception {
		
		logger.info("idChk() 진입");
		
		int result = memberservice.idCheck(id);
		
		logger.info("결과값 = " + result);
		
		if(result != 0 ) {
			return "fail"; //중복 아이디 존재
		} else {
			return "success";
		}
	}
	
	//이메일 인증
	@RequestMapping(value="/mailCheck", method=RequestMethod.GET)
	@ResponseBody
	public String mailCheckGET(String email) throws Exception{
		
		logger.info("이메일 데이터 전송 확인");
		logger.info("인증번호 : " + email);
		
		//인증번호 난수 생성
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;
		logger.info("인증번호 " + checkNum);
		
		//이메일 보내기
		String setFrom = "moonwg09@naver.com";
		String toMail = email;
		String title = "회원가입 인증 이메일 입니다.";
		String content = 
				"홈페이지를 방문해주셔서 감사합니다." +
				"<br><br>" +
				"인증 번호는 " + checkNum + "입니다." +
				"<br>" + 
				"해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
		
		try {
			
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content,true);
			mailSender.send(message);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String num = Integer.toString(checkNum);
		
		return num;
	}
	// 휴대폰 인증
	@RequestMapping(value="/memberPhoneCheck")
	@ResponseBody
	public String sendSMS(String memberPhone) throws Exception{
		Random rand  = new Random();
        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }

        System.out.println("수신자 번호 : " + memberPhone);
        System.out.println("인증번호 : " + numStr);
        memberservice.sendPhoneNumber(memberPhone,numStr);
        
        
        return numStr;
	}
	
	// 로그인
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String loginPOST(HttpServletRequest request, MemberVO mvo, RedirectAttributes rttr) throws Exception{
		
//		System.out.println("login 메서드 진입");
//		System.out.println("전달된 데이터 : " + mvo);
		
		HttpSession session = request.getSession();
		MemberVO lvo = memberservice.memberLogin(mvo);
		
		if(lvo == null) {
			
			int result = 0;
			rttr.addFlashAttribute("result", result);
			return "redirect:/member/login";
		}
		
		session.setAttribute("member", lvo);
		
		return "redirect:/main";
	}
	
	@RequestMapping(value="/kakao", method=RequestMethod.GET)
	public String kakaoLogin(@RequestParam(value = "code", required = false) String code, Model model) throws Exception{
	
		System.out.println("#########" + code);
        String access_Token = kakaoService.getAccessToken(code);
        KakaoDTO userInfo = kakaoService.getUserInfo(access_Token);
     
        session.invalidate();
        session.setAttribute("kakaoN", userInfo.getK_name());
        session.setAttribute("kakaoE", userInfo.getK_email());
     
       
        return "redirect:/main";
    }
	
	
	
		

}
