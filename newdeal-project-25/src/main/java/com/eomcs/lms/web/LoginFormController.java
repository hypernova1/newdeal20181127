package com.eomcs.lms.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;

@Controller("/auth/form")
public class LoginFormController implements PageController {
  

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    response.setContentType("text/html;charset=utf-8");
    return "/auth/form.jsp";
  }
  
}