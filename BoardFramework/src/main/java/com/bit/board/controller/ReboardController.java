package com.bit.board.controller;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.bit.board.model.ReboardDto;
import com.bit.board.service.ReboardService;
import com.bit.common.service.CommonService;
import com.bit.member.model.MemberDto;
import com.bit.util.PageNavigation;

@Controller
@RequestMapping("/reboard")
public class ReboardController {

  private ReboardService reboardService;
  @Autowired
  private CommonService commonService;

  public ReboardController(ReboardService reboardService, CommonService commonService) {
    this.reboardService = reboardService;
  }

  @RequestMapping("list.bit")
  public ModelAndView list(@RequestParam HashMap<String, String> param) {
    ModelAndView mav = new ModelAndView();
    List<ReboardDto> list = reboardService.listArticle(param);
    PageNavigation navigation = commonService.makePageNavigation(param);
    navigation.setRoot("/board");
    navigation.makeNavigator();
    mav.setViewName("reboard/list");
    mav.addObject("articlelist", list);
    mav.addObject("navigator", navigation);
    return mav;
  }

  @GetMapping("write.bit")
  public void write(@RequestParam HashMap<String, Object> param) {}

  @PostMapping("write.bit")
  public String write(ReboardDto reboardDto, HashMap<String, Object> param, HttpSession session,
      Model model) {
    MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");

    if (memberDto != null) {
//      reboardDto.setId(memberDto.getId());
//      reboardDto.setName(memberDto.getName());
//      reboardDto.setEmail(memberDto.getEmail());
      int seq = reboardService.writeArticle(reboardDto);

      if (seq != 0) {
        model.addAttribute("wseq", seq);
      } else {
        model.addAttribute("errorMsg", "글 작성 실패");
      }
    } else {
      model.addAttribute("errorMsg", "회원 전용 게시판이다. 로그인 해라.");

    }

    return "reboard/writeOk";
  }

  @GetMapping("view.bit")
  public String view(@RequestParam int seq, HttpSession session, Model model) {
    MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
    if (memberDto != null) {
      ReboardDto reboardDto = reboardService.viewArticle(seq);
      model.addAttribute("article", reboardDto);
    }
    return "reboard/view";
  }

  @GetMapping("/reply.bit")
  public String reply(@RequestParam int seq, Model model, HttpSession session) {

    MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
    if (memberDto != null) {
      ReboardDto reboardDto = reboardService.viewArticle(seq);
      model.addAttribute("article", reboardDto);
    }
    return "reboard/reply";
  }
  
  @PostMapping("reply.bit")
  public String reply(HashMap<String, Object> param, ReboardDto reboardDto, HttpSession session,
      Model model) {
    MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
    if (memberDto != null) {
//      reboardDto.setId(memberDto.getId());
//      reboardDto.setName(memberDto.getName());
//      reboardDto.setEmail(memberDto.getEmail());
      int seq = reboardService.replyArticle(reboardDto);

      if (seq != 0) {
        model.addAttribute("wseq", seq);
      } else {
        model.addAttribute("errorMsg", "글 작성 실패");
      }
    } else {
      model.addAttribute("errorMsg", "회원 전용 게시판이다. 로그인 해라.");

    }

    return "reboard/writeOk";
  }
}
