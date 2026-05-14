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
	 <section class="me-4">

            <h2 class="h3 mb-3 fw-normal
                bg-secondary bg-opacity-10
                py-2 px-4">

                成績管理

            </h2>
	<label class="d-block p-1 mb-1 text-black bg-success bg-opacity-50 border text-center">
    	<p class="m-0">登録が完了しました</p>
	</label>


			<a href="TestRegist.action">戻る</a>
			<a href="TestList.action">成績参照</a>
			
		</section>
	</c:param>
	
</c:import>