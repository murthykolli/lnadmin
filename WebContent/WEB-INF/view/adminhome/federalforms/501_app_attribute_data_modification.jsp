<%--
	Document : 501_app_attribute_data_modification
	Created on : 7 May, 2014, 2:59:35 PM
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
    <link rel="shortcut icon" href="resources/images/BrowserIcon/favicon.ico" type="image/x-icon" />      
    <link rel="stylesheet" type="text/css" href="resources/css/nicebuttonsdisplay.css" />  
    
    <script type="text/javascript">
    function attributesEditIds(id){
    	var saveId = "SaveModeId"+id; var editId = "EditModeId"+id; var fieldNamesHedID = "FieldNamesHedID"+id; var fieldNamesValID = "FieldNamesValID"+id; var fieldNamesUpdatedValID = "FieldNamesUpdatedValID"+id;
        $("."+saveId).show(); $("."+editId).hide(); $("#"+fieldNamesValID).show(); $("#"+fieldNamesHedID).hide(); $("#"+fieldNamesUpdatedValID).hide(); }
    function attributesSaveIds(id){
    	var saveId = "SaveModeId"+id; var editId = "EditModeId"+id; var textAreaID = "textAreaID"+id;
        $("."+saveId).hide(); $("."+editId).show();
        var attrFieldName = document.getElementById(textAreaID).value;        
        if(attrFieldName !== ''){
        var param1 = encodeURIComponent(attrFieldName); var attrComb =  id + " _ " + param1;
        newAjaxCall(attrComb);
        }        
        function newAjaxCall(selectedData){        	
        	$.ajax({
    			type : 'POST',
    			url : "fiveZeroOneAttrFieldNameUpdate?attributeFieldName="+selectedData,
    			dataType : 'json',
    			async : true,
    			cache: false,
    			success : function(result) {
    				var return_result=JSON.stringify(result);				
    				var data = JSON.parse(return_result);
    				for(var i=0;i<data.length;i++){
    					var value = data[i].JSonObj;
    					var splitedIdValue = value.split('&&');    					
    					if(splitedIdValue[0] !== "null"){    				    	
    						var fieldNamesHedID = "FieldNamesHedID"+splitedIdValue[1]; var fieldNamesValID = "FieldNamesValID"+splitedIdValue[1]; var fieldNamesUpdatedValID = "FieldNamesUpdatedValID"+splitedIdValue[1];
    				    	$("#"+fieldNamesUpdatedValID).show(); $("#"+fieldNamesHedID).hide(); $("#"+fieldNamesValID).hide();
    				        var returnValueId = "ReturnValId"+splitedIdValue[1];    				        
    				        document.getElementById(returnValueId).innerHTML = splitedIdValue[0];    				        					
    					} } } }); }
        return false;
        }
    </script>
    </head>
    	<body>    
        <!-- Related Projects Row -->
		<div class="row body-grey">
		<div class="container">
		
		<div class="col-md-12">
		<div class="nf-white-background" style="padding-bottom: 75px;">
		<div class="docCenter docCenter2">
		<div>
		<div><font id="myAccountDocText">501(c)(3) Application - <font id="myAccountDocCategory">Attributes Modification</font></font></div>
		<div class="row">
		
		<div class="col-md-12"><br>
		<div class="orderdisplay">
		<div class="orderdisplay-head">
		<h2> Document Review -</h2>
		<h2><img class="aln-st-img-adm" src="resources/images/FormNameImage.png" alt="">&nbsp; 501(c)(3) Application &nbsp;</h2>
		
		<div class="clearfix"></div>
		</div>
		
        <%    
        if (null != session.getAttribute("FZO_Attr_Info_ID_List")) {
        ArrayList attrInfoIdList = (ArrayList) session.getAttribute("FZO_Attr_Info_ID_List");
        if (null != attrInfoIdList && attrInfoIdList.size() > 0) {
        	
        if (null != session.getAttribute("FZO_Attr_Field_Names_List")) {
        ArrayList attrFieldNamesList = (ArrayList) session.getAttribute("FZO_Attr_Field_Names_List");
        if (null != attrFieldNamesList && attrFieldNamesList.size() > 0) { 
        %>
        
        <div class="order-display">	<br><br>	
		<table class="table table-striped">
		<tbody>
        <% for(int i=0,j=0;i<attrInfoIdList.size() && j<attrFieldNamesList.size();i++,j++){ %>
        
        <tr class="tr-wd-adj-attr">   
        
        <td id="FieldNamesHedID<%=attrInfoIdList.get(i)%>" class="value-text-adj-attr" style="vertical-align:middle;"><%=attrFieldNamesList.get(i)%> </td>
        <td id="FieldNamesValID<%=attrInfoIdList.get(i)%>" style="display: none;"><textarea name="attrFieldName" id="textAreaID<%=attrInfoIdList.get(i)%>" onkeyup="CheckFirstChar(event.keyCode, this);" onkeydown="return CheckFirstChar(event.keyCode, this);" ><%=attrFieldNamesList.get(j)%></textarea></td>
        <td id="FieldNamesUpdatedValID<%=attrInfoIdList.get(i)%>" class="value-text-adj-attr" style="display: none;vertical-align:middle;"><font id="ReturnValId<%=attrInfoIdList.get(i)%>"></font> </td>
                        
        
        <td class="EditModeId<%=attrInfoIdList.get(i)%>" style="vertical-align:middle;padding-left: 22px; width: 11%;"><button class="bt-edit-color" id="RbHideID<%=i%>" type="submit" onclick="return attributesEditIds('<%=attrInfoIdList.get(i)%>');" ><span style="vertical-align: middle;">Edit</span></button></td>         
        
        <td class="SaveModeId<%=attrInfoIdList.get(i)%>" width="10%" style="display: none; vertical-align: middle; padding-left: 20px;"><button class="bt-ove-color" type="submit" onclick="return attributesSaveIds('<%=attrInfoIdList.get(j)%>');" ><span style="vertical-align: middle;">Save</span></button></td></tr>
        <tr class="tr-whitespace-adj"><td></td></tr>        
        
        <% }%>
        </tbody>
		</table>
		</div>
        <% } } } }%>         
               
            <form action="admPendingFederalForms" name="psDocmentName" method="get" id="pro-form">
            <h4 style="margin-left: 15px; margin-top: -15px; padding-bottom: 20px;">		            
			<button class="ord-wt-bg-sub-but btn-height" type="submit" onMouseOver="document.getElementById('modi-sub-but').src='resources/images/SSImgs/TextPointerWMOPreviousImg.png';" onMouseOut="document.getElementById('modi-sub-but').src='resources/images/SSImgs/TextPointerPreViousImg.png';" ><img id="modi-sub-but" src="resources/images/SSImgs/TextPointerPreViousImg.png" alt=""><span class="butt-va-mid">Back</span></button>
			</h4>            
            </form>            
            
        </div>	
		<div class="ord-bottom-bg-admn">
		<img src="resources/images/CornerFoldImg.png" alt="" class="img-responsive">
		</div> <!--   End alabama  -->
		</div> </div> </div> 
		
		<div class="clearfix"></div>
		</div> 
		</div> </div>  <!--end col-md-12 --> 
		</div> <!-- /.contaner --> 
		</div> <!-- row -->
        
        <script type="text/javascript" src="resources/scripts/json-minified.js" ></script>
		</body>
</html>