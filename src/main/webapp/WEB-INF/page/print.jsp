<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
.container {
	width:210mm;
	border: solid 0px #000000;
	margin: 0 auto 0 auto;
}

.header {
	margin: auto;
	text-align: center;
}

.query {
	margin-left: 10px;
}

.spaceline {
	display: inline-block;
	width: 10px;
}

.newline {
	margin-top: 10px;
}

</style>
</head>
<body>
	<div class="container" id="app">
		<div class="header">
			<h4>入库清单</h4>
		</div>
		<div class="query">
			<div style="margin-left: 20px; margin-bottom: 20px;">
				<label><strong>单号:</strong> </label><span>${record.applyNum }</span>
				<div class="spaceline"></div>
				<label><strong>申请人: </strong></label><span>${record.proposer }</span>
				<div class="spaceline"></div>
				<label><strong>仓库/门店:</strong> </label><span>${record.cabinName }</span>
				<div class="newline"></div>
				<label><strong>日期:</strong> </label><span>${createDate }</span>
				<div class="spaceline"></div>
				<label><strong>类型:</strong> </label><span>${applyType }</span>
				<div class="spaceline"></div>
			</div>
			<div style="margin-left: 20px; margin-bottom: 10px;">
				<table border="1" bordercolor=" #000000"
					style="border-collapse: collapse; min-width: 85%">
					<tr>
						<th>序号</th>
						<th>仓库/门店</th>
						<th>原料名称</th>
						<th>采购数量</th>
						<th>实际入库</th>
						<th>总价</th>
						
					</tr>
					<c:forEach items="${list}" var="item" varStatus="s">
						<tr>
							<td align="center">${item.sno}</td>
							<td>${item.cabinName }</td>
							<td>${item.materialName }</td>
							<td>${item.purchaseInfo }</td>
							<td>${item.realStockInfo }</td>
							<td>${item.totalPrice }</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div style="margin-left: 20px; margin-bottom: 40px; text-align:center">
				<font size=2>
				<label>打印人员: </label><span>${printer }</span>
				<div class="spaceline"></div>
				<label>打印日期: </label><span>${currentDate }</span>
				<div class="spaceline"></div>
				</font>
			</div>
		</div>
	</div>

</body>
</html>
