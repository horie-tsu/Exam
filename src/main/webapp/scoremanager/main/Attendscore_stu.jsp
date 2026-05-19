<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

    <c:param name="title">出欠詳細</c:param>

    <c:param name="content">

        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                出欠詳細
            </h2>
        </section>

        <div class="px-4">

            <p class="fw-bold fs-5">${student.name}</p>

            <p>記録日数：${total}日 / 出席日数：${attended}日（${rate}%）</p>

            <%-- 出席ノルマ：15日 --%>
            <c:choose>
                <c:when test="${total - attended > 20}">
                    <div class="p-3 mb-3 text-danger bg-danger bg-opacity-25 border border-danger rounded fw-bold">
                        欠席日数を超過しました。
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="p-3 mb-3 text-success bg-success bg-opacity-25 border border-success rounded fw-bold">
                        進学条件済
                    </div>
                </c:otherwise>
            </c:choose>

            <%-- 出欠一覧 --%>
            <table class="table table-bordered w-50">
                <thead class="table-secondary">
                    <tr>
                        <th>日付</th>
                        <th>出欠</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="a" items="${attendList}">
                        <tr>
                            <td>${a.day}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${a.attend}">
                                        <span class="text-success">出席</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="text-danger">欠席</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <a href="AttendScoreList.action" class="btn btn-outline-secondary btn-sm">戻る</a>

        </div>

    </c:param>

</c:import>