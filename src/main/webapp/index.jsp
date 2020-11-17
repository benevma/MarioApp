<html>
<head>
<link href="<%= request.getContextPath() %>/css/customizations.css" type="text/css" rel="stylesheet" media="screen,projection">
</head>
<body class="secondaryColor_L30">
	<h2>SELECT YOUR TXT FILES AND SUBMIT THEM FOR ANALYZING!</h2>

	<form action="/MarioApp/APIServlet" method="post" enctype = "multipart/form-data">
		<ul>
			<li>
				File 1 : <input type="file" name="doc1" 
				  accept=".txt">
			</li>
			<li>
				File 2 : <input type="file" name="doc2" 
				  accept=".txt">
			</li>
			<li>
				File 3 : <input type="file" name="doc3" 
				  accept=".txt">
			</li>
		</ul>
		<br></br>
		 Update file : <input type="checkbox" name="update" >
		<br>
		<br> <input type="submit" value="SEND FILES">
		<br></br>
		
	</form>

</body>
</html>
