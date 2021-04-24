 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>

<head>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<title>Register</title>
</head>

<body>
    <div class="container-fluid register">
        <div class="row">
            <div class="col-md-3 register-left">
                <img src="https://image.ibb.co/n7oTvU/logo_white.png" alt="" />
                <h3>Welcome</h3>
                <p>Customer Registration Page</p>
                <form action="/">
                <input type="submit" name="" value="Login" /><br />
                </form>
                
            </div>
            <div class="col-md-9 register-right">

                <div class="tab-content" id="myTabContent">

                    <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">

                        <h3 class="register-heading">Customer Registration</h3>
                        <spring:form modelAttribute="customerdetails" method="post" action="register">
                            <div class="row register-form">

                                <div class="col-md-6">
                                    <div class="form-group">
                                       <spring:input path="customername" class="form-control" placeholder=" Customer Name *"
                                            value="" />
                                    </div>
                                    <div class="form-group">
                                      <spring:input path="user.uname" class="form-control" placeholder=" User Name *" value="" />
                                    </div>
                                    <div class="form-group">
                                        <spring:input path="user.upass" type="password" class="form-control" placeholder="Password *" value="" />
                                    </div>

                                    <div class="form-group">
                                       <input name="confirmpassword" type="password" class="form-control" placeholder="Confirm Password *"
                                            value="" />
                                    </div>

                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <spring:input path="customeremail" type="email" class="form-control" placeholder="Your Email *" value="" />
                                    </div>
                                    <div class="form-group">
                                       <spring:input path="customerphone" minlength="10" maxlength="10" name="txtEmpPhone"
                                            class="form-control" placeholder="Your Phone *" value="" />
                                    </div>
                                                                        <div class="form-group">
                                        <spring:input path="customeraddress" type="text" class="form-control" placeholder="Address *" value="" />
                                    </div>
                                    <div class="form-group">
                                       <spring:select path="user.role" name="txtEmpPhone" class="form-control">
                                       <option selected disabled>Select a Role</option>
                                       <spring:option value="customer"></spring:option>
                                       <spring:option value="store-admin"></spring:option>
                                       <spring:option value="management"></spring:option>
                                       </spring:select>
                                    </div>
					
                                    <input type="submit" class="btnRegister" value="Register" />
                                </div>
                            </div>
                        </spring:form>
                    </div>
                </div>

            </div>

        </div>
    </div>

    </div>
</body>

<style>
    .register {
        background: -webkit-linear-gradient(left, #3931af, #00c6ff);

        padding: 3%;
    }

    .register-left {
        text-align: center;
        color: #fff;

    }

    .register-left input {
        border: none;
        border-radius: 3rem;
        padding: 2%;
        width: 60%;
        background: #f8f9fa;
        font-weight: bold;
        color: #383d41;
        margin-top: 30%;
        margin-bottom: 3%;
        cursor: pointer;
    }

    .register-right {
        background: #f8f9fa;
        border-top-left-radius: 10% 50%;
        border-bottom-left-radius: 10% 50%;
    }

    .register-left img {
        margin-top: 15%;
        margin-bottom: 5%;
        width: 25%;
        -webkit-animation: mover 2s infinite alternate;
        animation: mover 1s infinite alternate;
    }

    @-webkit-keyframes mover {
        0% {
            transform: translateY(0);
        }

        100% {
            transform: translateY(-20px);
        }
    }

    @keyframes mover {
        0% {
            transform: translateY(0);
        }

        100% {
            transform: translateY(-20px);
        }
    }

    .register-left p {
        font-weight: lighter;
        padding: 12%;
        margin-top: -9%;
    }

    .register .register-form {
        padding: 10%;
        margin-top: 10%;
        padding-bottom: 9.8%;
    }

    .btnRegister {
        float: center;
        margin-top: 10%;
        margin-left: 10%;
        border: none;
        border-radius: 1.5rem;
        padding: 2%;
        background: #0062cc;
        color: #fff;
        font-weight: 600;
        width: 50%;
        cursor: pointer;
    }

    .register .nav-tabs {
        margin-top: 3%;
        border: none;
        background: #0062cc;
        border-radius: 1.5rem;
        width: 28%;
        float: right;
    }

    .register .nav-tabs .nav-link {
        padding: 2%;
        height: 34px;
        font-weight: 600;
        color: #fff;
        border-top-right-radius: 1.5rem;
        border-bottom-right-radius: 1.5rem;
    }

    .register .nav-tabs .nav-link:hover {
        border: none;
    }

    .register .nav-tabs .nav-link.active {
        width: 100px;
        color: #0062cc;
        border: 2px solid #0062cc;
        border-top-left-radius: 1.5rem;
        border-bottom-left-radius: 1.5rem;
    }

    .register-heading {
        text-align: center;
        margin-top: 15%;
        margin-bottom: -15%;
        color: #495057;
    }
</style>

</html>