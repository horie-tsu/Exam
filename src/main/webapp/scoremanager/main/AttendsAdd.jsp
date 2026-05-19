<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

    <c:param name="title">出欠確認</c:param>

    <c:param name="content">

        <%-- 日付ナビゲーション＆検索フォーム --%>
       <form action="${pageContext.request.contextPath}/scoremanager/main/AttendsList.action"
              method="post">

    <input type="hidden" name="day" value="${day}">

    <div class="d-flex align-items-center px-4 my-3">
        <button type="submit" name="move" value="prev" class="btn btn-outline-secondary btn-sm">
            &lt; 前日
        </button>
        <span class="mx-3 fw-bold">${day}</span>
        <button type="submit" name="move" value="next" class="btn btn-outline-secondary btn-sm">
            翌日 &gt;
        </button>
    </div>

    <div class="d-flex align-items-center flex-wrap px-4 mb-3">

        <div class="me-3 mb-2">
            <label class="form-label mb-1">クラス</label>
            <select name="classNum" class="form-select form-select-sm">
                <option value="">すべて</option>
                <c:forEach var="c" items="${class_num_set}">
                    <option value="${c.classNum}"
                        <c:if test="${c.classNum == classNum}">selected</c:if>>
                        ${c.classNum}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="me-3 mb-2">
            <label class="form-label mb-1">学生名</label>
            <input type="text"
                   name="keyword"
                   class="form-control form-control-sm"
                   maxlength="10"
                   placeholder="キーワードを入力"
                   value="${keyword}">
        </div>

        <div class="mb-2 mt-auto">
            <button type="submit" name="move" value="search" class="btn btn-primary btn-sm">
                検索
            </button>
        </div>

    </div>

</form>

        <%-- エラー表示 --%>
        <c:if test="${not empty errors}">
            <div class="text-danger mt-2 px-4">
                <c:forEach var="e" items="${errors}">
                    ${e}<br>
                </c:forEach>
            </div>
            <hr>
        </c:if>

        <%-- 出欠登録フォーム --%>
        <form action="${pageContext.request.contextPath}/scoremanager/main/AttendsAddExecute.action"
              method="post">
              <input type="hidden" name="classNum" value="${students[0].classNum}">

            <input type="hidden" name="day" value="${day}">

            <c:if test="${not empty students}">
    <div class="px-4 mb-2">
        <button type="button" class="btn btn-outline-secondary btn-sm" onclick="checkAll(true)">全員出席</button>
        <button type="button" class="btn btn-outline-secondary btn-sm" onclick="checkAll(false)">全員欠席</button>
    </div>

   <table class="table table-bordered table-striped w-75 ms-4">
                    <thead class="table-secondary">
                        <tr>
                            <th>学生番号</th>
                            <th>名前</th>
                            <th>出席</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="s" items="${students}">
                            <tr>
                                <td>${s.no}</td>
                                <td>${s.name}</td>
                                <td>
                                    <input type="hidden" name="studentNo[]" value="${s.no}">
                                    <input type="checkbox" name="attend[]" value="${s.no}"
    <c:if test="${attendMap[s.no] != false}">checked</c:if>>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

    <div class="px-4">
        <button type="submit" class="btn btn-secondary">更新する</button>
    </div>
</c:if>

        </form>

    </c:param>
<c:param name="scripts">
<script>
function checkAll(checked) {
    document.querySelectorAll('input[name="attend[]"]').forEach(function(cb) {
        cb.checked = checked;
    });
}
</script>
</c:param>
</c:import>

