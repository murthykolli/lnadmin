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
        .formLink > a:hover{ color: #00A9F1; }
        .formLink > a{ color: #393939; text-decoration: none; font:normal 1.095em/1em Arial; outline: medium none; }
        .count{ color: #00A9F1; font:normal 1.095em/1em Arial; text-align: right; }
    </style>
    <script type="text/javascript">
        $(document).ready(function(){        	
        $("#sFormsImg").click(function(){
        if($("#greenBubble").css("display")==='none'){
        $("#orangeBubble").hide();
        $("#sFormsImg").css( { marginLeft : "-30px", marginTop : "6px" } );
        $("#federalShow").css( { marginLeft : "113px", marginTop : "-6px" } );
        $("#greenBubble").animate( { marginLeft : "55px", marginTop : "65px" },300,function(){
        $("#sFormsImg").css( { marginLeft : "0px", marginTop : "-6px" } );
        $("#formsChart,#fFormsBar").hide();
        $("#greenBubble,#sFormsBar").show();
        }); } });         
        $("#fFormsImg").click(function(){
        if($("#orangeBubble").css("display")==='none'){
        $("#greenBubble,#fFormsImg").hide();
        $("#federalShow").show();
        $("#sFormsImg").css( { marginLeft : "0px", marginTop : "-6px" } );
        $("#federalShow").css( { marginLeft : "142px", marginTop : "-17px" } );
        $("#orangeBubble").animate( { marginLeft : "125px", marginTop : "35px" },300,function(){
        $("#formsChart,#sFormsBar,#federalShow").hide();
        $("#fFormsImg,#orangeBubble,#fFormsBar").show();
        }); } });        
        var FormBackStatus = '<c:out value="${FederalFormBack}"/>';
        if(FormBackStatus === 'StateForms') {        		
                if($("#greenBubble").css("display")==='none'){
                $("#orangeBubble").hide();
                $("#sFormsImg").css( { marginLeft : "-30px", marginTop : "6px" } );
                $("#federalShow").css( { marginLeft : "113px", marginTop : "-6px" } );
                $("#greenBubble").animate( { marginLeft : "55px", marginTop : "65px" },300,function(){
                $("#sFormsImg").css( { marginLeft : "0px", marginTop : "-6px" } );
                $("#formsChart,#fFormsBar").hide();
                $("#greenBubble,#sFormsBar").show();
                }); }               
        } else if(FormBackStatus === 'FederalForms') {        		
                if($("#orangeBubble").css("display")==='none'){
                $("#greenBubble,#fFormsImg").hide();
                $("#federalShow").show();
                $("#sFormsImg").css( { marginLeft : "0px", marginTop : "-6px" } );
                $("#federalShow").css( { marginLeft : "142px", marginTop : "-17px" } );
                $("#orangeBubble").animate( { marginLeft : "125px", marginTop : "35px" },300,function(){
                $("#formsChart,#sFormsBar,#federalShow").hide();
                $("#fFormsImg,#orangeBubble,#fFormsBar").show();
                }); } } });
            
        function showClick(event, obj, imgID, showImg, showMOImg){
        event.preventDefault();
        $("#"+imgID).attr("src","resources/images/homeimages/"+showMOImg); 
        $("#"+imgID).animate({opacity:1},300,function(){
        $("#"+imgID).attr("src","resources/images/homeimages/"+showImg); 
        });
        setTimeout(function(){document.location.href=$(obj).attr("href");}, 500); 
        }
    </script>
    </head>    
        <body>
        <!-- Related Projects Row -->
		<div class="row body-grey">
		<div class="container">
		
		<div class="col-md-12">
		<div class="white-man-backgnd" style="padding-bottom: 20px;">
		<div class="docCenter">
		<div>
		<div class="row">
		<div class="col-md-12">
		
		<div><font id="myAccountDocText">Admin - <font id="myAccountDocCategory">Home</font></font></div>
		
        <div style="margin-left: -18px;min-height: 355px;margin-top: 40px;">
        <div style="margin-left: 15px; margin-top: 70px;position: absolute;">
        <img id="sFormsImg" src="resources/images/homeimages/StateFormShowImage.png" alt="" style="cursor: pointer;margin-top:-6px;position: absolute;"/>
        <img id="fFormsImg" src="resources/images/homeimages/FederalFormShowImage.png" alt="" style="cursor: pointer;margin-top:-6px;margin-left:114px;position:absolute;"/>
        <img id="federalShow" src="resources/images/homeimages/FederalFormsSelectionImg.png" alt="" style="cursor: pointer;display: none;margin-top:0px;margin-left:0px;position:absolute;"/>
        <img id="greenBubble" src="resources/images/homeimages/GreenBobbleImg.png" alt="" style="display: none;position: absolute; margin-top: 65px; margin-left: 55px;"/>
        <img id="orangeBubble" src="resources/images/homeimages/OrangeBobbleImg.png" alt="" style="display: none;position: absolute; "/>
        <img src="resources/images/homeimages/PieHeadingImg.png" alt="" style="margin-left: -15px; margin-top: 200px;"/></div>
        
        
        <div id="formsChart" style="margin-left: 260px; margin-top: 0px;">
        <img src="resources/images/homeimages/FormsInfoChartImg.png" alt=""/>        
        <table style="margin-left:33px;margin-top: -235px;">
        <tr style="height:24px;"><td style="width:237px;" class="formLink"><a href="pendingStateForms">Pending State Forms</a></td><td style="width:36px;" class="count"><c:out value="${pendStateFormsDataCount}"/></td><td style="width:34px;"></td><td class="formLink" style="width:232px;"><a href="completedStateForms">Completed State Forms</a></td><td style="width:36px;" class="count"><c:out value="${compStateFormsDataCount}"/></td></tr>
        <tr style="height:25px;"><td class="formLink"><a href="pendingFederalForms">Pending Federal Forms</a></td><td class="count"><c:out value="${penFederalFormsDataCount}"/></td><td></td><td class="formLink"><a href="completedFederalForms">Completed Federal Forms</a></td><td class="count"><c:out value="${compFederalFormsSavingInfoCount}"/></td></tr>
        <tr style="height:25px;"><td class="formLink"><a href="pendingFreeFederalForms">Pending Free Federal Forms</a></td><td class="count"><c:out value="${pendFreeFedFormsDataCount}"/></td><td></td><td class="formLink"><a href="completedFreeFederalForms">Completed Free Federal Forms</a></td><td class="count"><c:out value="${compFreeFedFormsDataCount}"/></td></tr>
        </table>
        <table style="margin-left:33px;margin-top: 55px;">
        <tr style="height:25px;"><td style="width:237px;" class="formLink"><a href="nameAvailabilityCheck">Name Availability Check</a></td><td style="width:36px;" class="count"><c:out value="${compNameAvaCheckDataCount}"/></td><td style="width:34px;"><td class="formLink" style="width:232px;"><a href="adminStateFormsFileupload">State Forms File Upload</a></td><td style="width:36px;" class="count"><c:out value="${completedStateFormsFileUploadCount}"/></td></tr>
        <tr style="height:25px;"><td class="formLink"><a href="primaryContactInfo">User Contact Information</a></td><td class="count"><c:out value="${usersContactInformationCount}"/></td><td style="width:20px;"><td class="formLink"><a href="adminFederalFormsFileupload">Federal Forms File Upload</a></td><td class="count"><c:out value="${compFedFormsFileUploadCount}"/></td></tr>
        </table> </div> 
              
        <div style="margin-left: 370px;position: absolute;margin-top: -10px;">
        <div style="display: none;" id="sFormsBar">
        <img alt="" src="resources/images/homeimages/StateFormsFrameImg.png"/>   
        <div style="margin-top: -267px; margin-left: 14px;">
        <a href="stateFormsAttrModification" onclick="showClick(event,this,'stateFormsImg','StateFormsBarImg.png','StateFormsBarMOImg.png');" style="outline:medium none;"><img id="stateFormsImg" style="margin-left: 0px;" alt="" src="resources/images/homeimages/StateFormsBarImg.png"/></a>
        <a href="additionalFormsAttrModification" onclick="showClick(event,this,'addFormsImg','AdditionalFormsBarImg.png','AdditionalFormsBarMOImg.png');" style="outline:medium none;"><img id="addFormsImg" style="margin-left: 30px; margin-top: 41px;" alt="" src="resources/images/homeimages/AdditionalFormsBarImg.png"/></a>
        <a href="stateTaxIdFormsAttrModification" onclick="showClick(event,this,'staxidFormsImg','StateTaxIdFormsBarImg.png','StateTaxIdFormsBarMOImg.png');" style="outline:medium none;"><img id="staxidFormsImg" style="margin-left: 29px; margin-top: 82px;" alt="" src="resources/images/homeimages/StateTaxIdFormsBarImg.png"/></a>
        </div> </div>       
        <div style="display: none;" id="fFormsBar">
        <img alt="" src="resources/images/homeimages/FederalFormsFrameImg.png"/>   
        <div style="margin-top: -267px; margin-left: 14px;">
        <a href="federalTaxAttrModification" onclick="showClick(event,this,'fedtaxFormsImg','FederalTaxFormsBarImg.png','FederalTaxFormsBarMOImg.png');" style="outline:medium none;"><img id="fedtaxFormsImg" style="margin-left: 0px; margin-top: 81px;" alt="" src="resources/images/homeimages/FederalTaxFormsBarImg.png"/></a>
        <a href="sCorpAttrModification" onclick="showClick(event,this,'scorpFormsImg','SCorpFormsBarImg.png','SCorpFormsBarMOImg.png');" style="outline:medium none;"><img id="scorpFormsImg" style="margin-left:30px; margin-top: 41px;" alt="" src="resources/images/homeimages/SCorpFormsBarImg.png"/></a>
        <a href="fiveZeroOneAttrModification" onclick="showClick(event,this,'fzoFormsImg','501AppFormsBarImg.png','501AppFormsBarMOImg.png');" style="outline:medium none;"><img id="fzoFormsImg" style="margin-left:29px" alt="" src="resources/images/homeimages/501AppFormsBarImg.png"/></a>
        </div> </div></div>
        
        </div>
        
        
        </div> </div> </div> 
		<div class="clearfix"></div>
		</div> </div> </div>  <!--end col-md-12 --> 
		</div> <!-- /.contaner --> 
		</div> <!-- row -->
		</body>
</html>