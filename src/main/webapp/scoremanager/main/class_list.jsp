<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
    uri="jakarta.tags.core"%>

<!DOCTYPE html>

<c:import url="/common/base.jsp">

    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">

        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal
                bg-secondary bg-opacity-10
                py-2 px-4">

                クラス管理

            </h2>
            
            <div class="my-2 text-end px-4">
	<a href="Csv.action" class="btn btn-success">
		CSV取り込み
	</a>
</div>

            <div class="my-2 text-end px-4">

                <a href="ClassCreate.action">

                    新規登録

                </a>

            </div>

            <c:choose>

                <c:when test="${not empty classList}">

                    <table class="table table-hover">

                        <tr>
                            <th>学校コード</th>
                            <th>クラス番号</th>
                            <th></th>
                            <th></th>
                        </tr>

                        <c:forEach var="c"
                                   items="${classList}">

                            <tr>

                                <td>
                                    ${c.schoolCd}
                                </td>

                                <td>
                                    ${c.classNum}
                                </td>

                                <td>

                                    <a href="ClassUpdate.action?schoolCd=${c.schoolCd}&classNum=${c.classNum}">

                                        変更

                                    </a>

                                </td>

                                <td>

                                    <a href="ClassDelete.action?schoolCd=${c.schoolCd}&classNum=${c.classNum}">

                                        削除

                                    </a>

                                </td>

                            </tr>

                        </c:forEach>

                    </table>

                </c:when>

                <c:otherwise>

                    <div>
                        クラス情報が存在しませんでした。
                    </div>

                </c:otherwise>

            </c:choose>

        </section>

    </c:param>

</c:import>