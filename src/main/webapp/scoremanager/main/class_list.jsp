<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%><%--未完 --%>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>
	<c:param name="scripts"></c:param>
	
	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">クラス管理</h2>
			
			<form method="get">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
					
					<div class="col-4">
						<label class="form-label" for="student-f1-select">学校名</label>
						<select class="form-select" id="student-f1-select" name="f1">
							<option value="0">-------</option>
							
							<c:forEach var="school" items="${schools}">
								<option value="${school.cd}"
    <c:if test="${school.cd == f1}">selected</c:if>>
    ${school.name}
</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="col-2 text-center">
						<button class="btn btn-secondary" id="filter-button">絞り込み</button>
					</div>
					
					<div class="mt-2 text-warning">
						${errors.get("f1")}
					</div>
					
				</div>
			</form>
			
			<c:choose>
				<c:when test="${not empty classes}">
					<div>検索結果:${classes.size()}件</div>
					
					<table class="table table-hover">
						<tr>
							<th>学校名</th>
							<th>クラス</th>
						</tr>
						
						<c:forEach var="c" items="${classes}">
							<tr>
								<td>${c.school.name}</td>
								<td>${c.class_num}</td>
								
							</tr>
						</c:forEach>
						
					</table>
				</c:when>
				
				<c:otherwise>
					<div>クラス情報が存在しませんでした。</div>
				</c:otherwise>
			</c:choose>
			
		</section>
	</c:param>
</c:import>