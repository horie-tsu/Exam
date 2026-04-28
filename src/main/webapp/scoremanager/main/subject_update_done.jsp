<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
  <c:param name="title">科目情報変更</c:param>

  <c:param name="content">
    <section class="me-4">

      <!-- ① 画面タイトル -->
      <h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">
        科目情報変更
      </h2>
		
      <div class="px-4">

        <!-- ② 完了メッセージ -->
        <p class="alert alert-success text-center">
          変更が完了しました
        </p>
       

        <!-- ④ 科目一覧へ -->
        <a href="SubjectList.action" >
          科目一覧
        </a>

      </div>

    </section>
  </c:param>
</c:import>