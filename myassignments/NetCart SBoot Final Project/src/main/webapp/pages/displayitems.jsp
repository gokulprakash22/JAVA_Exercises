<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>
 <!DOCTYPE html>
 <html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>  
<head>
<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.19.1/css/mdb.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Josefin+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.19.1/css/mdb.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Josefin+Sans&display=swap" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

 </head>
  <style>



#myInput {
  background-image: url('/css/searchicon.png');
  background-position: 10px 10px;
  background-repeat: no-repeat;
  width: 100%;
  font-size: 16px;
  padding: 12px 20px 12px 40px;
  border: 1px solid #ddd;
  margin-bottom: 12px;
}

 body{
        font-family: 'Josefin Sans', 'sans-serif';
    }
td {
  width: 20% ;
}
</style>
<body>
 <nav class="large-device navbar navbar-dark bg-primary justify-content-between pb-3">
        <span class="navbar-brand" href="#" style="text-color:#ffffff"> Store Administration </span>
         <form class="form-inline my-1">
         	<a href="#" data-toggle="modal" data-target="#myModal" class="btn btn-md text-white btn-sm px-2 my-0" type="submit">Add New Item</a>
            <a href="/logout"><button type="button" class="btn btn-md btn-red btn-sm my-0" >Logout</button></a>
        </form>
 </nav>
<div class="container">
 
<p class="h2 text-center pt-5">LIST OF ITEMS</p>
<input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for Items.." title="Type Item Name">
<br>
<table class="table table-striped" id="myTable">  
 <thead class="thead-light">
    <tr>
      <th scope="col">Image</th>
      <th scope="col">Name</th>
      <th scope="col">Category</th>
      <th scope="col">Price</th>
      <th scope="col">Unit</th>
      <th scope="col">Stock</th>
      <th class="col" colspan="3">Action</th>
    </tr>
  </thead>
   <c:forEach var="item" items="${itemlist}">   
   <tr>  
   <td><img class="img-fluid" src=<c:out value='${item.image}'/>></td>
   
   <td>${item.itemdescription}</td> 
   <td>${item.category}</td>  
   <td>${item.itemprice}</td>  
   <td>${item.itemunit}</td>  
   <td>${item.stock}</td>  
     
   <td><a href="#editModal" class="edit px-2 float-right" data-toggle="modal" data-target="#editModal"><i class="fas fa-edit fa-2x text-success"></i> <input type="hidden" id="id" value=<c:out value='${item.itemno}'/>>  </a>                  
    <td>
   <td><a href="deleteitem/${item.itemno}"><i class="fas fa-trash-alt fa-2x text-danger"></i></a></td> 
    
   </tr>  
   </c:forEach>  
   </table>  
 </div>
   
   
   	<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModal" aria-hidden="true">
                <div class="modal-dialog cascading-modal modal-avatar modal-md" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                    <img src="https://static.vecteezy.com/system/resources/previews/000/496/455/non_2x/vector-add-to-cart-icon-design.jpg" alt="avatar" class="rounded-circle img-responsive">
                    </div>
                    <div class="modal-body text-center mb-1 blue-text">
                    <h5 class="mt-1 mb-2">EDIT ITEMS</h5>
                    <form method="post" action="itemUpdate">
                   <div class="md-form ml-0 mr-0">
	                    <input type="text" id="des" name="itemdesc" required value="xyz" class="form-control ml-0">
	                    <label for="itemdesc" class="ml-0">Item Name</label>
	                </div>
	                <div class="md-form ml-0 mr-0">
	                    <input type="text" id="ima" name="imageURL" required value="xyz" class="form-control ml-0">
	                    <label for="imageURL" class="ml-0 ">Item Image URL</label>
	                </div>
	                <input type="hidden" id="id" name="id" value="">
	                <div class="md-form ml-0 mr-0">
	                    <input type="text" id="pri" name="price" required value="xyz" class="form-control ml-0">
	                    <label for="price" class="ml-0 ">Price</label>
	                </div>
	                <div class="md-form ml-0 mr-0">
	                    <input type="text" id="uni" name="unit" required class="form-control ml-0">
	                    <label for="unit" class="ml-0">Units</label>
	                </div>
	          		<div class="md-form ml-0 mr-0">
	                    <input type="text" id="cat" name="category" required value="xyz" class="form-control ml-0">
	                    <label for="category" class="ml-0">Category</label>
	                </div>
	                <div class="md-form ml-0 mr-0">
	                    <input type="text" id="sto" name="stock" required value="xyz" class="form-control ml-0">
	                    <label for="stock" class="ml-0">Stock</label>
	                </div>
                <div class="text-center mt-4">
                    <button style="border-radius: 999px;" class="btn btn-blue mt-1">Update<i class="fas fa-sign-in ml-1"></i></button>
                </div>
                </form>
                    </div>
                </div>
                </div>
        </div>
   	
   
   	
   
   
   
   
   <div class="modal fade" id="myModal" role="dialog">
   <div class="modal-dialog cascading-modal modal-avatar modal-md" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                    <img src="https://static.vecteezy.com/system/resources/previews/000/496/455/non_2x/vector-add-to-cart-icon-design.jpg" alt="avatar" class="rounded-circle img-responsive">
                    </div>
                    <div class="modal-body text-center mb-1 blue-text">
                    <h5 class="mt-1 mb-2">ADD ITEMS</h5>
                   <spring:form modelAttribute="item" method="post" action="itemAdd">
	                <div class="md-form ml-0 mr-0">
	                     <spring:input path="itemno" class="form-control"  type="hidden" />
	                </div>
	                <div class="md-form ml-0 mr-0">
	                    <spring:input path="itemdescription" class="form-control ml-0" />
	                    <label for="pwd" class="ml-0 ">Item Name</label>
	                </div>
	                <div class="md-form ml-0 mr-0">
	                    <spring:input path="itemprice" class="form-control ml-0" id="priceattr"/>
	                    <label for="pwd" class="ml-0 ">Item Price</label>
	                </div>
	                <div class="md-form ml-0 mr-0">
	                    <spring:input path="itemunit" class="form-control ml-0" />
	                    <label for="pwd" class="ml-0 ">Item Unit</label>
	                </div>
	                <div class="md-form ml-0 mr-0">
	                    <spring:input path="image" class="form-control ml-0" />
	                    <label for="pwd" class="ml-0 ">Item Image URL</label>
	                </div>
	                <div class="md-form ml-0 mr-0">
	                    <spring:input path="category" class="form-control ml-0" />
	                    <label for="pwd" class="ml-0 ">Item Category</label>
	                </div>
	                <div class="md-form ml-0 mr-0">
	                    <spring:input path="stock" class="form-control ml-0" id="stockattr"/>
	                    <label for="pwd" class="ml-0 ">Item Stock</label>
	                </div>
	                
	                
                <div class="text-center mt-4">
                    <button style="border-radius:999px;" class="btn btn-primary">Add Item<i class="fas fa-sign-in ml-1"></i></button>
                </div>
                </spring:form>
                    </div>
                </div>
                </div>
   </div>
   
   
   
  
 
 
