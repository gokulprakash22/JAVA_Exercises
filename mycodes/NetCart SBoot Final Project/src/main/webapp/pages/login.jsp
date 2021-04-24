<!DOCTYPE HTML>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/myjsptags.tld" prefix="mytags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>

<html>
<head>
<title>Login Page</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
</head>
<style>
    :root {
  --input-padding-x: 1.5rem;
  --input-padding-y: .75rem;
}

body {
  background: #007bff;
  background: linear-gradient(to right, #0062E6, #33AEFF);
}

.card-signin {
  border: 0;
  border-radius: 1rem;
  box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);
}

.card-signin .card-title {
  margin-bottom: 2rem;
  font-weight: 300;
  font-size: 1.5rem;
}

.card-signin .card-body {
  padding: 2rem;
}

.form-signin {
  width: 100%;
}


.form-label-group {
  position: relative;
  margin-bottom: 1rem;
}

.form-label-group input {
  height: auto;
  border-radius: 2rem;
}
.form-label-group select {
  height: auto;
  border-radius: 2rem;
}

.form-label-group>input,
.form-label-group>label {
  padding: var(--input-padding-y) var(--input-padding-x);
}

.form-label-group>label {
  position: absolute;
  top: 0;
  left: 0;
  display: block;
  width: 100%;
  margin-bottom: 0;
  line-height: 1.5;
  color: #495057;
  border: 1px solid transparent;
  border-radius: .25rem;
  transition: all .1s ease-in-out;
}

.form-label-group input::-webkit-input-placeholder {
  color: transparent;
}

.form-label-group input:-ms-input-placeholder {
  color: transparent;
}

.form-label-group input::-ms-input-placeholder {
  color: transparent;
}

.form-label-group input::-moz-placeholder {
  color: transparent;
}

.form-label-group input::placeholder {
  color: transparent;
}

.form-label-group input:not(:placeholder-shown) {
  padding-top: calc(var(--input-padding-y) + var(--input-padding-y) * (2 / 3));
  padding-bottom: calc(var(--input-padding-y) / 3);
}

.form-label-group input:not(:placeholder-shown)~label {
  padding-top: calc(var(--input-padding-y) / 3);
  padding-bottom: calc(var(--input-padding-y) / 3);
  font-size: 12px;
  color: #777;
}

</style>
<body>

  <div class="container mt-5">
    <div class="row mt-5">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto mt-5">
        <div class="card card-signin my-5 mt-5">
          <div class="card-body">
            <h5 class="card-title text-center mt-5"><mytags:bundle key="sign in"/></h5>
           <spring:form modelAttribute="user" method="post" action="validateForm">
		
              <div class="form-label-group">
                <spring:input path="uname" class="form-control" />
                
               <label for="username"><mytags:bundle key="username"/></label>

              </div>

              <div class="form-label-group">
                <spring:input path="upass" class="form-control" type="password"/>
                <label for="inputPassword"><mytags:bundle key="password"/></label>
              </div>
              <div class="form-label-group">
              
                <spring:select path="role" class="form-control">
                <option><mytags:bundle key="select a role"/></opiton>
                <spring:option value="customer"><mytags:bundle key="customer"/></spring:option>
                <spring:option value="store-admin"><mytags:bundle key="store-admin"/></spring:option>
                <spring:option value="management"><mytags:bundle key="management"/></spring:option>
                </spring:select>
              </div>
              <button class="btn btn-lg btn-outline-primary text-uppercase w-100 mb-2" type="submit" style="border-radius:8rem"><mytags:bundle key="sign in"/></button>
              <a href="loadRegister"><button class="btn btn-lg btn-outline-primary text-uppercase w-100" type="button" style="border-radius:8rem"><mytags:bundle key="register"/></button></a>
            </spring:form>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>