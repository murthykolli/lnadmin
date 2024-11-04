<%--
	Document : state_tax_id_attribute_data_modification
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
    $(document).ready(function(){    	
        var stateName = '<c:out value="${stateName}"/>'; var formName = '<c:out value="${formName}"/>'; var stateNameVal = document.getElementById("stateNameId").value; var formNameVal = document.getElementById("formNameId").value;
        if(stateName !== "" && formName !== "" && stateNameVal === "" && formNameVal === ""){
        	$("[name=stateName]").val(stateName); $("#StateNameHDId").html(stateName); $("#FormNameHDId").html(formName); $("[name=formName]").val(formName); $("#FormsDropDownId").show();
        	newAjaxStateCall(stateName);
        	$("#AttributesDataDisplayID").show(); $("#AttrDisplayFooterID").show(); $("#AttrDisplayButtID").show(); } });
    </script>
        
    <script type="text/javascript">
    function attributesEditIds(id){
    	var saveId = "SaveModeId"+id; var editId = "EditModeId"+id; var fieldNamesHedID = "FieldNamesHedID"+id; var fieldNamesValID = "FieldNamesValID"+id; var fieldNamesUpdatedValID = "FieldNamesUpdatedValID"+id;
        $("."+saveId).show(); $("."+editId).hide(); $("#"+fieldNamesValID).show(); $("#"+fieldNamesHedID).hide(); $("#"+fieldNamesUpdatedValID).hide(); }
    
    function attributesSaveIds(id){
    	var saveId = "SaveModeId"+id; var editId = "EditModeId"+id; var textAreaID = "textAreaID"+id;
        $("."+saveId).hide(); $("."+editId).show();
        var attrFieldName = document.getElementById(textAreaID).value;        
        if(attrFieldName !== ''){
        var param1 = encodeURIComponent(attrFieldName);	
        var attrComb =  id + " _ " + param1;
        newAjaxCall(attrComb);
        }
        
        function newAjaxCall(selectedData){        	
        	$.ajax({
    			type : 'POST',
    			url : "stateFormsAttrFieldNameUpdate?attributeName="+selectedData,
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
    						var fieldNamesHedID = "FieldNamesHedID"+splitedIdValue[1];
    				    	var fieldNamesValID = "FieldNamesValID"+splitedIdValue[1];
    				    	var fieldNamesUpdatedValID = "FieldNamesUpdatedValID"+splitedIdValue[1];    						
    				    	$("#"+fieldNamesUpdatedValID).show();
    				        $("#"+fieldNamesHedID).hide();
    				        $("#"+fieldNamesValID).hide();    				        
    				        var returnValueId = "ReturnValId"+splitedIdValue[1];    				        
    				        document.getElementById(returnValueId).innerHTML = splitedIdValue[0];    				        					
    					} } } }); }
        return false;
        }
    
 /* State and Form selection code */ 
    	function stateNameValidation(){    		
            var stateName = document.getElementById("stateNameId").value;
            var selectedstate = '<c:out value="${stateName}"/>';
    	        if(stateName !== ""){
    	        	if(selectedstate !== stateName){
    					$("#FormsDropDownId").hide(); $("#AttributesDataDisplayID").hide(); $("#AttrDisplayFooterID").hide(); $("#AttrDisplayButtID").hide();
    				}
    	        	newAjaxStateCall(stateName);    	        	
    	        } else{    	        	
    	        	$("#FormsDropDownId").hide(); $("#AttributesDataDisplayID").hide(); $("#AttrDisplayFooterID").hide(); $("#AttrDisplayButtID").hide();
    	        } } 
 
    	function formNameValidation(){
    		var stateName = document.getElementById("stateNameId").value; var formName = document.getElementById("formNameId").value; var stateName1 = '<c:out value="${stateName}"/>'; var formName1 = '<c:out value="${formName}"/>'; var formHeading = '----Select Form Name---';
            $("#StateNameHDId").html(stateName); $("#FormNameHDId").html(formName);
    	        if(stateName !== "" && formName !== "" && formName !== formHeading && formName1 !== formName){    	        	
    	        	var selectedState = stateName+" _ "+formName;    	        	
    	        	newAjaxFormCall(selectedState);
    	        } else if(stateName !== "" && formName !== "" && formName1 === formName && stateName1 !== stateName ){ 
    	        	var selectedState = stateName+" _ "+formName;    	        	
    	        	newAjaxFormCall(selectedState);
    	        } else if(stateName !== "" && formName !== "" && formName1 === formName && stateName1 === stateName){
    	        	$("#AttributesDataDisplayID").show(); $("#AttrDisplayFooterID").show(); $("#AttrDisplayButtID").show();
    			} else{    	        	
    	        	$("#AttributesDataDisplayID").hide(); $("#AttrDisplayFooterID").hide(); $("#AttrDisplayButtID").hide();
    	        	
    	        } }
    	
    	function removeDuplicates(obj){ var uniques=[]; var stringify={};
            for(var i=0;i<obj.length;i++){ var keys = Object.keys(obj[i]); var str='';
            for(var j=0;j<keys.length;j++){       
            str+= JSON.stringify(keys[j]);
            str+= JSON.stringify(obj[i][keys[j]]);
            }
            if(!stringify.hasOwnProperty(str)){ uniques.push(obj[i]); stringify[str]=true;
            } }
            uniques = JSON.stringify(uniques);        
            return uniques;
        }
    	
    	function newAjaxStateCall(selectedstate){   
        	$.ajax({
    			type : 'POST',
    			url : "jSonFormSelectionWithState?typeOfDocument="+selectedstate,
    			dataType : 'json',
    			async : true,
    			cache: false,
    			success : function(result) {
    				$("#FormsDropDownId").show(); $('#formNameId').empty();
    				var jsonStrObj = removeDuplicates(result);				
    				var data = JSON.parse(jsonStrObj);
    				var formName = '<c:out value="${formName}"/>';
    				var stateName = '<c:out value="${stateName}"/>';
    				var selectHeading;
    				if(formName !== '' && selectedstate === stateName){ selectHeading = formName;
    				} else{ selectHeading = '----Select Form Name---';
    				}
    				var selectHeadingVal = $("<option value='" + selectHeading + "'>" + selectHeading + "</option>");
    				$('#formNameId').append(selectHeadingVal);    				
    				for(var i=0;i<data.length;i++){
    	        		var newOption = $("<option value='" + data[i].jSonFormName+ "'>" + data[i].jSonFormName + "</option>");        		
    			  		$('#formNameId').append(newOption);		  		
    				} } }); } 
    	
    	function newAjaxFormCall(selectedstate){    		
        	$.ajax({
    			type : 'POST',
    			url : "jSonDataSelectionWithStateAndForm?typeOfDocument="+selectedstate,
    			dataType : 'json',
    			async : true,
    			cache: false,
    			success : function(result) {
    				window.location.href = "jSonStateTaxIdFormsAttrDataDisplay";    				
    			} }); }    	
    </script>
    </head>    
        <body>    
        <!-- Related Projects Row -->
		<div class="row body-grey">
		<div class="container">
		
		<div class="col-md-12">
		<div class="nf-white-background" style="padding-bottom: 75px;min-height: 400px; height: auto;">
		<div class="docCenter docCenter2">
		<div>
		<div><font id="myAccountDocText">State Tax Id Forms - <font id="myAccountDocCategory">Attributes Modification</font></font></div>
		<div class="row" style="margin-top: 20px;">		
        <div class="col-md-4">
	    <table>        
	        <tr class="bsLabel"><td id="typeOfDocLabel" style="font: 500 1.12em/1em sans-serif; color: #1c1c1c;">Select State Name:</td></tr>
	        <tr style="height: 5px;"><td></td></tr>
	        <tr><td> <select id="stateNameId" name="stateName" style="height:25px;width:210px;background-color: white;border-color: #f4f4f4;border-radius: 2px;box-shadow: 3px 3px 1px gray;border: solid 1px #595959; -webkit-appearance: none;" onclick="return stateNameValidation();">
	        <option value="">---Select State Name---</option>
		    <c:forEach var="stateNameList" items="${stateNamesList}">
		    <option value="${stateNameList}">${stateNameList}</option>
		    </c:forEach>	        
		    </select> </td></tr>
		</table>
		</div>
		
		<div class="col-md-8">		
		<table id="FormsDropDownId" style="display: none;">        
	        <tr class="bsLabel"><td id="formlabel" style="font: 500 1.12em/1em sans-serif; color: #1c1c1c;">Select Form Name: </td></tr>
	        <tr style="height: 5px;"><td></td></tr>
	        <tr><td> <select id="formNameId" name="formName" style="height:25px;width:210px;background-color: white;border-color: #f4f4f4;border-radius: 2px;box-shadow: 3px 3px 1px gray;border: solid 1px #595959; -webkit-appearance: none;" onclick="return formNameValidation();">
	        <option value="">----Select Form Name---</option>        
			</select> </td></tr>
		</table>
		</div>
		
		<div class="col-md-12" id="AttributesDataDisplayID" style="display: none;margin-top: 15px;"><br>
		<div class="orderdisplay">
		<div class="orderdisplay-head">
		<h2> Document Review -</h2>
		<h2><img class="aln-st-img" src="resources/images/StateNameImage.png" alt="">&nbsp; <font id="StateNameHDId"></font> &nbsp; &nbsp;<img class="aln-st-img" src="resources/images/FormNameImage.png" alt="">&nbsp; <font id="FormNameHDId"></font> &nbsp;</h2>
		
		<div class="clearfix"></div>
		</div>
		
        <%    
        if (null != session.getAttribute("State_Tax_Id_Forms_Attr_Info_ID_List")) {
        ArrayList attrInfoIdList = (ArrayList) session.getAttribute("State_Tax_Id_Forms_Attr_Info_ID_List");
        if (null != attrInfoIdList && attrInfoIdList.size() > 0) {

        if (null != session.getAttribute("State_Tax_Id_Forms_Attr_Field_Names_List")) {
        ArrayList attrFieldNamesList = (ArrayList) session.getAttribute("State_Tax_Id_Forms_Attr_Field_Names_List");
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
        <br>    
        </div>	
		<div class="ord-bottom-bg-stadmn">
		<img src="resources/images/CornerFoldImg.png" alt="" class="img-responsive">
		</div> <!--   End alabama  -->
		
			<form action="admCompStateForms" name="psDocmentName" method="get" id="pro-form">
            <h4 style="margin-left: 0px; margin-top: 0px;">		            
			<button class="ord-wt-bg-sub-but btn-height" type="submit" onMouseOver="document.getElementById('modi-sub-but').src='resources/images/SSImgs/TextPointerWMOPreviousImg.png';" onMouseOut="document.getElementById('modi-sub-but').src='resources/images/SSImgs/TextPointerPreViousImg.png';" ><img id="modi-sub-but" src="resources/images/SSImgs/TextPointerPreViousImg.png" alt=""><span class="butt-va-mid">Back</span></button>
			</h4>            
            </form>
            
		</div> </div> </div> 
		
		<div class="clearfix"></div>
		</div> 
		</div> </div>  <!--end col-md-12 --> 
		</div> <!-- /.contaner --> 
		</div> <!-- row -->
        
        <script type="text/javascript" src="resources/scripts/json-minified.js" ></script>
		</body>
</html>