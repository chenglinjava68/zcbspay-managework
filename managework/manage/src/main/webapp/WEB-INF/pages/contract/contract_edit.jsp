<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../top.jsp"></jsp:include>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link href="<%=basePath%>js/uploadify/uploadify.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="<%=basePath%>js/uploadify/jquery.uploadify.min.js"></script>
<style type="text/css">
.left, .mid, .right {
	width: auto;
	float: left;
}

.form-control {
	border: 2px solid #A9C9E2;
}

.mid {
	padding-top: 45px;
	padding-left: 12px;
	padding-right: 12px;
}
</style>
<body>
	<style type="text/css">
table tr td {
	height: 25px;
}

table tr td input {
	height: 15px
}

table tr td select {
	height: 20px
}
</style>
	<div style="padding-top: 5px; margin-left: 5px; margin-right: 5px" id="continer">
		<div id="p" class="easyui-panel" title="查询条件"
			style="height: 130px; padding-top: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">
			<form action="" id="searchForm">
				<table width="100%">
					<tr>
						<td align="right">商户号</td>
						<td align="left" style="padding-left: 5px"><input
							id="a_merchNo" name="merchNo" /></td>
						<td align="right">合同编号</td>
						<td align="left" style="padding-left: 5px"><input
							id="a_contractNum" name="contractNum" /></td>
					</tr>
					<tr>
						<td align="right">付款人名称</td>
						<td align="left" style="padding-left: 5px"><input
							id="a_debName" name="debName" /></td>
						<td align="right">付款人账号</td>
						<td align="left" style="padding-left: 5px"><input
							id="a_debBranchCode" name="debBranchCode" /></td>
					</tr>
					<tr>
						<td align="right">收款人名称</td>
						<td align="left" style="padding-left: 5px"><input
							id="a_credName" name="credName" /></td>
						<td align="right">收款人账号</td>
						<td align="left" style="padding-left: 5px"><input
							id="a_credAccNo" name="credAccNo" /></td>
						<td align="right" colspan="3"><a href="javascript:search()"
							class="easyui-linkbutton" iconCls="icon-search">查询</a> <a
							href="javascript:resize()" class="easyui-linkbutton"
							iconCls="icon-redo">清空</a></td>
							<td align="right"></td>
					</tr>
				</table>
			</form>
		</div>
		<div style="margin-top: 5px">
			<table id="bankList">
			</table>
		</div>
	</div>
	<div id="w2" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save" style="width: 500px; height: 400px; padding: 10px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc; text-align: center">
				<form id="b_saveForm" action="" method="post">
					<input type="hidden" id="b_tId" name="tId" readonly="true"/> 
					<input type="hidden" id="b_fileAddress" name="fileAddress" readonly="true"/> 
					<table width="90%" cellpadding="2" cellspacing="2">
						<tr style="height: 30px">
							<td>商户号</td>
							<td align="left">
							<input type="text" id="b_merchNo" name="merchNo" class="easyui-validatebox" readonly="true"
								maxlength="15"/></td>
							<td>合同编号 </td>
							<td align="left">
							<input type="text" id="b_contractNum" name="contractNum" class="easyui-validatebox" readonly="true"
								maxlength="20"/></td>
						</tr>
						<tr style="height: 30px">
							<td>付款人名称</td>
							<td align="left">
							<input type="text" id="b_debName" name="debName" class="easyui-validatebox" readonly="true"
								maxlength="12"/></td>
							<td>付款人账号 </td>
							<td align="left">
							<input type="text" id="b_debAccNo" name="debAccNo" class="easyui-validatebox" readonly="true"
								maxlength="20"/></td>
						</tr>
						<tr style="height: 30px">
							<td>付款行行号</td>
							<td align="left">
							<input type="text" id="b_debBranchCode" name="debBranchCode" class="easyui-validatebox" readonly="true"
								maxlength="12"></td>
							<td>单笔金额上限 </td>
							<td align="left">
							<input type="text" id="b_debAmoLimit" name="debAmoLimit" class="easyui-validatebox" readonly="true"
								maxlength="20" /></td>
						</tr>
						<tr style="height: 30px">
							<td>金额限制类型</td>
							<td align="left">
							<select id="b_debTranLimitType" class="easyui-validatebox" missingMessage="请选择类型"
								readonly="true" name="debTranLimitType" disabled="disabled">
									<option value='00 '>--不限--</option>
									<option value='01'>按年限次</option>
									<option value='02'>按月限次</option>
									<option value='03'>总计限次</option>
							</select></td>
							<td>累计金额上限</td>
							<td align="left">
							<input type="text" id="b_debAccyAmoLimit" name="debAccyAmoLimit" class="easyui-validatebox" readonly="true"
								maxlength="12"/></td>
						</tr>
						<tr style="height: 30px">
							<td>付款次数限制类型</td>
							<td align="left">
							<select id="b_debTransLimitType" class="easyui-validatebox" missingMessage="请选择类型"
								readonly="true" name="debTransLimitType" disabled="disabled">
									<option value='00 '>--不限--</option>
									<option value='01'>按年限次</option>
									<option value='02'>按月限次</option>
									<option value='03'>总计限次</option>
							</select></td>
							<td>付款次数限制</td>
							<td align="left">
							<input type="text" id="b_debTransLimit" name="debTransLimit" class="easyui-validatebox" readonly="true"
								maxlength="12"/></td>
							<td align="left"></td>
						</tr>
						<tr style="height: 30px">
							<td>收款人名称</td>
							<td align="left">
							<input type="text" id="b_credName" name="credName" class="easyui-validatebox" readonly="true"
								maxlength="12"/></td>
							<td>收款人账号 </td>
							<td align="left">
							<input type="text" id="b_credAccNo" name="credAccNo" class="easyui-validatebox" readonly="true"
								maxlength="20"/></td>
						</tr>
						<tr style="height: 30px">
							<td>收款行行号</td>
							<td align="left">
							<input type="text" id="b_credBranchCode" name="credBranchCode" class="easyui-validatebox" readonly="true"
								maxlength="12"/></td>
							<td>单笔金额上限 </td>
							<td align="left">
							<input type="text" id="b_credAmoLimit" name="credAmoLimit" class="easyui-validatebox" readonly="true"
								maxlength="20"/></td>
						</tr>
						<tr style="height: 30px">
							<td>金额限制类型</td>
							<td align="left">
							<select id="b_credTranLimitType" class="easyui-validatebox" missingMessage="请选择类型"
								readonly="true" name="credTranLimitType" disabled="disabled">
									<option value='00 '>--不限--</option>
									<option value='01'>按年限次</option>
									<option value='02'>按月限次</option>
									<option value='03'>总计限次</option>
							</select></td>
							<td>累计金额上限</td>
							<td align="left">
							<input type="text" id="b_credAccuAmoLimit" name="credAccuAmoLimit" class="easyui-validatebox" readonly="true"
								maxlength="12"/></td>
						</tr>
						<tr style="height: 30px">
							<td>收款次数限制类型</td>
							<td align="left">
							<select id="b_credTransLimitType" class="easyui-validatebox" missingMessage="请选择类型"
								readonly="true" name="credTransLimitType" disabled="disabled">
									<option value='00 '>--不限--</option>
									<option value='01'>按年限次</option>
									<option value='02'>按月限次</option>
									<option value='03'>总计限次</option>
							</select></td>
							<td>收款次数限制</td>
							<td align="left">
							<input type="text" id="b_credTransLimit" name="credTransLimit" class="easyui-validatebox" readonly="true"
								maxlength="12"/></td>
						</tr>
						<tr style="height: 30px">
							<td align="center">合约开始日期</td>
							<td align="left"><input name="signDate" maxlength="12" type="test" id="b_startDate" readonly="true"/></td>
							<td align="center">合约终止日期</td>
							<td align="left"><input class="easyui-validatebox" maxlength="12" type="test" name="expiryDate" id="b_endDate" readonly="true"/></td>
						</tr>
						<tr style="height: 30px">
							<td>合同类型</td>
							<td align="left">
							<select id="b_contractType" class="easyui-validatebox" missingMessage="请选择类型"
								readonly="true" name="b_contractType" disabled="disabled">
									<option value=''>请选择合同类型</option>
									<option value='CT00'>代收协议</option>
									<option value='CT01'>代付协议</option>
									<option value='CT02'>代收付协议</option>
							</select></td>
							<td align="center">合同附件</td>
							<td align="left">
								<div id="signfileOpp_span" class="easyui-validatebox" readonly="true"></div> 
							</td>
						</tr>
						<tr style="height: 30px">
							<td>备注</td>
							<td align="left" colspan="3">
							<textarea rows="3" cols="81" id="b_notes" maxlength="64" name="notes" style="margin: 5px" readonly="true"></textarea></td>
						</tr>
						<tr style="height: 30px;">
							<td>审核意见</td>
							<td colspan="3" align="left">
							<textarea rows="3" cols="81" style="margin: 5px;" maxlength="60" id="STOPINION"></textarea>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div region="south" border="false" style="text-align: center; padding: 5px 0;">
				<a href="javascript:merchAudit('0');" id="button_ins1" class="easyui-linkbutton" iconCls="icon-ok">通过</a>
				<a href="javascript:merchAudit('1');" id="button_ins3" class="easyui-linkbutton" iconCls="icon-no">不通过</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeAdd()">返回</a>
			</div>
		</div>
	</div>
