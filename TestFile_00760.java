
   1| package org.owasp.benchmark.testcode;
   2| 
   3| import java.io.IOException;
   4| import javax.servlet.ServletException;
   5| import javax.servlet.annotation.WebServlet;
   6| import javax.servlet.http.HttpServlet;
   7| import javax.servlet.http.HttpServletRequest;
   8| import javax.servlet.http.HttpServletResponse;
   9| 
  10| @WebServlet(value = "/sqli-01/testFile_00760")
  11| public class testFile_00760 extends HttpServlet {
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
  26|         String[] values = request.getParameterValues("testFile_00760");
  27|         String param;
  28|         if (values != null && values.length > 0) param = values[0];
  29|         else param = "";
  30| 
  31|         String bar = "safe!";
  32|         java.util.HashMap<String, Object> map18915 = new java.util.HashMap<String, Object>();
  33|         map18915.put("keyA-18915", "a-Value"); // put some stuff in the collection
  34|         map18915.put("keyB-18915", param); // put it in a collection
  35|         map18915.put("keyC", "another-Value"); // put some stuff in the collection
  36|         bar = (String) map18915.get("keyB-18915"); // get it back out
  37| 
  38|         String sql = "{call " + bar + "}";
  39| 
  40|         try {
  41|             java.sql.Connection connection =
  42|                     org.owasp.benchmark.helpers.DatabaseHelper.getSqlConnection();
  43|             java.sql.CallableStatement statement = connection.prepareCall(sql);
  44|             java.sql.ResultSet rs = statement.executeQuery();
  45|             org.owasp.benchmark.helpers.DatabaseHelper.printResults(rs, sql, response);
  46| 
  47|         } catch (java.sql.SQLException e) {
  48|             if (org.owasp.benchmark.helpers.DatabaseHelper.hideSQLErrors) {
  49|                 response.getWriter().println("Error processing request.");
  50|                 return;
  51|             } else throw new ServletException(e);
  52|         }
  53|     }
  54| }
