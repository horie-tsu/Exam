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
	<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2">学生情報登録</h2>
		<div id="wrap_box">
		<p class="text-center" style="background-color:#66CC99">登録が完了しました。</p>
				
				<a href="Menu.action">戻る</a>
				<a href="StudentList.action">学生一覧</a>
				
		</div>
	</c:param>
	
</c:import>