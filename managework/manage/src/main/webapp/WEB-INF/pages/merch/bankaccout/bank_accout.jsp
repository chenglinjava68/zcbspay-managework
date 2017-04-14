<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../../top.jsp"></jsp:include>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript"
	src="<%=basePath%>/js/extendsValidator_1.0_20151215.js"></script>
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
	height: 25px
}

table tr td input {
	height: 15px
}

table tr td select {
	height: 20px
}
</style>
	<div style="padding-top: 5px; margin-left: 5px; margin-right: 5px"
		id="continer">
		<div id="p" class="easyui-panel" title="查询条件"
			style="height: 130px; padding-top: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">
			<form action="" id="searchForm">
				<table width="100%">
					<tr>
						<td align="right">商户号</td>
						<td align="left" style="padding-left: 5px"><input
							id="merchNo" name="merchNo" /></td>
						<td align="right">账户号</td>
						<td align="left" style="padding-left: 5px"><input
							id="accoutNo" name="accoutNo" /></td>
						<td align="right">账户名称</td>
						<td align="left" style="padding-left: 5px"><input
							id="accoutName" name="accoutName" /></td>
						
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
	<div id="w" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save" style="width: 500px; height: 400px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc; font-size: 12px; text-align: center">
				<form id="saveForm" action="bankaccout/save" method="post">
					<input type="hidden" id="bankProvince" name="bankProvince" />
					<input type="hidden" id="bankCity" name="bankCity" />
					<table width="90%" cellpadding="2" cellspacing="2">
						<tr style="height: 25px">
							<td width="15%">银行账号</td>
							<td align="left">
							<input type="text" id="accoutNoa" name="accoutNo" class="easyui-validatebox" required="true"
								maxlength="7" missingMessage="请输入银行账号" validType="bankcard"/></td>
							<td>银行账户名称</td>
							<td align="left">
							<input type="text" id="accoutNamea" name="accoutName" class="easyui-validatebox" required="true"
								maxlength="20" missingMessage="请输入银行账户名称" validType="debName"/></td>
						</tr>
						<tr style="height: 25px">
							<td>商户号</td>
							<td align="left">
							<input type="text" id="merchNoa" name="merchNo" class="easyui-validatebox" required="true"
								maxlength="32" missingMessage="请输入商户号" validType="merchno"/></td>
							<td>协议类型</td>
							<td align="left">
							<select id="protocoltype" class="easyui-validatebox" missingMessage="请选择协议类型"
								required="true" name="protocoltype">
									<option value=''>--请选择协议类型--</option>
									<option value='1'>代理收款</option>
									<option value='2'>代理付款</option>
							</select></td>
						</tr>
						<tr style="height: 25px">
							<td>支行行号</td>
							<td align="left">
							<input type="text" id="bankNode" name="bankNode" class="easyui-validatebox" required="true"
								maxlength="32" missingMessage="请输入支行行号" onchange="showBankInfo()"/></td>
							<td>清算行号</td>
							<td align="left">
							<input type="text" id="bankCode" name="bankCode" class="easyui-validatebox" required="true"
								maxlength="32"  /></td>
							<td></td>
							<td align="left"></td>
						</tr>
						<tr style="height: 25px">
							<td>所属省</td>
							<td align="left">
							<input type="text" id="a_bankProvince" name="a_bankProvince" class="easyui-validatebox" required="true"
								maxlength="32"  onkeyup="value=value.replace(/<[^<]+>/g,'')"/></td>
							</select></td>
							<td>所属市</td>
							<td align="left">
							<input type="text" id="a_bankCity" name="a_bankCity" class="easyui-validatebox" required="true"
								maxlength="32"  onkeyup="value=value.replace(/<[^<]+>/g,'')"/></td>
							<td align="left"></td>
						</tr>
						<tr style="height: 25px">
							<td>渠道代码</td>
							<td align="left">
							<input type="text" id="channelCode" name="channelCode" class="easyui-validatebox"
								maxlength="32" missingMessage="请输入登录账号" onkeyup="value=value.replace(/<[^<]+>/g,'')"/></td>
							<td align="left"></td>
						</tr>
						<tr style="height: 25px">
							<td>备注</td>
							<td align="left" colspan="3">
							<textarea rows="3" cols="81" id="notes" maxlength="64" name="notes" style="resize: none;"
									onkeyup="value=value.replace(/<[^<]+>/g,'')"></textarea></td>

						</tr>
					</table>
				</form>
			</div>
			<div region="south" border="false" style="text-align: center; padding: 5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:saveUser()" id="btn_submit" onclick="">提交</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="closeAdd()">取消</a>
			</div>
		</div>
	</div>
	<div id="w2" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save" style="width: 500px; height: 400px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc; font-size: 12px; text-align: center">
				<form id="b_saveForm" action="bankaccout/eidtBankAccount" method="post">
					<input type="hidden" id="b_tId" name="tId" /> 
					<input type="hidden" id="b_status" name="status" />
					<input type="hidden" id="b_bankProvince" name="bankProvince" />
					<input type="hidden" id="b_bankCity" name="bankCity" />
					<table width="90%" cellpadding="2" cellspacing="2">
						<tr style="height: 25px">
							<td width="15%">银行账号</td>
							<td align="left">
							<input type="text" id="b_accoutNoa" name="accoutNo" class="easyui-validatebox" required="true"
								maxlength="7" missingMessage="请输入银行账号" validType="bankcard"/></td>
							<td>银行账户名称</td>
							<td align="left">
							<input type="text" id="b_accoutNamea" name="accoutName" class="easyui-validatebox" required="true"
								maxlength="20" missingMessage="请输入银行账户名称" validType="debName"/></td>
						</tr>
						<tr style="height: 25px">
							<td>商户号</td>
							<td align="left">
							<input type="text" id="b_merchNoa" name="merchNo" class="easyui-validatebox" required="true"
								maxlength="32" missingMessage="请输入商户号" validType="merchno"/></td>
							<td>协议类型</td>
							<td align="left">
							<select id="b_protocoltype" class="easyui-validatebox" missingMessage="请选择所属机构"
								required="true" name="protocoltype">
									<option value=''>--请选择协议类型--</option>
									<option value='1'>代理收款</option>
									<option value='2'>代理付款</option>
							</select></td>
						</tr>
						<tr style="height: 25px">
							<td>支行行号</td>
							<td align="left">
							<input type="text" id="b_bankNode" name="bankNode" class="easyui-validatebox" required="true"
								maxlength="32" missingMessage="请输入支行行号" onchange="showBankInfo()"/></td>
							<td>清算行号</td>
							<td align="left">
							<input type="text" id="b_bankCode" name="bankCode" class="easyui-validatebox" required="true"
								maxlength="32"  onkeyup="value=value.replace(/<[^<]+>/g,'')"/></td>
							<td></td>
							<td align="left"></td>
						</tr>
						<tr style="height: 25px">
							<td>所属省</td>
							<td align="left">
							<input type="text" id="c_bankProvince" name="a_bankProvince" class="easyui-validatebox" required="true"
								maxlength="32"  onkeyup="value=value.replace(/<[^<]+>/g,'')"/></td>
							</select></td>
							<td>所属市</td>
							<td align="left">
							<input type="text" id="c_bankCity" name="a_bankCity" class="easyui-validatebox" required="true"
								maxlength="32"  onkeyup="value=value.replace(/<[^<]+>/g,'')"/></td>
							<td align="left"></td>
						</tr>
						<tr style="height: 25px">
							<td>渠道代码</td>
							<td align="left">
							<input type="text" id="b_channelCode" name="channelCode" class="easyui-validatebox"
								maxlength="32" missingMessage="请输入渠道代码" onkeyup="value=value.replace(/<[^<]+>/g,'')"/></td>
							<td align="left"></td>
						</tr>
						<tr style="height: 25px">
							<td>备注</td>
							<td align="left" colspan="3">
							<textarea rows="3" cols="81" id="b_notes" maxlength="64" name="notes" style="resize: none;"
									onkeyup="value=value.replace(/<[^<]+>/g,'')"></textarea></td>

						</tr>
					</table>
				</form>
			</div>
			<div region="south" border="false" style="text-align: center; padding: 5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:saveBank()" id="b_btn_submit" onclick="">提交</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="closeAdd()">取消</a>
			</div>
		</div>
	</div>
	<div id="w3" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save" style="width: 500px; height: 400px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc; font-size: 12px; text-align: center">
				<form id="" action="" method="post">
					<input type="hidden" id="b_tId" name="tId" value="${json.tId}" readonly="true"/> 
					<table width="90%" cellpadding="2" cellspacing="2">
						<tr style="height: 25px">
							<td width="15%">银行账号</td>
							<td align="left">
							<input type="text" id="d_accoutNoa" name="accoutNo" readonly="true"/></td>
							<td>银行账户名称</td>
							<td align="left">
							<input type="text" id="d_accoutNamea" name="accoutName" readonly="true"/></td>
						</tr>
						<tr style="height: 25px">
							<td>商户号</td>
							<td align="left">
							<input type="text" id="d_merchNoa" name="merchNo" readonly="true"/></td>
							<td>协议类型</td>
							<td align="left">
								<select id="d_protocoltype" name="protocoltype" disabled="disabled">
										<option value=''>--请选择协议类型--</option>
										<option value='1'>代理收款</option>
										<option value='2'>代理付款</option>
								</select>
							</td>
						</tr>
						<tr style="height: 25px">
							<td>支行行号</td>
							<td align="left">
							<input type="text" id="d_bankNode" name="bankNode" readonly="true"/></td>
							<td>清算行号</td>
							<td align="left">
							<input type="text" id="d_bankCode" name="bankCode" readonly="true"/></td>
						</tr>
						<tr style="height: 25px">	
							<td>所属省</td>
							<td align="left">
							<input type="text" id="d_bankProvince" name="a_bankProvince" readonly="true"/></td>
							<td>所属市</td>
							<td align="left">
							<input type="text" id="d_bankCity" name="a_bankCity" readonly="true"/></td>
						</tr>
						<tr style="height: 25px">
							<td>渠道代码</td>
							<td align="left">
							<input type="text" id="d_channelCode" name="channelCode" readonly="true"/></td>
						</tr>
						<tr style="height: 25px">
							<td>备注</td>
							<td align="left" colspan="3">
							<textarea rows="3" cols="81" id="d_notes" maxlength="64" name="notes" style="resize: none;"
									onkeyup="value=value.replace(/<[^<]+>/g,'')" readonly="true"></textarea></td>

						</tr>
					</table>
				</form>
			</div>
			<div region="south" border="false" style="text-align: center; padding: 5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="closeAdd()">返回</a>
			</div>
		</div>
	</div>

