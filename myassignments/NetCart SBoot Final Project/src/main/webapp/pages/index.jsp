<!DOCTYPE html>
<html>
<head>
<title>NetCart</title>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container-fluid">
    <div class="row">
        <div class="col-12 p-0">
            <div class="jumbotron min-vh-100 text-center m-0 d-flex flex-column justify-content-center">
  <h1 class="display-4">Hi There,</h1>
  <p class="lead">Choose a Language to Continue</p>
  <hr class="my-4">

	<form action="loadForm" method="post">
		<div class="col-xs-4">

		<select name="language" class="form-control w-25 mx-auto">
			<option value="en">English(Default)</option>
			<option value="ta">Tamil</option>
			<option value="hi">Hindi</option>
		</select>
		</div><br>
	<input type="submit" value="Select Language" class="btn btn-outline-dark">
	</form>
	 
</div>
</div>
</div>
</div>
</body>
</html>