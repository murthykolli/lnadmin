<%--
	Document : completed_state_forms_file_upload
	Created on : 11 June, 2015, 2:59:35 PM
    Author     : MurthyK
--%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
    <head>
    <meta charset="UTF-8"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="description" content=""/>
    <meta name="keywords" content=""/>
    
    <meta http-equiv="refresh" content="<%= session.getMaxInactiveInterval()%>;url=sessionTimeOut" />
    <link rel="shortcut icon" href="resources/images/BrowserIcon/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="resources/css/tablelayout.css" type="text/css" />
       
    <script type="text/javascript">
    $(document).ready(function(){    	
    var compStateFormsOrderReceivedCount = '<c:out value="${compStateFormsOrderReceivedCount}"/>';
	var compStateFormsOrderProcessedCount = '<c:out value="${compStateFormsOrderProcessedCount}"/>';
	var compStateFormsSignatureCount = '<c:out value="${compStateFormsSignatureCount}"/>';
	var compStateFormsDocFiledCount = '<c:out value="${compStateFormsDocFiledCount}"/>';
	var compStateFormsDocAcceptedCount = '<c:out value="${compStateFormsDocAcceptedCount}"/>';
	var compStateFormsDocEmailedCount = '<c:out value="${compStateFormsDocEmailedCount}"/>';	
	if(compStateFormsOrderReceivedCount > 0){ $("#CompStateFormsOrderReceivedID").show(); } 
	if(compStateFormsOrderProcessedCount > 0){ $("#CompStateFormsOrderProcessedID").show(); } 
	if(compStateFormsSignatureCount > 0){ $("#CompStateFormsSignatureID").show(); } 
	if(compStateFormsDocFiledCount > 0){ $("#CompStateFormsDocFiledID").show(); } 
	if(compStateFormsDocAcceptedCount > 0){ $("#CompStateFormsDocAcceptedID").show(); } 
	if(compStateFormsDocEmailedCount > 0){ $("#CompStateFormsDocEmailedID").show(); } }); 
    </script>
    
    <script  type="text/javascript">
    window.onload = function onloadfunction(){
    rec();
    document.addcontact.subject.value="";
    document.addcontact.comments.value="";
    };
    function clearAll(){
    document.addcontact.subject.value="";
    document.addcontact.comments.value="";
    }
    </script>  
     
    <script>
    function validation(){
    var subject=document.addcontact.subject.value;
    var comment=document.addcontact.comments.value;
    var fileUpload=document.addcontact.file.value;
    var result = true; 
    
    if(subject!=""){
    document.getElementById("sublabel").removeAttribute("class");
    } else{
    document.getElementById("sublabel").setAttribute("class","redTestDisplay");
    result=false;
    }  
    
    if(comment!=""){
    document.getElementById("doclabel").removeAttribute("class");
    }
    else{
    document.getElementById("doclabel").setAttribute("class","redTestDisplay");
    result=false;
    }    
    if(fileUpload!=""){
    document.getElementById("filelabel").removeAttribute("class");
    } else{
    document.getElementById("filelabel").setAttribute("class","redTestDisplay");
    result=false;
    }    
    return result;
    }  
    
    function subjectValidation(){
    var subject=document.addcontact.subject.value;
    
    if(subject!=""){
    document.getElementById("sublabel").removeAttribute("class");
    } else{
    document.getElementById("sublabel").setAttribute("class","redTestDisplay");
    result=false;
    }  
    
    if(subject==""){
    document.getElementById("sublabel").removeAttribute("class");
    } }  
    
    function commentValidation(){
    var comment=document.addcontact.comments.value;
    
    if(comment!=""){
    document.getElementById("doclabel").removeAttribute("class");
    } else{
    document.getElementById("doclabel").setAttribute("class","redTestDisplay");
    result=false;
    } 
    
    if(comment==""){
    document.getElementById("passlabel").removeAttribute("class");
    } }
    
    function fileUploadValidation(){
    var fileUpload=document.addcontact.file.value;
    
    if(fileUpload != ""){
    document.getElementById("filelabel").removeAttribute("class");
    } else{
    document.getElementById("filelabel").setAttribute("class","redTestDisplay");
    result=false;
    }  
    
    if(fileUpload==""){
    document.getElementById("filelabel").removeAttribute("class");
    } } 
    
    function CheckFirstChar(key, txt){
    if(key == 32 && txt.value.length<=0){
    return false;
    } else if(txt.value.length>0){
    if(txt.value.charCodeAt(0) == 32){
    txt.value=txt.value.substring(1,txt.value.length);
    return true;
    } }
    return true;
    }    
    </script>
    
    </head>   
        <body>
        <!-- Related Projects Row -->
		<div class="row body-grey">
		<div class="container">
		
		<div class="col-md-12">
		<div class="white-man-backgnd" style="padding-bottom: 73px;">
		<div class="docCenter">
		<div>
		<div class="row">
		<div class="col-md-12">
		<div><font id="myAccountDocText">Completed State Forms - <font id="myAccountDocCategory">File Upload</font></font></div>
		
		<div id="CompStateFormsOrderReceivedID" class="pending-federal-form" style="display: none;">
		<a href="completedStateForms"><font class="pendingfederal">&nbsp;Received&nbsp;<font color="#00a9f1">-<c:out value="${compStateFormsOrderReceivedCount}"/></font></font></a> </div>
		
		<div id="CompStateFormsOrderProcessedID" class="pending-federal-form" style="display: none;">
		<a href="compStateFormsOrderProcessed"><font class="pendingfederal">&nbsp;Processed&nbsp;<font color="#00a9f1">-<c:out value="${compStateFormsOrderProcessedCount}"/></font></font></a> </div>
		
		<div id="CompStateFormsSignatureID" class="pending-federal-form" style="display: none;">
		<a href="compStateFormsSignature"><font class="pendingfederal">&nbsp;Signature&nbsp;<font color="#00a9f1">-<c:out value="${compStateFormsSignatureCount}"/></font></font></a> </div>
		
		<div id="CompStateFormsDocFiledID" class="pending-federal-form" style="display: none;">
		<a href="compStateFormsDocFiled"><font class="pendingfederal">&nbsp;Filed&nbsp;<font color="#00a9f1">-<c:out value="${compStateFormsDocFiledCount}"/></font></font></a> </div>
		
		<div id="CompStateFormsDocAcceptedID" class="pending-federal-form" style="display: none;">
		<a href="compStateFormsDocAccepted"><font class="pendingfederal">&nbsp;Accepted&nbsp;<font color="#00a9f1">-<c:out value="${compStateFormsDocAcceptedCount}"/></font></font></a> </div>
		
		<div id="CompStateFormsDocEmailedID" class="editUserProfile" style="display: none;">
		<a href="compStateFormsDocEmailed"><font class="myAccountCurrentTab">&nbsp;Mailed&nbsp;<font color="#00a9f1">-<c:out value="${compStateFormsDocEmailedCount}"/></font></font></a> </div>
		
		<div class="clearfix"></div>
		<div class="my-account-pending-stats">
		<form action="compStateFormsEmailWithUploadFile" method="post" name="addcontact" enctype="multipart/form-data">		
		
		<div class="pendingfederal-box">				             
			<table style="margin-top: 40px;margin-left: 15px;">
            <tr class="upload-file-font-sz"><td id="sublabel">Subject</td></tr>
            <tr style="height: 5px;"></tr>
            <tr><td><input type="text" name="subject" style="width: 300px;height:28px;border-radius: 5px;box-shadow: 3px 3px 1px #999999;border: solid 1px #595959;" onblur="subjectValidation();" onkeyup = "CheckFirstChar(event.keyCode, this);subjectValidation();" onkeydown = "return CheckFirstChar(event.keyCode, this);"/></td></tr>
            <tr style="height: 15px;"></tr>
            <tr class="upload-file-font-sz"><td id="doclabel">Comments and Questions</td></tr>
            <tr style="height: 5px;"></tr>            
            <tr><td> <textarea name="comments" style="width: 300px;height: 120px;border-radius: 5px;box-shadow: 3px 3px 1px #999999;border: 1px solid #595959;" cols="35" rows="8" onblur="commentValidation();" onkeyup = "CheckFirstChar(event.keyCode, this);commentValidation();" onkeydown = "return CheckFirstChar(event.keyCode, this);" rows="8" cols="25" ></textarea></td></tr>
            <tr style="height: 15px;"></tr>
            <tr class="upload-file-font-sz"><td id="filelabel">Select a file to upload</td></tr>
            <tr style="height: 5px;"></tr>
            <tr><td> <input type="file" style="width: 300px;height:28px;border: 1px solid #595959;box-shadow: 3px 3px 1px #999999;" id="file" name="file" onblur="fileUploadValidation();" onkeyup = "fileUploadValidation();"/></td></tr>
            <tr style="height: 35px;"></tr> 
            
            <tr><td><h4 class="pull-left btnn3" style="margin-left: 0px;">		            
			<button class="white-bg-sub-but" type="submit" onclick="return validation()" onMouseOver="document.getElementById('fcheout-sub-but').src='resources/images/SSImgs/SendButtonMOImg.png';" onMouseOut="document.getElementById('fcheout-sub-but').src='resources/images/SSImgs/SendButtonImg.png';" ><img id="fcheout-sub-but" src="resources/images/SSImgs/SendButtonImg.png" alt=""><span class="butt-va-mid">Send</span></button> </h4></td></tr>
            </table>
		</div>
		
        </form>
        <font color="white" id="text" size="3"></font>
		            
		<div class="clearfix"></div>
		</div> </div> </div> </div> 
		<div class="clearfix"></div>
		</div> </div> </div>  <!--end col-md-12 --> 
		</div> <!-- /.contaner --> 
		</div> <!-- row -->
				
        <script type="text/javascript">
        window.onload = function(){
        var count = '<c:out value="${compStateFormsOrderProcessedCount}"/>';        
        var  numnerOfDocuments = eval(count);
        if(numnerOfDocuments < 11) {
        if(document.getElementById("cmdNextId") != null) {
        document.getElementById("cmdNextId").style.display = "none";
        }
        if(document.getElementById("cmdPrevId") != null) {
        document.getElementById("cmdPrevId").style.display = "none";
        }
        }    
        else {
        document.getElementById("cmdPrevId").style.display = "none";
        document.getElementById("cmdNextId").style.display = "";
        }
        var ele =document.getElementById("displayCount");
        var countele = document.createTextNode(count);
        ele.appendChild(countele);        
        };        
        
        function signature(){
            var formNames="";
            document.allStateFormsName.allCompStateFormsRefType.value = "Order Processed";
            for(var k=0; k < document.allStateFormsName.formName.length; k++) {
            if(document.allStateFormsName.formName[k].checked){
            formNames=document.allStateFormsName.formName[k].value;        
            } } 
            if(document.allStateFormsName.formName.checked){
            formNames=document.allStateFormsName.formName.value;
            } 
            if(formNames === ""){
            window.alert("Please select a document");
            return false;
            } }       
        </script>
        
        <script type="text/javascript" src="resources/scripts/tablelayout.js"></script>
    	</body>        
</html>