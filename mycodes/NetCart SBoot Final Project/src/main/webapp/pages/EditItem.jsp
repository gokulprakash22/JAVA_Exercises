<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Edit Item</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
  <h2>Edit Item</h2>
  <spring:form modelAttribute="itemdetail" method="post" action="/springweb4/itemEdit">
    <div class="form-group">
      <label for="email">Item No:</label>
       <spring:input path="itemno" class="form-control" name="itemno" />
    </div>
    <div class="form-group">
      <label for="pwd">Item Name:</label>
       <spring:input path="itemdescription" class="form-control" />
    </div>
      <div class="form-group">
      <label for="pwd">Item Price:</label>
       <spring:input path="itemprice" class="form-control" />
    </div>
      <div class="form-group">
      <label for="pwd">Item Unit:</label>
       <spring:input path="itemunit" class="form-control" />
    </div>
      <div class="form-group">
      <label for="pwd">Item Category:</label>
       <spring:input path="category" class="form-control" />
    </div>
      <div class="form-group">
      <label for="pwd">Item Image:</label>
       <spring:input path="image" class="form-control" />
    </div>
      <div class="form-group">
      <label for="pwd">Item Stock:</label>
       <spring:input path="stock" class="form-control" />
    </div>
    
    <button type="submit" class="btn btn-primary">Submit</button>
 </spring:form>
</div>
</body>
</html>
