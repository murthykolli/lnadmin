<%--
	Document : admin_home
	Created on : 7 May, 2014, 2:59:35 PM
    Author     : MurthyK
--%>
<!DOCTYPE html>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html> 

    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
    
    <meta http-equiv="refresh" content="<%= session.getMaxInactiveInterval()%>;url=sessionTimeOut" />
    <link rel="shortcut icon" href="resources/images/BrowserIcon/favicon.ico" type="image/x-icon" /> 
      
    <style type="text/css">
        .formLink > a:hover { color: #00A9F1; }
        .formLink > a { color: #393939; text-decoration: none; font:normal 1.095em/1em Arial; outline: medium none; }
        .count { color: #00A9F1; font:normal 1.095em/1em Arial; text-align: right; }
    </style>
    <script type="text/javascript">
        $(document).ready(function(){
        $("#sReportsImg").click(function(){
        $("#fReportsImg").hide(); $("#fedRepShow").show();
        $("#sReportsImg").css( { marginLeft : "-17px", marginTop : "-5px" } );
        $("#fedRepShow").css( { marginLeft : "104px", marginTop : "-7px" } );        
        });         
        $("#fReportsImg").click(function(){
        $("#fReportsImg").hide(); $("#fedRepShow").show();
        $("#sReportsImg").css( { marginLeft : "0px", marginTop : "-15px" } );
        $("#fedRepShow").css( { marginLeft : "123px", marginTop : "-17px" } );        
        }); 
        });
    </script>
    </head>    
        <body>
        <!-- Related Projects Row -->
		<div class="row body-grey">
		<div class="container">
		
		<div class="col-md-12">
		<div class="white-man-backgnd" style="padding-bottom: 100px;">
		<div class="docCenter">
		<div>
		<div class="row">
		<div class="col-md-12">
		
		<div><font id="myAccountDocText">Reports - <font id="myAccountDocCategory">Home</font></font></div>
		
        <div style="margin-left: 20px; margin-top: 80px;position: absolute;">
        <a href="allStateFormsReports"><img id="sReportsImg" src="resources/images/reportsimgs/StateReportsImg.png" alt="" style="cursor: pointer;margin-top:-15px;position: absolute;"></a>
        <a href="allFederalFormsReports"><img id="fReportsImg" src="resources/images/reportsimgs/ReportsPieChartImg.png" alt="" style="cursor: pointer;margin-top:-8px;margin-left:105px;position:absolute;"></a>
        <img id="fedRepShow" src="resources/images/reportsimgs/FederalReportsImg.png" alt="" style="cursor: pointer;display: none;margin-top:0px;margin-left:0px;position:absolute;">
        <img src="resources/images/reportsimgs/PieChartTextImg.png" alt="" style="margin-left: -38px; margin-top: 175px;">
        </div>
        <div id="formsChart" style="margin-left: 282px; margin-top: 20px;">
        <img src="resources/images/reportsimgs/ReportsHomeImg.png" alt="">        
        <table style="margin-left:33px;margin-top: -188px;">
        <tr style="height:24px;"><td style="width:200px;" class="formLink"><a href="dailyStateReports">Daily</a></td><td style="width:38px;" class="count"><c:out value="${dailyStateReportsCount}"/></td><td style="width:49px;"></td><td class="formLink" style="width:190px;"><a href="dailyFederalReports">Daily</a></td><td style="width:38px;" class="count"><c:out value="${dailyFederalReportsCount}"/></td></tr>
        <tr style="height:27px;"><td class="formLink"><a href="weeklyStateReports">Weekly</a></td><td class="count"><c:out value="${weeklyStateReportsCount}"/></td><td></td><td class="formLink"><a href="weeklyFederalReports">Weekly</a></td><td class="count"><c:out value="${weeklyFederalReportsCount}"/></td></tr>
        <tr style="height:26px;"><td class="formLink"><a href="monthlyStateReports">Monthly</a></td><td class="count"><c:out value="${monthlyStateReportsCount}"/></td><td></td><td class="formLink"><a href="monthlyFederalReports">Monthly</a></td><td class="count"><c:out value="${monthlyFederalReportsCount}"/></td></tr>
        <tr style="height:27px;"><td class="formLink"><a href="quarterlyStateReports">Quarterly</a></td><td class="count"><c:out value="${quarterlyStateReportsCount}"/></td><td></td><td class="formLink"><a href="quarterlyFederalReports">Quarterly</a></td><td class="count"><c:out value="${quarterlyFederalReportsCount}"/></td></tr>
        <tr style="height:27px;"><td class="formLink"><a href="yearlyStateReports">Yearly</a></td><td class="count"><c:out value="${yearlyStateReportsCount}"/></td><td></td><td class="formLink"><a href="yearlyFederalReports">Yearly</a></td><td class="count"><c:out value="${yearlyFederalReportsCount}"/></td></tr>
        </table>
        </div>
        
        
        </div> </div> </div> 
		<div class="clearfix"></div>
		</div> </div> </div>  <!--end col-md-12 --> 
		</div> <!-- /.contaner --> 
		</div> <!-- row -->
		</body>
</html>