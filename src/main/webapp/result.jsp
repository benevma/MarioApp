<%@page import="api.restbean.ChartBean"%>
<html>

<jsp:useBean id="result" scope="session" class="java.util.ArrayList"></jsp:useBean>

<head>
	<link href="<%= request.getContextPath() %>/css/customizations.css" type="text/css" rel="stylesheet" media="screen,projection">
	
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Analyze', 'Types'],
          
          <%
           if(result!=null && result.size()>0){
           		for(int i = 0;i<result.size();i++){
           			ChartBean chart = (ChartBean)result.get(i);%>
           			['<%=chart.getType()%>',     <%=chart.getCount()%>] <%if(i<result.size()){%>,<%}%> 
           		<%
           		}%>]);<%
          }
           else{%>
		          ['Work',     11],
		          ['Eat',      2],
		          ['Commute',  2],
		          ['Watch TV', 2],
		          ['Sleep',    7]
		 ]);
		       
		<%}%>
        var options = {
          title: 'Categories Chart',
          is3D: true,
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
        chart.draw(data, options);
      }
    </script>
    
</head>

<body class="secondaryColor_L30">
<h2>This is the result of file analyzing!</h2>
		<br></br>
		
    <h3>Categories Chart</h3>
	<br></br>
    <div id="piechart_3d" style="width: 900px; height: 500px;"></div>
    
    <br></br>
    <h3>All results!</h3>
     <br></br>
			<% if(result!=null && result.size()>0){ %>
				<table>
					<% for(Object x : result){
						ChartBean s = (ChartBean) x;
						%>
						<tr>
							<td><h4>Category</h4></td>
							<td><b><%=s.getType() %></b></td>
							<td><h4>Counts</h4></td>
							<td><b><%=s.getCount() %></b></td>
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
