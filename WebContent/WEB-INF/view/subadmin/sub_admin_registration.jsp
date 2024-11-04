<%--
    Document   : sub_admin_registration
    Created on : 14 May, 2015, 10:59:30 AM
    Author     : MurthyK
--%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>    
    <head>        
    <meta charset="UTF-8"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="description" content=""/>
    <meta name="keywords" content=""/>
    
    <link rel="shortcut icon" href="resources/images/BrowserIcon/favicon.ico" type="image/x-icon" />
    
    <script type="text/javascript">
    window.onload=function(){ document.register.elements['userName'].focus(); };
    
    function validation(){
    var email = document.register.userName.value;
    var password = document.register.password.value;
    var confirmpass = document.register.confirmPassword.value;    
    var reg = /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.[A-Za-z]{2,}$/;
    var reg1 = /^(?=.*[\d!@#$%\^*()_\-+=\[{\]};:|\./])(?=.*[a-z0-9]).{8,30}$/;
    var nameReg = /^[A-Za-z 0-9][A-Za-z 0-9 -#/_().&amp;,]*$/;
    var result = true;
    
    if(reg.test(email)){
    document.getElementById("emaillabel").removeAttribute("class");
    } else{
    document.getElementById("emaillabel").setAttribute("class","redTestDisplay");
    result=false;
    } 
    
    if(reg1.test(password)){
    document.getElementById("passlabel").removeAttribute("class");
    } else{
    document.getElementById("passlabel").setAttribute("class","redTestDisplay");
    result=false;
    }  
    
    if(password!=confirmpass || confirmpass===""){
    document.getElementById("confPasslabel").setAttribute("class","redTestDisplay");
    result=false;
    } else{
    document.getElementById("confPasslabel").removeAttribute("class");
    }  
    
    if(reg.test(email) && reg1.test(password) && password===confirmpass ){
    if(document.register.terms.checked===false){
    alert("Please accept the Terms and Conditions");
    result=false;
    }; } 
    return result;
    }
    
    function emailValidation(){
    var reg = /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.[A-Za-z]{2,}$/;
    var email=document.register.userName.value;
    
    if(reg.test(email)){
    document.getElementById("emaillabel").removeAttribute("class");
    } else{
    document.getElementById("emaillabel").setAttribute("class","redTestDisplay");
    
    if(email===""){
    document.getElementById("emaillabel").removeAttribute("class");
    }; }; } 
    
    function passValidation(){
    var reg1 = /^(?=.*[\d!@#$%\^*()_\-+=\[{\]};:|\./])(?=.*[a-z0-9]).{8,30}$/;
    var password=document.register.password.value;
    
    if(reg1.test(password)){
    document.getElementById("passlabel").removeAttribute("class");
    } else{
    document.getElementById("passlabel").setAttribute("class","redTestDisplay");
    
    if(password===""){
    document.getElementById("passlabel").removeAttribute("class");
    }; }; } 
    
    function confPassValidation(){
    var password=document.register.password.value;
    var confirmpass=document.register.confirmPassword.value;
    
    if(password===confirmpass){
    document.getElementById("confPasslabel").removeAttribute("class");
    } else{
    document.getElementById("confPasslabel").setAttribute("class","redTestDisplay");
    
    if(confirmpass===""){
    document.getElementById("confPasslabel").removeAttribute("class");
    }; }; }
    
    function CheckFirstChar(key, txt){
    if(key === 32 && txt.value.length<=0){
    return false;
    } else if(txt.value.length>0){
    if(txt.value.charCodeAt(0) === 32){
    txt.value=txt.value.substring(1,txt.value.length);
    return true;
    } } 
    return true;
    }
    
    function passwdvalidation(){
    var reg1 = /^(?=.*[\d!@#$%\^*()_\-+=\[{\]};:|\./])(?=.*[a-z0-9]).{8,30}$/;
    var password=document.register.password.value;
    
    if(!(reg1.test(password))){
    document.register.password.value="" ;
    alert("Password must be a minimum of 8 characters with at least one digit or special character");
    }; }
    </script>
    </head>
        <body>
        <!-- Related Projects Row -->
		<div class="row body-grey">
		<div class="container">		
		<div class="col-md-12">		
		<div class="white-bg paddingg">		
		<div class="row">         
        <div class="col-md-6">
            
        <div class="left-login" style="min-height:380px;">        
        <div class="headingg">
        <p id="smallFormHeading" style="margin-left: 37px; margin-top: 9px; position: absolute;">New Admin User</p> </div>
            <form action="subAdminDataSavingInDB" name="register" id="signup" method="post">            
            <table style="margin-top: 25px;margin-left: 30px;position: absolute;">            
            
            <c:if test="${not empty regErrorPage}"> <tr><td><font style="color: #c00002;font-size: 14px;height: 10px;margin-top:-20px;position: absolute;"><img src="resources/images/warning.gif" width="18" height="12"/> ${regErrorPage}</font></td></tr> <tr style="height: 5px;"></tr></c:if>
            
            <tr class="smallFormLabel"><td id="emaillabel">Email Address*</td></tr>
            <tr style="height: 5px;"></tr>
            <tr><td> <input type="text" id="userName" name="userName" autocomplete="off" onCopy="return false" onPaste="return false" onkeyup = "CheckFirstChar(event.keyCode, this);emailValidation();" onkeydown = "return CheckFirstChar(event.keyCode, this);" onblur="emailValidation()"/></td></tr> 
            <tr style="height: 7px;"></tr>
            <tr class="smallFormLabel"><td id="passlabel">Password*<td></tr>
            <tr style="height: 5px;"></tr>
            <tr><td><input type="password" id="password" name="password" autocomplete="off" onkeyup = "CheckFirstChar(event.keyCode, this);passValidation();" onkeydown = "return CheckFirstChar(event.keyCode, this);" onblur="passValidation()" onchange="passwdvalidation();"/></td></tr>
            <tr style="height: 7px;"></tr>
            <tr class="smallFormLabel"><td id="confPasslabel">Confirm Password*</td></tr>
            <tr style="height: 5px;"></tr>
            <tr><td><input type="password" name="confirmPassword" autocomplete="off" id="confirmPassword" onkeyup = "CheckFirstChar(event.keyCode, this);confPassValidation();" onkeydown = "return CheckFirstChar(event.keyCode, this);" onblur="confPassValidation()"/></td></tr>
                     
            <tr><td>            
            <h4 class="pull-left" style="margin-top: 35px;">
            <button class="submit-button" type="submit" onclick="return validation();" onMouseOver="document.getElementById('reg-sub-but').src='resources/images/SSImgs/RegPointerWhiteImg.png';" onMouseOut="document.getElementById('reg-sub-but').src='resources/images/SSImgs/RegPointerBlueImg.png';" ><span style="vertical-align: middle;">Sign Up</span><img id="reg-sub-but" style="padding-left: 10px;" src="resources/images/SSImgs/RegPointerBlueImg.png" alt=""></button>
			</h4></td></tr> </table>
            <div class="clearfix"></div> </form>
            </div> </div>

		<div class="col-md-6">		
		<div class="right-login">
        <p style="margin-left: 0px;margin-top: 55px;text-align: left;color: #00a9f1;">
        <img src="resources/images/approvedstatusImg.png"/> <font style="font: bold 1.5em/1em san-serif;margin-left:5px;">PERSONALIZED</font> </p>
        <p style="margin-left: 34px;margin-top: -10px;text-align: left;color: #1c1c1c;font: 1.0em/1.25em sans-serif;">Create personalized documents in a short amount of time</p>
        <p style="margin-left: 0px;margin-top: 20px;text-align: left;color: #00a9f1;">
        <img src="resources/images/approvedstatusImg.png" /> <font style="font: bold 1.5em/1em san-serif;margin-left:5px;">COST-EFFECTIVE</font> </p>
        <p style="margin-left: 34px;margin-top: -10px;text-align: left;color: #1c1c1c;font: 1.0em/1.25em sans-serif;">Avoid high legal fees by using our affordable services</p>
        <p style="margin-left: 0px;margin-top: 20px;text-align: left;color: #00a9f1;">
        <img src="resources/images/approvedstatusImg.png"/> <font style="font: bold 1.5em/1em san-serif;margin-left:5px;">EFFICIENT</font> </p>
        <p style="margin-left: 34px;margin-top: -10px;text-align: left;color: #1c1c1c;font: 1.0em/1.25em sans-serif;">Save your progress and access your documents 24/7</p>
        <p style="margin-left: 0px;margin-top: 20px;text-align: left;color: #00a9f1;">
        <img src="resources/images/approvedstatusImg.png"/> <font style="font: bold 1.5em/1em san-serif;margin-left:5px;">SECURE</font> </p>
        <p style="margin-left: 34px;margin-top: -10px;text-align: left;color: #1c1c1c;font: 1.0em/1.25em sans-serif;">Keep track of all of your legal documents in one secure place</p> </div>
	    </div> </div>
    		
		<div class="clearfix"></div>
		</div><!-- white Bg -->
		</div> </div>
		<!-- /.contaner -->		 
		</div>		
		<!-- row --> 
        </body>    
</html>