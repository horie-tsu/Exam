<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

    <c:param name="title">出欠スコア一覧</c:param>

    <c:param name="content">

        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                出欠スコア一覧
            </h2>
        </section>

        <%-- 並び替えフォーム --%>
        <form action="${pageContext.request.contextPath}/scoremanager/main/AttendScoreList.action"
              method="post" class="px-4 mb-3">
            <div class="d-flex gap-2">
                <button type="submit" name="sort" value="bad" class="btn btn-outline-danger btn-sm">
                    出席率やばいやつ順
                </button>
                <button type="submit" name="sort" value="perfect" class="btn btn-outline-success btn-sm">
                    出席率100%のみ（学生番号順）
                </button>
                <button type="submit" name="sort" value="no" class="btn btn-outline-secondary btn-sm">
                    学生番号順
                </button>
            </div>
        </form>

        <%-- 一覧テーブル --%>
        <table class="table table-hover w-75 ms-4">
            <thead class="table-secondary">
                <tr>
                    <th>学生番号</th>
                    <th>名前</th>
                    <th>出席率</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="s" items="${scoreList}">
                    <tr>
                        <td>${s.student.no}</td>
                        <td>${s.student.name}</td>
                        <td>${s.rate}%</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/scoremanager/main/AttendScoreListExecute.action?no=${s.student.no}"
                               class="btn btn-sm btn-outline-primary">
                                詳しく見る
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </c:param>

</c:import>