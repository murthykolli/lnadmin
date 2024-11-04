<%--
    Document   : checkout_additional_service_display
    Created on : 18 March, 2015, 10:21:38 AM
    Author     : MurthyK
--%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<html>    
    <head>
    <meta charset="UTF-8"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="description" content=""/>
    <meta name="keywords" content=""/>
    
    <meta http-equiv="refresh" content="<%= session.getMaxInactiveInterval()%>;url=sessionTimeOut" />
    <link rel="shortcut icon" href="resources/images/BrowserIcon/favicon.ico" type="image/x-icon">    
    <link rel="stylesheet" type="text/css" href="resources/css/datePicker.css">
    <link rel="stylesheet" type="text/css" href="resources/css/nicebuttonsdisplay.css" />    
    </head>    
        <body>    
        <!-- Related Projects Row -->
		<div class="row body-grey">
		<div class="container">
		
		<div class="col-md-12">
		<div class="nf-white-background" style="padding-bottom: 75px;">
		<div class="docCenter docCenter2">
		<div>
		<div class="row">
		
		<div class="col-md-12"><br>
		<div class="orderdisplay">
		<div class="orderdisplay-head">
		<h2> Document Review - </h2>
		<h2><img class="aln-st-img" src="resources/images/StateNameImage.png" alt="">&nbsp; ${stateName}  &nbsp;</h2>
		<h2><img class="aln-st-img" src="resources/images/FormNameImage.png" alt="">&nbsp; ${formName}</h2>
		
		<div class="clearfix"></div>
		</div>
		
		<%
			if(null != session.getAttribute("Attr_AS_Names_CheckOutList")) {
	        ArrayList attNamesCorpList = (ArrayList) session.getAttribute("Attr_AS_Names_CheckOutList");
	        if (null != attNamesCorpList && attNamesCorpList.size() > 0) { 
	        	
	        if(null != session.getAttribute("Attr_AS_Values_CheckOutList")) {
	        ArrayList attValueCorpList = (ArrayList) session.getAttribute("Attr_AS_Values_CheckOutList");
	        if (null != attValueCorpList && attValueCorpList.size() > 0) {
        %>
		
		<div class="order-display">	<br><br>	
		<table class="table table-striped">
		<tbody>
		<% for(int i=0,j=0;i<attNamesCorpList.size() && j<attValueCorpList.size();i++,j++){ %>
        <tr class="tr-wd-adj-attr">
        <td class="heding-text-adj" style="vertical-align:middle; width: 450px; font-weight: normal;"><%=attNamesCorpList.get(i)%> </td>
        <td class="value-text-adj" style="vertical-align:middle; width: 400px; font-weight: bold; color: black;"><b><%=attValueCorpList.get(j)%> </b></td></tr>
        
        <tr class="tr-whitespace-adj"><td></td></tr>
        <% } %>
		</tbody>
		</table>		
		
		<div style="margin-top: -15px;">
			<form action="pendingStateForms" name="psDocmentName" method="get" id="pro-form">
            <h4 style="margin-left: 0px; margin-top: 0px;">		            
			<button class="ord-wt-bg-sub-but btn-height" type="submit" onMouseOver="document.getElementById('modi-sub-but').src='resources/images/SSImgs/TextPointerWMOPreviousImg.png';" onMouseOut="document.getElementById('modi-sub-but').src='resources/images/SSImgs/TextPointerPreViousImg.png';" ><img id="modi-sub-but" src="resources/images/SSImgs/TextPointerPreViousImg.png" alt=""><span class="butt-va-mid">Back</span></button>
			</h4>            
            </form> 
        <div class="clearfix"></div>
		</div> </div>
		
		<div class="clearfix"></div>
		<% } } } } %>
		</div>
		
		<div class="ord-bottom-bg">
		<img src="resources/images/CornerFoldImg.png" alt="" class="img-responsive">
		</div> <!--   End alabama  -->
		</div> </div> </div> 
		
		<div class="clearfix"></div>
		</div> 
		</div> </div>  <!--end col-md-12 --> 
		</div> <!-- /.contaner --> 
		</div> <!-- row -->		   	
        </body>
</html>