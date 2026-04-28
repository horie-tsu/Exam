<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
  <c:param name="title">科目情報削除</c:param>

  <c:param name="content">
    <section class="me-4">

      <!-- ① 画面タイトル -->
      <h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">
        科目情報削除
      </h2>
		
      <div class="px-4">

        <!-- ② 完了メッセージ -->
        <p class="alert alert-success text-center">
          削除が完了しました
        </p>
       

        <!-- ④ 科目一覧へ -->
        <a href="SubjectList.action" class="btn btn-primary">
          科目一覧
        </a>

      </div>

    </section>
  </c:param>
</c:import>