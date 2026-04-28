<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
    <section class="me-4">

        <h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">
            テスト成績一覧
        </h2>

        <div class="text-end px-4">
            <a href="TestRegist.action">新規登録</a>
        </div>

        <!-- 検索フォーム -->
        <jsp:include page="test_list_subject.jsp" />
        <jsp:include page="test_list_student.jsp" />

        <!-- ▼ 結果表示 -->
        <c:choose>

        
           <c:when test="${mode == 'subject'}">
    <div>科目：${cdname}</div>

    <table class="table table-bordered">
        <tr>
            <th>入学年</th>
            <th>クラス</th>
            <th>学生番号</th>
            <th>氏名</th>
            <th>一回</th>
            <th>二回</th>
        </tr>

        <c:forEach var="t" items="${test_list}">
            <tr>
                <td>${t.entYear}</td>
                <td>${t.classNum}</td>
                <td>${t.studentNo}</td>
                <td>${t.studentName}</td>
                <td><c:choose>
  <c:when test="${t.points[1] != null}">
    ${t.points[1]}
  </c:when>
  <c:otherwise>-</c:otherwise>
</c:choose></td>
                <td><c:choose>
  <c:when test="${t.points[2] != null}">
    ${t.points[2]}
  </c:when>
  <c:otherwise>-</c:otherwise>
</c:choose></td>
            </tr>
        </c:forEach>
    </table>
</c:when>

          
            <c:when test="${mode == 'student'}">

                <div class="fw-bold">
                    氏名：${student.name}（${student.no}）
                </div>

                <table class="table table-striped">
                    <tr>
                        <th>科目</th>
                        <th>回数</th>
                        <th>点数</th>
                    </tr>

                    <c:forEach var="t" items="${test_list}">
                        <tr>
                            <td>${t.subjectName}</td>
                            <td>${t.no}</td>
                            <td>${t.point}</td>
                        </tr>
                    </c:forEach>
                </table>

            </c:when>

        </c:choose>

    </section>
</c:param>
</c:import>