<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">
	function mySubmit() {

		var subject = document.getElementById("subject");
		var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		var recipientAddress = document.getElementById("recipientAddress");
		

		if (reg.test(recipientAddress.value) == false) {
			alert('Invalid Email Address');
			return false;
		}
		
		if (subject.value == "") {

			document.getElementById("error").innerHTML = "Subject can't be empty";
			subject.focus();

			return false;
		}

		return true;

	}
</script>

</head>
<body style="background-color: aliceblue">
	<h3 id="msg" style="color: red">${param.msg}</h3>

	<div>
		<form action="./SendEmailController" method="post"
			style="margin-top: 5%; border: 10%" onsubmit="return mySubmit() ">
			<table align="center">
				<tr>
					<td>Enter-Recipient Address &nbsp</td>
					<td><input type="text" name="recipientAddress"
						id="recipientAddress" placeholder="F:abc@gmail.com"></td>
				</tr>
				<tr>
					<td>Subject &nbsp</td>
					<td><input type="text" name="subject" id="subject"
						placeholder="Subject"></td>
					<td>
						<div id="error"></div>
					</td>
				</tr>

				<tr>
					<td>Content &nbsp</td>
					<td><textarea rows="10" cols="50" name="textToSend"
							placeholder="Enter Your Message Here."></textarea></td>
				</tr>




				<tr>
					<td></td>
					<td align="right"><input type="submit" value="sendEmail"></td>
				</tr>

			</table>
		</form>
	</div>


</body>
</html>