</body>
</body>
<script>
  	var width = $("#continer").width();
		$(function(){
			$('#bankList').datagrid({
				title:'合同列表',
				iconCls:'icon-save',
				height:600,
				nowrap: false,
				striped: true,
				singleSelect:true,
				url:'contract/queryAudit',
				remoteSort: false,
				columns:[[
					{field:'MERCHNO',title:'商户号',align:'center',width:130},
					{field:'CONTRACTNUM',title:'合同编号',width:130,align:'center'},
					{field:'DEBTORNAME',title:'付款人',align:'center',width:100},
					{field:'CREDITORNAME',title:'收款人',width:120,align:'center'},
					{field:'CONTRACTTYPE',title:'合同类型',width:100,align:'center',
						formatter:function(value,rec){
							if(value=="CT00"){
								return "代收";
							}else if(value=="CT01"){
								return "代付";
							}else if(value=="CT02"){
								return "代收付";
							}
						}
					},
					{field:'STATUS',title:'状态',width:60,align:'center',
						formatter:function(value,rec){
							if(value=="00"){
								return "有效";
							}else if(value=="10" || value=="20"){
								return "待审";
							}else if(value=="19" || value=="29"){
								return "审核未通过";
							}else if(value=="89"){
								return "过期失效";
							}else if(value=="99"){
								return "撤销";
							}
						}
					},
					{field:'TID',title:'操作',align:'center',width:160,rowspan:2,
						formatter:function(value,rec){
							if(rec.STATUS=="00"){
								return '<a href="javascript:deleteUser('+value+')" style="color:blue;margin-left:10px">注销</a>'+
								'<a href="javascript:findById('+value+')" style="color:blue;margin-left:10px">详情</a>'
							}else if(rec.STATUS=="10" || rec.STATUS=='20'){
								return '<a href="javascript:findById('+value+')" style="color:blue;margin-left:10px">审核</a>';
							}
						}}
					]],
				pagination:true,
				rownumbers:true
			});
		});

		function resize(){
			$('#searchForm :input').val('');
		}
		
		function closeAdd(){
			$('#w').window('close');
			$('#w2').window('close');
			$('#w3').window('close');
			
		}		
		function search(){
			var data={'merchNo':$('#a_merchNo').val(),'contractNum':$('#a_contractNum').val(),
					'debName':$("#a_debName").val(),'credName':$("#a_credName").val(),
					'debBranchCode':$("#a_debBranchCode").val(),'credAccNo':$("#a_credAccNo").val()};
			$('#bankList').datagrid('load',data);
		}
		function findById(tId){
			$.ajax({
			   type: "POST",
			   url: "contract/findById",
			   data: "tId="+tId,
			   async: false,
			   dataType:"json",
			   success: function(json){
				   var tId = json.tId;
				   $("#b_tId").val(json.tId);
				   $("#b_merchNo").val(json.merchNo);
				   $("#b_contractNum").val(json.contractNum);
				   $("#b_debName").val(json.debName);
				   $("#b_debAccNo").val(json.debAccNo);
				   $("#b_debBranchCode").val(json.debBranchCode);
				   $("#b_credName").val(json.credName);
				   $("#b_credAccNo").val(json.credAccNo);
				   $("#b_contractType").val(json.contractType);
				   $("#b_credBranchCode").val(json.credBranchCode);
				   $("#b_debAmoLimit").val(json.debAmoLimit);
				   $("#b_debTranLimitType").val(json.debTranLimitType);
				   $("#b_debAccyAmoLimit").val(json.debAccyAmoLimit);
				   $("#b_debTransLimitType").val(json.debTransLimitType);
				   $("#b_debTransLimit").val(json.debTransLimit);
				   $("#b_credAmoLimit").val(json.credAmoLimit);
				   $("#b_credTranLimitType").val(json.credTranLimitType);
				   $("#b_credAccuAmoLimit").val(json.credAccuAmoLimit);
				   $("#b_credTransLimitType").val(json.credTransLimitType);
				   $("#b_credTransLimit").val(json.credTransLimit);
				   $("#b_startDate").val(json.signDate);
				   $("#b_endDate").val(json.expiryDate);
				   $("#b_notes").val(json.notes);
				   $("#b_fileAddress").val(json.fileAddress);
				   initCertUrl(tId);
			   }
			});
			$('#w2').window({
				title: '合同详情',
				top:100,
				width: 800,
				modal: true,
				minimizable:false,
				collapsible:false,
				maximizable:false,
				shadow: false,
				closed: false,
				height: 580
			});
		}

		$(function(){
			$("input[id*='_cert_img']").each(function(){
				  var tId = $("#b_tId");
				  var _this = $(this);
				  var id = _this.attr('id');
				  var certType = id.substring(0,id.indexOf('_cert_img'));
				  var certSpan = $('#fileAddress_span');
				  $(this).uploadify({
						'auto' : true,
						'swf' : '<%=basePath%>js/uploadify/uploadify.swf', 
						'uploader': '<%=basePath%>contract/fileUpload',
						'formData' : {'tId':tId,'fileAddress':fileAddress},
						'fileObjName': 'headImage',
						 'method'   : 'post',
						//可选  
// 						'height': 20,
						//可选  
						'width': 120,
						//设置允许上传的文件格式  
// 						'fileExt'   : '*.jpg;*.gif;*.png',  
						//设置允许上传的文件格式后，必须加上下面这行代码才能帮你过滤  
						//'fileDesc'    : 'Image Files',  
						//允许连续上传多个文件  
						'multi': 'false',
						//一次性最多允许上传多少个,不设置的话默认为999个  
						'queueSizeLimit': 1,
						//每个文件允许上传的大小(字节)  
						'sizeLimit'   : 1024*1024*10,  //1M=1024K=1024*1024Byte
						'buttonText'     : '选择文件' ,
						'onUploadError' : function(file, errorCode, errorMsg, errorString) {
							$.messager.alert('提示', '上传失败');
				        },
						 'onUploadSuccess' : function(file, data, response) {
					         	var isSucc = false;   
					         	var retInfo;
					         	jsonRet = eval('(' + data + ')');
					         	var URL =jsonRet.path;
						 		$("#fileAddress").val(URL);
							 	if(!response||data.indexOf("status")==-1){
							 		isSucc = false; 
					            }else{
						            
						            if(jsonRet.status=='OK'){
						            	isSucc = true;  
						            }
					            }
							 	if(isSucc){
							 		retInfo = "上传成功";
							 		certSpan.html('<a href="'+URL+'" target="view_window" style="font-size: 12px;color:blue">点击查看</a>');
							 	}else{retInfo = "上传失败";}
							 	
					            $.messager.alert('提示', retInfo);
					    }, 
				        'onFallback' : function() {
				        	$.messager.alert('提示', '检测到flash控件不可用,请确认当前浏览器支持flash控件');
				        }
					});
			});
			 initCertUrl();
		  });
		function initCertUrl(tId){
			$("input[id='b_fileAddress']").each(function(){
				var _this = $(this);
				if(_this.val()==''){
					return;
				}
				var id = _this.attr('id');
				var certType = id.substring(0,id.indexOf('b_fileAddress'));
				var certSpan = $('#signfileOpp_span');
				$.ajax({
					type: "POST",
					url: "contract/downloadImgUrl",
					data: "tId=" + tId,
					dataType: "json",
					success: function(json) {
						 if(json.status=='OK'){
							 var URL = json.url;
							 certSpan.html('<a href="'+URL+'" target="view_window" style="font-size: 12px;color:blue">点击查看</a>');
						 }else if(json.status=='notExist'){
							 certSpan.html('暂无可查看文件');
						 } else{
							 certSpan.html('查询失败');
						 }
					}
				}); 
			});
		}
		
		function merchAudit(result) {
			$("#button_ins1").linkbutton('disable');
			$("#button_ins3").linkbutton('disable');
			var tId = $("#b_tId").val();
			var stexaOpt = $("#STOPINION").val();
			$.ajax({
				type: "POST",
				url: "contract/audit?isAgree=" + result + "&tId=" + tId,
				data: "stexaOpt=" + encodeURI(stexaOpt),
				dataType: "json",
				success: function(json) {
			    	$.each(json, function(key,value){
			    		if(value.RET == "succ"){
		    				$.messager.alert('提示',"操作成功!");
			    			search();
				    		closeAdd();
				    	}else{
				    		$.messager.alert('提示',value.INFO); 
				    		search();
				    		closeAdd();
				    	}
			    		$("#button_ins1").linkbutton('enable');
						$("#button_ins3").linkbutton('enable');		
					}) 
				}
			});
		}
	</script>
</html>
