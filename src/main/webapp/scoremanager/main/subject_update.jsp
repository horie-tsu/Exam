<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

<form action="SubjectUpdateExecute.action" method="post">

<table>
    <tr>
        <td>科目コード</td>
        <td>
            ${cd}
            <input type="hidden" name="cd" value="${cd}">
        </td>
    </tr>
    <tr>
        <td>科目名</td>
        <td>
            <input type="text"
                   name="name"
                   value="${name}"
                   maxlength="20">
        </td>
    </tr>
</table>

<!-- ボタン -->
          <button type="submit" class="btn btn-primary">
            更新
          </button>
          
		<div class="mt-2">
          <a href="${pageContext.request.contextPath}/scoremanager/main/SubjectList.action"
             class="btn btn-secondary ms-2">
            戻る
          </a>

        </div>

      </form>

    </section>
  </c:param>
</c:import>