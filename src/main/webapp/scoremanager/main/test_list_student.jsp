<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
  <c:param name="title">成績一覧(学生)</c:param>

  <c:param name="content">

<section class="me-4">

<h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-3">
成績一覧(学生)
</h2>

<!-- ===== 検索フォーム ===== -->
<form action="${pageContext.request.contextPath}/scoremanager/main/TestListStudentExecute.action" method="post">

<!-- 入学年度 -->
入学年度：
<select name="entYear">
  <option value="">----</option>
  <c:forEach var="year" items="${ent_year_set}">
    <option value="${year}"
      <c:if test="${year == f1}">selected</c:if>>
      ${year}
    </option>
  </c:forEach>
</select>

<!-- クラス -->
クラス：
<select name="classNum">
  <option value="">----</option>
  <c:forEach var="c" items="${class_num_set}">
    <option value="${c.classNum}"
      <c:if test="${c.classNum == f2}">selected</c:if>>
      ${c.classNum}
    </option>
  </c:forEach>
</select>

<!-- 科目 -->
科目：
<select name="subjectCd">
  <option value="">----</option>
  <c:forEach var="s" items="${subject_set}">
    <option value="${s.cd}"
      <c:if test="${s.cd == f3}">selected</c:if>>
      ${s.name}
    </option>
  </c:forEach>
</select>

<br><br>

学生番号：
<input type="text" name="studentNo" value="${studentNo}">

<input type="submit" value="検索">

</form>

<!-- ===== エラー ===== -->
<c:if test="${not empty errors}">
  <div style="color:red;">
    <c:forEach var="e" items="${errors}">
      ${e}<br>
    </c:forEach>
  </div>
</c:if>

<hr>

<!-- ===== 学生情報 ===== -->
<c:if test="${student != null}">
  <div>
    氏名：${student.name}（${student.no}）
  </div>
</c:if>

<!-- ===== 成績一覧 ===== -->
<table border="1" width="80%">
<tr>
  <th>科目名</th>
  <th>科目コード</th>
  <th>回数</th>
  <th>点数</th>
</tr>

<c:forEach var="t" items="${test_list}">
<tr>
  <td>${t.subjectName}</td>
  <td>${t.subjectCd}</td>
  <td>${t.no}</td>
  <td>${t.point}</td>
</tr>
</c:forEach>

</table>

</section>
<!-- データなし -->
		<c:if test="${list == null || list.size() == 0}">
			<div class="text-danger px-4 mt-3">
				成績情報が存在しません
			</div>
		</c:if>

		<div class="my-2 text-end px-4">
			<a href="Menu.action">戻る</a>
		</div>
	</c:param>

  </c:param>
</c:import>