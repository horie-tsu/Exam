<%-- CSVアップロード画面 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
	<c:param name="title">CSV取り込み</c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				CSV取り込み
			</h2>
		</section>

		<form action="Csv.action" method="POST" enctype="multipart/form-data" class="px-4">
			<div class="mb-3">
				<label for="csv" class="form-label">CSVファイル</label>
				<input type="file" id="csv" name="csv" class="form-control" required>
			</div>

			<button type="submit" class="btn btn-primary">
				取り込み開始
			</button>
		</form>
	</c:param>
</c:import>