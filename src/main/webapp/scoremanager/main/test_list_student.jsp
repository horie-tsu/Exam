<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


  <!-- OUTER BORDER BOX (contains BOTH forms) -->
  
  <div class="border px-4 py-3 mb-4" style="border-radius:0;">

    
    

    
    <!-- 下段：学生番号検索 -->
   
    <form action="${pageContext.request.contextPath}/scoremanager/main/TestListStudentExecute.action" method="post">

      <div class="fw-bold mb-2">学生情報</div>

      <div class="d-flex align-items-center flex-wrap">

        <div class="me-3 mb-2">
          <span class="me-1">学生番号：</span>
          <input type="text" name="f4" value="${f4}">
        </div>

        <div class="ms-auto mb-2">
          <input type="submit" value="検索" class="btn btn-primary btn-sm">
        </div>

      </div>
    </form>

  </div> <!-- END OUTER BORDER BOX -->

  
 
 