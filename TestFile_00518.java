
   1| package org.owasp.benchmark.testcode;
   2| 
   3| import java.io.IOException;
   4| import javax.servlet.ServletException;
   5| import javax.servlet.annotation.WebServlet;
   6| import javax.servlet.http.HttpServlet;
   7| import javax.servlet.http.HttpServletRequest;
   8| import javax.servlet.http.HttpServletResponse;
   9| 
  10| @WebServlet(value = "/sqli-01/testFile_00518")
  11| public class testFile_00518 extends HttpServlet {
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
  26|         java.util.Map<String, String[]> map = request.getParameterMap();
  27|         String param = "";
  28|         if (!map.isEmpty()) {
  29|             String[] values = map.get("testFile_00518");
  30|             if (values != null) param = values[0];
  31|         }
  32| 
  33|         org.owasp.benchmark.helpers.ThingInterface thing =
  34|                 org.owasp.benchmark.helpers.ThingFactory.createThing();
  35|         String bar = thing.doSomething(param);
  36| 
  37|         String sql = "INSERT INTO users (username, password) VALUES ('foo','" + bar + "')";
  38| 
  39|         try {
  40|             java.sql.Statement statement =
  41|                     org.owasp.benchmark.helpers.DatabaseHelper.getSqlStatement();
  42|             int count = statement.executeUpdate(sql, new int[] {1, 2});
  43|             org.owasp.benchmark.helpers.DatabaseHelper.outputUpdateComplete(sql, response);
  44|         } catch (java.sql.SQLException e) {
  45|             if (org.owasp.benchmark.helpers.DatabaseHelper.hideSQLErrors) {
  46|                 response.getWriter().println("Error processing request.");
  47|                 return;
  48|             } else throw new ServletException(e);
  49|         }
  50|     }
  51| }
