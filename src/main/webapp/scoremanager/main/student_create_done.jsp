<%-- JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>
	
	<c:param name="scripts"></c:param>
	
	<c:param name="content">
		登録が完了しました。
		
		<div class="my-2 text-end px-4">
			<a href="StudentList.action">学生一覧へ戻る</a>
		</div>
	</c:param>
	
</c:import>