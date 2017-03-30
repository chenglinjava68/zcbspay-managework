<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="../../top.jsp"></jsp:include>
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
	<div style="margin: 5px; border:" id="continer">
		<div id="p" class="easyui-panel" title="查询条件"
			style="height: 140px; padding: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">
			<form id="theForm" method="post">
				<table width="100%">
					<tr>
						<td align="right">批次序号</td>
						<td align="left" style="padding-left: 5px"><input
							id="batchNo" maxlength="8" /></td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td align="right"></td>
						<td align="right"></td>
						<td align="right"></td>
						<td style="padding-left: 5px"></td>
						<td align="right"><a href="javascript:search()"
							class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div style="margin-top: 5px">
			<table id="test"></table>
		</div>
		<div style="margin-top: 5px">
			<table id="detailInfo"></table>
		</div>
	</div>
	<div id="w" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save" style="width: 500px; height: 200px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc; text-align: center">
				<table width="100%" cellpadding="2" cellspacing="2" id="groupinfo"
					border="1">
					<tr>
						<td>标识</td><td id="ttid"></td>
						<td>报文标识号</td><td id="tmsgid"></td>
					</tr>
					<tr>
						<td>批次序号</td><td id="tbatchno"></td>
						<td>转发日期</td><td id="ttransmitdate"></td>
					</tr>
					<tr>
						<td>回执期限</td><td id="treturnlimited"></td>
						<td>付款行行号</td><td id="tdebtorbranchcode"></td>
					</tr>
					<tr>
						<td>付款清算行行号</td><td id="tdebtoragentcode"></td>
						<td>收款清算行行号</td><td id="tcreditoragentcode"></td>
					</tr>
					<tr>
						<td>收款行行号</td><td id="tcreditorbranchcode"></td>
						<td>收款人名称</td><td id="tcreditorname"></td>
					</tr>
					<tr>
						<td>收款人账号</td><td id="tcreditoraccountno"></td>
						<td>总金额</td><td id="ttotalamount"></td>
					</tr>
					<tr>
						<td>业务类型编码</td><td id="tcategorypurposecode"></td>
						<td>付款人数目</td><td id="tdebtornumber"></td>
					</tr>
					<tr>
						<td>成功付款总笔数</td><td id="treceivingtotalnumber"></td>
						<td>成功付款总金额</td><td id="treceivingtotalamount"></td>
					</tr>
					<tr>
						<td>失败付款总笔数</td><td id="tfailtotalnumber"></td>
						<td>失败付款总金额</td><td id="tfailtotalamount"></td>
					</tr>
					<tr>
						<td>NPC处理状态</td><td id="tnpcprocessstatus"></td>
						<td>NPC业务处理码</td><td id="tnpcprocesscode"></td>
					</tr>
					<tr>
						<td>NPC拒绝信息</td><td id="tnpcrejectinformation"></td>
						<td>NPC轧差日期</td><td id="tnpcnettingdate"></td>
					</tr>
					<tr>
						<td>NPC轧差场次</td><td id="tnpcnettinground"></td>
						<td>NPC清算日期/终态日期</td><td id="tnpcsettlementdate"></td>
					</tr>
					<tr>
						<td>NPC接收时间</td><td id="tnpcreceivetime"></td>
						<td>NPC转发时间</td><td id="tnpctransmittime"></td>
					</tr>
					<tr>
						<td>应答状态</td><td id="trspstatus"></td>
						<td>应答码</td><td id="trsprejectcode"></td>
					</tr>
					<tr>
						<td>业务拒绝信息</td><td id="trsprejectinformation"></td>
						<td>业务处理参与机构</td><td id="trspprocessparty"></td>
					</tr>
					<tr>
						<td>业务应答时间</td><td id="trspdate"></td>
						<td>参与机构业务状态</td><td id="tcomprocessstatus"></td>
					</tr>
					<tr>
						<td>参与机构业务处理码</td><td id="tcomprocesscode"></td>
						<td>拒绝业务的参与机构行号</td><td id="tcompartyidentification"></td>
					</tr>
					<tr>
						<td>参与机构业务拒绝码</td><td id="tcompartyprocesscode"></td>
						<td>参与机构业务拒绝信息</td><td id="tcomrejectinformation"></td>
					</tr>
					<tr>
						<td>参与机构处理日期（终态日期）</td><td id="tcomprocessdate"></td>
						<td>参与机构轧差场次</td><td id="tcomnettinground"></td>
					</tr>
					<tr>
						<td>通用处理报文接收时间</td><td id="tcomdate"></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>

