<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <!DOCTYPE html>
 <html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  <head>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.min.js"></script>
  <script src="https://code.jquery.com/jquery-1.12.3.min.js"></script>


<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/0.9.0rc1/jspdf.min.js"></script>
<script type="text/javascript">
 function createpdf(){
	 window.print();
 }
</script>
  </head>
  <style>
table {
  width:100%;
}
td {
  width: 25% ;
}
td,th {
  text-align:center;
}
@media print {
    #show_button {
        display :  none;
    }
    #logout {
        display :  none;
    }
</style>
<body>
 <nav class="large-device navbar navbar-dark bg-primary justify-content-between pb-3">
        	<span class="navbar-brand" href="#" style="text-color:#ffffff"> Management </span>
           <div class="text-right"><a href="managementhome"><button class="btn btn-dark btn-sm">Home</button></a>
            <a href="/logout"><button type="button" class="btn btn-md btn-danger btn-sm my-0" >Logout</button></a>
            </div>
       
 </nav>
<p class="display-4 text-center">Stock Report</p> 
<!-- <div class="text-right"><a href="managementhome"><button class="btn btn-dark">Home</button></a></div> 
 --><div class="container">
<table class="table" id="styledTable">  
 <thead class="thead-light">
    <tr>
      <th scope="col">Image</th>
      <th scope="col">Item No</th>
      <th scope="col">Item Name</th>
      <th class="col">Item Stock</th>
    </tr>
  </thead>
   <c:forEach var="item" items="${itemlist}">   
   <tr class="table-info">  
   <td><img class="img-fluid w-25 rounded-circle" src=<c:out value='${item.image}'/>></td>  
   <td>${item.itemno}</td>  
   <td>${item.itemdescription}</td>  
   <td>${item.stock}</td>  
   </tr> 
   </c:forEach>  
   </table>  
   </div>
   <div class="text-center">
  <button id="show_button" onclick="createpdf()" class="btn btn-dark">Save as PDF</button></div><br>
  </body>
</html>


  