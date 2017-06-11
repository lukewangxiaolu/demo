package com.say2.action;

import java.io.IOException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.say2.bean.Result;

/**
 * Servlet implementation class Say2Servlet
 */
public class Say2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/say2.jsp").forward(request, response);		
	}
  	
	
  	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String input = request.getParameter("number");
        Result result = new Result();
        result.setInput(input);
        result.setAnswer(Say2(input));
        request.setAttribute("result", result);  
        request.getRequestDispatcher("/say2.jsp").forward(request, response);		
	}
  	
  	protected String Say2(String input) 
    {
        String NumberNames20[] = { "Zero" , "One" , "Two" , "Three" , "Four", 
                                     "Five", "Six", "Seven","Eight", "Nine", 
                                    "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", 
                                    "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" }; 
        String NumberNamesTens[] = { "Twenty" , "Thirty" , "Forty" , "Fifty" , "Sixty", 
                                     "Seventy", "Eighty", "Ninty"}; 
        String NumberNamesBig[] = { "", "", "Thousand", "Million", "Billion", "Trillion"};
        
        String EnglishNumber = "";
        String BigEnglishNumber = "";
        
        long UserNbr = 0;
        long WorkingNbr = 0;
        long Conversion = 0;
        int Remainder1000 = 0;
        int Remainder100 = 0;
        int LoopCount = 1;
        Scanner in = null;
        
       try{
        in = new Scanner(input);   
        UserNbr = in.nextLong();
        in.close();
       }catch(Exception e){
    	   in.close();  
    	   return "invalid input";
       }     
       
       WorkingNbr = UserNbr;            
       while (WorkingNbr > 0)
       {
           Conversion = WorkingNbr % 1000;
           Remainder1000 = (int) (long) Conversion;
           Remainder100 = Remainder1000 % 100;
                             
            if (Remainder100 < 20)
            {
                if (Remainder100 > 0)
                   EnglishNumber = NumberNames20[Remainder100] + EnglishNumber;
                else
                    EnglishNumber = "";
            }
            else if (UserNbr % 10 == 0 )
            {
                EnglishNumber = NumberNamesTens[(Remainder100/10)-2] + EnglishNumber;
            }
            else
            {
                EnglishNumber = NumberNamesTens[(Remainder100/10)-2] + "-" + NumberNames20[Remainder100%10] + EnglishNumber; 
            }

           if (Remainder1000 > 99)
           {
               EnglishNumber = NumberNames20[Remainder1000/100] + "-" + "Hundred" + "-" + EnglishNumber;
           }
                       
            if ((LoopCount > 1) && ((Remainder100 > 0) || (WorkingNbr < 1000)))
            {
                if (BigEnglishNumber.length() > 1)
                    BigEnglishNumber = EnglishNumber + "-" + NumberNamesBig[LoopCount] + "-" + BigEnglishNumber;
                else
                    BigEnglishNumber = EnglishNumber + "-" + NumberNamesBig[LoopCount];
            }
            else
            {
                if (EnglishNumber.length() > 1)
                     BigEnglishNumber = EnglishNumber;
            }
            
            EnglishNumber = "";
            WorkingNbr /= 1000;  
            LoopCount ++;
       }
       
       if (UserNbr == 0)
           return "Zero";
       else
           return BigEnglishNumber;
    }

}
