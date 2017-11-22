<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
.container {
	width: 80%;
	border: solid 0px blue;
	margin: 0 auto 0 auto;
}

.header {
	margin: auto;
	text-align: center;
}

.query {
	margin-left: 20px;
}

.spaceline {
	display: inline-block;
	width: 20px;
}

.newline {
	margin-top: 10px;
}

</style>
</head>
<body>
	<div class="container" id="app">
		<div class="header">
			<h4>入库单详细清单</h4>
		</div>
		<div class="query">
			<div style="margin-left: 40px; margin-bottom: 20px;">
				<label><strong>单号:</strong> </label><span>${record.applyNum }</span>
				<div class="spaceline"></div>
				<label><strong>申请人: </strong></label><span>${record.proposer }</span>
				<div class="spaceline"></div>
				<label><strong>仓库/门店:</strong> </label><span>${record.cabinName }</span>
				<div class="newline"></div>
				<label><strong>采购日期:</strong> </label><span>${crateDate }</span>
				<div class="spaceline"></div>
				
			</div>
			<div style="margin-left: 40px; margin-bottom: 40px;">
				共102条
				<table border="1" bordercolor="#a0c6e5"
					style="border-collapse: collapse; min-width: 80%">
					<tr>
						<th>序号</th>
						<th>仓库/门店</th>
						<th>原料名称</th>
						<th>规格数量</th>
						<th>单价</th>
						<th>数量</th>
					</tr>
					<c:forEach items="${list}" var="item" varStatus="s">
						<tr>
							<td align="center">${s.index + 1}</td>
							<td>${item.cabinName }</td>
							<td>${item.materialName }</td>
							<td>${item.specAmt }${item.specUnit }</td>
							<td>${item.specPrice }</td>
							<td>${item.stockAmt }${item.stockUnit }</td>
							
						</tr>
					</c:forEach>

				</table>
			</div>
			<div style="margin-left: 40px; margin-bottom: 40px;"></div>
		</div>
	</div>

</body>
</html>
