
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="spring"%>
<html>

<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>


<title></title>
</head>

<body>
	<div class="container container-main">

		<div class="row row-main">
			<div class="col-md-3 no-float  "
				style="background: linear-gradient(to bottom, #83eaf1, #63a4ff);">
				<div class="card-block text-center text-white">
					<div class="m-b-25 padding-top-50">
						<img src="https://img.icons8.com/bubbles/100/000000/user.png"
							class="img-radius" alt="User-Profile-Image">
					</div>
					<h4 class="f-w-600">${customer.getCustomername()}</h4>
					<p>Customer</p>
					<i
						class=" mdi mdi-square-edit-outline feather icon-edit m-t-10 f-16"></i>
				</div>
			</div>
			<div class="col-md-9 no-float"
				style="background-color: white; padding-top: 5%;">
				<spring:form modelAttribute="customer" method="post"
					action="updateProfile">
						<div class="row">
							<p style="text-align: center; font-size: 40px; padding: 40px;">CUSTOMER
								INFORMATION</p>
							<div class="col-sm-6" style="text-align: center;">
								<p class="m-b-10 f-w-600">Customer Number</p>
								<spring:input type="text" value="${customer.getCustomerno()}"
									path="customerno" disabled="true"/>
								<%-- <h6 class=" f-w-400">${customer.getId()}</h6> --%>
								<hr
									style="width: 50%; text-align: center; margin-left: 25%; color: black; background-color: white">
							</div>
							<div class="col-sm-6" style="text-align: center;">
								<p class="m-b-10 f-w-600">Name</p>
								<spring:input type="text" value="${customer.getCustomername()}"
									path="customername" />
								<hr
									style="width: 50%; text-align: left; margin-left: 25%; color: black; background-color: white">
							</div>
						</div>
						<div class="row" style="text-align: center;">
							<div class="col-sm-6" style="text-align: center;">
								<p class="m-b-10 f-w-600">E-Mail</p>
								<spring:input type="text" value="${customer.getCustomeremail()}" path="customeremail" />
								<!--                         <hr style="width:50%;text-align:left;margin-left:%;color:black;background-color:white">
 -->
							</div>
							<div class="col-sm-6" style="text-align: center;">
								<p class="m-b-10 f-w-600">Phone</p>
								<spring:input type="text" value="${customer.getCustomerphone()}"
									path="customerphone" />
								<!--                         <hr style="width:50%;text-align:left;margin-left:25%;color:black;background-color:white">
 -->
							</div>
						</div>
						<div class="row" style="text-align: center;">
							<div class="col-sm-12">
								<p class="m-b-10 f-w-600">Address</p>
								<spring:input type="text"
									value="${customer.getCustomeraddress()}" path="customeraddress" />
								<hr
									style="width: 50%; text-align: left; margin-left: 25%; color: black; background-color: white">
							</div>
						</div>
						
						<div class="row" style="text-align:center">
						
						<div class="col-sm-6">
								<button type="submit" class="btn btn-info">Update</button>
						</div>
						</spring:form>
						<div class="col-sm-6">
									<form
										action="${pageContext.servletContext.contextPath}/"
										method="get">
										<button type="submit" class="btn btn-info ">Go Back</button>
									</form></div>
						</div>
							
								
								
</div>
</div>


	<!-- <div class="row" style="text-align: center;"> -->				
						
			

</body>
<style>
input[type=text] {
	background: transparent;
	border: none;
	border-bottom: 1px solid #000000;
}

.no-padding {
	padding: 0px
}

.navbar {
	margin: 0px;
	padding: 0px;
}

body {
	padding: 100px;
}
/* html,
    body, */
.container-main {
	box-shadow: 0px 0px 10px 10px #b3b3b3;
	height: 100%;
}

.container-main {
	display: table;
	width: 100%;
	margin-top: 0px;
	padding: 0px 0 0 0;
	/*set left/right padding according to needs*/
	box-sizing: border-box;
}

.row-main {
	height: 100%;
	display: table-row;
}

.row-main .no-float {
	display: table-cell;
	float: none;
}

.padding {
	padding: 3rem !important
}

.padding-top-50 {
	padding-top: 50%;
}

.user-card-full {
	overflow: hidden
}

