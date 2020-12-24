<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h2>${titlePage}</h2>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">

					<div class="x_content">
						<br />


						<form:form id="formUser" modelAttribute="modelFormUser"
							cssClass="form-horizontal form-label-left"
							servletRelativeAction="/user/save" method="POST">
							<form:hidden path="user.createDate" />
							<form:hidden path="user.updateDate" />
							<form:hidden path="user.activeFlag" />
							<form:hidden path="user.id" />
							<div class="item form-group">
								<label
									class="control-label col-md-3 col-sm-3 col-xs-12 label-align"
									for="name">Tên<span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<form:input path="user.name"
										cssClass="form-control col-md-7 col-xs-12"
										disabled="${viewOnly}" />
								</div>
							</div>
							<div class="item form-group">
								<label
									class="control-label col-md-3 col-sm-3 col-xs-12 label-align"
									for="email">Email <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<form:input path="user.email"
										cssClass="form-control col-md-7 col-xs-12"
										disabled="${viewOnly}" />
								</div>
							</div>
							<div class="item form-group">
								<label for="userName"
									class="control-label col-md-3 col-sm-3 col-xs-12 label-align">Username</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<form:input path="user.userName"
										cssClass="form-control col-md-7 col-xs-12"
										disabled="${viewOnly}" readonly="${readOnly}" />


								</div>

							</div>
							<div class="item form-group">
								<label for="userNameerr"
									class="control-label col-md-3 col-sm-3 col-xs-12 label-align"></label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<form:errors path="user.userName" cssClass="error" />
								</div>
							</div>


							<div class="item form-group">
								<label for="password"
									class="control-label col-md-3 col-sm-3 col-xs-12 label-align">Password</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<form:password showPassword="true" path="user.password"
										cssClass="form-control col-md-7 col-xs-12"
										disabled="${viewOnly}" />


								</div>
							</div>
							<div class="item form-group">
								<label for="passWorderr"
									class="control-label col-md-3 col-sm-3 col-xs-12 label-align"></label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<form:errors path="user.password" cssClass="error" />
								</div>
							</div>

							<div class="item form-group">
								<label
									class="control-label col-md-3 col-sm-3 col-xs-12 label-align"
									for="roleName">Quyền người dùng<span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<form:select path="role.roleName"
										cssClass="form-control col-md-7 col-xs-12"
										disable="${viewOnly}">
										<form:options items="${listRoleName}" />
									</form:select>
								</div>
							</div>


							<div class="ln_solid"></div>
							<div class="item form-group">
								<div
									class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3 label-align">
									<button class="btn btn-primary" type="button"
										onclick="cancel();">Cancel</button>
									<c:if test="${!viewOnly }">
										<button class="btn btn-primary" type="reset">Reset</button>
										<button type="submit" class="btn btn-success">Submit</button>
									</c:if>
								</div>
							</div>

						</form:form>


					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(
			function() {
				$('#userlistId').addClass('current-page').siblings()
						.removeClass('current-page');
				var parent = $('#userlistId').parents('li');
				parent.addClass('active').siblings().removeClass('active');
				$('#userlistId').parents().show();
			});
	function cancel() {
		window.location.href = '<c:url value="/user/list"/>'
	}
</script>
