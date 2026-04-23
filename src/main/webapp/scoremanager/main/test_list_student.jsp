<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
  <c:param name="title">得点管理システム</c:param>
  <c:param name="scripts"></c:param>

  <c:param name="content">
    <section class="me-4">

      <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
        成績―覧(学生)
      </h2>
<!-- ===== 検索エリア ===== -->
<div style="border:1px solid #ccc; padding:20px; background:#f8f8f8; width:700px;">

  <!-- 科目情報 -->
  <div>
    <label>科目情報</label><br><br>

    入学年度：
    <select name="entYear">
      <option value="">--------</option>
      <c:forEach var="y" items="${ent_year_set}">
        <option value="${y}">${y}</option>
      </c:forEach>
    </select>

    クラス：
    <select name="classNum">
      <option value="">--------</option>
      <c:forEach var="c" items="${class_num_set}">
        <option value="${c}">${c}</option>
      </c:forEach>
    </select>

    科目：
    <select name="subjectCd">
      <option value="">--------</option>
      <c:forEach var="s" items="${subject_set}">
        <option value="${s.cd}">${s.name}</option>
      </c:forEach>
    </select>

    <button disabled>検索</button>
  </div>

  <hr>

  <!-- 学生情報 -->
  <form action="${pageContext.request.contextPath}/TestListStudentExecute.action" method="post">
    学生番号：
    <input type="text" name="studentNo" value="${param.studentNo}">
    <input type="submit" value="検索">

    <!-- ① 入力エラー -->
    <c:if test="${errors != null && errors.size() > 0}">
      <div style="color:red; margin-top:8px;">
        <c:forEach var="e" items="${errors}">
          ${e}<br>
        </c:forEach>
      </div>
    </c:if>
  </form>

</div>

<br>

<!-- ===== 結果エリア ===== -->
<c:if test="${student != null}">

  <div style="border:1px solid #ccc; padding:20px; width:700px;">

    <!-- ① 氏名表示 -->
    <div>
      氏名：${student.name}（${student.no}）
    </div>

    <!-- ② 成績一覧 -->
    <table border="1" width="100%" style="margin-top:10px;">
      <tr style="background:#eee;">
        <th>科目名</th>     <!-- ③ -->
        <th>科目コード</th> <!-- ④ -->
        <th>回数</th>       <!-- ⑤ -->
        <th>点数</th>       <!-- ⑥ -->
      </tr>

      <!-- データあり -->
      <c:if test="${test_list != null && test_list.size() > 0}">
        <c:forEach var="t" items="${test_list}">
          <tr>
            <td>${t.subjectName}</td> <!-- ⑦ -->
            <td>${t.subjectCd}</td>   <!-- ⑧ -->
            <td>${t.no}</td>          <!-- ⑨ -->
            <td>${t.point}</td>       <!-- ⑩ -->
          </tr>
        </c:forEach>
      </c:if>

    </table>

    <!-- データなし -->
    <c:if test="${test_list == null || test_list.size() == 0}">
      <div style="color:red; margin-top:10px;">
        成績情報が存在しません
      </div>
    </c:if>

  </div>

</c:if>

</c:param>
</c:import>