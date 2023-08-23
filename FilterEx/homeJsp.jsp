<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
.button {
	padding: 10px 20px;
	font-size: 16px;
	cursor: pointer;
	margin: 5px;
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 5px;
}

.button:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<center>
		<h1>Home Page</h1>
		<form method="post">
		<div>
			<button class="button" onclick="buttonClicked(1)">self</button>
		</div>
		<div>
			<button class="button" onclick="buttonClicked(2)">cart</button>
		</div>
		<button class="button" onclick="buttonClicked(3)">crud</button>
		</form>
		<%
			String b = response.getHeader("first");

			System.out.println("kettt" + b);
		%>
	</center>
	<script>
		var xhr = new XMLHttpRequest();
		var d= "<%= b%>";
		console.log(d);
		function buttonClicked(n) {
			
			console.log("hellooooooooo");
			window.location.href = "self.jsp?key="+d;
				sessionStorage.setItem("key",d)

			
			alert("Button " + n + " clicked!");
		}
	</script>


</body>
</html>
