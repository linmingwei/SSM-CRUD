<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工列表</title>
<!-- 1.web路径问题
  不以/开始的相对路径，找资源，以当前路径为基准寻找资源，容易出问题；
 以/开始的绝对路径，找资源，以服务器路径为基准(http://localhost:3306)，加上项目名称/crut
     http://localhost:3306/crut
 -->
<link rel="stylesheet"
	href="${APP_PATH}/static/bootstrap/css/bootstrap.min.css">
<script type="text/javascript"
	src="${APP_PATH}/static/js/jquery-3.2.1.js"></script>
<script type="text/javascript"
	src="${APP_PATH}/static/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-4 col-sm-offset-8">
				<button class="btn btn-primary ">
					<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					编辑
				</button>
				<button class="btn btn-danger">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					删除
				</button>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-10">
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>#</th>
							<th>empName</th>
							<th>gender</th>
							<th>email</th>
							<th>deptName</th>
							<th>操作</th>
						</tr>

					</thead>
					<tbody>
						

					</tbody>
				</table>
			</div>
			<div class="row">
				<!-- 分页信息 -->
				<div class="col-sm-6">
					当前页数：<span class="label label-default"></span>
					总记录数：<span class="label label-default"></span>
					总页数：<span class="label label-default"></span>
				</div>
				<!-- 分页条 -->
				<div class="col-sm-6">
					<nav aria-label="Page navigation">

					<ul class="pagination">
						<li><a href="${APP_PATH }/emps?pn=1">首页</a></li>
						<c:if test="${pageInfo.hasPreviousPage }">
							<li><a href="${APP_PATH }/emps?pn=${pageInfo.pageNum-1 }"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a></li>
						</c:if>
						<c:forEach items="${pageInfo.navigatepageNums }" var="page">
							<c:if test="${page == pageInfo.pageNum }">
								<li class="active"><a href="${APP_PATH }/emps?pn=${page }">${page }</a></li>
							</c:if>
							<c:if test="${page != pageInfo.pageNum }">
								<li><a href="${APP_PATH }/emps?pn=${page }">${page }</a></li>
							</c:if>
						</c:forEach>
						<!-- 						<li><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li> 
 -->
 						<c:if test="${pageInfo.hasNextPage }">
 							
						<li><a href="${APP_PATH }/emps?pn=${pageInfo.pageNum+1 }" aria-label="Next"> <span
								aria-hidden="true">&raquo;</span>
						</a></li>
 						</c:if>
						<li><a href="${APP_PATH }/emps?pn=${pageInfo.pages }">末页</a></li>
					</ul>
					</nav>
				</div>
			</div>
		</div>
	</div>
	<script>
		//1.页面加载完成，直接发送Ajax请求，去后台请求分页数据
		$(function(){
			$.get(function(){
				url:"${APP_PATH}/emps",
				data:"pn=1",
				success:function(res){
					console.log(res);
				}
			});
		})
		
	</script>
</body>

</html>