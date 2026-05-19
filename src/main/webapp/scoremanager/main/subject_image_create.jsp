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
				科目画像登録
			</h2>
		</section>
		
		<div class="my-2 text-end px-4">
	<a href="Csv.action" class="btn btn-success">
		CSV取り込み
	</a>
</div>

		<form method="post" action="SubjectImageCreateExecute.action" enctype="multipart/form-data">
			<div class="col-11 px-4 my-3">

				<label class="form-label">科目コード</label>
				<input type="text"
					   name="cd"
					   class="form-control"
					   value="${cd}"
					   readonly>
				<span class="text-danger">${errors.cd}</span>
			</div>
			
			<div class="col-11 px-4 my-3">
				<label class="form-label">科目名</label>
				<input type="text"
					   name="name"
					   class="form-control"
					   value="${name}"
					   readonly>
				<span class="text-danger">${errors.name}</span>

				</div>
				
				<div class="col-11 px-4 my-3">
					<label class="form-label">科目画像</label>
					<input type="file"
							name="image"
							class="form-control"
							accept="image/*">
				</div>
				<div class="col-11 px-4 my-3">
					<input type="submit" class="btn btn-primary" value="登録">
				</div>
				<div class="col-11 px-4 my-3">
					<a href="SubjectList.action" class="d-block mt-2">
						戻る
					</a>
				</div>
		</form>
	</c:param>
</c:import>