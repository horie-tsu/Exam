<%-- 科目登録完了JSP --%>
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
				科目登録完了
			</h2>
		</section>

		<div class="px-4">
			<p>科目の登録が完了しました。</p>
		</div>

		<div class="my-2 text-end px-4">
			<a href="SubjectList.action">一覧へ戻る</a>
		</div>
	</c:param>
</c:import>