<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                科目情報削除
            </h2>
            <div>「${subject.name}」を削除してもよろしいですか</div>
            	<div class="row mt-3">
            		<div class="col-2 text-left">
                        	<a href = "SubjectDeleteExecute.action?cd=${subject.cd}"" class="btn btn-danger mb-3">削除</button>
            		
					<a href="SubjectList.action" class="d-block mt-5">
						戻る
					</a>
				</div>
			</div>
        </section>
    </c:param>
</c:import>