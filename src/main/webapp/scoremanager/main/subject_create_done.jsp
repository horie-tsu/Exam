<%-- 科目登録完了JSP --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
  <c:param name="title">科目情報登録</c:param>

  <c:param name="content">
    <section class="me-4">

      <!-- ① 画面タイトル -->
      <h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">
        科目情報登録
      </h2>
		
      <div class="px-4">


        <!-- ② 完了メッセージ -->
        <label class="d-block p-1 mb-1 text-black bg-success bg-opacity-50 border text-center">
    		<p class="m-0">登録が完了しました</p>
		</label>
        
        <!-- ③科目登録画面へ -->
        <div class="d-flex gap-4 mt-2">
        <a href="SubjectCreate.action" class="me-5">
          戻る
        </a>
       

        <!-- ④ 科目一覧へ -->
        <a href="SubjectList.action" >
          科目一覧
        </a>
        </div>
      </div>

    </section>
  </c:param>
</c:import>