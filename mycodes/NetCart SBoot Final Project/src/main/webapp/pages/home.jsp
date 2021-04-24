<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
a.custom-card,
a.custom-card:hover {
  color: inherit;
}
body{
background-color:#E6F2FF;
}
a{
text-decoration:none;}
.card {
box-shadow: 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
button{
background: none;
	color: inherit;
	border: none;
	padding: 0;
	font: inherit;
	cursor: pointer;
	outline: inherit;
}
</style>
<body>
 <nav class="large-device navbar navbar-primary bg-primary justify-content-between pb-3">
        <span class="navbar-brand" href="#" style="text-color:#ffffff"> Welcome Management</span>
         <form class="form-inline my-1">
            <a href="/logout"><button type="button" class="btn btn-md btn-red btn-sm my-0" >Logout</button></a>
        </form>
   </nav>
<div class="container pt-5">
  <div class="row">
    <div class="col-sm-6">
    
        <div class="card mb-4" style="height:8rem;">
            <div class="card-body">
            <h3 class="card-title text-center">Get Invoice By Invoice no</h3>
                        <form action="invoicebyinvno" method="post">
            <div style="display:flex; flex-direction: row; justify-content: center; align-items: center">

    <input type="number" class="form-control w-50 mr-4" placeholder="Enter Invoice Number" name="invno"/>
    <button type="submit"><span><i class="fas fa-arrow-right fa-2x text-dark"></i></span></button>

	</div>
    </form>
    </div>
    </div>
    </div>
    <div class="col-sm-6">
    
    <div class="card mb-4" style="height:8rem;">
    <div class="card-body">
    <h3 class="card-title text-center">Get Item Stock by Item Number</h3>
    <form action="itemstockybyitemno" method="post">
    <div style="display:flex; flex-direction: row; justify-content: center; align-items: center">
    <input type="number" id="id" class="form-control w-50 mr-4" placeholder="Enter Item Number" name="itemno" required/>
     <a href="#editModal" data-toggle="modal" data-target="#editModal" class="edit"><button class="button button1"><span><i class="fas fa-arrow-right fa-2x text-dark"></i></span></button> </a>

	</div>
    </form>
    </div>
    </div>
    </div>
    <div class="col-sm-6">
	<div class="card mb-4" style="height:8rem;">
            <div class="card-body">
            <h3 class="card-title text-center">Get Invoice By Customer Number</h3>
                        <form action="invoicebycustno" method="post">
            <div style="display:flex; flex-direction: row; justify-content: center; align-items: center">

    <input type="number" class="form-control w-50 mr-4" placeholder="Enter Customer Number" name="custno" required/>
    <button type="submit"><span><i class="fas fa-arrow-right fa-2x text-dark"></i></span></button>

	</div>
    </form>
    </div>
    </div>
</div>
     <div class="col-sm-6">
    
        <div class="card mb-4" style="height:8rem;">
            <div class="card-body">
            <h3 class="card-title text-center">Get Invoice By Invoice Date</h3>
                        <form action="invoicebyinvdate" method="post">
            <div style="display:flex; flex-direction: row; justify-content: center; align-items: center">

		    <input type="date" class="form-control w-50 mr-4" placeholder="Enter Start Date" name="startdate" required />
		    <input type="date" class="form-control w-50 mr-4" placeholder="Enter End Date" name="enddate" required/>
		    <button type="submit"><span><i class="fas fa-arrow-right fa-2x text-dark"></i></span></button>
		
			</div>
		    </form>
    </div>
    </div>
    </div>
</div>
<div class="row">
  <div class="col-sm-12">
    <a href="stockreport" class="custom-card" style="text-decoration:none">
        <div class="card mb-4" style="height:8rem;">
            <div class="card-body">
                <h3 class="card-title text-center mt-3">Stock Report</h3>
            </div>
        </div>
        </a>
    </div>
</div>
  </div>
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModal" aria-hidden="true">
                <div class="modal-dialog cascading-modal modal-avatar modal-md" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                    <img src="https://static.vecteezy.com/system/resources/previews/000/496/455/non_2x/vector-add-to-cart-icon-design.jpg" alt="avatar" class="rounded-circle img-responsive">
                    </div>
                    <div class="modal-body text-center mb-1 blue-text">
                    <h5 class="mt-1 mb-2">STOCK DETAILS</h5>
                    <form>
                   <div class="md-form ml-0 mr-0">
	                    <input type="text" id="des" name="itemdesc" required value="xyz" class="form-control ml-0" readonly>
	                    <label for="itemdesc" class="ml-0">Item Name</label>
	                </div>
	                <input type="hidden" id="id" name="id" value="">
	                <div class="md-form ml-0 mr-0">
	                    <input type="text" id="pri" name="price" required value="xyz" class="form-control ml-0" readonly>
	                    <label for="price" class="ml-0 ">Price</label>
	                </div>
	                <div class="md-form ml-0 mr-0">
	                    <input type="text" id="sto" name="stock" required value="xyz" class="form-control ml-0" readonly>
	                    <label for="stock" class="ml-0">Stock</label>
	                </div>
                </form>
                    </div>
                     <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
	                        $("#editModal #sto").val(item.stock);
                    }
                });
            });
        });
    
    
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