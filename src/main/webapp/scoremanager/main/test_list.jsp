<%--成績管理 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">
        
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                テスト成績一覧
            </h2>

            <div class="my-2 text-end px-4">
                <a href="TestRegist.action">新規登録</a>
            </div>

            <!-- 絞り込みフォーム -->
            <jsp:include page="test_list_subject.jsp" />
            <jsp:include page="test_list_student.jsp" />

            <!-- 検索結果 -->
            <c:choose>
                <c:when test="${tests.size() > 0}">
                    <div>検索結果：${tests.size()}件</div>

                    <table class="table table-hover">
                        <tr>
                            <th>No</th>
                            <th>学生番号</th>
                            <th>氏名</th>
                            <th>クラス</th>
                            <th>科目</th>
                            <th>点数</th>
                            <th></th>
                        </tr>

                        <c:forEach var="t" items="${tests}">
                            <tr>
                                <td>${t.no}</td>
                                <td>${t.student.no}</td>
                                <td>${t.student.name}</td>
                                <td>${t.classNum}</td>
                                <td>${t.subject.name}</td>
                                <td>${t.point}</td>
                                <td>
                                    <a href="TestUpdate.action?no=${t.no}">変更</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>

                <c:otherwise>
                    <div>テスト情報が存在しませんでした。</div>
                </c:otherwise>
            </c:choose>

        </section>
    </c:param>
</c:import>