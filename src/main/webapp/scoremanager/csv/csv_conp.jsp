<%-- CSV取り込み完了 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
	<c:param name="title">CSV取り込み完了</c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-normal bg-success bg-opacity-10 py-2 px-4">
				取り込み完了
			</h2>
		</section>

		<div class="px-4">
			CSVの取り込みが正常に完了しました。
		</div>
	</c:param>
</c:import>