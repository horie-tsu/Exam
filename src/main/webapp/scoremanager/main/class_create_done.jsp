<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
    uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

    <c:param name="title">
        クラス登録完了
    </c:param>

    <c:param name="content">

        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal
                bg-secondary bg-opacity-10
                py-2 px-4">

                クラス登録完了

            </h2>

            <div class="alert alert-success">

                クラスを登録しました。

            </div>

            <a href="ClassList.action">

                クラス一覧へ

            </a>

        </section>

    </c:param>

</c:import>