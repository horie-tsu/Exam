<%-- 成績参照（科目別）JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

		<!-- 上段：科目情報検索 -->
		 <div class="border px-4 py-3 mb-4" style="border-radius:0;">
    
    <form action="${pageContext.request.contextPath}/scoremanager/main/TestListSubjectExecute.action" method="post">

      <div class="fw-bold mb-2">科目情報</div>

      <div class="d-flex align-items-center flex-wrap">

        <!-- 入学年度 -->
        <div class="me-4 mb-2">
          <span class="me-1">入学年度：</span>
          <select name="f1">
            <option value="">----</option>
            <c:forEach var="year" items="${ent_year_set}">
              <option value="${year}" <c:if test="${year == f1}">selected</c:if>>
                ${year}
              </option>
            </c:forEach>
          </select>
        </div>

        <!-- クラス -->
        <div class="me-4 mb-2 w-20">
          <span class="me-1">クラス：</span>
          <select name="f2">
            <option value="">----</option>
            <c:forEach var="c" items="${class_num_set}">
    <option value="${c}" 
        <c:if test="${c == f2}">selected</c:if>>
        ${c}
    </option>
</c:forEach>
          </select>
        </div>

        <!-- 科目 -->
        <div class="me-4 mb-2">
          <span class="me-1">科目：</span>
          <select name="f3">
            <option value="">----</option>
            <c:forEach var="s" items="${subject_set}">
              <option value="${s.cd}" <c:if test="${s.cd == f3}">selected</c:if>>
                ${s.name}
              </option>
            </c:forEach>
          </select>
        </div>

        <!-- 検索ボタン -->
        <div class="ms-auto mb-2">
          <input type="submit" value="検索" class="btn btn-primary btn-sm">
        </div>

      </div>
    </form>
    </div>

    <!-- Divider line INSIDE the border box -->		