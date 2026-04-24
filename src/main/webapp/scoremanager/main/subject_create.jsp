<%-- 科目登録画面JSP（装飾あり） --%>
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
				科目登録
			</h2>
		</section>

		<form method="post" action="SubjectCreateExecute.action">
			<div class="col-4 px-4">

				<label class="form-label">科目コード</label>
				<input type="text"
					   name="cd"
					   class="form-control"
					   maxlength="3"
					   required
					   placeholder="科目コードを入力してください"
					   value="${param.cd}">
				<span class="text-danger">${errors.cd}</span>

				<br>

				<label class="form-label">科目名</label>
				<input type="text"
					   name="name"
					   class="form-control"
					   maxlength="20"
					   required
					   placeholder="科目名を入力してください"
					   value="${param.name}">
				<span class="text-danger">${errors.name}</span>

				<br>

				<button type="submit" class="btn btn-secondary">
					登録して終了
				</button>

			</div>
		</form>

		<div class="my-2 text-end px-4">
			<a href="SubjectList.action">戻る</a>
		</div>
	</c:param>
</c:import>