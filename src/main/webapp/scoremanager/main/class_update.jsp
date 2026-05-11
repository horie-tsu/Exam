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

                クラス変更

            </h2>

            <form action="ClassUpdateExecute.action"
                  method="post">

                <!-- 元の学校コード -->
                <input type="hidden"
                       name="schoolCd"
                       value="${classNumData.schoolCd}">

                <!-- 元のクラス番号 -->
                <input type="hidden"
                       name="oldClassNum"
                       value="${classNumData.classNum}">

                <div class="mb-3">

                    <label class="form-label">

                        学校コード

                    </label>

                    <input type="text"
                           class="form-control"
                           value="${classNumData.schoolCd}"
                           readonly>

                </div>

                <div class="mb-3">

                    <label class="form-label">

                        クラス番号

                    </label>

                    <input type="text"
                           name="classNum"
                           class="form-control"
                           value="${classNumData.classNum}">

                </div>

                <div class="text-danger">

                    ${errors.get("classNum")}

                </div>

                <div class="mt-3">

                    <button type="submit"
                            class="btn btn-primary">

                        更新

                    </button>

                    <a href="ClassList.action"
                       class="btn btn-secondary ms-2">

                        戻る

                    </a>

                </div>

            </form>

        </section>

    </c:param>

</c:import>