<%-- 学生登録JSP --%>
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
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>
		</section>
		
		<form method="post" action="StudentCreateExecute.action">
			<div class="col-11">
				
				<label class="form-label">入学年度</label>
				<select name="entYear" class="form-select">
					<option value="0">-------</option>
					<c:forEach var="year" items="${ent_year_set}">
						<option value="${year}" <c:if test="${year==param.entYear}">selected</c:if>>
							${year}
						</option>
					</c:forEach>
				</select>
				<span class="text-danger">${errors.entYear}</span>

				<label class="form-label">学生番号</label>
				<input type="text"
					   name="cd"
					   class="form-control"
					   maxlength="10"
					   required
					   placeholder="学生番号を入力してください"
					   value="${stuId}">
				<span class="text-danger">${errors.stuId}</span>

				<br>

				<label class="form-label">氏名</label>
				<input type="text"
					   name="cd"
					   class="form-control"
					   maxlength="30"
					   required
					   placeholder="氏名を入力してください"
					   value="${stuId}">
				<span class="text-danger">${errors.cd}</span>

				<br>
				<label class="form-label" for="student-f2-select">クラス</label>
				<select class="form-select" id="student-f2-select" name="f2">
					<c:forEach var="num" items="${class_num_set}">
						<%--現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
						<option value="${num}" <c:if test="${num==f2}">selected</c:if>>
							${num}
						</option>
					</c:forEach>
				</select>
				<br>

				<button type="submit" class="btn btn-secondary">登録して終了</button>
				<a href="StudentList.action" class="d-block mt-2">戻る</a>
			</div>
		</form>

	</c:param>
	
</c:import>