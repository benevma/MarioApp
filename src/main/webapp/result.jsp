<%@page import="api.restbean.OutputBean"%>
<html>

<jsp:useBean id="result" scope="session" class="api.restbean.OutputExpertBean"></jsp:useBean>

<head>
	<link href="<%= request.getContextPath() %>/css/customizations.css" type="text/css" rel="stylesheet" media="screen,projection">
</head>

<body class="secondaryColor_L30">
<h2>This is the result of file analyzing!</h2>

			<% if(result.getValue()!=null && result.getValue().size()>0
				){ %>
				<table>
					<% for(OutputBean s : result.getValue()){%>
						<tr>
							<td>FILE NAME</td>
							<td><%=s.getFilename() %></td>
						</tr>
						<tr>
							<td>ANALYZE RESPONSE</td>
							<td><%=s.getAnalyze() %></td>
						</tr>
						<tr>
							<td>CATEGORIZE RESPONSE</td>
							<td><%=s.getCategorize() %></td>
						</tr>
						<tr>
							<td><br></br></td>
							<td><br></br></td>
						</tr>
					<% }%>
				</table>	
			<%}%>
			
		
		
</body>
</html>
