<%-- 成績登録画面JSP（仮） --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				成績登録
			</h2>
		</section>

		<form method="post" action="TestRegistExecute.action">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
					
					<div class="col-4">
						<label class="form-label" for="student-f1-select">入学年度</label>
						<select class="form-select" id="student-f1-select" name="f1">
							<option value="0">-------</option>
							
							<c:forEach var="year" items="${ent_year_set}">
								<option value="${year}" <c:if test="${year==f1}">selected</c:if>>
									${year}
								</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="col-4">
						<label class="form-label" for="student-f2-select">クラス</label>
						<select class="form-select" id="student-f2-select" name="f2">
							<option value="0">-------</option>
							
							<c:forEach var="num" items="${class_num_set}">
								<%--現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
								<option value="${num}" <c:if test="${num==f2}">selected</c:if>>
									${num}
								</option>
							</c:forEach>
						</select>
					</div>

				<button type="submit" class="btn btn-secondary">
					登録して終了
				</button>

			</div>
		</form>
		
		<c:choose>
				<c:when test="${not empty students}">
					<div>科目:${param.name}</div>
					
					<table class="table table-hover">
						<tr>
							<th>入学年度</th>
							<th>クラス</th>
							<th>学生番号</th>
							<th>氏名</th>
							<th>点数</th>
						</tr>
						
						<c:forEach var="test" items="${students}">
							<tr>
								<td>${student.entYear}</td>
								<td>${student.no}</td>
								<td>${student.name}</td>
								<td>${student.classNum}</td>
								
								<td class="text-center">
									<%--在学フラグが立っている場合「〇」それ以外は「×」 --%>
									
								</td>
								
								<td>
									<a href="StudentUpdate.action?no=${student.no}">
										変更
									</a>
								</td>
							</tr>
						</c:forEach>
						
					</table>
				</c:when>
				
			</c:choose>
		<div class="my-2 text-end px-4">
			<a href="SubjectList.action">戻る</a>
		</div>
	</c:param>
	
</c:import>