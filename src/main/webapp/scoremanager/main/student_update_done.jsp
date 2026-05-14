<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
  <c:param name="title">学生情報変更</c:param>

  <c:param name="content">
    <section class="me-4">

      <!-- ① 画面タイトル -->
      <h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">
        学生情報変更
      </h2>
		
      <div class="px-4">

        <!-- ② 完了メッセージ -->
        <label class="d-block p-1 mb-1 text-black bg-success bg-opacity-50 border text-center">
    		<p class="m-0">変更が完了しました</p>
		</label>
       

   
        <a href="StudentList.action" >
          学生一覧
        </a>

      </div>

    </section>
  </c:param>
</c:import>