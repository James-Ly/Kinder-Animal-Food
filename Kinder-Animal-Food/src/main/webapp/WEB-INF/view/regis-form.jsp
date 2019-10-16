<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<!doctype html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Kinder-Animal-Food Login page</title>

	<!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
  	<!-- Custom styles for this template -->
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/scrolling-nav.css" rel="stylesheet">
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/register-form.css" rel="stylesheet">

</head>

<body id="page-top">

  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <div class="container">
      <a class="navbar-brand js-scroll-trigger" href="#page-top">Kinder-Animal-Food</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/showMyLoginPage">Login</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/register/showRegistrationForm">Signup</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
    
  <div id="loginbox" style="margin-top: 50px;"
			class="register-box">
			
			<div class="panel panel-primary">

				<div style="padding-top: 30px" class="panel-body">

					<!-- Registration Form -->
					<form:form action="${pageContext.request.contextPath}/register/processRegistrationForm" 
						  	   modelAttribute="crmUser"
						  	   class="form-horizontal">
						  	   
						<div class="form-header">
							<div>Register New User</div>
						</div>

					    <!-- Place for messages: error, alert etc ... -->
					    <div class="form-group">
					        <div class="col-xs-15">
					            <div>
								
									<!-- Check for registration error -->
									<c:if test="${registrationError != null}">
								
										<div class="alert alert-danger col-xs-offset-1 col-xs-10">
											${registrationError}
										</div>
		
									</c:if>
																			
					            </div>
					        </div>
					    </div>
					    <div clas="error-container">
					    	<form:errors path="userName" class="error" />
					    	<form:errors path="password" class="error" />
					    	<form:errors path="matchingPassword" class="error" />
					    	<form:errors path="gender" class="error" />
					    </div>
					    
						<!-- User name -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-text">Email</span> 
							<form:input path="userName" placeholder="email (*)" class="form-control" />
						</div>

						<!-- Password -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-text">Password</span> 
							<form:password path="password" placeholder="password (*)" class="form-control" />
						</div>
						
						<!-- Confirm Password -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-text">Confirm password</span> 
							<form:password path="matchingPassword" placeholder="confirm password (*)" class="form-control" />
						</div>
					
						<!-- Gender -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-text">Gender</span> 
							<form:select path = "gender" placeholder = "gender (*)" class = "form-control">
								<form:option value = "Female" label = "Female"/>
								<form:option value = "Male" label = "Male"/>
								<form:option value = "Not disclose" label = "Not disclose"/>
							</form:select>
						</div> 
						
						<!-- Date of Birth -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-text">Birthdate</span> 
							<form:input type="date" path="birthDate" placeholder="Enter your birthday (*)" class="form-control" />
						</div>
						
						<!-- Register Button -->
						<div style="margin-top: 10px" class="form-group">						
							<div class="col-sm-6 controls">
								<button type="submit" class="btn btn-primary">Register</button>
							</div>
						</div>
						
					</form:form>

				</div>

			</div>

		</div>
  
  </div>
  

  <!-- Bootstrap core JavaScript -->
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Plugin JavaScript -->
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom JavaScript for this theme -->
  <script src="${pageContext.request.contextPath}/resources/js/scrolling-nav.js"></script>

</body>

</html>