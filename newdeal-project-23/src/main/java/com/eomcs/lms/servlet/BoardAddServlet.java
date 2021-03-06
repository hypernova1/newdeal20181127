package com.eomcs.lms.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Member;

@WebServlet("/board/add")
public class BoardAddServlet extends HttpServlet {
  
  private static final long serialVersionUID = 1L;
  private BoardDao boardDao;
  private LessonDao lessonDao; 
  
  @Override
  public void init() throws ServletException {
    ApplicationContext iocContainer =
        (ApplicationContext) this.getServletContext().getAttribute("iocContainer");
    
    this.boardDao = iocContainer.getBean(BoardDao.class);
    this.lessonDao = iocContainer.getBean(LessonDao.class);
  }
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    HttpSession session = request.getSession();
    Member loginUser = (Member) session.getAttribute("loginUser");
    List<Map<String, Object>> lessons = lessonDao.findByParticipantNo(loginUser.getNo());
    
    request.setAttribute("lessons", lessons);
    request.getRequestDispatcher("/board/form.jsp").forward(request, response);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    
    try {

      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      Board board = new Board();
      
      board.setContents(request.getParameter("contents"));
      board.setWriterNo(loginUser.getNo());
      board.setLessonNo(Integer.parseInt(request.getParameter("lessonNo")));
      boardDao.insert(board);
      
      response.sendRedirect("./list");
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServletException(e);
    }
    
  }
}
