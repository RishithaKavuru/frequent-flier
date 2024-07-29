<%@page import = "java.sql.*" %>
<%
    String award_id = request.getParameter("awardid");
    String passengerid = request.getParameter("pid");

   DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
   String url="jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu"; 
   Connection conn=DriverManager.getConnection(url,"sdasarap","xeegloso");
   Statement stmt=conn.createStatement();
   ResultSet rs=stmt.executeQuery("SELECT A.A_DESCRIPTION, A.POINTS_REQUIRED, RH.REDEMPTION_DATE, EX.CENTER_NAME FROM REDEMPTION_HISTORY RH, AWARDS A, EXCHGCENTERS EX WHERE RH.AWARD_ID = A.AWARD_ID AND RH.CENTER_ID = EX.CENTER_ID AND RH.PASSID = '"+passengerid+"'AND RH.AWARD_ID = '"+award_id+"'");
   String output="";
   while(rs.next())
   {
     output+=rs.getObject(1)+","+rs.getObject(2)+","+rs.getObject(3)+","+rs.getObject(4)+"#";
   }
   out.print(output);
   conn.close();
%>

