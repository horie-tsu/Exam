<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 
  <!-- OUTER BORDER BOX (contains BOTH forms) -->
  
  <div class="border px-4 py-3 mb-4" style="border-radius:0;">

   
   
    
    <!-- 下段：学生番号検索 -->
   
    <form action="${pageContext.request.contextPath}/scoremanager/main/TestListStudentExecute.action" method="post">

      <div class="fw-bold mb-2">学生情報</div>

      <div class="d-flex align-items-center flex-wrap">

        <div class="me-3 mb-2">
          <span class="me-1">学生番号：</span>
          <input type="text" name="f4" value="${f4}">
        </div>

        <div class="ms-auto mb-2">
          <input type="submit" value="検索" class="btn btn-primary btn-sm">
        </div>

      </div>
    </form>

  </div> <!-- END OUTER BORDER BOX -->


  
  <!-- エラー表示 -->
  
  <c:if test="${not empty errors}">
    <div class="text-danger mt-2 px-4">
      <c:forEach var="e" items="${errors}">
        ${e}<br>
      </c:forEach>
    </div>
  </c:if>

  <hr>

  
  <!-- 学生情報表示 -->
  
  <c:if test="${student != null}">
    <div class="fw-bold px-4 mb-2">
      氏名：${student.name}（${student.no}）
    </div>
  </c:if>

 
  <!-- 成績一覧テーブル -->

  <c:if test="${student != null}">
    <table class="table table-bordered table-striped w-75 ms-4">
      <thead class="table-secondary">
        <tr>
            <th>科目コード</th>
            <th>科目名</th>
            <th>回数</th>
            <th>点数</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="t" items="${test_list}">
          <tr>
            <td>${t.subject.name}</td>
            <td>${t.subject.cd}</td>
            <td>${t.no}</td>
            <td>${t.point}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </c:if>

  <!-- データなし -->

  <c:if test="${student != null && (test_list == null || test_list.size() == 0)}">
    <div class="text-danger px-4 mt-3">
      成績情報が存在しません
    </div>
  </c:if>
  <!-- 戻る -->

  <div class="my-2 text-end px-4">
    <a href="${pageContext.request.contextPath}/scoremanager/main/TestList.action">戻る</a>
  </div>


  
 
 