 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <%! int data=50; %>  
 <!DOCTYPE html>
 <html>
 <head>
 <title>CheckOut</title>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
 <link href="https://use.fontawesome.com/releases/v5.0.7/css/all.css" rel="stylesheet">
    </head>
 <style>body {
  background: #eecda3;
  background: -webkit-linear-gradient(to right, #eecda3, #ef629f);
  background: linear-gradient(to right, #00c6ff, #0072ff);
  min-height: 100vh;
}
.btn-primary{
background:linear-gradient(to right, #00c6ff, #0072ff) !important;
}
</style>
<div class="px-4 px-lg-0">
  <div class="container text-white py-5 text-center">
    <h1 class="display-4">Shopping Cart</h1>
  </div>

  <div class="pb-5">
    <div class="container">
      <div class="row">
        <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

          <!-- Shopping cart table -->
          <div class="table-responsive">
            <table class="table">
              <thead>
                <tr>
                  <th scope="col" class="border-0 bg-light">
                    <div class="p-2 px-3 text-uppercase">Product</div>
                  </th>
                  <th scope="col" class="border-0 bg-light">
                    <div class="py-2 text-uppercase">Price</div>
                  </th>
                  <th scope="col" class="border-0 bg-light">
                    <div class="py-2 text-uppercase">Quantity</div>
                  </th>
                  <th scope="col" class="border-0 bg-light">
                    <div class="py-2 text-uppercase">Total</div>
                  </th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${itemmap}" var="item">
                <tr>
                  <th scope="row" class="border-0">
                    <div class="p-2">
                      <img src=<c:out value='${item.key.getImage()}'/> alt="" width="70" class="img-fluid rounded shadow-sm">
                      <div class="ml-3 d-inline-block align-middle">
                        <h5 class="mb-0">${item.key.getItemdescription()}</h5><span class="text-muted font-weight-normal font-italic">Category: ${item.key.getCategory()} </span>
                      </div>
                    </div>
                  </th>
                  <td class="border-0 align-middle"><strong>${item.key.getItemprice()}</strong></td>
                  <td class="border-0 align-middle"><strong>${item.value}</strong></td>
                  <td class="border-0 align-middle"><strong>${item.value*item.key.getItemprice()}</strong></td>
                 <!--  <td class="border-0 align-middle"><a href="#" class="text-dark"><i class="fa fa-trash"></i></a></td> -->
                <c:set var="data" value="${data+item.value*item.key.getItemprice()}" />
                </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
<c:set var="tax" value="${(0.05)*data}" />
<c:set var="shipping" value="200" />
      <div class="row py-5 bg-white rounded shadow-sm">
        <div class="col-lg-12">
          <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Order summary </div>
          <div class="p-4">
            <p class="font-italic mb-4">Shipping and additional costs are calculated based on values you have entered.</p>
            <ul class="list-unstyled mb-4">
              <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Order Subtotal </strong><strong>&#8377;<c:out value="${data}"/></strong></li>
              <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Shipping and handling</strong><strong>&#8377; <c:out value="${shipping}"/></strong></li>
              <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Tax</strong><strong>&#8377; <c:out value="${tax}"/></strong></li>
              <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Total</strong>
                <h5 class="font-weight-bold">&#8377; <c:out value="${data+tax+shipping}"/></h5>
              </li>
            </ul><a class="btn btn-primary rounded-pill py-2 btn-block" id="rzp-button1">Confirm Order</a>
          </div>
        </div>
      </div>
  </div>
</div>
<script src="https://checkout.razorpay.com/v1/checkout.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
<script>
var options = {
    "key": "rzp_test_px3hQBNFEYVrmD", // Enter the Key ID generated from the Dashboard
    "amount": ${amount}, // Amount is in currency subunits. Default currency is INR. Hence, 50000 refers to 50000 paise
    "currency": "INR",
    "name": "NetCart",
    "description": "Buy Items",
    "image": "https://www.graphicsprings.com/filestorage/stencils/b8639dd9acb3c6ab0b6771e529ad3930.png?width=500&height=500",
    "order_id": "${razorpayOrderId}", //This is a sample Order ID. Pass the `id` obtained in the response of Step 1
    "handler": function (response){
        //alert(response.razorpay_payment_id);
        //alert(response.razorpay_order_id);
        //alert(response.razorpay_signature);
        var form = $('<form action="verify" method="post">' +
		  '<input type="hidden" name="razorpay_payment_id" value="' + response.razorpay_payment_id + '" />' +
		  '<input type="hidden" name="razorpay_order_id" value="' + response.razorpay_order_id + '" />' +
		  '<input type="hidden" name="razorpay_signature" value="' + response.razorpay_signature + '" />' +
		  '</form>');
		$('body').append(form);
		form.submit();
    },
    "prefill": {
        "name": "${customer.getCustomername()}",
        "email": "${customer.customeremail}",
        "contact": "${customer.customerphone}"
    },
    "notes": {
        "address": "Shop, Chennai",
        "shopping_order_id":"${invno}"
    },
    "theme": {
        "color": "#3399CC"
    }
};
var rzp1 = new Razorpay(options);
rzp1.on('payment.failed', function (response){
        alert(response.error.code);
        alert(response.error.description);
        alert(response.error.source);
        alert(response.error.step);
        alert(response.error.reason);
        alert(response.error.metadata.order_id);
        alert(response.error.metadata.payment_id);
});
document.getElementById('rzp-button1').onclick = function(e){
    rzp1.open();
    e.preventDefault();
}
</script>
</html>
