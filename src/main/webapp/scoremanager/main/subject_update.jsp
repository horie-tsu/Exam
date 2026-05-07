<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
  <c:param name="title">得点管理システム</c:param>

  <c:param name="content">
    <section class="me-4">

      <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
      科目情報変更
      </h2>

<!-- エラーメッセージ表示 -->
<c:if test="${not empty errorMsg}">
    <div style="color:red; margin-bottom:10px;">
        ${errorMsg}
    </div>
</c:if>

<!-- <form action="${pageContext.request.contextPath}/scoremanager/main/SubjectUpdateExecute.action" method="post"> -->

<form method="post" action="SubjectUpdateExecute.action">
			<div class="col-11 px-4 my-3">
        		<label class="form-label">科目コード</label>
            	<input 
            		type="text" 
            		name="cd" 
            		class="form-control"
            		value="${cd}"
            		readonly>
            </div>
            <div class="col-11 px-4 my-3">
        		<label class="form-label">科目名</label>
            	<input type="text"
                   name="name"
                   class="form-control"
                   value="${name}"
                   maxlength="20">
             </div>

<!-- ボタン -->
          <div class="col-11 px-4 my-3">
          	<button type="submit" class="btn btn-primary">変更</button>
          </div>
          
        <div class="col-11 px-4 my-3">
			<a href="SubjectList.action">戻る</a>
		</div>

      </form>

    </section>
  </c:param>
</c:import>