<script>
    $(document).ready(function(){
            $(".container .edit").on("click",function(){
                var id=$(this).parent().find("#id").val();
                console.log("Hello");
                console.log(id);
                $.ajax({
                    type: "POST",
                    url:"getItemDetails",
                    data:{"id": id },
                    success:function(item){
                        	$("#editModal #id").val(item.itemno);
	                        $("#editModal #des").val(item.itemdescription);
	                        $("#editModal #pri").val(item.itemprice);
	                        $("#editModal #ima").val(item.image);
	                        $("#editModal #uni").val(item.itemunit);
	                        $("#editModal #cat").val(item.category);
	                        $("#editModal #sto").val(item.stock);
                    }
                });
            });
        });
    
    $("#stockattr").attr({
        "type" : "number",
    });
    $("#priceattr").attr({
        "type" : "number",
    });
    </script>
    <script>
	function myFunction() {
		  var input, filter, table, tr, td, i, txtValue;
		  input = document.getElementById("myInput");
		  filter = input.value.toUpperCase();
		  table = document.getElementById("myTable");
		  tr = table.getElementsByTagName("tr");
		  for (i = 0; i < tr.length; i++) {
		    td = tr[i].getElementsByTagName("td")[1];
		    if (td) {
		      txtValue = td.textContent || td.innerText;
		      if (txtValue.toUpperCase().indexOf(filter) > -1) {
		        tr[i].style.display = "";
		      } else {
		        tr[i].style.display = "none";
		      }
		    }       
		  }
		}
	</script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/umd/popper.min.js"></script>
   <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.19.1/js/mdb.min.js"></script>


    
   </body>
   
</html>