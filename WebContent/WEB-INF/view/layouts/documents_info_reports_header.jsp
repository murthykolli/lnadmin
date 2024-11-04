<%--
	Document : documents_info_reports_header
	Created on : 7 May, 2014, 2:59:35 PM
    Author     : MurthyK
--%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
	
	<!-- Bootstrap Core CSS -->
	<link href="resources/css/bootstrap.min.css" rel="stylesheet">
	<!-- Custom CSS -->
	<link href="resources/css/modern-business.css" rel="stylesheet">
	<!-- Custom CSS -->
	<link href="resources/css/style.css" rel="stylesheet">
	<!-- font CSS -->
	<link href="resources/css/font-awesome.min.css" rel="stylesheet">
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->    
    </head>    
        <body>
		<!-- Navigation -->
		<header>
		  <div class="container">
		    <div class="row">
		      <div class="col-md-3 col-xs-4 col-sm-12">
		        <div class="logo"> <a href="#"><img src="resources/images/legalnodLogoImg.png" class="img-responsive"></a> </div>
		      </div>		      
		    <div class="col-md-9 col-xs-8 col-sm-12 text-right">        	
	        <div class="top-login">
	        <p><i class="img-user"><img src="resources/images/logoutMO.png" id="log-out-img"></i><a href="logOut" onMouseOver="document.getElementById('log-out-img').src='resources/images/logout.png';" onMouseOut="document.getElementById('log-out-img').src='resources/images/logoutMO.png';">Logout</a></p>        
	        <p><i class="img-user glyphicon glyphicon-user login-adjmt" id="usr-login-img"></i><a href="userMyAccountRedirection" onMouseOver="document.getElementById('usr-login-img').style.color='#3c3c3c';" onMouseOut="document.getElementById('usr-login-img').style.color='#00a9f1';">User Profile </a></p>
	        <p class="font-bold"> Welcome  &nbsp;<span>${adminFirstName}</span>&nbsp;&nbsp;</p>
	        </div>
      		</div>
		    </div>
		  </div>
		</header>
		<nav class="navbar navbar-inverse">
		  <div class="container">
		    <div class="collapse navbar-collapse js-navbar-collapse pull-right">
		      <ul id="menu" class="nav navbar-nav">
		        <li> <a href="adminHomeRedirection">HOME</a> </li>
		        <li class="dropdown"> <a href="pendingForms" class="dropdown-toggle">PENDING FORMS </a> <span data-toggle="dropdown" class="caret"></span>
		          <ul class="dropdown-menu-pnd-sf pnding-sf">
		                  <li class="dropdown-header">BUSINESS SERVICES</li>
		                  <li><a href="pendingStateForms">Pending State Forms</a></li>
		                  <li><a href="pendingFederalForms">Pending Federal Forms</a></li>
		                  <li><a href="pendingFreeFederalForms">Pending Free Federal Forms</a></li>		                  		                  
		          </ul>		              
		        </li>
		        
		        <li class="dropdown"> <a href="completedForms" class="dropdown-toggle">COMPLETED FORMS </a> <span data-toggle="dropdown" class="caret"></span>
		          <ul class="dropdown-menu-comp-sf completed-sf">		                  
		                  <li class="dropdown-header">BUSINESS SERVICES</li>
		                  <li><a href="completedStateForms" >Completed State Forms</a></li>
        				  <li><a href="completedFederalForms" >Completed Federal Forms</a></li>
				          <li><a href="completedFreeFederalForms" >Completed Free Federal Forms</a></li>
				          <li><a href="nameAvailabilityCheck" >Name Availability Check</a></li>
				          <li><a href="adminFormsUploadRedirection" >Completed Forms File Upload</a></li>                	            
		          </ul>
		        </li>
		        
		        <li class="dropdown"> <a href="contactInfo" class="dropdown-toggle">CONTACT INFO </a> <span data-toggle="dropdown" class="caret"></span>
		          <ul class="dropdown-menu-continf-sf cont-inf-fm">	                  
		                  <li class="dropdown-header">BUSINESS SERVICES</li>
		                  <li><a href="primaryContactInfo" >Primary Contact Info</a></li>
        				  <li><a href="stateFormsPaymentContact" >State Forms Payment Contact</a></li>
				          <li><a href="federalFormsPaymentContact" >Federal Forms Payment Contact</a></li>
				          <li><a href="paymentTransactionContact" >User Payment Transaction Contact</a></li>		                		            
		          </ul>
		        </li>
		        
		        <li class="dropdown active"> <a href="reportsHome" class="dropdown-toggle">REPORTS </a> <span data-toggle="dropdown" class="caret"></span>
		          <ul class="dropdown-menu-reports-sf reports-fm">		                  
		                  <li><a href="allStateFormsReports" >State Forms Reports</a></li>
        				  <li><a href="allFederalFormsReports" >Federal Forms Reports</a></li>		                		            
		          </ul>
		        </li>
		        
		        <li style="margin-right: -10px;"> <a href="subAdminInfo">SUB ADMIN</a></li>		        
		        
		      </ul>
		    </div>
		    <!-- /.nav-collapse -->		    
		  </div>
		</nav> <!-- Navigation -->
		<!-- jQuery --> 
		<script src="resources/js/jquery.js"></script> 
		<!-- Bootstrap Core JavaScript --> 
		<script src="resources/js/bootstrap.min.js"></script> 
		<!-- Navigation --> 
		<script src="resources/js/jquery.slicknav.js"></script> 
		<script type="text/javascript">
		$(document).ready(function(){
	    $('#menu').slicknav();
		});

		$(function () {
		$('[data-toggle="tooltip"]').tooltip();
		});
		</script>		
		</body>       
</html>