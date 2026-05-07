<%-- CSV取り込みエラー --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
	<c:param name="title">CSV取り込みエラー</c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-normal bg-danger bg-opacity-10 py-2 px-4">
				取り込み失敗
			</h2>
		</section>

		<div class="px-4 text-danger">
			処理に失敗しました。<br>
			${error}
		</div>
	</c:param>
</c:import>