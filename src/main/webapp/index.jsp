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
				<button class="btn btn-primary " id="emp_add_modal_btn">
					<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					新建
				</button>
				<button class="btn btn-danger " id="del_all">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					删除
				</button>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-10">
				<table id="emp_table" class="table table-striped table-hover">
					<thead>
						<tr>
							<th><input type="checkbox" id="check_all"></th>
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
				<div class="col-sm-6" id="page_info"></div>
				<!-- 分页条 -->
				<div class="col-sm-6" id="page_navigate"></div>
			</div>
		</div>
		<!-- 新建员工模态框 -->
		<div class="modal fade" tabindex="-1" role="dialog" id="empAddModal">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">员工添加</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal">
							<div class="form-group">
								<label for="empName" class="col-sm-2 control-label">empName</label>
								<div class="col-sm-10">
									<input type="text" name="empName" class="form-control"
										id="empName_add_input" placeholder="empName"> <span
										class="help-block"></span>
								</div>
							</div>
							<div class="form-group">
								<label for="email" class="col-sm-2 control-label">email</label>
								<div class="col-sm-10">
									<input type="email" name="email" class="form-control"
										id="email_add_input" placeholder="email"> <span
										class="help-block"></span>
								</div>
							</div>
							<div class="form-group">
								<label for="gender" class="col-sm-2 control-label">gender</label>
								<div class="col-sm-10">
									<label class="radio-inline"> <input type="radio"
										name="gender" id="gender_add_input1" value="M" checked>
										男
									</label> <label class="radio-inline"> <input type="radio"
										name="gender" id="gender_add_input2" value="F"> 女
									</label>
								</div>
							</div>
							<div class="form-group">
								<label for="deptName" class="col-sm-2 control-label">deptName</label>
								<div class="col-sm-5">
									<select class="form-control" name="dId" id="dept_add_select">
									</select>
								</div>
							</div>

						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="emp_save_btn">保存
						</button>
					</div>

				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->

		<!-- 编辑员工模态框 -->
		<div class="modal fade" tabindex="-1" role="dialog" id="empEditModal">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">员工编辑</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal">
							<div class="form-group">
								<label for="empName" class="col-sm-2 control-label">empName</label>
								<div class="col-sm-10">
									<p class="form-control-static" id="empName_edit_static"></p>
									<span class="help-block"></span>
								</div>
							</div>
							<div class="form-group">
								<label for="email" class="col-sm-2 control-label">email</label>
								<div class="col-sm-10">
									<input type="email" name="email" class="form-control"
										id="email_edit_input" placeholder="email"> <span
										class="help-block"></span>
								</div>
							</div>
							<div class="form-group">
								<label for="gender" class="col-sm-2 control-label">gender</label>
								<div class="col-sm-10">
									<label class="radio-inline"> <input type="radio"
										name="gender" id="gender_edit_input1" value="M" checked>
										男
									</label> <label class="radio-inline"> <input type="radio"
										name="gender" id="gender_edit_input2" value="F"> 女
									</label>
								</div>
							</div>
							<div class="form-group">
								<label for="deptName" class="col-sm-2 control-label">deptName</label>
								<div class="col-sm-5">
									<select class="form-control" name="dId" id="dept_edit_select">
									</select>
								</div>
							</div>

						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="emp_update_btn">更新
						</button>
					</div>

				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
	</div>
	<script>
		var totalRecord, currentNum;
		//1.页面加载完成，直接发送Ajax请求，去后台请求分页数据
		$(function() {
			to_page(1);
		});
		//发送ajax请求,进行页面跳转
		function to_page(pn) {
			$.get({
				url : "${APP_PATH}/emps",
				data : "pn=" + pn,
				success : function(res) {
					//console.log(res);
					//清空旧数据
					$('#emp_table tbody').empty();
					$('#page_info').empty();
					$('#page_navigate').empty();
					//1.解析json，并显示员工数据
					build_emps_table(res);

					//2.显示分页信息
					build_page_info(res);
					//3. 显示分页导航条
					build_page_navigate(res);
				}
			});
		}
		//构建表格
		function build_emps_table(res) {
			var emps = res.extend.pageInfo.list;
			$
					.each(
							emps,
							function(i, emp) {
								//alert(emp.empName);
								var checkboxTd = $('<td><input type="checkbox" class="check_item" ></td>')
								var empIdTd = $('<td></td>').append(emp.empId);
								var empNameTd = $('<td></td>').append(
										emp.empName);
								var genderTd = $('<td></td>').append(
										emp.gender == 'M' ? '男' : '女');
								var emailTd = $('<td></td>').append(emp.email);
								var deptNameTd = $('<td></td>').append(
										emp.department.deptName);
								//console.log("dd");
								var editBtn = $('<button></button>')
										.attr('emp-id', emp.empId)
										.addClass(
												'btn btn-primary btn-sm edit-btn')
										.append(
												$('<span></span>')
														.addClass(
																'glyphicon glyphicon-pencil'))
										.append('编辑');
								var delBtn = $('<button></button>')
										.attr('emp-id', emp.empId)
										.addClass(
												'btn btn-danger btn-sm del-btn')
										.append(
												$('<span></span>')
														.addClass(
																'glyphicon glyphicon-remove'))
										.append('删除');
								var btnTd = $('<td></td>').append(editBtn)
										.append(" ").append(delBtn);
								$('<tr></tr>').append(checkboxTd).append(
										empIdTd).append(empNameTd).append(
										genderTd).append(emailTd).append(
										deptNameTd).append(btnTd).appendTo(
										'#emp_table tbody');
							});

		}
		//构建分页信息
		function build_page_info(res) {
			var pageNum = res.extend.pageInfo.pageNum;
			var total = res.extend.pageInfo.total;
			var pages = res.extend.pageInfo.pages;
			$('#page_info').append("当前页码：").append(
					$('<span></span>').addClass('label label-default').append(
							pageNum)).append('总记录数：').append(
					$('<span></span>').addClass('label label-default').append(
							total)).append('总页数：').append(
					$('<span></span>').addClass('label label-default').append(
							pages));
			totalRecord = total;
			currentNum = pageNum;

		}
		//构建分页导航
		function build_page_navigate(res) {
			var firstPageLi = $('<li></li>').append(
					$('<a></a>').append('首页').attr('href', '#'));

			var lastPageLi = $('<li></li>').append(
					$('<a></a>').append('末页').attr('href', '#'));

			var prePageLi = $('<li></li>').append(
					$('<a></a>').append('&laquo;').attr('href', '#'));
			if (res.extend.pageInfo.hasPreviousPage == false) {
				prePageLi.addClass("disabled");
				firstPageLi.addClass("disabled");
			} else {
				firstPageLi.click(function() {
					to_page(1);
				});
				prePageLi.click(function() {
					to_page(res.extend.pageInfo.pageNum - 1);
				});
			}

			var nextPageLi = $('<li></li>').append(
					$('<a></a>').append('&raquo;').attr('href', '#'));
			if (res.extend.pageInfo.hasNextPage == false) {
				nextPageLi.addClass("disabled");
				lastPageLi.addClass("disabled");
			} else {
				lastPageLi.click(function() {
					to_page(res.extend.pageInfo.pages);
				});
				nextPageLi.click(function() {
					to_page(res.extend.pageInfo.pageNum + 1);
				});
			}

			var ul = $('<ul></ul>').addClass('pagination');
			//添加首页和前一页
			ul.append(firstPageLi).append(prePageLi);
			//添加1、2、3...页
			$.each(res.extend.pageInfo.navigatepageNums, function(i, page) {
				var pageLi = $('<li></li>').append(
						$('<a></a>').append(page).attr('href', '#'));
				if (page == res.extend.pageInfo.pageNum) {
					pageLi.addClass('active');
				}
				pageLi.click(function() {

					to_page(page);
				});
				ul.append(pageLi);
			})
			//添加下一页和末页
			ul.append(nextPageLi).append(lastPageLi);
			var navElem = $('<nav></nav>').append(ul);
			$('#page_navigate').append(navElem);

		}
		//清空表单样式和数据
		function reset_form(ele) {

			$(ele)[0].reset();
			$(ele).find('*').removeClass('has-success has-error');
			$(ele).find('.help-block').text('');
		}
		//点击新增按钮打开模态框
		$('#emp_add_modal_btn').click(function() {
			//查出所有部门显示在下拉框
			getDepts($('#empAddModal select'));

			//弹出模态框
			$('#empAddModal').modal({
				backdrop : 'static'
			});
			//打开模态框之前清除表单数据
			reset_form('#empAddModal form');
		});
		function getDepts(ele) {
			//清空之前查询数据
			ele.empty();
			$.get({
				url : '${APP_PATH}/getDepts',
				success : function(res) {
					//console.log(res);
					//$('#empAddModal select').append();
					$.each(res.extend.depts, function(i, dept) {
						var optionEle = $('<option></option>').append(
								this.deptName).attr('value', this.deptId);
						optionEle.appendTo(ele);
					});

				}
			})
		}

		//校验员工数据
		function validate_add_form() {
			//1. 拿到校验数据,使用正则表达式 ,校验用户名
			var empName = $('#empName_add_input').val();
			var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/
			if (!regName.test(empName)) {
				//alert("用户名2-5位中文,6-16位英文,_-");
				show_validate_msg('#empName_add_input', 'error',
						'2-5位汉字,6-16位字符(包括 _-)');
				/* $('#empName_add_input').parent().addClass('has-error');
				$('#empName_add_input').next('span').text('2-5位汉字,6-16位字符(包括 _-)'); */
				return false;
			} else {
				show_validate_msg('#empName_add_input', 'success', '');
				//$('#empName_add_input').parent().addClass('has-success');
			}
			;
			//2. 校验邮箱
			var email = $('#email_add_input').val();
			var regEmail = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/

			if (!regEmail.test(email)) {
				//alert("请输入正确的邮箱格式");
				show_validate_msg('#email_add_input', 'error', '格式:"xx@xx.com"');
				/* $('#email_add_input').parent().addClass('has-error');
				$('#email_add_input').next('span').text('格式:"xx@xx.com"'); */
				return false;
			} else {
				show_validate_msg('#email_add_input', 'success', '');
				/* $('#email_add_input').parent().removeClass('has-error').addClass('has-success'); */
			}

			return true;

		}
		//校验用户名是否可用
		$('#empName_add_input').change(
				function() {
					var empName = this.value;
					$.ajax({
						url : '${APP_PATH}/checkuser',
						data : 'empName=' + empName,
						type : 'POST',
						success : function(res) {
							if (res.code == 100) {
								show_validate_msg('#empName_add_input',
										'success', '用户名可用');
								$('#emp_save_btn').attr('ajax-va', 'success');
							} else {
								show_validate_msg('#empName_add_input',
										'error', res.extend.va_msg);
								$('#emp_save_btn').attr('ajax-va', 'error');
							}
						}

					})
				})

		function show_validate_msg(ele, status, msg) {
			//清除当前元素校验状态
			$(ele).parent().removeClass('has-success').removeClass('has-error');
			$(ele).next('span').text('');
			if (status == "success") {
				$(ele).parent().addClass('has-success');
				$(ele).next('span').text(msg);
			} else if (status == 'error') {
				$(ele).parent().addClass('has-error');
				$(ele).next('span').text(msg);
			}
		}

		//点击模态框保存按钮发送Ajax请求
		$('#emp_save_btn')
				.click(
						function() {
							//alert($('#empAddModal form').serialize());

							//校验数据
							if (!validate_add_form()) {
								return false;
							}
							;
							//校验用户名是否存在
							if ($(this).attr('ajax-va') == 'error') {
								return false;
							}
							//发送Ajax保存员工信息 
							$
									.post({
										url : '${APP_PATH}/emp',
										data : $('#empAddModal form')
												.serialize(),
										success : function(res) {
											//alert(res.msg);
											if (res.code == 100) {
												//员工保存成功,关闭模态框,页码调到最后一页查看新增员工
												$('#empAddModal').modal('hide');
												//发送Ajax请求最后一页数据,请求页码大于总页码即可显示最后一页
												to_page(totalRecord);
												//alert("dd");
											} else {
												//显示失败信息
												//console.log(res);
												//哪个字段错误就显示哪个字段错误信息
												if (undefined != res.extend.errorFields.empName) {
													show_validate_msg(
															'#empName_add_input',
															'error',
															'2-5位汉字,6-16位字符(包括 _-)');
												}
												if (undefined != res.extend.errorFields.email) {
													show_validate_msg(
															'#email_add_input',
															'error',
															'格式:"xx@xx.com"');
												}
											}

										}
									})
						});

		//点击编辑打开模态框
		$(document).on('click', '.edit-btn', function() {
			//alert("ff");
			getDepts($('#empEditModal select'));

			//弹出模态框
			$('#empEditModal').modal({
				backdrop : 'static'
			});
			getEmp($(this).attr('emp-id'));
			//将empId传到更新按钮
			$('#emp_update_btn').attr('emp-id', $(this).attr('emp-id'));
		});
		//根据ID获取emp
		function getEmp(id) {
			$.ajax({
				url : '${APP_PATH}/emp/' + id,
				type : 'GET',
				success : function(res) {
					//console.log(res);
					var emp = res.extend.emp;
					$('#empName_edit_static').text(emp.empName);
					$('#email_edit_input').val(emp.email);
					$('#empEditModal select').val([ emp.dId ]);
					$('#empEditModal input[type=radio]').val([ emp.gender ]);

				}
			})
		}

		//点击更新按钮进行更新
		$('#emp_update_btn')
				.click(
						function() {
							//1. 校验邮箱
							var email = $('#email_edit_input').val();
							var regEmail = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/

							if (!regEmail.test(email)) {
								//alert("请输入正确的邮箱格式");
								show_validate_msg('#email_add_input', 'error',
										'格式:"xx@xx.com"');
								/* $('#email_add_input').parent().addClass('has-error');
								$('#email_add_input').next('span').text('格式:"xx@xx.com"'); */
								return false;
							} else {
								show_validate_msg('#email_add_input',
										'success', '');
								/* $('#email_add_input').parent().removeClass('has-error').addClass('has-success'); */
							}
							//2.发送Ajax请求,更新数据
							$.ajax({
								url : '${APP_PATH}/emp/'
										+ $(this).attr('emp-id'),
								type : 'PUT',
								data : $('#empEditModal form').serialize(),
								/* 				type:'POST',
								 data:$('#empEditModal form').serialize()+"&_method=PUT", */
								success : function(res) {
									//alert(res.msg);
									//console.log(res);
									//1. 关闭modal
									$('#empEditModal').modal('hide');
									//2. 更新页面员工数据 
									to_page(currentNum);

								}

							})

						});
		//点击删除按钮,执行单个删除
		$(document).on('click', '.del-btn', function() {
			var empName = $(this).parents('tr').find('td:eq(2)').text();
			if (confirm('确认删除[' + empName + ']吗?')) {
				//确认,发送Ajax请求
				$.ajax({
					url : '${APP_PATH}/emp/' + $(this).attr('emp-id'),
					type : 'DELETE',
					success : function(res) {
						alert(res.msg);
						to_page(currentNum);
					}	
				})
			}
			//alert('确认删除'+empName+'吗?');

		});

		//完成全选/全部不选功能
		$('#check_all').click(function() {
			//attr获取的checked属性是undefined
			//dom原生的属性用 prop获取,  attr获取自定义的属性
			//prop修改和获取checked值
			$('.check_item').prop('checked', $(this).prop('checked'));
		});
		//check_item都选中,#check_all被选中

		$(document)
				.on(
						'click',
						'.check_item',
						function() {
							//判断当前选中的个数是否为5个
							var flag = $('.check_item:checked').length == $('.check_item').length;
							$('#check_all').prop('checked', flag);
						});
		//点击全部删除按键
		$('#del_all').click(function(){
			var empNames = "";
			var del_idstr = "";
			$.each($('.check_item:checked'),function(){
				empNames += $(this).parents('tr').find('td:eq(2)').text()+",";
				del_idstr +=$(this).parents('tr').find('td:eq(1)').text()+"-";
				
			});
			//去除多余的 -
			del_idstr = del_idstr.substring(0, del_idstr.length-1);
			//alert(del_idstr);
			//去除多余的 ,
			empNames = empNames.substring(0, empNames.length-1);
			if (confirm("要全部删除["+empNames+"]吗?")){
				$.ajax({
					url:'${APP_PATH}/emp/'+del_idstr,
					type:'DELETE',
					success:function(res){
						alert(res.msg);
						to_page(currentNum);;
					}
				})
				
			};
		});
	</script>
</body>

</html>