<%-- 学生情報変更JSP --%>
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
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>
		</section>
		
		<form method="post" action="StudentUpdateExecute.action">
			<div class="col-11 px-4 my-3">
				<label class="form-label">入学年度</label>
				<input
					type="text"
					name="entYear"
					class="form-control"
					value="${student.entYear}"
					readonly>
			</div>
			<div class="col-11 px-4 my-3">
				<label class="form-label">学生番号</label>
				<input
					type="text"
					name="no"
					class="form-control"
					value="${student.no}"
					readonly>
			</div>
			<div class="col-11 px-4 my-3">
				<label class="form-label">氏名</label>
				<input
					type="text"
					name="name"
					class="form-control"
					maxlength="30"
					required
					placeholder="氏名を入力してください"
					value="${student.name}">
			</div>
				<div class="col-11 px-4 my-3">
					<label class="form-label" for="student-f2-select">クラス</label>
					<select class="form-select" id="student-f2-select" name="f2">
						<c:forEach var="num" items="${class_num_set}">
							<%--現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
							<option value="${num.classNum}" <c:if test="${num.classNum==student.classNum}">selected</c:if>>
								${num.classNum}
							</option>
						</c:forEach>
					</select>
				</div>
				
				<label>
					<div class="px-4">
						在学中
						<input
							type="checkbox"
							name="attend"
							value="true"
							${student.attend ? "checked" : ""}>
					</div>
				</label>
				<div class="col-11 px-4 my-3">
					<button type="submit" class="btn btn-primary">変更</button>
				</div>
		</form>
		
		<div class="col-11 px-4 my-3">
			<a href="StudentList.action">戻る</a>
		</div>
	</c:param>
	
</c:import>