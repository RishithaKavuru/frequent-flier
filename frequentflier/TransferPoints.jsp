<%@page import = "java.sql.*" %>
<%
    String sourceid = request.getParameter("spid");
    String destinationid = request.getParameter("dpid");
    String amount = request.getParameter("npoints");

   DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
   String url="jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu"; 
   Connection conn=DriverManager.getConnection(url,"sdasarap","xeegloso");
   Statement stmt=conn.createStatement();
   String query1="UPDATE POINT_ACCOUNTS SET TOTAL_POINTS=TOTAL_POINTS+'"+amount+"' WHERE PASSID='"+destinationid+"'";
   String query2="UPDATE POINT_ACCOUNTS SET TOTAL_POINTS=TOTAL_POINTS-'"+amount+"' WHERE PASSID='"+sourceid+"'";
   
   int aff_query1 = stmt.executeUpdate(query1);
   int aff_query2 = stmt.executeUpdate(query2);
   
   int total_aff = aff_query1 + aff_query2;
   
   
   String output="";
   if(total_aff==2)
   {
    output = "The Transfer was SUCCESSFUL";
   } 
   else
   {
    output = "The Transfer was UNSUCCESSFUL";
   }
   out.print(output);
   conn.close();
%>