</body>
</body>
<script>
  	var width = $("#continer").width();
		$(function(){
			$('#bankList').datagrid({
				title:'银行账户列表',
				iconCls:'icon-save',
				height:600,
				nowrap: false,
				striped: true,
				singleSelect:true,
				url:'bankaccout/query',
				remoteSort: false,
				columns:[[
					{field:'MERCHNO',title:'商户号',align:'center',width:130},
					{field:'ACCOUNTNO',title:'银行账号',width:130,align:'center'},
					{field:'BANKNODE',title:'支行行号',align:'center',width:100},
					{field:'BANKCODE',title:'清算行号',width:120,align:'center'},
					{field:'ACCOUNTNAME',title:'银行账户名称',width:100,align:'center'},
					{field:'PROTOCOLTYPE',title:'协议类型',width:100,align:'center',
						formatter:function(value,rec){
							if(value=="1"){
								return "代理收款";
							}else{
								return "代理付款";
							}
						}
					},
					{field:'CHANNELCODE',title:'渠道代码',width:100,align:'center'},
					{field:'STATUS',title:'状态',width:60,align:'center',
						formatter:function(value,rec){
							if(value=="00"){
								return "正常";
							}else{
								return "注销";
							}
						}
					},
					{field:'TID',title:'操作',align:'center',width:120,rowspan:2,
						formatter:function(value,rec){
							if(rec.STATUS=="00"){
								return '<a href="javascript:showUser('+value+')" style="color:blue;margin-left:10px">修改</a>'+
								'<a href="javascript:deleteUser('+value+')" style="color:blue;margin-left:10px">注销</a>'+
								'<a href="javascript:findById('+value+')" style="color:blue;margin-left:10px">详情</a>'
							}else{
								return '';
							}
							
					}}
				]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					id:'btnadd',
					text:'新增银行账户',
					iconCls:'icon-add',
					handler:function(){
						$("#user_code").removeAttr('readonly');
						showAdd(0);
						$("#saveForm").attr("action","bankaccout/save");
					}
				}]
			});
		});

		function showCity(CCode){
			$.ajax({
			   type: "POST",
			   url: "bankaccout/queryCity",
			   data: "CCode="+CCode,
			   dataType:"json",
			   success: function(json){
				   var cname =json.cname;
				   var cid=json.ccode;
				   var pId = json.pid;
					$.ajax({
					   type: "POST",
					   url: "bankaccout/queryProvince",
					   data: "pId="+pId,
					   dataType:"json",
					   success: function(json){
						   $("#a_bankCity").val(cname);
						   $("#bankCity").val(cid);
						   $("#a_bankProvince").val(json.pname);
						   $("#bankProvince").val(json.pid);
						   $("#c_bankCity").val(cname);
						   $("#d_bankCity").val(cname);
						   $("#b_bankCity").val(cid);
						   $("#c_bankProvince").val(json.pname);
						   $("#d_bankProvince").val(json.pname);
						   $("#b_bankProvince").val(json.pid);
					   }
					});
			   }
			});	
		}
		
		function resize(){
			$('#searchForm :input').val('');
		}
		
		function showAdd(num){
			$("#saveForm").attr("action","bankaccout/save");
			$('#saveForm :input').val('');
			$('#w').window({
				title: '新增银行账户信息',
				top:100,
				width: 600,
				modal: true,
				minimizable:false,
				collapsible:false,
				maximizable:false,
				shadow: false,
				closed: false,
				height: 320
			});
			$('#btn_submit').linkbutton('enable');	
		}
		function closeAdd(){
			$('#w').window('close');
			$('#w2').window('close');
			$('#w3').window('close');
			
		}		
		function search(){
			var data={'merchNo':$('#merchNo').val(),'accoutNo':$('#accoutNo').val(),'accoutName':$("#accoutName").val()};
			$('#bankList').datagrid('load',data);
		}
		function saveUser(){
			$('#saveForm').form('submit', {  
			    onSubmit: function(){  
			    	if($('#saveForm').form('validate')){
			    		$('#btn_submit').linkbutton('disable');	
			    		return true;   
				    }
			        return false;   
			    }, 
			    success: function(json) {
			    	$('#w').window('close');
		    		$('#btn_submit').linkbutton('enable');
		    		json = eval('(' + json + ')');
					 if(json.status=='OK'){
						 $.messager.alert('提示',"保存成功");
						 search();
					 }else{
						 $.messager.alert('提示',"保存失败"); 
						 search();
					 }
				}
			});  
		}
		function saveBank(){
			$('#b_saveForm').form('submit', {  
			    onSubmit: function(){  
			    	if($('#b_saveForm').form('validate')){
			    		$('#b_btn_submit').linkbutton('disable');	
			    		return true;   
				    }
			        return false;   
			    },   
			    success: function(json) {
			    	$('#w2').window('close');
			    	json = eval('(' + json + ')');
		    		$('#b_btn_submit').linkbutton('enable');
					 if(json.status=='OK'){
						 $.messager.alert('提示',"修改成功");
						 search();
					 }else{
						 $.messager.alert('提示',"修改失败");
						 search();
					 }
				} 
			});
		}
		
		function showUser(tId){
			$.ajax({
			   type: "POST",
			   url: "bankaccout/findById",
			   data: "tId="+tId,
			   async: false,
			   dataType:"json",
			   success: function(json){	
					$("#b_tId").val(json.tId);
					$("#b_status").val(json.status);
					$("#b_bankProvince").val(json.bankProvince);
					$("#b_bankCity").val(json.accoutNo);
					$("#b_accoutNoa").val(json.accoutNo);
					$("#b_accoutNamea").val(json.accoutName);
					$("#b_merchNoa").val(json.merchNo);
					$("#b_protocoltype").val(json.protocoltype);
					$("#b_bankNode").val(json.bankNode);
					$("#b_bankCode").val(json.bankCode);
					$("#b_channelCode").val(json.channelCode);
					$("#b_notes").val(json.notes);
					var bankNode = $("#b_bankCode").val();
					$.ajax({
					   type: "POST",
					   url: "bankaccout/queryBankInfo",
					   data: "bankNode="+bankNode,
					   async: false,
					   dataType:"json",
					   success: function(json){	
							showCity(json.bankCity)
					   }
					});
			   }
			});
			$('#w2').window({
				title: '修改银行账户信息',
				top:100,
				width: 600,
				modal: true,
				minimizable:false,
				collapsible:false,
				maximizable:false,
				shadow: false,
				closed: false,
				height: 320
			});
		}
		function findById(tId){
			$.ajax({
			   type: "POST",
			   url: "bankaccout/findById",
			   data: "tId="+tId,
			   async: false,
			   dataType:"json",
			   success: function(json){	
				   $("#d_tId").val(json.tId);
					$("#d_status").val(json.status);
					$("#d_bankProvince").val(json.bankProvince);
					$("#d_bankCity").val(json.accoutNo);
					$("#d_accoutNoa").val(json.accoutNo);
					$("#d_accoutNamea").val(json.accoutName);
					$("#d_merchNoa").val(json.merchNo);
					$("#d_protocoltype").val(json.protocoltype);
					$("#d_bankNode").val(json.bankNode);
					$("#d_bankCode").val(json.bankCode);
					$("#d_channelCode").val(json.channelCode);
					$("#d_notes").val(json.notes);
					var bankNode = $("#d_bankCode").val();
					$.ajax({
					   type: "POST",
					   url: "bankaccout/queryBankInfo",
					   data: "bankNode="+bankNode,
					   async: false,
					   dataType:"json",
					   success: function(json){	
							showCity(json.bankCity)
					   }
					});
			   }
			});
			$('#w3').window({
				title: '银行账户信息',
				top:100,
				width: 600,
				modal: true,
				minimizable:false,
				collapsible:false,
				maximizable:false,
				shadow: false,
				closed: false,
				height: 300
			});
		}

		function deleteUser(tId){
			$.messager.confirm('提示','您是否想要注销此银行账户信息?',function(r){   
			    if (r){  
			    	$.ajax({
						   type: "POST",
						   url: "bankaccout/delect",
						   data: "tId="+tId,
						   async: false,
						   dataType:"json",
						   success: function(json){
//		 					   json = eval("(" + data + ")");
								 if(json.status=='OK'){
									 $.messager.alert('提示',"注销成功");
									 search();
								 }else{
									 $.messager.alert('提示',"注销失败");
									 search();
								 }
						    }
						});
			    }   
			}); 
			
				
						
		}
		function showBankInfo(){ 
			var bankNode = $("#bankNode").val();
			$.ajax({
			   type: "POST",
			   url: "bankaccout/queryBankInfo",
			   data: "bankNode="+bankNode,
			   async: false,
			   dataType:"json",
			   success: function(json){	
				   if(json != null){
					   $("#bankCode").val(json.bankCode);
						showCity(json.bankCity)
				   }else{
				   		$.messager.alert('提示', '银行行号输入错误!');
				   }
			   }
			});
			$("#saveForm").attr("action","bankaccout/save");
			$('#btn_submit').linkbutton('enable');	
		}
	</script>
</html>
