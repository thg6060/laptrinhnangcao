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

				<div class="table-responsive">
					<table class="table table-striped jambo_table bulk_action">
						<thead>
							<tr class="headings">
								<th class="column-title">#</th>
								<th class="column-title">Id</th>
								<th class="column-title">productId</th>
								<th class="column-title">Quantity</th>
								<th class="column-title">Price</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach items="${productInStocks}" var="productInStock" varStatus="loop">

								<c:choose>
									<c:when test="${loop.index%2==0 }">
										<tr class="even pointer">
									</c:when>
									<c:otherwise>
										<tr class="odd pointer">
									</c:otherwise>
								</c:choose>
								<td class=" ">${loop.index+1}</td>
								<td class=" ">${productInStock.id }</td>
								<td class=" ">${productInStock.productId }</td>
								<td class=" ">${productInStock.qty }</td>
								<td class=" ">${productInStock.price }</td>
							</c:forEach>

						</tbody>
					</table>
				</div>


			</div>
		</div>
	</div>
</div>
