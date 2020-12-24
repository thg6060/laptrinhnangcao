<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="right_col" role="main">
	<div class="clearfix"></div>
	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel">
			<div class="x_title">
				<h2>ProductInfo List</h2>

				<div class="clearfix"></div>
			</div>


			<div class="x_content">
				<div class="container">
					<a href="<c:url value="/product-info/add"/>" class="btn btn-app"><i
						class="fa fa-plus"></i>Add</a>
					<div class="container" style="padding: 50px;">
						<form:form modelAttribute="searchForm"
							cssClass="form-horizontal form-label-left"
							servletRelativeAction="/product-info/list" method="POST">
							<div class="item form-group">
								<label for="description"
									class="control-label col-md-3 col-sm-3 col-xs-12">ID</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<form:input path="id"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
									<button type="submit" class="btn btn-success">Search</button>
								</div>
							</div>

						</form:form>
					</div>

				</div>

				<div class="table-responsive">
					<table class="table table-striped jambo_table bulk_action">
						<thead>
							<tr class="headings">
								<th class="column-title">#</th>
								<th class="column-title">Id</th>
								<th class="column-title">Code</th>
								<th class="column-title">Name</th>
								<th class="column-title">Description</th>
								<th class="column-title no-link last text-center" colspan="3"><span
									class="nobr">Action</span></th>
							</tr>
						</thead>

						<tbody>
							<c:forEach items="${productInfos}" var="productInfo" varStatus="loop">

								<c:choose>
									<c:when test="${loop.index%2==0 }">
										<tr class="even pointer">
									</c:when>
									<c:otherwise>
										<tr class="odd pointer">
									</c:otherwise>
								</c:choose>
								<td class=" ">${loop.index+1}</td>
								<td class=" ">${productInfo.id }</td>
								<td class=" ">${productInfo.code }</td>
								<td class=" ">${productInfo.name }</td>
								<td class=" ">${productInfo.description }</td>
								<td class="text-center"><a
									href="<c:url value="/product-info/view/${productInfo.id }"/>"
									class="btn btn-round btn-success">View</a></td>
								<td class="text-center"><a
									href="<c:url value="/product-info/edit/${productInfo.id }"/>"
									class="btn btn-round btn-primary">Edit</a></td>
								<td class="text-center"><a href="javascript:void(0);"
									onclick="confirmDelete(${productInfo.id});"
									class="btn btn-round btn-danger">Delete</a></td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>


			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	 function confirmDelete(id){
		 if(confirm('Do you want delete this record?')){
			 window.location.href = '<c:url value="/product-info/delete/"/>'+id;
		 }
	 }
</script>