<script>
	var width = $("#continer").width();
	$(function() {
		$('#test')
				.datagrid(
						{
							title : '交易流水列表',
							iconCls : 'icon-save',
							height : 400,
							singleSelect : true,
							nowrap : false,
							striped : true,
							url : 'trade/getBepsCollectBatchByPage',
							remoteSort : false,
							idField : 'MSGID',
							columns : [ [
								{field:'TID',title:'标志',width:120,align:'center'},
								{field:'ACCESSTYPE',title:'接入类型',width:121,align:'center'},
								{field:'COOPINSTIID',title:'合作机构号',width:122,align:'center'},
								{field:'MERID',title:'商户号',width:123,align:'center'},
								{field:'VERSION',title:'版本',width:124,align:'center'},
								{field:'ENCODING',title:'编码方式',width:125,align:'center'},
								{field:'TXNTYPE',title:'交易类型',width:126,align:'center'},
								{field:'TXNSUBTYPE',title:'交易子类',width:127,align:'center'},
								{field:'BIZTYPE',title:'产品类型',width:128,align:'center'},
								{field:'BACKURL',title:'通知地址',width:129,align:'center'},
								{field:'BATCHNO',title:'批次号',width:130,align:'center'},
								{field:'TXNTIME',title:'订单发送时间',width:131,align:'center'},
								{field:'TOTALQTY',title:'总笔数',width:132,align:'center'},
								{field:'TOTALAMT ',title:'总金额 ',width:133,align:'center'},
								{field:'RESERVED',title:'保留域',width:134,align:'center'},
								{field:'RESPCODE',title:'响应码',width:135,align:'center'},
								{field:'RESPMSG',title:'应答信息',width:136,align:'center'},
								{field:'STATUS',title:'状态',width:137,align:'center'},
								{field:'ORDERCOMMITIME',title:'订单提交时间',width:138,align:'center'},
								{field:'SYNCNOTIFY',title:'异步通知结果',width:139,align:'center'},
								{field:'NOTES',title:'备注',width:140,align:'center'},
								{field:'REMARKS',title:'备注',width:141,align:'center'},
								{field:'ID',title:'操作',width:120,align:'center',
									formatter:function(value,rec){
										return '<a href="javascript:queryDetail(\''+rec.BATCHNO+'\')" style="color:blue;margin-left:10px">详细信息</a>';
									}
								}
							] ],
							pagination : true,
							rownumbers : true,
							onClickRow: function (index, row) { 
								var batchNo= row["BATCHNO"];
								$('#detailInfo').datagrid({
									title:'详情表',
									iconCls:'icon-save',
									height:400,
									singleSelect:true,
									nowrap: false,
									striped: true,
									url:'trade/queryCollectDetail?batchNo='+batchNo,	
									remoteSort: false,
									idField:'TID',
									columns:[
									[
										{field:'TID',title:'标志',width:141,align:'center'},
										{field:'BATCHTID',title:'批次表标志',width:142,align:'center'},
										{field:'BATCHNO',title:'批次号',width:143,align:'center'},
										{field:'ORDERID',title:'商户订单号',width:144,align:'center'},
										{field:'CURRENCYCODE',title:'交易币种',width:145,align:'center'},
										{field:'AMT',title:'单笔金额',width:146,align:'center'},
										{field:'DEBTORBANK',title:'付款人银行号',width:147,align:'center'},
										{field:'DEBTORACCOUNT',title:'付款人账号',width:148,align:'center'},
										{field:'DEBTORNAME',title:'付款人名称',width:149,align:'center'},
										{field:'DEBTORCONSIGN',title:'付款合同号',width:150,align:'center'},
										{field:'CREDITORBANK',title:'收款人银行号',width:151,align:'center'},
										{field:'CREDITORACCOUNT',title:'收款人账号',width:152,align:'center'},
										{field:'CREDITORNAME',title:'收款人名称',width:153,align:'center'},
										{field:'PROPRIETARY',title:'业务种类编码',width:154,align:'center'},
										{field:'SUMMARY',title:'摘要',width:155,align:'center'},
										{field:'RESPCODE',title:'响应码',width:156,align:'center'},
										{field:'RESPMSG',title:'应答信息',width:157,align:'center'},
										{field:'RELATETRADETXN',title:'关联交易序列号',width:158,align:'center'},
										{field:'STATUS',title:'状态',width:159,align:'center'},
									]],
									pagination:true,
									rownumbers:true,
								})
							}

						});

	});

	function search() {
		var data = {
			"batchNo" : $('#batchNo').val(),
		}
		$('#test').datagrid('load', data);
	}
	function queryDetail(batchNo){
		$("#tid").html("");
		$("#accesstype").html("");
		$("#coopinstiid").html("");
		$("#merid").html("");
		$("#version").html("");
		$("#encoding").html("");
		$("#txntype").html("");
		$("#txnsubtype").html("");
		$("#biztype").html("");
		$("#backurl").html("");
		$("#batchno").html("");
		$("#txntime").html("");
		$("#totalqty").html("");
		$("#totalamt ").html("");
		$("#reserved").html("");
		$("#respcode").html("");
		$("#respmsg").html("");
		$("#status").html("");
		$("#ordercommitime").html("");
		$("#syncnotify").html("");
		$("#notes").html("");
		$("#remarks").html("");
		   
		$('#w').window({
			title: '详细信息',
			top:90,
			left:100,
			width:900,
			modal: true,
			minimizable:false,
			collapsible:false,
			maximizable:false,
			shadow: false,
			closed: false,
			height: 800
		});
	
		var rows = $('#test').datagrid('getSelected');
		$("#tid").html(rows["TID"]);
		$("#accesstype").html(rows["ACCESSTYPE"]);
		$("#coopinstiid").html(rows["COOPINSTIID"]);
		$("#merid").html(rows["MERID"]);
		$("#version").html(rows["VERSION"]);
		$("#encoding").html(rows["ENCODING"]);
		$("#txntype").html(rows["TXNTYPE"]);
		$("#txnsubtype").html(rows["TXNSUBTYPE"]);
		$("#biztype").html(rows["BIZTYPE"]);
		$("#backurl").html(rows["BACKURL"]);
		$("#batchno").html(rows["BATCHNO"]);
		$("#txntime").html(rows["TXNTIME"]);
		$("#totalqty").html(rows["TOTALQTY"]);
		$("#totalamt ").html(rows["TOTALAMT "]);
		$("#reserved").html(rows["RESERVED"]);
		$("#respcode").html(rows["RESPCODE"]);
		$("#respmsg").html(rows["RESPMSG"]);
		$("#status").html(rows["STATUS"]);
		$("#ordercommitime").html(rows["ORDERCOMMITIME"]);
		$("#syncnotify").html(rows["SYNCNOTIFY"]);
		$("#notes").html(rows["NOTES"]);
		$("#remarks").html(rows["REMARKS"]);
	}
</script>
</html>
