<%@page import = "java.sql.*" %>
<%
    String passengerid = request.getParameter("pid");
    
   DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
   String url = "jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu"; 
   Connection conn = DriverManager.getConnection(url,"sdasarap","xeegloso");
   Statement stmt = conn.createStatement();
   ResultSet rs = stmt.executeQuery("SELECT P.PASSID, P.PNAME , PA.TOTAL_POINTS  FROM passengers P JOIN Point_Accounts PA ON  P.PASSID=PA.PASSID WHERE P.PASSID='"+passengerid+"'");
   String output="";
   while(rs.next())
   {
     output+=rs.getObject(2)+","+rs.getObject(3);
   }
   out.print(output);
   conn.close();
%>
