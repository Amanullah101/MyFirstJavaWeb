package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import model.Operation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/calc")
public class CalculatorServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello Java Servlet");
		
		//PrintWriter p =resp.getWriter();
		//p.print("Wellcome to Servlet");
		
		
		
	int num1 = Integer.parseInt(req.getParameter("param1"))	;//Those passed from the client to the server
	int num2 = Integer.parseInt(req.getParameter("param2"))	;
	  Operation operation = new Operation();
	  int result = operation.adding(num1, num2);
	  req.setAttribute("total",result);
	  
	  // Session
	  HttpSession session = req.getSession();
	  Integer previousResult = (Integer)session.getAttribute("previous");
	  
	  /*
	   * if(previousResult==null) {
		  session.setAttribute("previous", result);
	  }else {
		  session.setAttribute("previous", previousResult+result);
	  }
	   */
	  
	  
	  Optional<Integer> optionalPreviousValue = Optional.ofNullable(previousResult);
	  if(optionalPreviousValue.isPresent()) {
		  previousResult = optionalPreviousValue.get();
		  previousResult += result;
	  }else {
		  previousResult = result;
		  
	  }
	  session.setAttribute("previous", previousResult+result);
	  RequestDispatcher rd = req.getRequestDispatcher("/result.jsp");
	  rd.forward(req, resp);
	}

}
