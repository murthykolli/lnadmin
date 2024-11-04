<%--
	Document : sub_admin_active_users
	Created on : 7 May, 2014, 2:59:35 PM
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
    	var activeUsersCount = '<c:out value="${activeUsersCount}"/>';
    	var inactiveUsersCount = '<c:out value="${inactiveUsersCount}"/>'; 
    	if(activeUsersCount > 0){ $("#ActiveUsersID").show(); } 
    	if(inactiveUsersCount > 0){ $("#InactiveUsersID").show(); }
    	
    	$("#insured_list tr").not(':first').hover(
    		    function () {
    		    if(!$(this).find("input[type='subAdminUserName']").is(':checked')){
    		    $(this).find('td').css({"background-color":"#00a9f1","color":"#FFFFFF","cursor":"pointer"});
    		    } },
    		    function () {
    		    if(!$(this).find("input[name='subAdminUserName']").is(':checked')){
    		    $(this).find('td').css({"background-color":"","color":"#000000"});
    		    } } );
    		    $("#insured_list tr").not(':first').click(function(){
    		    $("#insured_list tr td").css({"background-color":"","color":"#000000"});   
    		    $(this).find('td').css({"background-color":"#00a9f1","color":"#FFFFFF"}); 
    		    $(this).find("input[name='subAdminUserName']").prop("checked",true);
    		    });	    
    });
    </script>
    
    <script type="text/javascript">
    function Pager(tableName, itemsPerPage) {
    this.tableName = tableName;
    this.itemsPerPage = itemsPerPage;
    this.currentPage = 1;
    this.pages = 0;
    this.inited = false;

    this.showRecords = function(from, to) {
    var rows = document.getElementById(tableName).rows;
    for (var i = 1; i < rows.length; i++) {
    if ((i < from) || (i > to))
    rows[i].style.display = 'none';
    else
    rows[i].style.display = '';
    } };

    this.showPage = function(pageNumber) {
    if (! this.inited) {
    return;
    }

    this.currentPage = pageNumber;
    var from = (pageNumber - 1) * itemsPerPage + 1;
    var to = from + itemsPerPage - 1;
    this.showRecords(from, to);
    };

    this.prev = function() {
    if (this.currentPage > 1) {
    this.showPage(this.currentPage - 1);
    }
    
    if(this.currentPage === 1) {
    document.getElementById("cmdPrevId").style.display = "none";
    document.getElementById("cmdNextId").style.display = "";
    }  
    
    if(this.currentPage < this.pages) {
    document.getElementById("cmdNextId").style.display = "";
    } };

    this.next = function() {
    if (this.currentPage < this.pages) {
    this.showPage(this.currentPage + 1);
    }
    
    document.getElementById("cmdPrevId").style.display = "";    
    if(this.currentPage === this.pages) {
    document.getElementById("cmdNextId").style.display = "none";
    } };

    this.init = function() {
    var rows = document.getElementById(tableName).rows;
    var records = (rows.length - 1);
    this.pages = Math.ceil(records / itemsPerPage);
    this.inited = true;
    };

    this.showPageNav = function(pagerName, positionId) {
    if (! this.inited) {
    return;
    }
    
    var element = document.getElementById(positionId);
    var pagerHtml ='<table border="0" id="navigation" align="left"> <tr><td align="right" width="131">';
    pagerHtml += '<input type="button"  style="position: relative; left: -15px; top: -8px; outline: none;" id="cmdPrevId" name="Prev" value="<-- View Prev" onclick="' + pagerName + '.prev();" class="pg-normal"/>  &nbsp; &nbsp; ';
    pagerHtml += '</td><td width="635">&nbsp;<td>';

    pagerHtml += '<td  align="right">';
    pagerHtml += '<input type="button" style="position: relative; left: 5px; top: -8px; outline: none;" id="cmdNextId" name="Next" value="View Next -->" onclick="'+pagerName+'.next();" class="pg-normal"/>';
    pagerHtml += '</td></tr></table>';
    element.innerHTML = pagerHtml;
    }; }
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
		<div><font id="myAccountDocText">Sub Admin - <font id="myAccountDocCategory">Active Users</font></font></div>
		
		<div id="ActiveUsersID" class="editUserProfile" style="display: none;">
		<a href="#"><font class="myAccountCurrentTab">&nbsp;Active Users&nbsp;</font></a> </div>
		
		<div id="InactiveUsersID" class="pending-federal-form" style="display: none;">
		<a href="subAdminInactiveUsers"><font class="pendingfederal">&nbsp;Inactive Users&nbsp;<font color="#00a9f1">-&nbsp;<c:out value="${inactiveUsersCount}"/>&nbsp;</font></font></a> </div>
		
		<div class="clearfix"></div>
		<div class="my-account-pending-stats">
		<form action="subAdminActiveUserPermissions" name="subAdminUsers" id="Documentform" method="get">		
		<h3 class="pending-head-text">Total active users <span>: <b id="displayCount"></b>&nbsp;</span></h3>
		
		<div class="pendingfederal-box">				             
			<display:table  id="insured_list"  class="tablesorter" name="activeUsersSavingList">
            <display:column headerClass="sortDisabled">            
            <input type="radio" style=" cursor: pointer;" name="subAdminUserName" value="${insured_list.userName},&, ${insured_list.adminType},&, ${insured_list.typeOfUser}"/>
            </display:column>            
            <display:column property ="userName" title="User Name &nbsp;"/>
            <display:column property ="adminType" title="Admin Type &nbsp;"/>
            <display:column style="width:130px;" property ="typeOfUser" title="Status &nbsp;" />
            <display:column style="width:80px;" property ="createdDate" title="Started On &nbsp;" format="{0,date,MM/dd/yyyy}" />
            </display:table>
        
        	<script>
            $(document).ready(function() {
            $("#insured_list")
            .tablesorter({widthFixed: true, widgets: ['pager']})
            .tablesorterPager({container: $("#pager")});
            } );
            </script>
              
            <input type="hidden" name="tableLengthName" id="tableLengthId" value="displayCount"/>
            <div id="pageNavPosition"></div>
            <script type="text/javascript"><!--
            var pager = new Pager('insured_list', 10);
            pager.init();
            pager.showPageNav('pager', 'pageNavPosition');
            pager.showPage(1);
            //--></script>
		
		</div>
		
		<div align="center" style="margin-top: 50px;margin-left: 290px;">
		
		<h4 class="pull-left btnn3">		            
		<button class="white-bg-sub-but" type="submit" onclick="return orderInfo();" onMouseOver="document.getElementById('fedit-sub-but').src='resources/images/SSImgs/ActivePermissionMOImg.png';" onMouseOut="document.getElementById('fedit-sub-but').src='resources/images/SSImgs/InactivePermissionImg.png';" ><img id="fedit-sub-but" src="resources/images/SSImgs/InactivePermissionImg.png" alt=""><span class="butt-va-mid">Inactive Permissions</span></button> </h4>
		
		<input type="hidden" name="allStateFormsRefType"/>
		<div class="clearfix"></div>
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
        var count = '<c:out value="${activeUsersCount}"/>';        
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

        function orderInfo(){
            var subAdminUserNames="";        
            for(var k=0; k < document.subAdminUsers.subAdminUserName.length; k++) {
            if(document.subAdminUsers.subAdminUserName[k].checked){
            subAdminUserNames=document.subAdminUsers.subAdminUserName[k].value;        
            } } 
            if(document.subAdminUsers.subAdminUserName.checked){
            subAdminUserNames=document.subAdminUsers.subAdminUserName.value;
            } 
            if(subAdminUserNames === ""){
            window.alert("Please select an user");
            return false;
            } } 
            </script>
        
        <script type="text/javascript" src="resources/scripts/tablelayout.js"></script>
    	</body>        
</html>