<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.say2.bean.Result"  %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Say2</title>
</head>
<body>
<form method="post" action="Say2Servlet">
    <h4>Please enter a number 0-999,999,999,999,999:<br></h4>
    <input type="text" name="number"/>
    <input type="submit" value="Submit"/><br> 
</form>

<%  
      Result result = new Result();  
      result = (Result)request.getAttribute("result");  
      if(result!= null){  
 %>    	  
  	  <h4><%= result.getInput() %> -> <%= result.getAnswer() %></h4>   	    
 <%           
      }  
 %>  

</body>
</html>