.card {
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 20px 0 rgba(69, 90, 100, 0.08);
	box-shadow: 0 1px 20px 0 rgba(69, 90, 100, 0.08);
	border: none;
	margin-bottom: 30px
}

.m-r-0 {
	margin-right: 0px
}

.m-l-0 {
	margin-left: 0px
}

.user-card-full .user-profile {
	border-radius: 5px 0 0 5px
}

.bg-c-lite-green {
	background: -webkit-gradient(linear, left top, right top, from(#83eaf1),
		to(#63a4ff));
	background: linear-gradient(to right, #83eaf1, #63a4ff)
}

.user-profile {
	padding: 20px 0
}

.card-block {
	padding: 1.25rem
}

.m-b-25 {
	margin-bottom: 25px
}

.img-radius {
	border-radius: 5px
}

img {
	display: block;
	margin-left: auto;
	margin-right: auto;
	width: 15rem;
}

h6 {
	font-size: 14px
}

.card .card-block p {
	line-height: 25px
}

@media only screen and (min-width: 1400px) {
	p {
		font-size: 14px
	}
}

.card-block {
	padding: 1.25rem
}

.b-b-default {
	border-bottom: 1px solid #e0e0e0
}

.f-s-30 {
	font-size: 30px;
}

.m-b-20 {
	margin-bottom: 20px
}

.p-b-5 {
	padding-bottom: 5px !important
}

.card .card-block p {
	line-height: 25px
}

.m-b-10 {
	margin-bottom: 10px
}

.text-muted {
	color: #919aa3 !important
}

.b-b-default {
	border-bottom: 1px solid #e0e0e0
}

.f-w-600 {
	font-weight: 600 text-align: center;
}

.m-b-20 {
	margin-bottom: 20px
}

.m-t-40 {
	margin-top: 20px
}

.p-b-5 {
	padding-bottom: 5px !important
}

.m-b-10 {
	margin-bottom: 10px
}

.m-t-40 {
	margin-top: 20px
}

.user-card-full .social-link li {
	display: inline-block
}

.user-card-full .social-link li a {
	font-size: 20px;
	margin: 0 10px 0 0;
	-webkit-transition: all 0.3s ease-in-out;
	transition: all 0.3s ease-in-out
}
</style>

</html>








<%-- 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>

<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
        integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
        integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>


    <title>

    </title>
</head>

<body>
    <div class="container container-main">

        <div class="row row-main">
            <div class="col-md-3 no-float  " style="background: linear-gradient(to bottom, #83eaf1, #63a4ff);">
                <div class="card-block text-center text-white">
                    <div class="m-b-25 padding-top-50"> <img src="https://img.icons8.com/bubbles/100/000000/user.png"
                            class="img-radius" alt="User-Profile-Image"> </div>
                    <h4 class="f-w-600">${customer.getCustomername()}</h4>
                    <p>Customer</p> <i class=" mdi mdi-square-edit-outline feather icon-edit m-t-10 f-16"></i>
                </div>
            </div>
            <div class="col-md-9 no-float" style="background-color:white; padding-top: 5%;">
                
                <div class="row">
                    <p style="text-align: center; font-size: 40px; padding: 10px; padding-bottom:20px;">CUSTOMER INFORMATION</p>
                    <div class="col-sm-6" style="text-align: center;">
                        
                        <p class="m-b-10 f-w-600">Customer Number</p>
                        
                        <h6 class="text-muted f-w-400">${customer.getCustomerno()}</h6>
                        <hr style="width:50%;text-align:center;margin-left:25%;color:black;background-color:black">
                    </div>
                    <div class="col-sm-6" style="text-align: center;">
                        <p class="m-b-10 f-w-600"> Name</p>
                        <h6 class="text-muted f-w-400">${customer.getCustomername()}</h6>
                        <hr style="width:50%;text-align:left;margin-left:25%;color:black;background-color:black">
                    </div>
                </div>
                <div class="row" style="text-align: center;">
                    <div class="col-sm-6" style="text-align: center;">
                        <p class="m-b-10 f-w-600"> E-Mail</p>
                        <h6 class="text-muted f-w-400">${customer.getCustomeremail()}</h6>
                        <hr style="width:50%;text-align:left;margin-left:%;color:black;background-color:black">
                    </div>
                    <div class="col-sm-6"style="text-align: center;">
                        <p class="m-b-10 f-w-600">Phone</p>
                        <h6 class="text-muted f-w-400">${customer.getCustomerphone()}</h6>
                        <hr style="width:50%;text-align:left;margin-left:25%;color:black;background-color:black">
                    </div>
                </div>
                <div class="row" style="text-align: center;">
                    <div class="col-sm-12">
                        <p class="m-b-10 f-w-600">Address</p>
                        <h6 class="text-muted f-w-400">${customer.getCustomeraddress()}</h6>
                        <hr style="width:50%;text-align:left;margin-left:25%;color:black;background-color:black">
                    </div>
                </div>
                <div class="row" style="text-align: center;">
                <div class="col-sm-12">
                    <button class="btn btn-info ">Go Back</button>
                </div>
                </div>
            </div>
            
        </div>
        
    </div>
</body>
<style>
    .no-padding {
        padding: 0px
    }

    .navbar {
        margin: 0px;
        padding: 0px;
    }

    body{
        padding: 100px;
        
    }
    /* html,
    body, */
    .container-main {
        box-shadow: 0px 0px 10px 10px #b3b3b3;
        height: 100%;
    }

    .container-main {
        display: table;
        width: 100%;
        margin-top: 0px;
        padding: 0px 0 0 0;
        /*set left/right padding according to needs*/
        box-sizing: border-box;
    }

    .row-main {
        height: 100%;
        display: table-row;
    }

    .row-main .no-float {
        display: table-cell;
        float: none;
    }

    .padding {
        padding: 3rem !important
    }


    .padding-top-50 {
        padding-top: 50%;
    }

    .user-card-full {
        overflow: hidden
    }

    .card {
        border-radius: 5px;
        -webkit-box-shadow: 0 1px 20px 0 rgba(69, 90, 100, 0.08);
        box-shadow: 0 1px 20px 0 rgba(69, 90, 100, 0.08);
        border: none;
        margin-bottom: 30px
    }

    .m-r-0 {
        margin-right: 0px
    }

    .m-l-0 {
        margin-left: 0px
    }

    .user-card-full .user-profile {
        border-radius: 5px 0 0 5px
    }

    .bg-c-lite-green {
        background: -webkit-gradient(linear, left top, right top, from(#83eaf1), to(#63a4ff));
        background: linear-gradient(to right, #83eaf1, #63a4ff)
    }

    .user-profile {
        padding: 20px 0
    }

    .card-block {
        padding: 1.25rem
    }

    .m-b-25 {
        margin-bottom: 25px
    }

    .img-radius {
        border-radius: 5px
    }

    img {
        display: block;
        margin-left: auto;
        margin-right: auto;
        width: 15rem;
    }

    h6 {
        font-size: 14px
    }

    .card .card-block p {
        line-height: 25px
    }

    @media only screen and (min-width: 1400px) {
        p {
            font-size: 14px
        }
    }


    .card-block {
        padding: 1.25rem
    }

    .b-b-default {
        border-bottom: 1px solid #e0e0e0
    }

    .f-s-30 {
        font-size: 30px;
    }

    .m-b-20 {
        margin-bottom: 20px
    }

    .p-b-5 {
        padding-bottom: 5px !important
    }

    .card .card-block p {
        line-height: 25px
    }

    .m-b-10 {
        margin-bottom: 10px
    }

    .text-muted {
        color: #919aa3 !important
    }

    .b-b-default {
        border-bottom: 1px solid #e0e0e0
    }

    .f-w-600 {
        font-weight: 600
    }

    .m-b-20 {
        margin-bottom: 20px
    }

    .m-t-40 {
        margin-top: 20px
    }

    .p-b-5 {
        padding-bottom: 5px !important
    }

    .m-b-10 {
        margin-bottom: 10px
    }

    .m-t-40 {
        margin-top: 20px
    }

    .user-card-full .social-link li {
        display: inline-block
    }

    .user-card-full .social-link li a {
        font-size: 20px;
        margin: 0 10px 0 0;
        -webkit-transition: all 0.3s ease-in-out;
        transition: all 0.3s ease-in-out
    }
</style>

</html>  --%>