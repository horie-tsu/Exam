<%-- クラス登録JSP --%>
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
				クラス登録
			</h2>
		</section>

		<form method="post" action="SubjectCreateExecute.action">
			<div class="col-11 px-4">

				<label class="form-label">クラス番号</label>
				<input type="text"
					   name="cd"
					   class="form-control"
					   maxlength="3"
					   required
					   placeholder="科目コードを入力してください"
					   value="${param.cd}">
				<span class="text-danger">${errors.cd}</span>

				<br>

				<label class="form-label">クラス名</label>
				<input type="text"
					   name="name"
					   class="form-control"
					   maxlength="20"
					   required
					   placeholder="科目名を入力してください"
					   value="${param.name}">
				<span class="text-danger">${errors.name}</span>

				<br>

				<button type="submit" class="btn btn-primary">
					登録
				</button>
				<a href="SubjectList.action" class="d-block mt-2">戻る</a>
			</div>
		</form>
	</c:param>
</c:import>