<%@page import = "java.sql.*" %>
<%
    String flight_id = request.getParameter("flightid");
    
   DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
   String url="jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu"; 
   Connection conn=DriverManager.getConnection(url,"sdasarap","xeegloso");
   Statement stmt=conn.createStatement();
   ResultSet rs=stmt.executeQuery("SELECT F.DEPT_DATETIME, F.ARRIVAL_DATETIME, F.FLIGHT_MILES, FT.TRIP_ID, T.TRIP_MILES FROM FLIGHTS F, FLIGHTS_TRIPS FT, TRIPS T WHERE F.FLIGHT_ID = FT.FLIGHT_ID AND FT.TRIP_ID = T.TRIP_ID AND F.FLIGHT_ID ='"+flight_id+"'");
   String output="";
   while(rs.next())
   {
     output+=rs.getObject(1)+","+rs.getObject(2)+","+rs.getObject(3)+","+rs.getObject(4)+","+rs.getObject(5)+"#";
   }
   out.print(output);
   conn.close();
%>

