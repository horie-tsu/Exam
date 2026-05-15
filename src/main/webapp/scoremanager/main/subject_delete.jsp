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
                科目情報削除
            </h2>

            <p>
                「${subject.name}」を削除してもよろしいですか？
            </p>

            <div class="row mt-4">
                <div class="col-2">

                    <form action="SubjectDeleteExecute.action" method="post">

                        <input type="hidden" name="cd" value="${subject.cd}">

                        <button type="submit" class="btn btn-danger w-100">
                            削除
                        </button>

                    </form>

                    <a href="SubjectList.action" class="d-block mt-3">
                        戻る
                    </a>

                </div>
            </div>

        </section>

    </c:param>

</c:import>