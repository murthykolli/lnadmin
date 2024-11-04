<%--
	Document : daily_reports
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
    	var dailyFederalReportsCount = '<c:out value="${dailyFederalReportsCount}"/>';
    	var weeklyFederalReportsCount = '<c:out value="${weeklyFederalReportsCount}"/>';
    	var monthlyFederalReportsCount = '<c:out value="${monthlyFederalReportsCount}"/>';    	
    	var quarterlyFederalReportsCount = '<c:out value="${quarterlyFederalReportsCount}"/>';
    	var yearlyFederalReportsCount = '<c:out value="${yearlyFederalReportsCount}"/>';    	    	
    	if(dailyFederalReportsCount > 0){ $("#DailyFederalReportsFormsID").show(); } 
    	if(weeklyFederalReportsCount > 0){ $("#WeeklyFederalReportsFormsID").show(); } 
        if(monthlyFederalReportsCount > 0){ $("#MonthlyFederalReportsFormsID").show(); } 
        if(quarterlyFederalReportsCount > 0){ $("#QuarterlyFederalReportsFormsID").show(); } 
        if(yearlyFederalReportsCount > 0){ $("#YearlyFederalReportsFormsID").show(); } }); 
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
    }

    this.showPageNav = function(pagerName, positionId) {
    if (! this.inited) {
    return;
    }
    
    var element = document.getElementById(positionId);
    var pagerHtml ='<table border="0" id="navigation" align="left"> <tr><td align="right" width="131">';
    pagerHtml += '<input type="button"  style="position: relative; left: -15px; top: 10px; outline: none;" id="cmdPrevId" name="Prev" value="<-- View Prev" onclick="' + pagerName + '.prev();" class="pg-normal"/>  &nbsp; &nbsp; ';
    pagerHtml += '</td><td width="635">&nbsp;<td>';

    pagerHtml += '<td  align="right">';
    pagerHtml += '<input type="button" style="position: relative; left: 5px; top: 10px; outline: none;" id="cmdNextId" name="Next" value="View Next -->" onclick="'+pagerName+'.next();" class="pg-normal"/>';
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
		<div><font id="myAccountDocText">Federal Forms Reports - <font id="myAccountDocCategory">Daily</font></font></div>
		
		<div id="DailyFederalReportsFormsID" class="editUserProfile" style="display: none;">
		<a href="#"><font class="myAccountCurrentTab">&nbsp;Daily&nbsp;</font></a> </div>
		
		<div id="WeeklyFederalReportsFormsID" class="pending-federal-form" style="display: none;">
		<a href="weeklyFederalReports"><font class="pendingfederal">&nbsp;Weekly&nbsp;<font color="#00a9f1">-&nbsp;<c:out value="${weeklyFederalReportsCount}"/>&nbsp;</font></font></a> </div>
		
		<div id="MonthlyFederalReportsFormsID" class="pending-federal-form" style="display: none;">
		<a href="monthlyFederalReports"><font class="pendingfederal">&nbsp;Monthly&nbsp;<font color="#00a9f1">-&nbsp;<c:out value="${monthlyFederalReportsCount}"/>&nbsp;</font></font></a> </div>
		
		<div id="QuarterlyFederalReportsFormsID" class="pending-federal-form" style="display: none;">
		<a href="quarterlyFederalReports"><font class="pendingfederal">&nbsp;Quarterly&nbsp;<font color="#00a9f1">-&nbsp;<c:out value="${quarterlyFederalReportsCount}"/>&nbsp;</font></font></a> </div>
		
		<div id="YearlyFederalReportsFormsID" class="pending-federal-form" style="display: none;">
		<a href="yearlyFederalReports"><font class="pendingfederal">&nbsp;Yearly&nbsp;<font color="#00a9f1">-&nbsp;<c:out value="${yearlyFederalReportsCount}"/>&nbsp;</font></font></a> </div>
		
		<div class="clearfix"></div>
		<div class="my-account-pending-stats">
		<form action="reportsDataOperations" name="reportsName" id="Documentform" method="get">		
		<h3 class="pending-head-text">Total daily reports forms <span>: <b id="displayCount"></b>&nbsp;</span></h3>
		
		<div class="pendingfederal-box">				             
			<div class="table-wd-adjmnt">
			<display:table  id="insured_list"  class="tablesorter" name="dailyFederalReportsInfoList">
            <display:column style="width:50px;" property ="allFederalFormsPaymentInfoSavingId" title="Index"/>                  
            <display:column property ="userName" title="User Name &nbsp;"/>
            <display:column property ="legalName" title="Entity Name &nbsp;" />
            <display:column property ="typeOfDocument" title="Type of Document &nbsp;"/>
            <display:column property ="processingFee" title="Processing Fee &nbsp;" />
            <display:column property ="totalFee" title="Total Fee &nbsp;" />
            <display:column style="width:80px;" property ="createdDate" title="Started On &nbsp;" format="{0,date,MM/dd/yyyy}" />
            </display:table></div>
            
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
        var count = '<c:out value="${dailyFederalReportsCount}"/>';        
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
        </script>
        
        <script type="text/javascript" src="resources/scripts/tablelayout.js"></script>
    	</body>        
</html>