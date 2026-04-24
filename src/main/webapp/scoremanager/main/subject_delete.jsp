<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                科目削除情報
            </h2>
            <div>「${param.name}」を削除してもよろしいですか</div>
            <div class="col-2 text-left">
                        <button class="btn btn-danger">削除</button>
            </div>
            <div class="col-2 text-left mt-3">
			<a href="SubjectDelete.action?cd=${param.cd}">
			戻る
			</a>
			</div>
        </section>
    </c:param>
</c:import>