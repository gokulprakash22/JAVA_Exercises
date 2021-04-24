<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<title>Invoice List</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<style>

.card {
  padding-left:auto;
  padding-right:auto;
  min-height:150px;
  margin-top:15px;
 box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
 -webkit-transition: .25s box-shadow;
 transition: .25s box-shadow;
}
.card:focus, .card:hover {
 box-shadow: 0 5px 11px 0 rgba(0, 0, 0, 0.18), 0 4px 15px 0 rgba(0, 0, 0, 0.15);
}
.card-inverse .card-img-overlay {
 background-color: rgba(51, 51, 51, 0.85);
 border-color: rgba(51, 51, 51, 0.85);
}
.card-img-top{
  margin-top:10px;
}
.col-xs-12{
  margin-left:auto;
  margin-right:auto;
}
.row{
  margin-left:auto;
  margin-right:auto;
}body {
  background: #eecda3;
  background: -webkit-linear-gradient(to right, #6190E8, #A7BFE8);
  background: linear-gradient(to right,  #6190E8, #A7BFE8);
  min-height: 100vh;
}
</style>
<body>
<div class="container">
<div class="text-right p-5"><a href="managementhome"><button class="btn btn-dark">Home</button></a></div> 
<p class="display-4 text-center">Invoice List of Customer No : </p>
<c:forEach var="invoice" items="${invoicelist}">
<form action="invoicebyinvno" method="post">
  <div class="row">
    <div class="col-xs-12 col-md-6 col-lg-12">
      <div class="card">
      
      <div class="card-block">
        <h4 class="card-title pt-5 pl-5">Invoice No : ${invoice.invno}</h4><input type="hidden" name="invno" value=<c:out value='${invoice.invno}' /> />
        <p class="card-text text-left pl-5">Customer No: ${invoice.customerno}</p>
        <p class="card-text pb-1 pl-5">Invoice Date : ${invoice.invdate}</p>
        <p class="card-text text-right mb-2"><button type="submit" class="btn btn-outline-secondary mr-3">Generate Invoice</button></p>

      </div>
     
    </div>
    </div>
</div>
</form>
</c:forEach>
</div>
</body>
</html>