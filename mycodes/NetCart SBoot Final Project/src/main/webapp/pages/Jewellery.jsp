<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.19.1/css/mdb.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Josefin+Sans&display=swap" rel="stylesheet">
    <title>Jewels</title>
</head>
<style>
    body{
        font-family: 'Josefin Sans', 'sans-serif';
    }
</style>
<body>

    <nav class="large-device navbar navbar-primary bg-primary justify-content-between pb-3">
        <span class="navbar-brand" href="#"> Welcome </span>
         <form class="form-inline my-1">
            <a href="/logout"><button type="button" class="btn btn-md btn-red btn-sm my-0" >Logout</button></a>
        </form>
    </nav>


    <div class="container pt-5">

        <form action="savejewels" method="post">
      
        <div class="row">
        <c:forEach var="item" items="${items}">
			
			
			<div class="col-lg-6">
                <div class="jumbotron text-center hoverable p-4">

                    <!-- Grid row -->
                    <div class="row">
                  
                      <!-- Grid column -->
                      <div class="col-lg-6 offset-md-1 mx-3 my-3">
                  
                            <!-- Featured image -->
                            <div class="view overlay">
                            <img src="${item.image}" width="200px" height="200px">
                            <a>
                                <div class="mask rgba-white-slight"></div>
                            </a>
                            </div>
                  
                        </div>
        
                      
        
                        <div class="col-lg-4 text-md-left ml-3 mt-3">
                    
                            <p class="h4 mb-4 font-weight-bold">${item.itemdescription}</p>
            
		                    <p class="font-weight-normal h5">Unit ${item.itemunit}</p>
		                    <p class="font-weight-normal h5">Price ${item.itemprice}</p>
		                    <p class="font-weight-normal h5 text-primary pt-5">In Stock ${item.stock}</p>
		                      
                    
                           
                    
                        </div>
                        <div class="col-lg-6 text-md-left ml-3 mt-3">
                       
                         
                            <div class="md-form input-group mb-3">
	                        <div class="input-group-prepend">
	                          <span class="input-group-text md-addon" id="inputGroupMaterial-sizing-default">Quantity</span>
	                        </div>
	                        <input type="number" name="${item.itemno}" min="1" max="${item.stock}" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroupMaterial-sizing-default">
	                       </div>
	                      <input type="checkbox" style="display:none" name="selecteditems" onclick="slect(this.id)" class="btn-check" value="${item.itemno}" id="${item.itemno}" autocomplete="off" />
	                      <label class="btn btn-primary" id="${item.itemno}1" for="${item.itemno}">Select</label>
                      
                    
                        </div>
                    </div>
        
                </div>
               
            </div>
           
        </c:forEach>
        </div>
        <div class="text-right">
            <button type="submit" class="btn btn-md btn-primary">Next</button>
        </div>
        
        </form>
        
    </div>
      <script>
        function slect(clkid){
            var i=document.getElementById(clkid+"1").innerHTML;
            if(i=="Select"){
                document.getElementById(clkid+"1").innerHTML="Selected";
                document.getElementById(clkid+"1").className = "btn btn-success";
            }else{
                document.getElementById(clkid+"1").innerHTML="Select";
                document.getElementById(clkid+"1").className = "btn btn-primary";
            }
        

        }
    </script>

    
    
</body>
</html>