
<H2>context-params</H2>
<ul>
<%
java.util.Enumeration names = application.getInitParameterNames();
while (names.hasMoreElements()){
  String name = (String)names.nextElement();
  out.println("<li>" + name + " - " + application.getInitParameter(name) + "</li>");
}
%>
</ul>