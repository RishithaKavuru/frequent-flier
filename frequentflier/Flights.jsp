<%@page import = "java.sql.*" %>
<%
    String passengerid = request.getParameter("pid");
    
   DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
   String url="jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu"; 
   Connection conn=DriverManager.getConnection(url,"sdasarap","xeegloso");
   Statement stmt=conn.createStatement();
   ResultSet rs=stmt.executeQuery("SELECT F.FLIGHT_ID,F.FLIGHT_MILES,F.DESTINATION FROM FLIGHTS F WHERE F.PASSID='"+passengerid+"'");
   String output="";
   while(rs.next())
   {
     output+=rs.getObject(1)+","+rs.getObject(2)+","+rs.getObject(3)+"#";
   }
   out.print(output);
   conn.close();
%>
