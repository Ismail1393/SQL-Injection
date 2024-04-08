
   1| package org.owasp.benchmark.testcode;
   2| 
   3| import java.io.IOException;
   4| import javax.servlet.ServletException;
   5| import javax.servlet.annotation.WebServlet;
   6| import javax.servlet.http.HttpServlet;
   7| import javax.servlet.http.HttpServletRequest;
   8| import javax.servlet.http.HttpServletResponse;
   9| 
  10| @WebServlet(value = "/sqli-03/testFile_01805")
  11| public class testFile_01805 extends HttpServlet {
  12| 
  13|     private static final long serialVersionUID = 1L;
  14| 
  15|     @Override
  16|     public void doGet(HttpServletRequest request, HttpServletResponse response)
  17|             throws ServletException, IOException {
  18|         doPost(request, response);
  19|     }
  20| 
  21|     @Override
  22|     public void doPost(HttpServletRequest request, HttpServletResponse response)
  23|             throws ServletException, IOException {
  24|         response.setContentType("text/html;charset=UTF-8");
  25| 
  26|         org.owasp.benchmark.helpers.SeparateClassRequest scr =
  27|                 new org.owasp.benchmark.helpers.SeparateClassRequest(request);
  28|         String param = scr.getTheValue("testFile_01805");
  29| 
  30|         String bar = new Test().doSomething(request, param);
  31| 
  32|         String sql = "SELECT * from USERS where USERNAME=? and PASSWORD='" + bar + "'";
  33| 
  34|         try {
  35|             java.sql.Connection connection =
  36|                     org.owasp.benchmark.helpers.DatabaseHelper.getSqlConnection();
  37|             java.sql.PreparedStatement statement =
  38|                     connection.prepareStatement(
  39|                             sql,
  40|                             java.sql.ResultSet.TYPE_FORWARD_ONLY,
  41|                             java.sql.ResultSet.CONCUR_READ_ONLY);
  42|             statement.setString(1, "foo");
  43|             statement.execute();
  44|             org.owasp.benchmark.helpers.DatabaseHelper.printResults(statement, sql, response);
  45|         } catch (java.sql.SQLException e) {
  46|             if (org.owasp.benchmark.helpers.DatabaseHelper.hideSQLErrors) {
  47|                 response.getWriter().println("Error processing request.");
  48|                 return;
  49|             } else throw new ServletException(e);
  50|         }
  51|     } // end doPost
  52| 
  53|     private class Test {
  54| 
  55|         public String doSomething(HttpServletRequest request, String param)
  56|                 throws ServletException, IOException {
  57| 
  58|             String bar = "safe!";
  59|             java.util.HashMap<String, Object> map10271 = new java.util.HashMap<String, Object>();
  60|             map10271.put("keyA-10271", "a_Value"); // put some stuff in the collection
  61|             map10271.put("keyB-10271", param); // put it in a collection
  62|             map10271.put("keyC", "another_Value"); // put some stuff in the collection
  63|             bar = (String) map10271.get("keyB-10271"); // get it back out
  64|             bar = (String) map10271.get("keyA-10271"); // get safe value back out
  65| 
  66|             return bar;
  67|         }
  68|     } // end innerclass Test
  69| } // end DataflowThruInnerClass
