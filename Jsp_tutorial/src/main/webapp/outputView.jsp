<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

   <table border=1 align=center>
      <tr>
         <th colspan=3>Message List
      </tr>
      <tr>
         <th>Seq
         <th>Writer
         <th>Message
      </tr>

      <c:choose>
         <c:when test="${not empty list }">
            <c:forEach var="i" items="${list }">
               <tr>
                  <td>${i.seq }</td>
                  <td>${i.writer }</td>
                  <td>${i.message }</td>
               </tr>
            </c:forEach>
         </c:when>
         <c:otherwise>
            출력할 내용이 없습니다.
         </c:otherwise>
      </c:choose>
      <tr>
         <td colspan=3>
            <form action="DeleteServlet">
               <input type=text name=delSeq placeholder="Input seq to delete">
               <button>Delete</button>
            </form>
         </td>
      </tr>
      <tr>
         <td colspan=3>
            <form action="UpdateServlet">
               <input type=text name=seq placeholder="Input seq to modify"><br>
               <input type=text name=writer placeholder="Input writer to modify"><br>
               <input type=text name=message placeholder="Input message to modify">
               <button>Modify</button>
            </form>
         </td>
      </tr>
      <tr>
         <td colspan=3 align=center>
            <a href="index.html"><button>Back</button></a>
         </td>
      </tr>

   </table>





</body>
</html>