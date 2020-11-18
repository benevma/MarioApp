<html>
<head>
<link href="<%= request.getContextPath() %>/css/customizations.css" type="text/css" rel="stylesheet" media="screen,projection">
</head>
<body class="secondaryColor_L30">
	<h2>SELECT YOUR TXT FILES AND SUBMIT THEM FOR ANALYZING!</h2>

	<form action="/MarioApp/APIServlet" method="post" enctype = "multipart/form-data">
		<table>
			<%for(int i = 1;i<11;i++){ %>
			 <tr>
			 	<td>File <%=i %> :
			 	</td>
			 	<td><input type="file" name="doc<%=i %>" accept=".txt">
			 	</td>
			 </tr>
			 <tr>
			 	<td><br></br></td>
			 	<td><br></br></td>
			 </tr>
			<%} %>
			 <tr>
			 	<td>Update file :</td>
			 	<td><input type="checkbox" name="update" ></td>
			 </tr>
			 <tr>
			 	<td><br></br></td>
			 	<td><br></br></td>
			 </tr>
			 <tr>
			 	<td><input type="submit" value="SEND FILES"></td>
			 	<td></td>
			 </tr>
		</table>
		  
		
	</form>

</body>
</html>
